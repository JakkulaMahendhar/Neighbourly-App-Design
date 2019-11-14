package com.example.fincare_uat;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

public class Viewholder2 extends RecyclerView.ViewHolder {

    private ImageView ivExample;

    public Viewholder2(@NonNull View itemView) {
        super(itemView);
        ivExample = (ImageView) itemView.findViewById(R.id.ivExample);
    }

    public ImageView getImageView() {
        return ivExample;
    }

    public void setImageView(ImageView ivExample) {
        this.ivExample = ivExample;
    }


}
