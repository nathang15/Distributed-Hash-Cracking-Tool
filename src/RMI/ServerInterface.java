package RMI;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ServerInterface extends Remote {
    public void completed(String done) throws RemoteException;
    public void assign(int port) throws RemoteException;
    public void addJob(String hash, String algo, String charset, int maxlen, int port) throws RemoteException;
    public void stop() throws RemoteException;
}
