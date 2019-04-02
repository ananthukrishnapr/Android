package com.example.ananthu.sqlite;

import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity
{

    DataBaseHelper db;
    EditText rollno, name, age,mark;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        db = new DataBaseHelper(this);
        rollno = (EditText) findViewById(R.id.rollno);
        name = (EditText) findViewById(R.id.name);
        age = (EditText) findViewById(R.id.age);
        mark = (EditText) findViewById(R.id.mark);
    }

    public void insert(View view)
    {
                boolean isinsert = db.insertData(rollno.getText().toString(),
                        name.getText().toString(), age.getText().toString(),mark.getText().toString());
                if (isinsert == true)
                    Toast.makeText(MainActivity.this, "Data saved", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(MainActivity.this, "Data not saved", Toast.LENGTH_SHORT).show();
    }
    public void read(View view)
    {
        String r = rollno.getText().toString();
               Cursor c = db.getAllData();
               if(c.getCount()==0)
               {
                   Toast.makeText(MainActivity.this, "No data found", Toast.LENGTH_SHORT).show();
                   return;
               }
               StringBuffer stringBuffer = new StringBuffer();
               while(c.moveToNext()) {
                   if (r.equals(c.getString(0))) {
                       stringBuffer.append("RollNo = " + c.getString(0) + "\n");
                       stringBuffer.append("Name = " + c.getString(1) + "\n");
                       stringBuffer.append("Age = " + c.getString(2) + "\n");
                       stringBuffer.append("Mark = " + c.getString(3) + "\n\n\n");
                       break;
                   }
               }

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle("Student Details");
        builder.setMessage(stringBuffer.toString());
        builder.show();
    }

    public void update(View view)
    {
               boolean isupdated = db.updateData(rollno.getText().toString(),
                       name.getText().toString(), age.getText().toString(),mark.getText().toString());
               if(isupdated == true)
                   Toast.makeText(MainActivity.this, "Data updated", Toast.LENGTH_SHORT).show();
                else
                   Toast.makeText(MainActivity.this, "Data not updated", Toast.LENGTH_SHORT).show();

    }
    public void delete(View view)
    {
              Integer deleted_row = db.deleteData(rollno.getText().toString());
              if(deleted_row > 0)
                  Toast.makeText(MainActivity.this, deleted_row+" row(s) deleted", Toast.LENGTH_SHORT).show();
              else
                  Toast.makeText(MainActivity.this, deleted_row+" row(s) deleted", Toast.LENGTH_SHORT).show();
    }

}
