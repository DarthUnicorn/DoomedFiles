package doom;
 
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/**
 * @author tassadar
 */
public class ChatClientConnection 
{
    private static String host;
    private static int port;
    
    public ChatClientConnection(String host, int port)
    {
        this.host = host;
        this.port = port;
    }
    
   public void sendChatMessage(String message) throws Exception
   { 
      DatagramSocket clientSocket = new DatagramSocket();
      InetAddress IPAddress = InetAddress.getByName(host); 
      byte[] sendData = new byte[1024]; 
      String sentence = message;
      sendData = sentence.getBytes();
      DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, port);
      clientSocket.send(sendPacket); 
      clientSocket.close();
   }
}
