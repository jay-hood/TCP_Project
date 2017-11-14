

import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.NoSuchElementException;
import java.util.Random;
import java.util.Scanner;

public class Router {

    private ServerSocket server_socket_one;
    private ServerSocket server_socket_two;
    private InetAddress host;
    private Socket socket_one;
    private Socket socket_two;
    private int router_number;
    private String message;
    private Socket connection;
    private Socket connection_two;
    //private static Scanner read_from_sender;
    //private static PrintWriter write_to_sender;
    private PrintWriter write_one;
    private Scanner read_one;
    private PrintWriter write_two;
    private Scanner read_two;



    public Router(int router_number) throws IOException{
        this.host = InetAddress.getLocalHost();
        this.router_number = router_number;

        switch(router_number) {

            case 1:
                this.server_socket_one = new ServerSocket(1240);//listening port from Sender
                this.connection = server_socket_one.accept();
                this.socket_one = new Socket(host, 1241);//socket to router 2
                this.socket_two = new Socket(host, 1242);//socket to router 3
                this.write_one = new PrintWriter(socket_one.getOutputStream());
                this.write_two = new PrintWriter(socket_two.getOutputStream());
                break;

            case 2:
                this.server_socket_one = new ServerSocket(1241);//listener for socket 2
                this.connection = server_socket_one.accept();
                this.socket_one = new Socket(host, 1243);//socket to router 4
                this.read_one = new Scanner(connection.getInputStream());
                this.write_one = new PrintWriter(socket_one.getOutputStream());
                break;

            case 3:
                this.server_socket_one = new ServerSocket(1242);//listener for socket 3
                this.connection = server_socket_one.accept();
                this.socket_one = new Socket(host, 1244);//socket to router 4
                this.read_one = new Scanner(connection.getInputStream());
                this.write_one = new PrintWriter(socket_one.getOutputStream());
                break;

            case 4:
                this.server_socket_one = new ServerSocket(1243);
                this.server_socket_two = new ServerSocket(1244);
                this.connection = server_socket_one.accept();
                this.connection_two = server_socket_two.accept();
                this.socket_one = new Socket(host, 1245);
                this.read_one = new Scanner(connection.getInputStream());
                this.read_two = new Scanner(connection_two.getInputStream());
                this.write_one = new PrintWriter(socket_one.getOutputStream());
                break;
        }


    }

    public void run(){

        switch(router_number) {
            case 1:
                this.one();
                break;

            case 2:
                this.two();
                break;

            case 3:
                this.two();
                break;

            case 4:
                this.four();
                break;

        }

    }

    private void one() throws NoSuchElementException{

        message = read_one.nextLine();
        while(!message.equals("exit")){

            Random randomGenerator = new Random();
            int randomInt = randomGenerator.nextInt(2);
            //System.out.println("Generated random number for the packet is: "+randomInt);
            //System.out.println("Message from sender: " + message);
            if(randomInt==0) {

                System.out.println("Sending " + message + " to router 2");
                write_one.println(message);

            }
            else {
                System.out.println("Sending " + message + " to router 3");
                write_two.println(message);

            }
            message = read_one.nextLine();

        }

    }

    private void two() throws NoSuchElementException{

        message = read_one.nextLine();
        while(!message.equals("exit")){

            write_one.println(message);
            message = read_one.nextLine();
        }

    }


    private void four() throws NoSuchElementException{

        System.out.println("Router four active.");
        do{
            if(read_one.hasNextLine()) {
                message = read_one.nextLine();
                write_one.println(message);

            }
            if(read_two.hasNextLine()) {
                message = read_two.nextLine();
                write_one.println(message);
            }
        }while(!message.equals("exit"));

    }




}
