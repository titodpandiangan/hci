package id.hci.homecreditindonesia.utils

import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import id.hci.homecreditindonesia.R

open class Helper {

    companion object{
        fun showImageFromUrl(imageView: ImageView, url:String, context:Context){
            val option = RequestOptions()
                .error(R.drawable.ic_not_found)
                .placeholder(R.drawable.ic_no_image)

            Glide.with(context)
                .load(url)
                .apply(option)
                .centerCrop()
                .into(imageView)
        }

        fun showBanneerFromUrl(imageView: ImageView, url:String, context:Context){
            val option = RequestOptions()
                .error(R.drawable.ic_banner_not_found)
                .placeholder(R.drawable.ic_no_image)

            Glide.with(context)
                .load(url)
                .apply(option)
                .centerCrop()
                .into(imageView)
        }
    }


}