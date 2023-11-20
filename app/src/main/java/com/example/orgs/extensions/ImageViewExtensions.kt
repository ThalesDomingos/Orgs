package com.example.orgs.extensions

import android.icu.number.NumberRangeFormatter.RangeIdentityFallback
import android.widget.ImageView
import coil.load
import com.example.orgs.R

fun ImageView.tentaCarregarImagem (url: String? = null, fallback: Int = R.drawable.imagem_padrao) {
    load(url) {
        error(R.drawable.iconejava)
        fallback(fallback)
        placeholder(R.drawable.baseline_refresh)
    }
}