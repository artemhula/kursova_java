// gui/GUI.java
package gui;

import javax.swing.*;
import java.awt.*;

import models.*;
import collections.*;

public class GUI {
    private RealtorCollection realtorCollection;
    private EstateCollection estateCollection;
    private ClientCollection clientCollection;
    private JTable realtorTable;
    private JTable estateTable;
    private JTable clientTable;

    public GUI() {
        realtorCollection = new RealtorCollection();
        estateCollection = new EstateCollection();
        clientCollection = new ClientCollection();
        createAndShowGUI();
    }

    private void createAndShowGUI() {
        JFrame frame = new JFrame("Ріелторська компанія");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1200, 600);

        JTabbedPane tabbedPane = new JTabbedPane();

        JPanel realtorPanel = new JPanel(new BorderLayout());
        JButton addRealtorButton = new JButton("Додати ріелтора");
        addRealtorButton.addActionListener(e -> new AddRealtorForm(frame, realtorCollection, this).setVisible(true));
        JButton deleteRealtorButton = new JButton("Видалити ріелтора");
        deleteRealtorButton.addActionListener(e -> {
            int selectedRow = realtorTable.getSelectedRow();
            if (selectedRow != -1) {
                long id = (long) realtorTable.getValueAt(selectedRow, 0);
                realtorCollection.removeRealtorById(id);
                realtorCollection.writeToFile();
                displayRealtors();
            } else {
                JOptionPane.showMessageDialog(frame, "Будь ласка, виберіть ріелтора для видалення.", "Помилка", JOptionPane.ERROR_MESSAGE);
            }
        });

        JPanel realtorButtonPanel = new JPanel();
        realtorButtonPanel.add(addRealtorButton);
        realtorButtonPanel.add(deleteRealtorButton);
        realtorPanel.add(realtorButtonPanel, BorderLayout.NORTH);
        realtorTable = new JTable();
        realtorPanel.add(new JScrollPane(realtorTable), BorderLayout.CENTER);
        tabbedPane.addTab("Ріелтори", realtorPanel);

        JPanel estatePanel = new JPanel(new BorderLayout());
        JButton addEstateButton = new JButton("Додати нерухомість");
        addEstateButton.addActionListener(e -> new AddEstateForm(frame, estateCollection, this).setVisible(true));
        JButton deleteEstateButton = new JButton("Видалити нерухомість");
        deleteEstateButton.addActionListener(e -> {
            int selectedRow = estateTable.getSelectedRow();
            if (selectedRow != -1) {
                int id = (int) estateTable.getValueAt(selectedRow, 0);
                estateCollection.removeEstateById(id);
                estateCollection.writeToFile();
                displayEstates();
            } else {
                JOptionPane.showMessageDialog(frame, "Будь ласка, виберіть нерухомість для видалення.", "Помилка", JOptionPane.ERROR_MESSAGE);
            }
        });
        JButton changePriceButton = new JButton("Змінити ціну");
        changePriceButton.addActionListener(e -> {
            int selectedRow = estateTable.getSelectedRow();
            if (selectedRow == -1) {
                JOptionPane.showMessageDialog(frame, "Будь ласка, виберіть нерухомість для зміни ціни.", "Помилка", JOptionPane.ERROR_MESSAGE);
            } else {
                int estateId = (int) estateTable.getValueAt(selectedRow, 0); // Assuming the ID is in the first column
                new ChangePriceForm(frame, estateCollection, this, estateId).setVisible(true);
            }
        });
        JButton showLargestAreaButton = new JButton("Показати найбільшу площу");
        showLargestAreaButton.addActionListener(e -> showLargestAreaEstate());
        JButton showMostRoomsButton = new JButton("Показати найбільшу кількість кімнат");
        showMostRoomsButton.addActionListener(e -> showMostRoomsEstate());
        JButton showAveragePriceButton = new JButton("Показати середню вартість");
        showAveragePriceButton.addActionListener(e -> showAveragePrice());

        JPanel estateButtonPanel = new JPanel();
        estateButtonPanel.add(addEstateButton);
        estateButtonPanel.add(deleteEstateButton);
        estateButtonPanel.add(changePriceButton);
        estateButtonPanel.add(showLargestAreaButton);
        estateButtonPanel.add(showMostRoomsButton);
        estateButtonPanel.add(showAveragePriceButton);
        estatePanel.add(estateButtonPanel, BorderLayout.NORTH);
        estateTable = new JTable();
        estatePanel.add(new JScrollPane(estateTable), BorderLayout.CENTER);
        tabbedPane.addTab("Нерухомість", estatePanel);

        JPanel clientPanel = new JPanel(new BorderLayout());
        JButton addClientButton = new JButton("Додати клієнта");
        addClientButton.addActionListener(e -> new AddClientForm(frame, clientCollection, this).setVisible(true));
        JButton deleteClientButton = new JButton("Видалити клієнта");
        deleteClientButton.addActionListener(e -> {
            int selectedRow = clientTable.getSelectedRow();
            if (selectedRow != -1) {
                long id = (long) clientTable.getValueAt(selectedRow, 0);
                clientCollection.removeClientById(id);
                clientCollection.writeToFile();
                displayClients();
            } else {
                JOptionPane.showMessageDialog(frame, "Будь ласка, виберіть клієнта для видалення.", "Помилка", JOptionPane.ERROR_MESSAGE);
            }
        });
        JPanel clientButtonPanel = new JPanel();
        clientButtonPanel.add(addClientButton);
        clientButtonPanel.add(deleteClientButton);
        clientPanel.add(clientButtonPanel, BorderLayout.NORTH);
        clientTable = new JTable();
        clientPanel.add(new JScrollPane(clientTable), BorderLayout.CENTER);
        tabbedPane.addTab("Клієнти", clientPanel);

