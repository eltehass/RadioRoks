package com.example.leonid.radioroks;

import android.graphics.Color;
import android.graphics.ColorFilter;
import android.media.AudioManager;
import android.media.Image;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.media.MediaPlayer.OnCompletionListener;
import android.media.MediaPlayer.OnPreparedListener;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.IOException;



public class MainActivity extends AppCompatActivity implements OnPreparedListener,
        OnCompletionListener {

    AudioManager am;
    MediaPlayer mediaPlayer;

    ImageView playBtn;
    ImageView circlePage1,circlePage2,circlePage3,circlePage4,circlePage5;
    public static int TAB_POSITION = 0;
    public static int PLAYING_TAB_POSITION = -1;

    ColorFilter STANDART_COLOR_FILTER;

    final String DATA_STREAM_1 = "http://online-radioroks.tavrmedia.ua/RadioROKS";
    final String DATA_STREAM_2 = "http://online-radioroks2.tavrmedia.ua/RadioROKS_NewRock";
    final String DATA_STREAM_3 = "http://online-radioroks2.tavrmedia.ua/RadioROKS_HardnHeavy";
    final String DATA_STREAM_4 = "http://online-radioroks2.tavrmedia.ua/RadioROKS_Ukr";
    final String DATA_STREAM_5 = "http://online-radioroks2.tavrmedia.ua/RadioROKS_Ballads";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        playBtn = (ImageView) findViewById(R.id.playBtn);

        circlePage1 = (ImageView) findViewById(R.id.circlePage1);
        circlePage2 = (ImageView) findViewById(R.id.circlePage2);
        circlePage3 = (ImageView) findViewById(R.id.circlePage3);
        circlePage4 = (ImageView) findViewById(R.id.circlePage4);
        circlePage5 = (ImageView) findViewById(R.id.circlePage5);

        STANDART_COLOR_FILTER = circlePage1.getColorFilter();
        circlePage1.setColorFilter(Color.CYAN);

        am = (AudioManager) getSystemService(AUDIO_SERVICE);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout);

        tabLayout.addTab(tabLayout.newTab().setText("Tab 1")); //ICON TAB
        tabLayout.addTab(tabLayout.newTab().setText("Tab 2"));  //TEXT TAB
        tabLayout.addTab(tabLayout.newTab().setText("Tab 3"));  //TEXT TAB
        tabLayout.addTab(tabLayout.newTab().setText("Tab 4"));  //TEXT TAB
        tabLayout.addTab(tabLayout.newTab().setText("Tab 5"));  //TEXT TAB

        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        final ViewPager viewPager = (ViewPager) findViewById(R.id.pager);

        final PagerAdapter adapter = new PagerAdapter(getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(adapter);


        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());

                circlePage1.setColorFilter(STANDART_COLOR_FILTER);
                circlePage2.setColorFilter(STANDART_COLOR_FILTER);
                circlePage3.setColorFilter(STANDART_COLOR_FILTER);
                circlePage4.setColorFilter(STANDART_COLOR_FILTER);
                circlePage5.setColorFilter(STANDART_COLOR_FILTER);

                TAB_POSITION = tab.getPosition();


                if (TAB_POSITION == PLAYING_TAB_POSITION && mediaPlayer.isPlaying()) {
                    playBtn.setImageResource(R.drawable.pause);
                } else {
                    playBtn.setImageResource(R.drawable.play);
                }

                switch (tab.getPosition()) {
                    case 0: {
                        circlePage1.setColorFilter(Color.CYAN);
                        break;
                    }
                    case 1: {
                        circlePage2.setColorFilter(Color.CYAN);
                        break;
                    }
                    case 2: {
                        circlePage3.setColorFilter(Color.CYAN);
                        break;
                    }
                    case 3: {
                        circlePage4.setColorFilter(Color.CYAN);
                        break;
                    }
                    case 4: {
                        circlePage5.setColorFilter(Color.CYAN);
                        break;
                    }
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) { }
            @Override
            public void onTabReselected(TabLayout.Tab tab) { }
        });
    }


    public void playOrStopRadioPlaying(View view) {
        if (PLAYING_TAB_POSITION == TAB_POSITION && mediaPlayer.isPlaying()) {
            mediaPlayer.pause();
            playBtn.setImageResource(R.drawable.play);
            return;
        } else if (PLAYING_TAB_POSITION == TAB_POSITION) {
            mediaPlayer.start();
            playBtn.setImageResource(R.drawable.pause);
            return;
        } else {
            releaseMP();
        }
        try {
            switch (TAB_POSITION) {
                case 0: {
                    PLAYING_TAB_POSITION = 0;
                    mediaPlayer = new MediaPlayer();
                    mediaPlayer.setDataSource(DATA_STREAM_1);
                    mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
                    mediaPlayer.setOnPreparedListener(this);
                    mediaPlayer.prepareAsync();
                    break;
                }
                case 1: {
                    PLAYING_TAB_POSITION = 1;
                    mediaPlayer = new MediaPlayer();
                    mediaPlayer.setDataSource(DATA_STREAM_2);
                    mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
                    mediaPlayer.setOnPreparedListener(this);
                    mediaPlayer.prepareAsync();
                    break;
                }
                case 2: {
                    PLAYING_TAB_POSITION = 2;
                    mediaPlayer = new MediaPlayer();
                    mediaPlayer.setDataSource(DATA_STREAM_3);
                    mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
                    mediaPlayer.setOnPreparedListener(this);
                    mediaPlayer.prepareAsync();
                    break;
                }
                case 3: {
                    PLAYING_TAB_POSITION = 3;
                    mediaPlayer = new MediaPlayer();
                    mediaPlayer.setDataSource(DATA_STREAM_4);
                    mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
                    mediaPlayer.setOnPreparedListener(this);
                    mediaPlayer.prepareAsync();
                    break;
                }
                case 4: {
                    PLAYING_TAB_POSITION = 4;
                    mediaPlayer = new MediaPlayer();
                    mediaPlayer.setDataSource(DATA_STREAM_5);
                    mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
                    mediaPlayer.setOnPreparedListener(this);
                    mediaPlayer.prepareAsync();
                    break;
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        if (mediaPlayer == null)
            return;

        playBtn.setImageResource(R.drawable.pause);
        mediaPlayer.setOnCompletionListener(this);
    }

    private void releaseMP() {
        if (mediaPlayer != null) {
            try {
                mediaPlayer.release();
                mediaPlayer = null;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onCompletion(MediaPlayer mediaPlayer) { }

    @Override
    public void onPrepared(MediaPlayer mediaPlayer) {
        mediaPlayer.start();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        releaseMP();
    }
}

