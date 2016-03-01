
package rmi;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 *
 * @author matang
 */
public class RMIServer {

    private void startServer() {
        try {
            // create on port 2016
            Registry registry = LocateRegistry.createRegistry(2016);
            // create a new service named myMessage
            registry.rebind("rmi", new MessageImpl());
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("RMI server ready ..");
    }

    public static void main(String[] args) {
        RMIServer main = new RMIServer();
        main.startServer();
    }
}
