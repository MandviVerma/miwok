package com.miwok.miwok;


import java.util.PropertyResourceBundle;

public class Word {

    private String mDefaultTranslation;

    public Word(String mDefaultTranslation, String mMiwokTranslation, int mAudioResourceId) {
        this.mDefaultTranslation = mDefaultTranslation;
        this.mMiwokTranslation = mMiwokTranslation;
        this.mAudioResourceId = mAudioResourceId;
    }

    private String mMiwokTranslation;
    private int mAudioResourceId;
    //remove image frm phrase
    private int mImageResourceid = NO_IMAGE_PROVIDED;
    private static final int NO_IMAGE_PROVIDED = -1;
    //remove image frm phrase


    public Word(String mDefaultTranslation, String mMiwokTranslation, int mImageResourceid, int audioResourceId) {
        this.mDefaultTranslation = mDefaultTranslation;
        this.mMiwokTranslation = mMiwokTranslation;
        this.mImageResourceid = mImageResourceid;
        this.mAudioResourceId = audioResourceId;


    }




    public String getmDefaultTranslation() {
        return mDefaultTranslation;
    }

    public String getmMiwokTranslation() {
        return mMiwokTranslation;
    }

    public int getmImageResourceid() {
        return mImageResourceid;
    }

    //remove image from phrase
    public boolean hasImage() {
        return mImageResourceid != NO_IMAGE_PROVIDED;
    }
//remove image from phrase


    public int getmAudioResourceId() {
        return mAudioResourceId;
    }


}

