package com.asto.a91recyclingtreasure.mvp.view.mine

import android.view.View
import com.asto.a91recyclingtreasure.R
import com.asto.a91recyclingtreasure.base.BaseFragment
import com.asto.a91recyclingtreasure.mvp.contract.mine.MineContract
import com.asto.a91recyclingtreasure.mvp.presenter.mine.MinePresenter
import com.asto.a91recyclingtreasure.mvp.view.MainActivity
import kotlinx.android.synthetic.main.fragment_mine.*

/**
 * 作者 ：nxk
 * 日期 ：2020/1/17
 * 备注 ：我的界面
 */
class MineFragment(override val mActivity: MainActivity) : BaseFragment<MainActivity>(),
    MineContract.View {
    override val layoutId: Int
        get() = R.layout.fragment_mine
    override val mPresenter = MinePresenter(this)

    override fun initDatas(mView: View) {
    }

    override fun bindinOnClickListener(mView: View) {
        mLoginOutBtn.setOnClickListener {
            //退出登录
            mPresenter.loginOut()
        }
    }

    override fun loadData() {
    }

}