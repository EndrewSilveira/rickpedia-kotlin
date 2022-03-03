package com.personal.rickpedia.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding

abstract class BaseFragment<VB : ViewBinding> : Fragment(), BaseContract.View {

    private var mToast: Toast? = null
    private var mIsLayoutCreated = false
    private var mLayoutView: View? = null
    private var mViewBinding: ViewBinding? = null

    abstract val viewModel: BaseViewModel?
    abstract val bindingInflater: (LayoutInflater) -> VB

    @Suppress("UNCHECKED_CAST")
    protected val binding: VB
        get() = mViewBinding as VB

    override fun onCreate(savedInstanceState: Bundle?) {
        onInitObserver()
        initDefaultObservers()
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        if (mLayoutView == null){
            mViewBinding = bindingInflater.invoke(inflater)
            mLayoutView = requireNotNull(mViewBinding).root
        } else {
            (mLayoutView?.parent as? ViewGroup)?.removeView(mLayoutView)
        }
        return mLayoutView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (!mIsLayoutCreated){
            onInitView()
            onFetchInitialData()
            mIsLayoutCreated = true
        }
    }

    private fun initDefaultObservers() {
        viewModel?.loading?.observe(this, { isLoading ->
            onLoading(isLoading)
        })
    }

    override fun onDestroy() {
        super.onDestroy()
        mViewBinding = null
    }
}