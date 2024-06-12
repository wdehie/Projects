import java.io.*;
import java.util.*;

public class MyLinkedListTester
{
	public static void main( String args[] ) throws Exception
	{
		if ( args.length < 2 )
			die( "FATAL ERROR: must enter two filenames on command line.\njava MyLinkedListTester set1.txt  set2.txt\n" );

		BufferedReader infile = null;  // USE SAME FILE HANDLE TWICE FOR SET1 & SET2
		MyLinkedList list1 = new MyLinkedList();
		MyLinkedList list2 = new MyLinkedList();

		// READ set1 INTO list1
		infile = new BufferedReader( new FileReader( args[0] ) );
		while ( infile.ready() )
				list1.insertAtTail( infile.readLine() );
		infile.close();

		// READ set2 INTO list2
		infile = new BufferedReader( new FileReader( args[1] ) );
		while ( infile.ready() )
				list2.insertAtTail( infile.readLine() );
		infile.close();

		// ECHO THE 2 LISTS
		System.out.format( "list1 loaded from %s contains %d elements: %s\n", args[0], list1.size(), list1 ); // invokes toString
		System.out.format( "list2 loaded from %s contains %d elements: %s\n", args[1], list2.size(), list2 ); // invokes toString

		// SEARCH LIST1 FOR EACH KEY IN LIST2
		String[] keys = list2.toString().split( "\\s+" ); //  split() COMES IN HANDY SOMETIMES
		System.out.println( "Searching list1 for the following words from list2: " );
		for ( String key : keys )
		{
			if ( list1.contains( key  ) )
				System.out.println( key + "\tfound" );
			else
				System.out.println( key + "\tNOT found" );
		}

	} // END MAIN

	static void die( String errMsg )
	{	System.out.println( errMsg );
		System.exit(0); // kills program
	}
} // EOF