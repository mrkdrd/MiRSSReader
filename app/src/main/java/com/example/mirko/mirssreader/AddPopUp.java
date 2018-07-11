package com.example.mirko.mirssreader;

import android.app.Activity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

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
        final EditText linktext = (EditText) findViewById(R.id.link_text);
        final EditText nameText = (EditText) findViewById(R.id.name_text);

        Button button= (Button) findViewById(R.id.add_button);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //if(URLUtil.isValidUrl(linktext.getText().toString()))
                //{
                    MainActivity.RSS_link = linktext.getText().toString();
                    Log.d("link text: ", linktext.getText().toString());
                    MainActivity.RSS_Name_List.add(nameText.getText().toString());
                    Log.d("tostring", MainActivity.RSS_Name_List.toString());
                    MainActivity.RSS_Link_List.add(linktext.getText().toString());
                    finish();
                //}
            }
        });
    }
}
