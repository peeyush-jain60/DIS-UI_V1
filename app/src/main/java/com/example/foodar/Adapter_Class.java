package com.example.foodar;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.LinkedList;

public class Adapter_Class extends RecyclerView.Adapter<Adapter_Class.ViewHolder> {
    private Context con;
    private LinkedList<main_item> list;
    Adapter_Class(Context c,LinkedList<main_item> e){
        this.con=c;
        this.list=e;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(con).inflate(R.layout.list_item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        main_item m=list.get(position);
        holder.bindto(m);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends  RecyclerView.ViewHolder implements View.OnClickListener{
        private TextView heading;
        private TextView subheading;
        private ImageView img;
        private ImageView rati;
        ViewHolder(View itemView){
            super(itemView);
            heading=itemView.findViewById(R.id.title);
            subheading=itemView.findViewById(R.id.subtitle);
            img=itemView.findViewById(R.id.some_image);
            rati=itemView.findViewById(R.id.rating_image);
            itemView.setOnClickListener(this);
        }
        @Override
        public void onClick(View v) {
            main_item m=list.get(getAdapterPosition());
            Intent i=new Intent(con,dish_select.class);
            i.putExtra("title",m.gettitle());
            i.putExtra("image",m.getimgae());
            i.putExtra("rating",m.getRatingResource());
            con.startActivity(i);

        }
        void bindto(main_item m){
            heading.setText(m.gettitle());
            subheading.setText(m.getinfo());
            Glide.with(img).load(m.getimgae()).into(img);
            Glide.with(rati).load(m.getRatingResource()).into(rati);
        }
    }
}