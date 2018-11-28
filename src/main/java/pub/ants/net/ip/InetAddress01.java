package pub.ants.net.ip;

import java.net.InetAddress;

/**
 * 没有提供端口
 */
public class InetAddress01 {
    public static void main(String[] args) throws Exception {
        InetAddress addr = InetAddress.getLocalHost();
        System.out.println(addr.getHostAddress());
        System.out.println(addr.getHostName());

        addr = InetAddress.getByName("www.baidu.com");
        System.out.println(addr.getHostName());
        System.out.println(addr.getHostAddress());

        addr = InetAddress.getByName("127.0.0.1");
        System.out.println(addr.getHostName());//ip地址或DNS不允许解析返回ip本身
        System.out.println(addr.getHostAddress());//ip地址或DNS不允许解析返回ip本身
    }
}
