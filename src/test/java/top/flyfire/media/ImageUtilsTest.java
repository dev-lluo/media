package top.flyfire.media;

import org.junit.Test;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

/**
 * Created by shyy_work on 2016/8/1.
 */
public class ImageUtilsTest {
    @Test
    public void splitByClmnSpacing() throws Exception {
        BufferedImage bufferedImage = ImageIO.read(new File("C:\\Users\\shyy_work\\Desktop\\show.bmp"));
        ImageUtils.splitByXProjection(bufferedImage);
    }

    @Test
    public void image2Text() throws Exception {
        File file = new File("C:\\Users\\shyy_work\\Desktop\\grayDraw.txt");
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file));
        bufferedWriter.write(ImageUtils.image2Text(ImageIO.read(new File("C:\\Users\\shyy_work\\Desktop\\images.jpg"))));
        bufferedWriter.flush();
    }

    @org.junit.Test
    public void grayImage() throws Exception {
        BufferedImage bufferedImage = ImageIO.read(new File("C:\\Users\\shyy_work\\Desktop\\LI~)[GVY$PPA)5HY[G)[}OW.jpg"));
        ImageUtils.grayscale(bufferedImage);
        ImageIO.write(bufferedImage,"jpg",new File("C:\\Users\\shyy_work\\Desktop\\testGray.jpg"));
    }

    @org.junit.Test
    public void binaryImage() throws Exception {
        BufferedImage bufferedImage = ImageIO.read(new File("C:\\Users\\shyy_work\\Desktop\\show.bmp"));
        ImageUtils.binarizate(bufferedImage);
        ImageIO.write(bufferedImage,"bmp",new File("C:\\Users\\shyy_work\\Desktop\\showBinary.bmp"));
    }

}