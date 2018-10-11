package com.furkanturkmen.studentportal;

import android.content.Intent;
import android.graphics.Color;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class AddActivity extends AppCompatActivity {

    public static Portal portal;

//    private TextView https_portal_field;
    private EditText url_portal_field;
    private EditText name_portal_field;
    private Button addBtn;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        setTitle("Add portal");
        // getActionBar().setHomeButtonEnabled(true);
        // getActionBar().setDisplayHomeAsUpEnabled(true);

//        https_portal_field = findViewById(R.id.httpsTV);
        url_portal_field = findViewById(R.id.url_field_text);
        name_portal_field = findViewById(R.id.name_field_text);

//        https_portal_field.setKeyListener(null);

        addBtn = findViewById(R.id.btnAdd);

        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent();
                portal = new Portal(name_portal_field.getText().toString(), url_portal_field.getText().toString());

                isValidURL(v, portal);
                System.out.println("SOUT!!! " + portal);
            }
        });
    }

    private void isValidURL(View v, Portal url) {

        if (TextUtils.isEmpty(url.getName()) || url.getName().length() < 4 || !Patterns.WEB_URL.matcher(url.getUrl()).matches()){
            System.out.println("SOUT!!! " + "Not added due incorrect syntax!");

            Snackbar snackbar = Snackbar.make(v, "Check syntax for name or URL!", Snackbar.LENGTH_LONG).setAction("Action", null);
            snackbar.show();

        } else {
                System.out.println("SOUT!!! " + "Added!");
                Snackbar snackbar = Snackbar.make(v, url.getName() + " has been successfully added!", Snackbar.LENGTH_LONG).setAction("Action", null);
                snackbar.show();
            intent.putExtra(MainActivity.EXTRA_PORTAL, portal);
            setResult(RESULT_OK, intent);
            finish();
        }
    }
}
