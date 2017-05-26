import java.lang.Math;
public class IntroElliptic 
{

	public static void main(String[] args) 
	{
		

	}
	public static boolean isPythagorianTriple(int a, int b, int c)
	{
			if ((a * a) + (b * b) == (c * c))
				return true;
			return false;
	}
	public static long[][] generateNRationalNumbers(long N)
	{
		long[][] rationals=new long[(int)N][2];
		long[] DiatomicSerieUntilN=generateSternDiatomicSerie(N);
		for( long i=1;i<=N;i++)
		{
			rationals[i][0]=DiatomicSerieUntilN[];
			rationals[i][1]=DiatomicSerieUntilN[];
		}
		return rationals;
	}
	public static long[] generateSternDiatomicSerie(long n)
	{
		long[] dioatimicSerieTable= new long[(int)n+1];
		dioatimicSerieTable[0]=0;
		dioatimicSerieTable[1]=1;
		for(long i=2;i<=n;i++)
			if(i%2==0)
				dioatimicSerieTable[(int)i]=dioatimicSerieTable[(int)i/2];
			else
				dioatimicSerieTable[(int)i]=dioatimicSerieTable[((int)i-1)/2]+dioatimicSerieTable[((int)i-1)/2-1];
				
		return dioatimicSerieTable;
		
	}

}
