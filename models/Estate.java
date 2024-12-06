// models/Estate.java
package models;

public class Estate {
    private int id;
    private Address address;
    private int area;
    private int level;
    private int year;
    private int numberOfRooms;
    private long price;
    private int clientId;

    static int lastId = 0;

    public Estate(Address address, int area, int level, int year, int numberOfRooms, long price, int clientId) {
        lastId++;
        this.id = lastId;
        this.address = address;
        this.area = area;
        this.level = level;
        this.year = year;
        this.numberOfRooms = numberOfRooms;
        this.price = price;
        this.clientId = clientId;
    }


    public int getId() {
        return id;
    }

    public Address getAddress() {
        return address;
    }

    public int getArea() {
        return area;
    }

    public int getLevel() {
        return level;
    }

    public int getYear() {
        return year;
    }

    public int getNumberOfRooms() {
        return numberOfRooms;
    }

    public long getPrice() {
        return price;
    }

    public int getClientId(){
        return clientId;
    }

    public void setPrice(long price) {
        this.price = price;
    }
}
