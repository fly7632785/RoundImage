package com.jafir.roundimage;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.jafir.RoundImageView;
import com.squareup.picasso.Picasso;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();

    }

    private void init() {
        RoundImageView round1 = findViewById(R.id.img1);
        RoundImageView round2 = findViewById(R.id.img2);
        RoundImageView round3 = findViewById(R.id.img3);

        Glide.with(this)
                .load("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1504780240648&di=e93bed0fea8152bf947139e2415b9c0e&imgtype=jpg&src=http%3A%2F%2Fimg3.imgtn.bdimg.com%2Fit%2Fu%3D1057519802%2C4245354110%26fm%3D214%26gp%3D0.jpg")
                .into(round1);

        round2.setImageResource(R.mipmap.ic_launcher);
        round3.setScaleType(ImageView.ScaleType.FIT_XY);

        Picasso.with(this)
                .load("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1504780236751&di=f8b218d6111cae92f092a257d4e23b80&imgtype=0&src=http%3A%2F%2Fwww.qqleju.com%2Fuploads%2Fallimg%2F160213%2F13-053516_87.jpg")
                .into(round3);

    }
}
