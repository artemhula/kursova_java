package collections;

import models.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class RealtorCollection {
    private ArrayList<Realtor> realtors;

    public RealtorCollection() {
        realtors = readFromFile();
    }

    public void addRealtor(Realtor realtor) {
        realtors.add(realtor);
    }

    public static ArrayList<Realtor> readFromFile() {
        ArrayList<Realtor> realtors = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader("realtors.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length == 9) {
                    Address address = new Address(data[4], data[5], data[6], Integer.parseInt(data[7]), Integer.parseInt(data[8]));
                    Realtor realtor = new Realtor(data[1], Long.parseLong(data[2]), data[3], address);
                    realtors.add(realtor);
                } else {
                    System.out.println("Невірний формат запису: " + line);
                }
            }
        } catch (IOException e) {
            System.out.println("Файла запису ріелторів не існує або помилки при читанні");
        }
        return realtors;
    }

    public void writeToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("realtors.txt"))) {
            for (Realtor realtor : realtors) {
                Address address = realtor.getCompanyAddress();
                writer.write(realtor.getId() + "," + realtor.getName() + "," + realtor.getPhone() + "," + realtor.getCompanyName() + "," +
                        address.getCountry() + "," + address.getCity() + "," + address.getStreet() + "," + address.getHouseNumber() + "," + address.getApartmentNumber());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Помилка запису до файлу.");
        }
    }

    public void showRealtors() {
        if (realtors.isEmpty()) {
            System.out.println("Немає ріелторів.");
        }
        for (Realtor realtor : realtors) {
            System.out.println("---------");
            System.out.println("ПІБ: " + realtor.getName());
            System.out.println("Телефон: " + realtor.getPhone());
            System.out.println("Назва компанії: " + realtor.getCompanyName());
            Address companyAdress = realtor.getCompanyAddress();
            System.out.println("Адреса компанії: " + companyAdress.getCountry() + ", " + companyAdress.getCity() + ", " + companyAdress.getStreet() + ", " + companyAdress.getHouseNumber() + ", " + companyAdress.getApartmentNumber());
            System.out.println();
        }
    }

    public void removeRealtor(Scanner scanner) {
        System.out.print("Введіть ПІБ ріелтора для видалення: ");
        String name = scanner.next();
        realtors.removeIf(realtor -> realtor.getName().equals(name));
        System.out.println("Ріелтора видалено, якщо такий існував.");
    }
}
