package com.example.foodar;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ListActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
public class dish_select extends ListActivity {
    private final static String str="INSIDE dish_selector";
    private ListView listview;
    private String dishname[]={
            "Dish A","DishB","DishC","DishD"
    };
    private String dish_desc[]={
      "THis is about disg1","This is about dish2","This is about dish3"
            ,"This is about dish4"
    };
    private Integer imageid[]={
            R.drawable.disha,
            R.drawable.dishb,
            R.drawable.dishc,
            R.drawable.dishd
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dish_select);
        TextView title=findViewById(R.id.title_detail);
        ImageView img=findViewById(R.id.imageView);
        ImageView rati=findViewById(R.id.rating_image);
        title.setText(getIntent().getStringExtra("title"));
        Glide.with(this).load(getIntent().getIntExtra("image",0)).into(img);
        Glide.with(this).load(getIntent().getIntExtra("rating",0)).into(rati);
        ListView listView=(ListView)findViewById(android.R.id.list);
        List_Adapter dish_list=new List_Adapter(this,dishname,dish_desc,imageid);
        listView.setAdapter(dish_list);
        setheight(listView);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.i(str,"IN click funcrtion "+position);
                Toast.makeText(getApplicationContext(),"You Selected "+dishname[position]+ " as Country", Toast.LENGTH_SHORT).show();
            }
        });
    }
    public void setheight(ListView mylistView){
        ListAdapter listadapter=mylistView.getAdapter();
        if(listadapter==null)
            return;
        int tot=0;
        for(int i=0;i<listadapter.getCount();i++){
            View listitem=listadapter.getView(i,null,mylistView);
            listitem.measure(0,0);
            tot+=listitem.getMeasuredHeight();
        }
        ViewGroup.LayoutParams params=mylistView.getLayoutParams();
        params.height=tot+(mylistView.getDividerHeight()*(listadapter.getCount()-1));
        mylistView.setLayoutParams(params);
    }
}