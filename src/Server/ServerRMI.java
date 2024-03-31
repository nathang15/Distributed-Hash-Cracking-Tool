package Server;

import RMI.ClientInterface;
import RMI.ServerInterface;
import RMI.WorkerInterface;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.server.ServerNotActiveException;
import java.rmi.server.UnicastRemoteObject;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ServerRMI extends UnicastRemoteObject implements ServerInterface {
    public ServerRMI() throws RemoteException {
        super();
    }

    // wr stands for WORKERS RECORD
    @Override
    public void completed(String pass) throws RemoteException {
        // TODO: implement completed method. Hint: look at the stop() method below
        /*
         * The purpose of this method is stop all running workers
         * by iterating through worker records.
         * and notify the clientRmi that the password is found
         */
    }
    @Override
    public void assign(int port) {
        try {
            String address = getClientHost();
            WorkerInterface worker = (WorkerInterface) Naming.lookup("rmi://" + address + ":" + port + "/" + "rmi");
            SMain.wr.workers.add(worker);
            System.out.println("Assigned worker at " + port);
        }
        catch (NotBoundException | MalformedURLException | RemoteException | ServerNotActiveException e) {
            Logger.getLogger(ServerRMI.class.getName()).log(Level.SEVERE, null, e);
        }   
    }

    public void addJob(String hash, String algo, String charset, int maxlen, int port) throws RemoteException {
        try {
            String address = getClientHost();
            SMain.clientRmi = (ClientInterface) Naming.lookup("rmi://" + address + ":" + port + "/" + "rmi");
            Job job = new Job(hash, algo, charset, maxlen);
            SMain.jobList.push(job);
            new Scheduler(SMain.wr).start();
            System.out.println("Started Hash cracking process...");
        }
        catch (NotBoundException | MalformedURLException | ServerNotActiveException e) {
            Logger.getLogger(ServerRMI.class.getName()).log(Level.SEVERE, null, e);
        }

    }

    @Override
    public void stop() throws RemoteException {
        for (int i = 0; i < SMain.wr.workers.size(); i++){
            try {
                SMain.wr.workers.get(i).stop();
            }
            catch (RemoteException e) {
                Logger.getLogger(ServerRMI.class.getName()).log(Level.SEVERE, null, e);
            }
        }
    }


}