        frame.add(tabbedPane);
        frame.setVisible(true);

        displayRealtors();
        displayEstates();
        displayClients();
    }

    private void showLargestAreaEstate() {
        Estate largestAreaEstate = estateCollection.getLargestAreaEstate();
        if (largestAreaEstate != null) {
            JOptionPane.showMessageDialog(null,
                    "ID: " + largestAreaEstate.getId() + "\n" +
                            "Адреса: " + largestAreaEstate.getAddress().getCountry() + ", " + largestAreaEstate.getAddress().getCity() + ", " + largestAreaEstate.getAddress().getStreet() + ", " + largestAreaEstate.getAddress().getHouseNumber() + ", " + largestAreaEstate.getAddress().getApartmentNumber() + "\n" +
                            "Площа: " + largestAreaEstate.getArea() + "\n" +
                            "Поверх: " + largestAreaEstate.getLevel() + "\n" +
                            "Рік побудови: " + largestAreaEstate.getYear() + "\n" +
                            "Кількість кімнат: " + largestAreaEstate.getNumberOfRooms() + "\n" +
                            "Вартість: " + largestAreaEstate.getPrice() + "\n" +
                            "ID клієнта: " + largestAreaEstate.getClientId(),
                    "Нерухомість з найбільшою площею", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "Немає даних про нерухомість.", "Інформація", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void showMostRoomsEstate() {
        Estate mostRoomsEstate = estateCollection.getMostRoomsEstate();
        if (mostRoomsEstate != null) {
            JOptionPane.showMessageDialog(null,
                    "ID: " + mostRoomsEstate.getId() + "\n" +
                            "Адреса: " + mostRoomsEstate.getAddress().getCountry() + ", " + mostRoomsEstate.getAddress().getCity() + ", " + mostRoomsEstate.getAddress().getStreet() + ", " + mostRoomsEstate.getAddress().getHouseNumber() + ", " + mostRoomsEstate.getAddress().getApartmentNumber() + "\n" +
                            "Площа: " + mostRoomsEstate.getArea() + "\n" +
                            "Поверх: " + mostRoomsEstate.getLevel() + "\n" +
                            "Рік побудови: " + mostRoomsEstate.getYear() + "\n" +
                            "Кількість кімнат: " + mostRoomsEstate.getNumberOfRooms() + "\n" +
                            "Вартість: " + mostRoomsEstate.getPrice() + "\n" +
                            "ID клієнта: " + mostRoomsEstate.getClientId(),
                    "Нерухомість з найбільшою кількістю кімнат", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "Немає даних про нерухомість.", "Інформація", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public void showAveragePrice() {
        long averagePrice = estateCollection.getAveragePrice();
        if (averagePrice != 0) {
            JOptionPane.showMessageDialog(null, "Середня вартість: " + averagePrice, "Середня вартість", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "Нерухомості відсутні", "Помилка", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void displayRealtors() {
        String[] columnNames = {"ID", "ПІБ", "Телефон", "Назва компанії", "Країна", "Місто", "Вулиця", "Номер будинку", "Номер квартири"};
        Object[][] data = new Object[realtorCollection.getRealtors().size()][9];
        int i = 0;
        for (Realtor realtor : realtorCollection.getRealtors()) {
            data[i][0] = realtor.getId();
            data[i][1] = realtor.getName();
            data[i][2] = realtor.getPhone();
            data[i][3] = realtor.getCompanyName();
            data[i][4] = realtor.getCompanyAddress().getCountry();
            data[i][5] = realtor.getCompanyAddress().getCity();
            data[i][6] = realtor.getCompanyAddress().getStreet();
            data[i][7] = realtor.getCompanyAddress().getHouseNumber();
            data[i][8] = realtor.getCompanyAddress().getApartmentNumber();
            i++;
        }
        realtorTable.setModel(new javax.swing.table.DefaultTableModel(data, columnNames));
    }

    public void displayEstates() {
        String[] columnNames = {"ID", "Країна", "Місто", "Вулиця", "Номер будинку", "Номер квартири", "Площа", "Поверх", "Рік побудови", "Кількість кімнат", "Вартість", "ID клієнта"};
        Object[][] data = new Object[estateCollection.getEstates().size()][12];
        int i = 0;
        for (Estate estate : estateCollection.getEstates()) {
            data[i][0] = estate.getId();
            data[i][1] = estate.getAddress().getCountry();
            data[i][2] = estate.getAddress().getCity();
            data[i][3] = estate.getAddress().getStreet();
            data[i][4] = estate.getAddress().getHouseNumber();
            data[i][5] = estate.getAddress().getApartmentNumber();
            data[i][6] = estate.getArea();
            data[i][7] = estate.getLevel();
            data[i][8] = estate.getYear();
            data[i][9] = estate.getNumberOfRooms();
            data[i][10] = estate.getPrice();
            data[i][11] = estate.getClientId();
            i++;
        }
        estateTable.setModel(new javax.swing.table.DefaultTableModel(data, columnNames));
    }

    public void displayClients() {
        String[] columnNames = {"ID", "ПІБ", "Телефон", "Назва компанії", "Тип послуги"};
        Object[][] data = new Object[clientCollection.getClients().size()][5];
        int i = 0;
        for (Client client : clientCollection.getClients()) {
            data[i][0] = client.getId();
            data[i][1] = client.getName();
            data[i][2] = client.getPhone();
            data[i][3] = client.getCompanyName();
            data[i][4] = client.getService() == Service.BUY ? "BUY" : "SELL";
            i++;
        }
        clientTable.setModel(new javax.swing.table.DefaultTableModel(data, columnNames));
    }

}