package com.example.main.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.main.R;
import com.example.main.film_page;
import com.example.main.model.film;

import java.util.ArrayList;
import java.util.List;

public class tAdapter extends RecyclerView.Adapter<tAdapter.tViewHolder> implements Filterable {

    Context context;
    List<film> tlist;
    List<film> tFullList;

    public tAdapter(Context context, List<film> tlist) {
        this.context = context;
        this.tlist = tlist;
        tFullList = new ArrayList<>(tlist);
    }

    @NonNull
    @Override
    public tViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View tItems = LayoutInflater.from(context).inflate(R.layout.theatre_item, parent , false);
        return new tAdapter.tViewHolder(tItems);
    }

    @Override
    public void onBindViewHolder(@NonNull tViewHolder holder, int position) {
        holder.filmBG.setBackgroundColor(Color.parseColor(tlist.get(holder.getAdapterPosition()).getColor()));

        int imageId = context.getResources().getIdentifier(tlist.get(holder.getAdapterPosition()).getImg(),"drawable",context.getPackageName());
        holder.img.setImageResource(imageId);

        holder.TitleName.setText(tlist.get(position).getTitle());
        holder.date.setText("Дата: " + tlist.get(position).getDate());
        holder.type.setText(tlist.get(position).getType());
        holder.Description.setText("Описание доступно при выборе события!");
        holder.place.getText().toString();
        holder.add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, film_page.class);


                intent.putExtra("filmBG",Color.parseColor(tlist.get(holder.getAdapterPosition()).getColor()));
                intent.putExtra("img", imageId);
                intent.putExtra("TitleName",tlist.get(holder.getAdapterPosition()).getTitle());
                intent.putExtra("date",tlist.get(holder.getAdapterPosition()).getDate());
                intent.putExtra("type",tlist.get(holder.getAdapterPosition()).getType());
                intent.putExtra("text",tlist.get(holder.getAdapterPosition()).getText());
                intent.putExtra("id",tlist.get(holder.getAdapterPosition()).getId());
                intent.putExtra("place",holder.place.getText().toString());

                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return tlist.size();
    }

    @Override
    public Filter getFilter() {
        return exampleFilter;
    }
    private Filter exampleFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<film> filteredlist = new ArrayList<>();
            if (constraint == null || constraint.length() == 0){
                filteredlist.addAll(tFullList);
            } else{
                String filterPattern = constraint.toString().toLowerCase().trim();

                for(film item: tFullList){
                    if (item.getType().toLowerCase().contains(filterPattern)){
                        filteredlist.add(item);
                    }

                }
            }
            FilterResults results = new FilterResults();
            results.values = filteredlist;

            return results;

        }

        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
            tlist.clear();
            tlist.addAll((List)filterResults.values);
            notifyDataSetChanged();

        }
    };

    public static final class tViewHolder extends RecyclerView.ViewHolder{
        LinearLayout filmBG;
        ImageView img;
        TextView TitleName, Description, date, type,place;
        Button add, next;
        public tViewHolder(@NonNull View itemView) {
            super(itemView);
            filmBG = itemView.findViewById(R.id.tBg);
            img = itemView.findViewById(R.id.tImage);
            TitleName = itemView.findViewById(R.id.tTitle);
            Description = itemView.findViewById(R.id.tdescription);
            date = itemView.findViewById(R.id.tdate);
            type = itemView.findViewById(R.id.ttype);
            add = itemView.findViewById(R.id.taddToRecycle);
            place = itemView.findViewById(R.id.place1);
        }
    }
}