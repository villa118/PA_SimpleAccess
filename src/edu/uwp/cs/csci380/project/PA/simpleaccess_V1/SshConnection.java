package edu.uwp.cs.csci380.project.PA.simpleaccess_V1;

import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;

public class SshConnection {
    private String host;
    private String user;
    private String password;
    private int port;
    private Session session;

    SshConnection(String host, String user, String password, int port) {
        this.host = host;
        this.user = user;
        this.password = password;
        this.port = port;
    }

    public void connect() throws JSchException {
        JSch jsch = new JSch();
        session = jsch.getSession(user, host, port);
        session.setPassword(password);
        session.connect();
    }

    public void setupPortForwarding(int localPort, String remoteHost, int remotePort) throws JSchException {
            session.setPortForwardingL(localPort, remoteHost, remotePort);
    }

    public void disconnect() {
        if (session != null && session.isConnected()) {
            session.disconnect();
        }
    }

    public static void main(String[] args){
        SshConnection connection = new SshConnection("labs.cs.uwp.edu", "villalot","1qaz@WSX3edc$RFV", 4321);
        try {
            connection.connect();
            connection.setupPortForwarding(0, "jdbc:mysql://localhost:4321/QACS_db?characterEncoding=utf8", 4321);
        } catch (JSchException e) {
            throw new RuntimeException(e);
        }
    }

}
