package Workers;

import java.io.Serializable;

public class Job implements Serializable {
    public String hash;
    public int jobID;
    public int start;
    public int end;
}
