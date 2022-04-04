package com.example.testing;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

class User implements Parcelable {
    private String UID;
    private String Name;
    private Double Weight;
    private Double Height;
    private Integer Age;
    private String Email;
    private Double bmi;

    User(){ }
    protected User(Parcel in) {
        UID = in.readString();
        Name = in.readString();
        if (in.readByte() == 0) {
            Weight = null;
        } else {
            Weight = in.readDouble();
        }
        if (in.readByte() == 0) {
            Height = null;
        } else {
            Height = in.readDouble();
        }
        if (in.readByte() == 0) {
            Age = null;
        } else {
            Age = in.readInt();
        }
        Email = in.readString();
        if (in.readByte() == 0) {
            bmi = null;
        } else {
            bmi = in.readDouble();
        }
    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };

    public void setName(String n){
        Name = n;
    }
    public void setUID(String x){UID = x;}
    public void setWeight(Double w){
        Weight = w;
    }
    public void setHeight(Double h){
        Height = h;
    }
    public void setAge(Integer i){
        Age = i;
    }
    public void setEmail(String n){Email = n;}
    public void setbmi(double k) {
        bmi = k;
    }
    public String getName(){
        return Name;
    }
    public Double getWeight(){ return Weight;}
    public Double getHeight(){ return Height;}
    public Integer getAge(){ return Age;}
    public String getEmail(){ return Email;}
    public Double getBmi() {
        return bmi;
    }
    public String getUID() {
        return UID;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(UID);
        parcel.writeString(Name);
        if (Weight == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeDouble(Weight);
        }
        if (Height == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeDouble(Height);
        }
        if (Age == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(Age);
        }
        parcel.writeString(Email);
        if (bmi == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeDouble(bmi);
        }
    }
}
