package com.makingdevs.transaction.appservice

import android.graphics.drawable.Drawable;

/**
 * Created by makingdevs on 01/02/17.
 */

public class Account {
    protected long id
    protected Drawable Photo;
    protected String Name;
    protected String Acoount;




    long getId() {
        return id
    }

    void setId(long id) {
        this.id = id
    }


}
