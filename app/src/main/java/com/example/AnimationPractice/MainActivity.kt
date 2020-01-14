package com.example.AnimationPractice

import android.animation.*
import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.animation.*
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    fun View.dip2px(dpValue: Int): Int {
        var scale = context.resources.displayMetrics.density
        return (dpValue * scale + 0.5f).toInt()
    }

    //https://github.com/yangchong211/YCBlogs/blob/master/android/%E5%8A%A8%E7%94%BB%E6%9C%BA%E5%88%B6/01.%E5%8A%A8%E7%94%BB%E6%9C%BA%E5%88%B6%E6%80%BB%E7%BB%93.md
    //https://wiki.jikexueyuan.com/project/android-animation/1.html
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        //TEST

        button2.setOnClickListener {
            CDNLAYOUT2.visibility=View.GONE
        }

        button3.setOnClickListener {
            CDNLAYOUT2.visibility=View.VISIBLE
//            button2.visibility = View.VISIBLE
//            translateCode()
        }
    }

    @SuppressLint("WrongConstant")
    fun 拉伸Layoyt(nowHeight: Int, newHeight: Int) {
        var valueAnimator = ValueAnimator.ofInt(nowHeight, newHeight)
        valueAnimator.addListener(object : Animator.AnimatorListener {
            override fun onAnimationEnd(animation: Animator?) {

            }

            override fun onAnimationCancel(animation: Animator?) {

            }

            override fun onAnimationStart(animation: Animator?) {
                textView2.visibility = View.VISIBLE
                textView3.visibility = View.VISIBLE
                textView4.visibility = View.VISIBLE
            }

            override fun onAnimationRepeat(animation: Animator?) {

            }
        })
        valueAnimator.addUpdateListener {
            var parma = con.layoutParams
            parma.height = it.animatedValue as Int
            con.layoutParams = parma
        }
        valueAnimator.duration = 1000
        valueAnimator.repeatMode = Animation.RESTART
        valueAnimator.start()
    }


    fun Layout彈跳() {
        var translateAnimation = TranslateAnimation(
            0f,    //属性为动画起始时 X坐标上的位置
            0f,      //属性为动画结束时 X坐标上的位置
            -400f,  //属性为动画起始时 Y坐标上的位置
            0f       //属性为动画结束时 Y坐标上的位置
        )
        translateAnimation.duration = 1000
        translateAnimation.interpolator = BounceInterpolator()
        translateAnimation.repeatCount = Animation.INFINITE
        translateAnimation.repeatMode = Animation.REVERSE
        CDN.startAnimation(translateAnimation)
    }

    fun 客製化TypeEvaluator() {
        var valueAnimator = ValueAnimator.ofObject(CharEvaluator(), 'A', 'Z')
        valueAnimator.addUpdateListener {
            tv.text = it.animatedValue.toString()
        }
        valueAnimator.setTarget(tv)
        valueAnimator.duration = 3000
        valueAnimator.interpolator = AccelerateInterpolator()
        valueAnimator.start()
    }

    /*
    * 補間動畫
    * 1.透明
    * 2.縮放
    * 3.移動
    * 4.旋轉
    * Interpolator 插值器 決定值的變化模式(加速,均速...)
    * 在xml裡面animation原生的屬性不會自動出現,例如:duration
    * 在set裡面有一些值可以設定,例如:duration
    * 只能對view使用
    */

    //透明動畫
    fun alphaXML() {
        var ani = AnimationUtils.loadAnimation(this, R.anim.alpha)
        button2.startAnimation(ani)
    }

    fun alphaCode() {
        var alphaAnimation = AlphaAnimation(0.0f, 1.0f)
        alphaAnimation.duration = 5000
        alphaAnimation.fillAfter = true //結束後保留狀態
        alphaAnimation.interpolator = AccelerateInterpolator() //添加插值器 加速，开始时慢中间加速
        button2.startAnimation(alphaAnimation)
    }
    //---------------------------------------------------------------------------------------//

    //縮放動畫
    fun scaleXML() {
        var ani = AnimationUtils.loadAnimation(this, R.anim.scale)
        button2.startAnimation(ani)
    }

    fun scaleCode() {
        var scaleAlphaAnimation = ScaleAnimation(
            0.0f,  // 属性为动画起始时 X坐标上的伸缩尺寸
            2.0f,    // 属性为动画结束时 X坐标上的伸缩尺寸
            0.0f,  // 属性为动画起始时 Y坐标上的伸缩尺寸
            2.0f,    // 属性为动画结束时 Y坐标上的伸缩尺寸
            Animation.RELATIVE_TO_SELF,  //属性为动画相对于物件的X坐标的开始位置
            0.5f,              // 50%等於中點
            Animation.RELATIVE_TO_SELF,  //属性为动画相对于物件的Y坐标的开始位置
            0.5f
        )
        //pivotX=50 自己的左上角的X+50
        //pivotX=50% 自己的左上角的X+50%的寬度
        //pivotX=50%p p=取值的基數是父原件
        scaleAlphaAnimation.duration = 5000
        scaleAlphaAnimation.fillAfter = true
        button2.startAnimation(scaleAlphaAnimation)
    }
    //---------------------------------------------------------------------------------------//

    //移動動畫
    fun translateXML() {
        var ani = AnimationUtils.loadAnimation(this, R.anim.translate)
        button2.startAnimation(ani)
    }

    //這個可以拿來做ＣＤＮ由底部彈出的動畫
    fun translateCode() {
        var translateAnimation = TranslateAnimation(
            0f,    //属性为动画起始时 X坐标上的位置
            0f,      //属性为动画结束时 X坐标上的位置
            200f,  //属性为动画起始时 Y坐标上的位置
            0f       //属性为动画结束时 Y坐标上的位置
        )
        translateAnimation.duration = 1000

        button2.startAnimation(translateAnimation)
    }
    //---------------------------------------------------------------------------------------//

    //旋轉動畫
    fun rotateXML() {
        var ani = AnimationUtils.loadAnimation(this, R.anim.rotate)
        button2.startAnimation(ani)
    }

    fun rotateCode() {
        var rotateAnimation = RotateAnimation(
            0f,   //開始角度
            360f,   //結束角度
            Animation.RELATIVE_TO_SELF,   //不加這個話預設好像是parent 所以會在左上角
            0.5f,      //50%
            Animation.RELATIVE_TO_SELF,   //不加這個話預設好像是parent 所以會在左上角
            0.5f       //50%
        )
        rotateAnimation.duration = 2000
        rotateAnimation.fillAfter = true
        button2.startAnimation(rotateAnimation)
    }
    //---------------------------------------------------------------------------------------//

    /*
    * 屬性動畫
    * 1.ValueAnimator
    * 2.ObjectAnimator
    * TypeEvaluator 估值器 決定值的具體變化數據
    */

    //AnimatorSet
    /*
    * 不能用AnimationUtils.loadAnimation因為這個是補間動畫在用的
    * 在裡面寫objectAnimator or valueAnimator都是沒有提示的超累
    */
    @SuppressLint("ResourceType")
    fun animatorSetXML() {
        var animatorSet = AnimatorInflater.loadAnimator(this, R.anim.animation_set) as AnimatorSet
        animatorSet.setTarget(button2)
        animatorSet.start()
    }

    fun animatorSetCode() {
        //平移
        var translation = ObjectAnimator.ofFloat(
            button2,
            "translationX",  //屬性名稱 例如:rotore scale...
            0f,
            300f
            , 0f
        )

        //旋轉
        var rotate = ObjectAnimator.ofFloat(
            button2,
            "rotation",
            0f,
            360f
        )

        //透明
        var alpha = ObjectAnimator.ofFloat(
            button2,
            "alpha",
            0f,
            1f
        )

        //組合動畫
        var animatorSet = AnimatorSet()
        animatorSet.play(translation).with(rotate).before(alpha)
        animatorSet.duration = 5000
        animatorSet.start()
//        AnimatorSet.play(Animator anim)   ：播放当前动画
//        AnimatorSet.after(long delay)     ：将现有动画延迟x毫秒后执行
//        AnimatorSet.with(Animator anim)   ：同時執行
//        AnimatorSet.after(Animator anim)  ：先做完他在做他前面的
//        AnimatorSet.before(Animator anim) ：前面的做完才會輪到他做
    }

    @SuppressLint("ResourceType")
    fun valueAnimatorXML() {
        //animator要放在animator的資料夾才有用
        //不能用<set>包住 應該會被認為是補間動畫
        //animator==valueAnimator在XML裡面
        var valueAnimator =
            AnimatorInflater.loadAnimator(this, R.animator.value_animator) as ValueAnimator
        valueAnimator.setTarget(button2)
        valueAnimator.start()

        valueAnimator.addListener(object : Animator.AnimatorListener {
            override fun onAnimationRepeat(animation: Animator?) {

            }

            override fun onAnimationEnd(animation: Animator?) {

            }

            override fun onAnimationCancel(animation: Animator?) {

            }

            override fun onAnimationStart(animation: Animator?) {

            }
        })
        valueAnimator.addUpdateListener {
            var parma = button2.layoutParams
            parma.width = it.animatedValue as Int
            parma.height = it.animatedValue as Int
            button2.layoutParams = parma
            button2.text = it.animatedValue.toString()
        }

    }

    fun valueAnimatorCode() {
        //0,100=0~100  0,100,200=0~100 100~200
        var valueAnimator = ValueAnimator.ofInt(200, 500)
        valueAnimator.duration = 1000
        valueAnimator.startDelay = 0
        valueAnimator.repeatCount = 3
        valueAnimator.repeatMode = ValueAnimator.REVERSE
        // ValueAnimator.RESTART(默认):正序重放
        // ValueAnimator.REVERSE:倒序回放

        valueAnimator.setTarget(button2)
        valueAnimator.start()

        valueAnimator.addUpdateListener {
            var parma = button2.layoutParams
            parma.width = it.animatedValue as Int
            parma.height = it.animatedValue as Int
            button2.layoutParams = parma
            button2.text = it.animatedValue.toString()
        }
    }


    @SuppressLint("ResourceType")
    fun objectAnimatorXML() {
        var objectAnimator =
            AnimatorInflater.loadAnimator(this, R.animator.object_animator) as ObjectAnimator
        objectAnimator.target = button2
        objectAnimator.start()

        objectAnimator.addListener(object : Animator.AnimatorListener {
            override fun onAnimationRepeat(animation: Animator?) {

            }

            override fun onAnimationEnd(animation: Animator?) {

            }

            override fun onAnimationCancel(animation: Animator?) {

            }

            override fun onAnimationStart(animation: Animator?) {

            }
        })
        objectAnimator.addUpdateListener {
            var parma = button2.layoutParams
            parma.width = it.animatedValue as Int
            parma.height = it.animatedValue as Int
            button2.layoutParams = parma
            button2.text = it.animatedValue.toString()
        }
    }

    fun objectAnimatorCode() {
        //propertyName是控件上的屬性
        var objectAnimator = ObjectAnimator.ofFloat(tv, "textSize", 14f, 32f, 14f)
        objectAnimator.duration = 3000
        objectAnimator.start()
    }

    fun PropertyValuesHolder() {
        var rotateProper = PropertyValuesHolder.ofFloat(
            "Rotation",
            60f, -60f,
            40f, -40f,
            20f, -20f,
            10f, -10f,
            0f, 0f
        )
        var colorProper = PropertyValuesHolder.ofInt(
            "BackgroundColor",
            0xffffffff.toInt(),
            0xffff00ff.toInt(),
            0xffffff00.toInt(),
            0xffffffff.toInt()
        )
        var objectAnimator=ObjectAnimator.ofPropertyValuesHolder(tv,rotateProper,colorProper)
        objectAnimator.duration=3000
        objectAnimator.start()
    }

    fun setLayoutTranslate(){
        var layoutTransition=LayoutTransition()
        var objectAnimator=ObjectAnimator.ofFloat(CDNLAYOUT2,"translationX",100f,0f,100f)
        objectAnimator.duration=3000
        layoutTransition.setAnimator(LayoutTransition.APPEARING,objectAnimator)
        MainLayout.layoutTransition=layoutTransition

        //不知道怎麼取得副組建的位置
    }

}
