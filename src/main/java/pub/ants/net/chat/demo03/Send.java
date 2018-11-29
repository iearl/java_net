package pub.ants.net.chat.demo03;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class Send implements Runnable {
    //控制台输入流
    BufferedReader console;
    //管道输出流
    DataOutputStream dos;
    //线程标识
    private boolean isRunning = true;

    public Send() {
        console = new BufferedReader(new InputStreamReader(System.in));
    }

    public Send(Socket client) {
        this();
        try {
            dos = new DataOutputStream(client.getOutputStream());
        } catch (IOException e) {
            isRunning = false;
            CloseUtils.closeAll(dos, console);
        }
    }

    //从控制台接受数据
    private String getMsgFormConsole() {
        try {
            return console.readLine();
        } catch (IOException e) {
            return "";
        }
    }

    /**
     * 发送数据
     */
    public void send() {
        String msg = getMsgFormConsole();
        if (null != msg && !"".equals(msg)) {
            try {
                dos.writeUTF(msg);
                dos.flush();
            } catch (IOException e) {
                isRunning = false;
                CloseUtils.closeAll(dos, console);
            }
        }
    }

    public void run() {
        while (isRunning) {
            send();
        }
    }
}
