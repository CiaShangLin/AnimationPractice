package com.dn.planet.Animation

import android.util.Log
import android.view.View
import android.view.animation.AnimationUtils
import androidx.core.view.ViewCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.AnimationPractice.Animation.BaseItemAnimator
import com.example.AnimationPractice.R
import com.example.AnimationPractice.RecyclerviewAnimationActivity

class RatioAnimation: BaseItemAnimator() {

    var mode=false

    override fun animateChange(
        oldHolder: RecyclerView.ViewHolder?,
        newHolder: RecyclerView.ViewHolder?,
        fromX: Int,
        fromY: Int,
        toX: Int,
        toY: Int
    ): Boolean {
        var myVH=newHolder as RecyclerviewAnimationActivity.MyVH
        Log.d("TAG","$fromX $fromY $toX $toY" )
        if(fromY>toY){
            Log.d("TAG","OUT")
            AnimationUtils.loadAnimation(myVH.itemView.context, R.anim.img_tranlate2).apply {
                myVH.img.startAnimation(this)
            }

        }else{
            Log.d("TAG","IN")
            AnimationUtils.loadAnimation(myVH.itemView.context, R.anim.img_tranlate).apply {
                myVH.img.startAnimation(this)
            }
        }

        return super.animateChange(oldHolder, newHolder, fromX, fromY, toX, toY)
    }

    override fun animateRemoveImpl(holder: RecyclerView.ViewHolder?) {

    }

    override fun animateAddImpl(holder: RecyclerView.ViewHolder?) {

    }
}