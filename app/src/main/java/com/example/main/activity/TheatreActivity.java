package com.example.main.activity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.main.R;
import com.example.main.adapter.filmAdapter;
import com.example.main.adapter.tAdapter;
import com.example.main.model.film;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class TheatreActivity extends AppCompatActivity {
    RecyclerView tRecycle;
    tAdapter tAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_theatre2);
        String CurrentTime = new SimpleDateFormat("dd-MM"). format(new Date());
        List<film> tList = new ArrayList<>();
        tList.add(new film(1,"lebedy","Лебединое озеро",CurrentTime,"#808080","balet","Все начинается с грустной истории принцессы Одетты. Красивая девушка прогуливалась в старинном парке, когда ее увидел незнакомец. Им оказался злой волшебник Ротбарт. Принцесса очень понравилась ему, и Ротбарт предложил ей стать его супругой. Но Одетта ответила отказом. В гневе Ротбарт наложил на нее заклятие. Теперь Одетта днем превращается в лебедя, а ночью принимает человеческое обличие.\n" +
                "\n" +
                "Источник: Краткое содержание балета Лебединое озеро сюжет за 2 минуты пересказ сюжета"));
        tList.add(new film(2,"shelk","Щелкунчик",CurrentTime,"#34495E","lololo","Повесть-сказка «Щелкунчик и Мышиный король» Гофмана была написана в 1816 году и в том же году опубликована в сборнике «Детские сказки». В основе сюжета сказки для детей лежит противостояние двух вымышленных царств: Мышиного и Кукольного."));
        tList.add(new film(3,"traviata","Травиата", CurrentTime,"#3498DB","opera","Ни одной проходной мелодии, ни одного лишнего слова - музыка этой оперы захватывает с первых звуков и становится любовью на всю жизнь. Каждая её нота пронизана чувством и вдохновением создателя. Страсть и самопожертвование несчастной куртизанки обрели вечную жизнь в гениальных произведениях искусства, одно из которых - «Травиата» Джузеппе Верди. "));
        tList.add(new film(4,"bogema","Богема", CurrentTime,"#BB8FCE","opera","La bohème) — опера в четырёх актах Джакомо Пуччини. Либретто по произведению Анри Мюрже «Сцены из жизни богемы» написали Луиджи Иллика и Джузеппе Джакоза. Премьера оперы состоялась 1 февраля 1896 года в туринском Teatro Regio, дирижировал Артуро Тосканини. Одна из самых исполняемых опер в мире."));
        tList.add(new film(5,"anna","Анна Каренина", CurrentTime,"black","Musicl","«Анна Каренина» — великий роман, известный каждому. Это шедевр мировой классики о взаимоотношениях мужчины и женщины. Драматическая история любви замужней дамы Анны Карениной и блестящего молодого офицера Алексея Вронского разворачивается на фоне блеска и роскоши дворянской жизни второй половины XIX века."));
        Collections.shuffle(tList);
        tRecycler(tList);

    }
    private void tRecycler(List<film> tList) {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this,RecyclerView.HORIZONTAL,false);
        tRecycle = findViewById(R.id.tRecycle);
        tRecycle.setLayoutManager(layoutManager);

        tAdapter = new tAdapter(this, tList);
        tRecycle.setAdapter(tAdapter);
    }
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);

        MenuItem searchItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) searchItem.getActionView();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                tAdapter.getFilter().filter(newText);
                return false;
            }
        });
        return true;


    }
}