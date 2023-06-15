package com.example.vendingmachine;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<ImageView> imgList = new ArrayList<>();
    ArrayList<LinearLayout> linearList  = new ArrayList<>();
    String[] nameArr = {
            "곽영균",
            "김건호",
            "김기곤",
            "김수봉",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
    }
    int[] imgIdArr= {
            R.drawable.img1,
            R.drawable.img2,
            R.drawable.img3,
            R.drawable.img4,
            R.drawable.img5,
            R.drawable.img6,
            R.drawable.img7,
            R.drawable.img8,
            R.drawable.img9,
            R.drawable.img10,
            R.drawable.img11
    };
    LinearLayout ln_layout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ln_layout = findViewById(R.id.ln_layout);
        linearList.add(createLinearHorizon());
        for (int i = 1; i <= imgIdArr.length; i++) {
            ImageView imgv = createImageView(imgIdArr[i-1]);
            imgList.add(imgv);
            linearList.get(linearList.size()-1).addView(imgv);
            if(i%4 == 0){
                linearList.add(createLinearHorizon());
            }
        }
        for (int i = 0; i < linearList.size(); i++) {
            ln_layout.addView(linearList.get(i));
        }




    }

    ImageView createImageView(int imgId){
        ImageView view = new ImageView(this);
        view.setImageResource(imgId);
        view.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(200 , 200);
        view.setLayoutParams(params);
        doBounceAnimation(view);
        view.setColorFilter(Color.parseColor("#55ff0000"));
        view.setColorFilter(null);
        return view;
    }

    LinearLayout createLinearHorizon(){
        LinearLayout linearLayout = new LinearLayout(this);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        linearLayout.setLayoutParams(params);
        linearLayout.setOrientation(LinearLayout.HORIZONTAL);
        return linearLayout;
    }
    private void doBounceAnimation(View targetView) {
        ObjectAnimator animator = ObjectAnimator.ofFloat(targetView, "translationY", 0, 10, 0);
        animator.setStartDelay(500);
        animator.setDuration(1500);
        animator.setRepeatCount(ValueAnimator.INFINITE);

        animator.start();
    }
}