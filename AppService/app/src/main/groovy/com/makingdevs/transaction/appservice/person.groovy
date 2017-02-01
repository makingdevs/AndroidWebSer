package com.makingdevs.transaction.appservice

import android.graphics.drawable.Drawable;

/**
 * Created by makingdevs on 01/02/17.
 */

public class person {
    long id
    Drawable photo;
    String Name;
    String Acoount;



    void datos(Drawable photo, String name, String account){

        this.photo = photo
        this.Name = name
        this.Acoount = account

    }
}
