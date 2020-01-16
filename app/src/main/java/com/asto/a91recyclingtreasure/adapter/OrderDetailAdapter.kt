package com.asto.a91recyclingtreasure.adapter

import com.asto.a91recyclingtreasure.R
import com.asto.a91recyclingtreasure.bean.OrderDetail
import com.asto.recyclingbins.core.to2Point
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder

/**
 * 作者 ：nxk
 * 日期 ：2020/1/9
 * 备注 ：订单详情列表
 */
class OrderDetailAdapter(list: List<OrderDetail>,val status : Int) :
    BaseQuickAdapter<OrderDetail, BaseViewHolder>(R.layout.item_order_detail, list) {

    override fun convert(helper: BaseViewHolder, item: OrderDetail) {
        helper.setText(R.id.mTypeTv, item.name)// 待选品类
                .setText(R.id.mDateTv, item.created_at)//时间
            .setText(R.id.mWeightTv,String.format(mContext.getString(R.string.gross_weight_d_unit), to2Point(item.gross_weight)))//毛重
            .setText(R.id.mPriceTv,String.format(mContext.getString(R.string.price_d_unit),to2Point(item.price)))//单价
            .setText(R.id.mSuttleTv,String.format(mContext.getString(R.string.suttle_d_unit),to2Point(item.net_weight)))//净重
            .setText(R.id.mSubtotalTv,String.format(mContext.getString(R.string.rmb_d),item.actual_pay.toString()))//小计
            .addOnClickListener(R.id.mDeleteTv)
            .addOnClickListener(R.id.mDetailTv)
        when(status) {
            //待结算
            1 ->{
                helper.setText(R.id.mDetailTv, mContext.getString(R.string.pricing))
                helper.setVisible(R.id.mDeleteTv, true)
            }
            //待审核
            2 ->{
                helper.setText(R.id.mDetailTv, mContext.getString(R.string.edit))
                helper.setVisible(R.id.mDeleteTv, true)
            }
            //审核未付
            3 ->{
                helper.setText(R.id.mDetailTv, mContext.getString(R.string.look))
                helper.setVisible(R.id.mDeleteTv, true)
            }
            //审核已付
            4 ->{
                helper.setText(R.id.mDetailTv, mContext.getString(R.string.look))
                helper.setVisible(R.id.mDeleteTv, false)
            }
        }
    }

}