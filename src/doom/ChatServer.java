package doom;
 
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;  
import java.net.SocketException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author tassadar
 */
public class ChatServer 
{ 
   private final int UDPServerPort;
   private static User user;
   
   public ChatServer(User user)
   {
       ChatServer.user = user;
       this.UDPServerPort = user.getUDPServerPort();
   }
   
   public void activateChatServer() throws SocketException, IOException
   { 
       try
             {
                DatagramSocket serverSocket = new DatagramSocket(UDPServerPort); 
                ReceiveMessageThread newThread = new ReceiveMessageThread(serverSocket);
                newThread.start();
             }
             catch(IOException e)
             {
                System.out.println(e.toString());
             }
                
   }
   static class ReceiveMessageThread extends Thread
   {
       DatagramSocket serverSocket;
       
       public ReceiveMessageThread(DatagramSocket serverSocket)
       {
        this.serverSocket = serverSocket;
       }
       
       @Override
       public void run() 
       {
          byte[] receiveData = new byte[1024]; 
            while(true)
           {
              DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
              try {
                  serverSocket.receive(receivePacket);
              } catch (IOException ex) {
                  Logger.getLogger(ChatServer.class.getName()).log(Level.SEVERE, null, ex);
              }
              String sentence = new String( receivePacket.getData());
              
              System.out.println("\nMessage received from chat:");
              System.out.println(sentence);
              
              MainContent.chat.getjTextArea1().append("\n" + user.getId() + ": " + sentence.trim()); 
           } 
       }
       
   } 
     
}
