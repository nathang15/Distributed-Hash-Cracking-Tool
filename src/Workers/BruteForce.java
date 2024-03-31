package Workers;

import org.apache.commons.codec.digest.DigestUtils;
import java.rmi.RemoteException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BruteForce extends Thread{
    private final MessageDigest md;
    private final String ch;
    private final long start;
    private final long end;
    private final String hash;
    private String pwd;
    private boolean cracked = false;
    private int len;
    private boolean terminatedRequested = false;
    public BruteForce(long start, long end, String hash, String choices, String algo) throws NoSuchAlgorithmException {
        this.md = MessageDigest.getInstance(algo);
        this.start = start;
        this.end = end;
        this.hash = hash;
        this.ch = choices;
    }

    @Override
    public void run() {
        long cur = start;
        len = ch.length();
        while (!terminatedRequested && !cracked && cur <= end) {
            if (check(cur)) {
                cracked = true;
                try {
                    WMain.serverRmi.completed(pwd);
                } catch (RemoteException e) {
                    Logger.getLogger(BruteForce.class.getName()).log(Level.SEVERE, null, e);
                }
            }
            else {
                cur++;
            }
        }
    }

    private boolean check(long temp){
        StringBuilder sb = new StringBuilder();
        String guess;
        long i = temp;
        do {
            sb.append(ch.charAt((int)(i % len)));
            i = i / len;
        } while (i >= len);
        guess = (convertByteToHexadecimal(md.digest(sb.toString().getBytes())));
        System.out.println(guess + ": " + sb);
        if (guess.equals(hash)) {
            System.out.println(sb);
            pwd = sb.toString();
            return true;
        }
        return false;
    }

    //TODO: implement this method. Hint: use StringBuilder
    /**
     * Convert byte to hexademical value
     * @param byteArray
     * @return
     */
    private static String convertByteToHexadecimal(byte[] byteArray)
    {
        return "";
    }

    public void terminate() {
        terminatedRequested = true;
    }
}
