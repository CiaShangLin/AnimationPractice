package com.example.AnimationPractice

import android.animation.LayoutTransition
import android.animation.ObjectAnimator
import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.view.animation.TranslateAnimation
import android.widget.ImageView
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout

//要在Layout第一次加時有動畫 android:layoutAnimation="@anim/layout_animation"
//但是Gone之後再顯示是沒有動畫的
class CDNLayout2 : ConstraintLayout {
    constructor(context: Context?) : super(context)
    {
        init()
    }
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs){
        init()
    }
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ){
        init()
    }

    private fun init(){
        var view= View.inflate(context,R.layout.cdn_layout,this)
        var image=view.findViewById<ImageView>(R.id.imageView2)
        image.setOnClickListener {
            Toast.makeText(context,"料理界沒一個好東西",Toast.LENGTH_SHORT).show()
        }
    }

}