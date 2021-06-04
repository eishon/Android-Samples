package com.lazypotato.volleysampleapp.ui.user.album

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.lazypotato.volleysampleapp.data.network.albums.get.AlbumResponseListener
import com.lazypotato.volleysampleapp.data.network.albums.get.GETAlbums
import com.lazypotato.volleysampleapp.data.network.albums.model.Album
import com.lazypotato.volleysampleapp.data.network.todos.get.GETToDos
import com.lazypotato.volleysampleapp.databinding.FragmentUserAlbumBinding
import com.lazypotato.volleysampleapp.ui.user.UserActivity
import com.lazypotato.volleysampleapp.ui.user.todos.ToDoRecyclerViewAdapter

class AlbumFragment : Fragment(), AlbumResponseListener {

    private lateinit var albumViewModel: AlbumViewModel
    private var _binding: FragmentUserAlbumBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private var userId: Int = -1

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        albumViewModel =
            ViewModelProvider(this).get(AlbumViewModel::class.java)

        _binding = FragmentUserAlbumBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setRecyclerView()

        userId = (activity as UserActivity).intent.getIntExtra("USER_ID", -1)

        if(userId != -1){
            context?.let { GETAlbums(it, this).requestAlbumsList(userId) }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setRecyclerView() {
        binding.albumRecyclerView.layoutManager = LinearLayoutManager(context)
    }

    override fun onAlbumResponse(albums: List<Album>, responseCode: Int) {
        context?.let {
            val adapter = AlbumRecyclerViewAdapter(it, albums)
            binding.albumRecyclerView.adapter = adapter
        }
    }

    override fun showProgress(flag: Boolean) {
        if(flag)
            binding.progressBar.visibility = View.VISIBLE
        else
            binding.progressBar.visibility = View.GONE
    }
}