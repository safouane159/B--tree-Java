package fr.miage.fsgbd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class SGBDconnect {

    public void initTable() throws SQLException {
        createTable(connectToDatabase());
    }

    public Connection connectToDatabase(){
        Connection connection = null;

        try {
            Class.forName("org.postgresql.Driver");
            connection= DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres","postgres","admin");

            if (connection != null){
                System.out.println("Connection réussis");
            }else{
                System.out.println("Connection na pas réussis");
            }

        }catch (Exception e){
            System.out.println("Erreur de connection !");
        }
        return connection ;
    }
    public void createTable(Connection c) throws SQLException {
        Statement stmt = c.createStatement();

       String CreateSql = "Create Table Test(nss int ,nom varchar, prenom varchar) ";
        System.out.println("Création table de test !");
        stmt.executeUpdate(CreateSql);

        stmt.close();
    }
}
