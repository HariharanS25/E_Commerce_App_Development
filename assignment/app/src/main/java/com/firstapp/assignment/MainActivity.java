package com.firstapp.assignment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText username,email,password,repassword;
    Button btnRegister,btnSignin;
    DBHelper myDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        username=(EditText)findViewById(R.id.Username);
        email=(EditText)findViewById(R.id.Email);
        password=(EditText)findViewById(R.id.Password);
        repassword=(EditText)findViewById(R.id.Repassword);

        btnRegister=(Button) findViewById(R.id.register);
        btnSignin=(Button)findViewById(R.id.login);

        myDB=new DBHelper(this);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = username.getText().toString();
                String emai=email.getText().toString();
                String pass = password.getText().toString();
                String repass = repassword.getText().toString();
                if (user.equals("") || emai.equals("")|| pass.equals("") || repass.equals("")) {
                    Toast.makeText(MainActivity.this, "Fill all the fields", Toast.LENGTH_SHORT).show();
                }
                else{
                    if(pass.equals(repass)) {
                        Boolean usercheckResult = myDB.checkuser(user);
                            if(usercheckResult==false){
                                Boolean regResult= myDB.insertData(user,pass);
                                        if(regResult==true){
                                            Toast.makeText(MainActivity.this, "Registration Successful", Toast.LENGTH_SHORT).show();
                                            Intent intent=new Intent(getApplicationContext(),LoginActivity.class);
                                            startActivity(intent);

                                        }else{
                                            Toast.makeText(MainActivity.this, "Registration Failed", Toast.LENGTH_SHORT).show();
                                        }
                            }else{
                                Toast.makeText(MainActivity.this, "User already exists \n Go to Signin", Toast.LENGTH_SHORT).show();
                            }
                    }
                    else{
                        Toast.makeText(MainActivity.this, "Password mismatch", Toast.LENGTH_SHORT).show();
                    }
                }


            }
        });

        btnSignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(),LoginActivity.class);
                startActivity(intent);
            }
        });





    }
}