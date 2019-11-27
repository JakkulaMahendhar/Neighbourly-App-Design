package com.example.neighbourly;

import android.app.Dialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.facebook.shimmer.ShimmerFrameLayout;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    RecyclerView recyclerview;
    ImageView locate;
    Dialog dialog;
    TextView btn_post;
    boolean frsttineclick = false;
    private ShimmerFrameLayout mShimmerViewContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mShimmerViewContainer = findViewById(R.id.shimmer_view_container);

        dialog = new Dialog(this);
        recyclerview = (RecyclerView) findViewById(R.id.recyclerview);
        locate = (ImageView) findViewById(R.id.locate);
        btn_post = (TextView) findViewById(R.id.btn_post);
        final RecyclerviewAdapter adapter = new RecyclerviewAdapter(getSampleArrayList(), this,mShimmerViewContainer);
        recyclerview.setLayoutManager(new LinearLayoutManager(this));
        recyclerview.setAdapter(adapter);
//        final Handler handler = new Handler();
//        handler.postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                recyclerview.setAdapter(adapter);
//                mShimmerViewContainer.stopShimmerAnimation();
//                mShimmerViewContainer.setVisibility(View.GONE);
//            }
//        }, 2000);
        btn_post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, PostActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_up, 0);
            }
        });
        locate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPopup();
            }
        });
    }

    private void showPopup() {

        Window window = dialog.getWindow();
        WindowManager.LayoutParams wlp = window.getAttributes();
        wlp.gravity = Gravity.TOP;
        wlp.width = WindowManager.LayoutParams.MATCH_PARENT;
        wlp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        window.setAttributes(wlp);
        dialog.setContentView(R.layout.locate_popup);
        dialog.setCancelable(true);
        dialog.setCanceledOnTouchOutside(true);
        if (frsttineclick) {
            dialog.show();
        } else {
            frsttineclick = true;
            showPopup();
        }


    }


    private ArrayList<Object> getSampleArrayList() {
        ArrayList<Object> items = new ArrayList<>();
        items.add(new User("Dany Targaryen", "Valyria"));
        items.add(new User("Rob Stark", "Winterfell"));
        items.add("image");
        items.add(new User("Jon Snow", "Castle Black"));
        items.add("image");
        items.add(new User("Tyrion Lanister", "King's Landing"));
        return items;
    }
}
