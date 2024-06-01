package rain.net.udp;

import rain.net.IP;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.MulticastSocket;
import java.net.SocketException;

/**
 * UDP协议：用户数据报协议
 * UDP是面向无连接通信协议
 * 速度快，有大小限制，一次最多发送64K，数据不安全，容易丢失数据
 * 应用场景：在线会议，语音通话，视频聊天，在线视频
 * UDP的三种通信方式：单播，组播，广播
 * 组播地址：224.0.0.0-239.255.255.255，其中224.0.0.0-224.0.0.255为预留的组播地址
 * 组播的发送端和接收端的ip要一致
 * 广播地址：255.255.255.255
 */
public class DataSend {
    private byte[] bytes;
    private IP ip;
    private int port;
    private DatagramPacket dp;
    private DatagramSocket ds;
    private MulticastSocket ms;

    /**
     * @param bytes 发送的数据
     * @param ip    发送数据的ip地址
     * @param port  端口
     */
    public DataSend(byte[] bytes, IP ip, int port) {
        this.bytes = bytes;
        this.ip = ip;
        this.port = port;
        try {
            ds = new DatagramSocket();
        } catch (SocketException e) {
            throw new RuntimeException(e);
        }
        dp = new DatagramPacket(bytes, bytes.length, ip.getAddress(), port);
    }

    /**
     * @param ip   地址
     * @param port 端口
     * @param n    0是单播，1是组播
     */
    public DataSend(IP ip, int port, int n) {
        this.ip = ip;
        this.port = port;
        switch (n) {
            case 0:
                try {
                    ds = new DatagramSocket();
                } catch (SocketException e) {
                    throw new RuntimeException(e);
                }
                break;
            case 1:
                try {
                    ms = new MulticastSocket();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                break;
        }

    }

    public DatagramPacket getDp() {
        return dp;
    }

    /**
     * 发送数据
     */
    public void send() {
        try {
            ds.send(dp);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 单播形式发送的字符串
     *
     * @param st 发送的字符串
     */
    public void send(String st) {
        byte[] bytes = st.getBytes();
        try {
            dp = new DatagramPacket(bytes, bytes.length, ip.getAddress(), port);
            ds.send(dp);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void close() {
        if (ds != null) {
            ds.close();
        } else if (ms != null) {
            ms.close();
        }
    }

    /**
     * 组播发送的数据
     *
     * @param bytes 组播发送的数据
     */
    public void sendMulticast(byte[] bytes) {
        try {
            ms.send(new DatagramPacket(bytes, bytes.length, ip.getAddress(), port));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
