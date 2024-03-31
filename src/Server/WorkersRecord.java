package Server;

import RMI.WorkerInterface;

import java.util.ArrayList;

public class WorkersRecord {
    public ArrayList<WorkerInterface> workers;
    public WorkersRecord() {
        workers = new ArrayList<>();
    }
}
