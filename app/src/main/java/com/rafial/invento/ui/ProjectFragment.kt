//package com.rafial.invento.ui
//
//import android.content.Intent
//import android.os.Bundle
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import android.widget.Toast
//import androidx.fragment.app.Fragment
//import androidx.fragment.app.viewModels
//import androidx.recyclerview.widget.ItemTouchHelper
//import androidx.recyclerview.widget.RecyclerView
//import com.rafial.invento.R
//import com.rafial.invento.databinding.FragmentProjectBinding
//import com.rafial.invento.viewmodel.AuthVM
//import dagger.hilt.android.AndroidEntryPoint
//
//@AndroidEntryPoint
//class ProjectFragment : Fragment() {
//    private val binding: FragmentProjectBinding by lazy {
//        FragmentProjectBinding.inflate(layoutInflater)
//    }
//    private val projVM: AuthVM by viewModels ()
//    private var token: String? = null
//
//    override fun onCreateView(
//        inflater: LayoutInflater, container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? {
//        return binding.root
//    }
//
//    override fun onResume() {
//        super.onResume()
//        getToken(projVM)
//    }
//
//    override fun onDestroy() {
//        super.onDestroy()
//        binding.root.removeAllViews()
//    }
//
//    private fun getToken(viewModel: AuthVM) {
//        viewModel.getToken().observe(requireActivity()) {
//            if (it == "null") {
//                val intent = Intent(requireContext(), LoginActivity::class.java)
//                intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
//                startActivity(intent)
//            } else {
//                token = "Bearer $it"
////                setupView(viewModel)
//            }
//        }
//    }
//
////    private fun setupView(viewModel: BookmarkViewModel) {
////        if (token != null) {
////
////            ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
////                override fun onMove(
////                    recyclerView: RecyclerView,
////                    viewHolder: RecyclerView.ViewHolder,
////                    target: RecyclerView.ViewHolder
////                ): Boolean {
////                    return false
////                }
////
////                override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
////                    viewModel.deleteBookmarks(token as String, viewHolder.adapterPosition)
////                        .observe(requireActivity()) { result ->
////                            if (result is Result.Success) {
////                                updateList()
////                            }
////                        }
////                }
////            }).attachToRecyclerView(binding.rvBookmark)
////
////            if (activity != null) {
////                updateList()
////            }
////        }
////    }
////
////    private fun updateList() {
////        viewModel.getBookmarks(token as String).observe(requireActivity()) { result ->
////            when (result) {
////                is Result.Loading -> {
////                    binding.progressBar.isVisible = true
////                }
////                is Result.Error -> {
////                    val error = result.error
////                    Toast.makeText(requireContext(), error, Toast.LENGTH_SHORT).show()
////                    binding.progressBar.isVisible = false
////                    binding.imgNoBookmark.isVisible = true
////                }
////                is Result.Success -> {
////                    binding.progressBar.isVisible = false
////                    val data = result.data
////
////                    adapter.submitList(data)
////                    binding.imgNoBookmark.isVisible = data.isEmpty()
////                }
////            }
////        }
////    }
//}
