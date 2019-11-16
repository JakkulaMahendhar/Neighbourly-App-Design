package com.example.fincare_uat;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import de.hdodenhof.circleimageview.CircleImageView;


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


            final TextView textView = view.findViewById(R.id.answer);
            final CircleImageView circleImageView = view.findViewById(R.id.iv_answr_dp3);
            final EditText et_cmnt = (EditText) view.findViewById(R.id.et_cmnt);
            final ImageView img_cmnt = (ImageView) view.findViewById(R.id.img_cmnt);
            textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    circleImageView.setVisibility(View.VISIBLE);
                    et_cmnt.setVisibility(View.VISIBLE);
                    img_cmnt.setVisibility(View.VISIBLE);
                    textView.setVisibility(View.GONE);
                }
            });
        }else if(isNull(image).equals("image")){
            view =   inflater.inflate(R.layout.fragment_image_item_view, container, false);

            final TextView textView = view.findViewById(R.id.answer);
            final CircleImageView circleImageView = view.findViewById(R.id.iv_answr_dp3);
            final EditText et_cmnt = (EditText) view.findViewById(R.id.et_cmnt);
            final ImageView img_cmnt = (ImageView) view.findViewById(R.id.img_cmnt);
            textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    circleImageView.setVisibility(View.VISIBLE);
                    et_cmnt.setVisibility(View.VISIBLE);
                    img_cmnt.setVisibility(View.VISIBLE);
                    textView.setVisibility(View.GONE);
                }
            });
        }
        return view;
    }


}
