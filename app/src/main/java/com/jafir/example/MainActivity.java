package com.jafir.example;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.jafir.roundimage.RoundImageView;
import com.squareup.picasso.Picasso;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    RoundImageView round1;
    RoundImageView round2;
    RoundImageView round3;
    String[] avatars = new String[]{
            "http://diy.qqjay.com/u2/2012/0618/ed6982355b1340095aeaf79072bdc1cc.jpg",
            "http://v1.qzone.cc/avatar/201310/12/15/42/5258fd6f0db4b914.jpg%21200x200.jpg",
            "http://diy.qqjay.com/u2/2012/0912/a188c51e5d10d1c61241759ed3c43307.jpg",
            "http://img4.imgtn.bdimg.com/it/u=2326007109,671296332&fm=214&gp=0.jpg",
            "http://www.feizl.com/upload2007/2013_07/1307202132986830.jpg",
            "http://www.feizl.com/upload2007/2014_09/14091313267725.jpg",
            "http://diy.qqjay.com/u2/2012/0907/d5fcf854c9df639fb80187d0370873c9.jpg",
            "http://www.qqpk.cn/Article/UploadFiles/201111/20111130141151352.jpg",
            "http://www.qqtn.com/up/2014-10/14129256733987424.jpg"
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();

    }

    private void init() {
       round1 = findViewById(R.id.img1);
       round2 = findViewById(R.id.img2);
       round3 = findViewById(R.id.img3);

        Glide.with(this)
                .load(avatars[new Random().nextInt(avatars.length)])
                .into(round1);

        round2.setImageResource(R.mipmap.ic_launcher);
        round3.setScaleType(ImageView.ScaleType.FIT_XY);

        Picasso.with(this)
                .load("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1504780236751&di=f8b218d6111cae92f092a257d4e23b80&imgtype=0&src=http%3A%2F%2Fwww.qqleju.com%2Fuploads%2Fallimg%2F160213%2F13-053516_87.jpg")
                .into(round3);

    }

    public void change(View view) {
        round1.setmInsideBorderThickness(10);
        round1.setBorderInsideColor(Color.GREEN);

    }
}
