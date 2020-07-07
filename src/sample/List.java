package sample;

/**
 * Class to get something and put it to ComboBox
 */

public class List {
    private int id;
    private String name;

    public List(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public List(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
