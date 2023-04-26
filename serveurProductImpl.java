import java.rmi.*;
import java.rmi.server.*;
import java.util.*;
import java.rmi.registry.LocateRegistry;

public class serveurProductImpl extends UnicastRemoteObject implements serveurProduct {

    private HashMap<String, serveurProduct> listeClients;

    private HashMap<String,String> products;
    private HashMap<String,Details> detail;



    public serveurProductImpl() throws RemoteException {
        super();
        listeClients = new HashMap<>();
        products = new HashMap<>();
        detail = new HashMap<>();


        //-------------------------------
        products.put("1","laptops");
        products.put("2","phones");
        products.put("3","TVs");

        //--------------------------------
        Details detail1 = new Details("1","Macbook","Macbook Pro 2019 ","180 000.00 DA",5);
        detail.put("1",detail1);

        Details detail2 = new Details("2","Iphone","14 pro max ","230 000.00 DA",6);
        detail.put("2",detail2);

        Details detail3 = new Details("3","TV Samsung","smart TV 2021 ","10 000.00 DA",19);
        detail.put("3",detail3);


    }

    public static void main(String[] args) {


        try {
            System.out.println("Server is booting....");

            serveurProductImpl serveur = new serveurProductImpl();
            LocateRegistry.createRegistry(1099);
            String url = "rmi://localhost:1099/serveurProduct"; // replace localhost with your IP address and 1099 with your desired port
            Naming.rebind(url, serveur);
            System.out.println("Serveur prêt.");
        } catch (Exception e) {
            System.out.println("Erreur : " + e.getMessage());
        }
    }


    public void register(String name, serveurProduct callback) throws RemoteException {
        listeClients.put(name, callback);
        System.out.println(name + " vient de se connecter.");
    }

    public void unregister(String name) throws RemoteException {
        listeClients.remove(name);
        System.out.println(name + " vient de se déconnecter.");
    }

    public void sendData(String message) throws RemoteException {
        System.out.println("Le serveur envoie le message suivant : " + message);
        for (serveurProduct callback : listeClients.values()) {
            callback.notify(message);
        }
    }

    public void notify(String message) throws RemoteException {
        System.out.println("Le message du client est : " + message);
    }


public HashMap<String, String> getAllData() throws RemoteException {
    return products;
}

public Details InfoAboutProduct(String key_product) throws RemoteException {
    if (detail.containsKey(key_product)) {

        return detail.get(key_product);

    }
    else{
        Details error = new Details("no products found!!!!!! ","no products found!!!!!! ","no products found!!!!!! ","no products found!!!!!!",0);
  
        return error ;
    }




}





public void Delete_product(String key) throws RemoteException {
    products.remove(key);
    detail.remove(key);

}

public void Update_product_quantity(String key,int quant) throws RemoteException{
    Details update1 =  detail.get(key);
    update1.quantity = quant;
    detail.put(key, update1);
}

 
public boolean check_catgorie_exist(String cat) throws RemoteException{
    if(products.containsValue(cat)){
        System.out.println("Information existe ! " );
        return false ;

    }
    else{
        System.out.println("accept the product  " );
        return true;
    }
}


public void add_product(String name, String cat ,String Des ,String price,int quant) throws RemoteException{

    int key = products.size();
    key = key+1;
    String k =Integer.toString(key);

    products.put(k,cat);


    Details add_product = new Details(k,name,Des,price,quant);

    detail.put(k,add_product);



}




    
}