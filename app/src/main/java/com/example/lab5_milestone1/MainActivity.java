package com.example.lab5_milestone1;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    static String usernameKey = "username";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);

        SharedPreferences sharedPreferences = getSharedPreferences("d.gong.lab5",Context.MODE_PRIVATE);

        if (!sharedPreferences.getString(usernameKey,"").equals("")){
            String username = sharedPreferences.getString(usernameKey, "");
            Intent intent = new Intent(this, login.class);
            intent.putExtra("username", username);
            startActivity(intent);
        }
        else {
            setContentView(R.layout.activity_main);
        }


    }

    public void clickFunction(View view) {
        //Log.i("Info", "Button Pressed");
        EditText myTextField = (EditText) findViewById(R.id.username);
        String str = myTextField.getText().toString();

        //Toast.makeText(MainActivity.this, myTextField.getText().toString(), Toast.LENGTH_LONG).show();
        goToActivity2(str);
    }

    public void goToActivity2(String s){
        Intent intent = new Intent (this, login.class);
        intent.putExtra("message", s);
        startActivity(intent);
    }

    public void onButtonClick(View view){
        EditText usernameT = findViewById(R.id.username);
        EditText passwordT = findViewById(R.id.password);

        String username = usernameT.getText().toString();
        String password = passwordT.getText().toString();

        SharedPreferences sharedPreferences = getSharedPreferences("d.gong.lab5", Context.MODE_PRIVATE);
        sharedPreferences.edit().putString("username", username).apply();

        goToActivity2(username);
    }

}