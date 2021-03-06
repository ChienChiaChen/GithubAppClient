package com.chiachen.githubappclient.presentation.base.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.chiachen.githubappclient.BR
import com.chiachen.githubappclient.R
import com.chiachen.githubappclient.model.Item
import com.chiachen.githubappclient.util.extension.setOnClickListener
import javax.inject.Inject


class ItemAdapter @Inject constructor() : RecyclerView.Adapter<DataBoundViewHolder>() {

    private var mDataset: MutableList<Item> = ArrayList()

    companion object {
        private const val TAG = "ItemAdapter"
    }

    fun update(newData: MutableList<Item>) {
        this.mDataset.addAll(newData)
        notifyDataSetChanged()
        Log.d(TAG, newData.size.toString())
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataBoundViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = DataBindingUtil.inflate<ViewDataBinding>(inflater, viewType, parent, false)
        val viewHolder = DataBoundViewHolder(binding)
        initViewHolderListeners(viewHolder, viewType)
        return viewHolder
    }

    override fun onBindViewHolder(holder: DataBoundViewHolder, position: Int) {
        mDataset.getOrNull(position)?.let { model ->
            bind(holder.binding, model, position)
            holder.binding.executePendingBindings()
        }
    }

    override fun getItemCount(): Int = mDataset.size

    private fun initViewHolderListeners(viewHolder: DataBoundViewHolder, viewType: Int) {
        viewHolder.setOnClickListener(mDataset) { item, _, _ ->
        }
    }

    override fun getItemViewType(position: Int): Int {
        return R.layout.item_github_user
    }

    private fun bind(binding: ViewDataBinding, item: Item, position: Int) {
        binding.setVariable(BR.item, item)
    }

    fun clear() {
        mDataset.clear()
        notifyDataSetChanged()
    }


}
