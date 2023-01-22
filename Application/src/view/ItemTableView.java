package view;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class ItemTableView {
    private IntegerProperty poid = new SimpleIntegerProperty();
    public IntegerProperty poidProperty() {
        return poid;
    }
    private void setPoid(int poid) {this.poid.set(poid);}
    public int getPoid() { return poid.get();}
    private IntegerProperty id = new SimpleIntegerProperty();
    public IntegerProperty idProperty() {
        return id;
    }
    private void setId(int id) {this.id.set(id);}
    public int getId() { return id.get();}

    private StringProperty icon = new SimpleStringProperty();
    public StringProperty iconProperty() {
        return icon;
    }
    private void setIcon(String icon) {this.icon.set(icon);}
    public String getIcon() { return icon.get();}

    public ItemTableView(int id, String icon, int poid) {
        setPoid(poid);
        setIcon(icon);
        setId(id);
    }
}
