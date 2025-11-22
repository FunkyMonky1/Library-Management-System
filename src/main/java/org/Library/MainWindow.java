package org.Library;

/* Hier der Plan: Library Managment System:
  (1) Kunde registiert sich und wenn er schon existiert dann "sorry geht net" sagen 
  (2) Kunde meldet sich an und darf sich ein Buch ausleihen 
  (3) Buch wird ausgeliehen wenn es da ist sonst geht es nicht 
  (4) benutze MYSQL für eine Datenbank mit Kunden und Bücher
  (5) java FX oder Swing verwenden für das Interactive User Interface benutze CardLayout ist am einfachesten!!!
  (6) das wird viel! :) 
 */


import org.Library.gui.AusleihenPanel;
import org.Library.gui.KundePanel;
import org.Library.gui.RueckgabePanel;

import javax.swing.*;

public class MainWindow extends JFrame {
    
    private JPanel contentPanel;
    
    public MainWindow() {
        setTitle("Library Management System from FunkyMonky1");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800,600);
        setLocationRelativeTo(null);
        
        
        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("Aktionen");
        JMenuItem kundeItem = new JMenuItem("Kunde registrieren");
        JMenuItem ausleihenItem = new JMenuItem("Buch ausleihen");
        JMenuItem rueckgabeItem = new JMenuItem("Buch zurückgeben");
        JMenuItem beendenItem = new JMenuItem("Beenden");

        menu.add(kundeItem);
        menu.add(ausleihenItem);
        menu.add(rueckgabeItem);
        menu.addSeparator();
        menu.add(beendenItem);
        menuBar.add(menu);
        setJMenuBar(menuBar);
        
        
        contentPanel = new JPanel();
        contentPanel.setLayout(new java.awt.BorderLayout());
        add(contentPanel);

        
        kundeItem.addActionListener(e -> showPanel(new KundePanel()));
        ausleihenItem.addActionListener(e -> showPanel(new AusleihenPanel()));
        rueckgabeItem.addActionListener(e -> showPanel(new RueckgabePanel()));
        beendenItem.addActionListener(e -> System.exit(0));

        
        showPanel(new KundePanel());
        

    }
    private void showPanel(JPanel panel) {
        contentPanel.removeAll();
        contentPanel.add(panel, java.awt.BorderLayout.CENTER);
        contentPanel.revalidate();
        contentPanel.repaint();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MainWindow window = new MainWindow();
            window.setVisible(true);
        }); 
    }
    
}