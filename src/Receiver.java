import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Receiver {

    private final int port = 1245;
    private ServerSocket receiving;

    public Receiver() throws IOException{
        this.receiving = new ServerSocket(port);
    }

    public void run() throws IOException{

        String message;
        Socket incoming = receiving.accept();
        Scanner input = new Scanner(incoming.getInputStream());
        message = input.nextLine();

        System.out.println("Receiver active.");
        while(!message.equals("exit")){
            System.out.println(message);
            message = input.nextLine();
        }

    }
}
