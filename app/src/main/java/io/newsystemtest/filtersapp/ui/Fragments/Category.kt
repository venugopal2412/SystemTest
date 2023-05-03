package io.newsystemtest.filtersapp.ui.Fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import com.kotlin.kotlinmadeapp.Api.Resource
import io.newsystemtest.filtersapp.api.Response.MainCategoryList
import io.newsystemtest.filtersapp.api.Response.ProductCategoryResponse
import io.newsystemtest.filtersapp.api.Response.SecMainCategoryList
import io.newsystemtest.filtersapp.databinding.FragmentCategoryBinding
import io.newsystemtest.filtersapp.ui.ViewModel.DashBoardViewModel
import io.newsystemtest.filtersapp.ui.adapter.CategoryAdapter
import io.newsystemtest.filtersapp.ui.adapter.SubCategoryAdapter
import io.newsystemtest.filtersapp.utils.ClickPosition
import org.koin.android.viewmodel.ext.android.viewModel

class Category : Fragment(), ClickPosition {
    val dashBoardViewModel: DashBoardViewModel by viewModel()
    private lateinit var binding: FragmentCategoryBinding
    lateinit var categoryAdapter: CategoryAdapter
    var categoryList = ArrayList<MainCategoryList>()
    var subcategoryList = ArrayList<SecMainCategoryList>()
    var type = 0
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCategoryBinding.inflate(inflater, container, false)
        type = 0
        dashBoardViewModel.getProductCategoryDetails("7")
        setObservers()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    private fun setObservers() {
        dashBoardViewModel.performToGetProductCategoryStatus.observe(
            requireActivity(),
            Observer { resource ->
                if (resource != null) {
                    when (resource.status) {
                        Resource.Status.RESULT -> {
                            binding.loader.visibility = View.GONE
                            binding.ContentLL.visibility = View.VISIBLE
                            var productCategoryResponse =
                                Gson().fromJson(
                                    resource.message,
                                    ProductCategoryResponse::class.java
                                )
                            Log.d("setObservers", Gson().toJson(productCategoryResponse))
                            if (type == 0) {
                                categoryList = productCategoryResponse.data!!.mainCategoryList
                                categoryList[0].isSelected = true
                                setCategoryAdapter()
                            }
                            subcategoryList = productCategoryResponse.data!!.secMainCategoryList
                            setSubCategoryAdapter()

                        }
                        Resource.Status.OFFLINE_ERROR -> {
                            binding.loader.visibility = View.GONE
                            binding.ContentLL.visibility = View.VISIBLE
                            Toast.makeText(
                                activity,
                                "No Internet Connection",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                        Resource.Status.ERROR -> {
                            binding.loader.visibility = View.GONE
                            binding.ContentLL.visibility = View.VISIBLE
                            resource.message?.let {
                                Log.d("setObservers", it)
                                Toast.makeText(activity, it, Toast.LENGTH_SHORT).show()
                            } ?: run {
                                Toast.makeText(
                                    activity,
                                    "Something went wrong",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        }
                        Resource.Status.LOADING -> {
                            binding.loader.visibility = View.VISIBLE
                            binding.ContentLL.visibility = View.GONE
                            println("Loading...")
                        }

                        else -> {}
                    }

                }

            })
    }

    fun setCategoryAdapter() {
        binding.CategoryRv.layoutManager = LinearLayoutManager(activity)
        categoryAdapter = CategoryAdapter(categoryList, activity, this)
        binding.CategoryRv.adapter = categoryAdapter
    }

    fun setSubCategoryAdapter() {
        binding.subCategoryRv.layoutManager = GridLayoutManager(activity, 3)
        binding.subCategoryRv.adapter = SubCategoryAdapter(subcategoryList, activity)
    }

    override fun onClickPosition(name: String, position: String, data: String) {
        binding.CategoryNameTv.text = name
        type = 1
        dashBoardViewModel.getProductCategoryDetails(data)

    }
}