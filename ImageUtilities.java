import java.applet.*;
import java.awt.*;
import java.awt.image.*;
import java.net.*;
import java.util.*;
import java.io.*;
import java.lang.Math.*;
import java.awt.Color.*;
import java.awt.geom.AffineTransform;

public class ImageUtilities {


	/**
	 * Get combined RGB value of image and store it in a matrix
	 * @param img
	 * @return double[][] matrix representation of image 
	 */
	public static double[][] getRGBInM(BufferedImage img)
	{
		int[] pixel;
        double[][]table=new double[img.getWidth()][img.getHeight()];

        for (int y = 0; y < img.getHeight(); y++) 
        {
            for (int x = 0; x < img.getWidth(); x++) 
            {
                pixel = img.getRaster().getPixel(x, y, new int[3]);
                table[x][y]=(double)pixel[0]+pixel[1]*1000.0+pixel[2]*1000000.0;
                //System.out.println(pixel[0] + " - " + pixel[1] + " - " + pixel[2] + " - " + (img.getWidth() * y + x));
            }
        }
        return table;
	}
	
    /**
     * scale image
     * 
     * @param sbi image to scale
     * @param imageType type of image
     * @param dWidth width of destination image
     * @param dHeight height of destination image
     * @param fWidth x-factor for transformation / scaling
     * @param fHeight y-factor for transformation / scaling
     * @return scaled image
     */
    public static BufferedImage scale(BufferedImage sbi, int imageType, int dWidth, int dHeight, double fWidth, double fHeight) {
        BufferedImage dbi = null;
        if(sbi != null) {
            dbi = new BufferedImage(dWidth, dHeight, imageType);
            Graphics2D g = dbi.createGraphics();
            AffineTransform at = AffineTransform.getScaleInstance(fWidth, fHeight);
            g.drawRenderedImage(sbi, at);
        }
        return dbi;
    }
    /**
     * scale image 
     * @param imageToScale
     * @param dWidth width of new imgage
     * @param dHeight height of new image
     * @return
     */
    public static BufferedImage scale(BufferedImage imageToScale, int dWidth, int dHeight) {
        BufferedImage scaledImage = null;
        if (imageToScale != null) {
            scaledImage = new BufferedImage(dWidth, dHeight, imageToScale.getType());
            Graphics2D graphics2D = scaledImage.createGraphics();
            graphics2D.drawImage(imageToScale, 0, 0, dWidth, dHeight, null);
            graphics2D.dispose();
        }
        return scaledImage;
    }
}
