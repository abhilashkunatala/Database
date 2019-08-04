package com.example.database;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    EditText editText_name;
    EditText editText_surname;
    EditText editText_marks;
    Button button_add;
    Button button_view;
    Button button_update;
    EditText editText_Id;
    Button button_delete;



    DatabaseHelper MyDb;
    //creating the instance of databasehelper

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MyDb = new DatabaseHelper(this);
        //its going to call the constructor in databasehelper.javax

        editText_name =(EditText)findViewById(R.id.editText_Name);
        editText_surname=(EditText)findViewById(R.id.editText_Surname);
        editText_marks = (EditText)findViewById(R.id.editText_Marks);
        button_add = (Button)findViewById(R.id.button_add);
        button_view = (Button)findViewById(R.id.button_View);

        editText_Id = (EditText)findViewById(R.id.editText_Id) ;
        button_update = (Button)findViewById(R.id.button_update) ;
        button_delete = (Button)findViewById(R.id.button_delete);


        addData();
        viewAll();
        UpdateData();
        DeleteData();



    }


    public void DeleteData(){
        button_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    Integer deleteRows = MyDb.deletefunction(editText_Id.getText().toString());

                    if(deleteRows>0){
                        Toast.makeText(MainActivity.this,"Data deleted",Toast.LENGTH_SHORT).show();
                    }
                    else{
                        Toast.makeText(MainActivity.this,"Data Not Deleted",Toast.LENGTH_SHORT).show();

                    }

            }
        });
    }


    public void UpdateData(){
        button_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            boolean isUpdate = MyDb.updatefunction(editText_Id.getText().toString(),editText_name.getText().toString(),editText_surname.getText().toString(),
                    editText_marks.getText().toString());

           if(isUpdate==true){
                Toast.makeText(MainActivity.this,"Data Updated",Toast.LENGTH_SHORT).show();

            }

            else {
                Toast.makeText(MainActivity.this,"Data  not Updated",Toast.LENGTH_SHORT).show();


            }
            }
        });
    }
    public void addData (){
        button_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean isInserted = MyDb.insertData(editText_name.getText().toString(),
                      editText_surname.getText().toString(),editText_marks.getText().toString()  );
                    if(isInserted=true)
                        Toast.makeText(MainActivity.this,"Data Inserted",Toast.LENGTH_SHORT).show();
                    else

                        Toast.makeText(MainActivity.this,"Data  not Inserted",Toast.LENGTH_SHORT).show();


            }
        });

    }

    public void viewAll(){
        button_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

               Cursor res =  MyDb.getAllData();
               if(res.getCount()==0){

                   showMessage("error", "nothing found");

                   //show msg
                   return;
               }
               StringBuffer buffer = new StringBuffer();
               while(res.moveToNext()){
                   buffer.append("Id:"+ res.getString(0)+"\n");
                   buffer.append("Name:"+ res.getString(1)+"\n");
                   buffer.append("Surname:"+ res.getString(2)+"\n");
                   buffer.append("Marks:"+ res.getString(3)+"\n");
               }
                    //show all data
                    showMessage("Data ", buffer.toString());


            }
        });
    }

    public void showMessage(String title , String Message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this );
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();

    }
}

