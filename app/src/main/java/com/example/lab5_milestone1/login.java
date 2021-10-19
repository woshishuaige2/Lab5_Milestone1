package com.example.lab5_milestone1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class login extends AppCompatActivity {

    TextView welcome;
    static ArrayList<Note> notesList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        welcome = (TextView) findViewById(R.id.welcome);
        Intent intent = getIntent();
        String str = intent.getStringExtra("username");
        welcome.setText("Welcome " + str + "!");

        Context context = getApplicationContext();
        SQLiteDatabase sqLiteDatabase = context.openOrCreateDatabase("notes", Context.MODE_PRIVATE,null);

        DBHelper helper = new DBHelper(sqLiteDatabase);
        notesList = helper.readNotes(str);
        ArrayList<String> displayNotes = new ArrayList<>();
        for (Note note : notesList){
            displayNotes.add(String.format("Title:%s\nDate:%s", note.getTitle(), note.getDate()));
        }

        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, displayNotes);
        ListView listView = (ListView) findViewById(R.id.notesListView);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getApplicationContext(), NoteActivity.class);
                intent.putExtra("noteid", i);
                startActivity(intent);
            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        super.onOptionsItemSelected(item);
        if (item.getItemId() == R.id.logOut){
            Intent intent = new Intent (this, MainActivity.class);
            SharedPreferences sharedPreferences = getSharedPreferences("d.gong.lab5", Context.MODE_PRIVATE);
            sharedPreferences.edit().remove(MainActivity.usernameKey).apply();
            startActivity(intent);
            return true;
        }
        if (item.getItemId() == R.id.addNote){
            Intent intent = new Intent (this, NoteActivity.class);
            SharedPreferences sharedPreferences = getSharedPreferences("d.gong.lab5", Context.MODE_PRIVATE);
            sharedPreferences.edit().remove(MainActivity.usernameKey).apply();
            startActivity(intent);
            return true;
        }


        return super.onOptionsItemSelected(item);
    }
}