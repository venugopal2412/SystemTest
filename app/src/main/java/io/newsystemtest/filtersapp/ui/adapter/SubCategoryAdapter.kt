package io.newsystemtest.filtersapp.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import io.newsystemtest.filtersapp.R
import io.newsystemtest.filtersapp.api.Response.SecMainCategoryList
import io.newsystemtest.filtersapp.databinding.ItemSubCategoryAdapterBinding

class SubCategoryAdapter(var subcategoryList: ArrayList<SecMainCategoryList>,var ctx:Context?) :
    RecyclerView.Adapter<SubCategoryAdapter.ViewHolder>() {
    lateinit var binding: ItemSubCategoryAdapterBinding

    class ViewHolder(var binding: ItemSubCategoryAdapterBinding) :
        RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): SubCategoryAdapter.ViewHolder {
        binding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_sub_category_adapter, parent, false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SubCategoryAdapter.ViewHolder, position: Int) {
       holder.binding.apply {
           SubCategoryNameTv.text = subcategoryList[position].name
           Picasso.get().load(subcategoryList[position].image).into( SubCategoryImg)
       }

        holder
            .itemView.setOnClickListener {
                Toast.makeText(ctx,subcategoryList[position].name,Toast.LENGTH_SHORT).show()
            }
    }

    override fun getItemCount(): Int {
        return subcategoryList.size
    }
}