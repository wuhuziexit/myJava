package rain.thread;

public class CharPrinter implements Runnable {
    private String str;
    private int delay;

    public CharPrinter(String str, int delay) {
        this.str = str;
        this.delay = delay;
    }

    @Override
    public void run() {
        for (char c : str.toCharArray()) {
            System.out.print(c);
            try {
                Thread.sleep(delay);
            } catch (InterruptedException e) {
                throw new RuntimeException();
            }
        }
        System.out.println();  // 打印换行符，以结束当前字符串的打印
    }
}
