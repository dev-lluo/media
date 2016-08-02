package top.flyfire.media;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by shyy_work on 2016/8/1.
 */
public final class ImageUtils {

    public static int[][] grayscale(BufferedImage bufferedImage) {
        int width = bufferedImage.getWidth(), height = bufferedImage.getHeight();
        int[][] grayTemp = new int[width][height];
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                bufferedImage.setRGB(x, y, ColorUtils.grayRGB(ColorUtils.grayscale(bufferedImage.getRGB(x, y))));
            }
        }
        return grayTemp;
    }

    public static int[][] binarizate(BufferedImage bufferedImage) {
        int width = bufferedImage.getWidth(), height = bufferedImage.getHeight();
        int[][] grayTemp = new int[width][height];
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                grayTemp[x][y] =ColorUtils.grayscale(bufferedImage.getRGB(x, y));
            }
        }
        int thresholdValue = ColorUtils.calcThresholdValue(grayTemp, width, height);
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                if (grayTemp[x][y] > thresholdValue) {
                    grayTemp[x][y] |= 0x00FFFF;
                } else {
                    grayTemp[x][y] &= 0xFF0000;
                }
                bufferedImage.setRGB(x, y, ColorUtils.grayRGB(grayTemp[x][y]));
            }
        }
        return grayTemp;
    }

    public static List<BufferedImage> splitByXProjection(BufferedImage bufferedImage) {
        ImageUtils.binarizate(bufferedImage);
        int width = bufferedImage.getWidth(),height = bufferedImage.getHeight(),splitStartIndex = 0;
        Projection[] projections = new Projection[width];
        Projection projection;
        for(int y = 0;y<height;y++){
            for(int x = 0;x<width;x++){
                if(ColorUtils.grayscale(bufferedImage.getRGB(x,y))==0) {
                    projection = projections[x]==null?new Projection():projections[x].putIngredient(y);
                }
            }
        }
        List<BufferedImage> bufferedImageList = new ArrayList<BufferedImage>();
        for(int i = 0;i<projections.length;i++){
            if(projections[i].getValue()==0){

            }
        }
        return null;
    }

    public static BufferedImage  zoomInImage(BufferedImage  originalImage, Integer times){
        int width = originalImage.getWidth()*times;
        int height = originalImage.getHeight()*times;
        return zoomImage(originalImage,width,height);
    }

    public static BufferedImage  zoomOutImage(BufferedImage  originalImage, Integer times){
        int width = originalImage.getWidth()/times;
        int height = originalImage.getHeight()/times;
        return zoomImage(originalImage,width,height);
    }

    public static BufferedImage zoomImage(BufferedImage  originalImage,int newWidth,int newHeight){
        BufferedImage newImage = new BufferedImage(newWidth,newWidth,originalImage.getType());
        Graphics g = newImage.getGraphics();
        g.drawImage(originalImage, 0,0,newWidth,newWidth,null);
        g.dispose();
        return newImage;
    }

    private static String[] GRAY_CHARS = {"@@", "**", "++", "=:","  "};

    private static String RELINE = "\r\n";

    public static String image2Text(BufferedImage bufferedImage) {
        int width = bufferedImage.getWidth(), height = bufferedImage.getHeight();
        StringBuilder builder = new StringBuilder();
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int gray = ColorUtils.grayscale(bufferedImage.getRGB(x, y));
                if (gray > 200) {
                    builder.append(GRAY_CHARS[4]);
                } else if (gray > 150) {
                    builder.append(GRAY_CHARS[3]);
                } else if (gray > 100) {
                    builder.append(GRAY_CHARS[2]);
                } else if (gray > 50) {
                    builder.append(GRAY_CHARS[1]);
                } else {
                    builder.append(GRAY_CHARS[0]);
                }
            }
            builder.append(RELINE);
        }
        return builder.toString();
    }

}
