package com.asto.a91recyclingtreasure.adapter

import com.asto.a91recyclingtreasure.R
import com.asto.a91recyclingtreasure.bean.PricingListBean
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder

/**
 * 作者 ：nxk
 * 日期 ：2020/1/4
 * 备注 ：待定价
 */
class PricingAdapter : BaseQuickAdapter<PricingListBean, BaseViewHolder>(R.layout.item_pricing) {

    override fun convert(helper: BaseViewHolder, item: PricingListBean) {
        helper.setText(R.id.mNameTv, item.name)
            .setText(R.id.mDateTv, item.date)
            .setText(R.id.mCountTv, item.weight)
            .addOnClickListener(R.id.mDeleteBtn)
            .addOnClickListener(R.id.mDetailBtn)
    }

}