package com.example.instagramcloneparse;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

public class SignUpActivity extends AppCompatActivity {

    EditText userNameText, passwordText;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        userNameText=findViewById(R.id.sign_up_activity_name_text);
        passwordText=findViewById(R.id.sign_up_activity_password_text);

        ParseUser parseUser=ParseUser.getCurrentUser();
        if (parseUser!=null){
            Intent intent=new Intent(getApplicationContext(),FeedActivity.class);
            startActivity(intent);
        }
    }

    public void signIn(View view){
        //Kullanıcının giris yapma islemi

        ParseUser.logInInBackground(userNameText.getText().toString(), passwordText.getText().toString(), new LogInCallback() {
            @Override
            public void done(ParseUser user, ParseException e) {

                if(e != null){
                    Toast.makeText(getApplicationContext(),e.getLocalizedMessage(),Toast.LENGTH_LONG).show();
                }
                else{
                    Toast.makeText(getApplicationContext(),"Welcome"+user.getUsername(),Toast.LENGTH_LONG).show();
                    //Intent
                    Intent intent=new Intent(getApplicationContext(),FeedActivity.class);
                    startActivity(intent);

                }
            }
        });
    }

    public void signUp(View view){//kullanıcı kayıt islemi

        ParseUser user=new ParseUser();

        user.setUsername(userNameText.getText().toString());//kullanıcı ve sifre aldık
        user.setPassword(passwordText.getText().toString());

        user.signUpInBackground(new SignUpCallback() {
            @Override
            public void done(ParseException e) {
                if (e!=null){

                    Toast.makeText(getApplicationContext(),e.getLocalizedMessage(),Toast.LENGTH_LONG).show();
                }else{

                    Toast.makeText(getApplicationContext(),"User Created !!!",Toast.LENGTH_LONG).show();
                    //nereye gideceksek onun icin intent yapmamız gerekiyor.

                    Intent intent=new Intent(getApplicationContext(),FeedActivity.class);
                    startActivity(intent);

                }
            }
        });
    }

}

