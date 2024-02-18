package edu.uwp.cs.csci380.project.PA.simpleaccess_V1;

import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;

public class SshConnection {
    private String sshHost;
    private String sshUser;
    private String sshPassword;
    private int sshPort;
    private int localPort;
    private int remotePort;
    private Session session;

    /**
     * Constructor for SShConnection object
     * @param sshHost String for the sshHost name
     * @param sshUser String for username accessing the SSH
     * @param sshPassword String for the user's password
     * @param sshPort int for the SSH port
     * @param localPort int for local port
     * @param remotePort int for remote port
     */

    public SshConnection(String sshHost, String sshUser, String sshPassword, int sshPort, int localPort, int remotePort) {
        this.sshHost = sshHost;
        this.sshUser = sshUser;
        this.sshPassword = sshPassword;
        this.sshPort = sshPort;
        this.localPort = localPort;
        this.remotePort = remotePort;
    }

    /**
     * This method creates a JSch object and creates an SSH session and port forwards to the desired host
     * @throws JSchException
     */

    public void connect() throws JSchException {
        JSch jsch = new JSch();
        session = jsch.getSession(sshUser, sshHost, sshPort);
        session.setPassword(sshPassword);
        session.setConfig("StrictHostKeyChecking", "no");
        session.connect();
        session.setPortForwardingL(localPort, sshHost, remotePort);
    }

    /**
     * This method sets up port forwarding to the desired host
     * @param localPort
     * @param remoteHost
     * @param remotePort
     * @throws JSchException
     */

    public void setupPortForwarding(int localPort, String remoteHost, int remotePort) throws JSchException {
            session.setPortForwardingL(localPort, remoteHost, remotePort);
    }

    public void disconnect() {
        if (session != null && session.isConnected()) {
            session.disconnect();
        }
    }

    public String getSshHost() {
        return sshHost;
    }

    public void setSshHost(String sshHost) {
        this.sshHost = sshHost;
    }

    public String getSshUser() {
        return sshUser;
    }

    public void setSshUser(String sshUser) {
        this.sshUser = sshUser;
    }

    public String getSshPassword() {
        return sshPassword;
    }

    public void setSshPassword(String sshPassword) {
        this.sshPassword = sshPassword;
    }

    public int getSshPort() {
        return sshPort;
    }

    public void setSshPort(int sshPort) {
        this.sshPort = sshPort;
    }

    public int getLocalPort() {
        return localPort;
    }

    public void setLocalPort(int localPort) {
        this.localPort = localPort;
    }

    public int getRemotePort() {
        return remotePort;
    }

    public void setRemotePort(int remotePort) {
        this.remotePort = remotePort;
    }
}
