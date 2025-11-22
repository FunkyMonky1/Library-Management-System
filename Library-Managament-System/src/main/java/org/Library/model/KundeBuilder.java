package org.Library.model;

public class KundeBuilder {
    String name;
    String email;
    String adresse;

    public KundeBuilder(String name) {
        this.name = name;
    }
    public KundeBuilder email(String email) {
        if(email == null) {
            throw new NullPointerException("email is null");
        }
        this.email = email;
        return this;
        
    }
    public KundeBuilder adresse(String adresse) {
        if(adresse == null) {
            throw new NullPointerException("adresse is null");
        }
        this.adresse = adresse;
        return this;
    }
    
    public Kunde build() {
        return new Kunde(this);
    }
}
    