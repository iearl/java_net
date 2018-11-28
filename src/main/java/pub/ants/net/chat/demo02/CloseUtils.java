package pub.ants.net.chat.demo02;

import java.io.Closeable;
import java.io.IOException;

public class CloseUtils {
    public static void closeAll(Closeable ... io){
        for (Closeable close:io){
            if(close!=null){
                try {
                    close.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
