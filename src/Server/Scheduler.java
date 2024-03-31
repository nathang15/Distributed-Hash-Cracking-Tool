package Server;

import java.rmi.RemoteException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Scheduler extends Thread {
    private final Job job;
    private final WorkersRecord wr;

    public Scheduler(WorkersRecord wrRecord) {
        wr = wrRecord;
        this.job = SMain.jobList.pop();
    }

    @Override
    public void run() {
        while (wr.workers.isEmpty()) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                Logger.getLogger(Scheduler.class.getName()).log(Level.SEVERE, null, e);
            }
        }
        System.out.println("Character set: " + job.choices + "\nInput hash: " + job.hash);

        // Scheduling the job ranges among workers
        int numWorkers = wr.workers.size();
        int choicesLength = job.choices.length();
        long totalSpace = (long) Math.pow(choicesLength, job.maxLen);
        long part = totalSpace / numWorkers;
        long remaining = totalSpace % numWorkers;

        long start = 0;
        for (int i = 0; i < numWorkers; i++) {
            long end = start + part - 1;
            // Distribute the remaining space among workers
            if (i < remaining) {
                end++;
            }

            try {
                wr.workers.get(i).assignJob(start, end, job.hash, job.choices, job.algo);
            } catch (RemoteException e) {
                Logger.getLogger(Scheduler.class.getName()).log(Level.SEVERE, null, e);
            }

            // Update the start for the next worker
            start = end + 1;
        }
    }
}
