 
package doom;
 
import java.io.File;
import java.io.Serializable; 
import java.net.Inet4Address;
import java.net.UnknownHostException;
import java.util.ArrayList;

public class User implements Serializable {
    
    private String ip, id, folderPath;
    private int TCPServerPort;   
    private int UDPServerPort;
    private ArrayList<File> files2Share;
    
    public User(String id, int UDPServerPort, int TCPServerPort) throws UnknownHostException
    {
        this.id = id;
        this.ip = Inet4Address.getLocalHost().getHostAddress();
        this.TCPServerPort = TCPServerPort;
        this.UDPServerPort = UDPServerPort;
        this.folderPath = "./DoomFiles2Share/";
        files2Share = getFiles2Share();
    }
    
    public User(String id) throws UnknownHostException
    {
        this.id = id;
        this.ip = Inet4Address.getLocalHost().getHostAddress();
        this.UDPServerPort = 6001;
        this.TCPServerPort = 7001;
        this.folderPath = "./DoomFiles2Share/";
        files2Share = getFiles2Share(); 
    }  
    
    //Getters
    public String getIp() {
        return ip;
    }
 
    public void setIp(String ip) {
        this.ip = ip;
    }
 
    public int getTCPServerPort() {
        return TCPServerPort;
    }
 
    public void setTCPServerPort(int TCPServerPort) {
        this.TCPServerPort = TCPServerPort;
    }
 
    public int getUDPServerPort() {
        return UDPServerPort;
    }
 
    public void setUDPServerPort(int UDPServerPort) {
        this.UDPServerPort = UDPServerPort;
    }
    
    //Methods 
    public ArrayList<File> getFiles2Share(String folderPath) {
        
        ArrayList<File> localFiles = new ArrayList<>();
        File currentDirectory = new File(folderPath);
        
        for(File file : currentDirectory.listFiles())
            localFiles.add(file);
        
        return localFiles;
    }
    
    public ArrayList<File> getFiles2Share() 
    {
        return getFiles2Share(folderPath);
    }
    
    @Override
    public String toString()
    {
        String output = id 
                + ","  + ip 
                + "," + UDPServerPort
                + "," + TCPServerPort 
                + ","+ folderPath;
        
        for(File file: files2Share)
            output += "\n" + file.getName();
        
        return output + "\n";
    }

    /**
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return the folderPath
     */
    public String getFolderPath() {
        return folderPath;
    }

    /**
     * @param folderPath the folderPath to set
     */
    public void setFolderPath(String folderPath) {
        this.folderPath = folderPath;
    }
}
