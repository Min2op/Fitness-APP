package com.example.testing;
import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

class User implements Parcelable {
    String Name;
    private Double Weight;
    private Double Height;
    private Integer Age;
    User(){

    }

    protected User(Parcel in) {
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
    public void setWeight(Double w){
        Weight = w;
    }
    public void setHeight(Double h){
        Height = h;
    }
    public void setAge(Integer i){
        Age = i;
    }
    public String getName(){
        return Name;
    }
    public Double getWeight(){
        return Weight;}
    public Double getHeight(){
        return Height;}
    public Integer getAge(){
        return Age;}

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel parcel, int i) {
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
    }
}
