//package com.rafial.invento.ui
//
//import android.content.Intent
//import android.os.Bundle
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import androidx.activity.viewModels
//import androidx.fragment.app.Fragment
//import androidx.fragment.app.viewModels
//import com.rafial.invento.R
//import com.rafial.invento.databinding.FragmentProfileBinding
//import com.rafial.invento.viewmodel.AuthVM
//import dagger.hilt.android.AndroidEntryPoint
//
//@AndroidEntryPoint
//class ProfileFragment : Fragment() {
//
//    private val binding: FragmentProfileBinding by lazy {
//        FragmentProfileBinding.inflate(layoutInflater)
//    }
////    private val viewModel: AuthVM by viewModels()
//    override fun onCreateView(
//        inflater: LayoutInflater, container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? {
//        // Inflate the layout for this fragment
//        return binding.root
//    }
//    private fun getToken() {
////        viewModel.getToken().observe(this) {
////            if (it.length <= 5) {
////                val intent = Intent(this, LoginActivity::class.java)
////                intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
////                startActivity(intent)
////                finish()
////            } else {
////                val token = "Bearer $it"
////                this.token = token
////                setupView()
////            }
////        }
//    }
////
////    override fun onDestroy() {
////        super.onDestroy()
////        binding.root.removeAllViews()
////    }
//    companion object {
//    }
//}