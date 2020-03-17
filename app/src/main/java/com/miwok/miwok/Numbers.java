package com.miwok.miwok;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Numbers extends AppCompatActivity {

    private MediaPlayer mMediaPlayer;
    //Audio button
    private AudioManager mAudioManager;

    AudioManager.OnAudioFocusChangeListener mOnAudioFocusChangeListener =
            new AudioManager.OnAudioFocusChangeListener() {

                public void onAudioFocusChange(int focusChange) {
                    if (focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT ||

                            focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK) {
                        mMediaPlayer.pause();
                        mMediaPlayer.seekTo(0);
                    } else if (focusChange == AudioManager.AUDIOFOCUS_GAIN) {
                        mMediaPlayer.start();

                    } else if (focusChange == AudioManager.AUDIOFOCUS_LOSS) {
                        releaseMediaPlayer();
                    }
                }
            };

    private MediaPlayer.OnCompletionListener mCompletionListener = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mp) {
            releaseMediaPlayer();



        }
    };
    //Audiobutton

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_numbers);
        setTitle("Numbers");
//Audiobtn
        mAudioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
//audiobtn
        final ArrayList<Word> words = new ArrayList<Word>();
        words.add(new Word("one", "ein", R.drawable.one, R.raw.ein));
        words.add(new Word("two", "zwei", R.drawable.two, R.raw.zwei));
        words.add(new Word("three", "drei", R.drawable.three, R.raw.drei));
        words.add(new Word("four", "vier", R.drawable.four, R.raw.vier));
        words.add(new Word("five", "funf", R.drawable.five, R.raw.funf));
        words.add(new Word("six", "sechs", R.drawable.six, R.raw.sechs));
        words.add(new Word("seven", "Sieben", R.drawable.seven, R.raw.sieben));
        words.add(new Word("eight", "acht", R.drawable.eight, R.raw.acht));
        words.add(new Word("nine", "neun", R.drawable.nine, R.raw.neun));
        words.add(new Word("ten", "zehn", R.drawable.tenpng, R.raw.zehn));

        WordAdapter adapter = new WordAdapter(this, words);

        ListView listView = (ListView) findViewById(R.id.list);
        listView.setAdapter((adapter));


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Word word = words.get(position);
//audiobutton
                releaseMediaPlayer();


                final View play, pause;
                play = view.findViewById(R.id.playbutton);
                pause = view.findViewById(R.id.pausebutton);


                int result = mAudioManager.requestAudioFocus(mOnAudioFocusChangeListener,
                        AudioManager.STREAM_MUSIC,
                        AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);

                if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {

                    play.setVisibility(View.GONE);
                    pause.setVisibility(View.VISIBLE);

                    mMediaPlayer = MediaPlayer.create(Numbers.this, word.getmAudioResourceId());
                    mMediaPlayer.start();
                    mMediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mp) {
                            play.setVisibility(View.VISIBLE);
                            pause.setVisibility(View.GONE);

                            releaseMediaPlayer();

                        }
                    });
                }
            }
        });

    }

    @Override
    protected void onStop() {
        super.onStop();
        releaseMediaPlayer();
    }

    private void releaseMediaPlayer() {
        if (mMediaPlayer != null) {
            mMediaPlayer.release();
            mMediaPlayer = null;


            mAudioManager.abandonAudioFocus(mOnAudioFocusChangeListener);
        }
    }
}
//AudioButton