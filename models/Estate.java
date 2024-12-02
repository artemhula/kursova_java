package models;

import java.util.Scanner;
import static models.Address.createAdress;

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

    public static Estate createEstate(Scanner scanner) {
        Address estateAdress = createAdress(scanner);
        int area;
        while (true) {
            System.out.print("Введіть площу: ");
            area = scanner.nextInt();
            if (area > 0) {
                break;
            } else {
                System.out.println("Площа не може бути менше 1.");
            }
        }
        System.out.print("Введіть поверх: ");
        int level = scanner.nextInt();
        int year;
        while (true) {
            System.out.print("Введіть рік побудови: ");
            year = scanner.nextInt();
            if (year <= 2024) {
                break;
            } else {
                System.out.println("Рік побудови не може бути більше поточного.");
            }
        }
        int numberOfRooms;
        while (true) {
            System.out.print("Введіть кількість кімнат: ");
            numberOfRooms = scanner.nextInt();
            if (numberOfRooms > 0) {
                break;
            } else {
                System.out.println("Кількість кімнат не може бути менше 1.");
            }
        }
        long price;
        while (true) {
            System.out.print("Введіть вартість: ");
            price = scanner.nextLong();
            if (price > 0) {
                break;
            } else {
                System.out.println("Вартість не може бути менше 1.");
            }
        }
        int clientId;
        while (true) {
            System.out.print("Введіть ID клієнта: ");
            clientId = scanner.nextInt();
            if (Client.lastId >= clientId) {
                break;
            } else {
                System.out.println("Клієнта з таким ID не існує.");
            }
        }
        return new Estate(estateAdress, area, level, year, numberOfRooms, price, clientId);
    }

}
