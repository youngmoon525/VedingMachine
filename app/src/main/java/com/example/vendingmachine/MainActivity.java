package com.example.vendingmachine;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.ContextThemeWrapper;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    Button btn_apply ;
    int selectCount = 0;
    ArrayList<ImageView> imgList = new ArrayList<>();
    ArrayList<Player> playerArrayList = new ArrayList<>();
    ArrayList<LinearLayout> linearList  = new ArrayList<>();
    LinearLayout ln_stage ;
    boolean isCheckedWinner = false;
    String[] nameArr = {
            "곽영균",
            "김건호",
            "김기곤",
            "김수봉",
            "김혜민",
            "문병준",
            "송빛나",
            "이진성",
            "임유주",
            "정수원",
            "조은평"
    };
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
        btn_apply = createButton();
        ln_layout = findViewById(R.id.ln_layout);
        linearList.add(createLinear(LinearLayout.HORIZONTAL));
        ln_stage = createLinear(LinearLayout.HORIZONTAL);
        for (int i = 1; i <= imgIdArr.length; i++) {
            LinearLayout ln_vertical = createLinear(LinearLayout.VERTICAL , 1);
            ImageView imgv = createImageView(imgIdArr[i-1]);
            TextView tv_name = createTextView(nameArr[i-1]);
            ln_vertical.addView(imgv);
            ln_vertical.addView(tv_name);
            imgList.add(imgv);
            linearList.get(linearList.size()-1).addView(ln_vertical);
            if(i%4 == 0){
                linearList.add(createLinear(LinearLayout.HORIZONTAL));
            }
        }
        for (int i = 0; i < linearList.size(); i++) {
            ln_layout.addView(linearList.get(i));
        }


        ln_layout.addView(btn_apply);


    }


    TextView createTextView(String text){
        TextView view = new TextView(this);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT , ViewGroup.LayoutParams.WRAP_CONTENT);
        view.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        view.setLayoutParams(params);
        doBounceAnimation(view);
        view.setText(text);
        return view;
    }


    ImageView createImageView(int imgId){
        
        ImageView view = new ImageView(this);
        view.setImageResource(imgId);
        view.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(200 , 200);
        view.setLayoutParams(params);
        doBounceAnimation(view);

        view.setOnClickListener(v->{

            if(view.getTag() != null ){
                view.setTag(null);
                view.setColorFilter(null);
                selectCount--;
                btn_apply.setVisibility(View.GONE);
            }else{
                if(selectCount==2){
                    Toast.makeText(this, "두개만선택가능", Toast.LENGTH_SHORT).show();
                    return;
                }
                view.setColorFilter(Color.parseColor("#55ff0000"));
                view.setTag("ON");
                selectCount++;
                if(selectCount >=2){
                    btn_apply.setVisibility(View.VISIBLE);
                }

            }

        });

        //view.setColorFilter(null);
        return view;
    }
    Button createButton(){
        Button view = new Button(this);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT , ViewGroup.LayoutParams.WRAP_CONTENT);
        view.setLayoutParams(params);
        doBounceAnimation(view);
        view.setText("확정");
        view.setVisibility(View.GONE);
        view.setOnClickListener(v->{
            ln_stage.removeAllViews();



            int tempCount  = 0;
            for(int i =  0 ; i < imgList.size() ; i ++){
                if(imgList.get(i).getTag()!=null){
                    imgList.get(i).setEnabled(false);
                    LinearLayout ln_vertical = createLinear(LinearLayout.VERTICAL , 1);
                    ImageView imgv = createImageView(imgIdArr[i]);
                    TextView tv_name = createTextView(nameArr[i]);
                    ln_vertical.addView(imgv);
                    ln_vertical.addView(tv_name);
                    ProgressBar progressBar = createHpBar();
                    ln_vertical.addView(progressBar);
                    playerArrayList.add(new Player(imgv , progressBar));

                    ln_stage.addView(ln_vertical);

                    tempCount++ ;
                    if(tempCount == 2){
                        doAnimationX( playerArrayList  );
                    }



                }
            }
            ln_layout.addView(ln_stage);
            Toast.makeText(this, "곧 싸움 시작", Toast.LENGTH_SHORT).show();

        });
        return view;
    }

    ProgressBar createHpBar(){
        ProgressBar progress = new ProgressBar(new ContextThemeWrapper(this  , com.google.android.material.R.style.Widget_AppCompat_ProgressBar_Horizontal),null,0);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
       params.leftMargin= 10;
       params.rightMargin= 10;
        progress.setLayoutParams(params);
        progress.setMax(100);
        progress.setProgress(100);



        return progress;
    }

    LinearLayout createLinear(int orientation , int weight){
        LinearLayout linearLayout = new LinearLayout(this);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT,weight);
        linearLayout.setLayoutParams(params);
        linearLayout.setOrientation(orientation);
        linearLayout.setGravity(Gravity.CENTER);
        return linearLayout;
    }
    LinearLayout createLinear(int orientation){
        LinearLayout linearLayout = new LinearLayout(this);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        linearLayout.setLayoutParams(params);
        linearLayout.setOrientation(orientation);
        linearLayout.setGravity(Gravity.CENTER);
        return linearLayout;
    }
    private void doBounceAnimation(View targetView) {
        ObjectAnimator animator = ObjectAnimator.ofFloat(targetView, "translationY", 0, 10, 0);
        animator.setStartDelay(500);
        animator.setDuration(1500);
        animator.setRepeatCount(ValueAnimator.INFINITE);
        animator.start();
    }

    private void doAnimationX(ArrayList<Player> list ) {
        for(int i= 0 ; i < list.size() ; i ++) {
            final int idx = i;
            int pos = -200;
            if(i == 0 ){
                pos = -pos;
            }

            list.get(i).animator = ObjectAnimator.ofFloat(list.get(i).getImgv(), "translationX", 0, pos ,0);
                     list.get(i).animator.setStartDelay(1000);
                     list.get(i).animator.setDuration(1000);
                     list.get(i).animator.setRepeatCount(ValueAnimator.INFINITE);
                     list.get(i).animator.start();
            list.get(i).animator.addListener(new Animator.AnimatorListener() {
                @Override
                public void onAnimationStart(@NonNull Animator animation) {

                }

                @Override
                public void onAnimationEnd(@NonNull Animator animation) {

                }

                @Override
                public void onAnimationCancel(@NonNull Animator animation) {

                }

                @Override
                public void onAnimationRepeat(@NonNull Animator animation) {


                    if (!isCheckedWinner) {
                        if (list.get(idx).getProgressBar().getProgress() == 0) {
                            int tempIdx = 0;
                            if(idx==tempIdx){
                                tempIdx = 1;
                            }

                            list.get(tempIdx).animator.cancel();
                            list.get(tempIdx).animator.pause();
                            ObjectAnimator animatorX = ObjectAnimator.ofFloat(list.get(tempIdx).getImgv(), "scaleY", 0, 10, 0);
                            ObjectAnimator animatorY = ObjectAnimator.ofFloat(list.get(tempIdx).getImgv(), "scaleX", 0, 10, 0);
                            animatorX.setDuration(2000);
                            animatorX.start();
                            animatorY.setDuration(2000);
                            animatorY.start();
                            animatorX.setRepeatCount(ValueAnimator.INFINITE);
                            animatorY.setRepeatCount(ValueAnimator.INFINITE);


                            list.get(idx).animator.cancel();
                            list.get(idx).animator.pause();
                            isCheckedWinner = true;
                            ObjectAnimator animator = ObjectAnimator.ofFloat(list.get(idx).getImgv(), "translationY", 0, 1000, 0);
                            animator.setDuration(2000);
                            animator.start();
                            animator.addListener(new Animator.AnimatorListener() {
                                @Override
                                public void onAnimationStart(@NonNull Animator animation) {

                                }

                                @Override
                                public void onAnimationEnd(@NonNull Animator animation) {
                                    list.get(idx).getImgv().setVisibility(View.INVISIBLE);
                                }

                                @Override
                                public void onAnimationCancel(@NonNull Animator animation) {

                                }

                                @Override
                                public void onAnimationRepeat(@NonNull Animator animation) {

                                }
                            });


                        }
                    }
                    if (!isCheckedWinner) {
                        list.get(idx).getProgressBar().setProgress(list.get(idx).getProgressBar().getProgress() - new Random().nextInt(10));
                    }
                }
            });
        }
    }

    public class Player{
        ImageView imgv ;
        ProgressBar progressBar;

        ObjectAnimator animator;



        public Player(ImageView imgv, ProgressBar progressBar) {
            this.imgv = imgv;
            this.progressBar = progressBar;
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
    }

}