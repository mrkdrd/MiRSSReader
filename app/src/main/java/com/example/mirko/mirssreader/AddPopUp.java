package com.example.mirko.mirssreader;

import android.app.Activity;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.webkit.URLUtil;
import android.widget.Button;
import android.widget.EditText;

import com.example.mirko.mirssreader.Model.ListObject;
import com.google.gson.Gson;

public class AddPopUp extends Activity {

    @Override
    protected  void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.add_popup);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int heigth = dm.heightPixels;

        getWindow().setLayout((int)(width * 0.8), (int)(heigth * 0.8));
        fetchLink();
    }
    private void fetchLink(){
        final EditText linkText = (EditText) findViewById(R.id.link_text);
        final EditText nameText = (EditText) findViewById(R.id.name_text);

        Button button= (Button) findViewById(R.id.add_button);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
               if(URLUtil.isValidUrl(linkText.getText().toString()))
                {
                ListObject object = new ListObject();
                object.link = linkText.getText().toString();
                object.name = nameText.getText().toString();
                MainActivity.Link_List.add(object);

                saveData();
                MainActivity.loadList();
                finish();
                }
                else{
                   linkText.setTextColor(Color.RED);
                   linkText.setText("---Invalid Link---");
               }
            }
        });
    }
    private void saveData(){
        SharedPreferences sharedPrefs = getSharedPreferences("sharedPrefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPrefs.edit();
        Gson gson = new Gson();
        String json = gson.toJson(MainActivity.Link_List);
        editor.putString("Link List", json);
        editor.apply();
    }
}
