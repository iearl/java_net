package pub.ants.net.tcp;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 客户端
 * 1.创建客户端，指定ip地址+端口  Socket("localhost",8888)
 * 2.接受数据
 * 3.发送数据
 */
public class Client {
    public static void main(String[] args) throws IOException {
        //1.创建客户端，指定ip地址+端口  Socket("localhost",8888)
        Socket client = new Socket("localhost",8888);
        //2.接受数据
        InputStream inputStream = client.getInputStream();
        DataInputStream dis =new DataInputStream(inputStream);
        System.out.println(dis.readUTF());
        //3.发送数据
        String msg = "OK";
        DataOutputStream dos = new DataOutputStream(client.getOutputStream());
        dos.writeUTF(msg);
        dos.flush();


    }
}
