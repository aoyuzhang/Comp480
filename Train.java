import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Train {

	    // File representing the folder that you select using a FileChooser
	    static final File dir = new File("C:\\Users\\hzhang127\\workspace\\NN\\src\\101_ObjectCategories\\airplanes");

	    // array of supported extensions (use a List if you prefer)
	    static final String[] EXTENSIONS = new String[]{
	        "jpg","gif", "png", "bmp" // and other formats you need
	    };
	    // filter to identify images based on their extensions
	    static final FilenameFilter IMAGE_FILTER = new FilenameFilter() 
	    {

	        @Override
	        public boolean accept(final File dir, final String name) 
	        {
	            for (final String ext : EXTENSIONS) 
	            {
	                if (name.endsWith("." + ext)) 
	                {
	                    return (true);
	                }
	            }
	            return (false);
	        }
	    };

	    public static void main(String[] args) {

			
//			BufferedImage img = null;
//			try {
//			    img = ImageIO.read(new File("C:\\Users\\hzhang127\\workspace\\NN\\src\\image_0001.jpg"));
//			} catch (IOException e) {
//			    // TODO Auto-generated catch block
//			    e.printStackTrace();
//			}
			
			
	    	NN neuralNet= new NN(50, 5, 5, 10, 2, 5);
	    	//neuralNet.train(img);

//	    	double[][] randomimg=MatrixUtilities.random(202, 202);
//	    	double[][] randomKernel=MatrixUtilities.random(20, 20);
//	    	double[][] conv=Convolution.convolution2D(randomimg, 202,202, randomKernel, 20, 20);
//	    	MatrixUtilities.printMatrix(conv);

	    	
	        if (dir.isDirectory()) { // make sure it's a directory
	            for (final File f : dir.listFiles(IMAGE_FILTER)) {
	                BufferedImage img = null;

	                try {
	                    img = ImageIO.read(f);
	                    //img=ImageUtilities.scale(img, 800, 800);
	                    neuralNet.train(img);

	                    
//	                    double[][]table=ImageUtilities.getRGBInM(img);
//	                    double[][] kernel=MatrixUtilities.random(20, 20);
//	                    //MatrixUtilities.printMatrix(kernel);
//	                    double[][] convolved=Convolution.convolutionType1(table,800, 800, kernel, 20,20,1);
//	                    //System.out.println("convolved\n");
//	                    //MatrixUtilities.printMatrix(convolved);
//	                    System.out.println(convolved.length+" "+convolved[0].length);
//	                    PoolingFunctions tempPooling=new PoolingFunctions(convolved,2);
//	                    double[][] tempoolingM=tempPooling.poolingMain();
//	                    //System.out.println("pooling\n");
//	                    //MatrixUtilities.printMatrix(tempoolingM);
//	                    System.out.println(tempoolingM.length+" "+tempoolingM[0].length);
//	                    
//	                    // you probably want something more involved here
//	                    // to display in your UI
//	                    System.out.println("image: " + f.getName());
//	                    System.out.println(" width : " + img.getWidth());
//	                    System.out.println(" height: " + img.getHeight());
//	                    System.out.println(" size  : " + f.length());
	                    break;
	                } catch (final IOException e) {
	                    // handle errors here
	                }
	            }
	        }
	    }
	    

	

}
