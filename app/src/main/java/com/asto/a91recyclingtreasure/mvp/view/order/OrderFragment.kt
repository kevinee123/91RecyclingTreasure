package com.asto.a91recyclingtreasure.mvp.view.order

import android.view.View
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.ViewPager
import com.asto.a91recyclingtreasure.R
import com.asto.a91recyclingtreasure.base.BaseFragment
import com.asto.a91recyclingtreasure.mvp.contract.order.OrderContract
import com.asto.a91recyclingtreasure.mvp.view.MainActivity
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.fragment_order.*


/**
 * 作者 ：nxk
 * 日期 ：2020/1/2
 * 备注 ：订单主界面
 */
class OrderFragment(override val mActivity: MainActivity) : BaseFragment<MainActivity>(),
    OrderContract.View {
    override val layoutId: Int
        get() = R.layout.fragment_order

    val mTitleList = listOf("待结算", "待审核", "审核未付", "审核已付")
    //TODO 后期修改
    val listFragments = listOf<Fragment>(PricingFragment(mActivity),PricingFragment(mActivity),PricingFragment(mActivity),PricingFragment(mActivity))

    override fun initViews(mView: View) {
    }

    override fun initDatas(mView: View) {
        addFragments()
    }

    override fun bindinOnClickListener(mView: View) {
    }

    fun addFragments() {
        //导航栏
        for (title in mTitleList) {
            val tab = mTabLayout.newTab()
            val inflate = View.inflate(mActivity, R.layout.tab_order, null)
            val mNameTv = inflate.findViewById<TextView>(R.id.mNameTv)
            val mCountTv = inflate.findViewById<TextView>(R.id.mCountTv)
            mNameTv.text = title
//       TODO 订单数量     mCountTv.text =
            mCountTv.visibility = View.GONE
            tab.customView = inflate
            mTabLayout.addTab(tab)
        }
        //添加适配器，在viewPager里引入Fragment
        mViewPager?.adapter = object : FragmentPagerAdapter(childFragmentManager) {
            override fun getItem(position: Int): Fragment {
                return listFragments[position]
            }

            override fun getCount(): Int {
                return mTitleList.size
            }

            override fun getPageTitle(position: Int): CharSequence {
                return mTitleList[position]
            }
        }

        //绑定tab点击事件
        mTabLayout?.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabReselected(tab: TabLayout.Tab?) {
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }

            override fun onTabSelected(tab: TabLayout.Tab?) {
                mViewPager?.currentItem = tab?.position!!
            }

        })
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
}