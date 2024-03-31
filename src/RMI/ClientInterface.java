package RMI;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ClientInterface extends Remote {
    public void completed(String password) throws RemoteException;
}
