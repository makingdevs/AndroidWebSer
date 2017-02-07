package com.makingdevs.ui

import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.makingdevs.model.Account
import com.makingdevs.transaction.appservice.R
import groovy.transform.CompileStatic


/**
 * Created by makingdevs on 02/02/17.
 */
@CompileStatic
public class AdapterDatos extends BaseAdapter {

    protected Activity activity

    protected ArrayList<Account> items

    public AdapterDatos(Activity activity, ArrayList<Account> items){
        this.activity = activity
        this.items = items
    }
    @Override
    int getCount() {
        return items.size() // Se obtiene el tamaño de la lista
    }

    @Override
    Object getItem(int position) {

        return items.get(position) // Obtenemos la posicion del item seleccionado
    }

    @Override
    long getItemId(int position) {
        return items.get(position).getId() // Obtenemos el ID de la posicion del item seleccionado
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView
        if(convertView==null){
            LayoutInflater Inf = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE)
            v = Inf.inflate(R.layout.item_user, null)
        }
        Account datos = items.get(position) // Se crea un objeto de la clase  Account
        ImageView photo = (ImageView) v.findViewById(R.id.photo_user);
        //photo.setImageDrawable(datos.Photo)
        TextView name = (TextView) v.findViewById(R.id.txt_Name_User);
        //name.setText(datos.Name)
        TextView account = (TextView) v.findViewById(R.id.txt_Name_Account);
        //saccount.setText(datos.Acoount)
        return v
    }
}
