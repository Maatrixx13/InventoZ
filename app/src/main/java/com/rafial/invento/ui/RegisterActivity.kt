package com.rafial.invento.ui

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.rafial.invento.R
import com.rafial.invento.databinding.ActivityRegisterBinding
import com.rafial.invento.viewmodel.AuthVM
import dagger.hilt.android.AndroidEntryPoint

@Suppress("DEPRECATION")
@AndroidEntryPoint
class RegisterActivity : AppCompatActivity() {
    private val binding: ActivityRegisterBinding by lazy {
        ActivityRegisterBinding.inflate(layoutInflater)
    }
    private val registVM: AuthVM by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        playAnimation()
        setupView()
    }

    private fun playAnimation() {
        ObjectAnimator.ofFloat(binding.ivLogo, View.TRANSLATION_X, -30f, 30f).apply {
            duration = 4000
            repeatCount = ObjectAnimator.INFINITE
            repeatMode = ObjectAnimator.REVERSE
        }.start()

        val edtName = ObjectAnimator.ofFloat(binding.edtName, View.ALPHA, 1f).setDuration(300)
        val edtEmail = ObjectAnimator.ofFloat(binding.edtEmail, View.ALPHA, 1f).setDuration(300)
        val edtPassword =
            ObjectAnimator.ofFloat(binding.edtPassword, View.ALPHA, 1f).setDuration(300)
        val btnRegister = ObjectAnimator.ofFloat(binding.btnRegister, View.ALPHA, 1f).setDuration(300)
        val tvSignup = ObjectAnimator.ofFloat(binding.tvAlready, View.ALPHA, 1f).setDuration(300)
        val signupBtn = ObjectAnimator.ofFloat(binding.btnSignin, View.ALPHA, 1f).setDuration(300)

        AnimatorSet().apply {
            playSequentially(edtName, edtEmail, edtPassword, btnRegister, tvSignup, signupBtn)
            start()
        }
    }

    private fun setupView() {
        binding.btnRegister.setOnClickListener {
            val username = binding.edtName.text.toString()
            val email = binding.edtEmail.text.toString()
            val password = binding.edtPassword.text.toString()
            registVM.register(username, email, password).observe(this) { result ->
                when (result) {
                    is com.rafial.invento.helper.ResultP.Loading -> {
                        showLoading(true)
                    }
                    is com.rafial.invento.helper.ResultP.Error -> {
                        val error = result.error
                        Log.d(TAG, error)
                        showLoading(false)
                        Toast.makeText(this, "Error: $error", Toast.LENGTH_SHORT).show()
                    }

                    is com.rafial.invento.helper.ResultP.Success -> {
                        Toast.makeText(this, getString(R.string.sign_up_success), Toast.LENGTH_SHORT).show()
                        showLoading(false)

                        val intent = Intent(this, LoginActivity::class.java)
                        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
                        startActivity(intent)
                        finish()
                    }
                }
            }
        }

        binding.btnSignin.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(intent)
            finish()
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                return true
            }
        }

        return super.onOptionsItemSelected(item)
    }
    private fun showLoading(isLoading: Boolean) {
        val loading = binding.pbLoading
        binding.apply {
            edtName.isEnabled = !isLoading
            edtEmail.isEnabled = !isLoading
            edtPassword.isEnabled = !isLoading
            btnRegister.isEnabled = !isLoading
            loading.visibility = if (isLoading) View.VISIBLE else View.GONE

        }
    }
    companion object {
        private const val TAG = "RegisterActivity"
    }
}