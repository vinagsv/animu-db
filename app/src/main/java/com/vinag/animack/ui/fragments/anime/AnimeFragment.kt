package com.vinag.animack.ui.fragments.anime

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.vinag.animack.adapters.PopularAnimeAdapter
import com.vinag.animack.adapters.RecommendationsAdapter
import com.vinag.animack.databinding.FragmentAnimeBinding
import com.vinag.animack.ui.activities.AnimeActivity
import com.vinag.animack.ui.activities.SearchAnimeActivity
import com.vinag.animack.viewmodels.anime.AnimeFragmentViewModel


class AnimeFragment : Fragment() {
    private lateinit var animeFragmentVM : AnimeFragmentViewModel
    private lateinit var binding : FragmentAnimeBinding
    private lateinit var recommendationsAdapter : RecommendationsAdapter
    private lateinit var popularAdapter : PopularAnimeAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        animeFragmentVM = ViewModelProvider(this).get(AnimeFragmentViewModel::class.java)
        recommendationsAdapter = RecommendationsAdapter()
        popularAdapter = PopularAnimeAdapter()

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAnimeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observeList()
        setupRecommendationsRecyclerView()
        setUpPopularRecyclerView()


        binding.ivSearch.setOnClickListener {
            Intent(activity, SearchAnimeActivity::class.java).also {
                startActivity(it)
            }
        }
    }





    private fun setupRecommendationsRecyclerView() {

        binding.rvRecommendations.apply {
            layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
            adapter = recommendationsAdapter
        }

        recommendationsAdapter.onItemClick = { entry ->

            Intent(activity, AnimeActivity::class.java).apply {
                putExtra("MAL_ID", entry.mal_id)
                putExtra("ANIME_IMAGE_URL", entry.images.jpg.large_image_url)
                putExtra("ANIME_TITLE", entry.title)
                startActivity(this)
            }

        }
    }

    private fun setUpPopularRecyclerView(){
        binding.rvPopularNow.apply {
            layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
            adapter = popularAdapter
        }

        popularAdapter.onItemClick = { animeData ->
            Intent(activity, AnimeActivity::class.java).apply {
                putExtra("MAL_ID", animeData.mal_id)
                putExtra("ANIME_TITLE", animeData.title)
                putExtra("ANIME_IMAGE_URL",animeData.images.jpg.large_image_url)
                startActivity(this)
            }
        }

    }



    private fun observeList() {
        animeFragmentVM.animeRecommendations.observe(viewLifecycleOwner) { list ->
            recommendationsAdapter.differ.submitList(list)

        }


        animeFragmentVM.animePopular.observe(viewLifecycleOwner){ list ->
            popularAdapter.differ.submitList(list)
        }

    }



}