package collections;

import models.*;
import java.io.*;
import java.util.ArrayList;

public class ClientCollection {
    private ArrayList<Client> clients = new ArrayList<>();

    public ClientCollection() {
        clients = readFromFile();
    }



    public ArrayList<Client> readFromFile() {
        ArrayList<Client> clients = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader("clients.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length == 5) {
                    clients.add(new Client(data[1], Long.parseLong(data[2]), data[3], Service.valueOf(data[4])));
                } else {
                    System.out.println("Невірний формат запису в файлі: " + line);
                }
            }
        } catch (IOException e) {
            System.out.println("Файла запису клієнтів не існує або помилка при читанні");
        }
        return clients;
    }

    public void writeToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("clients.txt"))) {
            for (Client client : clients) {
                writer.write(client.getId() + "," + client.getName() + "," + client.getPhone() + "," + client.getCompanyName() + "," + client.getService());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Помилка запису до файлу.");
        }
    }

    public void addClient(Client client) {
        clients.add(client);
    }

    public ArrayList<Client> getClients() {
        return clients;
    }

    public void showClients() {
        if (clients.isEmpty()) {
            System.out.println("Немає клієнтів.");
            return;
        }
        for (Client client : clients) {
            System.out.println("---------");
            System.out.println("ID: " + client.getId());
            System.out.println("ПІБ: " + client.getName());
            System.out.println("Телефон: " + client.getPhone());
            System.out.println("Назва компанії: " + client.getCompanyName());
            System.out.println("Тип послуги: " + (client.getService() == Service.BUY ? "BUY" : "SELL"));
            System.out.println();
        }
    }
}
