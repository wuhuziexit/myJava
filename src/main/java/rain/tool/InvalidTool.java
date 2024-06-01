package rain.tool;

import java.time.LocalDate;
import java.util.Random;
import java.util.Scanner;

/**
 * 该类记录了一些奇奇怪怪的不知道有没有用处的方法
 */
public class InvalidTool {
    public final int ARRAY_TYPE_DOUBLE = 0;

    /**
     * 一个银行小系统
     */
    public void bankingSystem() {
        Scanner sc = new Scanner(System.in);
        System.out.println("欢迎光临招商银行！！！");
        System.out.println("您在本银行中没有相应的账号，请输入用户名和密码进行开户");
        System.out.print("请输入用户名：");
        String username = sc.next();
        System.out.print("请输入6位数字密码：");
        int password1 = sc.nextInt();
        while (String.valueOf(password1).length() != 6) {
            System.err.print("您输入的或许不是6位数，请重新输入6位数字密码：");
            password1 = sc.nextInt();
        }
        System.out.print("请再次输入密码：");
        int password2 = sc.nextInt();
        while (password1 != password2) {
            System.err.print("您输入的密码不一致，请重新输入：");
            password2 = sc.nextInt();
        }
        System.out.print("请输入初次存储的金额(如不存储则输入0)：");
        double dol = sc.nextDouble();
        System.out.println(username + "开户成功，账户余额：" + dol);
        System.out.println("\n欢迎使用银行自助系统，请选择您要进行的操作");
        System.out.println("[0]查询余额  [1]存款  [2]取款  [3]退出");
        System.out.print("请选择：");
        int i = sc.nextInt();
        while (i != 3) {
            System.out.print("请输入密码：");
            int password = sc.nextInt();
            if (password == password1) {
                if (i == 0) {
                    System.out.println("您的账户余额为：" + dol);
                } else if (i == 1) {
                    System.out.print("请输入存储的金额：");
                    double dolT = sc.nextDouble();
                    dol = dol + dolT;
                    System.out.println("成功存储" + dolT + "元，当前余额为" + dol + "元");
                } else if (i == 2) {
                    System.out.print("请输入取走的金额：");
                    double dolT2 = sc.nextDouble();
                    if (dolT2 > dol) {
                        System.err.println("您的账户余额不足，取款失败");
                    } else {
                        dol = dol - dolT2;
                        System.out.println("成功取出" + dolT2 + "元，当前余额为" + dol + "元");
                    }
                }
            } else {
                System.err.println("密码错误");
            }
            System.out.println("[0]查询余额  [1]存款  [2]取款  [3]退出");
            System.out.print("请选择：");
            i = sc.nextInt();
        }
        System.out.println("成功退出");
        System.out.println("请携带好随身物品，欢迎下次光临");
    }

    /**
     * 返回一个随机数组
     *
     * @param len 数组长
     * @return double数组
     */
    public double[] arrayGeneration(int len) {
        int max = 100;
        Random r = new Random();
        double[] vs = new double[len];
        for (int i = 0; i < vs.length; i++) {
            vs[i] = r.nextDouble();
        }
        return vs;
    }

    /**
     * 输入十个数存入一维数组中，将这十个数存放的顺序首尾交换后，再按顺序输出这十个数
     *
     * @return 交换后的数组
     * @since 1.0
     */
    public double[] arraySequentialExchange(double[] vs) {
//        输入十个数存入一维数组中，将这十个数存放的顺序首尾交换后，再按顺序输出这十个数。
        double temp;
        for (int i = 0; i < vs.length / 2; i++) {
            temp = vs[vs.length - 1 - i];
            vs[vs.length - 1 - i] = vs[i];
            vs[i] = temp;
        }
        return vs;
    }

    /**
     * 判断出生年月日是什么星座
     *
     * @param y 年
     * @param m 月
     * @param d 日
     */
    public void findZodiacByBirthdate(int y, int m, int d) {
        if (IsYMD(y, m, d)) {
            switch (m) {
                case 1:
                    if (d >= 20) {
                        System.out.println("您是水瓶座！");
                    } else System.out.println("您是摩羯座！");
                    break;
                case 2:
                    if (d >= 19) {
                        System.out.println("您是双鱼座！");
                    } else System.out.println("您是水瓶座！");
                    break;
                case 3:
                    if (d >= 21) {
                        System.out.println("您是白羊座！");
                    } else System.out.println("您是双鱼座！");
                    break;
                case 4:
                    if (d >= 20) {
                        System.out.println("您是金牛座！");
                    } else System.out.println("您是白羊座！");
                    break;
                case 5:
                    if (d >= 21) {
                        System.out.println("您是双子座！");
                    } else System.out.println("您是金牛座！");
                    break;
                case 6:
                    if (d >= 22) {
                        System.out.println("您是巨蟹座！");
                    } else System.out.println("您是双子座！");
                    break;
                case 7:
                    if (d >= 23) {
                        System.out.println("您是狮子座！");
                    } else System.out.println("您是巨蟹座！");
                    break;
                case 8:
                    if (d >= 23) {
                        System.out.println("您是处女座！");
                    } else System.out.println("您是狮子座！");
                    break;
                case 9:
                    if (d >= 23) {
                        System.out.println("您是天秤座！");
                    } else System.out.println("您是处女座！");
                    break;
                case 10:
                    if (d >= 24) {
                        System.out.println("您是天蝎座！");
                    } else System.out.println("您是天秤座！");
                    break;
                case 11:
                    if (d >= 23) {
                        System.out.println("您是射手座！");
                    } else System.out.println("您是天蝎座！");
                    break;
                case 12:
                    if (d >= 22) {
                        System.out.println("您是魔蝎座！");
                    } else System.out.println("您是射手座！");
                    break;
            }
        }
    }

    /**
     * 判断一个 年月日 是否正常
     *
     * @param year  年
     * @param month 月
     * @param day   日
     * @return 是否正常
     */
    public boolean IsYMD(int year, int month, int day) {
        if (year < 1900 || year > LocalDate.now().getYear()) {
            System.err.println("年份不合理");
            return false; // 年份不合理
        }
        if (month < 1 || month > 12) {
            System.err.println("月份不合理");
            return false; // 月份不合理
        }
        int maxDay = 31;
        switch (month) {
            case 4:
            case 6:
            case 9:
            case 11:
                maxDay = 30;
                break;
            case 2:
                if ((year % 4 == 0 && year % 100 != 0) || year % 400 == 0) {
                    maxDay = 29; // 闰年2月有29天
                } else {
                    maxDay = 28; // 非闰年2月有28天
                }
                break;
        }
        if (!(day >= 1 && day <= maxDay)) {
            System.err.println("天数不合理");
        }
        return day >= 1 && day <= maxDay;
    }
}
