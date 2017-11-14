import java.io.IOException;

public class Client {

    public static void main(String[] args) throws IOException{

        Receiver receiver = new Receiver();
        Router router_four = new Router(4);
        Router router_three = new Router(3);
        Router router_two = new Router(2);
        Router router_one = new Router(1);
        Sender sender = new Sender();

        receiver.run();
        /*
        router_four.run();
        router_three.run();
        router_two.run();
        router_one.run();
        sender.run();
           */
    }
}
