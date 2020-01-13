package com.asto.a91recyclingtreasure.mvp.view.order

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.asto.a91recyclingtreasure.R
import com.asto.a91recyclingtreasure.adapter.OrderDetailAdapter
import com.asto.a91recyclingtreasure.base.BaseActivity
import com.asto.a91recyclingtreasure.bean.OrderDetailListBean
import com.asto.a91recyclingtreasure.core.Common
import com.asto.a91recyclingtreasure.mvp.contract.order.OrderDetailsListContract
import com.asto.a91recyclingtreasure.mvp.presenter.order.OrderDetailsListPresenter
import com.asto.recyclingbins.core.showAlertDialog
import kotlinx.android.synthetic.main.activity_order_details_list.*
import kotlinx.android.synthetic.main.activity_order_details_list.mHeadView

/**
 * 作者 ：nxk
 * 日期 ：2020/1/4
 * 备注 ：订单详情列表 订单3
 */
class OrderDetailsListActivity : BaseActivity(), OrderDetailsListContract.View {

    override val mPresenter = OrderDetailsListPresenter(this)
    override val layoutId: Int
        get() = R.layout.activity_order_details_list

    var mAdapter: OrderDetailAdapter? = null
    var bean: OrderDetailListBean? = null
    var orderId = 0
    var status = 0

    override fun initData() {
        orderId = intent.extras?.getInt("orderId") ?: 0
        status = intent.extras?.getInt("status") ?: 0
        mRecyclerView.layoutManager = LinearLayoutManager(this)
        mAdapter = OrderDetailAdapter(ArrayList(),status)
        mRecyclerView.adapter = mAdapter
        mPresenter.selectOrderDetailById(orderId)
    }

    override fun bindinOnClickListener() {
        mMergerReviewBtn.setOnClickListener {
            //合并审核
            mMergerReviewBtn.isSelected = !mMergerReviewBtn.isSelected
            if (mMergerReviewBtn.isSelected) {
                mAuditPaymentBtn.isSelected = false
            }
        }
        mAuditPaymentBtn.setOnClickListener {
            //审核支付
            mAuditPaymentBtn.isSelected = !mAuditPaymentBtn.isSelected
            if (mAuditPaymentBtn.isSelected) {
                mMergerReviewBtn.isSelected = false
            }
        }
        mHeadView.setRightBtnListener (onClickListener = View.OnClickListener {
            //删除整个订单
            showAlertDialog(this,getString(R.string.delete_order_msg)) {
                it.dismiss()
                mPresenter.deleteOrderById(intent.extras?.getInt("orderId") ?: 0)
            }
        })
        mAdapter?.setOnItemChildClickListener { adapter, view, position ->
            when (view.id) {
                R.id.mDeleteTv -> {
                    //删除
                    showAlertDialog(this,getString(R.string.delete_detail_msg)) {
                        it.dismiss()
                        mPresenter.deleteOrderDetailById(bean?.order?.detail?.get(position)?.id ?: 0)
                    }
                }
                R.id.mDetailTv -> {
                    //编辑
                    val intent = Intent(this, OrderDetailActivity::class.java)
                    val bundle = Bundle()
                    bundle.putInt("order_detail_id", orderId)
                    intent.putExtras(bundle)
                    startActivityForResult(intent,Common.REQUEST_ORDER_DETAILS_LIST_FRAGMENT)
                }
            }
        }
        mSubmitBtn.setOnClickListener {
            //结算
            var status = 1
            if(mMergerReviewBtn.isSelected) status = 2
            else if (mAuditPaymentBtn.isSelected) status = 3

            mPresenter.orderSettlement(bean?.order?.id?:0,status)
        }
    }

    /**
     * 显示界面数据
     */
    override fun showList(orderDetailListBean: OrderDetailListBean) {
        bean = orderDetailListBean
        if (bean?.order?.detail?.size == 0){
            setResult(Common.RESULT_REFRESH)
            finish()
        }
        mAdapter?.setNewData(orderDetailListBean.order.detail)
        mCountTv.text = String.format(
            resources.getString(R.string.record_d),
            orderDetailListBean.order.detail.size
        )
        mHeadView.setTitle(orderDetailListBean.order.name)
        mActualPriceTv.text = bean?.order?.actual_pay?:"0.00"
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == Common.REQUEST_ORDER_DETAILS_LIST_FRAGMENT && resultCode == Common.RESULT_REFRESH) {
            mPresenter.selectOrderDetailById(intent.extras?.getInt("orderId") ?: 0)
        }
    }
}