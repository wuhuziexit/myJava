package rain.net.tcp;

import java.io.*;
import java.net.Socket;
import java.nio.file.Files;

/**
 * TCP协议：传输控制协议TCP
 * TCP协议是面向连接的通信协议
 * 速度慢，没有大小限制，数据安全
 * 客户端在创建对象的同时会连接服务器，如果连接不上，则会报错
 */
/*客户端*/
public class DataSocket {
    private Socket socket;
    private String host;
    private int port;

    /**
     * 与服务器host在端口port创建连接
     *
     * @param host 连接对象的主机地址或名称
     * @param port 连接的端口
     */
    public DataSocket(String host, int port) {
        this.host = host;
        this.port = port;
        try {
            socket = new Socket(host, port);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 发送字节数组
     *
     * @param bytes 要发送的字节
     */
    public void sendString(byte[] bytes) {
        try {
            socket.getOutputStream().write(bytes);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public String receiveString() {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            return br.readLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void sendFile(String path) {
        File file = new File(path);
        try {
            BufferedInputStream bis = new BufferedInputStream(Files.newInputStream(file.toPath()));
            BufferedOutputStream bos = new BufferedOutputStream(socket.getOutputStream());
            byte[] bytes = new byte[1024];
            int len;
            while ((len = bis.read(bytes)) != -1) {
                bos.write(bytes, 0, len);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void sendFile(String path, int n) {
        File file = new File(path);
        try {
            BufferedInputStream bis = new BufferedInputStream(Files.newInputStream(file.toPath()));
            BufferedOutputStream bos = new BufferedOutputStream(socket.getOutputStream());
            byte[] bytes = new byte[n];
            int len;
            while ((len = bis.read(bytes)) != -1) {
                bos.write(bytes, 0, len);
            }
            bos.close();
            bis.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 关闭流与连接
     */
    public void close() {
        try {
            socket.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
