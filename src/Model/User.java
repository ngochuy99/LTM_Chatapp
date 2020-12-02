package Model;

import java.io.Serializable;

public class User implements Serializable {
    private static final long SerialVersionUID = 1l;
    private String username;
    private String password;
    private String ip;
    private int port;

    public User(String username, String password, String ip, int port) {
        this.username = username;
        this.password = password;
        this.ip = ip;
        this.port = port;
    }

    public User() {
    }

    public static long getSerialVersionUID() {
        return SerialVersionUID;
    }

    public String getUsername() {
        return username;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", ip='" + ip + '\'' +
                ", port=" + port +
                '}';
    }

    public String getPassword() {
        return password;
    }

    public String getIp() {
        return ip;
    }

    public int getPort() {
        return port;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public void setPort(int port) {
        this.port = port;
    }
}
