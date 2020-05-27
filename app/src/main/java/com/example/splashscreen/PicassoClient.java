package com.example.splashscreen;

import android.content.Context;
import android.media.Image;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

public class PicassoClient {

    public static void downloadImage(Context c, String url, ImageView img)
    {

        if (url != null && url.length()>0)
        {
            Picasso.with(c).load(url).placeholder(R.drawable.git_logo).into(img);
        }else{
            Picasso.with(c).load(R.drawable.git_logo).into(img);
        }


    }
}
