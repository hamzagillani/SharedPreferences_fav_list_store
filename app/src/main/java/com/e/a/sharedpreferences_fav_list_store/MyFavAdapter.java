package com.e.a.sharedpreferences_fav_list_store;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
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


public class MyFavAdapter extends RecyclerView.Adapter<ViewHolder> {
    Context context;
    private List<MyModel> listData;
    SharedPreferences sharedPreferences;

    public MyFavAdapter(Context context, List<MyModel> listData) {
        this.context = context;
        this.listData = listData;

        sharedPreferences = context.getSharedPreferences(MainActivity.MyPREFERENCES, Context.MODE_PRIVATE);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.list_row, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        holder.imageView.setImageResource(R.drawable.fav_red);
        holder.tvUserId.setText(listData.get(position).getId());
        holder.tvUserName.setText(listData.get(position).getName());
        holder.tvMobile.setText(listData.get(position).getMobile());

        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);
                alertDialog.setTitle("Remove from Fav Items");
                alertDialog.setMessage("Are You Sure?");

                alertDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.remove(MainActivity.user_id + listData.get(position).getPosition());
                        editor.remove(MainActivity.name + listData.get(position).getPosition());
                        editor.remove(MainActivity.number + listData.get(position).getPosition());
                        editor.remove(MainActivity.position + listData.get(position).getPosition());
                        editor.apply();
                        Log.d("deleted_", MainActivity.name + listData.get(position).getPosition());
                        listData.remove(position);
                        notifyDataSetChanged();
                    }
                });

                alertDialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

                alertDialog.create();
                alertDialog.show();

            }

        });


    }

    @Override
    public int getItemCount() {
        return listData.size();
    }
}

class ViewHolder extends RecyclerView.ViewHolder {
    TextView tvUserId;
    TextView tvUserName;
    TextView tvMobile;
    ImageView imageView;

    public ViewHolder(@NonNull View itemView) {
        super(itemView);

        tvUserId = itemView.findViewById(R.id.user_id_id);
        tvUserName = itemView.findViewById(R.id.name_id);
        tvMobile = itemView.findViewById(R.id.mobile_id);
        imageView = itemView.findViewById(R.id.fav_items_id);

    }
}
