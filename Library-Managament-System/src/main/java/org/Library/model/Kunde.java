package org.Library.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Scanner;

public class Kunde {
    
    private final static Logger logger = LoggerFactory.getLogger(Kunde.class);
    private final String name;
    private final String email;
    private final String adresse;

    public Kunde(KundeBuilder builder){
        this.name = builder.name;
        this.email = builder.email;
        this.adresse = builder.adresse;
    }

    public String getAdresse() {
        return adresse;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public void anzeigen() {
        System.out.println("Kunde: " + name );
        
        if (email != null){
            logger.info("Email: {} " ,email);
        }
        if (adresse != null){
            logger.info("Adresse: {}",adresse);
        }
    }
    public static Kunde createKunde(Scanner scanner) {
        System.out.println("Enter name : ");
        String name = scanner.nextLine();
        System.out.println("Enter email : ");
        String email  = scanner.nextLine();
        System.out.println("Enter adresse : ");
        String address = scanner.nextLine();
        return  new KundeBuilder(name)
                .email(email)
                .adresse(address)
                .build();
    }

    
}
