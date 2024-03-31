package Client;

import RMI.ClientInterface;

import javax.swing.*;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class ClientRMI extends UnicastRemoteObject implements ClientInterface {
    private final JLabel jlable;
    public ClientRMI(JLabel jlable) throws RemoteException {
        super();
        this.jlable = jlable;
    }


    @Override
    public void completed(String password) throws RemoteException {
        JOptionPane.showMessageDialog(null, "Text: " + password, "Completed", JOptionPane.INFORMATION_MESSAGE);
        jlable.setText("Completed!\nText: " + password);
    }
}
