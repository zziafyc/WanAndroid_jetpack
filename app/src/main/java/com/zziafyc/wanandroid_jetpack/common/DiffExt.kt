package com.zziafyc.wanandroid_jetpack.common

import androidx.recyclerview.widget.DiffUtil
import com.zziafyc.wanandroid_jetpack.bean.ArticleBean

/**
 * des diff扩展函数
 * @author zs
 * @date 2020/9/10
 */


/**
 * 文章diff
 */
fun getArticleDiff(): DiffUtil.ItemCallback<ArticleBean> {
    return object : DiffUtil.ItemCallback<ArticleBean>() {
        override fun areItemsTheSame(
            oldItem: ArticleBean,
            newItem: ArticleBean
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: ArticleBean,
            newItem: ArticleBean
        ): Boolean {
            //只有点赞和时间可能存在改变
            return oldItem.collect == newItem.collect
                    && oldItem.date == newItem.date
        }
    }
}

/**
 * 默认diff，item数据不可能改变
 */
fun <T> getDefaultDiff(): DiffUtil.ItemCallback<T> {
    return object : DiffUtil.ItemCallback<T>() {
        override fun areItemsTheSame(
            oldItem: T,
            newItem: T
        ): Boolean {
            //判断是否为同一个对象，不是直接触发刷新
            return oldItem == newItem
        }

        override fun areContentsTheSame(
            oldItem: T,
            newItem: T
        ): Boolean {
            //同一个对象的数据不可能改变
            return true
        }

    }
}