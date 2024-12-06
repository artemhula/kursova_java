// models/Realtor.java
package models;

public class Realtor extends Person {
    private long id;
    private Address companyAddress;

    private static long lastId = 0;

    public Realtor(String name, long phone, String companyName, Address companyAddress) {
        super(name, phone, companyName);
        lastId++;
        this.id = lastId;
        this.companyAddress = companyAddress;
    }

    public long getId() {return id;}

    public Address getCompanyAddress() {
        return companyAddress;
    }
}
