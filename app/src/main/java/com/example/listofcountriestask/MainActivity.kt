package com.example.listofcountriestask

import android.os.Build
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.listofcountriestask.data.errorHandling.ApiResult
import com.example.listofcountriestask.data.model.CountriesModel
import com.example.listofcountriestask.databinding.ActivityMainBinding
import com.example.listofcountriestask.ui.Adapter
import com.example.listofcountriestask.ui.ViewModel

class MainActivity : AppCompatActivity() {
    private val adapter = Adapter(CountriesModel())
    lateinit var viewModel: ViewModel
    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        window.statusBarColor = getColor(R.color.red)

        binding.rvCountries.adapter = adapter
        binding.rvCountries.layoutManager = LinearLayoutManager(applicationContext)


        viewModel = ViewModelProvider(this)[ViewModel::class.java]
        viewModel.getCountriesList()
        viewModel.countries.observe(this) { response ->
            when (response) {
                is ApiResult.Success -> {
                    val countries = response.value
                    setUpUI(countries)
                }

                is ApiResult.Failure -> {
                    val errorMessage = response.message ?: "Unknown error message"
                    response.throwable
                    // Handle failure, show error message
                    showError(errorMessage)
                }
            }
        }
    }

    private fun setUpUI(countries: CountriesModel) {

        adapter.updateData(countries)

    }

    fun showError(message: String) {
        val alertDialogBuilder = AlertDialog.Builder(this)
        alertDialogBuilder.setTitle("Error")
        alertDialogBuilder.setMessage(message)
        alertDialogBuilder.setPositiveButton("OK") { dialog, _ ->
            dialog.dismiss()
        }
        val alertDialog = alertDialogBuilder.create()
        alertDialog.show()
    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}
