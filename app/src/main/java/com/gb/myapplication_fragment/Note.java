package com.gb.myapplication_fragment;

import android.os.Parcel;
import android.os.Parcelable;

public class Note implements Parcelable {
    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public static Creator<Note> getCREATOR() {
        return CREATOR;
    }

    private int index;


    protected Note(Parcel in) {
        index = in.readInt();
    }

    public static final Creator<Note> CREATOR = new Creator<Note>() {
        @Override
        public Note createFromParcel(Parcel in) {
            return new Note(in);
        }

        @Override
        public Note[] newArray(int size) {
            return new Note[size];
        }
    };

    public Note(int index){
        this.index = index;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(index);
    }
}
