package gfx;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class ImageLoader {

    public static BufferedImage loadImage(String path){
        try{
            return ImageIO.read(ImageLoader.class.getResource(path)); // returns buffered image object
        }catch (IOException e){
            e.printStackTrace();
            System.exit(1);
        }
        return null;
    }
}
