package com.example.lab7;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Context;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    File file = new File(Environment.getExternalStorageDirectory().getAbsolutePath(),"outputFile.txt");
    private final static String FILE_NAME = "outputFile.txt";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button saveBtn = findViewById(R.id.saveBtn);
        saveBtn.setOnClickListener(this::saveData);
        Button loadBtn = findViewById(R.id.loadBtn);
        loadBtn.setOnClickListener(this::loadData);
    }
    private void saveData(View view){
        try(FileOutputStream fos = openFileOutput(FILE_NAME,MODE_PRIVATE)){
            EditText textBox = (EditText) findViewById(R.id.saveText);
            byte[] buf = textBox.getText().toString().getBytes();
            fos.write(buf,0,buf.length);
            fos.close();
            Toast.makeText(this, "Данные сохранены!", Toast.LENGTH_SHORT).show();
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
            Toast.makeText(this, "Файл не найден!", Toast.LENGTH_SHORT).show();
        } catch (IOException ex){
            ex.printStackTrace();
            Toast.makeText(this,"Ошибка сохранения файла!",Toast.LENGTH_SHORT).show();
        }
    }
    private void loadData(View view){
        try(FileInputStream fin = openFileInput(FILE_NAME)){
            byte[] buf = new byte[fin.available()];
            fin.read(buf);
            String text = new String(buf);
            TextView textView = findViewById(R.id.outputText);
            textView.setText(text);
        } catch (FileNotFoundException ex){
            ex.printStackTrace();
            Toast.makeText(this, "Файл не найден!", Toast.LENGTH_SHORT).show();
        } catch (IOException ex){
            ex.printStackTrace();
            Toast.makeText(this,"Ошибка чтения файла!", Toast.LENGTH_SHORT).show();
        }

    }
}