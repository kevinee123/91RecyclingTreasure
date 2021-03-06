package com.asto.a91recyclingtreasure.adapter

import com.asto.a91recyclingtreasure.R
import com.asto.a91recyclingtreasure.bean.OrderListChildBean
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder

/**
 * 作者 ：nxk
 * 日期 ：2020/1/4
 * 备注 ：待定价
 */
class OrderAdapter(list : List<OrderListChildBean>,val status: Int) : BaseQuickAdapter<OrderListChildBean, BaseViewHolder>(R.layout.item_order,list) {

    override fun convert(helper: BaseViewHolder, item: OrderListChildBean) {
        helper.setText(R.id.mNameTv, item.name)
            .setText(R.id.mDateTv, item.created_at)
            .setText(R.id.mCountTv, item.count.toString())
            .addOnClickListener(R.id.mDeleteTv)
            .addOnClickListener(R.id.mDetailTv)
        when(status){
            //待结算
            1 ->{
                helper.setText(R.id.mDetailTv, mContext.getString(R.string.settle))
            }
            //待审核
            2 ->{
            helper.setText(R.id.mDetailTv, mContext.getString(R.string.audit))
                helper.setVisible(R.id.mNetWeightTv,true)
                helper.setVisible(R.id.mTotalPricesTv,true)
                helper.setText(R.id.mNetWeightTv, String.format(mContext.resources.getString(R.string.net_weight_s_kg),item.net_weight.toString()))
                    .setText(R.id.mTotalPricesTv, String.format(mContext.resources.getString(R.string.total_prices_s_yuan),item.actual_pay.toString()))
            }
            //审核未付
            3 ->{
                helper.setText(R.id.mDetailTv, mContext.getString(R.string.payment))
                helper.setVisible(R.id.mNetWeightTv,true)
                helper.setVisible(R.id.mTotalPricesTv,true)
                helper.setText(R.id.mNetWeightTv, String.format(mContext.resources.getString(R.string.net_weight_s_kg),item.net_weight.toString()))
                    .setText(R.id.mTotalPricesTv, String.format(mContext.resources.getString(R.string.total_prices_s_yuan),item.actual_pay.toString()))
            }
            //审核已付
            4 ->{
                helper.setText(R.id.mDetailTv, mContext.getString(R.string.look))
                helper.setVisible(R.id.mNetWeightTv,true)
                helper.setVisible(R.id.mTotalPricesTv,true)
                helper.setText(R.id.mNetWeightTv, String.format(mContext.resources.getString(R.string.net_weight_s_kg),item.net_weight.toString()))
                    .setText(R.id.mTotalPricesTv, String.format(mContext.resources.getString(R.string.total_prices_s_yuan),item.actual_pay.toString()))

            }
        }
    }

}