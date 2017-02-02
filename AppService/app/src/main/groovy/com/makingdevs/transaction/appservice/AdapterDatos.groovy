package com.makingdevs.transaction.appservice

import android.app.Activity
import android.content.Context
import android.support.v4.view.LayoutInflaterFactory
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter


/**
 * Created by makingdevs on 02/02/17.
 */

public class AdapterDatos extends BaseAdapter {
    protected Activity activity

    protected ArrayList<person> items

    public AdapterDatos(Activity activity, ArrayList<person> items){
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
    View getView(int position, View convertView, ViewGroup parent) {
        if(convertView==null){
            LayoutInflaterFactory Inf = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE)
            v = Inf.inflate(R.layout.item_user, null)
        }

        return null
    }
}
