package pub.ants.net.url;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

/**
 * 获得资源：源代码
 * 乱码：字节数不够
 *       编码节码不同意
 */
public class URL02 {
    public static void main(String[] args) throws IOException {
        URL url = new URL("https://www.baidu.com");
        InputStream inputStream = url.openStream();
        BufferedInputStream bis = new BufferedInputStream(inputStream);
        byte[] b = new byte[1024];
        int len = 0;
        while((len=bis.read(b))!=-1){
            System.out.println(new String(b,0,len));
        }
        bis.close();
        inputStream.close();

    }
}
