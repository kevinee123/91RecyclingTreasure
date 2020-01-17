package com.asto.a91recyclingtreasure.mvp.view.statistics

import android.view.View
import com.asto.a91recyclingtreasure.R
import com.asto.a91recyclingtreasure.base.BaseFragment
import com.asto.a91recyclingtreasure.mvp.contract.statistics.StatisticsContract
import com.asto.a91recyclingtreasure.mvp.presenter.statistics.StatisticsPresenter
import com.asto.a91recyclingtreasure.mvp.view.MainActivity

/**
 * 作者 ：nxk
 * 日期 ：2020/1/17
 * 备注 ：统计界面
 */
class StatisticsFragment(override val mActivity: MainActivity) : BaseFragment<MainActivity>(),
    StatisticsContract.View {
    override val layoutId: Int
        get() = R.layout.fragment_statistics
    override val mPresenter = StatisticsPresenter(this)

    override fun initDatas(mView: View) {
    }

    override fun bindinOnClickListener(mView: View) {
    }

    override fun loadData() {
    }

}