package com.asto.a91recyclingtreasure.widget

import android.app.Activity
import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import com.asto.a91recyclingtreasure.R
import org.jetbrains.anko.find


class HeadView(context: Context, attrs: AttributeSet) : RelativeLayout(context, attrs) {

    private val mLeftIv: ImageView
    private val titleTv: TextView
    private val mRightIv : ImageView

    init {
        LayoutInflater.from(context).inflate(R.layout.view_head, this, true)
        mLeftIv = find(R.id.mLeftIv)
        titleTv = find(R.id.tv_title)
        mRightIv = find(R.id.mRightIv)

        val attributes = context.obtainStyledAttributes(attrs, R.styleable.HeadView)
        titleTv.text = attributes.getString(R.styleable.HeadView_title)
        mLeftIv.setOnClickListener { (context as Activity).finish() }
        mLeftIv.visibility = attributes.getInt(R.styleable.HeadView_show_left, View.VISIBLE)
        setBackgroundColor(context.resources.getColor(R.color.white))
        mRightIv.visibility = attributes.getInt(R.styleable.HeadView_show_right, View.GONE)
    }

    fun setTitle(title: String) {
        titleTv.text = title
    }

    fun setLeftBtnListener(onClickListener: OnClickListener) {
        mLeftIv.setOnClickListener{
            onClickListener.onClick(it)
        }
    }

    fun setRightBtnListener(onClickListener: OnClickListener) {
        mRightIv.setOnClickListener(onClickListener)
    }


}
