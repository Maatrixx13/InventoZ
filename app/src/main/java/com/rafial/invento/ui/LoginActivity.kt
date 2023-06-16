package com.rafial.invento.ui

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.rafial.invento.R
import com.rafial.invento.databinding.ActivityLoginBinding
import com.rafial.invento.helper.ResultP
import com.rafial.invento.viewmodel.AuthVM
import dagger.hilt.android.AndroidEntryPoint
import kotlin.math.log

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "token")
@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {
    private val binding: ActivityLoginBinding by lazy {
        ActivityLoginBinding.inflate(layoutInflater)
    }


    private val loginVM: AuthVM by viewModels ()
    private lateinit var googleSignInClient: GoogleSignInClient

    private lateinit var authFireBase: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        supportActionBar?.title = "Login"
//====================GOOGLE SIGN IN SERVICE===================
        val gso = GoogleSignInOptions
            .Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()
        googleSignInClient = GoogleSignIn.getClient(this, gso)
        // Initialize Firebase Auth
        authFireBase = Firebase.auth

        binding.btnLoginGoogle.setOnClickListener {
            signIn()
        }
//=====================================================
        setupView()


    }
// ================   FUNCTION SIGN IN GOOGLE  ================
    private fun signIn() {
        val signInIntent = googleSignInClient.signInIntent
        resultLauncher.launch(signInIntent)
    }

    private var resultLauncher: ActivityResultLauncher<Intent> = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            val task: Task<GoogleSignInAccount> = GoogleSignIn.getSignedInAccountFromIntent(result.data)
            try {
                // Google Sign In was successful, authenticate with Firebase
                val account: GoogleSignInAccount = task.getResult(ApiException::class.java)!!
                Log.d(TAG, "firebaseAuthWithGoogle:" + account.id)
                firebaseAuthWithGoogle(account.idToken!!)
            } catch (e: ApiException) {
                // Google Sign In failed, update UI appropriately
                Log.w(TAG, "Google sign in failed", e)
            }
        }
    }
    private fun firebaseAuthWithGoogle(idToken: String) {
        val credential = GoogleAuthProvider.getCredential(idToken, null)
        authFireBase.signInWithCredential(credential)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d(TAG, "signInWithCredential:success")
                    val user = authFireBase.currentUser
                    updateUI(user)
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w(TAG, "signInWithCredential:failure", task.exception)
                    updateUI(null)
                }
            }
    }
//update UI when login success
    private fun updateUI(currentUser: FirebaseUser?) {
        if (currentUser != null){
            startActivity(Intent(this, MainActivity::class.java))
        }
    }

    override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
        val currentUser = authFireBase.currentUser
        updateUI(currentUser)
    }
//=============================================================================
    private fun playAnimation() {
        ObjectAnimator.ofFloat(binding.ivLogo, View.TRANSLATION_X, -30f, 30f).apply {
            duration = 4000
            repeatCount = ObjectAnimator.INFINITE
            repeatMode = ObjectAnimator.REVERSE
        }.start()

        val tvSlogan = ObjectAnimator.ofFloat(binding.tvSlogan, View.ALPHA, 1f).setDuration(300)
        val edtEmail = ObjectAnimator.ofFloat(binding.edtEmail, View.ALPHA, 1f).setDuration(300)
        val edtPassword = ObjectAnimator.ofFloat(binding.edtPassword, View.ALPHA, 1f).setDuration(300)
        val btnLogin = ObjectAnimator.ofFloat(binding.btnSignIn, View.ALPHA, 1f).setDuration(300)
        val signup = ObjectAnimator.ofFloat(binding.tvKet, View.ALPHA, 1f).setDuration(300)
        val signupBtn = ObjectAnimator.ofFloat(binding.btnSignUp, View.ALPHA, 1f).setDuration(300)

        AnimatorSet().apply {
            playSequentially(tvSlogan, edtEmail, edtPassword, btnLogin, signup, signupBtn)
            start()
        }
    }
//    private val loginViewModel: LoginViewModel by viewModels {
//        LoginVMFactory.getInstance(AuthPrefs.getInstance(dataStore))
//    }
    private fun setupView() {
        supportActionBar?.title = "Sign In"
        playAnimation()

    binding.btnSignIn.setOnClickListener {
        val email = binding.etEmail.text.toString()
        val password = binding.etPassword.text.toString()
        loginVM.login(email, password).observe(this) { result ->
            when (result) {
                is ResultP.Loading -> {
                    showLoading(true)
                }
                is ResultP.Error -> {
                    val error = result.error.split(" ")

                    if ("401" in error) {
                        showLoading(false)
                        Toast.makeText(
                            this,
                            getString(R.string.wrong_input),
                            Toast.LENGTH_SHORT
                        ).show()
                    } else {
                        Toast.makeText(this, getString(R.string.signIn_failed), Toast.LENGTH_SHORT).show()
                    }
                }
                is ResultP.Success -> {
                    val token = result.data.token
                    showLoading(false)
                    Log.d(TAG, "Token: $token")
                    loginVM.saveToken(token)

                    Toast.makeText(this, "Login successful", Toast.LENGTH_SHORT).show()
                    val intent = Intent(this@LoginActivity, MainActivity::class.java)
                    startActivity(intent)
                    finish()
                }
            }
        }
//        loginVM.login(email, password).observe(this) {
//            when (it) {
//                is com.rafial.invento.helper.ResultProg.ResultP.Loading -> {
//                    showLoading(true)
//                }
//
//                is com.rafial.invento.helper.ResultProg.ResultP.Error -> {
//                    showLoading(false)
//                    val error = it.error
//                    Toast.makeText(this, error, Toast.LENGTH_SHORT).show()
//                }
//                is com.rafial.invento.helper.ResultProg.ResultP.Success -> {
//                    showLoading(false)
//                    val data = it.data
//                    loginVM.saveToken(data.token)
//                    Log.d("LoginActivity", "Token: ${data.token}")
//                    val intent = Intent(this, MainActivity::class.java)
//                    startActivity(intent)
//                    finish()
//                }
//                }
//
//
//        }
    }

    binding.btnSignUp.setOnClickListener {
        val intent = Intent(this, RegisterActivity::class.java)
        startActivity(intent)
    }
    }



//    private fun checkLogin() {
//        loginViewModel.isLoginState().observe(this) {
//            if (it) {
//                val intent = Intent(this, MainActivity::class.java)
//                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
//                startActivity(intent)
//                finish()
//            }
//            else{
//                Toast.makeText(this@LoginActivity,getString(R.string.login_first),Toast.LENGTH_SHORT).show()
//            }
//        }
//    }




    private fun showLoading(isLoading: Boolean) {
        val loading = binding.pbLoading
        binding.apply {
            edtEmail.isEnabled = !isLoading
//            etEmail.isEnabled = !isLoading

            edtPassword.isEnabled = !isLoading
//            etPassword.isEnabled = !isLoading
            btnSignIn.isEnabled = !isLoading
            loading.visibility = if (isLoading) View.VISIBLE else View.GONE

        }

    }

    override fun onResume() {
        super.onResume()
//        hideSystemUI()
    }
//    private fun hideSystemUI() {
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
//            window.insetsController?.hide(WindowInsets.Type.statusBars())
//        } else {
//            @Suppress("DEPRECATION")
//            window.setFlags(
//                WindowManager.LayoutParams.FLAG_FULLSCREEN,
//                WindowManager.LayoutParams.FLAG_FULLSCREEN
//            )
//        }
//        supportActionBar?.hide()
//    }
    companion object {
        private const val TAG = "LoginActivity"
    }

}



