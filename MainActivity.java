package com.example.sqlliteproject;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        // sql ile çalışılan yerlerde try catch tavsiye edilir
        try {
            //openOrCreateDatabase() bu veri tabanını aç yoksa oluştur
            SQLiteDatabase database=this.openOrCreateDatabase("Musicans",MODE_PRIVATE,null);
            database.execSQL(" CREATE  TABLE  IF  NOT EXISTS musicians( id INTEGER PRIMARY KEY, name VARCHAR,age INT)");
          // database.execSQL(" INSERT INTO musicians ( name ,age) VALUES ('james',50)");
            //database.execSQL(" INSERT INTO musicians ( name ,age) VALUES ('lars',58)");
            database.execSQL(" UPDATE musicians SET age=61 WHERE name ='lars'");
            database.execSQL("DELETE FROM musicians WHERE id =2");





            Cursor cursor =database.rawQuery("SELECT * FROM musicians",null);
            int name=cursor.getColumnIndex("name");//name'nin kaçıncı indexte olduğunu söyler
            int age = cursor.getColumnIndex("age");
            int id =cursor.getColumnIndex("id");
            while (cursor.moveToNext()){// movetotext  ilerleyebildiği kadar ilerlesin
                System.out.println("name:"+cursor.getString(name));
                System.out.println("age:"+cursor.getInt(age));
                System.out.println("id"+cursor.getInt(id));


            }
            cursor.close();// işlem bittikten sonra kapat



            // selectionsargs  filtreleme  isteyip istemediğmizi sorar
            // yıldız her şeyi dahil et demektir
            // tek tek hücreleri gezip verileri okur
        }catch (Exception e){
            e.printStackTrace();// hatanın detaylarını yazdırır

        }
    }
}