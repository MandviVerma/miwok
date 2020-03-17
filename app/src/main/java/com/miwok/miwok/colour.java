package com.miwok.miwok;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class colour extends AppCompatActivity {


    private MediaPlayer mMediaPlayer;

    private AudioManager mAudioManager;


    AudioManager.OnAudioFocusChangeListener mOnAudioFocusChangeListener =
            new AudioManager.OnAudioFocusChangeListener() {

                public void onAudioFocusChange(int focusChange) {
                    if (focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT||

                            focusChange==AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK)
                    {
                        mMediaPlayer.pause();
                        mMediaPlayer.seekTo(0);
                    } else if (focusChange == AudioManager.AUDIOFOCUS_GAIN) {
                        mMediaPlayer.start();

                    } else if (focusChange == AudioManager.AUDIOFOCUS_LOSS) {
                        releaseMediaPlayer();
                    }
                }
            };

    private MediaPlayer.OnCompletionListener mCompletionListener=new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mp) {
            releaseMediaPlayer();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_colour);

setTitle("Colours");


        mAudioManager=(AudioManager)getSystemService(Context.AUDIO_SERVICE);

        final ArrayList<Word> words = new ArrayList<Word>();
        words.add(new Word("Red", "rot", R.drawable.red, R.raw.rot));
        words.add(new Word("Blue", "blau", R.drawable.blue, R.raw.blau));
        words.add(new Word("Green", "grun", R.drawable.gren, R.raw.grun));
        words.add(new Word("Orange", "apfelsine", R.drawable.orange, R.raw.apfelsine));
        words.add(new Word("Yellow", "gelb", R.drawable.yellow, R.raw.gelb));
        words.add(new Word("Pink", "rosa", R.drawable.pink, R.raw.rosa));
        words.add(new Word("Purple", "lila", R.drawable.purple, R.raw.lila));
        words.add(new Word("Violet", "violett", R.drawable.violet, R.raw.violett));
        words.add(new Word("Lime", "limette", R.drawable.lime, R.raw.limette));
        words.add(new Word("Brown", "braun", R.drawable.brown, R.raw.braun));
        words.add(new Word("White",  "WeiB", R.drawable.white, R.raw.weib));
        words.add(new Word("Black", "schwarz", R.drawable.black, R.raw.schwarz));
        words.add(new Word("Navy", "marine", R.drawable.navy, R.raw.marine));


        WordAdapter adapter = new WordAdapter(this, words);

        ListView listView = (ListView) findViewById(R.id.listcolor);
        listView.setAdapter((adapter));


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Word word = words.get(position);

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


                    mMediaPlayer = MediaPlayer.create(colour.this, word.getmAudioResourceId());
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
    protected  void onStop() {
        super.onStop();
        releaseMediaPlayer();
    }


    private void releaseMediaPlayer()
    {
        if(mMediaPlayer!=null)
        {
            mMediaPlayer.release();
            mMediaPlayer=null;

            mAudioManager.abandonAudioFocus(mOnAudioFocusChangeListener);

    }
}
}