package com.example.android3_lesosn3.data.adapters;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android3_lesosn3.R;
import com.example.android3_lesosn3.data.interf.OnPostClickListener;
import com.example.android3_lesosn3.data.models.AllPostsModel;

import java.util.ArrayList;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.MainViewHolder> {

    public ArrayList<AllPostsModel> AllPostsModels;

    OnPostClickListener onPostClickListener;

    public MainAdapter(ArrayList<AllPostsModel> allPostsModels, OnPostClickListener onPostClickListener) {
        AllPostsModels = allPostsModels;
        this.onPostClickListener = onPostClickListener;
    }


    @NonNull
    @Override
    public MainViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.main_list_adapter, parent, false);
        return new MainViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull MainViewHolder holder, int position) {
        holder.text_title.setText(AllPostsModels.get(position).getTitle());
        holder.text_content.setText(AllPostsModels.get(position).getContent());
        holder.text_user.setText(AllPostsModels.get(position).getUser().toString());
        holder.text_group.setText(AllPostsModels.get(position).getGroup().toString());
    }

    @Override
    public int getItemCount() {
        return AllPostsModels.size();
    }

    public class MainViewHolder extends RecyclerView.ViewHolder {
        TextView text_title;
        TextView text_content;
        TextView text_user;
        TextView text_group;

        public MainViewHolder(@NonNull View itemView) {
            super(itemView);
            text_title = itemView.findViewById(R.id.list_title);
            text_content = itemView.findViewById(R.id.list_content);
            text_user = itemView.findViewById(R.id.list_user);
            text_group = itemView.findViewById(R.id.list_group);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onPostClickListener != null) {
                        onPostClickListener.onPostClick(getAdapterPosition());
                    }
                }
            });
            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    if (onPostClickListener != null) {
                        onPostClickListener.onPostLongClick(getAdapterPosition());
                    }
                    return true;
                }
            });


        }
    }
}
