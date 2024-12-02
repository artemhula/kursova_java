package models;

import java.util.Scanner;

import static models.Address.createAdress;

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

    public static Realtor createRealtor(Scanner scanner) {
        System.out.print("Введіть прізвище ріелтора: ");
        String name = scanner.next();
        System.out.print("Введіть ім'я ріелтора: ");
        name += " " + scanner.next();
        System.out.print("Введіть по-батькові ріелтора: ");
        name += " " + scanner.next();
        System.out.print("Введіть номер телефону: ");
        long phone = scanner.nextLong();
        System.out.print("Введіть назву компанії: ");
        String companyName = scanner.next();
        Address companyAdress = createAdress(scanner);
        return new Realtor(name, phone, companyName, companyAdress);
    }

}
