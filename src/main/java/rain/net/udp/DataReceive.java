package rain.net.udp;

import java.io.IOException;
import java.net.*;
/*
  端口号：应用程序在设备中的唯一标识，由两个字节标识的整数，取值范围：0-65535
  其中0-1023之间的端口用于一些知名的网络服务或者应用
  一个端口号只能被一个应用程序使用
 */
/*
 * UDP协议：用户数据报协议
 * UDP是面向无连接通信协议
 * 速度快，有大小限制，一次最多发送64K，数据不安全，容易丢失数据
 * 应用场景：在线会议，语音通话，视频聊天，在线视频
 * UDP的三种通信方式：单播，组播，广播
 * 组播地址：224.0.0.0-239.255.255.255，其中224.0.0.0-224.0.0.255为预留的组播地址
 * 组播的发送端和接收端的ip要一致
 * 广播地址：255.255.255.255
 */

/**
 * udp接收端
 */
public class DataReceive {
    /**
     * 接收数据的字节数组
     */
    private byte[] bytes = new byte[1024];
    /**
     * 接收数据的端口号
     */
    private int port;
    /**
     * 数据报包
     */
    private DatagramPacket dp;
    /**
     * 对数据进行接收
     */
    private DatagramSocket ds;
    /**
     * 多播
     */
    private MulticastSocket ms;

    /**
     * @param port 接收数据使用的端口号
     */
    public DataReceive(int port) {
        this.port = port;
        try {
            this.ds = new DatagramSocket(port);
        } catch (SocketException e) {
            throw new RuntimeException(e);
        }
        dp = new DatagramPacket(bytes, bytes.length);
        try {
            ds.receive(dp);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * @param bytes 用来接收数据的字节数组
     * @param port  端口号
     */
    public DataReceive(byte[] bytes, int port) {
        this.bytes = bytes;
        this.port = port;
        try {
            ds = new DatagramSocket(port);
        } catch (SocketException e) {
            throw new RuntimeException(e);
        }
        dp = new DatagramPacket(bytes, bytes.length);
        try {
            ds.receive(dp);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public DataReceive(int port, String host) {
        this.port = port;
        bytes = new byte[1024];
        try {
            ms = new MulticastSocket(port);
            InetAddress address = InetAddress.getByName(host);
            ms.joinGroup(address);
            dp = new DatagramPacket(bytes, bytes.length);
            ms.receive(dp);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * @return 接收到的字节数组
     */
    public byte[] getData() {
        return dp.getData();
    }

    /**
     * @return 接收到的字节数组
     */
    public String getStringData() {
        return new String(dp.getData(), 0, dp.getLength());
    }

    /**
     * @return 接收到的字节数组长度
     */
    public int getLength() {
        return dp.getLength();
    }

    /**
     * @return 从何处ip地址接收
     */
    public InetAddress getAddress() {
        return dp.getAddress();
    }

    /**
     * @return 接收端口来源
     */
    public int getPort() {
        return dp.getPort();
    }

    /**
     * 关闭流
     */
    public void close() {
        if (ds != null) {
            ds.close();
        } else if (ms != null) {
            ms.close();
        }
    }

    @Override
    public String toString() {
        return "电脑" + getAddress().getHostName() + "向端口" + getPort() + "发送了" + getLength() + "个字节，内容是：" + new String(getData(), 0, getLength());
    }
}
