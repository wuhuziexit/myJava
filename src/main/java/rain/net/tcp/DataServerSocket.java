package rain.net.tcp;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.file.Files;

/**
 * TCP协议：传输控制协议TCP
 * TCP协议是面向连接的通信协议
 * 速度慢，没有大小限制，数据安全
 * 客户端在创建对象的同时会连接服务器，如果连接不上，则会报错
 */
/*接收端，服务端*/
public class DataServerSocket {
    private ServerSocket serverSocket;
    private int port;
    private Socket socket;

    /**
     * 服务器端
     * 从port端口接收数据
     *
     * @param port 端口
     */
    public DataServerSocket(int port) {
        this.port = port;
        try {
            serverSocket = new ServerSocket(port);
            socket = serverSocket.accept();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void sendString(String st) {
        try {
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            bw.write(st);
            bw.newLine();
            bw.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 获取客户端发送过来的字符串
     *
     * @return 取得的字符串
     */
    public String receiveString() {
        StringBuilder sb = new StringBuilder();
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            int b;
            while ((b = br.read()) != -1) {
                sb.append((char) b);
            }
            br.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return sb.toString();
    }

    public void receiveFile(String filePath) {
        File file = new File(filePath);
        try {
            BufferedInputStream bis = new BufferedInputStream(socket.getInputStream());
            BufferedOutputStream bos = new BufferedOutputStream(Files.newOutputStream(file.toPath()));
            int len;
            byte[] bytes = new byte[1024];
            System.out.println("开始");
            while ((len = bis.read(bytes)) != -1) {
                System.out.println("读取：" + new String(bytes));
                bos.write(bytes, 0, len);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public ServerSocket getServerSocket() {
        return serverSocket;
    }

    public int getPort() {
        return port;
    }

    public Socket getSocket() {
        return socket;
    }

    /**
     * 关闭服务器
     */
    public void close() {
        try {
            socket.close();
            serverSocket.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
