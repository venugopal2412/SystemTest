package io.newsystemtest.filtersapp.ui.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import com.kotlin.kotlinmadeapp.Api.Resource
import io.newsystemtest.filtersapp.api.ApiInterface
import io.newsystemtest.filtersapp.api.Request.ProductCategoryRequest
import io.newsystemtest.filtersapp.api.Response.ProductCategoryResponse
import io.newsystemtest.filtersapp.utils.MyUtliz
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.net.UnknownHostException

class DashBoardViewModel : ViewModel() {
    private val performToGetProductCategoryData =
        MutableLiveData<Resource<Call<ProductCategoryResponse>>>()
    val performToGetProductCategoryStatus: LiveData<Resource<Call<ProductCategoryResponse>>>
        get() = performToGetProductCategoryData


    fun getProductCategoryDetails( mainCategoryId:String?) {
        viewModelScope.launch {
            try {
                performToGetProductCategoryData.value = Resource.loading()
                val apiInterface =
                    ApiInterface.create().getProductDetails(ProductCategoryRequest("en", mainCategoryId))
                apiInterface.enqueue(object : Callback<ProductCategoryResponse> {
                    override fun onResponse(
                        call: Call<ProductCategoryResponse>,
                        response: Response<ProductCategoryResponse>
                    ) {
                        if (response.code() == 200) {
                            var updateResponse = response.body()!!
                            val data = Gson().toJson(updateResponse)
                            performToGetProductCategoryData.value = Resource.data(data)
                        } else {
                            performToGetProductCategoryData.value =
                                Resource.error(
                                    null, message = MyUtliz.getErrorMessage(
                                        response.errorBody()!!.string()
                                    )
                                )
                        }
                    }

                    override fun onFailure(
                        call: Call<ProductCategoryResponse>,
                        t: Throwable
                    ) {
                        performToGetProductCategoryData.value =
                            Resource.error(null, message = t.localizedMessage)
                    }
                })
            } catch (e: Exception) {
                println("Product Category Detail failed ${e.message}")
                if (e is UnknownHostException) {
                    performToGetProductCategoryData.value = Resource.offlineError()
                } else {
                    //different type of error
                    performToGetProductCategoryData.value =
                        Resource.error(e, message = "Something went wrong!")
                }
            }
        }
    }
}