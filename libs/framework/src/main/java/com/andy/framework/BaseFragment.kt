package com.andy.framework

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment

open class BaseFragment : Fragment() {
    private var firstResume = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initData()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        afterInitView()
    }

    override fun onResume() {
        super.onResume()
        if (firstResume) {
            firstResume = false
            firstResume()
        } else {
            afterFirstResume()
        }
    }

    open fun initData() {

    }

    open fun initView() {

    }

    open fun afterInitView() {

    }

    open fun firstResume() {

    }

    open fun afterFirstResume() {

    }
}