package xyz.hannanshaikh.rickandmorty.util

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import coil.load
import coil.transform.CircleCropTransformation

@BindingAdapter("imageUrl")
fun imageUrl(imageView: ImageView,imageUrl: String){
    imageView.load(imageUrl){
        transformations(CircleCropTransformation())
    }

}