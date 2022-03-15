package com.light.springboot.controller;

import com.jcraft.jsch.*;

import java.io.*;
import java.util.Properties;

public class Sftp {
    private static Session sshSession;

    /**
     * 连接sftp服务器
     *
     * @param host     ftp地址
     * @param port     端口
     * @param userName 账号
     * @param password 密码
     * @return
     */
    public static ChannelSftp sftpConnection(String host, int port, String userName, String password) throws JSchException {
        JSch jsch = new JSch();
        jsch.addIdentity("C:/Users/wangyuling/Desktop/123/wangyuling");
        ChannelSftp channelSftp = null;
        try {
            jsch.getSession(userName, host, port);
            sshSession = jsch.getSession(userName, host, port);

            //sshSession.setPassword(password);
            Properties properties = new Properties();
            properties.put("StrictHostKeyChecking", "no");
            sshSession.setConfig(properties);
            sshSession.connect();
            Channel channel = sshSession.openChannel("sftp");
            channel.connect();
            channelSftp = (ChannelSftp) channel;
        } catch (JSchException e) {
            e.printStackTrace();
//            throw new RRException("Sftp服务器登录异常!");
        }
        return channelSftp;
    }

    /**
     * 连接sftp服务器
     *
     * @param host     ftp地址
     * @param port     端口
     * @param userName 账号
     * @param password 密码
     * @return
     */
    public static ChannelSftp sftpConnectionPsss(String host, int port, String userName, String password) throws JSchException {
        JSch jsch = new JSch();
        ChannelSftp channelSftp = null;
        try {
            jsch.getSession(userName, host, port);
            sshSession = jsch.getSession(userName, host, port);
            sshSession.setPassword(password);
            Properties properties = new Properties();
            properties.put("StrictHostKeyChecking", "no");
            sshSession.setConfig(properties);
            sshSession.connect();
            Channel channel = sshSession.openChannel("sftp");
            channel.connect();
            channelSftp = (ChannelSftp) channel;
        } catch (JSchException e) {
            e.printStackTrace();
//            throw new RRException("Sftp服务器登录异常!");
        }
        return channelSftp;
    }


    /**
     * 下载sftp文件
     *
     * @param sftp
     * @param newFileName 新文件名称
     * @param path        文件路径
     * @param fileName    文件名称
     * @param downUrl     下载到本地的路径
     * @throws Exception
     */
    public static void downSftpFile(ChannelSftp sftp, String newFileName, String path, String fileName, String downUrl) throws Exception {

        OutputStream os = null;
        try {
            File localFile = new File(downUrl + "/" + newFileName);
            if (!localFile.getParentFile().exists()) {
                localFile.getParentFile().mkdirs();
                localFile.createNewFile();
            }

            if (path != null && !"".equals(path)) {
                sftp.cd(path);//进入所在路径
            }

            os = new FileOutputStream(localFile);
            sftp.get(path + fileName, os);
            InputStream inputStream1 = sftp.get(path + fileName);
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream1);
            BufferedReader bufferedReader1 = new BufferedReader(inputStreamReader);
            String s = bufferedReader1.readLine();
            System.out.println(s);

            os.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws Exception {

        ChannelSftp easemob = Sftp.sftpConnectionPsss("111.205.128.7", 22000, "huanxin", "hx5422@GAB");

//        Sftp sftp = new Sftp();
//        sftp.sftp = Sftp.sftpConnection("120.26.6.24", 3299, "easemob", "");
//       // boolean b = sftp.uploadFile("/home/easemob/taojing/", "yu.txt", "C:/Users/wangyuling/Desktop/", "wang.txt");
//       // System.out.println(b);
//
//
        Sftp.downSftpFile(easemob, "wang.txt", "/home/easemob/taojing/", "id.txt", "C:/Users/wangyuling/Desktop");
//        sftp.disconnect();
    }


    /**
     * @return
     * @description 退出Sftp服务器登录
     **/
    public static void sftpClose(ChannelSftp channelSftp) {
        if (channelSftp != null) {
            if (channelSftp.isConnected()) {
                channelSftp.disconnect();
            }
        }
    }

    /**
     * 关闭session
     */
    public static void sessionClose() {
        if (sshSession != null) {
            if (sshSession.isConnected()) {
                sshSession.disconnect();
                sshSession = null;
            }
        }
    }


    ChannelSftp sftp = null;

    /**
     * 关闭连接
     */
    public void disconnect() {
        if (this.sftp != null) {
            if (this.sftp.isConnected()) {
                this.sftp.disconnect();
            }
        }
        if (this.sshSession != null) {
            if (this.sshSession.isConnected()) {
                this.sshSession.disconnect();

            }
        }
    }


    /**
     * 上传单个文件
     *
     * @param remotePath：远程保存目录
     * @param remoteFileName：保存文件名
     * @param localPath：本地上传目录(以路径符号结束)
     * @param localFileName：上传的文件名
     * @return
     */
    public boolean uploadFile(String remotePath, String remoteFileName, String localPath, String localFileName) {
        FileInputStream in = null;
        try {
            createDir(remotePath);
            File file = new File(localPath + localFileName);
            in = new FileInputStream(file);
            sftp.put(in, remoteFileName);
            return true;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (SftpException e) {
            e.printStackTrace();
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return false;
    }

    /**
     * 创建目录
     *
     * @param createpath
     * @return
     */
    public boolean createDir(String createpath) {
        try {
            if (isDirExist(createpath)) {
                sftp.cd(createpath);
                return true;
            }
            String pathArry[] = createpath.split("/");
            StringBuffer filePath = new StringBuffer("/");
            for (String path : pathArry) {
                if (path.equals("")) {
                    continue;
                }
                filePath.append(path + "/");
                if (isDirExist(filePath.toString())) {
                    sftp.cd(filePath.toString());
                } else {
                    // 建立目录
                    sftp.mkdir(filePath.toString());
                    // 进入并设置为当前目录
                    sftp.cd(filePath.toString());
                }

            }
            sftp.cd(createpath);
            return true;
        } catch (SftpException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 判断目录是否存在
     *
     * @param directory
     * @return
     */
    public boolean isDirExist(String directory) {
        boolean isDirExistFlag = false;
        try {
            SftpATTRS sftpATTRS = sftp.lstat(directory);
            isDirExistFlag = true;
            return sftpATTRS.isDir();
        } catch (Exception e) {
            if (e.getMessage().toLowerCase().equals("no such file")) {
                isDirExistFlag = false;
            }
        }
        return isDirExistFlag;
    }

}
