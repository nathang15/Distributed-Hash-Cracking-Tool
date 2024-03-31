package RMI;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface WorkerInterface extends Remote {
    public void assignJob(long start, long end, String hash, String ch, String algo) throws RemoteException;
    public void stop() throws RemoteException;
}
