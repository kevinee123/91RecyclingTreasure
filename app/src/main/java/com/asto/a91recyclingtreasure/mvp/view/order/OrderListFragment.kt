package com.asto.a91recyclingtreasure.mvp.view.order

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.asto.a91recyclingtreasure.R
import com.asto.a91recyclingtreasure.adapter.OrderAdapter
import com.asto.a91recyclingtreasure.base.BaseFragment
import com.asto.a91recyclingtreasure.bean.OrderListBean
import com.asto.a91recyclingtreasure.core.Common
import com.asto.a91recyclingtreasure.mvp.contract.order.OrderListContract
import com.asto.a91recyclingtreasure.mvp.presenter.order.OrderListPresenter
import com.asto.a91recyclingtreasure.mvp.view.MainActivity
import com.asto.recyclingbins.core.showAlertDialog
import kotlinx.android.synthetic.main.fragment_order_list.*

/**
 * 作者 ：nxk
 * 日期 ：2020/1/7
 * 备注 ：订单主页面列表 订单2
 */
class OrderListFragment(override val mActivity: MainActivity,val orderFragment: OrderFragment, val status: Int) :
    BaseFragment<MainActivity>(), OrderListContract.View {
    override val layoutId: Int
        get() = R.layout.fragment_order_list
    override val mPresenter = OrderListPresenter(this)

    var mAdapter: OrderAdapter? = null
    var page = 1
    var totalCount = 0
    var bean: OrderListBean? = null


    override fun initDatas(mView: View) {
        mAdapter = OrderAdapter(ArrayList(),status)
        mRecyclerView.layoutManager = LinearLayoutManager(mActivity)
        mRecyclerView.adapter = mAdapter
        page = 1
        mPresenter.selectOrder(status, page)
        orderFragment.mPresenter.selectOrderListCount()
    }

    override fun bindinOnClickListener(mView: View) {
        mSwipeRefreshLayout.setOnRefreshListener {
            //刷新
            page = 1
            mPresenter.selectOrder(status, page)
            orderFragment.mPresenter.selectOrderListCount()
        }
        mAdapter?.setOnLoadMoreListener {
            //上拉加载
            mPresenter.selectOrder(status, page)
        }
        mAdapter?.setOnItemChildClickListener { adapter, view, position ->
            when(view.id){
                R.id.mDeleteTv ->{
                    //删除
                    showAlertDialog(mActivity,getString(R.string.delete_order_msg)) {
                        it.dismiss()
                        mPresenter.deleteOrderById(mAdapter?.data?.get(position)?.id?:0)
                    }
                }
                R.id.mDetailTv ->{
                    //结算
                    val intent = Intent(mActivity,OrderDetailsListActivity::class.java)
                    val bundle = Bundle()
                    bundle.putInt("orderId", bean?.list?.get(position)?.id?:0)
                    bundle.putInt("status", status)
                    intent.putExtras(bundle)
                    startActivityForResult(intent,Common.REQUEST_ORDER_LIST_FRAGMENT)
                }
            }
        }
    }

    override fun updateList(bean: OrderListBean) {
        this.bean = bean
        mSwipeRefreshLayout.isRefreshing = false
        totalCount = bean.count
        if (page == 1) {
            mAdapter?.setNewData(bean.list)
        } else {
            mAdapter?.addData(bean.list)
        }
        if(mAdapter?.data?.size?:0 >= totalCount){
            mAdapter?.loadMoreEnd()
        }else{
            mAdapter?.loadMoreComplete()
        }
        page++
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == Common.REQUEST_ORDER_LIST_FRAGMENT && resultCode == Common.RESULT_REFRESH) {
            page = 1
            mPresenter.selectOrder(status, page)
            orderFragment.mPresenter.selectOrderListCount()
        }
    }
}