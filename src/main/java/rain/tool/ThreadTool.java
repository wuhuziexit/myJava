package rain.tool;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

/**
 * 记录一些线程有关的方法
 */
public final class ThreadTool {
    /**
     * 卖票
     */
    public void MP() {
        SellMovieTickets m = new SellMovieTickets();
        m.setName("窗口1");
        SellMovieTickets m1 = new SellMovieTickets();
        m1.setName("窗口2");
        m.start();
        m1.start();
    }

    /**
     * 线程状态
     */
    public void ZT() {
        // 创建并设置JFrame
        JFrame frame = new JFrame("线程状态");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);


        JLabel label = new JLabel("1");
        label.setHorizontalAlignment(JLabel.CENTER); // 设置标签文本居中

        // 创建按钮
        JButton button1 = new JButton("开始");
        JButton button2 = new JButton("挂起");
        JButton button3 = new JButton("恢复");
        JButton button4 = new JButton("停止");
        JButton button5 = new JButton("状态查询");

        JPanel panel1 = new JPanel();

        panel1.add(label);
        panel1.add(button1);
        panel1.add(button2);
        panel1.add(button3);
        panel1.add(button4);
        panel1.add(button5);

        frame.add(panel1);
        // 设置窗口可见
        frame.setVisible(true);


