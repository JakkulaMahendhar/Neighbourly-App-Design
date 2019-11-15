package com.example.fincare_uat;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class RecylerItemViewActivity extends AppCompatActivity {

    String[] values;
    String user = "";
    String image = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recyler_item_view);
       user  = getIntent().getStringExtra("user");
       image = getIntent().getStringExtra("image");

//        Bundle bundle = new Bundle();
//        bundle.putString("user", user);
//        bundle.putString("image",isNull(image));
//        ItemViewFragment cardTwoFragment = new ItemViewFragment();
//        cardTwoFragment.setArguments(bundle);
        getData();
       ItemViewFragment cardTwoFragment = ItemViewFragment.newInstance();
        getSupportFragmentManager().beginTransaction().add(R.id.cardContainer, cardTwoFragment).commit();
    }

    public String isNull(String name){
        String uer = "";
        if(name == null){
            return uer;
        }
        return name;
    }

    public String[] getData(){

       String values[] = new String[]{user,image};
        String[] ret = new String[2];
        ret[0]=values[0];
        ret[1]=values[1];
        return ret;

    }

}
