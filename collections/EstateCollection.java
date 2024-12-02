package collections;
import models.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class EstateCollection {
    private ArrayList<Estate> estates;

    public EstateCollection() {
        estates = readFromFile();
    }

    public ArrayList<Estate> readFromFile() {
        ArrayList<Estate> estates = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader("estates.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length == 12) {
                    Address address = new Address(data[1], data[2], data[3], Integer.parseInt(data[4]), Integer.parseInt(data[5]));
                    Estate estate = new Estate(address, Integer.parseInt(data[6]), Integer.parseInt(data[7]), Integer.parseInt(data[8]),
                            Integer.parseInt(data[9]), Long.parseLong(data[10]), Integer.parseInt(data[11]));
                    estates.add(estate);
                } else {
                    System.out.println("Невірний формат запису: " + line);
                }
            }
        } catch (IOException e) {
            System.out.println("Файла запису нерухомості не існує або помилка при читанні");
        }
        return estates;
    }

    public void writeToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("estates.txt"))) {
            for (Estate estate : estates) {
                Address address = estate.getAddress();
                writer.write(estate.getId() + "," + address.getCountry() + "," + address.getCity() + "," + address.getStreet() + "," +
                        address.getHouseNumber() + "," + address.getApartmentNumber() + "," + estate.getArea() + "," + estate.getLevel() + "," +
                        estate.getYear() + "," + estate.getNumberOfRooms() + "," + estate.getPrice() + "," + estate.getClientId());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Помилка запису до файлу.");
        }
    }

    public void addEstate(Estate estate) {
        estates.add(estate);
    }

    public ArrayList<Estate> getEstates() {
        return estates;
    }

    public void showEstates() {
        if (estates.isEmpty()) {
            System.out.println("Немає нерухомості.");
            return;
        }
        for (Estate estate : estates) {
            System.out.println("---------");
            Address address = estate.getAddress();
            System.out.println("ID: " + estate.getId());
            System.out.println("Адреса: " + address.getCountry() + ", " + address.getCity() + ", " + address.getStreet() + ", " + address.getHouseNumber() + ", " + address.getApartmentNumber());
            System.out.println("Площа: " + estate.getArea());
            System.out.println("Поверх: " + estate.getLevel());
            System.out.println("Рік побудови: " + estate.getYear());
            System.out.println("Кількість кімнат: " + estate.getNumberOfRooms());
            System.out.println("Вартість: " + estate.getPrice());
            System.out.println("ID клієнта: " + estate.getClientId());
            System.out.println();
        }
    }


    public Estate findLargestEstate() {
        Estate largest = estates.get(0);
        for (Estate current : estates) {
            if (current.getArea() > largest.getArea()) {
                largest = current;
            }
        }
        return largest;
    }

    public Estate findEstateWithMostRooms() {
        Estate mostRooms = estates.get(0);
        for (Estate estate : estates) {
            if (estate.getNumberOfRooms() > mostRooms.getNumberOfRooms()) {
                mostRooms = estate;
            }
        }
        return mostRooms;
    }

    public double calculateAveragePrice() {
        long totalPrice = 0;
        for (Estate estate : estates) {
            totalPrice += estate.getPrice();
        }
        return (double) totalPrice / estates.size();
    }

    public void removeEstate(Scanner scanner) {
        System.out.print("Введіть ID нерухомості для видалення: ");
        int id = scanner.nextInt();
        estates.removeIf(estate -> estate.getId() == id);
        System.out.println("Нерухомість видалено, якщо така існувала.");
    }

    public void changeEstatePrice(Scanner scanner) {
        System.out.print("Введіть ID нерухомості для зміни ціни: ");
        int id = scanner.nextInt();
        for (Estate estate : estates) {
            if (estate.getId() == id) {
                System.out.print("Введіть нову ціну: ");
                long newPrice = scanner.nextLong();
                while (newPrice <= 0) {
                    System.out.println("Ціна повинна бути більше 0.");
                    System.out.print("Введіть нову ціну: ");
                    newPrice = scanner.nextLong();
                }
                estate.setPrice(newPrice);
                System.out.println("Ціну оновлено.");
                return;
            }
        }
        System.out.println("Нерухомість з таким ID не знайдено.");
    }
}
