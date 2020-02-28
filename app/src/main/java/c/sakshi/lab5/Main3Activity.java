package c.sakshi.lab5;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Main3Activity extends AppCompatActivity {

    int noteid = -1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);



        //1. Get EditText view.
        EditText noteEdit = findViewById(R.id.noteEdit);

        //2. Get Intent.
        Intent intent = getIntent();

        //3. Get the value of integer "noteid" from intent.
        noteid = intent.getIntExtra("noteid", -1);

        //4. Initialize class variable "noteid" with the value from intent.

        if(noteid != -1) {
            //Display content of note by retrieving "notes" ArrayList in secondActivity.
            Note note = Main2Activity.notes.get(noteid);
            String noteContent = note.getContent();
            noteEdit.setText(noteContent);
        }
    }

    public void onClick(View view){
        //1. Get editText view and the content that use entered
        EditText newNote = findViewById(R.id.noteEdit);
        String content = newNote.getText().toString();

        //2. Initialize SQLiteDatabase instance.
        Context context = getApplicationContext();
        SQLiteDatabase sqLiteDatabase = context.openOrCreateDatabase("notes", Context.MODE_PRIVATE, null);

        //3. Initialize DBHelper class.
        DBHelper dbHelper = new DBHelper(sqLiteDatabase);

        //4. Set username in the following variable by fetching it from SharedPreferences.
        SharedPreferences sharedPreferences = getSharedPreferences("c.sakshi.lab5", Context.MODE_PRIVATE);
        String username = sharedPreferences.getString("username", "");

        //5. Save information to database
        String title;
        DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
        String date = dateFormat.format(new Date());

        if(noteid == -1) {
            title = "NOTE_" + (Main2Activity.notes.size() + 1);
            dbHelper.saveNotes(username, title, content, date);
        } else {
            title = "NOTE_" + (noteid + 1);
            dbHelper.updateNote(title, date, content, username);
        }

        //6. Go to second activity using intents
        Intent intent = new Intent(this, Main2Activity.class);
        startActivity(intent);
    }
}
