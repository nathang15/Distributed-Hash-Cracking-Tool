package Workers;

import RMI.ServerInterface;

import java.io.IOException;
import java.net.*;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class WMain {
    public static void main(String[] args) throws NotBoundException, IOException {
        serverRmi = (ServerInterface) Naming.lookup("rmi://localhost:8081/THE_SERVER");
        WorkerRMI rmi = new WorkerRMI();
        int assignedPort = generatePort();
        System.out.println(assignedPort);
        Registry registry = LocateRegistry.createRegistry(assignedPort);
        registry.rebind("rmi", rmi);
        serverRmi.assign(assignedPort);
    }
    public static ServerInterface serverRmi;

    // port ranges between 2000 and 3000
    // TODO generate port in range between 2000 and 3000 for worker to use
    /**
     * Hint: use while(true) loop and isPortAvailable methhod
     * @return
     */
    private static int generatePort() {
        return 0;
    }

    // TODO check if the input port is available or not
    /**
     * Use try catch block
     * @param port input port
     * @return true if input port is available, false otherwise
     */
    private static boolean isPortAvailable(int port){
        new ServerSocket(port).close();
        return false;
    }
}
