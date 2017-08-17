import java.util.ArrayList;
import java.util.Random;
import java.util.Arrays;
import java.util.List;

/**
 * This is the pooling class for the convolutional neural network
 * Call poolMain() to return pooling
 */

public class PoolingFunctions {
	
	// Constructors
	double[][] image;
	int filter_size_constr;
	public PoolingFunctions(double[][] image, int filter_size_constr){
		this.image = image;
		this.filter_size_constr = filter_size_constr;
	}
	// Outputs arrayList of 2d matrices
	public static ArrayList<double[]> PoolConv(double[][] image, int filter_size){
		ArrayList<double[]> StackedMatrices = new ArrayList();
		
		// for every row:
		for(int i=0;i<image.length;i+=filter_size){
			int a = 0;				// slice [a:b]
			int b = filter_size;	// slice [a:b]
			
			// moving horizontally:
			for(int j=0;j<(image[0].length/filter_size);j++){
				List filter = new ArrayList();
				// for each row in the filter size:
				for(int k = 0;k<filter_size;k++){
					double[] slice_ = Arrays.copyOfRange(image[i+k], a, b);
					filter.add(slice_);
				}
				a+=filter_size;
				b+=filter_size;
				StackedMatrices.addAll(filter);
			}
		}
		return StackedMatrices;
	}
	
	// Returns height/width dimensions based on filter and image size
	// inserts photo into blank matrix
	public static double[][]imageFrame(double[][] matrix2d, int height_image, int width_image, int filter_size_height, int filter_size_width){
		// Define dimensions
		int new_height = height_image;
		int new_width = width_image;
		// Add additional columns/ rows if necessary:
		if((height_image%filter_size_height)!= 0){		// Rows
			 new_height = height_image + (filter_size_height - (height_image % filter_size_height));	// height of new image
		}
		if((width_image%filter_size_width)!= 0){		// Columns
			new_width = width_image + (filter_size_width - (width_image % filter_size_height));		// width of new image
		}
		// Initialize blank matrix with repsective dimensions
		double[][] blank_matrix = new double [new_height][new_width];
		// Fill in blank matrix
        for (int i = 0; i<matrix2d.length;i++){			// for each row
            for (int j = 0; j<matrix2d[0].length;j++){	// for each cell
                blank_matrix[i][j] = matrix2d[i][j];    // Index in blank matrix is value of smaller matrix
            }
        }
		return blank_matrix;	// return image with frame
	}
	

	// Array with random numbers, specified by height/width
	public static double[][] ArrayMatrix(int height_size, int width_size){
		double [][] matrix = new double[height_size][width_size];
		for(int row=0;row<height_size;row++){
			for(int cell=0;cell<width_size;cell++){
				Random rn = new Random();
				matrix[row][cell] = rn.nextDouble();
			}
		}
		return matrix;
	}
	
    // Prints a 2D Matrix
    public static <E> void print2dMatrix(double[][] listMatrix){
        System.out.println();
        for (int i = 0 ; i < listMatrix.length ; i++) {
            for (int j = 0; j < listMatrix[i].length; j++) {
                System.out.print(listMatrix[i][j]+ " ");
            }
            System.out.println();
        }
    }
    
    // Pooling method: returns max of each 2d matrix in a 1D ArrayList format
    public static ArrayList<double[]> poolingMax(ArrayList maxList, int filter_size){
    	// List with maximums for each:
    	ArrayList maxCompress = new ArrayList();
    	// Convert arrayList to array type
    	Object[] arrayInt = new Object[maxList.size()];
    	arrayInt = maxList.toArray(arrayInt);
    	
    	// Specify local variables
    	int f_size = filter_size*filter_size;	// filter area
    	int a = 0;								// slice [a:b]
    	int b = f_size;							// slice [a:b]

    	for(int iter = 0; iter<(maxList.size()/f_size);iter++){
    		Object[] slice  = Arrays.copyOfRange(arrayInt,a,b);
    		Double max = (double) 0;

    		for(int k = 0; k < slice.length; k++){
    			Double value = (Double) slice[k];
    			if(value > max){
    				max = value;
    			}
    		}
    		maxCompress.add(max);	// Add integer to max

    		// Increment [a:b]
    		a+=f_size;
    		b+=f_size;
    	}
    	return maxCompress;
    }
    
    // Convert 1D list of maximums to 2D matrix of maximums
    public static double[][] return2d(ArrayList max1d, int rows, int columns){
		double[][] poolMatrix = new double[rows][columns];	// creates blank matrix with specified dimensions
		int counter = 0;	// returns index of max1d 
		for(int i = 0; i < (rows); i++){
			for(int k = 0; k < columns; k++){
				poolMatrix[i][k] = (double) max1d.get(counter);
				counter++;
				
			}
		}
		return poolMatrix;
    }
    
    // Basically the "main function of this class"
    public double[][] poolingMain(){
    	// Fill 0s
    	double[][] ListMatrix0 = imageFrame(image, image.length, image[0].length,
    			filter_size_constr, filter_size_constr);
		//print2dMatrix(ListMatrix0);	// Fill with 0s
		//System.out.println();
		
		// Testing PoolConv() method:
		ArrayList<double[]> stack = PoolConv(ListMatrix0,filter_size_constr);
		
		//System.out.println("");
		// Print result from PoolConv() method:
		
		//System.out.println(Arrays.toString(stack));
		
		double[] maxList;
		ArrayList <Double> max1d = new ArrayList();
		
		for(int i = 0; i<stack.size();i++){
			maxList = (stack.get(i));
			for(int k=0;k<maxList.length;k++){
				max1d.add(maxList[k]);
			}
		}
				
		// getting maximums
		ArrayList<double[]> al = poolingMax(max1d, filter_size_constr);

		// get dimensions for 2d pooling matrix
		int pool_height = ListMatrix0.length/filter_size_constr;
		int pool_width = ListMatrix0[0].length/filter_size_constr;
		// convert into 2d matrix
		double[][] pm = return2d(al,pool_height,pool_width);
		//print2dMatrix(pm);

    	
    	return pm;
    }

}