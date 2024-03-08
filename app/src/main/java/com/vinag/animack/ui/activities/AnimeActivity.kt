package com.vinag.animack.ui.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.vinag.animack.adapters.vp.AnimeViewPagerAdapter
import com.vinag.animack.databinding.ActivityAnimeBinding
import com.vinag.animack.viewmodels.anime.AnimeActivityViewModel
import com.google.android.material.tabs.TabLayoutMediator

class AnimeActivity : AppCompatActivity() {
    private lateinit var binding : ActivityAnimeBinding
    private lateinit var animeVM : AnimeActivityViewModel

    override fun onBackPressed() {
        finish()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAnimeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        animeVM = ViewModelProvider(this).get(AnimeActivityViewModel::class.java)

        val animeIMG = intent.getStringExtra("ANIME_IMAGE_URL")
        val animeTitle = intent.getStringExtra("ANIME_TITLE")
        setAnimeImageAndTitle(animeIMG,animeTitle)

        setUpTabLayout()

    }

    private fun setAnimeImageAndTitle(url : String?, animeTitle: String?){
            Glide
                .with(this)
                .load(url)
                .into(binding.ivAnime)

            binding.clToolbar.title = animeTitle
    }



    private fun setUpTabLayout() {
        val adapter = AnimeViewPagerAdapter(supportFragmentManager, lifecycle)
        binding.vpAnime.adapter = adapter


        TabLayoutMediator(binding.tlInfoChars, binding.vpAnime) { tab, position ->
            when (position) {
                0 -> {
                    tab.text = "INFO"
                }
                1 -> {
                    tab.text = "KNOW MORE"
                }
            }
        }.attach()
    }
}