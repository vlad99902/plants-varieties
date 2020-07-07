package sample;

import java.io.*;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Cell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.util.StringConverter;
import java.io.FileReader;


public class MainController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableView<Varieties> tableSorts;

    @FXML
    private TableColumn<Varieties, String> tableSortParent;

    @FXML
    private TableColumn<Varieties, String> tableSortName;

    @FXML
    private TableColumn<Varieties, String> tableSortCategories;

    @FXML
    private TableColumn<Varieties, String> tableSortAuthors;

    @FXML
    private TableColumn<Varieties, String> tableSortProductivity;

    @FXML
    private TableColumn<Varieties, String> tableSortCharacters;

    @FXML
    private TableColumn<Varieties, String> tableSortFrost;

    @FXML
    private TableColumn<Varieties, String> tableSortPests;

    @FXML
    private TableColumn<Varieties, String> tableSortFund;

    @FXML
    private TableColumn<Varieties, String> tableSortIllnes;

    @FXML
    private TableView<Varieties> tableParent;

    @FXML
    private TableColumn<Varieties, String> tableParentParent;

    @FXML
    private TableColumn<Varieties, String> tableParentName;

    @FXML
    private TableColumn<Varieties, String> tableParentCategory;

    @FXML
    private TableColumn<Varieties, String> tableParentAuthor;

    @FXML
    private TableColumn<Varieties, String> tableParentProductivity;

    @FXML
    private TableColumn<Varieties, String> tableParentCharacters;

    @FXML
    private TableColumn<Varieties, String> tableParentFrost;

    @FXML
    private TableColumn<Varieties, String> tableParentIllens;

    @FXML
    private TableColumn<Varieties, String> tableParentFund;

    @FXML
    private TableColumn<Varieties, String> tableParentPest;


    @FXML
    private TextField authorTextField;

    @FXML
    private TextField nameTextFiels;

    @FXML
    private ComboBox<String> parentComboBox;

    @FXML
    private Button refreshMainButton1;

    @FXML
    private ComboBox<List> illnesComboBox;

    @FXML
    private ComboBox<List> pestsComboBox;

    @FXML
    private ComboBox<List> fundsComboBox;

    @FXML
    private Button refreshMainWithFilter;

    @FXML
    private Button refreshMainButton;

    @FXML
    private Button exportData;

    @FXML
    private TableView<List> catTable;

    @FXML
    private TableColumn<List, String> catTableCategor;

    @FXML
    private TableColumn<List, String> catTableNum;

    @FXML
    private Button refreshMainButtonCategor;

    /**
     * Table that loads from file
     */

    @FXML
    private TableView<Varieties> tableSorts1;

    @FXML
    private TableColumn<Varieties, String> tableSortParent1;

    @FXML
    private TableColumn<Varieties, String> tableSortName1;

    @FXML
    private TableColumn<Varieties, String> tableSortCategories1;

    @FXML
    private TableColumn<Varieties, String> tableSortAuthors1;

    @FXML
    private TableColumn<Varieties, String> tableSortProductivity1;

    @FXML
    private TableColumn<Varieties, String> tableSortCharacters1;

    @FXML
    private TableColumn<Varieties, String> tableSortFrost1;

    @FXML
    private TableColumn<Varieties, String> tableSortPests1;

    @FXML
    private TableColumn<Varieties, String> tableSortIllnes1;

    @FXML
    private TableColumn<Varieties, String> tableSortFund1;

    @FXML
    private Button putDataToSort1Table;

    @FXML
    private Button addDataToDB;

    /**
     * Method to insert data to db
     * @param event
     */

    @FXML
    void addDataToDbevent(ActionEvent event) {
        DataBaseHandler dataBaseHandler = new DataBaseHandler();
        ObservableList<Varieties> dataToInsert = importFromTxt();

        //alert for sure insert

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Вы уверены?");
        alert.setHeaderText("Записать строки из Таблицы в БД");

        alert.showAndWait();

        if (alert.getResult().equals(ButtonType.OK)) {
            //take every variate from list
            int count = 0;
            for (Varieties varieties : dataToInsert){
                String varCat = varieties.getVarCat();
                String varPests = varieties.getVarPest();
                String varIllnes = varieties.getVarIllnes();
                String varFunds = varieties.getVarFunds();

                //get id by name
                Integer varIdCat = dataBaseHandler.returnIdByName("mydb.categories", "categories_name", "idcategories", varCat);
                Integer varIdPests = dataBaseHandler.returnIdByName("mydb.pests", "pests_name", "idpests", varPests);
                Integer varIdIllnes = dataBaseHandler.returnIdByName("mydb.illnes", "illnes_name", "idillnes", varIllnes);
                Integer varIdFinds = dataBaseHandler.returnIdByName("mydb.breeding_funds", "breeding_funds_name", "idbreeding_funds", varFunds);

                varieties.setVarIdCategories(varIdCat);
                varieties.setVarIdPests(varIdPests);
                varieties.setVarIdIllnes(varIdIllnes);
                varieties.setVarIdFunds(varIdFinds);

                //insert in data base
                dataBaseHandler.insertSortDataFromFile(varieties);
                count++;
            }

            Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
            alert1.setTitle("Успешно!");
            alert1.setHeaderText("Добавлено записей: " + count);

            alert1.showAndWait();
        }

    }

    /**
     * Put data to table from file
     * @param event
     */
    
    @FXML
    void refreshDataToSort1TableEvent(ActionEvent event) {
        ObservableList<Varieties> dataToInsert = FXCollections.observableArrayList();
        dataToInsert = importFromTxt();
        tableSorts1.setItems(dataToInsert);
    }

    /**
     * Refresh categories table
     * @param event
     */

    @FXML
    void refreshMainButtonCategrEvent(ActionEvent event) {
        DataBaseHandler dataBaseHandler = new DataBaseHandler();
        catTable.setItems(dataBaseHandler.returnListFromDb());
    }

    /**
     * Export data to file
     * @param event
     */

    @FXML
    void exportDataEvent(ActionEvent event) throws IOException{
        Varieties varieties = new Varieties();
        ObservableList <Varieties> list = FXCollections.observableArrayList();

        String filePath = "/Users/vlad99902ipad/Desktop/Проектирование курсач/Кожевников/DB/export.txt";
        File file = new File(filePath);

        try {
            FileWriter fw = new FileWriter(file);
            BufferedWriter bw = new BufferedWriter(fw);

            bw.write("В таблице строк: " + tableSorts.getItems().size());
            bw.newLine();
            if (tableSorts.getItems().size() > 0) {
                for (int i = 0; i < tableSorts.getItems().size(); i++) {  //rows
                    varieties = tableSorts.getItems().get(i);

                    bw.write(exportDataFormat(varieties.getVarParent())   + exportDataFormat(varieties.getVarName())
                             + exportDataFormat(varieties.getVarCat())  +
                            exportDataFormat(varieties.getVarAuthor())  + exportDataFormat (varieties.getVarProductivity())
                            + exportDataFormat(varieties.getVarCharacters()) +
                            exportDataFormat(varieties.getVarFrost())
                             + exportDataFormat(varieties.getVarPest()) + exportDataFormat(varieties.getVarIllnes()) +
                            exportDataFormat(varieties.getVarFunds()));
                    bw.newLine();
                    bw.newLine();
                }

                //info alrt if everything okay
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Успешно");
                alert.setHeaderText("Выгрузка данных в формат txt успешно завершена!");

                alert.showAndWait();
            } else {
                //info alrt if everything bad
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Ошибка!");
                alert.setHeaderText("Выгрузка данных в формат txt невозможна!\nВ таблице нет данных!");

                alert.showAndWait();
            }

            bw.close();
            fw.close();

        } catch (IOException ex) {
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
        }


    }

    /**
     * get data from file
     * @return
     */

    public ObservableList<Varieties> importFromTxt (){
        ObservableList<Varieties> dataToInsert = FXCollections.observableArrayList();
        try {
            File myObj = new File("/Users/vlad99902ipad/Desktop/Проектирование курсач/Кожевников/DB/import.txt");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                //what to do with string
                Varieties varieties = new Varieties(getWordsFromString(data).get(0), getWordsFromString(data).get(1),
                        getWordsFromString(data).get(2), getWordsFromString(data).get(3),getWordsFromString(data).get(4),
                        getWordsFromString(data).get(5), getWordsFromString(data).get(6), getWordsFromString(data).get(7),
                        getWordsFromString(data).get(8), getWordsFromString(data).get(9));
                dataToInsert.add(varieties);
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        return dataToInsert;
    }

    /**
     * get words from string
     * @param line
     * @return
     */

    public ObservableList<String> getWordsFromString (String line){
        ObservableList<String> list = FXCollections.observableArrayList();
        String[] words = line.split("\\s"); // Разбиение строки на слова с помощью разграничителя (пробел)
        // Вывод на экран
        for(String subStr:words) {
            list.add(subStr);
        }
        return list;
    }

    /**
     * Void to make table export in txt file
     * @param string
     */

    public String exportDataFormat (String string) {
        while (string.length() < 25){
            string += " ";
        }
        string += "|";
        return string;
    }

    /**
     * refresh with filter
     * @param event
     */

    @FXML
    void refreshMainWithFilter(ActionEvent event) {
        //Получение данных из combobox
        String parent = parentComboBox.getSelectionModel().getSelectedItem();
        Integer illnes = illnesComboBox.getSelectionModel().getSelectedItem().getId();
        Integer pests = pestsComboBox.getSelectionModel().getSelectedItem().getId();
        Integer funds = fundsComboBox.getSelectionModel().getSelectedItem().getId();
        String name = "";
        name = nameTextFiels.getText();
        String author = "";
        author = authorTextField.getText();
        DataBaseHandler dataBaseHandler = new DataBaseHandler();
        tableSorts.setItems(dataBaseHandler.getVarietiesInfoByName(name, author, illnes, pests, funds, parent));

    }

    /**
     * clear all filters
     * @param event
     */

    @FXML
    void refreshFiltersMainButton(ActionEvent event) {
        authorTextField.setText("");
        nameTextFiels.setText("");
        illnesComboBox.getSelectionModel().select(0);
        fundsComboBox.getSelectionModel().select(0);
        pestsComboBox.getSelectionModel().select(0);
        parentComboBox.getSelectionModel().select(0);
    }

    /**
     * Method to refresh main sorts table
     * @param event
     */

    @FXML
    void refreshMainButton(ActionEvent event) {
        DataBaseHandler dataBaseHandler = new DataBaseHandler();
        ObservableList<Varieties> varieties = dataBaseHandler.getVarietiesInfo();
        tableSorts.setItems(varieties);
    }

    /**
     * Method to refresh parents list
     * @param event
     */

    @FXML
    void tableSortRefreshParentSort(MouseEvent event) {
        if (!tableSorts.getItems().isEmpty()) {
            //получение выбранного объекта из таблицы сортов
            Varieties varieties = tableSorts.getSelectionModel().getSelectedItem();
            //получение вывод родителя в таблицу
            DataBaseHandler dataBaseHandler = new DataBaseHandler();
            ObservableList<Varieties> varieties1 =  dataBaseHandler.getVarietiesInfoByName(varieties.getVarParent());
            if (!varieties1.isEmpty()) {
                tableParent.setItems(varieties1);
            } else {
                tableParent.setItems(null);
            }
        }
    }

    @FXML
    void initialize() {
        //cell table sorts and parents
        cellMainTable(tableSortParent, tableSortName, tableSortCategories, tableSortAuthors,
                tableSortProductivity, tableSortCharacters, tableSortFrost, tableSortPests,
                tableSortIllnes, tableSortFund);
        cellMainTable(tableParentParent, tableParentName, tableParentCategory, tableParentAuthor,
                tableParentProductivity, tableParentCharacters, tableParentFrost, tableParentPest,
                tableParentIllens, tableParentFund);
        cellMainTable(tableSortParent1, tableSortName1, tableSortCategories1, tableSortAuthors1,
                tableSortProductivity1, tableSortCharacters1, tableSortFrost1, tableSortPests1,
                tableSortIllnes1, tableSortFund1);

        //set item to combobox
        cellComboBox();
        //set categories table
        cellTableCat();
    }

    /**
     * Put info to combobox
     */

    public void cellComboBox (){
        //illnes
        DataBaseHandler dataBaseHandler = new DataBaseHandler();
        ObservableList<List> list = dataBaseHandler.returnListFromDb("mydb.illnes", "illnes_name", "idillnes");
        illnesComboBox.setItems(FXCollections.observableList(list));
        illnesComboBox.setConverter(converterPublisherOutString);
        illnesComboBox.getSelectionModel().select(0);

        //pests
        ObservableList<List> listPests = dataBaseHandler.returnListFromDb("mydb.pests", "pests_name", "idpests");
        pestsComboBox.setItems(FXCollections.observableList(listPests));
        pestsComboBox.setConverter(converterPublisherOutString);
        pestsComboBox.getSelectionModel().select(0);

        //funds
        ObservableList<List> listFunds = dataBaseHandler.returnListFromDb("mydb.breeding_funds", "breeding_funds_name", "idbreeding_funds");
        fundsComboBox.setItems(FXCollections.observableList(listFunds));
        fundsComboBox.setConverter(converterPublisherOutString);
        fundsComboBox.getSelectionModel().select(0);

        //parents
        ObservableList<String> listParents = dataBaseHandler.returnListFromDb("mydb.varieties", "varieties_parent");
        parentComboBox.setItems(listParents);
        parentComboBox.getSelectionModel().select(0);
    }

    /**
     * void to cell every columns in table sorts
     */

    public void cellMainTable (TableColumn<Varieties, String> column1, TableColumn<Varieties, String> column2,
                               TableColumn<Varieties, String> column3, TableColumn<Varieties, String> column4,
                               TableColumn<Varieties, String> column5, TableColumn<Varieties, String> column6,
                               TableColumn<Varieties, String> column7, TableColumn<Varieties, String> column8,
                               TableColumn<Varieties, String> column9, TableColumn<Varieties, String> column10){

        column1.setCellValueFactory(new PropertyValueFactory<>("varParent"));
        column2.setCellValueFactory(new PropertyValueFactory<>("varName"));
        column3.setCellValueFactory(new PropertyValueFactory<>("varCat"));
        column4.setCellValueFactory(new PropertyValueFactory<>("varAuthor"));
        column5.setCellValueFactory(new PropertyValueFactory<>("varProductivity"));
        column6.setCellValueFactory(new PropertyValueFactory<>("varCharacters"));
        column7.setCellValueFactory(new PropertyValueFactory<>("varFrost"));
        column8.setCellValueFactory(new PropertyValueFactory<>("varPest"));
        column9.setCellValueFactory(new PropertyValueFactory<>("varIllnes"));
        column10.setCellValueFactory(new PropertyValueFactory<>("varFunds"));

    }

    /**
     *Cell table to out categories
     */

    public void cellTableCat (){
        catTableCategor.setCellValueFactory(new PropertyValueFactory<>("name"));
        catTableNum.setCellValueFactory(new PropertyValueFactory<>("id"));
    }

    /**
     * Method to convert objects and put it into combobox
     */

    StringConverter<List> converterPublisherOutString = new StringConverter<List>() {
        @Override
        public String toString(List object) {
            return object.getName();
        }

        @Override
        public List fromString(String string) {
            return null;
        }
    };

}
