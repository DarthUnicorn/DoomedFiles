package doom;
import java.io.*; 
import java.net.*;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

class UserClient
{   
    public static ArrayList<User> getOnlineUsers(User newUser) throws IOException, ClassNotFoundException 
    { 
        ArrayList<User> users = new ArrayList<>(); 
        String host = Inet4Address.getLocalHost().getHostAddress(); 
        Socket clientSocket = new Socket(host, 6789);
        OutputStream outputStream = clientSocket.getOutputStream();
        
        //creating byte array with content to send
        byte[] toServer = (newUser.getId() + "," + newUser.getTCPServerPort() + "," + newUser.getUDPServerPort()).getBytes();
        
        //get byte length
        int length = toServer.length;
        
        byte[] toServerLength = ByteBuffer.allocate(Integer.SIZE).putInt(length).array();
        
        //sending bytes 
        outputStream.write(toServerLength);
        outputStream.write(toServer);
        
        try{
        //receiving response from server
        InputStream stream = clientSocket.getInputStream();   
        ByteBuffer bb = ByteBuffer.wrap(new byte[Integer.SIZE]);
        
        int returnType = stream.read(bb.array());
        int lengthFromServer;

        if(returnType == -1) 
        { 
            clientSocket.close();
        }
        else
        {
            lengthFromServer = bb.getInt();
            byte[] onlineUsersListInBytes = new byte[lengthFromServer];
            stream.read(onlineUsersListInBytes, 0, lengthFromServer);
            
            //if whatever we received wasn't null
            if(onlineUsersListInBytes.length != 0)
            {
               String onlineUsersList = new String(onlineUsersListInBytes); 
                System.out.println(onlineUsersList); 
                //creating newUsers from received data and adding it to onlineUserList 
                String [] splitUsers = onlineUsersList.split(",");

                User tempUser = null;

                for(int count = 0; count < splitUsers.length; count++)
                {
                    if(count%4 == 0)
                    {
                        tempUser = new User(splitUsers[count]);
                        tempUser.setIp(splitUsers[count+1]);
                        tempUser.setTCPServerPort(Integer.parseInt(splitUsers[count+2].trim()));
                        tempUser.setUDPServerPort(Integer.parseInt(splitUsers[count+3].trim()));    
                        users.add(tempUser); 
                    }
                } 
            } 
        } 
        }
        catch(IOException ex){
            Logger.getLogger(UserClient.class.getName()).log(Level.SEVERE, null, ex);
        }
         
        return users;
    }
    
    public static ArrayList<User> getOnlineUsers() throws IOException, ClassNotFoundException 
    {
        ArrayList<User> users = new ArrayList<>(); 
        String host = Inet4Address.getLocalHost().getHostAddress(); 
        Socket clientSocket = new Socket(host, 6789);
        OutputStream outputStream = clientSocket.getOutputStream();
        
        //creating byte array with content to send
        byte[] toServer = ("REFRESH_REQUEST").getBytes();
        
        //get byte length
        int length = toServer.length;
        
        byte[] toServerLength = ByteBuffer.allocate(Integer.SIZE).putInt(length).array();
        
        //sending bytes 
        outputStream.write(toServerLength);
        outputStream.write(toServer);
        
        try{
        //receiving response from server
        InputStream stream = clientSocket.getInputStream();   
        ByteBuffer bb = ByteBuffer.wrap(new byte[Integer.SIZE]);
        
        int returnType = stream.read(bb.array());
        int lengthFromServer;

        if(returnType == -1) 
        { 
            clientSocket.close();
        }
        else
        {
            lengthFromServer = bb.getInt();
            byte[] onlineUsersListInBytes = new byte[lengthFromServer];
            stream.read(onlineUsersListInBytes, 0, lengthFromServer);
            
            String onlineUsersList = new String(onlineUsersListInBytes);
            
            System.out.println(onlineUsersList);
            
            //creating newUsers from received data and adding it to onlineUserList 
            String [] splitUsers = onlineUsersList.split(",");
            
            User tempUser = null;
            
            for(int count = 0; count < splitUsers.length; count++)
            {
                if(count%4 == 0)
                {
                    tempUser = new User(splitUsers[count]);
                    tempUser.setIp(splitUsers[count+1]);
                    tempUser.setTCPServerPort(Integer.parseInt(splitUsers[count+2].trim()));
                    tempUser.setUDPServerPort(Integer.parseInt(splitUsers[count+3].trim()));    
                    users.add(tempUser); 
                }
            } 
        } 
        }
        catch(IOException ex){
            Logger.getLogger(UserClient.class.getName()).log(Level.SEVERE, null, ex);
        }
         
        return users;
    }
      
}