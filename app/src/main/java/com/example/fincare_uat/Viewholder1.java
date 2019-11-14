package com.example.fincare_uat;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

public class Viewholder1 extends RecyclerView.ViewHolder {

    private TextView label1, label2;

    public Viewholder1(@NonNull View itemView) {
        super(itemView);
        label1 = (TextView) itemView.findViewById(R.id.textone);
        label2 = (TextView) itemView.findViewById(R.id.texttwo);
    }

    public TextView getLabel1() {
        return label1;
    }

    public void setLabel1(TextView label1) {
        this.label1 = label1;
    }

    public TextView getLabel2() {
        return label2;
    }

    public void setLabel2(TextView label2) {
        this.label2 = label2;
    }
}
