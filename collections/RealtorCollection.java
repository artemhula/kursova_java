// collections/RealtorCollection.java

package collections;

import models.*;
import java.io.*;
import java.util.ArrayList;

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

    public ArrayList<Realtor> getRealtors() {return realtors;}

    public void removeRealtorById(long id) {
        realtors.removeIf(realtor -> realtor.getId() == id);
    }
}