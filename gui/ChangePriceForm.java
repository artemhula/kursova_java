// gui/ChangePriceForm.java
package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import collections.EstateCollection;

public class ChangePriceForm extends JDialog {
    private JTextField idField;
    private JTextField priceField;
    private EstateCollection estateCollection;
    private GUI mainApp;

    public ChangePriceForm(JFrame parent, EstateCollection estateCollection, GUI mainApp, int estateId) {
        super(parent, "Змінити ціну нерухомості", true);
        this.estateCollection = estateCollection;
        this.mainApp = mainApp;
        setSize(400, 200);
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        idField = new JTextField(20);
        idField.setText(String.valueOf(estateId));
        idField.setEditable(false);
        priceField = new JTextField(20);

        gbc.gridx = 0;
        gbc.gridy = 0;
        add(new JLabel("ID нерухомості:"), gbc);
        gbc.gridx = 1;
        add(idField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        add(new JLabel("Нова ціна:"), gbc);
        gbc.gridx = 1;
        add(priceField, gbc);

        JButton submitButton = new JButton("Змінити");
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (validateFields()) {
                    int id = Integer.parseInt(idField.getText());
                    long price = Long.parseLong(priceField.getText());
                    estateCollection.changeEstatePriceById(id, price);
                    estateCollection.writeToFile();
                    mainApp.displayEstates();
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(ChangePriceForm.this, "Будь ласка, заповніть всі поля коректно.", "Помилка", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        gbc.gridx = 1;
        gbc.gridy = 2;
        add(submitButton, gbc);
    }

    private boolean validateFields() {
        if (idField.getText().isEmpty() || priceField.getText().isEmpty()) {
            return false;
        }
        try {
            int id = Integer.parseInt(idField.getText());
            long price = Long.parseLong(priceField.getText());
            if (id <= 0 || price <= 0) {
                return false;
            }
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }
}