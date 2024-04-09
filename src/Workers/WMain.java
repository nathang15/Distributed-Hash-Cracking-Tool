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

    /**
     * Generate port between 2000 and 3000
     * @return available port between the range
     */
    private static int generatePort() throws IOException {
        for (int port = 2000; port <= 3000; port++) {
            if (isPortAvailable(port)) {
                return port;
            }
        }
        throw new IOException("No available port in the specified range.");
    }

    /**
     * Check if the given port is available
     * @param port input port
     * @return true if input port is available, false otherwise
     */
    private static boolean isPortAvailable(int port){
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            return true;
        } catch (IOException e) {
            return false;
        }
    }
}
