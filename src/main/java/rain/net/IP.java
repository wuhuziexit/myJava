package rain.net;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class IP {

    private InetAddress address;

    /**
     * @param host 主机名或者主机ip地址
     */
    public IP(String host) {
        try {
            address = InetAddress.getByName(host);
        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * @return 主机ip地址
     */
    public String getHostAddress() {
        return address.getHostAddress();
    }

    /**
     * @return 主机名
     */
    public String getHostName() {
        return address.getHostName();
    }

    /**
     * @return ip地址
     */
    public InetAddress getAddress() {
        return address;
    }

    @Override
    public String toString() {
        return address.toString();
    }
}
