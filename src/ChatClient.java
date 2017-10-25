import java.awt.*;
import java.net.*;
import java.io.*;

public class ChatClient
{  private Socket socket              = null;
    private DataInputStream  console   = null;
    private DataOutputStream streamOut = null;

    public ChatClient(String serverName, int serverPort) throws Exception
    {

        System.out.println("Establishing connection. Please wait ...");
        try
        {  socket = new Socket(serverName, serverPort);
            System.out.println("Connected: " + socket);
            start();
        }
        catch(UnknownHostException uhe)
        {  System.out.println("Host unknown: " + uhe.getMessage());
        }
        catch(IOException ioe)
        {  System.out.println("Unexpected exception: " + ioe.getMessage());
        }
        String line = "";
        while (!line.equals(".bye"))
        {  try
        {
            String compare = MouseInfo.getPointerInfo().getLocation().toString();

            line = MouseInfo.getPointerInfo().getLocation().getX() + "," + MouseInfo.getPointerInfo().getLocation().getY();
            if(!MouseInfo.getPointerInfo().getLocation().toString().equals(compare)) {
                streamOut.writeUTF(line);
                streamOut.flush();
            }
        }
        catch(IOException ioe)
        {  System.out.println("Sending error: " + ioe.getMessage());
        }
        }
    }
    public void start() throws IOException
    {  console   = new DataInputStream(System.in);
        streamOut = new DataOutputStream(socket.getOutputStream());
    }
    public void stop()
    {  try
    {  if (console   != null)  console.close();
        if (streamOut != null)  streamOut.close();
        if (socket    != null)  socket.close();
    }
    catch(IOException ioe)
    {  System.out.println("Error closing ...");
    }
    }
    public static void main(String args[]) throws Exception{
        ChatClient client = new ChatClient("192.168.0.38", 9505);
    }
}