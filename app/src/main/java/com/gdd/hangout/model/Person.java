package com.gdd.hangout.model;

/**
 * Created by ANANDHAKRISHNAN on 11/29/2015.
 */
import android.os.Parcel;
import android.os.Parcelable;

public class Person implements Parcelable {

    String name = "";
    String phoneNumber = "";
    String street = "";
    String city = "";
    String state = "";
    String country = "";
    String zipCode = "";
    String interest = "";
    int listPosition = 0;

    public Person(String name, String phoneNumber, String street, String city, String state, String country
                        ,String zipCode, String interest) {
        super();
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.street = street;
        this.city = city;
        this.state = state;
        this.country = country;
        this.zipCode = zipCode;
        this.interest = interest;
    }


    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getInterest() {
        return interest;
    }

    public void setInterest(String interest) {
        this.interest = interest;
    }


    public int getListPosition() {
        return listPosition;
    }

    public void setListPosition(int listPosition) {
        this.listPosition = listPosition;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

        dest.writeString(this.name);
        dest.writeString(this.phoneNumber);
        dest.writeString(this.street);
        dest.writeString(this.city);
        dest.writeString(this.state);
        dest.writeString(this.country);
        dest.writeString(this.interest);
        dest.writeString(this.zipCode);
        dest.writeInt(this.listPosition);

    }

    public static final Parcelable.Creator<Person> CREATOR
            = new Parcelable.Creator<Person>() {
        public Person createFromParcel(Parcel in) {
            return new Person(in);
        }

        public Person[] newArray(int size) {
            return new Person[size];
        }
    };

    private Person(Parcel in) {
        this.name = in.readString();
        this.phoneNumber = in.readString();
        this.street = in.readString();
        this.city = in.readString();
        this.state = in.readString();
        this.country = in.readString();
        this.zipCode = in.readString();
        this.listPosition = in.readInt();
    }

}
