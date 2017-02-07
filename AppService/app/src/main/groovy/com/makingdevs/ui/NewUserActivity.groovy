package com.makingdevs.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle
import android.widget.EditText
import com.makingdevs.transaction.appservice.R
import groovy.transform.CompileStatic
import mehdi.sakout.fancybuttons.FancyButton;
@CompileStatic
public class NewUserActivity extends AppCompatActivity {
    FancyButton fButton_save
    EditText Edit_name
    Integer position

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_user);
        fButton_save = (FancyButton) findViewById(R.id.btn_save)
        Edit_name = (EditText) findViewById(R.id.editName)
        fButton_save.onClickListener = {

        }

    }

}
