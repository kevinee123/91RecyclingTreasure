package com.asto.a91recyclingtreasure.adapter

import com.asto.a91recyclingtreasure.R
import com.asto.a91recyclingtreasure.bean.Product
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder

/**
 * 作者 ：nxk
 * 日期 ：2020/1/9
 * 备注 ：大类
 */
class TypeAdapter(list: List<Product>) : BaseQuickAdapter<Product, BaseViewHolder>(R.layout.item_type, list) {

    override fun convert(helper: BaseViewHolder, item: Product) {
        helper.setText(R.id.mNameTv, item.name)// 大类名称
        if (item.isSelected){
            helper.setBackgroundRes(R.id.mNameTv,R.drawable.shape_btn_green_15)
        }else{
            helper.setBackgroundRes(R.id.mNameTv,R.drawable.shape_btn_gray_15)
        }
    }

}