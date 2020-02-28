package c.sakshi.lab5;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Main2Activity extends AppCompatActivity {

    public static ArrayList<Note> notes = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        //1. Display welocome message, Fetch username from sharedPreferences
        //2. Get SQL instance
        //3. Initiate the "notes" class using readNotes. Use username from sharedPreferences
        //4.Create and ArrayList<String> object by iterating over notes object.

        ArrayList<String> displayNotes = new ArrayList<>();
        for (Note note: notes) {
            displayNotes.add(String.format("Title: %s\nDate:%s", note.getTitle(), note.getDate()));
        }

        //5. Use ListView view to display notes on Screen,
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, displayNotes);
        ListView listView = findViewById(R.id.notesList);
        listView.setAdapter(adapter);

        //6. Add onItemClickListener for ListView item
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //initialize intent to take user to third activity
                Intent intent = new Intent(getApplicationContext(), Main3Activity.class);
                intent.putExtra("noteid", position);
                startActivity(intent);
            }
        });
        SharedPreferences sharedPreferences = getSharedPreferences("c.sakshi.lab5", Context.MODE_PRIVATE);

        TextView welcome = findViewById(R.id.welcome);
        welcome.setText("Welcome " + sharedPreferences.getString("username", "ERROR") + "!");
    }

    public void logoutFunction() {
        SharedPreferences sharedPreferences = getSharedPreferences("c.sakshi.lab5", Context.MODE_PRIVATE);
        sharedPreferences.edit().remove("username").apply();
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu1, menu);
        return true;
    }

    public void goToActivity3() {
        Intent intent = new Intent(this, Main3Activity.class);
        startActivity(intent);
    }



    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.logout:
                logoutFunction();
                return true;
            case R.id.note:
                goToActivity3();
                return true;

            default: return super.onOptionsItemSelected(item);
        }
    }
}
