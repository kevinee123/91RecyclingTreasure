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

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mView = inflater.inflate(layoutId, container, false)
        return mView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews(mView)
        bindinOnClickListener(mView)
        initDatas(mView)
    }

    abstract fun initViews(mView: View)

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

}