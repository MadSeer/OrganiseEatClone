package com.example.organiseeatclone.core

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.viewbinding.ViewBinding

abstract class BaseActivity<T : ViewBinding> : AppCompatActivity() {
    lateinit var binding: T

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = setupBinding(LayoutInflater.from(this), null)
        setContentView(binding.root)
        binding.initializeLayout()
    }

    abstract fun setupBinding(inflater: LayoutInflater, container: ViewGroup?): T

    abstract fun T.initializeLayout()

}