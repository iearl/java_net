package pub.ants.net.chat.demo03;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;

public class Receive implements Runnable {
    //管道输入流
    DataInputStream dis;
    //线程标识
    private boolean isRunning = true;

    public Receive() {
    }

    public Receive(Socket client) {
        try {
            dis = new DataInputStream(client.getInputStream());
        } catch (IOException e) {
            isRunning = false;
            CloseUtils.closeAll(dis);
        }
    }

    //从控制台接受数据
    public String receive() {
        String msg = null;
        try {
            msg =  dis.readUTF();
        } catch (IOException e) {
            isRunning = false;
            CloseUtils.closeAll(dis);
        }
        return msg;
    }

    public void run() {
        while (isRunning) {
            System.out.println(receive());
        }
    }
}
