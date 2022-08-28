package com.zziafyc.wanandroid_jetpack.viewholder

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.zhouwei.mzbanner.holder.MZViewHolder
import com.zziafyc.wanandroid_jetpack.R
import com.zziafyc.wanandroid_jetpack.ui.main.home.BannerBean

/**
 * @author zziafyc
 * @date 2021/8/29 0029
 * @description BannerViewHolder
 */
class BannerViewHolder : MZViewHolder<BannerBean> {
    private var mImageView: ImageView? = null
    override fun createView(context: Context): View {
        // 返回页面布局
        val view = LayoutInflater.from(context).inflate(R.layout.item_banner, null)
        mImageView = view.findViewById(R.id.banner_iv)
        return view
    }

    override fun onBind(context: Context, i: Int, model: BannerBean) {
        //设置图片
        Glide.with(context).load(model.imagePath).into(mImageView!!)
    }
}