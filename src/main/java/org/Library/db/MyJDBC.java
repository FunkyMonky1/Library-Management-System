package org.Library.db;
import org.Library.model.*;
import org.Library.model.Kunde;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;

public class MyJDBC {
    
    public static void main(String[] args)  {
        
    }
    private static final Logger logger =  LoggerFactory.getLogger(MyJDBC.class);
    
    private static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(CommonConstants.URL,CommonConstants.USER,CommonConstants.PASSWORD);
    }
        
    public static boolean checkUser(Kunde kunde){
        try{
            Connection connection = getConnection();
            PreparedStatement checkUserExists = connection.prepareStatement("SELECT * FROM " + CommonConstants.DB_KUNDE_TABLE_NAME
            + " WHERE email = ?");
            checkUserExists.setString(1,kunde.getEmail());
            ResultSet resultSet = checkUserExists.executeQuery();
            
            if(!resultSet.isBeforeFirst()){return false;}
            
        } catch(SQLException e){
            logger.error("Fehler beim Checken des Kunden",e);
        }
        
        return true;
    }
    public static boolean register(Kunde kunde){
        try{
            if (!checkUser(kunde)) {
                Connection connection = getConnection();
                
                PreparedStatement insertUser = connection.prepareStatement("INSERT INTO " + CommonConstants.DB_KUNDE_TABLE_NAME +
                        " (name,email,adresse) VALUES (?,?,?)");
                
                insertUser.setString(1,kunde.getName());
                insertUser.setString(2,kunde.getEmail());
                insertUser.setString(3,kunde.getAdresse());
                insertUser.executeUpdate();
                return true;



            }
        } catch(SQLException e){
            
            logger.error("Fehler beim Register des Kunden",e);
            
            
        }
        return false;
    }
    
    public static boolean validateLogin(String email){
        try{
                Connection connection = getConnection();
                PreparedStatement checkUserExists = connection.prepareStatement("SELECT * FROM " + CommonConstants.DB_KUNDE_TABLE_NAME
                        + " WHERE email = ?");
                
                checkUserExists.setString(1,email);
                
                ResultSet resultSet = checkUserExists.executeQuery();

                if(!resultSet.isBeforeFirst()){return false;}

            } catch(SQLException e){
                logger.error("Fehler beim Checken des Kunden",e);
            }

            return true;
        
    }
    
    
    public static boolean checkBook(Book book){
        try{
            Connection connection = getConnection();
            PreparedStatement checkBookExists = connection.prepareStatement("SELECT * FROM " + CommonConstants.DB_BOOKS_TABLE_NAME
                    + " WHERE titel LIKE ? AND available = 1");
            checkBookExists.setString(1,book.title());
            ResultSet resultSet = checkBookExists.executeQuery();

            return resultSet.next();
            
            
        } catch(SQLException e){
            logger.error("Fehler beim Checken des Buches",e);
            return false;
        }
        
    }
    public static void BorrowBook(Book book) {
        String title = book.title().trim(); 
        

        try (Connection connection = getConnection();
             PreparedStatement BorrowBookExists = connection.prepareStatement("UPDATE " + CommonConstants.DB_BOOKS_TABLE_NAME +
                     " SET available = 0 " +
                     "WHERE titel = ? AND available = 1")) {

            BorrowBookExists.setString(1, title);
            int rows = BorrowBookExists.executeUpdate();

            if (rows > 0) {
                System.out.println("Buch erfolgreich ausgeliehen: " + title);
            } else {
                System.out.println("Buch nicht verfügbar oder existiert nicht: " + title);
            }

        } catch (SQLException e) {
            logger.error("Fehler beim Ausleihen des Buches!", e);
        }
    }
    public static boolean ReturnBook(String title) {
        
        try(Connection connection = getConnection();
            PreparedStatement ReturnBookExists = connection.prepareStatement("UPDATE " + CommonConstants.DB_BOOKS_TABLE_NAME +
                    " SET available = 1 " +
                    "WHERE titel = ? AND available = 0")){
            
            ReturnBookExists.setString(1, title);
            int rows = ReturnBookExists.executeUpdate();
            if (rows > 0) {
                System.out.println("Buch erfolgreich abgegeben danke!: " + title);
                return true;
            } else {
                System.out.println("Buch wurde nicht ausgeliehen oder existiert bei uns in der Datenbank nicht!: " + title);
                return false;
            }
            
            
        } catch (SQLException e) {
            logger.error("Das Buch ist noch da oder uns ist ein Fehler unterlaufen",e);
            return false;
            
        }
    }
}
