package rain.swing;

import java.awt.*;
import java.util.Random;

public class Swing {
    // 生成随机颜色的方法
    public Color getRandomColor() {
        Random random = new Random();
        int red = random.nextInt(256);
        int green = random.nextInt(256);
        int blue = random.nextInt(256);
        return new Color(red, green, blue);
    }
}
