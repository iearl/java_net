package pub.ants.net.url;

import java.net.MalformedURLException;
import java.net.URL;

public class URL01 {
    public static void main(String[] args) throws MalformedURLException {
        //绝对路径构建
        URL url = new URL("https://www.baidu.com/s?ie=utf-8&f=8&rsv_bp=1&rsv_idx=2&tn=baiduhome_pg&wd=ip&rsv_spt=1&oq=ip&rsv_pq=d55ac4750001f2e7&rsv_t=86c4JKL3ZkH3zzJcVZ8xtLF%2BDrF2yHmsZbd2maMFwU5%2FMYURwGLTEgBNJlgh75RTHIMp&rqlang=cn&rsv_enter=0");
        URL url1 = new URL("https://www.baidu.com");
        url1 = new URL(url,"/s?ie=utf-8&f=8&rsv_bp=1&rsv_idx=2&tn=baiduhome_pg&wd=ip&rsv_spt=1&oq=ip&rsv_pq=d55ac4750001f2e7&rsv_t=86c4JKL3ZkH3zzJcVZ8xtLF%2BDrF2yHmsZbd2maMFwU5%2FMYURwGLTEgBNJlgh75RTHIMp&rqlang=cn&rsv_enter=0");
        System.out.println(url);
        System.out.println("协议："+url.getProtocol());
        System.out.println("域名："+url.getHost());
        System.out.println("端口："+url.getPort());
        System.out.println("资源："+url.getFile());
        System.out.println("相对路径："+url.getPath());
        System.out.println("锚点:"+url.getRef());
        System.out.println("参数："+url.getQuery());//存在锚点为null，不存在返回正确
    }
}
