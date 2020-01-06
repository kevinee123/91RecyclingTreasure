package com.asto.a91recyclingtreasure.mvp.view.order

import android.view.View
import com.asto.a91recyclingtreasure.R
import com.asto.a91recyclingtreasure.adapter.PricingAdapter
import com.asto.a91recyclingtreasure.base.BaseFragment
import com.asto.a91recyclingtreasure.mvp.contract.order.PricingContract
import com.asto.a91recyclingtreasure.mvp.view.MainActivity

/**
 * 作者 ：nxk
 * 日期 ：2020/1/4
 * 备注 ：待定价
 */
class PricingFragment(override val mActivity: MainActivity) : BaseFragment<MainActivity>(),PricingContract.View {
    override val layoutId = R.layout.fragment_pricing
    var pricingAdapter : PricingAdapter? = null

    override fun initViews(mView: View) {

    }

    override fun initDatas(mView: View) {
    }

    override fun bindinOnClickListener(mView: View) {
    }

}