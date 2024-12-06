// collections/EstateCollection.java

package collections;
import models.*;
import java.io.*;
import java.util.ArrayList;

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

    public void addEstate(Estate estate) {estates.add(estate);}

    public ArrayList<Estate> getEstates() {return estates;}

    public void removeEstateById(int id) {
        estates.removeIf(estate -> estate.getId() == id);
    }

    public void changeEstatePriceById(int id, long newPrice) {
        for (Estate estate : estates) {
            if (estate.getId() == id) {
                estate.setPrice(newPrice);
                System.out.println("Ціну оновлено.");
                return;
            }
        }
    }

    public Estate getLargestAreaEstate() {
        Estate largestAreaEstate = null;
        int largestArea = 0;
        for (Estate estate : estates) {
            if (estate.getArea() > largestArea) {
                largestArea = estate.getArea();
                largestAreaEstate = estate;
            }
        }
        return largestAreaEstate;
    }

    public Estate getMostRoomsEstate() {
        Estate mostRoomsEstate = null;
        int mostRooms = 0;
        for (Estate estate : estates) {
            if (estate.getNumberOfRooms() > mostRooms) {
                mostRooms = estate.getNumberOfRooms();
                mostRoomsEstate = estate;
            }
        }
        return mostRoomsEstate;
    }

    public long getAveragePrice() {
        if (estates.isEmpty()) {
            return 0;
        }
        long sum = 0;
        for (Estate estate : estates) {
            sum += estate.getPrice();
        }
        return sum / estates.size();
    }
}