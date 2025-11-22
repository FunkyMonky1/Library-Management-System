package org.Library.gui;

import org.Library.db.MyJDBC;
import org.Library.model.Book;

import javax.swing.*;
import java.awt.*;

public class AusleihenPanel extends JPanel {

    public AusleihenPanel() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBorder(BorderFactory.createEmptyBorder(10,10,10,10));

        JLabel title = new JLabel("Buch ausleihen");
        title.setFont(new Font("Arial", Font.BOLD, 18));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        JTextField emailField = new JTextField(20);
        JTextField buchField = new JTextField(20);
        JButton borrowBtn = new JButton("Ausleihen");
        JLabel feedback = new JLabel(" ");
        feedback.setAlignmentX(Component.CENTER_ALIGNMENT);

        JPanel emailPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        emailPanel.add(new JLabel("Email:"));
        emailPanel.add(emailField);

        JPanel buchPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        buchPanel.add(new JLabel("Buchtitel:"));
        buchPanel.add(buchField);

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(borrowBtn);

        borrowBtn.addActionListener(e -> {
            String email = emailField.getText().trim();
            String titel = buchField.getText().trim();

            if(email.isEmpty() || titel.isEmpty()) {
                feedback.setText("Bitte Email und Buchtitel eingeben.");
                return;
            }

            if(!MyJDBC.validateLogin(email)) {
                feedback.setText("Login fehlgeschlagen!");
                return;
            }

            Book book = new Book(titel);
            if(!MyJDBC.checkBook(book)) {
                feedback.setText("Buch nicht verfügbar!");
                return;
            }

            MyJDBC.BorrowBook(book);
            feedback.setText("Buch ausgeliehen: " + titel);
            buchField.setText("");
        });

        add(title);
        add(Box.createRigidArea(new Dimension(0,10)));
        add(emailPanel);
        add(buchPanel);
        add(buttonPanel);
        add(feedback);
    }
}

