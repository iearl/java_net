package pub.ants.net.udp;

import java.io.*;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

/**
 * 1.创建服务端(DatagramSocket)+端口
 * 2.装备接受容器
 * 3.封装成包(DatagramPacket)
 * 4.接受数据
 * 5.分析数据
 * 6.释放资源
 */
public class MyServer1 {
    public static void main(String[] args) throws IOException {
        //1.创建服务端(DatagramSocket)+端口
        DatagramSocket server = new DatagramSocket(8888);
        //2.装备接受容器
        byte[] container = new byte[1024];
        //3.封装成包(DatagramPacket)
        DatagramPacket packet = new DatagramPacket(container,container.length);
        //4.接受数据
        server.receive(packet);
        //5.分析数据
        double d= convert(packet.getData());

        System.out.println(d);
        //6.释放资源
        server.close();
    }
    public static double convert(byte[] data) throws IOException {

        ByteArrayInputStream bis = new ByteArrayInputStream(data);
        DataInputStream dos = new DataInputStream(bis);

        double num = dos.readDouble();

        return num;
    }
}
