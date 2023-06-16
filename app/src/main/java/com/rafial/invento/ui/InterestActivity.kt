package com.rafial.invento.ui

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowInsets
import android.view.WindowManager
import android.widget.Button
import com.rafial.invento.R
import com.rafial.invento.databinding.ActivityInterestBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class InterestActivity : AppCompatActivity() {
    private val selectedInterests = mutableListOf<String>()
    private lateinit var submitButton: Button
    private val binding: ActivityInterestBinding by lazy {
        ActivityInterestBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        submitButton = binding.btnSubmit
        submitButton.setOnClickListener {
            // Menjalankan logika rekomendasi konten kepada pengguna berdasarkan minat yang dipilih
            recommendContent(selectedInterests)
        }

        // Mendapatkan referensi ke CheckBox untuk minat tertentu
//        val checkboxArt = binding.ckArt
//        val checkboxDesign = binding.ckDesign
//        val checkboxCreativity = binding.ckCreativity
//        val checkboxVehicles = binding.ckVehicles
//        val checkboxBeauty = binding.ckBeauty
//        val checkboxBooks = binding.ckBooks
//        val checkboxBusiness = binding.ckBusiness
//        val checkboxCommunication = binding.ckCommunication
//        val checkboxMusic = binding.ckMusic
//        val checkboxVideo = binding.ckVideo
//        val checkboxEntertainment = binding.ckEntertainment
//        val checkboxFood = binding.ckFood
//        val checkboxDrink = binding.ckDrink
//        val checkboxHealth = binding.ckHealth
//        val checkboxFitness = binding.ckFitness
//        val checkboxHome = binding.ckHome
//        val checkboxLifestyle = binding.ckLifestyle
//        val checkboxGames = binding.ckGames
//        val checkboxSports = binding.ckSports
//        val checkboxEducational = binding.ckEducational
//        val checkboxAudio = binding.ckAudio
//        val checkboxSocial = binding.ckSocial
//        val checkboxPhotography = binding.ckPhotography
//        val checkboxTravel = binding.ckTravel
//        val checkboxTools = binding.ckTools
//        val checkboxProductivity = binding.ckProductivity
//        val checkboxParenting = binding.ckParenting
//        val checkboxTechnology = binding.ckTechnology
//        val checkboxFashion = binding.ckFashion
//        val checkboxEnvironment = binding.ckEnvironment
//        val checkboxScience = binding.ckScience
//        val checkboxSales = binding.ckSales
//        val checkboxManagement = binding.ckManagement
//
//
//        // Menambahkan listener ke setiap CheckBox untuk menangani perubahan pilihan
//        checkboxArt.setOnCheckedChangeListener { _, isChecked ->
//            handleInterestSelection("Art", isChecked)
//        }
//
//        checkboxDesign.setOnCheckedChangeListener { _, isChecked ->
//            handleInterestSelection("Design", isChecked)
//        }
//
//        checkboxCreativity.setOnCheckedChangeListener { _, isChecked ->
//            handleInterestSelection("Creativity", isChecked)
//        }
//
//        checkboxVehicles.setOnCheckedChangeListener { _, isChecked ->
//            handleInterestSelection("Vehicles", isChecked)
//        }
//
//        checkboxBeauty.setOnCheckedChangeListener { _, isChecked ->
//            handleInterestSelection("Beauty", isChecked)
//        }
//
//        checkboxBooks.setOnCheckedChangeListener { _, isChecked ->
//            handleInterestSelection("Books", isChecked)
//        }
//
//        checkboxBusiness.setOnCheckedChangeListener { _, isChecked ->
//            handleInterestSelection("Business", isChecked)
//        }
//
//        checkboxCommunication.setOnCheckedChangeListener { _, isChecked ->
//            handleInterestSelection("Communication", isChecked)
//        }
//
//        checkboxMusic.setOnCheckedChangeListener { _, isChecked ->
//            handleInterestSelection("Music", isChecked)
//        }
//
//        checkboxVideo.setOnCheckedChangeListener { _, isChecked ->
//            handleInterestSelection("Video", isChecked)
//        }
//
//        checkboxEntertainment.setOnCheckedChangeListener { _, isChecked ->
//            handleInterestSelection("Entertainment", isChecked)
//        }
//
//        checkboxFood.setOnCheckedChangeListener { _, isChecked ->
//            handleInterestSelection("Food", isChecked)
//        }
//
//        checkboxDrink.setOnCheckedChangeListener { _, isChecked ->
//            handleInterestSelection("Drink", isChecked)
//        }
//
//        checkboxHealth.setOnCheckedChangeListener { _, isChecked ->
//            handleInterestSelection("Health", isChecked)
//        }
//
//        checkboxFitness.setOnCheckedChangeListener { _, isChecked ->
//            handleInterestSelection("Fitness", isChecked)
//        }
//
//        checkboxHome.setOnCheckedChangeListener { _, isChecked ->
//            handleInterestSelection("Home", isChecked)
//        }
//
//        checkboxLifestyle.setOnCheckedChangeListener { _, isChecked ->
//            handleInterestSelection("Lifestyle", isChecked)
//        }
//
//        checkboxGames.setOnCheckedChangeListener { _, isChecked ->
//            handleInterestSelection("Games", isChecked)
//        }
//        checkboxSports.setOnCheckedChangeListener { _, isChecked ->
//            handleInterestSelection("Sports", isChecked)
//        }
//
//        checkboxEducational.setOnCheckedChangeListener { _, isChecked ->
//            handleInterestSelection("Educational", isChecked)
//        }
//
//        checkboxAudio.setOnCheckedChangeListener { _, isChecked ->
//            handleInterestSelection("Audio", isChecked)
//        }
//
//        checkboxSocial.setOnCheckedChangeListener { _, isChecked ->
//            handleInterestSelection("Social", isChecked)
//        }
//
//        checkboxPhotography.setOnCheckedChangeListener { _, isChecked ->
//            handleInterestSelection("Photography", isChecked)
//        }
//
//        checkboxTravel.setOnCheckedChangeListener { _, isChecked ->
//            handleInterestSelection("Travel", isChecked)
//        }
//
//        checkboxTools.setOnCheckedChangeListener { _, isChecked ->
//            handleInterestSelection("Tools", isChecked)
//        }
//
//        checkboxProductivity.setOnCheckedChangeListener { _, isChecked ->
//            handleInterestSelection("Productivity", isChecked)
//        }
//
//        checkboxParenting.setOnCheckedChangeListener { _, isChecked ->
//            handleInterestSelection("Parenting", isChecked)
//        }
//
//        checkboxTechnology.setOnCheckedChangeListener { _, isChecked ->
//            handleInterestSelection("Technology", isChecked)
//        }
//        checkboxFashion.setOnCheckedChangeListener { _, isChecked ->
//            handleInterestSelection("Fashion", isChecked)
//        }
//
//        checkboxEnvironment.setOnCheckedChangeListener { _, isChecked ->
//            handleInterestSelection("Environment", isChecked)
//        }
//
//        checkboxScience.setOnCheckedChangeListener { _, isChecked ->
//            handleInterestSelection("Science", isChecked)
//        }
//
//        checkboxSales.setOnCheckedChangeListener { _, isChecked ->
//            handleInterestSelection("Sales", isChecked)
//        }
//
//        checkboxManagement.setOnCheckedChangeListener { _, isChecked ->
//            handleInterestSelection("Management", isChecked)
//        }
//
//        // Tambahkan kode serupa untuk CheckBox lainnya

    }


    private fun handleInterestSelection(interest: String, isChecked: Boolean) {
        if (isChecked) {
            // Menambahkan minat ke daftar minat yang dipilih jika checkbox dicentang
            if (!selectedInterests.contains(interest)) {
                selectedInterests.add(interest)
            }
        } else {
            // Menghapus minat dari daftar minat yang dipilih jika checkbox tidak dicentang lagi
            selectedInterests.remove(interest)
        }

        // Mengatur kembali status tombol "Submit" berdasarkan jumlah minat yang dipilih
        submitButton.isEnabled = selectedInterests.size in 1..5
    }
    override fun onResume() {
        super.onResume()
        hideSystemUI()
    }
    private fun hideSystemUI() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            window.insetsController?.hide(WindowInsets.Type.statusBars())
        } else {
            @Suppress("DEPRECATION")
            window.setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
            )
        }
        supportActionBar?.hide()
    }

    private fun recommendContent(selectedInterests: List<String>) {
        // Implementasikan logika rekomendasi konten kepada pengguna berdasarkan minat yang dipilih di sini
        // Anda dapat menggunakan daftar `selectedInterests` untuk melakukan pengolahan dan menghasilkan rekomendasi konten
    }
}
