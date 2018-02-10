package com.example.leonid.radioroks;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by leonid on 08.02.2018.
 */

public class TabFragment1 extends Fragment {

    public TextView songTitle, singerTitle;

    public TabFragment1() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_tab_fragment1, container, false);
        songTitle = view.findViewById(R.id.song_title);
        singerTitle = view.findViewById(R.id.singer_title);

        new ParseTitle().execute();
        return view;
    }

    class ParseTitle extends AsyncTask<Void, Void, ArrayList<String>> {

        @Override
        protected ArrayList<String> doInBackground(Void... voids) {

            HashMap<String, String> hashMap = new HashMap<>();

            String songName = "";
            String singerName = "";

            try {
                Document document = Jsoup.connect("https://www.radioroks.ua/player/").get();

                Element song = document.getElementById("song0");
                Element singer = document.getElementById("singer0");

                Elements name_song = song.getElementsByTag("a");
                Elements name_singer = singer.getElementsByTag("a");

                for (Element el : name_song) { songName += el.text(); }
                for (Element el : name_singer) { singerName += el.text(); }

                if (songName.equals("")) { songName = song.text(); }
                if (singerName.equals("")) { singerName = singer.text(); }
            } catch (IOException e) {
                e.printStackTrace();
            }

            ArrayList<String> list = new ArrayList<>();
            list.add(songName);
            list.add(singerName);

            return list;
        }

        @Override
        protected void onPostExecute(ArrayList<String> strings) {
            super.onPostExecute(strings);
            songTitle.setText(strings.get(0).toString());
            singerTitle.setText(strings.get(1).toString());
        }
    }
}
