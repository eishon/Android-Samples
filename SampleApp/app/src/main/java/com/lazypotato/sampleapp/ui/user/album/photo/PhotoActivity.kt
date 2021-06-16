package com.lazypotato.volleysampleapp.ui.user.album.photo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.lazypotato.volleysampleapp.R
import com.lazypotato.volleysampleapp.data.network.photo.get.GETPhotos
import com.lazypotato.volleysampleapp.data.network.photo.get.PhotoResponseListener
import com.lazypotato.volleysampleapp.data.network.photo.model.Photo
import com.lazypotato.volleysampleapp.databinding.ActivityPhotoBinding
import com.lazypotato.volleysampleapp.databinding.ActivityUserBinding
import com.lazypotato.volleysampleapp.ui.comment.CommentRecyclerViewAdapter
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class PhotoActivity : AppCompatActivity(), PhotoResponseListener {

    private lateinit var binding: ActivityPhotoBinding

    @Inject lateinit var getPhotos: GETPhotos

    var albumId: Int = -1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityPhotoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.title = "Photos"

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        getAlbumId()

        setRecyclerView()

        if(albumId != -1) {
            getPhotos.requestPhotosList(this, albumId)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            android.R.id.home -> onBackPressed()
        }

        return true;
    }

    private fun getAlbumId() {
        albumId = intent.getIntExtra("ALBUM_ID", -1)
    }

    private fun setRecyclerView() {
        binding.photoRecyclerView.layoutManager = LinearLayoutManager(this)
    }

    override fun onPhotoResponse(photos: List<Photo>, responseCode: Int) {
        val adapter = PhotoRecyclerViewAdapter(applicationContext, photos)
        binding.photoRecyclerView.adapter = adapter
    }

    override fun showProgress(flag: Boolean) {
        if(flag)
            binding.progressBar.visibility = View.VISIBLE
        else
            binding.progressBar.visibility = View.GONE
    }
}