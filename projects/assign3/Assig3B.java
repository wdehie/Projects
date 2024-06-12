// CS 0445 Summer 2024
// Assignment 3 Test Program B
// This program should run as is with your MyStringBuilder2 class.
// Because this is timing operations the output will vary greatly based on the
// system being used.  However, note the trends of the times as N increases.

// Important Execution Note: Since your methods are implemented recursively, some
// may have long (linear) call chains that could cause the run-time stack to run
// out of space. In order to avoid a StackOverflowError you must increase the size
// of the run-time stack for execution. You can do this with the following execution
// statement:

// > java -Xss20M Assig3B

// This will increase the run-time stack to 20 Megabytes, which should be able to
// accommodate linear run-time stack size.

// Note that this program is identical to Assig2B except that MyStringBuilder2 has
// been substituted for MyStringBuilder.

// Compare the times here with those from Assig2B to see if there is a significant
// difference.
//
// Note: The append() and insert() methods must create new nodes within the linked
// list, and for larger N (ex: 400000) could be slowed by system allocation 
// of memory for the nodes.

import java.util.*;

public class Assig3B
{
	private MyStringBuilder2 s1;
	static int [] vals = {50000, 100000, 200000, 400000};
	
	public Assig3B()
	{
		for (int N: vals)
		{	
			System.out.println("Testing N = " + N + ":");
			testAppend(N);
			testDeleteBack();
			testInsertFront(N);
			testDeleteFront();
			testInsertMiddle(N);
			testDeleteMiddle();
			System.out.println();
		}
	}
	
	// Appending a character should be very fast (constant time) since the end of the 
	// list is accessible as the previous of the front.  This code demonstrates that.
	public void testAppend(int N)
	{
		s1 = new MyStringBuilder2("");
		long start = System.nanoTime();
		for (int i = 0; i < N; i++)
			s1.append("A");
		long stop = System.nanoTime();
		long delta = stop - start;
		double ave = (double) delta / N;
		System.out.println("Testing append: ");
		System.out.println("\t\tTotal time: " + delta + " ns for " + N + " appends");
		System.out.println("\t\tTime per append: " + ave + " ns");
	}
	
	// Due to the special case of deleting from the back of the list (going backward in
	// the circular list) the deleteCharAt() method at the end of the list COULD be very
	// very fast (constant time) if you checked for this special case.  However, if you
	// traversed forward to delete then this code will take a lot more time (linear
	// amount).  Either is ok, since were you were not required to explicitly test for
	// this special case.
	public void testDeleteBack()
	{
		int N = s1.length();
		long start = System.nanoTime();
		while (s1.length() > 0)
			s1.deleteCharAt(s1.length()-1);
		long stop = System.nanoTime();
		long delta = stop - start;
		double ave = (double) delta / N;
		System.out.println("Testing delete from back: ");
		System.out.println("\t\tTotal time: " + delta + " ns for " + N + " deletes");
		System.out.println("\t\tTime per delete: " + ave + " ns");
	}
	
	// Inserting at the front should clearly be very fast (constant time) in a linked list.
	public void testInsertFront(int N)
	{
		s1 = new MyStringBuilder2("");
		long start = System.nanoTime();
		for (int i = 0; i < N; i++)
			s1.insert(0, "A");
		long stop = System.nanoTime();
		long delta = stop - start;
		double ave = (double) delta / N;
		System.out.println("Testing insert(0): ");
		System.out.println("\t\tTotal time: " + delta + " ns for " + N + " inserts");
		System.out.println("\t\tTime per insert: " + ave + " ns");
	}

	// Deleting at the front should also be very fast (constant time) in a linked list.
	public void testDeleteFront()
	{
		int N = s1.length();
		long start = System.nanoTime();
		while (s1.length() > 0)
			s1.deleteCharAt(0);
		long stop = System.nanoTime();
		long delta = stop - start;
		double ave = (double) delta / N;
		System.out.println("Testing delete from front: ");
		System.out.println("\t\tTotal time: " + delta + " ns for " + N + " deletes");
		System.out.println("\t\tTime per delete: " + ave + " ns");
	}
	
	// Inserting in the middle will require a traversal of the list and the time will
	// clearly increase as the size of the list increases.  There is no special case
	// test that can help with this since the list does not have direct access.
	public void testInsertMiddle(int N)
	{
		s1 = new MyStringBuilder2("");
		long start = System.nanoTime();
		for (int i = 0; i < N; i++)
		{
			int loc = i/2;
			s1.insert(loc, "A");
		}
		long stop = System.nanoTime();
		long delta = stop - start;
		double ave = (double) delta / N;
		System.out.println("Testing insert(mid): ");
		System.out.println("\t\tTotal time: " + delta + " ns for " + N + " inserts");
		System.out.println("\t\tTime per insert: " + ave + " ns");
	}
	
	// Deleting in the middle will also require a traversal, so the time will be
	// proportional to the size of the list.
	public void testDeleteMiddle()
	{
		int N = s1.length();
		long start = System.nanoTime();
		while (s1.length() > 0)
		{
			int loc = s1.length() / 2;
			s1.deleteCharAt(loc);
		}
		long stop = System.nanoTime();
		long delta = stop - start;
		double ave = (double) delta / N;
		System.out.println("Testing delete from middle: ");
		System.out.println("\t\tTotal time: " + delta + " ns for " + N + " deletes");
		System.out.println("\t\tTime per delete: " + ave + " ns");
	}	
	
	public static void main(String [] args)
	{
		Assig3B E = new Assig3B();
	}
}


