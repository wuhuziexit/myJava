package rain.tool;

import rain.io.RainFile;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BooksTool {
    /**
     * 查找章节的规则，正则表达式
     */
    private final String findChapterRegex = "第.{1,6}章.{0,18}";
    /**
     * 查找作者的规则，正则表达式
     */
    private final String findAuthorRegex = "作者：.{1,10}";
    private String LatestChapter;

    /**
     * 清除指定字符串
     *
     * @param substringToRemove 指定字符串集合
     * @param n                 数量
     * @param f1                待清除文件
     * @param f2                清除后的文件
     */
    public void removeSubstring(String substringToRemove, int n, RainFile f1, RainFile f2) {
        String s = f1.readAllText();
        for (int i = 0; i < n; i++) {
            s = s.replace(substringToRemove + i, "");
        }
        f2.writeAllText(s, f1.getCharsetName());
    }

    /**
     * 清除指定字符串集合
     *
     * @param substringToRemove 指定字符串集合
     * @param f1                待清除文件
     * @param f2                清除后的文件
     */
    public void removeSubstring(ArrayList<String> substringToRemove, RainFile f1, RainFile f2) {
        String s = f1.readAllText();
        for (String string : substringToRemove) {
            s = s.replace(string, "");
        }
        f2.writeAllText(s, f1.getCharsetName());
    }

    /**
     * 清除空白行和缩进
     *
     * @param f1 待清除
     * @param f2 清除后
     */
    public void trimBlankLinesAndIndentation(RainFile f1, RainFile f2) {
        ArrayList<String> strings = f1.readFileTextArrayList();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < strings.size(); i++) {
            String s = strings.get(i);
            if (s.startsWith("　")) {
                s = s.replace("　", "");
            }
            s = s.trim();
            if (!s.isEmpty()) {
                sb.append(s);
                if (i != strings.size() - 1) sb.append(System.lineSeparator());
            }
        }
        f2.writeAllText(sb.toString(), f1.getCharsetName());
    }

    /**
     * 查找文本与规则里匹配的章节字串，查找最新章节
     *
     * @return 最新章节
     */
    public String findLatestChapter() {
        return LatestChapter;
    }

    /**
     * 获取书籍的章节集合
     *
     * @param text 书籍全部内容
     * @return 章节集合
     */
    public ArrayList<String> getBookChapters(String text) {
        LatestChapter = "";
        ArrayList<String> chapters = new ArrayList<>();
        Pattern pattern = Pattern.compile(findChapterRegex);
        Matcher matcher = pattern.matcher(text);
        while (matcher.find()) {
            LatestChapter = matcher.group();
            chapters.add(matcher.group());
        }
        return chapters;
    }

    /**
     * 获取书籍作者
     *
     * @param f 书籍文本文件
     * @return 书籍作者
     */
    public String getBookAuthor(RainFile f) {
        String charsetName = f.getCharsetName();
        StringBuilder sb = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(f), charsetName))) {
            String line;
            for (int i = 0; i < 10; i++) {
                line = br.readLine();
                if (line == null) {
                    return "null";
                } else if (line.contains("作者：") && line.length() <= 200) {
                    return line.substring(line.indexOf("：") + 1);
                }
            }
        } catch (IOException e) {
            System.err.println("获取作者名错误");
        }
        return "null";
    }

    /**
     * 获取该章节规则下的文本内容集合
     *
     * @param text 文本内容
     * @return 以章节划分的文本内容合集
     */
    public String[] getBookContents(String text) {
        return Pattern.compile(findChapterRegex).split(text);
    }
}
