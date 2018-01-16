package com.example.morig.gurahu;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;

public class MainActivity extends AppCompatActivity {
    String FILE_NAME="FileSampleFile";
    TextView msg;
    EditText name;
    EditText score;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        msg = (TextView)findViewById(R.id.message);
        name =(EditText)findViewById(R.id.name);
        score = (EditText)findViewById(R.id.score);

        Button saveB = (Button)findViewById(R.id.save);
        saveB.setOnClickListener(new ButtonSave());

        Button readB = (Button)findViewById(R.id.show);
        readB.setOnClickListener(new ButtonShow());

        Button delB = (Button)findViewById(R.id.delete);
        delB.setOnClickListener(new ButtonDel());
    }

    class ButtonSave implements View.OnClickListener {
        public void onClick(View v) {
            try {
                FileOutputStream stream = openFileOutput(FILE_NAME, MODE_APPEND);
                PrintStream out = new PrintStream(stream);
                String hantei = "SAFE";
                int p = 0;
                try {
                    p = Integer.parseInt(score.getText().toString());
                } catch (NumberFormatException e) {
                }
                if (p >= 80) {
                    hantei = "OVER";
                }
                out.println(name.getText() + "," + score.getText() + "," + hantei);
                out.close();
                msg.setText("保存");
            } catch (IOException e) {
                msg.setText("保存に失敗");
            }
        }
    }
        class ButtonShow implements View.OnClickListener{
            public void onClick(View v){
                String str ="";
                try{
                    FileInputStream stream =openFileInput(FILE_NAME);
                    BufferedReader in = new BufferedReader(new InputStreamReader(stream));
                    for(String line = "";(line = in.readLine()) !=null;){
                        str += line + "\n";
                    }
                    in.close();
                    msg.setText(str);
                }catch(IOException e){
                    msg.setText("取得に失敗");
                }
            }
        }
        class ButtonDel implements View.OnClickListener{
            public void onClick(View v){
                deleteFile(FILE_NAME);
                msg.setText("削除");
            }
        }
    }
