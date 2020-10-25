package com.example.android3_lesosn3.ui;

import android.app.Application;

import com.example.android3_lesosn3.data.local.PreferenceUtils;
import com.example.android3_lesosn3.data.network.HerokuappService;
import com.example.android3_lesosn3.data.network.auth_service.AuthService;

public class App extends Application {

    public static HerokuappService herokuappService;

    public static AuthService authService;



    @Override
    public void onCreate() {
        super.onCreate();
        herokuappService = new HerokuappService();
        authService = new AuthService();

        PreferenceUtils.init(this);

    }

}
