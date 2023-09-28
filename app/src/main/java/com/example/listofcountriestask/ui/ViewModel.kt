package com.example.listofcountriestask.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.listofcountriestask.data.errorHandling.ApiResult
import com.example.listofcountriestask.data.model.CountriesModel
import com.example.listofcountriestask.data.remote.ApiRequest
import com.example.listofcountriestask.data.remote.RetrofitBuilder
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

class ViewModel : ViewModel() {

    private val _countries = MutableLiveData<ApiResult<CountriesModel>>()
    val countries: LiveData<ApiResult<CountriesModel>> = _countries

    fun getCountriesList() {
        viewModelScope.launch {
            _countries.value = try {
                val retrofitInstance =
                    RetrofitBuilder.getRetrofitInstance().create(ApiRequest::class.java)
                val result = retrofitInstance.getCountriesList()
                ApiResult.Success(result)
            } catch (ioe: IOException) {
                ApiResult.Failure("[IO] error please retry", ioe)
            } catch (he: HttpException) {
                ApiResult.Failure("[HTTP] Error please retry", he)
            }
        }
    }
}
