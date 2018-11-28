package pub.ants.net.tcp;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 服务器端
 * 1.创建服务器端，指定端口  ServerSocket
 * 2.接受客户端得连接，阻塞式   server.accept()
 * 3.接受数据+发送数据
 */
public class Server {
    public static void main(String[] args) throws IOException {
        //1.创建服务器端，指定端口  ServerSocket
        ServerSocket server = new ServerSocket(8888);
        //2.接受客户端得连接，阻塞式   server.accept()
        Socket accept = server.accept();
        System.out.println("一个客户端建立连接");
        //3.发送数据
        String msg = "接受客户端发送得消息";
        DataOutputStream dos = new DataOutputStream(accept.getOutputStream());
        dos.writeUTF(msg);
        dos.flush();

        DataInputStream dis =new DataInputStream(accept.getInputStream());
        System.out.println(dis.readUTF());
    }
}
