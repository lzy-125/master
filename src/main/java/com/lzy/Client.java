package com.lzy;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import java.util.Random;
import java.util.Scanner;

public class Client {
    // 定义检测SocketChannel的Selector对象
    private Selector selector = null;
    // 客户端SocketChannel
    private SocketChannel sc = null;

    public void init() throws IOException {
        selector = Selector.open();
        InetSocketAddress isa = new InetSocketAddress("127.0.0.1", 9999);
        // 调用open静态方法创建连接到指定主机的SocketChannel
        sc = SocketChannel.open(isa);
        // 设置该sc以非阻塞方式工作
        sc.configureBlocking(false);
        // 将SocketChannel对象注册到指定Selector
        int op = SelectionKey.OP_READ | SelectionKey.OP_WRITE;
        sc.register(selector, op);
        // 启动读取服务器端数据的线server.setVisible(true);程
        new ClientThread().start();
        // 创建键盘输入流
        Scanner scan = new Scanner(System.in);
        while (scan.hasNextLine()) {
            // 读取键盘输入
            String line = scan.nextLine();
            // 将键盘输入的内容输出到SocketChannel中
            sc.write(StandardCharsets.UTF_8.encode(line));
        }
    }

    // 定义读取服务器数据的线程
    private class ClientThread extends Thread {
        public void run() {
            try {
                while (selector.select() > 0) {
                    // 遍历每个有可用IO操作Channel对应的SelectionKey
                    for (SelectionKey sk : selector.selectedKeys()) {
                        // 删除正在处理的SelectionKey
                        selector.selectedKeys().remove(sk);
                        // 如果该SelectionKey对应的Channel中有可读的数据
                        if (sk.isWritable()) {
                            Toolkit tk = Toolkit.getDefaultToolkit();
                            tk.sync();
                            // 创建屏幕高与宽区域
                            Dimension dem = tk.getScreenSize();
                            Rectangle ecran = new Rectangle(15, 170, dem.width - 30, dem.height
                                    - (170 + 140));
                            Robot robot = null;
                            robot = new Robot();
                            robot.setAutoDelay(0);
                            robot.setAutoWaitForIdle(false);
                            BufferedImage image = robot.createScreenCapture(ecran);

                            Random random = new Random();
                            int n = 26;
                            int b = random.nextInt(n) + 65;
                            String str = String.valueOf((char) b);
                            //将图片保存在xx文件夹里面
                            File file = new File("/home/andilylzy/xx/" + str + ".jpg");
                            javax.imageio.ImageIO.write(image, "jpg", file);
                        }
                    }
                }
            } catch (IOException | AWTException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws IOException {
        new Client().init();
    }
}
