package com.asto.a91recyclingtreasure.mvp.view.order

import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.ViewPager
import com.asto.a91recyclingtreasure.R
import com.asto.a91recyclingtreasure.base.BaseActivity
import com.asto.a91recyclingtreasure.bean.OrderDetailBean
import com.asto.a91recyclingtreasure.bean.ProductTypeBean
import com.asto.a91recyclingtreasure.mvp.contract.order.OrderDetailContract
import com.asto.a91recyclingtreasure.mvp.presenter.order.OrderDetailPresenter
import com.asto.recyclingbins.core.requestFocusAndShowKeyboard
import com.asto.recyclingbins.core.showAlertDialog
import com.asto.recyclingbins.core.to2Point
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.activity_order_detail.*

/**
 * 作者 ：nxk
 * 日期 ：2020/1/9
 * 备注 ：订单详情页面 订单4
 */
class OrderDetailActivity : BaseActivity(), OrderDetailContract.View {

    override val mPresenter = OrderDetailPresenter(this)
    override val layoutId: Int
        get() = R.layout.activity_order_detail
    var bean: OrderDetailBean? = null
    var productTypeList: List<ProductTypeBean>? = null
    var typeFragmentList = ArrayList<TypeFragment>()
    var productId = 0
    val textWatcher = object : TextWatcher {
        override fun afterTextChanged(p0: Editable?) {
            autoCalculate()
        }

        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
        }

        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
        }

    }

    override fun initData() {
        mPresenter.selectOrderDetailById(intent.extras?.getInt("order_detail_id") ?: 0)
    }

    override fun bindinOnClickListener() {
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
        mActualPriceRl.setOnClickListener {
            //实际总价
            mActualPriceEdt.requestFocusAndShowKeyboard()
        }
        mPriceLl.setOnClickListener {
            //单价
            mPriceEt.requestFocusAndShowKeyboard()
        }
        mPointLl.setOnClickListener {
            //扣点
            mPointEdt.requestFocusAndShowKeyboard()
        }
        mClaspLl.setOnClickListener {
            //扣杂
            mClaspEdt.requestFocusAndShowKeyboard()
        }
        mPriceEt.addTextChangedListener(textWatcher)
        mPointEdt.addTextChangedListener(textWatcher)
        mClaspEdt.addTextChangedListener(textWatcher)
        mHeadView.setRightBtnListener(onClickListener = View.OnClickListener {
            //删除订单详情
            showAlertDialog(this,getString(R.string.delete_detail_msg)) {
                it.dismiss()
                mPresenter.deleteOrderDetailById(intent.extras?.getInt("order_detail_id") ?: 0)
            }
        })
        mSubmitBtn.setOnClickListener {
            //提交
            mPresenter.orderDetailPriceSubmit(
                intent.extras?.getInt("order_detail_id") ?: 0,
                productId,
                mPriceEt.text.toString().toDouble(),
                mPointEdt.text.toString().toDouble(),
                mClaspEdt.text.toString().toDouble(),
                mActualPriceEdt.text.toString().toDouble()
            )
        }
    }

    override fun showDetailView(orderDetailBean: OrderDetailBean) {
        bean = orderDetailBean
        productId = bean?.product_id ?: 0
        mHeadView.setTitle(orderDetailBean.sname)
        mPriceEt.setText(to2Point(orderDetailBean.price))//单价
        mGrossWeightTv.text = to2Point(orderDetailBean.gross_weight)//毛重
        mPointEdt.setText(to2Point(orderDetailBean.point))
        mClaspEdt.setText(to2Point(orderDetailBean.clasp))
        autoCalculate()
        mActualPriceEdt.setText(bean?.actual_pay.toString())
        mPresenter.selectProductAndType()
    }

    override fun showProductView(list: List<ProductTypeBean>) {
        productTypeList = list
        if (productTypeList?.size ?: 0 > 0) {
            productTypeList?.forEach {
                typeFragmentList.add(
                    TypeFragment(
                        this,
                        it.product
                    ) {
                        productId = it
                        notifyDataSetChangedForProduct()
                    }
                )
            }
        }
        //导航栏
        for (title in list) {
            val tab = mTabLayout.newTab()
            tab.text = title.name
            mTabLayout.addTab(tab)
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
                for (i in 0..list.size) {
                    val tabAt = mTabLayout.getTabAt(i)
                    val mNameTvAt = tabAt?.customView?.findViewById<TextView>(R.id.mNameTv)
                    mNameTvAt?.setTextColor(resources.getColor(R.color.gray_B2B2B2))
                }
                val mNameTv = tab?.customView?.findViewById<TextView>(R.id.mNameTv)
                mNameTv?.setTextColor(resources.getColor(R.color.green_3EAB73))

                mViewPager.currentItem = tab?.position ?: 0
            }
        })

        mViewPager?.adapter = object : FragmentPagerAdapter(supportFragmentManager) {
            override fun getItem(position: Int): Fragment {
                typeFragmentList.forEach {
                    it.list.forEach { child ->
                        child.isSelected = false
                    }
                }
                return typeFragmentList[position]
            }

            override fun getCount(): Int {
                return productTypeList?.size ?: 0
            }

            override fun getPageTitle(position: Int): CharSequence {
                return productTypeList?.get(position)?.name ?: ""
            }
        }
        notifyDataSetChangedForProduct()
    }

    fun autoCalculate() {
        try {
            //净重 = 毛重 * （1-扣点/100） - 扣杂
            mSuttleTv.text =
                to2Point(mGrossWeightTv.text.toString().toDouble() * (100 - mPointEdt.text.toString().toDouble()) / 100 - mClaspEdt.text.toString().toDouble())
            //结算总价 = 净重 * 单价
            mSettlementPriceTv.text =
                to2Point(mPriceEt.text.toString().toDouble() * mSuttleTv.text.toString().toDouble())
            //实际总价
            mActualPriceEdt.setText(mSettlementPriceTv.text)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    /**
     * 刷新品类选择界面
     */
    fun notifyDataSetChangedForProduct() {
        productTypeList?.forEach {
            it.product.forEach {
                it.isSelected = productId == it.id
            }
        }
        typeFragmentList.forEach {
            it.adapter?.notifyDataSetChanged()
        }
    }
}