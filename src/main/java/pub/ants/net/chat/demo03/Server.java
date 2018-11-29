package pub.ants.net.chat.demo03;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

/**
 * 服务器，为每一个客户端创建一个线程
 * 写出数据：
 * 读取数据：
 * <p>
 * 输入流与输出流在同一线程内 应该彼此独立，使用两个不同得线程操作
 */
public class Server {
    private List<MyChanel> list = new ArrayList<MyChanel>();

    public static void main(String[] args) throws IOException {
        new Server().start();
    }

    private void start() throws IOException{
        ServerSocket server = new ServerSocket(8888);
        while (true) {
            Socket accept = server.accept();
            MyChanel myChanel = new MyChanel(accept);
            list.add(myChanel);
            new Thread(myChanel).start();

        }
    }

    /**
     * 抽取senf和resive方法，用多线程来完成聊天室得服务端
     */
    private class MyChanel implements Runnable {
        private DataOutputStream dos = null;
        private DataInputStream dis = null;
        private boolean isRunning = true;

        public MyChanel(Socket accept) {
            try {
                dos = new DataOutputStream(accept.getOutputStream());
                dis = new DataInputStream(accept.getInputStream());
            } catch (IOException e) {
                CloseUtils.closeAll(dos, dis);
                isRunning = false;
            }
        }

        private String receive() {
            String msg = null;
            try {
                msg = dis.readUTF();
            } catch (IOException e) {
                CloseUtils.closeAll(dis);
                isRunning = false;
                list.remove(this);
            }
            return msg;
        }

        private void send(String msg) {
            if (null != msg && !"".equals(msg)) {
                try {
                    dos.writeUTF(msg);
                    dos.flush();
                } catch (IOException e) {
                    CloseUtils.closeAll(dos);
                    isRunning = false;
                    list.remove(this);
                }
            }
        }

        private void sendOthers() {
            String msg = this.receive();//获得本身得消息
            for (MyChanel other : list) {
                if (other != this) {
                    other.send(msg);//将本身发的消息发送给其他管道得人
                }
            }
        }

        public void run() {
            while (isRunning) {
                sendOthers();
            }
        }
    }
}
