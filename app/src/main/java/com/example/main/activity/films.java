package com.example.main.activity;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.main.R;
import com.example.main.adapter.filmAdapter;
import com.example.main.model.film;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class films extends AppCompatActivity{

    RecyclerView filmRecycle;
    filmAdapter filmAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_films);
        String CurrentTime = new SimpleDateFormat("dd-MM"). format(new Date());
        List<film> filmsList = new ArrayList<>();
        filmsList.add(new film(1,"pauk","Человек Паук Нет Пути Домой",CurrentTime,"#c41e3a",
                "Action movie","Жизнь и репутация Питера Паркера оказываются под угрозой, поскольку Мистерио раскрыл всему миру тайну личности Человека-паука. Пытаясь исправить ситуацию, Питер обращается за помощью к Стивену Стрэнджу, но вскоре всё становится намного опаснее."));
        filmsList.add(new film(2,"venom","Веном 2",CurrentTime,"black","Science fiction","Более чем через год после тех событий журналист Эдди Брок пытается приспособиться к жизни в качестве хозяина инопланетного симбиота Венома, который наделяет его сверхчеловеческими способностями. Брок пытается возродить свою карьеру и берет интервью у серийного убийцы Клетуса Касади, который по воле случая становится хозяином Карнажа и сбегает из тюрьмы после неудавшейся казни."));
        filmsList.add(new film(3,"blood","Кровью и Потом", CurrentTime,"#DC7633",
                "Сrime film","Однажды тренеру по фитнесу надоело ходить в трениках. Он решил круто изменить свою судьбу и разбогатеть. Нашел двух других незадачливых качков и предложил им план похищения своего клиента-миллионера. Но если в организме мышц больше, чем мозгов, то даже самый лучший план, подсмотренный в экшен-боевике, может не сработать…"));
        filmsList.add(new film(4,"happy_day_death","Счастливого дня смерти", CurrentTime,"#AB63AB",
                  "Horror movie","Каждый в универе мечтал попасть на её день рождения, но праздник был безнадежно испорчен незнакомцем в маске, убившим виновницу торжества. Однако судьба преподнесла имениннице леденящий душу подарок — бесконечный запас жизней. И теперь у девушки появился шанс вычислить своего убийцу, ведь этот день будет повторяться снова и снова…"));
        filmsList.add(new film(5,"kids_games","Детские игры", CurrentTime,"black","Action movie","Мать-одиночка Карен дарит своему сыну Энди куклу, о которой мечтают все дети. Однако, вскоре становится ясно, что Энди достается больше, чем просто игрушка…"));
        Collections.shuffle(filmsList);
        setfilmRecycler(filmsList);

        }


    private void setfilmRecycler(List<film> filmsList) {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this,RecyclerView.HORIZONTAL,false);
        filmRecycle = findViewById(R.id.filmRecycle);
        filmRecycle.setLayoutManager(layoutManager);

        filmAdapter = new filmAdapter(this,filmsList);
        filmRecycle.setAdapter(filmAdapter);
    }
    @Override
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
                filmAdapter.getFilter().filter(newText);
                return false;
            }
        });
        return true;


}
}