// models/Client.java
package models;

public class Client extends Person{
    private long id;
    private Service service;

    static long lastId = 0;

    public Client(String name, long phone, String companyName, Service service){
        super(name, phone, companyName);
        lastId++;
        this.id = lastId;
        this.service = service;
    }

    public long getId(){return id;}

    public Service getService(){return service;}
}