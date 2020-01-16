package com.asto.a91recyclingtreasure.base

import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment

abstract class BaseFragment<A : BaseActivity> : Fragment(),IView {

    abstract val layoutId: Int
    lateinit var mView : View
    abstract val mActivity: A
    abstract val mPresenter : BasePresenter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mView = inflater.inflate(layoutId, container, false)
        return mView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initDatas(mView)
        bindinOnClickListener(mView)
    }

    override fun onDestroy() {
        super.onDestroy()
        mPresenter.detachView()
    }

    abstract fun initDatas(mView : View)

    abstract fun bindinOnClickListener(mView : View)

    override fun showLoading() {
        mActivity.showLoading()
    }

    override fun dismissLoading() {
        mActivity.dismissLoading()
    }

    override fun showToast(msg: String) {
        mActivity.showToast(msg)
    }

    override fun showToast(id: Int) {
        mActivity.showToast(id)
    }

    /**  懒加载  **/
    // 控件是否初始化完成
    var isViewInitiated: Boolean = false
    // 页面是否可见
    var isVisibleToUser: Boolean = false
    // 数据是否加载
    protected var isDataInitiated: Boolean = false

    // 每一次当前Fragment可见时，会调用该方法，并且isVisibleToUser字段会置为true，
    // 我们可以通过此字段判断当前Fragment是否可见；
    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)
        this.isVisibleToUser = isVisibleToUser
        // 当前可见时,就加载一次数据
        prepareFetchData(true)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        isViewInitiated = true
        prepareFetchData(false)
    }

    abstract fun loadData()

    protected fun prepareFetchData(forceUpdate : Boolean) {
        // 由于setUserVisibleHint方法在onActivityCreated等方法之前调用，所以加载数据时机是不太合适的
        // 所以最好是当前Fragment可见，并且当前Fragment中相关View控件都初始化ok时我们在调用数据请求刷新
        // 通过isViewInitiated为true来判断View控件是否初始化成功
        if (isVisibleToUser && isViewInitiated && (!isDataInitiated || forceUpdate) ) {
            loadData()
            isDataInitiated = true
        }
    }

    /**  懒加载  **/
}