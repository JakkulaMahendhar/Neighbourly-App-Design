package com.example.fincare_uat;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;



public class ItemViewFragment extends Fragment {



    // TODO: Rename and change types and number of parameters
    public static ItemViewFragment newInstance() {
        ItemViewFragment fragment = new ItemViewFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    public String isNull(String name){
        String uer = "";
        if(name == null){
            return uer;
        }
        return name;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = null;
        String strtext = "";
        String image = "";

        RecylerItemViewActivity recylerItemViewActivity = (RecylerItemViewActivity) getActivity();
           String[] values =  recylerItemViewActivity.getData();


            strtext = values[0];
            image = values[1];
//             image = this.getArguments().getString("image");


        if(isNull(strtext).equals("user")){
          view =   inflater.inflate(R.layout.fragment_item_view, container, false);
        }else if(isNull(image).equals("image")){
            view =   inflater.inflate(R.layout.fragment_image_item_view, container, false);
        }
        return view;
    }


}
