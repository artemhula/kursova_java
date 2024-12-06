// models/Address.java
package models;

public class Address {
    private String country;
    private String city;
    private String street;
    private int houseNumber;
    private int apartmentNumber = 0;

    public Address(String country, String city, String street, int houseNumber, int apartmentNumber) {
        this.country = country;
        this.city = city;
        this.street = street;
        this.houseNumber = houseNumber;
        this.apartmentNumber = apartmentNumber;
    }

    public String getCountry() {
        return country;
    }

    public String getCity() {
        return city;
    }

    public String getStreet() {
        return street;
    }

    public int getHouseNumber() {
        return houseNumber;
    }

    public int getApartmentNumber() {
        return apartmentNumber;
    }
}
