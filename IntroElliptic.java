import java.lang.Math;
public class IntroElliptic 
{

	public static void main(String[] args) 
	{
		long[][] rationals=generateNRationalNumbers(100);
		for(int i=0;i<100;i++)
			printRational(rationals[i][0],rationals[i][1]);

	}
	public static boolean isPythagorianTriple(int a, int b, int c)
	{
			if ((a * a) + (b * b) == (c * c))
				return true;
			return false;
	}
	public static long[][] generateNRationalNumbers(long N)
	{
		long[][] rationals=new long[(int)N+1][2];
		long[] DiatomicSerieUntilN=generateSternDiatomicSerie(N+1);
		for( long i=1;i<=N;i++)
		{
			rationals[(int)i][0]=DiatomicSerieUntilN[(int)i];
			rationals[(int)i][1]=DiatomicSerieUntilN[(int)i+1];
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
				dioatimicSerieTable[(int)i]=dioatimicSerieTable[((int)i-1)/2]+dioatimicSerieTable[((int)i-1)/2+1];
				
		for(int i=1;i<=n;i++)
			System.out.println(dioatimicSerieTable[i]);
		return dioatimicSerieTable;
	}
	public static void printRational(long a, long b)
	{
		System.out.println(a+"/"+b);
	}

}
