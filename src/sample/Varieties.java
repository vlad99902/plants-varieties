package sample;

public class Varieties {

    /**
     * Private variables like in DB
     */

    private Integer varId;
    private String varParent;
    private String varName;
    private String varCat;
    private String varAuthor;
    private String varProductivity;
    private String varCharacters;
    private String varFrost;
    private String varPest;
    private String varIllnes;
    private String varFunds;

    private Integer varIdCategories;
    private Integer varIdPests;
    private Integer varIdIllnes;
    private Integer varIdFunds;

    /**
     * Constructor for insert values to db
     * @param varParent
     * @param varName
     * @param varAuthor
     * @param varProductivity
     * @param varCharacters
     * @param varFrost
     * @param varIdCategories
     * @param varIdPests
     * @param varIdIllnes
     * @param varIdFunds
     */

    public Varieties(String varParent, String varName, String varAuthor,
                     String varProductivity, String varCharacters, String varFrost,
                     Integer varIdCategories, Integer varIdPests,
                     Integer varIdIllnes, Integer varIdFunds) {
        this.varParent = varParent;
        this.varName = varName;
        this.varAuthor = varAuthor;
        this.varProductivity = varProductivity;
        this.varCharacters = varCharacters;
        this.varFrost = varFrost;
        this.varIdCategories = varIdCategories;
        this.varIdPests = varIdPests;
        this.varIdIllnes = varIdIllnes;
        this.varIdFunds = varIdFunds;
    }

    public Varieties() {
    }

    /**
     * Constructor to put every from table in table view
     * @param varParent
     * @param varName
     * @param varCat
     * @param varAuthor
     * @param varProductivity
     * @param varCharacters
     * @param varFrost
     * @param varPest
     * @param varIllnes
     * @param varFunds
     */



    public Varieties(String varParent, String varName,
                     String varCat, String varAuthor,
                     String varProductivity, String varCharacters,
                     String varFrost, String varPest,
                     String varIllnes, String varFunds) {
        this.varParent = varParent;
        this.varName = varName;
        this.varCat = varCat;
        this.varAuthor = varAuthor;
        this.varProductivity = varProductivity;
        this.varCharacters = varCharacters;
        this.varFrost = varFrost;
        this.varPest = varPest;
        this.varIllnes = varIllnes;
        this.varFunds = varFunds;
    }

    /**
     * Getter and setter for every variable
     *
     */

    public Integer getVarId() {
        return varId;
    }

    public void setVarId(Integer varId) {
        this.varId = varId;
    }

    public String getVarParent() {
        return varParent;
    }

    public void setVarParent(String varParent) {
        this.varParent = varParent;
    }

    public String getVarName() {
        return varName;
    }

    public void setVarName(String varName) {
        this.varName = varName;
    }

    public String getVarCat() {
        return varCat;
    }

    public void setVarCat(String varCat) {
        this.varCat = varCat;
    }

    public String getVarAuthor() {
        return varAuthor;
    }

    public void setVarAuthor(String varAuthor) {
        this.varAuthor = varAuthor;
    }

    public String getVarProductivity() {
        return varProductivity;
    }

    public void setVarProductivity(String varProductivity) {
        this.varProductivity = varProductivity;
    }

    public String getVarCharacters() {
        return varCharacters;
    }

    public void setVarCharacters(String varCharacters) {
        this.varCharacters = varCharacters;
    }

    public String getVarFrost() {
        return varFrost;
    }

    public void setVarFrost(String varFrost) {
        this.varFrost = varFrost;
    }

    public String getVarPest() {
        return varPest;
    }

    public void setVarPest(String varPest) {
        this.varPest = varPest;
    }

    public String getVarIllnes() {
        return varIllnes;
    }

    public void setVarIllnes(String varIllnes) {
        this.varIllnes = varIllnes;
    }

    public String getVarFunds() {
        return varFunds;
    }

    public void setVarFunds(String varFunds) {
        this.varFunds = varFunds;
    }

    public Integer getVarIdCategories() {
        return varIdCategories;
    }

    public void setVarIdCategories(Integer varIdCategories) {
        this.varIdCategories = varIdCategories;
    }

    public Integer getVarIdPests() {
        return varIdPests;
    }

    public void setVarIdPests(Integer varIdPests) {
        this.varIdPests = varIdPests;
    }

    public Integer getVarIdIllnes() {
        return varIdIllnes;
    }

    public void setVarIdIllnes(Integer varIdIllnes) {
        this.varIdIllnes = varIdIllnes;
    }

    public Integer getVarIdFunds() {
        return varIdFunds;
    }

    public void setVarIdFunds(Integer varIdFunds) {
        this.varIdFunds = varIdFunds;
    }
}