        Thread thread = new Thread(new TS(label));
        System.out.println("线程创建后，启动前：" + thread.getState());
        button1.addActionListener(e -> {
            thread.start();
            System.out.println("线程刚启动：" + thread.getState());
        });
        button2.addActionListener(e -> {
            TS.setFlag(false);
            System.out.println("线程挂起：" + thread.getState());
        });
        button3.addActionListener(e -> {
            synchronized (TS.obj) {
                TS.setFlag(true);
                TS.obj.notify();
            }
            System.out.println("线程恢复：" + thread.getState());
        });
        button4.addActionListener(e -> {
            TS.setIsStop(true);
        });
        button5.addActionListener(e -> {
            System.out.println("线程当前状态：" + thread.getState());
        });
    }

    /**
     * 龟兔赛跑
     */
    public void GTRun() {
        class GT extends Thread {
            int n = 100;

            @Override
            public void run() {
                while (n != 0) {
                    n--;
                    if (n % 10 == 0) {
                        System.out.println(getName() + "跑完10米，现剩余" + n + "米");
                    }
                }
                System.out.println(getName() + "跑完了！！！\n");
            }
        }
        GT gt1 = new GT();
        gt1.setName("乌龟");
        GT gt2 = new GT();
        gt2.setName("兔子");
        gt1.start();
        gt2.start();
    }

    /**
     * 三条水果生产线
     */
    public void fruitProductionLine() {
        class SG extends Thread {
            String type;

            public SG(String type) {
                this.type = type;
            }

            @Override
            public void run() {
                do {
                    Random random = new Random();
                    int i = random.nextInt(11000);
                    try {
                        Thread.sleep((long) i);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    System.out.println(getName() + "：经过" + (long) i / 1000 + "秒生产5个" + type);
                } while (true);
            }
        }
        SG sg1 = new SG("桃子");
        sg1.setName("生产线1");
        SG sg2 = new SG("香蕉");
        sg2.setName("生产线2");
        SG sg3 = new SG("苹果");
        sg3.setName("生产线3");

        sg1.start();
        sg2.start();
        sg3.start();
    }

    /**
     * 循环打印数字
     */
    public void printNum() {
        class Ts extends Thread {
            int n;

            public Ts(int n) {
                this.n = n;
            }

            @Override
            public void run() {
                do {
                    System.out.println(n++);
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                } while (n != 1000);
            }
        }
        Thread thread = new Thread(new Ts(0));
        thread.start();
    }

    /**
     * 一个字一个字打印
     */
    public void printCharAt() {
        class ZM extends Thread {
            private String st;

            public ZM(String st) {
                this.st = st;
            }

            @Override
            public void run() {
                String[] split = st.split("\n");
                for (int i = 0; i < split.length; i++) {
                    try {
                        for (int j = 0; j < split[i].length(); j++) {
                            System.out.print(split[i].charAt(j));
                            switch (i) {
                                case 0:
                                    Thread.sleep(200);
                                    break;
                                case 1:
                                    Thread.sleep(400);
                                    break;
                                case 2:
                                    Thread.sleep(600);
                                    break;
                                default:
                                    Thread.sleep(800);
                            }
                        }
                        Thread.sleep(3000);
                        System.out.println();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }
        String st = "我的世界是一个非常好的游戏\n使我的史蒂夫旋转\n爱来自魔法女巫";
        ZM zm = new ZM(st);
        zm.start();
    }

    /**
     * Runnable接口的龟兔赛跑
     */
    public void GTRunR() {
        class GT implements Runnable {
            int n = 100;

            @Override
            public void run() {
                while (n != 0) {
                    n--;
                    if (n % 10 == 0) {
                        System.out.println(Thread.currentThread().getName() + "跑完10米，现剩余" + n + "米");
                    }
                }
                System.out.println(Thread.currentThread().getName() + "跑完了！！！\n");
            }
        }
        Thread gt1 = new Thread(new GT());
        Thread gt2 = new Thread(new GT());

        gt1.setName("乌龟");
        gt2.setName("兔子");

        gt1.start();
        gt2.start();
    }

    /**
     * 一个图片跑动的界面
     *
     * @param imgPath 图片文件路径
     */
    public void SWImgRun(String imgPath) {
        JFrame jf = new JFrame();
        jf.setSize(400, 200);//		框架大小
        jf.setTitle("");//设置框架标题
//		jf1.setAlwaysOnTop(true);//		界面置顶
        jf.setLocationRelativeTo(null);//		界面居中
        jf.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);//		界面关闭
        jf.setLayout(null);//		取消默认的居中方式

        JLabel jl = new JLabel(new ImageIcon(imgPath));
        final int[] x = {36};
        jl.setBounds(x[0], 36, 36, 36);
        jf.add(jl);
        Thread thread = new Thread(() -> {
            boolean b = true;
            while (true) {
                if (x[0] == 400) {
                    b = false;
                } else if (x[0] == 0) {
                    b = true;
                }
                if (b) {
                    x[0] = x[0] + 1;
                } else x[0] = x[0] - 1;
                jl.setBounds(x[0], 10, 20, 20);
                jf.getContentPane().repaint();
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        jf.setVisible(true);
        thread.start();
    }

    /**
     * 文本改变颜色的界面
     */
    public void SWTextColor() {
        JFrame jf = new JFrame();
        jf.setSize(400, 200);
        jf.setLocationRelativeTo(null);
        jf.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        jf.setLayout(null);
        JLabel jl = new JLabel("这是一个花花绿绿的广告牌");
        jl.setBounds(100, 45, 200, 20);
        jl.setOpaque(true);
        Random r = new Random();
        jl.setBackground(new Color(r.nextInt(256), r.nextInt(256), r.nextInt(256)));
        jf.add(jl);
        Thread thread = new Thread(() -> {
            while (true) {
                jl.setBackground(new Color(r.nextInt(256), r.nextInt(256), r.nextInt(256)));
                jf.getContentPane().repaint();
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }

        });
        thread.start();
        jf.setVisible(true);
    }

    /**
     * 随机数组
     */
    public void SJ() {
        JFrame jf = new JFrame();
        jf.setSize(400, 200);
        jf.setLocationRelativeTo(null);
        jf.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        jf.setLayout(null);
        String[] strings1 = {"张三", "李四", "王五"};
        String[] strings2 = {"在美国", "在教室", "在中国"};
        String[] strings3 = {"修炼", "睡觉", "玩游戏"};
        JLabel jl = new JLabel(strings1[0] + strings2[0] + strings3[0]);
        jl.setBounds(100, 45, 200, 20);
        jl.setOpaque(true);
        Random r = new Random();
        jf.add(jl);
        Thread thread = new Thread(() -> {
            while (true) {
                jl.setText(strings1[r.nextInt(strings1.length)] + strings2[r.nextInt(strings2.length)] + strings3[r.nextInt(strings3.length)]);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }

        });
        thread.start();
        jf.setVisible(true);
    }

    /**
     * 介绍
     */
    public void JS() {
        JFrame jf = new JFrame();
        jf.setSize(400, 200);
        jf.setLocationRelativeTo(null);
        jf.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        jf.setLayout(null);
        String[] strings = {"好吃就在九龙街", "好玩就在庐山", "好喝就要百事可乐"};
        JLabel jl = new JLabel("九江介绍：" + strings[0]);
        jl.setBounds(100, 45, 200, 20);
        jl.setOpaque(true);
        Random r = new Random();
        jl.setBackground(new Color(r.nextInt(256), r.nextInt(256), r.nextInt(256)));
        jf.add(jl);
        Thread thread = new Thread(() -> {
            while (true) {
                jl.setText("九江介绍：" + strings[new Random().nextInt(strings.length)]);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }

        });
        thread.start();
        jf.setVisible(true);
    }

    /**
     * 卖票
     */
    static class SellMovieTickets extends Thread {
        static int counter = 1000;
        static Object lock = new Object();

        @Override
        public void run() {
            while (counter > 0) {
                synchronized (lock) {
                    System.out.println(getName() + "剩余 " + counter--);
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }

        }
    }

    static class TS implements Runnable {
        static final Object obj = new Object();
        static boolean flag = true;
        static boolean isStop = false;
        JLabel label;

        public TS(JLabel label) {
            this.label = label;
        }


        public static void setIsStop(boolean isStop) {
            TS.isStop = isStop;
        }

        public static void setFlag(boolean flag) {
            TS.flag = flag;
        }

        @Override
        public void run() {
            synchronized (obj) {
                while (!isStop) {
                    if (!flag) {
                        try {
                            obj.wait();
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                    label.setText(new Random().nextInt(3) + 1 + "");
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }
    }
}
