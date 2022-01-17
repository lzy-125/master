package com.lzy;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;


public class Server extends JFrame{
    ArrayList arrayList = new ArrayList();
    HashMap hashMap = new HashMap();
    public static String[] id = new String[10];
    public int i = 0 ;
    // 用于检测所有Channel状态的Selector
    private Selector selector = null;

    public void init() throws IOException {
        selector = Selector.open();
        // 通过open方法来打开一个未绑定的ServerSocketChannel实例
        ServerSocketChannel server = ServerSocketChannel.open();
        InetSocketAddress isa = new InetSocketAddress("127.0.0.1", 9999);
        // 将该ServerSocketChannel绑定到指定IP地址
        server.socket().bind(isa);
        // 设置ServerSocket以非阻塞方式工作
        server.configureBlocking(false);
        // 将server注册到指定Selector对象
        server.register(selector, SelectionKey.OP_ACCEPT);
        while (selector.select() > 0) {
            // 依次处理selector上的每个已选择的SelectionKey
            for (SelectionKey sk : selector.selectedKeys()) {
                // 从selector上的已选择Key集中删除正在处理的SelectionKey
                selector.selectedKeys().remove(sk);
                // 如果sk对应的通道包含客户端的连接请求
                if (sk.isAcceptable()) {
                    // 调用accept方法接受连接，产生服务器端对应的SocketChannel
                    SocketChannel sc = server.accept();
                    // 设置采用非阻塞模式
                    sc.configureBlocking(false);
                    // 将该SocketChannel也注册到selector
                    sc.register(selector, SelectionKey.OP_READ);
                    System.out.println(sc.getRemoteAddress() + "上线！");

                    String str = sc.getRemoteAddress().toString();
//                    id[i] = str;
                    arrayList.add(i,str);
//                    hashMap.put(str,i);
                    System.out.println(arrayList);
                    i++;

                }
                // 如果sk对应的通道有数据需要读取
                if (sk.isReadable()) {
                    // 获取该SelectionKey对应的Channel，该Channel中有可读的数据
                    SocketChannel sc = (SocketChannel) sk.channel();
                    // 定义准备执行读取数据的ByteBuffer
                    ByteBuffer buff = ByteBuffer.allocate(1024);

                    StringBuilder content = new StringBuilder();
                    // 开始读取数据
                    try {
                        //写buffer
                        while (sc.read(buff) > 0) {
                            buff.flip();
                            content.append(StandardCharsets.UTF_8.decode(buff));

                            if (content.toString().equals("最大化")){
                                this.setExtendedState(JFrame.MAXIMIZED_BOTH);
                            }
                            if (content.toString().equals("最小化")){
                                this.setExtendedState(JFrame.ICONIFIED);
                            }
                            System.out.println("======" + content);
                        }
                        // 打印从该sk对应的Channel里读取到的数据

                    }// 如果捕捉到该sk对应的Channel出现了异常，即表明该Channel
                    // 对应的Client出现了问题，所以从Selector中取消sk的注册
                    catch (IOException ex) {
                        // 从Selector中删除指定的SelectionKey
                        sk.cancel();
                        if (sk.channel() != null) {
                            System.out.println(sc.getRemoteAddress() + "下线！");

                            arrayList.remove(sc.getRemoteAddress().toString());

                            System.out.println(arrayList);
                            sk.channel().close();
                        }

                    }
                    // 如果content的长度大于0，即聊天信息不为空
                    if (content.length() > 0) {
                        // 遍历该selector里注册的所有SelectKey
                        for (SelectionKey key : selector.keys()) {
                            // 获取该key对应的Channel
                            Channel targetChannel = key.channel();
                            // 如果该channel是SocketChannel对象
                            if (targetChannel instanceof SocketChannel) {
                                // 将读到的内容写入该Channel中
                                SocketChannel dest = (SocketChannel) targetChannel;
                                dest.write(StandardCharsets.UTF_8.encode(content.toString()));
                            }
                        }
                    }
                }
            }
        }
    }
//    public final static String TAG = "ProgrameTest";


    public Server() {
        initComponent();
    }
    private void initComponent() {

// 三个按钮
        JButton btnCn = new JButton("显示在线机器");
// 中间内容框
        final JTextArea jtaCont = new JTextArea();
// 滚动面板
        JScrollPane jspText = new JScrollPane();
        /*
         * 将内容框加到滚动面板
         * 当超出框大小，会出现滚动条
         */
        jspText.getViewport().add(jtaCont);
// 给按钮增加事件
        btnCn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
// 获取原有的内容
                String str = jtaCont.getText();
                for (int i = arrayList.size()-1; i>=0 ; i--) {
                    str += arrayList.get(i)+"\n";
                }
// 将新字符串设到内容框
                jtaCont.setText(str);
            }
        });
        /* 给窗体设置BorderLayout布局 */
        setLayout(new BorderLayout());
// 把中文按钮加到上面(北面)
        add(btnCn, BorderLayout.NORTH);
// 把内容框加到中间
        add(jspText, BorderLayout.CENTER);
// 窗体自适应大小
        setSize(400, 400);
// 设置关闭后的操作
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    public static void main(String[] args) throws IOException {
      Server server = new Server();
      server.setVisible(true);
      server.init();

    }
}
