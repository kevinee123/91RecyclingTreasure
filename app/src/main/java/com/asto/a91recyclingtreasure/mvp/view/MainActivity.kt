package com.asto.a91recyclingtreasure.mvp.view

import com.asto.a91recyclingtreasure.R
import com.asto.a91recyclingtreasure.base.BaseActivity
import com.asto.a91recyclingtreasure.base.BasePresenter
import com.asto.a91recyclingtreasure.mvp.contract.MainContract
import com.asto.a91recyclingtreasure.mvp.presenter.MainPresenter
import com.asto.a91recyclingtreasure.mvp.view.mine.MineFragment
import com.asto.a91recyclingtreasure.mvp.view.order.OrderFragment
import com.asto.a91recyclingtreasure.mvp.view.statistics.StatisticsFragment
import com.asto.recyclingbins.core.addFragment
import com.asto.recyclingbins.core.replaceFragment
import kotlinx.android.synthetic.main.activity_main.*

/**
 * 作者 ：nxk
 * 日期 ：2020/1/2
 * 备注 ：主界面
 */
class MainActivity : BaseActivity(), MainContract.View {
    override val mPresenter: BasePresenter
        get() = MainPresenter(this)
    override val layoutId: Int
        get() = R.layout.activity_main

    var orderFragment: OrderFragment? = null
    var statisticsFragment: StatisticsFragment? = null
    var mineFragment: MineFragment? = null

    var tabPosition = 1//默认显示订单页 0 统计 1 订单 2 我的
    override fun initData() {
        refreshView()
    }

    override fun bindinOnClickListener() {
        mStatisticsTv.setOnClickListener {
            //统计
            tabPosition = 0
            refreshView()
        }
        mOrderTv.setOnClickListener {
            //订单
            tabPosition = 1
            refreshView()
        }
        mMineTv.setOnClickListener {
            //我的
            tabPosition = 2
            refreshView()
        }
    }

    private fun refreshView() {
        when(tabPosition){
            //显示统计
            0 ->{
                if (statisticsFragment == null)
                    statisticsFragment = StatisticsFragment(this)
                replaceFragment(statisticsFragment!!, R.id.mContentLl)
                //todo 改变统计图标和文体颜色 暂无
            }
            //显示订单
            1 ->{
                if (orderFragment == null) {
                    orderFragment = OrderFragment(this)
                    addFragment(orderFragment!!, R.id.mContentLl)
                } else {
                    replaceFragment(orderFragment!!, R.id.mContentLl)
                }

                val drawable0 = resources.getDrawable(R.drawable.icon_statistics)
                drawable0 .setBounds(0, 0, drawable0.minimumWidth,  drawable0.minimumHeight)
                mStatisticsTv.setCompoundDrawables(null,drawable0,null,null)
                mStatisticsTv.setTextColor(resources.getColor(R.color.black))

                val drawable1 = resources.getDrawable(R.drawable.icon_order_selected)
                drawable1 .setBounds(0, 0, drawable1.minimumWidth,  drawable1.minimumHeight)
                mOrderTv.setCompoundDrawables(null,drawable1,null,null)
                mOrderTv.setTextColor(resources.getColor(R.color.green_3EAB73))

                val drawable2 = resources.getDrawable(R.drawable.icon_mine)
                drawable2 .setBounds(0, 0, drawable2.minimumWidth,  drawable2.minimumHeight)
                mMineTv.setCompoundDrawables(null,drawable2,null,null)
                mMineTv.setTextColor(resources.getColor(R.color.black))

            }
            //显示我的
            2 ->{
                if (mineFragment == null)
                    mineFragment = MineFragment(this)
                replaceFragment(mineFragment!!, R.id.mContentLl)

                val drawable0 = resources.getDrawable(R.drawable.icon_statistics)
                drawable0 .setBounds(0, 0, drawable0.minimumWidth,  drawable0.minimumHeight)
                mStatisticsTv.setCompoundDrawables(null,drawable0,null,null)
                mStatisticsTv.setTextColor(resources.getColor(R.color.black))

                val drawable1 = resources.getDrawable(R.drawable.icon_order)
                drawable1 .setBounds(0, 0, drawable1.minimumWidth,  drawable1.minimumHeight)
                mOrderTv.setCompoundDrawables(null,drawable1,null,null)
                mOrderTv.setTextColor(resources.getColor(R.color.black))

                val drawable2 = resources.getDrawable(R.drawable.icon_mine_selected)
                drawable2 .setBounds(0, 0, drawable2.minimumWidth,  drawable2.minimumHeight)
                mMineTv.setCompoundDrawables(null,drawable2,null,null)
                mMineTv.setTextColor(resources.getColor(R.color.green_3EAB73))
            }
        }
    }


}