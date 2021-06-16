package com.lazypotato.volleysampleapp.ui.user.todos

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.lazypotato.volleysampleapp.data.network.todos.get.GETToDos
import com.lazypotato.volleysampleapp.data.network.todos.get.ToDoResponseListener
import com.lazypotato.volleysampleapp.data.network.todos.model.ToDo
import com.lazypotato.volleysampleapp.databinding.FragmentUserTodosBinding
import com.lazypotato.volleysampleapp.ui.comment.CommentRecyclerViewAdapter
import com.lazypotato.volleysampleapp.ui.user.UserActivity
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class ToDosFragment : Fragment(), ToDoResponseListener {

    private lateinit var toDosViewModel: ToDosViewModel
    private var _binding: FragmentUserTodosBinding? = null

    @Inject lateinit var getToDos: GETToDos

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private var userId: Int = -1

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        toDosViewModel =
            ViewModelProvider(this).get(ToDosViewModel::class.java)

        _binding = FragmentUserTodosBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setRecyclerView()

        userId = (activity as UserActivity).intent.getIntExtra("USER_ID", -1)

        if(userId != -1){
            context?.let { getToDos.requestToDosList(this, userId) }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setRecyclerView() {
        binding.todoRecyclerView.layoutManager = LinearLayoutManager(context)
    }

    override fun onToDoResponse(todos: List<ToDo>, responseCode: Int) {
        context?.let {
            val adapter = ToDoRecyclerViewAdapter(it, todos)
            binding.todoRecyclerView.adapter = adapter
        }
    }

    override fun showProgress(flag: Boolean) {
        if(flag)
            binding.progressBar.visibility = View.VISIBLE
        else
            binding.progressBar.visibility = View.GONE
    }
}