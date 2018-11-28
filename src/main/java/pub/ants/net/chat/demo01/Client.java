package pub.ants.net.chat.demo01;

import java.io.*;
import java.net.Socket;

/**
 * 客户端：发送数据接受数据
 */
public class Client {
    public static void main(String[] args) throws IOException {
        Socket client = new Socket("localhost",8888);
        //控制台输入流
        BufferedReader console = new BufferedReader(new InputStreamReader(System.in));
        //向服务端发送消息
        DataOutputStream dos = new DataOutputStream(client.getOutputStream());
        while(true) {
            dos.writeUTF(console.readLine());
            dos.flush();
            //接受服务端消息
            DataInputStream dis = new DataInputStream(client.getInputStream());
            System.out.println(dis.readUTF());
        }


    }
}
