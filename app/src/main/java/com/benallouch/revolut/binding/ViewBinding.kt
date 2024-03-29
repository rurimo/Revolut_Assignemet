package com.benallouch.revolut.binding

import android.view.View
import android.widget.Toast
import androidx.databinding.BindingAdapter
import androidx.lifecycle.LiveData

@BindingAdapter("toast")
fun bindToast(view: View, text: LiveData<String>) {
    text.value?.let {
        Toast.makeText(view.context, it, Toast.LENGTH_SHORT).show()
    }
}

@BindingAdapter("visibilityByResource")
fun bindVisibilityByResource(view: View, list: List<Any>?) {
    when (list) {
        null -> view.visibility = View.VISIBLE
        else -> view.visibility = View.GONE
    }
}