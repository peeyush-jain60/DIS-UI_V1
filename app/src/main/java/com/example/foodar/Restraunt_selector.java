package com.example.foodar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import android.content.res.TypedArray;
import android.os.Bundle;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;

public class Restraunt_selector extends AppCompatActivity {
    private RecyclerView recycle;
    private LinkedList<main_item> list;
    private Adapter_Class adap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restraunt_selector);
        int gridColumnCount=1;
        recycle=findViewById(R.id.recycler);
        recycle.setLayoutManager(new GridLayoutManager(this,gridColumnCount));
        list=new LinkedList<main_item>();
        adap=new Adapter_Class(this,list);
        recycle.setAdapter(adap);
        fillstaticData();


    }
    private void fillstaticData(){
        String[] item_main=getResources().getStringArray(R.array.sports_titles);
        String [] item_info=getResources().getStringArray(R.array.sports_info);
        TypedArray item_image=getResources().obtainTypedArray(R.array.sports_images);
        TypedArray rating_image=getResources().obtainTypedArray(R.array.ratings);
//    list.clear();
        for(int i=0;i<item_image.length();i++){
            list.add(new main_item(item_main[i],item_info[i],item_image.getResourceId(i,0),rating_image.getResourceId(i,0)));
        }
        adap.notifyDataSetChanged();

    }
}