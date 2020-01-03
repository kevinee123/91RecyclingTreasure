package com.asto.a91recyclingtreasure.mvp.view

import android.content.Intent
import com.asto.a91recyclingtreasure.R
import com.asto.a91recyclingtreasure.core.Common
import com.asto.a91recyclingtreasure.mvp.contract.WelcomeContract
import com.asto.a91recyclingtreasure.mvp.presenter.WelcomePresenter
import com.asto.a91recyclingtreasure.util.ASimpleCache
import com.asto.a91recyclingtreasure.base.BaseActivity
import com.asto.recyclingbins.core.immersion

/**
 * 作者 ：nxk
 * 日期 ：2019/12/30
 * 备注 ：欢迎界面
 */
class WelcomeActivity : BaseActivity(),WelcomeContract.View{
    override val mPresenter: WelcomePresenter
        get() = WelcomePresenter(this)
    override val layoutId: Int
        get() = R.layout.activity_welcome

    override fun initData() {
        immersion()
        //TODO 检查自动更新 再进行页面跳转
        next()
    }

    override fun bindinOnClickListener() {
    }

    override fun next() {
        finish()
        if(ASimpleCache.get(this).getAsString(Common.TOKEN) != null){
            //TODO 跳转主界面
        }else{
            startActivity(Intent(this,LoginActivity::class.java))
        }
    }

}