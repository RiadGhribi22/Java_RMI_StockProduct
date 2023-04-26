import java.io.Serializable;

public class Details implements Serializable {
    public String id;
    public String name;
    public String description ;
    public String price;
    public int quantity ;

    public Details(String id,String name, String description,String price ,int quantity) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.quantity = quantity;


    }

}
