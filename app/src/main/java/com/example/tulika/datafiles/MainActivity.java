package com.example.tulika.datafiles;

import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    Button savebtn;
    static String a;
    EditText pack,command,reply;
    MyDbADapter myDbADapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        savebtn = (Button) findViewById(R.id.button);
        pack= (EditText) findViewById(R.id.editText);

        command = (EditText) findViewById(R.id.editText2);
        reply = (EditText) findViewById(R.id.editText3);
        myDbADapter = new MyDbADapter(MainActivity.this);
        myDbADapter.Opendb();
        savebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
                intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, "en_US");
                try {
                    startActivityForResult(intent, 1);
                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(MainActivity.this, "Sorry Your Device does not support Speech to Text feautre", Toast.LENGTH_SHORT).show();
                }
                Toast.makeText(MainActivity.this, "Saved", Toast.LENGTH_SHORT).show();


            }
        });
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1) {
            if (resultCode == RESULT_OK && data != null) {
                 ArrayList<String> al = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                String a=al.get(0);

        }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }


    @Override
    protected void onDestroy() {
        myDbADapter.close();
        super.onDestroy();
    }
}

