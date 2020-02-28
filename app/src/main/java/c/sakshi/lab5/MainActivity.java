package c.sakshi.lab5;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String usernameKey = "username";

        SharedPreferences sharedPreferences = getSharedPreferences("c.sakshi.lab5", Context.MODE_PRIVATE);

        if(!sharedPreferences.getString(usernameKey, "").equals("")) {
            Intent intent = new Intent(this, Main2Activity.class);
            startActivity(intent);

        } else {
            setContentView(R.layout.activity_main);
        }
    }

    public void clickFunction(View view) {
        EditText userText = findViewById(R.id.usernameText);
//        EditText passText = findViewById(R.id.passwordText);
        String userName = userText.getText().toString();
//        String password =  passText.getText().toString();
        SharedPreferences sharedPreferences = getSharedPreferences("c.sakshi.lab5", Context.MODE_PRIVATE);
        sharedPreferences.edit().putString("username", userName).apply();

        goToActivity2();
    }

    public void goToActivity2() {
        Intent intent = new Intent(this, Main2Activity.class);
        startActivity(intent);
    }
}
