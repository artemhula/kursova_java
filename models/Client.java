package models;

import java.util.Scanner;

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

    public void setService(Service service){
        this.service = service;
    }

    public static Client createClient(Scanner scanner) {
        System.out.print("Введіть прізвище клієнта: ");
        String name = scanner.next();
        System.out.print("Введіть ім'я клієнта: ");
        name += " " + scanner.next();
        System.out.print("Введіть по-батькові клієнта: ");
        name += " " + scanner.next();
        System.out.print("Введіть номер телефону: ");
        long phone = scanner.nextLong();
        System.out.print("Введіть назву компанії: ");
        String companyName = scanner.next();
        Service service;
        while (true) {
            System.out.print("Оберіть тип послуги (1 для BUY, 2 для SELL): ");
            int serviceOption = scanner.nextInt();
            if (serviceOption == 1 || serviceOption == 2) {
                service = serviceOption == 1 ? Service.BUY : Service.SELL;
                break;
            } else {
                System.out.println("Невірний вибір, спробуйте ще раз.");
            }
        }
        return new Client(name, phone, companyName, service);
    }
}