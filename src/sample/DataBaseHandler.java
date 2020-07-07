package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DataBaseHandler extends Config{

    Connection dbConnection;

    /**
     * Get connection with database
     * @return
     * @throws ClassNotFoundException
     * @throws SQLException
     */

    public Connection getDbConnection()
            throws ClassNotFoundException, SQLException {
        String ConnectionString = "jdbc:mysql://" + dbHost + ":"
                + dbPort + "/" + dbName + "?&serverTimezone=UTC&useSSL = false&verifyServerCertificate=false";
        Class.forName("com.mysql.cj.jdbc.Driver");
        dbConnection = DriverManager.getConnection(ConnectionString, dbUser,
                dbPass);
        return dbConnection;
    }

    /**
     * Info to put in varieties table
     * @return
     */

    public ObservableList<Varieties> getVarietiesInfo () {
        ObservableList<Varieties> varieties = FXCollections.observableArrayList();

        try {
            Connection connection = getDbConnection();
            ResultSet resultSet = connection.createStatement().executeQuery("SELECT varieties_name,\n" +
                    "varieties_parent, \n" +
                    "categories_name, varieties_author, varieties_productivity,\n" +
                    "varieties_characters, varieties_frost, pests_name, illnes_name, breeding_funds_name \n" +
                    "FROM mydb.varieties\n" +
                    "JOIN mydb.categories ON categories_idcategories = idcategories\n" +
                    "JOIN mydb.illnes ON illnes_idillnes = idillnes\n" +
                    "JOIN mydb.pests ON pests_idpests = idpests\n" +
                    "JOIN mydb.breeding_funds ON breeding_funds_idbreeding_funds = idbreeding_funds");
            while (resultSet.next()){
                String name = resultSet.getString("varieties_name");
                String parent = resultSet.getString("varieties_parent");
                String categories_name = resultSet.getString("categories_name");
                String varieties_author = resultSet.getString("varieties_author");
                String varieties_productivity = resultSet.getString("varieties_productivity");
                String varieties_characters = resultSet.getString("varieties_characters");
                String varieties_frost = resultSet.getString("varieties_frost");
                String pests_name = resultSet.getString("pests_name");
                String illnes_name = resultSet.getString("illnes_name");
                String breeding_funds_name = resultSet.getString("breeding_funds_name");

                varieties.add(new Varieties(parent, name, categories_name, varieties_author,
                        varieties_productivity, varieties_characters, varieties_frost, pests_name,
                        illnes_name, breeding_funds_name));
            }
        } catch (SQLException ex) {
            System.out.println("error");
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return  varieties;
    }

    /**
     * Return info about parent sort
     * @param parent
     * @return
     */

    public ObservableList<Varieties> getVarietiesInfoByName (String parent) {
        ObservableList<Varieties> varieties = FXCollections.observableArrayList();

        try {
            Connection connection = getDbConnection();
            ResultSet resultSet = connection.createStatement().executeQuery("SELECT \n" +
                    "varieties_parent,\n" +
                    "varieties_name,\n" +
                    "categories_name, varieties_author, varieties_productivity,\n" +
                    "varieties_characters, varieties_frost, pests_name, illnes_name, breeding_funds_name \n" +
                    "FROM mydb.varieties, mydb.categories, mydb.illnes, mydb.pests, mydb.breeding_funds\n" +
                    "\n" +
                    "WHERE categories_idcategories = idcategories AND illnes_idillnes = idillnes AND\n" +
                    "pests_idpests = idpests AND breeding_funds_idbreeding_funds = idbreeding_funds" +
                    " AND varieties_name = '" + parent + "'");
            while (resultSet.next()){
                String name = resultSet.getString("varieties_name");
                String varieties_parent = resultSet.getString("varieties_parent");
                String categories_name = resultSet.getString("categories_name");
                String varieties_author = resultSet.getString("varieties_author");
                String varieties_productivity = resultSet.getString("varieties_productivity");
                String varieties_characters = resultSet.getString("varieties_characters");
                String varieties_frost = resultSet.getString("varieties_frost");
                String pests_name = resultSet.getString("pests_name");
                String illnes_name = resultSet.getString("illnes_name");
                String breeding_funds_name = resultSet.getString("breeding_funds_name");

                varieties.add(new Varieties(varieties_parent, name, categories_name, varieties_author,
                        varieties_productivity, varieties_characters, varieties_frost, pests_name,
                        illnes_name, breeding_funds_name));
            }
        } catch (SQLException ex) {
            System.out.println("error");
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return  varieties;
    }

    /**
     * Return list to put it into combobox
     * @param tableName
     * @param tableColumn
     * @param tableColumnId
     * @return
     */

    public ObservableList<List> returnListFromDb (String tableName, String tableColumn, String tableColumnId) {
        ObservableList<List> data = FXCollections.observableArrayList();
        try {
            DataBaseHandler dbHandler = new DataBaseHandler();
            Connection con = dbHandler.getDbConnection();
            ResultSet result = con.createStatement().executeQuery("SELECT " + tableColumn
                    + ", " + tableColumnId + " FROM "+ tableName);

            while (result.next()) {
                Integer id = result.getInt(tableColumnId);
                String Name = result.getString(tableColumn);
                List publisher = new List(id, Name);
                data.add(publisher);
            }

        } catch (SQLException ex) {
            System.out.println("error");
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return data;

    }

    /**
     * Return list to put it into combobox without ID
     * @param tableName
     * @param tableColumn
     * @return
     */

    public ObservableList<String> returnListFromDb (String tableName, String tableColumn) {
        ObservableList<String> data = FXCollections.observableArrayList();
        try {
            DataBaseHandler dbHandler = new DataBaseHandler();
            Connection con = dbHandler.getDbConnection();
            ResultSet result = con.createStatement().executeQuery("SELECT " + tableColumn
                     + " FROM "+ tableName + " GROUP BY " + tableColumn);

            while (result.next()) {
                String Name = result.getString(tableColumn);
                data.add(Name);
            }

        } catch (SQLException ex) {
            System.out.println("error");
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return data;

    }

    /**
     * Get info from DB with filters
     * @param name
     * @param author
     * @param idillnes
     * @param idpests
     * @param idFunds
     * @param parent
     * @return
     */

    public ObservableList<Varieties> getVarietiesInfoByName (String name, String author, Integer idillnes,
                                                             Integer idpests, Integer idFunds, String parent) {
        ObservableList<Varieties> varieties = FXCollections.observableArrayList();

        try {
            Connection connection = getDbConnection();
            ResultSet resultSet = connection.createStatement().executeQuery("SELECT \n" +
                    "varieties_parent,\n" +
                    "varieties_name,\n" +
                    "categories_name, varieties_author, varieties_productivity,\n" +
                    "varieties_characters, varieties_frost, pests_name, illnes_name, breeding_funds_name \n" +
                    "FROM mydb.varieties, mydb.categories, mydb.illnes, mydb.pests, mydb.breeding_funds\n" +
                    "\n" +
                    "WHERE categories_idcategories = idcategories AND illnes_idillnes = idillnes AND\n" +
                    "pests_idpests = idpests AND breeding_funds_idbreeding_funds = idbreeding_funds" +
                    " AND varieties_name LIKE '%" + name + "%'" + " AND illnes_idillnes = '"+idillnes+"' AND\n" +
                    "pests_idpests = '"+idpests+"' AND breeding_funds_idbreeding_funds = '" + idFunds +
                    "' AND\n" +
                    "varieties_author LIKE '%"+author+"%' AND varieties_parent = '"+ parent+"'");
            while (resultSet.next()){
                String varieties_name = resultSet.getString("varieties_name");
                String varieties_parent = resultSet.getString("varieties_parent");
                String categories_name = resultSet.getString("categories_name");
                String varieties_author = resultSet.getString("varieties_author");
                String varieties_productivity = resultSet.getString("varieties_productivity");
                String varieties_characters = resultSet.getString("varieties_characters");
                String varieties_frost = resultSet.getString("varieties_frost");
                String pests_name = resultSet.getString("pests_name");
                String illnes_name = resultSet.getString("illnes_name");
                String breeding_funds_name = resultSet.getString("breeding_funds_name");

                varieties.add(new Varieties(varieties_parent, varieties_name, categories_name, varieties_author,
                        varieties_productivity, varieties_characters, varieties_frost, pests_name,
                        illnes_name, breeding_funds_name));
            }
        } catch (SQLException ex) {
            System.out.println("error");
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return  varieties;
    }


    /**
     * Return categoryes with num of usage
     *
     * @return
     */

    public ObservableList<List> returnListFromDb () {
        ObservableList<List> data = FXCollections.observableArrayList();
        try {
            DataBaseHandler dbHandler = new DataBaseHandler();
            Connection con = dbHandler.getDbConnection();
            ResultSet result = con.createStatement().executeQuery("SELECT categories_name, " +
                    "COUNT(categories_idcategories) AS count FROM mydb.categories\n" +
                    "left outer join mydb.varieties ON categories.idcategories = varieties.categories_idcategories\n" +
                    "group by categories_name\n" +
                    "order by categories_name");

            while (result.next()) {
                String name = result.getString("categories_name");
                Integer count = result.getInt("count");
                List list = new List(count, name);
                data.add(list);
            }

        } catch (SQLException ex) {
            System.out.println("error");
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return data;

    }

    /**
     * Method to take id ny name
     * @param tableName
     * @param tableColumn
     * @param tableColumnId
     * @param name
     * @return
     */

    public Integer returnIdByName(String tableName, String tableColumn,
                                  String tableColumnId, String name){

        ObservableList<Integer> data = FXCollections.observableArrayList();
        try {
            DataBaseHandler dbHandler = new DataBaseHandler();
            Connection con = dbHandler.getDbConnection();
            ResultSet result = con.createStatement().executeQuery("SELECT " +  tableColumnId + " FROM "+ tableName +
                    " WHERE " + tableColumn + " = '" + name + "'");
            while (result.next()) {
                Integer id_q = result.getInt(tableColumnId);
                data.add(id_q);
            }

        } catch (SQLException ex) {
            System.out.println("error");
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return data.get(0);
    }

    /**
     * Method to add new sort from file
     * @param varieties
     */

    public void insertSortDataFromFile (Varieties varieties) {
        String insert = "INSERT INTO mydb.varieties (varieties_parent, varieties_name, categories_idcategories, varieties_author,\n" +
                "varieties_productivity, varieties_characters, varieties_frost, pests_idpests, illnes_idillnes, breeding_funds_idbreeding_funds)\n" +
                "values (?,?,?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(insert);
            prSt.setString(1, varieties.getVarParent());
            prSt.setString(2,varieties.getVarName());
            prSt.setInt(3,varieties.getVarIdCategories());
            prSt.setString(4,varieties.getVarAuthor());
            prSt.setString(5, varieties.getVarProductivity());
            prSt.setString(6, varieties.getVarCharacters());
            prSt.setString(7, varieties.getVarFrost());
            prSt.setInt(8, varieties.getVarIdPests());
            prSt.setInt(9, varieties.getVarIdIllnes());
            prSt.setInt(10, varieties.getVarIdFunds());
            prSt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
