package gfx;

import java.awt.image.BufferedImage;

public class Assets {

    private static final int width = 60, height = 60;

    public static BufferedImage player, dirt, grass, stone, tree;

    public static void init(){
        SpriteSheet playerSheet = new SpriteSheet(ImageLoader.loadImage("/textures/playerSpriteSheet.png"));
        SpriteSheet tileSheet = new SpriteSheet(ImageLoader.loadImage("/textures/tilesheet.png"));

        player = playerSheet.crop(0, 0, width, height);

        dirt = tileSheet.crop(width * 10, 0, width, height);
        grass = tileSheet.crop(0, height * 2, width, height);
        stone = tileSheet.crop(0, 0, width, height);
        tree = tileSheet.crop(0, height * 4, width, height);
    }
}
