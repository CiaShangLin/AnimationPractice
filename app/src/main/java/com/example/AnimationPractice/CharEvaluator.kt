package com.example.AnimationPractice

import android.animation.TypeEvaluator

class CharEvaluator : TypeEvaluator<Char> {
    override fun evaluate(fraction: Float, startValue: Char, endValue: Char): Char {
        var startInt=startValue.toInt()
        var endInt=endValue.toInt()
        var cur:Int=(startInt+fraction*(endInt-startInt)).toInt()
        return cur.toChar()
    }
}