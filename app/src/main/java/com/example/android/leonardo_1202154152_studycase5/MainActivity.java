package com.example.android.leonardo_1202154152_studycase5;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    //deklarasi variable yang akan digunakan
    Database dtbase;
    RecyclerView rv;
    Adapter adapter;
    ArrayList<AddData> datalist;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //set title activity menjadi ToDoList
        setTitle("ToDoList");

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AddToDo.class);
                //memulai intent
                startActivity(intent);
            }
        });

        //mengakses recyclerview yang ada pada layout
        rv = findViewById(R.id.recview);
        //membuat araylist baru
        datalist = new ArrayList<>();
        //membuat database baru
        dtbase = new Database(this);
        //memanggil method readdata
        dtbase.ReadData(datalist);

        //menginisialisasi shared preference
        SharedPreferences sharedP = this.getApplicationContext().getSharedPreferences("Preferences", 0);
        int color = sharedP.getInt("Colourground", R.color.white);

        //membuat adapter baru
        adapter = new Adapter(this,datalist, color);
        //menghindari perubahan ukuran yang tidak perlu ketika menambahkan / hapus item pada recycler view
        rv.setHasFixedSize(true);
        //menampilkan layoutnya linier
        rv.setLayoutManager(new LinearLayoutManager(this));
        //inisiasi adapter untuk recycler view
        rv.setAdapter(adapter);

        //menjalankan method hapus data pada list to do
        hapusGeser();
    }

    //membuat method untuk menghapus item pada to do list
    public void hapusGeser(){
        //membuat touch helper baru untuk recycler view
        ItemTouchHelper.SimpleCallback touchcall = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {

            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                int position = viewHolder.getAdapterPosition();
                AddData current = adapter.getData(position);
                //apabila item di swipe ke arah kiri
                if(direction==ItemTouchHelper.LEFT){
                    //remove item yang dipilih dengan mengenali todonya sebagai primary key
                    if(dtbase.RemoveData(current.getTodo())){
                        //menghapus data
                        adapter.deleteData(position);
                        //membuat toast ketika data list berhasil dihapus
                        Toast.makeText(MainActivity.this,"Data berhasil dihapus",Toast.LENGTH_SHORT).show();
                    }
                }
            }
        };
        //menentukan itemtouchhelper untuk recycler view
        ItemTouchHelper touchhelp = new ItemTouchHelper(touchcall);
        touchhelp.attachToRecyclerView(rv);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //mendapatkan id dari item yang
        int id = item.getItemId();
        //apabila item yang dipilih adalah setting
        if (id==R.id.action_settings){
            //membuat intent baru dari list to do ke pengaturan
            Intent intent = new Intent(MainActivity.this, Settings.class);
            //memulai intent
            startActivity(intent);
            //menutup aktivitas setelah intent dijalankan
            finish();
        }
        return true;
    }
}
