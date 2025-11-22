package org.Library.gui;

import org.Library.db.MyJDBC;

import javax.swing.*;
import java.awt.*;

public class RueckgabePanel extends JPanel {

    public RueckgabePanel() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBorder(BorderFactory.createEmptyBorder(10,10,10,10));

        JLabel title = new JLabel("Buch zurückgeben");
        title.setFont(new Font("Arial", Font.BOLD, 18));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        JTextField buchField = new JTextField(20);
        JButton returnBtn = new JButton("Zurückgeben");
        JLabel feedback = new JLabel(" ");
        feedback.setAlignmentX(Component.CENTER_ALIGNMENT);

        JPanel buchPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        buchPanel.add(new JLabel("Buchtitel:"));
        buchPanel.add(buchField);

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(returnBtn);

        returnBtn.addActionListener(e -> {
            String titel = buchField.getText().trim();
            if(titel.isEmpty()) {
                feedback.setText("Bitte Buchtitel eingeben.");
                return;
            }

            boolean ok = MyJDBC.ReturnBook(titel);
            feedback.setText(ok ? "Buch zurückgegeben: " + titel : "Dieses Buch wurde nicht ausgeliehen.");
            buchField.setText("");
        });

        add(title);
        add(Box.createRigidArea(new Dimension(0,10)));
        add(buchPanel);
        add(buttonPanel);
        add(feedback);
    }
}
