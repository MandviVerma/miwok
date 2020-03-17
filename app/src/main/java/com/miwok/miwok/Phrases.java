package com.miwok.miwok;//W

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;//W//

public class Phrases extends AppCompatActivity {//w


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






    //w
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phrases);
        //w
        setTitle("Phrases");

        mAudioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);


        final ArrayList<Word> words = new ArrayList<Word>();
        words.add(new Word("What is your name", "Wie heiBen Sie", R.raw.rot));
        words.add(new Word("My name is", "Ich heiBe", R.raw.blau));
        words.add(new Word("How are you", "Wie geht es Ihnen", R.raw.grun));
        words.add(new Word("I am fine", "ich bin ok", R.raw.apfelsine));
        words.add(new Word("Where do you live", "Wo lebst du", R.raw.gelb));
        words.add(new Word("I live in", "Ich wohne in", R.raw.rosa));
        words.add(new Word("My favourite place is", "Mein Lieblingsplatz ist", R.raw.lila));
        words.add(new Word("My qualifications are", "Qualifikationen sind", R.raw.violett));
        words.add(new Word("What is your age", "Wie alt bist du", R.raw.limette));
        words.add(new Word("I love you", "Ich liebe dich", R.raw.braun));
        words.add(new Word("What would you like to have ", "Was mochten Sie haben", R.raw.weib));
        words.add(new Word("Where do you stay here", "Wo bleibst du hier", R.raw.schwarz));
        words.add(new Word("See you soon", "bis bald", R.raw.marine));


        WordAdapter adapter = new WordAdapter(this, words);

        ListView listView = (ListView) findViewById(R.id.listphrases);
        listView.setAdapter((adapter));


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent,  View view, int position, long id) {
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


                    mMediaPlayer = MediaPlayer.create(Phrases.this, word.getmAudioResourceId());
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
