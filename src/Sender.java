import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

public class Sender {

private InetAddress host;
private final int port_number = 1240;



    public Sender() throws IOException{
        this.host = InetAddress.getLocalHost();

    }

    public void run() throws IOException{

        Socket socket = new Socket(host, port_number);

        String entry_value, message = "Packet";
        int final_value;

        //Scanner input = new Scanner(socket.getInputStream());
        Scanner user_entry = new Scanner(System.in);
        System.out.println("Enter number of packets: ");

        entry_value = user_entry.nextLine();
        final_value = Integer.parseInt(entry_value);

        PrintWriter output = new PrintWriter(socket.getOutputStream());


        int counter = 0;

        do{
            output.println(message + " " + counter);
            counter++;
        }while(counter<final_value);






    }


}
