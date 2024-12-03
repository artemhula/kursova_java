import java.util.Scanner;
import models.*;
import collections.*;

import static models.Client.createClient;
import static models.Estate.createEstate;
import static models.Realtor.createRealtor;

public class Main {
    public static void printMenu() {
        System.out.println("1. Додати ріелтора");
        System.out.println("2. Додати об'єкт нерухомості");
        System.out.println("3. Додати клієнта");
        System.out.println("4. Показати ріелторів");
        System.out.println("5. Показати адрес продажу");
        System.out.println("6. Показати клієнтів");
        System.out.println("7. Показати нерухомість з найбільшою площею");
        System.out.println("8. Показати нерухомість з найбільшою кількістю кімнат");
        System.out.println("9. Показати середню вартість нерухомості");
        System.out.println("10. Видалити ріелтора");
        System.out.println("11. Видалити нерухомість");
        System.out.println("12. Змінити ціну нерухомості");
        System.out.println("0. Вийти");
        System.out.print("Ваш вибір: ");
    }

    public static void main(String[] args) {
        RealtorCollection realtorCollection = new RealtorCollection();
        EstateCollection estateCollection = new EstateCollection();
        ClientCollection clientCollection = new ClientCollection();

        Scanner scanner = new Scanner(System.in);
        boolean continueProgram = true;

        while (continueProgram) {
            printMenu();
            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    realtorCollection.addRealtor(createRealtor(scanner));
                    break;
                case 2:
                    if (clientCollection.getClients().isEmpty()) {
                        System.out.println("Немає клієнтів, спочатку додайте клієнта.");
                        break;
                    } else {
                        estateCollection.addEstate(createEstate(scanner));
                    }
                    break;
                case 3:
                    clientCollection.addClient(createClient(scanner));
                    break;
                case 4:
                    realtorCollection.showRealtors();
                    break;
                case 5:
                    estateCollection.showEstates();
                    break;
                case 6:
                    clientCollection.showClients();
                    break;
                case 7:
                    if (estateCollection.getEstates().isEmpty()) {
                        System.out.println("Немає нерухомості.");
                    } else {
                        Estate largest = estateCollection.findLargestEstate();
                        System.out.println("Найбільша площа: " + largest.getArea());
                        System.out.println("ID нерухомості: " + largest.getId());
                        System.out.println("Адреса: " + largest.getAddress().getCountry() + ", " + largest.getAddress().getCity() + ", " + largest.getAddress().getStreet() + ", " + largest.getAddress().getHouseNumber() + ", " + largest.getAddress().getApartmentNumber());
                        System.out.println("Вартість: " + largest.getPrice());
                    }
                    break;
                case 8:
                    if (estateCollection.getEstates().isEmpty()) {
                        System.out.println("Немає нерухомості.");
                    } else {
                        Estate mostRooms = estateCollection.findEstateWithMostRooms();
                        System.out.println("Найбільша кількість кімнат: " + mostRooms.getNumberOfRooms());
                        System.out.println("ID нерухомості: " + mostRooms.getId());
                        System.out.println("Адреса: " + mostRooms.getAddress().getCountry() + ", " + mostRooms.getAddress().getCity() + ", " + mostRooms.getAddress().getStreet() + ", " + mostRooms.getAddress().getHouseNumber() + ", " + mostRooms.getAddress().getApartmentNumber());
                        System.out.println("Вартість: " + mostRooms.getPrice());
                    }
                    break;
                case 9:
                    if (estateCollection.getEstates().isEmpty()) {
                        System.out.println("Немає нерухомості.");
                    } else {
                        System.out.println("Середня вартість нерухомості: " + estateCollection.calculateAveragePrice());
                    }
                    break;
                case 10:
                    realtorCollection.removeRealtor(scanner);
                    break;
                case 11:
                    estateCollection.removeEstate(scanner);
                    break;
                case 12:
                    estateCollection.changeEstatePrice(scanner);
                    break;
                case 0:
                    continueProgram = false;
                    break;
                default:
                    System.out.println("Невірний вибір, спробуйте ще раз.");
            }
        }

        realtorCollection.writeToFile();
        estateCollection.writeToFile();
        clientCollection.writeToFile();
    }
}
