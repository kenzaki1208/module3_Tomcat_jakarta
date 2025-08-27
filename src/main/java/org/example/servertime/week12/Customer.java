package org.example.servertime.week12;

public class Customer {
    String name;
    String dob;
    String address;
    String image;

    public  Customer(String name, String dob, String address, String image) {
        this.name = name;
        this.dob = dob;
        this.address = address;
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public String getDob() {
        return dob;
    }

    public String getAddress() {
        return address;
    }

    public String getImage() {
        return image;
    }
}
