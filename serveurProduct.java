/*
 * Riad Ghribi
 */
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.*;

public interface serveurProduct extends Remote {

    void register(String name, serveurProduct callback) throws RemoteException ;

    void unregister(String name) throws RemoteException ;

    void sendData(String message) throws RemoteException ;

    void notify(String message) throws RemoteException ;

    HashMap<String, String> getAllData() throws RemoteException;

    Details InfoAboutProduct(String key_product) throws RemoteException;

    void Delete_product(String key) throws RemoteException;

    void Update_product_quantity(String key, int quant) throws RemoteException;

    void add_product(String name, String cat ,String Des ,String price,int quant) throws RemoteException;

    

    boolean check_catgorie_exist(String data) throws RemoteException;



    
}
