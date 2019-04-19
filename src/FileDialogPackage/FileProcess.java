package FileDialogPackage;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;


public final class FileProcess {

    private final static int colRow = 4;
    private final static int newWidth = 499;
    private final static int newHeight = 455;

    public static BufferedImage cutImage(File input) {

        try {

            FileInputStream fis = new FileInputStream(input);
            Image image = ImageIO.read(fis); //reading the image file
            image = image.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);
            BufferedImage bimage = new BufferedImage(image.getWidth(null), image.getHeight(null), BufferedImage.TYPE_INT_ARGB);

            Graphics2D bGr = bimage.createGraphics();
            bGr.drawImage(image, 0, 0, null);
            bGr.dispose();

            return bimage;

        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static BufferedImage[] chunkImage(BufferedImage image) {

        int chunksNumber = colRow * colRow;

        int chunkWidth = image.getWidth() / colRow;
        int chunkHeight = image.getHeight() / colRow;
        int count = 0;
        BufferedImage[] imageChunks = new BufferedImage[chunksNumber];

        for (int x = 0; x < colRow; x++) {
            for (int y = 0; y < colRow; y++) {
                imageChunks[count] = new BufferedImage(chunkWidth, chunkHeight, image.getType());

                Graphics2D graphic = imageChunks[count++].createGraphics();
                graphic.drawImage(image, 0, 0, chunkWidth, chunkHeight, chunkWidth * y, chunkHeight * x, chunkWidth * y + chunkWidth, chunkHeight * x + chunkHeight, null);
                graphic.dispose();
            }
        }

        return imageChunks;

    }
}
