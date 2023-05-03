package io.newsystemtest.filtersapp.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import io.newsystemtest.filtersapp.R
import io.newsystemtest.filtersapp.api.Response.MainCategoryList
import io.newsystemtest.filtersapp.databinding.FragmentCategoryBinding
import io.newsystemtest.filtersapp.databinding.ItemCategoryAdapterBinding
import io.newsystemtest.filtersapp.utils.ClickPosition

class CategoryAdapter(
    var categoryList: ArrayList<MainCategoryList>,
    var ctx: Context?,
    var clickPosition: ClickPosition,
) :
    RecyclerView.Adapter<CategoryAdapter.ViewHolder>() {
    lateinit var binding: ItemCategoryAdapterBinding

    class ViewHolder(var binding: ItemCategoryAdapterBinding) :
        RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        binding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_category_adapter, parent, false
        )
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return categoryList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.apply {
            if (categoryList[position].isSelected) {
                Bgll.setBackgroundColor(ctx!!.resources.getColor(R.color.white))
                CategoryImg.setColorFilter(
                    ContextCompat.getColor(ctx!!, R.color.selected_bg),
                    android.graphics.PorterDuff.Mode.MULTIPLY
                );
                SideView.visibility = View.VISIBLE
                CategoryNameTv.setTextColor(ctx!!.resources.getColor(R.color.selected_bg))
                clickPosition.onClickPosition(
                    categoryList[position].name!!,
                    position.toString(),
                    categoryList[position].id!!
                )
            } else {
                CategoryImg.setColorFilter(
                    ContextCompat.getColor(ctx!!, R.color.black),
                    android.graphics.PorterDuff.Mode.MULTIPLY
                );
                CategoryNameTv.setTextColor(ctx!!.resources.getColor(R.color.black))
                SideView.visibility = View.INVISIBLE
                Bgll.setBackgroundColor(ctx!!.resources.getColor(R.color.img_bg))
            }

            CategoryNameTv.text = categoryList[position].name
            Picasso.get().load(categoryList[position].icon).into(CategoryImg)

        }
        holder.itemView.setOnClickListener {
            for (i in categoryList.indices) {
                categoryList[i].isSelected = false
            }
            categoryList[position].isSelected = true
            notifyDataSetChanged()
        }
    }
}