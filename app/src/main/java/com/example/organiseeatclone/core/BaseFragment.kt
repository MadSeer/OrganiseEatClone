package com.example.organiseeatclone.core

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding

abstract class BaseFragment <T : ViewBinding> : Fragment() {
    protected lateinit var binding: T

    protected val parentActivity: BaseActivity<*> by lazy { requireActivity() as BaseActivity<*> }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = setupBinding(inflater, container)
        binding.initializeLayout()
        return binding.root
    }

    abstract fun setupBinding(layoutInflater: LayoutInflater, container: ViewGroup?): T

    abstract fun T.initializeLayout()
}