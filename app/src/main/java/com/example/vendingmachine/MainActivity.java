package com.example.vendingmachine;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.ContextThemeWrapper;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    Button btn_apply;
    int selectCount = 0;
    ArrayList<Player> totalPlayer = new ArrayList<>();
    ArrayList<Player> playerArrayList = new ArrayList<>();
    ArrayList<LinearLayout> linearList = new ArrayList<>();
    LinearLayout ln_stage;
    boolean isCheckedWinner = false;

    LinearLayout ln_layout;
    CreateUi ui ;
    Animator anim ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ui = new CreateUi(this);
        anim = new Animator();

        btn_apply = ui.createButton("확정");
        ln_layout = findViewById(R.id.ln_layout);
        linearList.add(ui.createLinear(LinearLayout.HORIZONTAL));
        totalPlayer = ui.initPlayer();
//        ln_stage = createLinear(LinearLayout.HORIZONTAL);
        for (int i = 1; i <= totalPlayer.size(); i++) {
            LinearLayout ln_vertical = ui.createLinear(LinearLayout.VERTICAL, 1);

            ln_vertical.addView(totalPlayer.get(i-1).getImgv());
            ln_vertical.addView(totalPlayer.get(i-1).getTextView());
            ln_vertical.setOnClickListener(new PlayerOnclick(totalPlayer.get(i-1)));
            anim.doBounceAnimation(totalPlayer.get(i-1).getImgv());
            linearList.get(linearList.size() - 1).addView(ln_vertical);

            if (i % 4 == 0) {
                linearList.add(ui.createLinear(LinearLayout.HORIZONTAL));
            }
        }
        for (int i = 0; i < linearList.size(); i++) {
            ln_layout.addView(linearList.get(i));
        }
          ln_layout.addView(btn_apply);

        btn_apply.setOnClickListener(v->{
            ArrayList<Player> currPlayer = new ArrayList<>();
            LinearLayout layoutVertical = ui.createLinear(LinearLayout.VERTICAL);
            LinearLayout linearHorizon = ui.createLinear(LinearLayout.HORIZONTAL);

            for (int i = 0 ; i < totalPlayer.size(); i++){
                if(totalPlayer.get(i).getFlag() == 1){
                    LinearLayout ln_vertical = ui.createLinear(LinearLayout.VERTICAL, 1);

                    currPlayer.add(new Player(ui.createImageView(CreateUi.BaseData.imgIdArr[i]),
                            ui.createHpBar() ,  ui.createTextView(CreateUi.BaseData.nameArr[i])
                    ));
                    currPlayer.get(currPlayer.size()-1).setFlag(i);
                    ln_vertical.addView(currPlayer.get(currPlayer.size()-1).getImgv());

                    ln_vertical.addView(currPlayer.get(currPlayer.size()-1).getProgressBar());

                    linearHorizon.addView(ln_vertical);
                }
            }
            Button button = ui.createButton("다음판");
            button.setVisibility(View.VISIBLE);
            layoutVertical.addView(ui.createTextView(currPlayer.get(0).getTextView().getText().toString()+ " VS "+currPlayer.get(1).getTextView().getText().toString()));
            layoutVertical.addView(linearHorizon);
            layoutVertical.addView(ui.createLine(1) );
            layoutVertical.addView(button);

           AlertDialog dialog =   ui.getInstenceDialogBuilder(layoutVertical,currPlayer , new AnimatorAction(currPlayer));;
           dialog.setCancelable(false);

           button.setOnClickListener(view -> {
               if(isCheckedWinner==true){
                   
           
               dialog.dismiss();
               selectCount=0;
               isCheckedWinner=false;
               }else{
                   Toast.makeText(this, "진행중", Toast.LENGTH_SHORT).show();
               }
           });
           Window window = dialog.getWindow();
           window.setGravity(Gravity.BOTTOM);
           dialog.show();




        });


    }

    public class AnimatorAction implements android.animation.Animator.AnimatorListener {
        ArrayList<Player> list;

        public AnimatorAction(ArrayList<Player> list) {
            this.list = list;
        }

        @Override
        public void onAnimationStart(@NonNull android.animation.Animator animator) {

        }

        @Override
        public void onAnimationEnd(@NonNull android.animation.Animator animator) {

        }

        @Override
        public void onAnimationCancel(@NonNull android.animation.Animator animator) {

        }

        @Override
        public void onAnimationRepeat(@NonNull android.animation.Animator animator) {


            for (int i = 0; i < list.size(); i++) {
                final int idx = i;
                int pos = -200;
                if (i == 0) {
                    pos = -pos;
                }

                if (!isCheckedWinner) {
                    if (list.get(idx).getProgressBar().getProgress() == 0) {
                        int tempIdx = 0;
                        if (idx == tempIdx) {
                            tempIdx = 1;
                        }


                        list.get(tempIdx).getAnimator().cancel();
                        list.get(tempIdx).getAnimator().pause();
                        list.get(idx).getAnimator().cancel();
                        list.get(idx).getAnimator().pause();
                        totalPlayer.get(list.get(idx).getFlag()).getImgv().setEnabled(false);
                        totalPlayer.get(list.get(idx).getFlag()).setFlag(2);


                        totalPlayer.get(list.get(tempIdx).getFlag()).getImgv().setColorFilter(Color.parseColor("#CB5CCC77"));
                        totalPlayer.get(list.get(tempIdx).getFlag()).setFlag(3);
                        totalPlayer.get(list.get(tempIdx).getFlag()).getImgv().setEnabled(true);



                        ObjectAnimator animatorX = ObjectAnimator.ofFloat(list.get(tempIdx).getImgv(), "scaleY", 2);
                        ObjectAnimator animatorY = ObjectAnimator.ofFloat(list.get(tempIdx).getImgv(), "scaleX", 2);


                        animatorX.setDuration(2000);
                        animatorX.start();
                        animatorY.setDuration(2000);
                        animatorY.start();
                        animatorX.setRepeatMode(ValueAnimator.REVERSE);
                        animatorY.setRepeatMode(ValueAnimator.REVERSE);
                        animatorX.setRepeatCount(ValueAnimator.INFINITE);
                        animatorY.setRepeatCount(ValueAnimator.INFINITE);



                        isCheckedWinner = true;
                        ObjectAnimator rotaionAnimator = ObjectAnimator.ofFloat(list.get(idx).getImgv(), "rotation", 360);
                        rotaionAnimator.setDuration(2000);
                        rotaionAnimator.start();
                        rotaionAnimator.addListener(new android.animation.Animator.AnimatorListener() {
                            @Override
                            public void onAnimationStart(@NonNull android.animation.Animator animator) {

                            }

                            @Override
                            public void onAnimationEnd(@NonNull android.animation.Animator animator) {
                                list.get(idx).getImgv().setRotation(90);
                                totalPlayer.get(list.get(idx).getFlag()).getImgv().setRotation(90);

                            }

                            @Override
                            public void onAnimationCancel(@NonNull android.animation.Animator animator) {

                            }

                            @Override
                            public void onAnimationRepeat(@NonNull android.animation.Animator animator) {

                            }
                        });
                        totalPlayer.get(list.get(idx).getFlag()).getImgv().setColorFilter(Color.parseColor("#80808077"));
                        totalPlayer.get(list.get(tempIdx).getFlag()).getImgv().setColorFilter(Color.parseColor("#CB5CCC77"));
                      //  findViewById(R.id.btn_next).setVisibility(View.VISIBLE);
                    }
                }
                if (!isCheckedWinner) {
                    if (list.get(idx).getProgressBar().getProgress() < list.get(idx).getProgressBar().getMax() / 2) {
                        list.get(idx).getProgressBar().setProgressTintList(ColorStateList.valueOf(Color.RED));
                    }
                    int rnadomInt = new Random().nextInt(10);
                    list.get(idx).getProgressBar().setProgress(list.get(idx).getProgressBar().getProgress() - new Random().nextInt(10));
                    if (rnadomInt > 5) {
                        anim.doFade(list.get(idx).getImgv());
                    }
                    anim.randomAnimator(list.get(idx).getImgv());
                }
            }
        }
    }


    public class PlayerOnclick implements View.OnClickListener{
        Player player ;
        public PlayerOnclick(Player player) {
            this.player = player;
        }

        @Override
        public void onClick(View v) {


            if (player.getFlag() == 0 || player.getFlag()==3) {
                if (selectCount == 2) {
                    Toast.makeText(MainActivity.this, player.getTextView().getText().toString() + " 두개만선택가능 ", Toast.LENGTH_SHORT).show();
                    return;
                }
                selectCount++;
                player.setFlag(1);
                Toast.makeText(MainActivity.this, player.getTextView().getText().toString() + " 선택 ", Toast.LENGTH_SHORT).show();
                player.getImgv().setColorFilter(Color.parseColor("#55ff0000"));

            } else if (player.getFlag() == 1) {
                selectCount--;
                player.setFlag(0);
                if(player.getImgv().getColorFilter()!=null){
                    player.getImgv().setColorFilter(null);
                }
                Toast.makeText(MainActivity.this, player.getTextView().getText().toString() + " 선택 해제 ", Toast.LENGTH_SHORT).show();
            } else {

            }


            if (selectCount == 2) {
                btn_apply.setVisibility(View.VISIBLE);
            } else {
                btn_apply.setVisibility(View.GONE);
            }

        }
    }






}