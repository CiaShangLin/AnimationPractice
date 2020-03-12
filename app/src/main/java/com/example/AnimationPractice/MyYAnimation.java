package com.example.AnimationPractice;

import android.graphics.Camera;
import android.graphics.Matrix;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Transformation;

//中心點翻轉動畫
public class MyYAnimation extends Animation {
    int centerX, centerY;
    Camera camera = new Camera();
    /**
     * 獲取座標，定義動畫時間
     * @param width
     * @param height
     * @param parentWidth
     * @param parentHeight
     */
    @Override
    public void initialize(int width, int height, int parentWidth, int parentHeight) {
        super.initialize(width, height, parentWidth, parentHeight);
//獲得中心點座標
        centerX = width / 2;
        centerY = width / 2;
//動畫執行時間 自行定義
//        setDuration(3 * 1000);
//        setInterpolator(new DecelerateInterpolator());
    }

    /**
     * 旋轉的角度設定
     * @param interpolatedTime
     * @param t
     */
    @Override
    protected void applyTransformation(float interpolatedTime, Transformation t) {
        final Matrix matrix = t.getMatrix();
        camera.save();
//中心是Y軸旋轉，這裡可以自行設定X軸 Y軸 Z軸
        camera.rotateY(3600 * interpolatedTime);
//把我們的攝像頭加在變換矩陣上
        camera.getMatrix(matrix);
//設定翻轉中心點
        matrix.preTranslate(-centerX, -centerY);
        matrix.postTranslate(centerX,centerY);
        camera.restore();
    }
}