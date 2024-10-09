package com.andy.framework

import android.os.Bundle
import androidx.activity.ComponentActivity

open class BaseActivity : ComponentActivity() {
    private var firstResume = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initData()
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