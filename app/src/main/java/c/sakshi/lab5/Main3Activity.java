package c.sakshi.lab5;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

public class Main3Activity extends AppCompatActivity {

    int noteid = -1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        //1. Get EditText view.
        //2. Get Intent.
        //3. Get the value of integer "noteid" from intent.
        //4. Initialize class variable "noteid" with the value from intent.

        if(noteid != -1) {
            //Display content of note by retrieving "notes" ArrayList in secondActivity.
            Note note = Main2Activity.notes.get(noteid);
            String noteContent = note.getContent();
        }
    }

    public void onClick(View view){

    }
}
