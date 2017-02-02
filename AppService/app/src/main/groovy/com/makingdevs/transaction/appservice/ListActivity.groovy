package com.makingdevs.transaction.appservice

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle
import android.widget.ImageView
import com.getbase.floatingactionbutton.FloatingActionButton;

public class ListActivity extends AppCompatActivity {

    FloatingActionButton fB_delete
    FloatingActionButton fB_new
    FloatingActionButton fB_edit
    FloatingActionButton fB_deposit
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        fB_deposit = (FloatingActionButton) findViewById(R.id.fb_deposit_user)
        fB_new = (FloatingActionButton) findViewById(R.id.fb_new_user)
        fB_edit = (FloatingActionButton) findViewById(R.id.fb_edit_user)

        fB_deposit.onClickListener ={
            Intent Acdeposti = new Intent(this,MainActivity.class)
            startActivity(Acdeposti)
        }
        fB_new.onClickListener = {
            Intent AcUser = new Intent(this,UserActivity.class)
            startActivity(AcUser)
        }
        fB_edit.onClickListener = {
            Intent AcEdit = new Intent(this,UserActivity.class)
            startActivity(AcEdit)
        }


    }
}
