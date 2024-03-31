package Server;

public class Job {
    public String hash;
    public String choices;
    public int maxLen;
    public String algo;
    public Job(String hash,String algo,String charset,int maxLen)
    {
        this.hash=hash;
        choices=charset;
        this.maxLen=maxLen;
        this.algo=algo;
    }
}
