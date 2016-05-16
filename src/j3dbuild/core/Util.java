package j3dbuild.core;

import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

public class Util {

    public static BufferedImage loadImage(String filePath) {
        try {
            return ImageIO.read(Util.class.getResource("/" + filePath));
        } catch (Exception e) {
            return null;
        }
    }
}
