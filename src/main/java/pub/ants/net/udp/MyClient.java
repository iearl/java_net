package pub.ants.net.udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;


/**
 * 1.创建客户端(DatagramSocket)+端口(不能与服务端得端口冲突)
 * 2.数据准备
 * 3.打包 DatagramPacket(byte buf[], int length,SocketAddress address)
 * 4.发送数据
 * 5.释放资源
 */
public class MyClient {
    public static void main(String[] args) throws IOException {
        //1.创建客户端(DatagramSocket)+端口(不能与服务端得端口冲突)
        DatagramSocket client = new DatagramSocket(6666);
        //2.数据准备
        byte[] msg = "hello word udp".getBytes();
        //3.打包 DatagramPacket(byte buf[], int length,SocketAddress address)
        DatagramPacket packet = new DatagramPacket(msg, msg.length, new InetSocketAddress("localhost",8888));
        //4.发送数据
        client.send(packet);
        //5.释放资源
        client.close();
    }
}
