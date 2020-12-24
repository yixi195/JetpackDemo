package com.ysl.fastframe.interfaces

import androidx.fragment.app.Fragment

/**
 * 业务实现此接口，相当于重写BaseFragment
 */
interface IFragmentHelper {
    fun onCreate(f: Fragment?)
    fun onViewCreated(f: Fragment?)
    fun onStart(f: Fragment?)
    fun onResume(f: Fragment?)
    fun onPause(f: Fragment?)
    fun onStop(f: Fragment?)
    fun onDestroyView(f: Fragment?)
    fun onDestroy(f: Fragment?)
}