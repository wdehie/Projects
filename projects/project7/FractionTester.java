import java.io.*;
import java.util.*;

public class FractionTester
{
	public static void main( String args[] )
	{
		
		int[] numers = { 13, -7, 26, -5,   8, -1 };
		int[] denoms = { 3,  -49, 13, 15,  -7, -1 };

		// POPULATE AN ARRAY OF FRACTIONS WITH SOME VALUES

		Fraction[] plainArr = new Fraction[ numers.length ];
		for ( int i = 0 ; i < numers.length ; ++i )
			plainArr[i] = new Fraction( numers[i], denoms[i] );

		System.out.println("\nplainArr OF FRACTIONS");
		for ( Fraction f : plainArr )
			System.out.println( f );

	}// END MAIN
} // END

