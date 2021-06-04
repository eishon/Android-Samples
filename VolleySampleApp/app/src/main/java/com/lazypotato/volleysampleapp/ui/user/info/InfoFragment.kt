package com.lazypotato.volleysampleapp.ui.user.info

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.lazypotato.volleysampleapp.data.network.user.get.GETUserInfo
import com.lazypotato.volleysampleapp.data.network.user.get.UserInfoResponseListener
import com.lazypotato.volleysampleapp.data.network.user.model.User
import com.lazypotato.volleysampleapp.databinding.FragmentUserInfoBinding
import com.lazypotato.volleysampleapp.ui.user.UserActivity

class InfoFragment : Fragment(), UserInfoResponseListener {

    private lateinit var infoViewModel: InfoViewModel
    private var _binding: FragmentUserInfoBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private var userId: Int = -1

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        infoViewModel =
            ViewModelProvider(this).get(InfoViewModel::class.java)

        _binding = FragmentUserInfoBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        userId = (activity as UserActivity).intent.getIntExtra("USER_ID", -1)

        if(userId != -1) {
            context?.let { GETUserInfo(it,this).requestUserInfo(userId) }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onUserInfoResponse(user: User?, responseCode: Int) {
        _binding?.user = user
    }

    override fun showProgress(flag: Boolean) {
        if(flag)
            binding.progressBar.visibility = View.VISIBLE
        else
            binding.progressBar.visibility = View.GONE
    }
}