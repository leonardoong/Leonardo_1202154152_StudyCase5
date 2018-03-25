package com.example.android.leonardo_1202154152_studycase5;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class AddToDo extends AppCompatActivity {
    //deklarasi variable yang akan digunakan
    EditText ToDo, Description, Priority;
    Database dtbase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_to_do);
        //set title menjadi add to do
        setTitle("Add To Do");

        //mengakses id edit text pada layout
        ToDo = (EditText) findViewById(R.id.editTodo);
        Description = (EditText) findViewById(R.id.editDesc);
        Priority = (EditText) findViewById(R.id.editPriority);
        dtbase = new Database(this);
    }


    public void tambah(View view) {
        //mengambil data yang diisikan user
        String toDo = ToDo.getText().toString();
        String desc = Description.getText().toString();
        String prior = Priority.getText().toString();

        //mengecek jika ada data yang tidak diisikan oleh user
        if (TextUtils.isEmpty(toDo) || TextUtils.isEmpty(desc) || TextUtils.isEmpty(prior)) {
            //apabila edit text kosong maka akan muncul toast bahwa tidak bisa menambah ke dalam list
            Toast.makeText(this, "Harap isi semua data terlebih dahulu", Toast.LENGTH_SHORT).show();
            //set semua edit text menjadi kosong
            ToDo.setText(null);
            Description.setText(null);
            Priority.setText(null);
        } else {
            //apabila data todoname, deskripsi dan prioritas di isi
            //maka data akan diinputkan
            dtbase.InputData(new AddData((toDo), (desc), (prior)));
            //maka akan menampilkan toast bawha data berhasil di tambahkan ke dalam list
            Toast.makeText(this, "To Do List berhasil ditambahkan!", Toast.LENGTH_SHORT).show();
            //berpindah dari add to do ke list to do
            startActivity(new Intent(AddToDo.this, MainActivity.class));
            //menutup aktivitas agar tidak kembali ke activity yang dijalankan setelah intent
            this.finish();
        }
    }
}


