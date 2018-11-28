package pub.ants.net.udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

/**
 * 1.创建服务端(DatagramSocket)+端口
 * 2.装备接受容器
 * 3.封装成包(DatagramPacket)
 * 4.接受数据
 * 5.分析数据
 * 6.释放资源
 */
public class MyServer {
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
        byte[] data = packet.getData();
        int length = packet.getLength();
        System.out.println(new String(data,0,length));
        //6.释放资源
        server.close();
    }
}
