package Workers;

import org.apache.commons.codec.digest.DigestUtils;

import java.math.BigInteger;
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

    /**
     * Convert byte to hexadecimal value for hash comparison
     * @param byteArray the byte array to convert
     * @return a hexadecimal string representation of the byte array
     */
    private static String convertByteToHexadecimal(byte[] byteArray) {
        BigInteger bigInteger = new BigInteger(1, byteArray);
        String hexString = bigInteger.toString(16);
        
        // Ensure that the resulting string has two characters for each byte
        int paddingLength = (byteArray.length * 2) - hexString.length();
        if (paddingLength > 0) {
            StringBuilder padding = new StringBuilder();
            for (int i = 0; i < paddingLength; i++) {
                padding.append("0");
            }
            hexString = padding.toString() + hexString;
        }
        
        return hexString;
    }

    public void terminate() {
        terminatedRequested = true;
    }
}
