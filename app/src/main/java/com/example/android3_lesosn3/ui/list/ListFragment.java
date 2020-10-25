package com.example.android3_lesosn3.ui.list;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android3_lesosn3.R;
import com.example.android3_lesosn3.data.adapters.MainAdapter;
import com.example.android3_lesosn3.data.interf.OnPostClickListener;
import com.example.android3_lesosn3.data.models.AllPostsModel;
import com.example.android3_lesosn3.data.network.HerokuappService;
import com.example.android3_lesosn3.ui.App;
import com.example.android3_lesosn3.ui.form.FormActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;


public class ListFragment extends Fragment implements OnPostClickListener {

    RecyclerView recyclerView;
    MainAdapter adapter;
    ArrayList<AllPostsModel> postsModels;
    int idDelete;

    FloatingActionButton actionButton;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_list, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerViewActive(view);

        actionButton = view.findViewById(R.id.actionButton);
        actionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), FormActivity.class));
            }
        });

        viewAllPosts();
    }

    private void viewAllPosts() {
        App.herokuappService.getPosts(new HerokuappService.ArrayCallBackPosts() {
            @Override
            public void onSuccess(ArrayList<AllPostsModel> allPostsModels) {
                if (allPostsModels != null) {
                    postsModels.clear();
                    postsModels.addAll(allPostsModels);
                    adapter.notifyDataSetChanged();
                }
            }
            @Override
            public void onFailure(Throwable throwable) {
            }
        });
    }




    private void recyclerViewActive(View view) {
        recyclerView = view.findViewById(R.id.recycler_view);
        recyclerView.setAdapter(adapter);
        postsModels = new ArrayList<>();
        adapter = new MainAdapter(postsModels, this);
        adapter.notifyDataSetChanged();

    }

    @Override
    public void onPostClick(int position) {
        Toast.makeText(getContext(), "Success", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onPostLongClick(int position) {
        Integer id = postsModels.get(position).getId();
        App.herokuappService.deletePost(id, new HerokuappService.CallBackPost() {
            @Override
            public void onSuccess(AllPostsModel allPostsModel) {
                Toast.makeText(getContext(), "Deleted!", Toast.LENGTH_SHORT).show();
                postsModels.remove(position);
                adapter.notifyItemChanged(position);
            }

            @Override
            public void onFailure(Throwable throwable) {
                Toast.makeText(getContext(), "Failed!!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}