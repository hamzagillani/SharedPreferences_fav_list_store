package com.e.a.sharedpreferences_fav_list_store;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;


public class MyAdapter extends RecyclerView.Adapter<MyViewHolder> {

    private Context context;
    private List<MyModel> list;
    private SharedPreferences sharedPreferences;

    public MyAdapter(Context context, List<MyModel> list) {
        this.context = context;
        this.list = list;


        sharedPreferences = context.getSharedPreferences(MainActivity.MyPREFERENCES, Context.MODE_PRIVATE);


        for (int i = 0; i < 20; i++) {
            int actualPosition = sharedPreferences.getInt(MainActivity.position + i, -1);
            if (actualPosition != -1) {
                list.get(i).setFav(true);
                Log.d("deleted__", i + "");
            } else {
                list.get(i).setFav(false);
            }

        }


    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.list_row, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int position) {
        holder.tvUserId.setText(list.get(position).getId());
        holder.tvUserName.setText(list.get(position).getName());
        holder.tvMobile.setText(list.get(position).getMobile());

        if (list.get(position).isFav())
            holder.imageView.setImageResource(R.drawable.fav_red);
        else
            holder.imageView.setImageResource(R.drawable.non_fav);


        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SharedPreferences.Editor editor = sharedPreferences.edit();

                if (list.get(position).isFav()) {
                    holder.imageView.setImageResource(R.drawable.non_fav);
                    list.get(position).setFav(false);
                    editor.remove(MainActivity.user_id + position);
                    editor.remove(MainActivity.name + position);
                    editor.remove(MainActivity.number + position);
                    editor.remove(MainActivity.position + position);
                    editor.apply();
                } else {
                    editor.putString(MainActivity.user_id + position, list.get(position).getId());
                    editor.putString(MainActivity.name + position, list.get(position).getName());
                    editor.putString(MainActivity.number + position, list.get(position).getMobile());
                    editor.putInt(MainActivity.position + position, position);
                    editor.apply();
                    holder.imageView.setImageResource(R.drawable.fav_red);
                    list.get(position).setFav(true);


                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}

class MyViewHolder extends RecyclerView.ViewHolder {

    TextView tvUserId;
    TextView tvUserName;
    TextView tvMobile;
    ImageView imageView;


    public MyViewHolder(@NonNull View itemView) {
        super(itemView);
        tvUserId = itemView.findViewById(R.id.user_id_id);
        tvUserName = itemView.findViewById(R.id.name_id);
        tvMobile = itemView.findViewById(R.id.mobile_id);
        imageView = itemView.findViewById(R.id.fav_items_id);

    }
}
