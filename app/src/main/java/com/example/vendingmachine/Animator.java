package com.example.vendingmachine;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.view.View;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.Random;

public class Animator {

    void randomAnimator(View targetView){
        int randomInt = 1;//new Random().nextInt(3)+1;
        ObjectAnimator animator = null;
        if(randomInt==1){
            animator = ObjectAnimator.ofFloat(targetView, "rotation", new Random().nextInt(360));
        }else if(randomInt==2){
            //  animator = ObjectAnimator.ofFloat(targetView, "scaleY", 2);
        }else if(randomInt==3){
            // animator = ObjectAnimator.ofFloat(targetView, "scaleX", 1);
        }
        animator.setDuration(1000);

        animator.start();

    }
//
    public void doBounceAnimation(View targetView) {
        ObjectAnimator animator = ObjectAnimator.ofFloat(targetView, "translationY", 0, 15, 0);
        animator.setStartDelay(500);
        animator.setDuration(1500);
        animator.setRepeatCount(ValueAnimator.INFINITE);
        animator.start();
    }
    public ObjectAnimator doFade(View targetView) {
        ObjectAnimator animator = ObjectAnimator.ofFloat(targetView, "alpha", 0f , 1f);
        animator.setStartDelay(1000);
        animator.setDuration(1000);
        animator.start();
        return animator;
    }
    public void doAnimationX(ArrayList<Player> list ,  MainActivity.AnimatorAction action) {
        for (int i = 0; i < list.size(); i++) {
            final int idx = i;
            int pos = -200;
            if (i == 0) {
                pos = -pos;
            }

            list.get(i).setAnimator(ObjectAnimator.ofFloat(list.get(i).getImgv(), "translationX", 0, pos, 0));
            list.get(i).getAnimator().setStartDelay(1000);
            list.get(i).getAnimator().setDuration(1000);
            list.get(i).getAnimator().setRepeatCount(ValueAnimator.INFINITE);
            list.get(i).getAnimator().start();

            list.get(i).getAnimator().addListener(action );

        }
    }


}
