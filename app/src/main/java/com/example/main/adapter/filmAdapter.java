package com.example.main.adapter;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.util.Pair;
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
import com.example.main.activity.film_page;
import com.example.main.activity.films;
import com.example.main.model.film;

import java.util.ArrayList;
import java.util.List;

public class filmAdapter extends RecyclerView.Adapter<filmAdapter.FilmViewHolder> implements Filterable {


    Context context;
    List<film> films;
    List<film> FullFilmList;
    String str1,str2;
    private RecyclerViewClickListener listener;
    public filmAdapter(Context context, List<film> films) {
        this.context = context;
        this.films = films;
        FullFilmList = new ArrayList<>(films);
    }



    @NonNull
    @Override
    public FilmViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View filmItems = LayoutInflater.from(context).inflate(R.layout.films_item, parent , false);
        return new filmAdapter.FilmViewHolder(filmItems);

    }

    @Override
    public void onBindViewHolder(@NonNull FilmViewHolder holder, int position) {
        holder.filmBG.setBackgroundColor(Color.parseColor(films.get(holder.getAdapterPosition()).getColor()));
        int imageId = context.getResources().getIdentifier(films.get(holder.getAdapterPosition()).getImg(),"drawable",context.getPackageName());
        holder.img.setImageResource(imageId);

        holder.TitleName.setText(films.get(position).getTitle());
        holder.date.setText("Дата: " + films.get(position).getDate());
        holder.type.setText(films.get(position).getType());
        holder.Description.setText("Описание доступно при выборе события!");
        holder.place.getText().toString();

        holder.add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (holder.place.getText().toString().isEmpty()){
                    holder.place.setError("Введите ряд и место!");
                } else {
                    Intent intent = new Intent(context, film_page.class);
                    ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(
                            (Activity) context,
                            new Pair<View, String>(holder.img, "filmImage"));

                intent.putExtra("filmBG",Color.parseColor(films.get(holder.getAdapterPosition()).getColor()));
                intent.putExtra("img", imageId);
                intent.putExtra("TitleName",films.get(holder.getAdapterPosition()).getTitle());
                intent.putExtra("date",films.get(holder.getAdapterPosition()).getDate());
                intent.putExtra("type",films.get(holder.getAdapterPosition()).getType());
                intent.putExtra("text",films.get(holder.getAdapterPosition()).getText());
                intent.putExtra("place",holder.place.getText().toString());
                intent.putExtra("id",films.get(holder.getAdapterPosition()).getId());

                context.startActivity(intent, options.toBundle());
            }}
        });



    }

    @Override
    public int getItemCount() {
        return films.size();
    }
    public interface RecyclerViewClickListener{
        void onClick(View v, int position);


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
                filteredlist.addAll(FullFilmList);
            } else{
                String filterPattern = constraint.toString().toLowerCase().trim();

                for(film item: FullFilmList){
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
            films.clear();
            films.addAll((List)filterResults.values);
            notifyDataSetChanged();

        }
    };

    public static class FilmViewHolder extends RecyclerView.ViewHolder{
        LinearLayout filmBG;
        ImageView img;
        TextView TitleName, Description, date, type,place;
        Button add;
        ;
        public FilmViewHolder(@NonNull View itemView) {
            super(itemView);

            filmBG = itemView.findViewById(R.id.filmBg);
            img = itemView.findViewById(R.id.filmImage);
            TitleName = itemView.findViewById(R.id.filmTitle);
            Description = itemView.findViewById(R.id.description);
            date = itemView.findViewById(R.id.date);
            type = itemView.findViewById(R.id.type);
            add = itemView.findViewById(R.id.addToRecycle);
            place = itemView.findViewById(R.id.place1);


        }
    }


}
