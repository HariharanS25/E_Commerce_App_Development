package com.firstapp.assignment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    EditText username,password;
    Button login2;

    DBHelper myDb;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        username=(EditText) findViewById(R.id.Username1);

        password= (EditText) findViewById (R.id.Password1);

        login2=  findViewById (R.id.login2);

        myDb=new DBHelper(this);

        login2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user=username.getText().toString();
                String pass=password.getText().toString();


                if(user.equals("")|| pass.equals("")){
                    Toast.makeText(LoginActivity.this, "Enter the Credentials", Toast.LENGTH_SHORT).show();
                }
                else{
                    Boolean result = myDb.checkusernamePassword(user,pass);
                    if(result==true){
                        Intent intent=new Intent(getApplicationContext(),Home_activity.class);
                        intent.putExtra("message key",user);
                        startActivity(intent);
                    }
                    else{
                        Toast.makeText(LoginActivity.this, "Invalid Details", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });



    }
}