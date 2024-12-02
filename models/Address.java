package models;

import java.util.Scanner;

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

    public static Address createAdress(Scanner scanner) {
        System.out.print("Введіть країну: ");
        String country = scanner.next();
        System.out.print("Введіть місто: ");
        String city = scanner.next();
        System.out.print("Введіть вулицю: ");
        String street = scanner.next();
        System.out.print("Введіть номер будинку: ");
        int houseNumber = scanner.nextInt();
        while (houseNumber <= 0) {
            System.out.println("Номер будинку повинен бути більше 0.");
            System.out.print("Введіть номер будинку: ");
            houseNumber = scanner.nextInt();
        }
        System.out.print("Введіть номер квартири: ");
        int apartmentNumber = scanner.nextInt();
        while (apartmentNumber < 0) {
            System.out.println("Номер квартири не може бути від'ємним.");
            System.out.print("Введіть номер квартири: ");
            apartmentNumber = scanner.nextInt();
        }
        return new Address(country, city, street, houseNumber, apartmentNumber);
    }
}
