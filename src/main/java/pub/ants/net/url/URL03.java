package pub.ants.net.url;

import java.io.*;
import java.net.URL;

/**
 * 获得资源：源代码
 * 乱码：字节数不够
 *       编码节码不同意
 */
public class URL03 {
    public static void main(String[] args) throws IOException {
        URL url = new URL("https://www.baidu.com");
        InputStream inputStream = url.openStream();
        BufferedReader br = new BufferedReader(new InputStreamReader(inputStream,"utf-8"));
        String msg = "";
        while((msg=br.readLine())!=null){
            System.out.println(msg);
        }

    }
}
