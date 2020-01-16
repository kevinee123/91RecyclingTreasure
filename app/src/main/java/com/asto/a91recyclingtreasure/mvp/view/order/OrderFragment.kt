package com.asto.a91recyclingtreasure.mvp.view.order

import android.content.Intent
import android.view.View
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentPagerAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager.widget.ViewPager
import com.asto.a91recyclingtreasure.R
import com.asto.a91recyclingtreasure.adapter.OrderAdapter
import com.asto.a91recyclingtreasure.base.BaseFragment
import com.asto.a91recyclingtreasure.base.BasePresenter
import com.asto.a91recyclingtreasure.bean.OrderCountBean
import com.asto.a91recyclingtreasure.bean.OrderListBean
import com.asto.a91recyclingtreasure.bean.OrderStatisticsBean
import com.asto.a91recyclingtreasure.core.Common
import com.asto.a91recyclingtreasure.mvp.contract.order.OrderContract
import com.asto.a91recyclingtreasure.mvp.presenter.order.OrderPresenter
import com.asto.a91recyclingtreasure.mvp.view.MainActivity
import com.chad.library.adapter.base.BaseQuickAdapter
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.fragment_order.*


/**
 * 作者 ：nxk
 * 日期 ：2020/1/2
 * 备注 ：订单主界面 订单1
 */
class OrderFragment(override val mActivity: MainActivity) : BaseFragment<MainActivity>(),
    OrderContract.View {

    override val mPresenter = OrderPresenter(this)
    override val layoutId: Int
        get() = R.layout.fragment_order

    val mTitleList = listOf("待结算", "待审核", "审核未付", "审核已付")
    var dataList =
        listOf(
            OrderListFragment(mActivity, this, 1),
            OrderListFragment(mActivity, this, 2),
            OrderListFragment(mActivity, this, 3),
            OrderListFragment(mActivity, this, 4)
        )

    override fun loadData() {

    }

    override fun initDatas(mView: View) {
        initTab()
        mPresenter.selectOrderListCount()
        mPresenter.orderStatistics()
    }

    override fun bindinOnClickListener(mView: View) {
        mViewPager?.adapter = object : FragmentPagerAdapter(childFragmentManager) {
            override fun getItem(position: Int): Fragment {
                return dataList[position]
            }

            override fun getCount(): Int {
                return mTitleList.size
            }

            override fun getPageTitle(position: Int): CharSequence {
                return mTitleList[position]
            }
        }
        mViewPager?.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(state: Int) {
            }

            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {

            }

            override fun onPageSelected(position: Int) {
                mTabLayout?.getTabAt(position)?.select()
            }
        })
    }

    /**
     * 初始化TAB
     */
    fun initTab() {
        //导航栏
        for (title in mTitleList) {
            val tab = mTabLayout.newTab()
            val inflate = View.inflate(mActivity, R.layout.tab_order, null)
            val mNameTv = inflate.findViewById<TextView>(R.id.mNameTv)
            val mCountTv = inflate.findViewById<TextView>(R.id.mCountTv)
            mNameTv.text = title
            mCountTv.text = "0"
            tab.customView = inflate
            mTabLayout.addTab(tab)
            if (title == mTitleList[3]){
                mCountTv.visibility = View.GONE
            }
        }
        val tabAt = mTabLayout.getTabAt(0)
        val mNameTvAt = tabAt?.customView?.findViewById<TextView>(R.id.mNameTv)
        mNameTvAt?.setTextColor(resources.getColor(R.color.green_3EAB73))

        //绑定tab点击事件
        mTabLayout?.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabReselected(tab: TabLayout.Tab?) {
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }

            override fun onTabSelected(tab: TabLayout.Tab?) {
                for (i in 0..mTitleList.size) {
                    val tabAt = mTabLayout.getTabAt(i)
                    val mNameTvAt = tabAt?.customView?.findViewById<TextView>(R.id.mNameTv)
                    mNameTvAt?.setTextColor(resources.getColor(R.color.gray_B2B2B2))
                }
                val mNameTv = tab?.customView?.findViewById<TextView>(R.id.mNameTv)
                mNameTv?.setTextColor(resources.getColor(R.color.green_3EAB73))

                mViewPager.currentItem = tab?.position ?: 0
            }
        })
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == Common.REQUEST_ORDER_LIST_FRAGMENT && resultCode == Common.RESULT_REFRESH) {
            dataList[mViewPager.currentItem].mPresenter.selectOrder(
                dataList[mViewPager.currentItem].status,
                1
            )
        }
    }

    /**
     * 刷新列表数量统计
     */
    override fun refreshTitleCount(orderCountBean: OrderCountBean) {
        //待结算
        val tab = mTabLayout.getTabAt(0)
        val mCountTv = tab?.customView?.findViewById<TextView>(R.id.mCountTv)
        if (orderCountBean.settlement == 0) {
            mCountTv?.visibility = View.GONE
        } else {
            mCountTv?.visibility = View.VISIBLE
            mCountTv?.text = orderCountBean.settlement.toString()
        }

        //待审核
        val tab1 = mTabLayout.getTabAt(1)
        val mCountTv1 = tab1?.customView?.findViewById<TextView>(R.id.mCountTv)
        if (orderCountBean.auditNo == 0) {
            mCountTv1?.visibility = View.GONE
        } else {
            mCountTv1?.visibility = View.VISIBLE
            mCountTv1?.text = orderCountBean.auditNo.toString()
        }

        //审核未付
        val tab2 = mTabLayout.getTabAt(2)
        val mCountTv2 = tab2?.customView?.findViewById<TextView>(R.id.mCountTv)
        if (orderCountBean.audit_payNo == 0) {
            mCountTv2?.visibility = View.GONE
        } else {
            mCountTv2?.visibility = View.VISIBLE
            mCountTv2?.text = orderCountBean.audit_payNo.toString()
        }

        //审核已付
        val tab3 = mTabLayout.getTabAt(3)
        val mCountTv3 = tab3?.customView?.findViewById<TextView>(R.id.mCountTv)
        if (orderCountBean.audit_payOk == 0) {
            mCountTv3?.visibility = View.GONE
        } else {
            mCountTv3?.visibility = View.VISIBLE
            mCountTv3?.text = orderCountBean.audit_payOk.toString()
        }
    }

    /**
     * 刷新订单今日统计
     */
    override fun refreshOrderStatistics(orderStatisticsBean: OrderStatisticsBean) {
        mOrderCountTv.text = orderStatisticsBean.count.toString()
        mOrderPayTv.text = orderStatisticsBean.pay.toString()
    }
}