package rain.tool;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Time {
    /**
     * 传入毫秒值，计算对应毫秒值代表的值
     *
     * @param milliseconds 毫秒值
     * @return %d天%d小时%d分钟%d秒
     */
    public static String convertMilliseconds(long milliseconds) {
        long days = milliseconds / (24 * 60 * 60 * 1000);
        long hours = (milliseconds % (24 * 60 * 60 * 1000)) / (60 * 60 * 1000);
        long minutes = (milliseconds % (60 * 60 * 1000)) / (60 * 1000);
        long seconds = (milliseconds % (60 * 1000)) / 1000;
        return String.format("%d天%d小时%d分钟%d秒", days, hours, minutes, seconds);
    }

    /**
     * 传入System.currentTimeMillis()获取的毫秒值，获取年月日等
     *
     * @param currentTimeMillis System.currentTimeMillis()获取的毫秒值
     * @return 年月日等
     */
    public String formattedDateTime(long currentTimeMillis) {
        // 将毫秒值转化为LocalDateTime对象
        LocalDateTime localDateTime = LocalDateTime.ofInstant(
                java.time.Instant.ofEpochMilli(currentTimeMillis),
                java.time.ZoneId.systemDefault());
        System.out.println(localDateTime);
        // 定义日期时间格式
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy年MM月dd日HH时mm分ss秒");
        // 格式化日期时间
        return localDateTime.format(formatter);
    }

    /**
     * 获取传入的毫秒以秒的方式返回
     *
     * @param l 毫秒数
     * @return 秒
     */
    public String getTime(long l) {
        return (float) (System.currentTimeMillis() - l) / 1000 + "秒";
    }

    /**
     * 获取传入毫秒与当前毫秒的差字符串值
     *
     * @param l 毫秒值
     * @return 毫秒差
     */
    public String getMsecTime(long l) {
        return (float) (System.currentTimeMillis() - l) + "毫秒";
    }

    /**
     * 获取当前毫秒值
     *
     * @return 当前毫秒值
     */
    public long getNowTime() {
        return System.currentTimeMillis();
    }
}
