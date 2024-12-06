// gui/AddRealtorForm.java
package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import collections.*;
import models.*;

public class AddRealtorForm extends JDialog {
    private JTextField nameField;
    private JTextField phoneField;
    private JTextField companyNameField;
    private JTextField countryField;
    private JTextField cityField;
    private JTextField streetField;
    private JTextField houseNumberField;
    private JTextField apartmentNumberField;
    private RealtorCollection realtorCollection;
    private GUI mainApp;

    public AddRealtorForm(JFrame parent, RealtorCollection realtorCollection, GUI mainApp) {
        super(parent, "Додати ріелтора", true);
        this.realtorCollection = realtorCollection;
        this.mainApp = mainApp;
        setSize(400, 400);
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        nameField = new JTextField(20);
        phoneField = new JTextField(20);
        companyNameField = new JTextField(20);
        countryField = new JTextField(20);
        cityField = new JTextField(20);
        streetField = new JTextField(20);
        houseNumberField = new JTextField(20);
        apartmentNumberField = new JTextField(20);

        gbc.gridx = 0;
        gbc.gridy = 0;
        add(new JLabel("ПІБ:"), gbc);
        gbc.gridx = 1;
        add(nameField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        add(new JLabel("Телефон:"), gbc);
        gbc.gridx = 1;
        add(phoneField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        add(new JLabel("Назва компанії:"), gbc);
        gbc.gridx = 1;
        add(companyNameField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        add(new JLabel("Країна:"), gbc);
        gbc.gridx = 1;
        add(countryField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        add(new JLabel("Місто:"), gbc);
        gbc.gridx = 1;
        add(cityField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 5;
        add(new JLabel("Вулиця:"), gbc);
        gbc.gridx = 1;
        add(streetField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 6;
        add(new JLabel("Номер будинку:"), gbc);
        gbc.gridx = 1;
        add(houseNumberField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 7;
        add(new JLabel("Номер квартири:"), gbc);
        gbc.gridx = 1;
        add(apartmentNumberField, gbc);

        JButton submitButton = new JButton("Додати");
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (validateFields()) {
                    Address address = new Address(countryField.getText(), cityField.getText(), streetField.getText(), Integer.parseInt(houseNumberField.getText()), Integer.parseInt(apartmentNumberField.getText()));
                    Realtor realtor = new Realtor(nameField.getText(), Long.parseLong(phoneField.getText()), companyNameField.getText(), address);
                    realtorCollection.addRealtor(realtor);
                    realtorCollection.writeToFile();
                    mainApp.displayRealtors();
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(AddRealtorForm.this, "Будь ласка, заповніть всі поля коректно.", "Помилка", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        gbc.gridx = 1;
        gbc.gridy = 8;
        add(submitButton, gbc);
    }

    private boolean validateFields() {
        if (nameField.getText().isEmpty() || phoneField.getText().isEmpty() || companyNameField.getText().isEmpty() ||
                countryField.getText().isEmpty() || cityField.getText().isEmpty() || streetField.getText().isEmpty() ||
                houseNumberField.getText().isEmpty() || apartmentNumberField.getText().isEmpty()) {
            return false;
        }
        try {
            long phone = Long.parseLong(phoneField.getText());
            int houseNumber = Integer.parseInt(houseNumberField.getText());
            int apartmentNumber = Integer.parseInt(apartmentNumberField.getText());
            if (phone <= 0 || houseNumber <= 0 || apartmentNumber < 0) {
                return false;
            }
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }
}