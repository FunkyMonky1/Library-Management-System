package org.Library.gui;

import org.Library.db.MyJDBC;
import org.Library.model.Kunde;
import org.Library.model.KundeBuilder;

import javax.swing.*;
import java.awt.*;

public class KundePanel extends JPanel {

    public KundePanel() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel title = new JLabel("Kunde registrieren");
        title.setFont(new Font("Arial", Font.BOLD, 18));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        
        JTextField nameField = new JTextField(20);
        JTextField emailField = new JTextField(20);
        JTextField adresseField = new JTextField(20); 

        JButton registerBtn = new JButton("Registrieren");
        JLabel feedback = new JLabel(" ");
        feedback.setAlignmentX(Component.CENTER_ALIGNMENT);

        
        JPanel namePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        namePanel.add(new JLabel("Name:"));
        namePanel.add(nameField);

        JPanel emailPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        emailPanel.add(new JLabel("Email:"));
        emailPanel.add(emailField);

        JPanel adressePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        adressePanel.add(new JLabel("Adresse:")); 
        adressePanel.add(adresseField);

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(registerBtn);

        
        registerBtn.addActionListener(e -> {
            String name = nameField.getText().trim();
            String email = emailField.getText().trim();
            String adresse = adresseField.getText().trim(); 

            if(name.isEmpty() || email.isEmpty() || adresse.isEmpty()) {
                feedback.setText("Bitte Name, Email und Adresse eingeben.");
                return;
            }

            
            Kunde kunde = new KundeBuilder(name)
                    .email(email)
                    .adresse(adresse) 
                    .build();

            boolean erfolgreich = MyJDBC.register(kunde);
            if(erfolgreich) {
                feedback.setText("Kunde registriert: " + name + " (" + email + ")");
                nameField.setText("");
                emailField.setText("");
                adresseField.setText("");
            } else {
                feedback.setText("Email existiert bereits oder Fehler.");
            }
        });
        
        add(title);
        add(Box.createRigidArea(new Dimension(0,10)));
        add(namePanel);
        add(emailPanel);
        add(adressePanel); 
        add(buttonPanel);
        add(feedback);
    }
}
