package com.zziafyc.wanandroid_jetpack.adapter

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.zziafyc.base_library.common.extend.clickNoRepeat
import com.zziafyc.wanandroid_jetpack.R
import com.zziafyc.wanandroid_jetpack.bean.ArticleBean
import com.zziafyc.wanandroid_jetpack.common.getArticleDiff
import com.zziafyc.wanandroid_jetpack.constants.Constants
import com.zziafyc.wanandroid_jetpack.databinding.ItemHomeArticleBinding
import com.zziafyc.wanandroid_jetpack.databinding.ItemProjectBinding

/**
 *
 * @author zziafyc
 * @date 2021/8/13 0013
 * @description 文章adapter
 */
class ArticleAdapter(private val context: Context) :
    ListAdapter<ArticleBean, RecyclerView.ViewHolder>(getArticleDiff()) {


    /**
     * item点击事件
     * @param Int 角标
     * @param View 点击的View
     */
    private var onItemClickListener: ((Int, View) -> Unit)? = null


    /**
     * item中子View点击事件，需要子类做具体触发
     * @param Int 角标
     * @param View 点击的View
     */
    private var onItemChildClickListener: ((Int, View) -> Unit)? = null

    /**
     * 注册item点击事件
     */
    fun setOnItemClickListener(onItemClickListener: ((Int, View) -> Unit)? = null) {
        this.onItemClickListener = onItemClickListener
    }

    /**
     * 注册item子View点击事件
     */
    fun setOnItemChildClickListener(onItemChildClickListener: ((Int, View) -> Unit)? = null) {
        this.onItemChildClickListener = onItemChildClickListener
    }

    /**
     * 创建viewHolder并且与DataBinding绑定
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == Constants.ITEM_ARTICLE) {
            val binding: ItemHomeArticleBinding = DataBindingUtil.inflate(
                LayoutInflater.from(context),
                R.layout.item_home_article,
                parent,
                false
            )
            ArticleViewHolder(binding.root)
        } else {
            val binding: ItemProjectBinding = DataBindingUtil.inflate(
                LayoutInflater.from(context),
                R.layout.item_project,
                parent,
                false
            )
            ArticlePicViewHolder(binding.root)
        }
    }

    /**
     * 将数据和ui进行绑定
     */
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        holder.itemView.clickNoRepeat {
            onItemClickListener?.invoke(position, it)
        }
        //收藏
        holder.itemView.findViewById<View>(R.id.ivCollect)?.clickNoRepeat {
            onItemChildClickListener?.invoke(position, it)
        }
        val binding = if (holder is ArticleViewHolder) {
            //获取ViewDataBinding
            DataBindingUtil.getBinding<ItemHomeArticleBinding>(holder.itemView)?.apply {
                dataBean = getItem(position)
            }
        } else {
            DataBindingUtil.getBinding<ItemProjectBinding>(holder.itemView)?.apply {
                dataBean = getItem(position)
            }
        }
        binding?.executePendingBindings()
    }


    override fun getItemViewType(position: Int): Int {
        return if (currentList[position].itemType == Constants.ITEM_ARTICLE) {
            //普通类型
            Constants.ITEM_ARTICLE
        } else {
            //带图片类型
            Constants.ITEM_ARTICLE_PIC
        }
    }

    /**
     * 默认viewHolder
     */
    class ArticleViewHolder constructor(itemView: View) :
        RecyclerView.ViewHolder(itemView) {

    }

    /**
     * 带图片viewHolder
     */
    class ArticlePicViewHolder constructor(itemView: View) :
        RecyclerView.ViewHolder(itemView) {

    }

    /**
     * 重新加载数据时必须换一个list集合，否则diff不生效
     */
    override fun submitList(list: List<ArticleBean>?) {
        super.submitList(if (list == null) mutableListOf() else
            mutableListOf<ArticleBean>().apply {
                addAll(
                    list
                )
            })
    }

    /**
     * 获取跳转至web界面的bundle
     */
    fun getBundle(position: Int): Bundle {
        return Bundle().apply {
            putString("loadUrl", currentList[position].link)
            putString("title", currentList[position].title)
            putString("author", currentList[position].author)
            putInt("id", currentList[position].id)
        }
    }


}