package com.asto.a91recyclingtreasure.mvp.presenter.mine

import android.annotation.SuppressLint
import com.asto.a91recyclingtreasure.base.BasePresenter
import com.asto.a91recyclingtreasure.mvp.contract.mine.MineContract
import com.asto.a91recyclingtreasure.mvp.view.mine.MineFragment
import com.asto.a91recyclingtreasure.util.RxUtil
import com.asto.recyclingbins.core.loginOut

/**
 * 作者 ：nxk
 * 日期 ：2020/1/17
 * 备注 ：我的界面
 */
class MinePresenter(var view: MineFragment) : BasePresenter(view), MineContract.Presenter {
    @SuppressLint("CheckResult")
    override fun loginOut() {
        if(isViewAttached()){
            view.showLoading()
            model.loginOut().compose(RxUtil.applySchedulers()).subscribe {
                view.dismissLoading()
                if (it.isSuccess){
                     view.mActivity.loginOut()
                } else {
                    view.showToast(it.msg)
                    if (it.isLoginOut) view.mActivity.loginOut()
                }
            }
        }
    }

}