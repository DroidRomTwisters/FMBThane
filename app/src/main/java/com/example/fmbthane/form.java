package com.example.fmbthane;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class form extends AppCompatActivity {
EditText et1,et2,et3,et4,et5,et6;
Button bt1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_form);
        et1=(EditText)findViewById(R.id.et1);
        et2=(EditText)findViewById(R.id.et2);
        et3=(EditText)findViewById(R.id.et3);
        et4=(EditText)findViewById(R.id.et4);
        et5=(EditText)findViewById(R.id.et5);
        et6=(EditText)findViewById(R.id.et6);

        bt1=(Button)findViewById(R.id.bt);

        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String uriText = "mailto:ibth5253@gmail.com"+
                        "?subject=" + Uri.encode("START/STOP Thaali") +
                        "&body=" + Uri.encode("Name:"+et1.getText()+
                         "\n Its Number:"+et2.getText()+
                         "\n Thaali Number:"+et3.getText()+
                         "\n Address:"+et4.getText()+
                         "\n Date to Stop Thaali from:"+et5.getText()+
                        "\n Date to Start Thaali from:"+et6.getText());
                Uri uri = Uri.parse(uriText);
                Intent i=new Intent(Intent.ACTION_SENDTO);
                i.setType("message/html");
                i.setData(uri);

                try {
                    startActivity(i.createChooser(i,"Please Select Gmail"));
                }
                catch (android.content.ActivityNotFoundException ex){
                    Toast.makeText(getApplicationContext(),"No gmail app found",Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    public void hideKeyboard(View view) {
        InputMethodManager inputMethodManager=(InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(),0);
    }
}