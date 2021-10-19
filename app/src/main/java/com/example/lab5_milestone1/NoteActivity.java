package com.example.lab5_milestone1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class NoteActivity extends AppCompatActivity {
    int noteid = -1;
    EditText note;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);

        note = findViewById(R.id.note);
        Intent intent = getIntent();
        noteid = intent.getIntExtra("noteid", noteid);

        if (noteid != -1){
            Note note = login.notesList.get(noteid);
            String noteContent = note.getContent();
            this.note.setText(noteContent);
        }


    }


    public void saveMethod(View view) {
        note = findViewById(R.id.note);
        String content = note.getText().toString();

        Context context = getApplicationContext();
        SQLiteDatabase sqLiteDatabase = context.openOrCreateDatabase("notes", Context.MODE_PRIVATE,null);

        DBHelper helper = new DBHelper(sqLiteDatabase);

        SharedPreferences sharedPreferences = getSharedPreferences("lab5_milestone1", Context.MODE_PRIVATE);

        String username = sharedPreferences.getString("username", "");

        String title;
        DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
        String date = dateFormat.format(new Date());


        if (noteid == -1){
            title = "NOTE_" + (noteid+1);
            helper.saveNotes(username,title,content,date);
        }
        else{
            title = "NOTE_" + (noteid+1);
            helper.updateNote(title,date,content,username);
        }

        //6.
        Intent login = new Intent (this, login.class);
        startActivity(login);

    }
}