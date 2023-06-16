package com.rafial.invento.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.navigation.findNavController
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.rafial.invento.R
import com.rafial.invento.databinding.ActivityMainBinding
import com.rafial.invento.helper.ResultP
import com.rafial.invento.viewmodel.AuthVM
import com.rafial.invento.viewmodel.ProjectVM
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
//    private lateinit var navController: NavController

    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private val authVM: AuthVM by viewModels()
    private val projectVM: ProjectVM by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        supportActionBar?.hide()
            checkToken()
//      val navView: BottomNavigationView = binding.navView
//
//        val navController = findNavController(R.id.nav_host_fragment_activity_main)
//
//        val appBarConfiguration = AppBarConfiguration(
//            setOf(
//                R.id.navigation_home, R.id.navigation_project, R.id.navigation_profile
//            )
//        )
//        setupActionBarWithNavController(navController, appBarConfiguration)
//        navView.setupWithNavController(navController)

    }
    private fun checkToken(){
        authVM.getToken().observe(this) {
            if (it!=null) {
                val intent = Intent(this@MainActivity, LoginActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
                startActivity(intent)
                finish()
            }
        }
    }
    private fun setupView(token: String) {
        projectVM.getAllProject(token).observe(this) {
            if (it is ResultP.Success) {
                val location = it.data.results.origin
                isLocationNull = (location.lat == 0.0 && location.lng == 0.0)
            }
        }

        val recipeId = intent.getStringExtra(EXTRA_RECIPE_ID)
        if (recipeId != null) {
            viewModel.getRecipe(token, recipeId).observe(this) { result ->
                when (result) {
                    is ResultP.Loading -> {
                        binding.progressBar.isVisible = true
                    }
                    is ResultP.Error -> {
                        binding.progressBar.isVisible = false
                        val error = result.error
                        Log.d(TAG, error)
                    }
                    is ResultP.Success -> {
                        showLoading(false)
                        val proj = result.data
                        this.proj = recipe
                        checkIfRecipeBookmarked(token, recipe.id)

                        binding.tvRecipe.text = recipe.title
                        binding.tvDescription.text = recipe.description
                        binding.tvServings.text = "${recipe.servings} servings"

                        binding.rvIngredients.apply {
                            adapter = DetailItemAdapter(recipe.ingredients)
                            layoutManager = LinearLayoutManager(this@DetailActivity)
                        }

                        binding.rvInstructions.apply {
                            adapter = DetailItemAdapter(recipe.steps)
                            layoutManager = LinearLayoutManager(this@DetailActivity)
                        }

                        Glide.with(this)
                            .load(recipe.image)
                            .into(binding.imgRecipe)
                    }
                }
            }
        }
    }


    private fun showLoading(isLoading: Boolean) {
        val loading = binding.pbLoading
        binding.apply {
            loading.visibility = if (isLoading) View.VISIBLE else View.GONE

        }
    }
}