package com.example.vendingmachine;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;

public class Player {
   private ImageView imgv;
   private ProgressBar progressBar;
   private int flag;
   private TextView textView;
   private Context context;

   private ObjectAnimator animator;

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public ObjectAnimator getAnimator() {
        return animator;
    }

    public void setAnimator(ObjectAnimator animator) {
        this.animator = animator;
    }

    public ImageView getImgv() {
        return imgv;
    }

    public void setImgv(ImageView imgv) {
        this.imgv = imgv;
    }

    public ProgressBar getProgressBar() {
        return progressBar;
    }

    public void setProgressBar(ProgressBar progressBar) {
        this.progressBar = progressBar;
    }

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }

    public TextView getTextView() {
        return textView;
    }

    public void setTextView(TextView textView) {
        this.textView = textView;
    }

    public Player(ImageView imgv, ProgressBar progressBar, TextView textView) {
        this.imgv = imgv;
        this.progressBar = progressBar;
        this.textView = textView;
    }

    public Player(ImageView imgv, int flag, TextView textView) {
        this.imgv = imgv;
        this.flag = flag;
        this.textView = textView;
    }


}
