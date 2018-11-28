package pub.ants.net.ip;

import java.net.InetAddress;
import java.net.InetSocketAddress;

/**
 * 提供端口
 */
public class InetSocketAddress01 {
    public static void main(String[] args) {
        InetSocketAddress addr = new InetSocketAddress("localhost",9999);
        System.out.println(addr.getHostName());
        System.out.println(addr.getPort());
        InetAddress address = addr.getAddress();
        System.out.println(address.getHostName());
        System.out.println(address.getHostAddress());
    }
}
