package com.miwok.miwok;

import android.app.Activity;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.text.BreakIterator;
import java.util.ArrayList;


public  class WordAdapter extends ArrayAdapter<Word>

{

    public WordAdapter(Activity context, ArrayList<Word> words) {
        super(context, 0, words);
    }



        public View getView( int position, View convertView, ViewGroup parent) {
            View listItemView = convertView;
            if (listItemView == null)
            {
                listItemView = LayoutInflater.from(getContext()).inflate(R.layout.activity_list__item, parent, false);
            }
            Word currentWord = getItem(position);

            TextView miwokTextView = (TextView) listItemView.findViewById(R.id.miwoktextview);
            miwokTextView.setText(currentWord.getmMiwokTranslation());//this is for the text such as ein,rot


            TextView defaultTextView = (TextView) listItemView.findViewById(R.id.defaulttextview);
            defaultTextView.setText(currentWord.getmDefaultTranslation());//this for text such as one or red



            ImageView imageView = (ImageView) listItemView.findViewById(R.id.image);
            if(currentWord.hasImage()) {
                imageView.setImageResource(currentWord.getmImageResourceid());//this is for the image in listview

                imageView.setVisibility(View.VISIBLE);
            }
            else
            {
                imageView.setVisibility(View.GONE);//to remove image from phrase
            }


            ImageView playbuttonimage=(ImageView)listItemView.findViewById(R.id.playbutton);



             //we us ethis if else to have diff features in different activity like color bg tect color etc
            LinearLayout linearLayout = (LinearLayout) listItemView.findViewById(R.id.text_container);
            //this is the horizontal linear layout which has blue color in numbers activity and laevander color in colors activity

            if (getItem(0).getmDefaultTranslation().equals("one")) //if u want to make change in any feature of number activity do it in this statement
            {
                linearLayout.setBackgroundColor(Color.parseColor("#1E90FF"));//change color of bg is blue for number activity

                //
                }
            else if (getItem(0).getmDefaultTranslation().equals("Red")) //if u wants to make any change in feature of class do it in this statement
            {
                miwokTextView.setTextColor(Color.parseColor("#000000"));//change text color of miwok word that is rot and other german colours
                defaultTextView.setTextColor(Color.parseColor("#000000"));//change text color of default word that is red other english colours
                linearLayout.setBackgroundColor(Color.parseColor("#E6E6FA"));// i will get lavender color in colours activity ,change the color tht is it was blue in number activity but i want//

            }



            return listItemView;
        }

    }

