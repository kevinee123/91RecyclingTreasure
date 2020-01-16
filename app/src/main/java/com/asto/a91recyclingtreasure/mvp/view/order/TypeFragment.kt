package com.asto.a91recyclingtreasure.mvp.view.order

import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import com.asto.a91recyclingtreasure.R
import com.asto.a91recyclingtreasure.adapter.TypeAdapter
import com.asto.a91recyclingtreasure.base.BaseActivity
import com.asto.a91recyclingtreasure.base.BaseFragment
import com.asto.a91recyclingtreasure.base.BasePresenter
import com.asto.a91recyclingtreasure.bean.Product
import kotlinx.android.synthetic.main.fragment_type.*

/**
 * 作者 ：nxk
 * 日期 ：2020/1/9
 * 备注 ：品类
 */
class TypeFragment(
    override val mActivity: BaseActivity,
    val list: List<Product>,
    val status: Int,
    val onSelectedProduct: (productId: Int) -> Unit
) : BaseFragment<BaseActivity>() {
    override val layoutId: Int
        get() = R.layout.fragment_type
    override val mPresenter = BasePresenter(mActivity)
    var adapter: TypeAdapter? = null

    override fun loadData() {
    }

    override fun initDatas(mView: View) {
        val mTypeSmallLayoutManager = GridLayoutManager(mActivity, 4)
        adapter = TypeAdapter(list)
        mRecyclerView.layoutManager = mTypeSmallLayoutManager
        mRecyclerView.adapter = adapter
    }

    override fun bindinOnClickListener(mView: View) {
        adapter?.setOnItemClickListener { adapter, view, position ->
            if (status != 3 && status != 4)
                onSelectedProduct(list[position].id)
        }
    }

}