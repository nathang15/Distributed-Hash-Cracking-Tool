package Client;

import RMI.ServerInterface;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.security.MessageDigest;

public class ConnectToWorker extends SwingWorker<ServerInterface, Object> {
    private final String hash;
    private final String algo;
    private final String charset;
    private final int maxlen;
    private final String serverAddress;
    private final JPanel jPanel;
    private ServerInterface serverRmi;
//    private File dictionary;
    private final JTable table;
    public ConnectToWorker(String hash, String algo, String charset, int maxlen, String serverAddress, JPanel jPanel, ServerInterface serverRmi, JTable table) {
        this.hash = hash;
        this.algo = algo;
        this.charset = charset;
        this.maxlen = maxlen;
        this.serverAddress = serverAddress;
        this.jPanel = jPanel;
        this.serverRmi = serverRmi;
//        this.dictionary = dictionary;
        this.table = table;
    }


    @Override
    protected ServerInterface doInBackground() throws Exception {
        JLabel jLabel = (JLabel) jPanel.getComponent(0);

        System.out.println("Request Sent!!!");
        jLabel.setText("Connecting...");
        CardLayout cl = (CardLayout) jPanel.getParent().getLayout();
        cl.last(jPanel.getParent());

        serverRmi = (ServerInterface) Naming.lookup("rmi://" + serverAddress + ":8081/THE_SERVER");

        ClientRMI rmi = new ClientRMI(jLabel);

        Registry registry = LocateRegistry.createRegistry(5555);
        registry.rebind("rmi", rmi);

        jLabel.setText("Hash cracking in progress...");

        serverRmi.addJob(hash, algo, charset, maxlen, 5555);

        table.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][]{
                        {"Hash", hash},
                        {"Algorithm", algo},
                        {"Character Set", charset},
                        {"Password Length", maxlen - 1},
                        {"No of workers", null},
                },
                new String[]{
                        "", ""
                }
        ));

        return null;
    }
}
