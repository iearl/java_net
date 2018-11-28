package pub.ants.net.chat.demo01;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 服务器，为每一个客户端创建一个线程
 * 写出数据：
 * 读取数据：
 *
 * 输入流与输出流在同一线程内 应该彼此独立，使用两个不同得线程操作
 */
public class Server {
    public static void main(String[] args) throws IOException {
        ServerSocket server = new ServerSocket(8888);
        Socket accept = server.accept();
        //服务器向客户端发送消息
        DataOutputStream dos = new DataOutputStream(accept.getOutputStream());
        dos.writeUTF("服务器向客户端信息");
        dos.flush();
        //接受客户端得消息
        DataInputStream dis = new DataInputStream(accept.getInputStream());
        System.out.println("聊天室"+dis.readUTF());





    }
}
