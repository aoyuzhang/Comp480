import java.awt.image.BufferedImage;
import java.util.ArrayList;
// Training images from: http://www.vision.caltech.edu/Image_Datasets/Caltech101/#Download
public class NN 
{
	
	private int inputSizes;
	private int numberOfKernel1 ;
	private int numberOfKernel2 ;
	private int kernelSize1;
	private int kernelSize2;
	private int outputSize;
	
	private double[][] input;
	private double[][][] kernel1;
	private double[][][] convolutionLayer1;
	private double[][][] poolingLayer1;
	private double[][][] kernel2;
	private double[][][] convolutionLayer2;
	private double[][][] poolingLayer2;
	private double[][] fullyConnectedHiddenLayer;
	private double[] output;
	
	/**
	 * @param inputSizes
	 * @param numberOfKernel
	 * @param kernelSize
	 * @param outputSize
	 */
	public NN(int inputSizes, int numberOfKernel1,int numberOfKernel2, int kernelSize1, int outputSize,int kernelSize2) {
		super();
		this.inputSizes = inputSizes;
		this.numberOfKernel1 = numberOfKernel1;
		this.numberOfKernel2 = numberOfKernel2;
		this.kernelSize1 = kernelSize1;
		this.kernelSize2 = kernelSize2;
		this.outputSize = outputSize;
		// Initialize the kernel
		this.kernel1=new double[numberOfKernel1][][];
		this.kernel2=new double[numberOfKernel2][][];
		for(int i=0;i<numberOfKernel1;i++)
		{
			//System.out.println("kernel layer 1,"+i);
			double[][] temp= MatrixUtilities.random(kernelSize1, kernelSize1);
			kernel1[i]=temp;				
			//MatrixUtilities.printMatrix(kernel1[i]);
		}
		for(int i=0;i<numberOfKernel2;i++)
		{
			//System.out.println("kernel layer 2,"+i);
			double[][] temp= MatrixUtilities.random(kernelSize2, kernelSize2);
			kernel2[i]=temp;
			//MatrixUtilities.printMatrix(kernel2[i]);
		}
		
	}
	public void train(BufferedImage img)
	{
		// Scale image
		img=ImageUtilities.scale(img, inputSizes, inputSizes);
		this.input= ImageUtilities.getRGBInM(img);
		//MatrixUtilities.printMatrix(input);
		//System.out.println("width is "+input.length+" and height is "+input[0].length);
		// first Convolution layer
		this.convolutionLayer1= new double[numberOfKernel1][][];
		for(int i=0;i<numberOfKernel1;i++)
		{
			convolutionLayer1[i]=Convolution.convolution2D(input,inputSizes, inputSizes, kernel1[i], kernelSize1,kernelSize1);
			//System.out.println("feature "+i+" for convolution layer 1");
			//MatrixUtilities.printMatrix(convolutionLayer1[i]);
		}
		// first pooling layer+relu
		this.poolingLayer1= new double[numberOfKernel1][][];
		for(int i=0;i<numberOfKernel1;i++)
		{
			PoolingFunctions temp=new PoolingFunctions(convolutionLayer1[i],3);
			poolingLayer1[i]=temp.poolingMain();
			//System.out.println("feature "+i+" for pooling layer 1");
			//MatrixUtilities.printMatrix(poolingLayer1[i]);
		}
		//second Convolution
		int convLayer2Number=numberOfKernel1*numberOfKernel2;
		int imgDiml1=this.poolingLayer1[0].length;
		this.convolutionLayer2= new double[convLayer2Number][][];
		for(int i=0;i<numberOfKernel1;i++)
		{
			for(int j=0;j<numberOfKernel2;j++)
			{
				convolutionLayer2[i*numberOfKernel1+j]=Convolution.convolutionType1(this.poolingLayer1[i],imgDiml1, imgDiml1, kernel2[j], kernelSize2,kernelSize2,1);
				//System.out.println("feature "+(i*numberOfKernel1+j)+" for convolution layer 2");
				//MatrixUtilities.printMatrix(convolutionLayer2[i*numberOfKernel1+j]);
			}
		}
		//second pooling +relu
		this.poolingLayer2= new double[convLayer2Number][][];
		for(int i=0;i<numberOfKernel1;i++)
		{
			for(int j=0;j<numberOfKernel2;j++)
			{
				PoolingFunctions temp=new PoolingFunctions(convolutionLayer2[i*numberOfKernel1+j],3);
				poolingLayer2[i*numberOfKernel1+j]=temp.poolingMain();
				//System.out.println("feature "+(i*numberOfKernel1+j)+" for pooling layer 2");
				//MatrixUtilities.printMatrix(poolingLayer2[i*numberOfKernel1+j]);
				
			}
		}
		int imgdpl2=poolingLayer2[0].length;
		int sizeOfVector=convLayer2Number*imgdpl2*imgdpl2;
		// make a vector out of the matrices
		double[] polling2Vector= new double[sizeOfVector];
		int count=0;
		int width=poolingLayer2[0].length;
		for(int i=0;i<numberOfKernel1;i++)
		{
			for(int j=0;j<numberOfKernel2;j++)
			{
				for(int m=0;m<width;m++)
				{
					for(int n=0;n<width;n++)
					{
						polling2Vector[count]=poolingLayer2[i*numberOfKernel1+j][m][n];
						count++;
					}
				}
				
			}
		}
		//MatrixUtilities.printArray(polling2Vector);
		
		
		//System.out.println("Size of the vector is: "+sizeOfVector);
		fullyConnectedHiddenLayer=MatrixUtilities.random(2,sizeOfVector);
		
		// get the output
		this.output=MatrixUtilities.multiply(fullyConnectedHiddenLayer,polling2Vector);
		this.output=MatrixUtilities.sigmoid(output);
		MatrixUtilities.printArray(output);
		
		
		//
		
		
		
	}
//	public double[] test(BufferedImage img)
//	{
//		// Scale image
//		ImageUtilities.scale(img, inputSizes, inputSizes);
//		//double input= getRGB(img);
//
//		//Create kernel
//		
//		
//	}
	
	
	//ArrayList<>
	
	
//	File folder = new File("C:\\Users\\hzhang127\\workspace\\NN\\src\\101_ObjectCategories");
//	File[] listOfFiles = folder.listFiles();
//	for (int i = 0; i < listOfFiles.length; i++) 
//	{
//	  File file = listOfFiles[i];
//	  if (file.isFile() && file.getName().endsWith(".txt")) {
//	    String content = FileUtils.readFileToString(file);
//	    /* do somthing with content */
//	  } 
//	}
	
	



}
