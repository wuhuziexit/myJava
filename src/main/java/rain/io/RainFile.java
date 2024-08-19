package rain.io;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

public final class RainFile extends File {
    public RainFile(String pathname) {
        super(pathname);
    }

    public RainFile(String parent, String child) {
        super(parent, child);
    }

    public RainFile(File parent, String child) {
        super(parent, child);
    }

    @Override
    public String[] list() {
        return super.list();
    }

    /**
     * 重写File类的listFiles方法
     *
     * @return 返回一个RainFile数组
     */
    public RainFile[] listFiles() {
        String[] ss = list();
        if (ss == null) return null;
        int n = ss.length;
        RainFile[] fs = new RainFile[n];
        for (int i = 0; i < n; i++) {
            fs[i] = new RainFile(this, ss[i]);
        }
        return fs;
    }

    /**
     * @return 文件的全部内容
     */
    public String readAllText() {
        try {
            StringBuilder sb = new StringBuilder();
            BufferedReader bfr = new BufferedReader(new InputStreamReader(new FileInputStream(this), getCharsetName()));
            String st;
            while ((st = bfr.readLine()) != null) {
                sb.append(st).append(System.lineSeparator());
            }
            bfr.close();
            return sb.toString();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private ArrayList<RainFile> findSearchResultList = new ArrayList<>();

    /**
     * 把字符串以charsetName的编码格式写入文件
     *
     * @param charsetName 编码
     * @param text        字符串
     */
    public void writeAllText(String text, String charsetName) {
        try {
            BufferedWriter bfw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(this), charsetName));
            bfw.write(text);
            bfw.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 把字符串写入文件
     *
     * @param text 字符串
     */
    public void writeAllText(String text) {
        try {
            BufferedWriter bfw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(this)));
            bfw.write(text);
            bfw.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 把字符串写入文件
     *
     * @param append 是否续写
     * @param text   字符串
     */
    public void writeAllText(String text, boolean append) {
        try {
            BufferedWriter bfw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(this, append)));
            bfw.write(text);
            bfw.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 加密与解密文件
     */
    public void encryptedToFile() {
        try {
            FileInputStream fis = new FileInputStream(this);
            int b;
            ArrayList<Integer> list = new ArrayList<>();
            while ((b = fis.read()) != -1) {
                list.add(b ^ 2);
            }
            fis.close();
            FileOutputStream fos = new FileOutputStream(this);
            for (Integer integer : list) {
                fos.write(integer);
            }
            fos.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 通过按位异或读取通过c解密过的文件字节数组
     *
     * @param c 解密的数字
     * @return 通过c解密过的文件字节数组
     */
    public byte[] readEncryptedToFileByte(int c) {
        try {
            FileInputStream fis = new FileInputStream(this);
            byte[] buffer = new byte[(int) this.length()];
            for (int i = 0; i < buffer.length; i++) {
                buffer[i] = (byte) (fis.read() ^ c);
            }
            fis.close();
            return buffer;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 读取文件字节数组
     *
     * @return 文件字节数组
     */
    public byte[] read() {
        try {
            FileInputStream fis = new FileInputStream(this);
            byte[] buffer = new byte[(int) this.length()];
            for (int i = 0; i < buffer.length; i++) {
                buffer[i] = (byte) (fis.read());
            }
            fis.close();
            return buffer;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 通过按位异或使用2来解密文件
     *
     * @return 通过2来解密文件的数组
     */
    public byte[] readEncryptedToFileByte() {
        return readEncryptedToFileByte(2);
    }

    /**
     * 通过按位异或加密写入字节数组，默认加密数字为2
     *
     * @param bytes 字节数组
     */
    public void writeEncryptedToFileByte(byte[] bytes) {
        writeEncryptedToFileByte(bytes, 2);
    }

    /**
     * 通过按位异或加密写入字节数组
     *
     * @param bytes 字节数组
     * @param c     加密数字
     */
    public void writeEncryptedToFileByte(byte[] bytes, int c) {
        try {
            FileOutputStream fos = new FileOutputStream(this);
            for (byte by : bytes) {
                fos.write(by ^ c);
            }
            fos.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 文件写入字节数组
     *
     * @param bytes 待写入的字节数组
     */
    public void write(byte[] bytes) {
        try {
            FileOutputStream fos = new FileOutputStream(this);
            fos.write(bytes, 0, bytes.length);
            fos.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * @param charsetName 编码
     * @return 文件的全部内容
     */
    public String readAllText(String charsetName) {
        try {
            StringBuilder sb = new StringBuilder();
            BufferedReader bfr = new BufferedReader(new InputStreamReader(new FileInputStream(this), charsetName));
            String st;
            while ((st = bfr.readLine()) != null) {
                sb.append(st).append(System.lineSeparator());
            }
            bfr.close();
            return sb.toString();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 搜索目录文件夹下的文本文件，如果文件内容出现字符串st则添加入集合中
     *
     * @param st 要搜索的字符串
     * @return 匹配文件的文件集合
     * @throws RuntimeException 如果该文件非目录则抛出异常
     */
    public ArrayList<RainFile> findFilesWithStringInDirectory(String st) {
        return findFilesWithStringInDirectory(this, st);
    }

    /**
     * 利用递归手段搜索目录文件夹下的文本文件，如果文件内容出现字符串st则添加入集合中
     *
     * @param rf 要查找的文件目录
     * @param st 要搜索的字符串
     * @return 匹配文件的文件集合
     * @throws RuntimeException 如果该文件非目录则抛出异常
     */
    private ArrayList<RainFile> findFilesWithStringInDirectory(RainFile rf, String st) {
        String[] stArr = rf.list();
        if (stArr != null) {
            for (String s : stArr) {
                RainFile file = new RainFile(rf, s);
                if (file.isFile()) {
                    if (file.readAllText().contains(st)) {
                        findSearchResultList.add(file);
                    }
                } else {
                    findFilesWithStringInDirectory(file, st);
                }
            }
        }
        return findSearchResultList;
    }

    /**
     * 利用递归手段搜索目录文件夹下的文本文件，如果文件内容出现字符串st则添加入集合中
     *
     * @param rf          要查找的文件目录
     * @param st          要搜索的字符串
     * @param charsetName 文件编码
     * @return 匹配文件的文件集合
     * @throws RuntimeException 如果该文件非目录则抛出异常
     */
    public ArrayList<RainFile> findFilesWithStringInDirectory(RainFile rf, String st, String charsetName) {
        String[] stArr = rf.list();
        if (stArr != null) {
            for (String s : stArr) {
                RainFile file = new RainFile(rf, s);
                if (file.isFile()) {
                    if (file.readAllText(charsetName).contains(st)) {
                        findSearchResultList.add(file);
                    }
                } else {
                    findFilesWithStringInDirectory(file, st, charsetName);
                }
            }
        }
        return findSearchResultList;
    }

    /**
     * 获取资源文件夹内的文件并转为一个{@code BufferedReader}
     *
     * @param filePath 文件在资源文件夹里的路径
     * @return {@code BufferedReader}
     * @since 1.0
     */
    private BufferedReader getResourcesBufferedReader(String filePath) {
        // 使用类加载器获取资源的输入流
        try {
            InputStream inputStream = getClass().getClassLoader().getResourceAsStream(filePath);
            if (inputStream == null) {
                throw new RuntimeException("资源不存在: " + filePath);
            }
            return new BufferedReader(new InputStreamReader(inputStream));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 在{@link #getResourcesBufferedReader(String filePath)}方法的基础上添加了一个字符集参数
     * 让InputStreamReader
     * 以指定的编码格式打开
     *
     * @since 1.0
     */
    private BufferedReader getResourcesBufferedReader(String filePath, String codeString) {
        // 使用类加载器获取资源的输入流
        try {
            InputStream inputStream = getClass().getClassLoader().getResourceAsStream(filePath);
            if (inputStream == null) {
                throw new RuntimeException("资源不存在: " + filePath);
            }
            return new BufferedReader(new InputStreamReader(inputStream, codeString));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 获取资源路径的输入流
     *
     * @param filePath 文件路径
     * @since 1.1
     */
    private InputStream getResourcesInputStream(String filePath) {
        // 使用类加载器获取资源的输入流
        try {
            return getClass().getClassLoader().getResourceAsStream(filePath);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 获取文件路径的输入流
     *
     * @param filePath 文件路径
     * @since 1.1
     */
    private InputStream getFileInputStream(String filePath) {
        // 输入流
        try {
            return Files.newInputStream(Paths.get(filePath));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 获取资源文件夹内的文件并转为一个{@code BufferedReader}
     *
     * @return {@code BufferedReader}
     */
    public BufferedReader getFileBufferedReader() {
        try {
            return new BufferedReader(new InputStreamReader(new FileInputStream(this), getCharsetName()));
        } catch (FileNotFoundException | UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * @param reader 读取数据的缓冲流
     * @return 读取的文本，按行储存进字符串容器
     */
    public StringBuilder readFileStringBuilder(BufferedReader reader) {
        StringBuilder sb = new StringBuilder();
        try {
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line).append(System.lineSeparator());
            }
            reader.close();
        } catch (IOException e) {
            throw new RuntimeException(String.format("%s 读取失败: %s", getAbsoluteFile(), e.getMessage()));
        }
        return sb;
    }

    /**
     * @return BufferedWriter
     */
    public BufferedWriter getFileBufferedWriter() {
        try {
            return new BufferedWriter(new OutputStreamWriter(new FileOutputStream(this), getCharsetName()));
        } catch (UnsupportedEncodingException | FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * @return BufferedWriter
     */
    public BufferedWriter getFileBufferedWriter(String charsetName) {
        try {
            return new BufferedWriter(new OutputStreamWriter(new FileOutputStream(this), charsetName));
        } catch (UnsupportedEncodingException | FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * @param reader 读取数据的缓冲流
     * @return 读取的文本，按行储存进集合
     */
    public ArrayList<String> readFileTextArrayList(BufferedReader reader) {
        ArrayList<String> list = new ArrayList<>();
        try {
            String line;
            while ((line = reader.readLine()) != null) {
                list.add(line);
            }
            reader.close();
        } catch (IOException e) {
            throw new RuntimeException(String.format("%s 读取失败: %s", getAbsoluteFile(), e.getMessage()));
        }
        return list;
    }

    public ArrayList<String> readFileTextArrayList() {
        ArrayList<String> list = new ArrayList<>();
        try {
            String line;
            BufferedReader reader = getFileBufferedReader();
            while ((line = reader.readLine()) != null) {
                list.add(line);
            }
            reader.close();
        } catch (IOException e) {
            throw new RuntimeException(String.format("%s 读取失败: %s", getAbsoluteFile(), e.getMessage()));
        }
        return list;
    }

    /**
     * 改变文件的编码格式
     *
     * @param charsetName 要变为的结果编码
     */
    public void changeFileEncoding(String charsetName) {
        writeAllText(readAllText(), charsetName);
    }

    @Override
    public long length() {
        if (isDirectory()) {
            return directoryFilesLength(this.getAbsolutePath());
        }
        return super.length();
    }

    /**
     * 利用递归测算一个目录下的所有包括子目录下的文件字节数
     *
     * @param path 目录路径
     * @return 目录下的所有包括子目录下的文件字节数
     */
    public final long directoryFilesLength(String path) {
        File file = new File(path);
        long l = 0L;
        if (file.isDirectory()) {
            String[] list = file.list();
            if (list != null) {
                for (String s : list) {
                    l = l + directoryFilesLength(path + "\\" + s);
                }
            }
        } else return file.length();
        return l;
    }

    /**
     * 判断文件编码
     *
     * @return 文件编码
     */
    public String getCharsetName() {
        String charset = "GBK";
        byte[] first3Bytes = new byte[3];
        try {
            boolean checked = false;
            BufferedInputStream bis = new BufferedInputStream(new FileInputStream(this));
            bis.mark(0);
            int read = bis.read(first3Bytes, 0, 3);
            if (read == -1) {
                return charset; //文件棉麻为 ANSI
            } else if (first3Bytes[0] == (byte) 0xFF
                    && first3Bytes[1] == (byte) 0xFE) {
                charset = "UTF-16LE"; //文件编码为Unicode
                checked = true;
            } else if (first3Bytes[0] == (byte) 0xFE
                    && first3Bytes[1] == (byte) 0xFF) {
                charset = "UTF-16BE"; //文件编码为 Unicode big endian
                checked = true;
            } else if (first3Bytes[0] == (byte) 0xEF
                    && first3Bytes[1] == (byte) 0xBB
                    && first3Bytes[2] == (byte) 0xBF) {
                charset = "UTF-8"; //文件编码为 utf-8
                checked = true;
            }
            bis.reset();
            if (!checked) {
                int loc = 0;
                while ((read = bis.read()) != -1) {
                    loc++;
                    if (read >= 0xF0)
                        break;
                    if (0x80 <= read && read <= 0xBF) // 单独出现BF以下的,也算是GBK
                        break;
                    if (0xC0 <= read && read <= 0xDF) {
                        read = bis.read();
                        if (0x80 <= read && read <= 0xBF) // 双字节 (0xC0 - 0xDF)
                            // (0x80 -0xBF) 也可能在GBK编码内
                            continue;
                        else
                            break;
                    } else if (0xE0 <= read && read <= 0xEF) {// 也有可能出错，但几率很小
                        read = bis.read();
                        if (0x80 <= read && read <= 0xBF) {
                            read = bis.read();
                            if (0x80 <= read && read <= 0xBF) {
                                charset = "UTF-8";
                                break;
                            } else
                                break;
                        } else
                            break;
                    }
                }
            }
            bis.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return charset;
    }

    /**
     * 传入字节，将其转换为0.00mb，0.00kb的字符串
     *
     * @return 字节长的字符串
     */
    public String lengthText() {
        long bytes = length();
        if (bytes < 1024) {
            return bytes + "B";
        } else if (bytes < 1024 * 1024) {
            return String.format("%.2fKB", (double) bytes / 1024);
        } else if (bytes < 1024 * 1024 * 1024) {
            return String.format("%.2fMB", (double) bytes / (1024 * 1024));
        } else {
            return String.format("%.2fGB", (double) bytes / (1024 * 1024 * 1024));
        }
    }

    /**
     * 是否为一个目录
     *
     * @return 是目录则返回true
     */
    @Override
    public boolean isDirectory() {
        return super.isDirectory();
    }

    /**
     * 判断是否为一个文件
     *
     * @return 是文件则返回true
     */
    @Override
    public boolean isFile() {
        return super.isFile();
    }
}
