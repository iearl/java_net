package pub.ants.net.url;

import info.monitorenter.cpdetector.io.*;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;
import java.util.List;
import java.util.Map;

/**
 * 获得网页编码得三种格式
 */
public class URL04 {
    public static void main(String[] args) throws Exception {
        URL url = new URL("https://www.baidu.com");
        URLConnection connection = url.openConnection();
        //System.out.println(getDecode01(connection));
        //System.out.println(getDecode02(connection));
        System.out.println(getDecode03(connection));


    }

    /**
     * 通过header中解析charset
     *
     * @return
     */
    public static String getDecode01(URLConnection connection) {
        String charset = null;
        Map<String, List<String>> headerFields = connection.getHeaderFields();
        if (headerFields.containsKey("Content-Type")) {
            List<String> attrs = headerFields.get("Content-Type");
            String[] as = attrs.get(0).split(";");
            for (String att : as) {
                if (att.contains("charset")) {
                    charset = att.split("=")[1];
                }
            }
        }
        return charset;
    }

    public static String getDecode02(URLConnection urlConn) throws  IOException{
        String charset = null;
        //避免被拒绝
        urlConn.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/51.0.2704.103 Safari/537.36");
        // 将html读取成行,放入list
        BufferedReader br = new BufferedReader(new InputStreamReader(urlConn.getInputStream()));
        String line = "";
        while((line=br.readLine())!=null){
            if (line.contains("http-equiv") && line.contains("charset")) {
                String tmp = line.split(";")[1];
                charset = tmp.substring(tmp.indexOf("=") + 1, tmp.indexOf("\""));
            }
        }
        return charset;
    }

    public static String getDecode03(URLConnection urlConn) {
        Charset charset = null;
        try {
            //打开链接,加上User-Agent,避免被拒绝
            urlConn.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/51.0.2704.103 Safari/537.36");
            //解析页面内容
            CodepageDetectorProxy cdp = CodepageDetectorProxy.getInstance();
            cdp.add(JChardetFacade.getInstance());//
            cdp.add(ASCIIDetector.getInstance());
            cdp.add(UnicodeDetector.getInstance());
            cdp.add(new ParsingDetector(false));
            cdp.add(new ByteOrderMarkDetector());

            InputStream in = urlConn.getInputStream();
            ByteArrayOutputStream swapStream = new ByteArrayOutputStream();
            byte[] buff = new byte[100]; //buff用于存放循环读取的临时数据
            int rc = 0;
            while ((rc = in.read(buff, 0, 100)) > 0) {
                swapStream.write(buff, 0, rc);
            }
            byte[] b = swapStream.toByteArray(); //in_b为转换之后的结果
            ByteArrayInputStream bais = new ByteArrayInputStream(b);
            // detectCodepage(InputStream in, int length) 只支持可以mark的InputStream
            charset = cdp.detectCodepage(bais, 2147483647);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return charset == null ? null : charset.name().toLowerCase();
    }

}
