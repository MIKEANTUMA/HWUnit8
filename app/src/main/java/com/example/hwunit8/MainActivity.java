package com.example.hwunit8;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;

public class MainActivity extends AppCompatActivity {

    LinearLayout ll;
    List<String> list;
    EditText et;
    Map<String, Float> values = new HashMap<>();
    graph g;
    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ll=findViewById(R.id.myMainLayout);





//        g = findViewById(R.id.myGraph);
        ArrayList<EditText> views = new ArrayList();

        Button btn = new Button(this);
        Button btn1 =new Button(this);
        LinearLayout.LayoutParams etParas=new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );
        et = new EditText(this);
        g= new graph(this);

        et.setLayoutParams(etParas);
        btn.setLayoutParams(etParas);
        btn.setText("click me");
        ll.addView(et);
        ll.addView(btn);

        //getting letters
        btn.setOnClickListener(e->{
            list = Arrays.asList(et.getText().toString().split(","));
            for (String letter : list) {
               et = new EditText(this);
               views.add(et);
               ll.addView(et);
            }

            btn1.setLayoutParams(etParas);
            btn1.setText("graph");
            ll.addView(btn1);

            //getting values
            btn1.setOnClickListener(v -> {
                 int counter =0;
                for(EditText te : views){
                   // Log.d("KEY",te.getText().toString());

                    values.put(list.get(counter), Float.valueOf(te.getText().toString())/100f);
                    Log.d("KEY", String.valueOf(Float.valueOf(te.getText().toString())/100f));
                    counter++;
                }
                graphChart();
            });

        });


    }


    @RequiresApi(api = Build.VERSION_CODES.N)
    public void graphChart(){
        ll.removeAllViews();
        ll.addView(g);
        g.setVisibility(View.VISIBLE);
        g.getValues(values);
        g.postInvalidate();
    }
}