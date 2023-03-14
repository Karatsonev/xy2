package com.example.x_y

import android.view.View
import android.widget.RadioButton
import androidx.databinding.BindingAdapter
import androidx.lifecycle.MutableLiveData

object BindingAdapters {

    @BindingAdapter("mutableEnabled")
    @JvmStatic
    fun setMutableEnabled(v: View, isEnabled: MutableLiveData<Boolean?>?) {
        if (isEnabled != null && isEnabled.value != null) {
            v.isEnabled = isEnabled.value!!
        }
    }
}