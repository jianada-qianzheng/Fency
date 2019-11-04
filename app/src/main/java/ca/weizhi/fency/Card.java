//this object is the card show on Main activity screen. Every store has a card. for example, metro has a metro memebership card
package ca.weizhi.fency;

public class Card {
    int id;
    String barcode;

    String storeName;

    public  Card(int id,String storeName,String barcode){
        this.id=id;
        this.barcode=barcode;
        this.storeName=storeName;

    }

    public int getId() {
        return id;
    }

    public String getBarcode() {
        return barcode;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }


}
