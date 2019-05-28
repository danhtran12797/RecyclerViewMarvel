package com.example.mypc.recyclerviewmarvel;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MarvelAdapter extends RecyclerView.Adapter<MarvelAdapter.ViewHolder> {

    private ArrayList<Marvel> marvels;
    private Context context;
    private OnItemClickListener mListener;

    public interface OnItemClickListener{
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener){
        mListener=listener;
    }

    public MarvelAdapter(ArrayList<Marvel> marvels, Context context) {
        this.marvels = marvels;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view=LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row,viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        final Marvel marvel=marvels.get(i);
        viewHolder.txtHead.setText(marvel.getName());
        viewHolder.txtDesc.setText(marvel.getDesc());
        Picasso.get().load(marvel.getImage()).into(viewHolder.imgMarvel);

    }

    @Override
     public int getItemCount() {
        return marvels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtHead;
        TextView txtDesc;
        ImageView imgMarvel;

        public ViewHolder(@NonNull final View itemView) {
            super(itemView);
            txtHead=itemView.findViewById(R.id.txtHead);
            txtDesc=itemView.findViewById(R.id.txtDesc);
            imgMarvel=itemView.findViewById(R.id.imgMarvel);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(mListener!=null){
                        int position=getAdapterPosition();
                        if(position!=RecyclerView.NO_POSITION){
                            mListener.onItemClick(position);
                        }
                    }
                }
            });
        }
    }
}
