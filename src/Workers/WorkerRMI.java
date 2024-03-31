package Workers;

import RMI.WorkerInterface;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class WorkerRMI extends UnicastRemoteObject implements WorkerInterface {
    private RMI.ServerInterface serverInterface;
    private BruteForce bruteForce;
    public WorkerRMI() throws RemoteException {
        super();
    }

    //TODO: assign brutefoce job and start the bruteforce
    /**
     * Assign brute force job for worker
     * @param start
     * @param end
     * @param hash
     * @param ch
     * @param algo
     * @throws RemoteException
     */
    @Override
    public void assignJob(long start, long end, String hash, String ch, String algo) throws RemoteException {
        try {

        } catch (NoSuchAlgorithmException e) {
            Logger.getLogger(WorkerRMI.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    @Override
    // stop the brute force
    public void stop() throws RemoteException {
        bruteForce.terminate();
    }
}
