# RoundImage
A round imageview

### screenshot

<img src="https://github.com/fly7632785/RoundImage/raw/master/screenshot/shot1.png" width="40%"/>

### Use
| filed                  |meaning                 |
| -------------          | --------------         |
| inside_border_color    |  inside border color   |
| inside_border_width    |  inside border width   |
| outside_border_color   |  outside border color  |
| outside_border_width   |  outside border width  |


```
 <com.jafir.RoundImageView
            android:layout_width="150dp"
            android:layout_height="150dp"
            android:id="@+id/img3"
            app:inside_border_color="@android:color/white"
            app:inside_border_width="5dp"
            app:outside_border_color="@android:color/black"
            app:outside_border_width="5dp" />
```

**Support Glide and Picasso**
```
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
```


### Gradle
```
repositories {
			...
			maven { url 'https://jitpack.io' }
		}
```
```
compile 'com.github.fly7632785:RoundImage:1.0.0'
```



