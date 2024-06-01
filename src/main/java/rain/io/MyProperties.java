package rain.io;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;

public class MyProperties extends Properties {
    /**
     * 从项目的资源目录加载一个.properties属性文件，并将其内容加载到一个 Properties 对象中
     *
     * @param propertiesFilePath 属性文件的路径，相对于classpath
     * @throws IOException 如果无法加载资源文件
     */
    public void loadResourcePropertiesFile(String propertiesFilePath) {
        try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream(propertiesFilePath)) {
            if (inputStream == null) {
                throw new IOException("无法找到资源文件: " + propertiesFilePath);
            }
            load(inputStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 将Properties对象的内容存储到指定的文件路径中
     *
     * @param filePath       要写入的文件路径
     * @param commentsHeader 文件的注释头部
     */
    public void store(String filePath, String commentsHeader) {
        try (OutputStream outputStream = Files.newOutputStream(Paths.get(filePath))) {
            store(outputStream, commentsHeader);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 将Properties对象的内容存储到指定的文件路径中
     *
     * @param filePath 要写入的文件路径
     */
    public void store(String filePath) {
        try (OutputStream outputStream = Files.newOutputStream(Paths.get(filePath))) {
            store(outputStream, null);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 获取字符串
     *
     * @param key 键值对
     * @return key键表示的值
     */
    public String getString(String key) {
        return (String) super.get(key);
    }

    /**
     * 获取数字
     *
     * @param key 键值对
     * @return key键表示的值
     */
    public int getInt(String key) {
        return Integer.parseInt(getString(key));
    }
}
