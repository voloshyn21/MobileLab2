package com.example.lab2;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Vector;

public class MainActivity
        extends AppCompatActivity
        implements View.OnClickListener {

    ViewGroup m_my_list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        m_my_list = (ViewGroup) findViewById(R.id.list);

        LayoutInflater l = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        Vector<String> strings = new Vector<String>();
        for (int i = 0; i < 13; ++i) {
            strings.add("Item " + i);
        }

        for (String s : strings) {
            View item = l.inflate(R.layout.list_item, null);
            ((TextView) item.findViewById(R.id.itemtext)).setText(s);
            item.findViewById(R.id.morebutton).setOnClickListener(this);
            m_my_list.addView(item);
        }

        findViewById(R.id.detail).setOnClickListener(this);
        findViewById(R.id.button1).setOnClickListener(this);
        findViewById(R.id.button2).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.morebutton) {
            View detailview = findViewById(R.id.detail);
            float width = findViewById(R.id.main_layout).getWidth();
            TranslateAnimation anim = new TranslateAnimation(width, 0.0f, 0.0f, 0.0f);
            anim.setDuration(300);
            anim.setFillAfter(true);
            detailview.bringToFront();
            detailview.startAnimation(anim);
            detailview.setVisibility(View.VISIBLE);
            detailview.setEnabled(true);
        } else if (v.getId() == R.id.detail) {
            View detailview = v;
            TranslateAnimation anim = new TranslateAnimation(0.0f, detailview.getWidth(), 0.0f, 0.0f);
            anim.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {
                }

                @Override
                public void onAnimationEnd(Animation animation) {
                    findViewById(R.id.listview).bringToFront();
                }

                @Override
                public void onAnimationRepeat(Animation animation) {
                }
            });
            anim.setDuration(800);
            anim.setFillAfter(true);
            detailview.startAnimation(anim);
            detailview.setEnabled(false);
        } else if (v.getId() == R.id.button1) {
            Toast.makeText(getApplicationContext(),
                    "You clicked button 1",
                    Toast.LENGTH_SHORT).show();
        } else if (v.getId() == R.id.button2) {
            System.out.println("You clickec button 2");
        }
    }
}