package com.example.android3_lesosn3.ui.form;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.android3_lesosn3.R;
import com.example.android3_lesosn3.data.models.AllPostsModel;
import com.example.android3_lesosn3.data.network.HerokuappService;
import com.example.android3_lesosn3.ui.App;

import org.checkerframework.checker.units.qual.A;

public class FormActivity extends AppCompatActivity {

    private EditText title;
    private EditText group;
    private EditText content;
    private EditText user;
    private Button sendPost;

    private AllPostsModel allPostsModelCreate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);

        initId();
        addPost();

    }

    private void addPost() {
        sendPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("sendPOST", "success Aza");
                if (!title.getText().toString().isEmpty() && !content.getText().toString().isEmpty()
                        && user.getText().toString().isEmpty() && group.getText().toString().isEmpty()) {
                    allPostsModelCreate = new AllPostsModel(title.getText().toString(), content.getText().toString(),
                            2, Integer.valueOf(group.getText().toString()));
                    cratePost(allPostsModelCreate);
                } else {
                    title.setError("fill in the field");
                    content.setError("fill in the field");
                    group.setError("fill in the field");
                }
            }
        });
    }

    private void cratePost(AllPostsModel allPostsModelCreate){
        App.herokuappService.cratePost(allPostsModelCreate, new HerokuappService.CallBackPost() {
            @Override
            public void onSuccess(AllPostsModel allPostsModel) {
                Log.d("sendPost", "onSuccess: createPost");
                finish();
            }

            @Override
            public void onFailure(Throwable throwable) {

            }
        });
    }



    private void initId() {
        title = findViewById(R.id.et_title_form);
        content = findViewById(R.id.et_content_form);
        group = findViewById(R.id.et_group_form);
        user = findViewById(R.id.et_user_form);
        sendPost = findViewById(R.id.btn_form);
    }
}