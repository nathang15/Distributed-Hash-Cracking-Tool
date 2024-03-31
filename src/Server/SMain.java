package Server;


import RMI.ClientInterface;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SMain {
    public static WorkersRecord wr;
    public static LinkedList<Job> jobList;
    public static ClientInterface clientRmi;

    public static void main(String[] args) throws RemoteException {
        /*
          The value of this property represents the host name string that should be associated with remote stubs
          for locally created remote objects, in order to allow clients to invoke methods on the remote object.
          The default value of this property is the IP address of the local host, in "dotted-quad" format.
         */
        System.setProperty("java.rmi.server.hostname", "localhost");

        wr = new WorkersRecord();
        jobList = new LinkedList<>();
        Registry registry = LocateRegistry.createRegistry(8081);
        ServerRMI rmi = new ServerRMI();
        registry.rebind("THE_SERVER", rmi);
        System.out.println("Server Up!");
        try {
            Thread.sleep(10000);
        }
        catch (InterruptedException e){
            Logger.getLogger(SMain.class.getName()).log(Level.SEVERE, null, e);
        }
    }
}
