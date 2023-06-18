package com.example.vendingmachine;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Build;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.view.ContextThemeWrapper;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class CreateUi {
    Context context;

    public CreateUi(Context context) {
        this.context = context;
    }

    TextView createTextView(String text) {
        TextView view = new TextView(context);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        view.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        view.setLayoutParams(params);
        view.setText(text);
        return view;
    }

    ImageView createImageView(int imgId, int gravity) {
        ImageView view = new ImageView(context);
        view.setImageResource(imgId);
        view.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(200, 200);
        params.gravity = Gravity.CENTER;
        return view;
    }

    ImageView createImageView(int imgId) {
        ImageView view = new ImageView(context);
        view.setImageResource(imgId);
        view.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(200, 200);
        return view;
    }



    Button createButton(String text) {
        Button view = new Button(context);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        view.setLayoutParams(params);
        view.setText(text);
        view.setVisibility(View.GONE);
        return view;
    }


    ProgressBar createHpBar() {
        ProgressBar progress = new ProgressBar(new ContextThemeWrapper(context, com.google.android.material.R.style.Widget_AppCompat_ProgressBar_Horizontal), null, 0);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 50);
        params.leftMargin = 10;
        params.rightMargin = 10;
        progress.setScaleY(5);
        progress.setProgressTintList(ColorStateList.valueOf(Color.GREEN));
        progress.setLayoutParams(params);
        progress.setMax(100);
        progress.setProgress(100);


        return progress;
    }
//

    FrameLayout createBack() {
        FrameLayout linearLayout = new FrameLayout(context);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(350, 600);
        linearLayout.setLayoutParams(params);
        return linearLayout;
    }

    FrameLayout createLine( int weight) {
        FrameLayout linearLayout = new FrameLayout(context);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,100, weight);
        linearLayout.setLayoutParams(params);
        return linearLayout;
    }
    LinearLayout createLinear(int orientation, int weight) {
        LinearLayout linearLayout = new LinearLayout(context);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, weight);
        linearLayout.setLayoutParams(params);
        linearLayout.setOrientation(orientation);
        linearLayout.setGravity(Gravity.CENTER);
        return linearLayout;
    }

    LinearLayout createLinear(int orientation) {
        LinearLayout linearLayout = new LinearLayout(context);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        linearLayout.setLayoutParams(params);
        linearLayout.setOrientation(orientation);
        linearLayout.setGravity(Gravity.CENTER);
        return linearLayout;
    }
    FrameLayout createFrame() {
        FrameLayout frameLayout = new FrameLayout(context);
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        frameLayout.setLayoutParams(params);
        return frameLayout;
    }

    AlertDialog getInstenceDialogBuilder(LinearLayout ln , ArrayList<Player> players , MainActivity.AnimatorAction action) {

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        FrameLayout frameLayout = createFrame();
        frameLayout.setPadding(5,5,5,5);
        frameLayout.addView(createBack());
        frameLayout.addView(ln);
        Animator animator = new Animator();
        animator.doAnimationX(players , action);

        builder.setView(frameLayout);
        return builder.create();
    }


    ArrayList<Player> initPlayer() {
        ArrayList<Player> players = new ArrayList<>();
        for (int i = 0; i < BaseData.nameArr.length; i++) {
            players.add(new Player(createImageView(BaseData.imgIdArr[i]), 0, createTextView(BaseData.nameArr[i])));
        }
        return players;
    }


    public static class BaseData {


        static String[] nameArr = {
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
        static int[] imgIdArr = {
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
    }


}
