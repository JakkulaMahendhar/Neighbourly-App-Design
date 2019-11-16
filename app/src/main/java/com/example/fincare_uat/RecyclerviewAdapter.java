package com.example.fincare_uat;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.facebook.shimmer.ShimmerFrameLayout;

import java.util.List;

public class RecyclerviewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    // The items to display in your RecyclerView
    private List<Object> items;

    private final int USER = 0, IMAGE = 1;

    Context ctx;

    ShimmerFrameLayout mShimmerViewContainer;

    // Provide a suitable constructor (depends on the kind of dataset)
    public RecyclerviewAdapter(List<Object> items, Context ctx, ShimmerFrameLayout shimmerFrameLayout) {
        try {
            this.items = items;
            this.ctx = ctx;
            this.mShimmerViewContainer = shimmerFrameLayout;
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        RecyclerView.ViewHolder viewHolder = null;
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        try {
            switch (i) {
                case USER:
                    View v1 = inflater.inflate(R.layout.layout_viewholder1, viewGroup, false);
                    viewHolder = new Viewholder1(v1);
                    break;
                case IMAGE:
                    View v2 = inflater.inflate(R.layout.layout_viewholder2, viewGroup, false);
                    viewHolder = new Viewholder2(v2);
                    break;
//            default:
//                View v = inflater.inflate(android.R.layout.simple_list_item_1, viewGroup, false);
//                viewHolder = new RecyclerViewSimpleTextViewHolder(v);
//                break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        try {
            switch (viewHolder.getItemViewType()) {
                case USER:
                    Viewholder1 vh1 = (Viewholder1) viewHolder;
                    configureViewHolder1(vh1, i);
                    vh1.itemView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                                Intent i = new Intent(ctx,RecylerItemViewActivity.class);
                                i.putExtra("user","user");
                                ctx.startActivity(i);
                        }
                    });
                    break;
                case IMAGE:
                    Viewholder2 vh2 = (Viewholder2) viewHolder;
                    configureViewHolder2(vh2);
                    vh2.itemView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent i = new Intent(ctx,RecylerItemViewActivity.class);
                            i.putExtra("image","image");
                            ctx.startActivity(i);
                        }
                    });
                    break;


            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (items.get(position) instanceof User) {
            return USER;
        } else if (items.get(position) instanceof String) {
            return IMAGE;
        }
        return -1;
    }

    private void configureViewHolder1(Viewholder1 vh1, int position) {
        User user = (User) items.get(position);
        if (user != null) {
            vh1.getLabel1().setText(user.name);
            vh1.getLabel2().setText(user.hometown);
        }
    }

    private void configureViewHolder2(final Viewholder2 vh2) {
        mShimmerViewContainer.startShimmerAnimation();
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                vh2.getImageView().setImageResource(R.drawable.ic_launcher_background);
                mShimmerViewContainer.stopShimmerAnimation();
                mShimmerViewContainer.setVisibility(View.GONE);
            }
        }, 2000);

    }
}
