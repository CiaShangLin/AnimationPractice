package com.example.AnimationPractice

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.transition.Fade
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dn.planet.Animation.FadeInRightAnimator
import com.dn.planet.Animation.RatioAnimation
import kotlinx.android.synthetic.main.activity_main.view.*
import kotlinx.android.synthetic.main.activity_recyclerview_animation.*

class RecyclerviewAnimationActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recyclerview_animation)

        var list = mutableListOf<Int>()
        for (i in 0..5) {
            list.add(i)
        }

        rv.layoutManager = LinearLayoutManager(this)
        rv.itemAnimator = RatioAnimation()
        rv.adapter = MyAdapter(list)



        add.setOnClickListener {
            list.add(list.size)
            rv.adapter?.notifyItemInserted(list.size)
        }

        delete.setOnClickListener {
            var size=list.size-1
            list.removeAt(size)
            rv.adapter?.notifyItemRemoved(size)
        }

        btChange.setOnClickListener {
            (rv.adapter as MyAdapter).mode=!(rv.adapter as MyAdapter).mode
            (rv.adapter as MyAdapter).notifyItemRangeChanged(0,list.size)
        }


    }


    class MyAdapter(var list: List<Int>) : RecyclerView.Adapter<MyVH>() {
        var mode=false
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyVH {
            return MyVH(LayoutInflater.from(parent.context).inflate(R.layout.item, parent, false))
        }

        override fun getItemCount(): Int {
            return list.size
        }

        override fun onBindViewHolder(holder: MyVH, position: Int) {
            holder.tv.text = "$position"

            if(mode){
                holder.img.visibility=View.VISIBLE
            }else{
                holder.img.visibility=View.GONE
            }
        }
    }

    class MyVH(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tv = itemView.findViewById<TextView>(R.id.textView7)
        var img = itemView.findViewById<ImageView>(R.id.imageView3)
    }
}
