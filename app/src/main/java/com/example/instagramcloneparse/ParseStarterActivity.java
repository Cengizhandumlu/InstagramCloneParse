package com.example.instagramcloneparse;

import android.app.Application;
import android.os.Bundle;
import android.os.PersistableBundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.parse.Parse;

public class ParseStarterActivity extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        Parse.setLogLevel(Parse.LOG_LEVEL_DEBUG);

        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId("rog00A79jntYBpR9kQPtuxN3JucdVgjvadIHZ7TM")
                .clientKey("Cmb3opr0UCuoo9QxHbOxHZbVgiMiOq6nmsD9eN2U")
                .server("https://parseapi.back4app.com/")
                .build()
        );

    }
}
