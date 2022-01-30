package fr.miage.fsgbd;

import java.sql.*;


public class SGBDconnect {


    private String[] listTest = new String[10];

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

       String CreateSql = "Create Table Test(nss bigint ,nom varchar, prenom varchar) ";
        System.out.println("Création table de test !");
        stmt.executeUpdate(CreateSql);

        stmt.close();
    }
    public void addRow(Connection c,long nss,String nom,String prenom) throws SQLException {


            String CreateSql = "INSERT INTO test"+
                " (nss,nom,prenom) VALUES " +
                " (?,?,?);";
        PreparedStatement statement = c.prepareStatement(CreateSql);
        statement.setLong(1, nss);
        statement.setString(2, nom);
        statement.setString(3, prenom);


        int rowsInserted = statement.executeUpdate();
        System.out.println("Ajout d'une ligne ");



}
    public void testSequentielle(Connection c,String[] list) throws SQLException {
        for (int x = 0; x < list.length; x++) {

            String sql = "EXPLAIN (ANALYZE, BUFFERS) Select * from public.test where nss = "+list[x];

            Statement statement = c.createStatement();
            ResultSet result = statement.executeQuery(sql);
            ResultSetMetaData rsmd = result.getMetaData();
            int columnsNumber = rsmd.getColumnCount();
            while (result.next()) {
                for (int i = 1; i <= columnsNumber; i++) {
                    if (i > 1) System.out.print(",  ");
                    String columnValue = result.getString(i);
                    System.out.println(columnValue + " " + rsmd.getColumnName(i));
                }
                ;
            }
        }
};
    public void addListTest(long nss, int n){

        String str = Long.toString(nss);
        this.listTest[n]=str;

    };
    public String[] getListTest(){
        return this.listTest;
    };

    
};

