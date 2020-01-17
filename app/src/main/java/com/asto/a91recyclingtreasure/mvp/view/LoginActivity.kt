package com.asto.a91recyclingtreasure.mvp.view

import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.view.MotionEvent
import com.asto.a91recyclingtreasure.R
import com.asto.a91recyclingtreasure.mvp.contract.LoginContract
import com.asto.a91recyclingtreasure.mvp.presenter.LoginPresenter
import com.asto.a91recyclingtreasure.base.BaseActivity
import com.asto.a91recyclingtreasure.base.BasePresenter
import com.asto.recyclingbins.core.dp2px
import com.asto.recyclingbins.core.immersion
import kotlinx.android.synthetic.main.activity_login.*

/**
 * 作者 ：nxk
 * 日期 ：2019/12/30
 * 备注 ：登录界面
 */
class LoginActivity : BaseActivity(), LoginContract.View {
    override val mPresenter
        get() = LoginPresenter(this)
    override val layoutId: Int
        get() = R.layout.activity_login

    var isPasswordShow = false

    override fun initData() {
        immersion()
    }

    override fun bindinOnClickListener() {
        mPasswordEdt.setOnTouchListener { view, motionEvent ->
            val eyes = mPasswordEdt.compoundDrawables[2]
            //如果是抬起动作，进行触发
            if (motionEvent.action == MotionEvent.ACTION_UP) {
                if (motionEvent.x > mPasswordEdt.width - mPasswordEdt.paddingRight - eyes.intrinsicWidth) {
                    isPasswordShow = !isPasswordShow
                    showOrHidePassword(isPasswordShow)
                }
            }
            false
        }
        mForgotTv.setOnClickListener{
            startForgotPassword()
        }
        mLoginBtn.setOnClickListener {
            mPresenter.login()
        }
    }

    /**
     * 显示或隐藏密码
     */
    override fun showOrHidePassword(isShow: Boolean) {
        if (isShow) {
            //显示密码
            val leftDrawable = resources.getDrawable(R.drawable.icon_password)
            leftDrawable .setBounds(0, 0, leftDrawable.minimumWidth,  leftDrawable.minimumHeight)
            val rightDrawable = resources.getDrawable(R.drawable.icon_eyes)
            rightDrawable .setBounds(0, 0, rightDrawable.minimumWidth,  rightDrawable.minimumHeight)

            mPasswordEdt.setCompoundDrawables(leftDrawable,null,rightDrawable,null)
            mPasswordEdt.transformationMethod =
                HideReturnsTransformationMethod.getInstance()
        } else {
            //隐藏密码
            val leftDrawable = resources.getDrawable(R.drawable.icon_password)
            leftDrawable .setBounds(0, 0, leftDrawable.minimumWidth,  leftDrawable.minimumHeight)
            val rightDrawable = resources.getDrawable(R.drawable.icon_eyes_close)
            rightDrawable .setBounds(0, 0, rightDrawable.minimumWidth,  rightDrawable.minimumHeight)
            mPasswordEdt.setCompoundDrawables(leftDrawable,null,rightDrawable,null)
            mPasswordEdt.transformationMethod =
                PasswordTransformationMethod.getInstance()
        }
    }

    /**
     * 跳转忘记密码
     */
    override fun startForgotPassword() {
        //TODO 跳转忘记密码界面
    }
}