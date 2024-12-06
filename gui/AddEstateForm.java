// gui/AddEstateForm.java
package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import collections.*;
import models.*;

public class AddEstateForm extends JDialog {
    private JTextField countryField;
    private JTextField cityField;
    private JTextField streetField;
    private JTextField houseNumberField;
    private JTextField apartmentNumberField;
    private JTextField areaField;
    private JTextField levelField;
    private JTextField yearField;
    private JTextField numberOfRoomsField;
    private JTextField priceField;
    private JTextField clientIdField;
    private EstateCollection estateCollection;
    private GUI mainApp;

    public AddEstateForm(JFrame parent, EstateCollection estateCollection, GUI mainApp) {
        super(parent, "Додати об'єкт нерухомості", true);
        this.estateCollection = estateCollection;
        this.mainApp = mainApp;
        setSize(500, 500);
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        countryField = new JTextField(20);
        cityField = new JTextField(20);
        streetField = new JTextField(20);
        houseNumberField = new JTextField(20);
        apartmentNumberField = new JTextField(20);
        areaField = new JTextField(20);
        levelField = new JTextField(20);
        yearField = new JTextField(20);
        numberOfRoomsField = new JTextField(20);
        priceField = new JTextField(20);
        clientIdField = new JTextField(20);

        gbc.gridx = 0;
        gbc.gridy = 0;
        add(new JLabel("Країна:"), gbc);
        gbc.gridx = 1;
        add(countryField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        add(new JLabel("Місто:"), gbc);
        gbc.gridx = 1;
        add(cityField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        add(new JLabel("Вулиця:"), gbc);
        gbc.gridx = 1;
        add(streetField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        add(new JLabel("Номер будинку:"), gbc);
        gbc.gridx = 1;
        add(houseNumberField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        add(new JLabel("Номер квартири:"), gbc);
        gbc.gridx = 1;
        add(apartmentNumberField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 5;
        add(new JLabel("Площа:"), gbc);
        gbc.gridx = 1;
        add(areaField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 6;
        add(new JLabel("Поверх:"), gbc);
        gbc.gridx = 1;
        add(levelField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 7;
        add(new JLabel("Рік побудови:"), gbc);
        gbc.gridx = 1;
        add(yearField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 8;
        add(new JLabel("Кількість кімнат:"), gbc);
        gbc.gridx = 1;
        add(numberOfRoomsField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 9;
        add(new JLabel("Вартість:"), gbc);
        gbc.gridx = 1;
        add(priceField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 10;
        add(new JLabel("ID клієнта:"), gbc);
        gbc.gridx = 1;
        add(clientIdField, gbc);

        JButton submitButton = new JButton("Додати");
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (validateFields()) {
                    Address address = new Address(countryField.getText(), cityField.getText(), streetField.getText(), Integer.parseInt(houseNumberField.getText()), Integer.parseInt(apartmentNumberField.getText()));
                    Estate estate = new Estate(address, Integer.parseInt(areaField.getText()), Integer.parseInt(levelField.getText()), Integer.parseInt(yearField.getText()), Integer.parseInt(numberOfRoomsField.getText()), Long.parseLong(priceField.getText()), Integer.parseInt(clientIdField.getText()));
                    estateCollection.addEstate(estate);
                    estateCollection.writeToFile();
                    mainApp.displayEstates();
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(AddEstateForm.this, "Будь ласка, заповніть всі поля коректно.", "Помилка", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        gbc.gridx = 1;
        gbc.gridy = 11;
        add(submitButton, gbc);
    }

    private boolean validateFields() {
        if (countryField.getText().isEmpty() || cityField.getText().isEmpty() || streetField.getText().isEmpty() ||
                houseNumberField.getText().isEmpty() || apartmentNumberField.getText().isEmpty() || areaField.getText().isEmpty() ||
                levelField.getText().isEmpty() || yearField.getText().isEmpty() || numberOfRoomsField.getText().isEmpty() ||
                priceField.getText().isEmpty() || clientIdField.getText().isEmpty()) {
            return false;
        }
        try {
            int houseNumber = Integer.parseInt(houseNumberField.getText());
            int apartmentNumber = Integer.parseInt(apartmentNumberField.getText());
            int area = Integer.parseInt(areaField.getText());
            int level = Integer.parseInt(levelField.getText());
            int year = Integer.parseInt(yearField.getText());
            int numberOfRooms = Integer.parseInt(numberOfRoomsField.getText());
            long price = Long.parseLong(priceField.getText());
            int clientId = Integer.parseInt(clientIdField.getText());
            if (houseNumber <= 0 || apartmentNumber < 0 || area <= 0 || level <= 0 || year <= 0 || numberOfRooms <= 0 || price <= 0 || clientId <= 0) {
                return false;
            }
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }
}