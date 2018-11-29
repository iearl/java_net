package pub.ants.net.chat.demo03;

import java.io.IOException;
import java.net.Socket;

/**
 * 客户端：发送数据接受数据
 */
public class Client {
    public static void main(String[] args) throws IOException {
        Socket client = new Socket("localhost",8888);
        new Thread(new Send(client)).start();
        new Thread(new Receive(client)).start();
    }
}
