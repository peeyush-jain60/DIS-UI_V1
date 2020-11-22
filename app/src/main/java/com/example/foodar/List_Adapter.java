package com.example.foodar;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class List_Adapter extends ArrayAdapter {
    private String[] DishNames;
    private String[] DishDescription;
    private Integer[] dish_image;
    private Activity context;
    public List_Adapter(Activity cont,String[] dishNames,String[] dish_des,Integer[] image_id){
        super(cont,R.layout.row_item,dishNames);
        this.context=cont;
        this.DishNames=dishNames;
        this.DishDescription=dish_des;
        this.dish_image=image_id;
    }
    @Override
    public View getView(int position,View convertView,ViewGroup parent){
        View row=convertView;
        LayoutInflater inflater=context.getLayoutInflater();
        if(convertView==null)
            row=inflater.inflate(R.layout.row_item,null,true);
        TextView textViewDishName=(TextView)row.findViewById(R.id.textView_dish_name);
        TextView textViiewDishDescript=(TextView)row.findViewById(R.id.textView_dish_descrip);
        ImageView imagedish=(ImageView)row.findViewById(R.id.imageView_dish);
        Button tap=(Button)row.findViewById(R.id.button2);
        tap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(context,ar_show.class);
                i.putExtra("title",textViewDishName.getText());
                context.startActivity(i);
            }
        });
        textViewDishName.setText(DishNames[position]);
        textViiewDishDescript.setText((DishDescription[position]));
        imagedish.setImageResource(dish_image[position]);
        return row;
    }

}
