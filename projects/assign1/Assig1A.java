// CS 0445 Summer 2024
// Assig1A driver program.  This program must work as is with your
// MultiDS<T> class.  Look carefully at all of the method calls so that
// you create your MultiDS<T> methods correctly.  For example, note the
// constructor calls and the toString() method call.  The output should
// be identical to my sample output, with the exception of the result of
// the shuffle() methods -- since this should be random yours should not
// match mine.
public class Assig1A
{
	public static void main(String [] args)
	{
		// Testing constructor and MyQ<T> interface
		MyQ<Integer> theQ = new MultiDS<Integer>(4);

		// Testing enqueue and resizing
		for (int i = 0; i < 10; i++)
		{
			Integer newItem = Integer.valueOf(2 * i);
			theQ.enqueue(newItem);
		}
		System.out.println("Q size: " + theQ.size());
		System.out.println("Q capacity: " + theQ.capacity());
		System.out.println(theQ.toString());

		// Testing dequeue
		while (!(theQ.isEmpty()))
		{
			Integer oldItem = theQ.dequeue();
			System.out.println(oldItem + " retrieved from Q");
		}
		try
		{
			Integer noItem = theQ.dequeue();
			System.out.println("Non-existent item is " + noItem);
		}
		catch (EmptyQueueException e)
		{
			System.out.println("Attempt to remove from empty queue!");
		}
		System.out.println();
		
		// Testing array management
		int count = 1;
		MyQ<String> theQ2 = new MultiDS<String>(5);
		String theItem = new String("Item " + count);
		System.out.println("Adding " + theItem);
		theQ2.enqueue(theItem);
		count++;
		theItem = new String("Item " + count);
		System.out.println("Adding " + theItem);
		theQ2.enqueue(theItem);
		for (int i = 0; i < 10; i++)
		{
			count++;
			theItem = new String("Item " + count);
			System.out.println("Adding " + theItem);
			theQ2.enqueue(theItem);
			theItem = theQ2.dequeue();
			System.out.println("Removing " + theItem);
		}
		System.out.println("Q size: " + theQ2.size());
		System.out.println("Q capacity: " + theQ2.capacity());
		System.out.println(theQ2.toString());
		
		for (int i = 0; i < 5; i++)
		{
			count++;
			theItem = new String("Item " + count);
			System.out.println("Adding " + theItem);
			theQ2.enqueue(theItem);
		}
		System.out.println("Q size: " + theQ2.size());
		System.out.println("Q capacity: " + theQ2.capacity());
		System.out.println(theQ2.toString());

		// This code will test the toString() method and the Shufflable
		// interface.
		System.out.println("\nAbout to test shuffle method...");
		MultiDS<Integer> newDS = new MultiDS<Integer>(15);
		for (int i = 0; i < 8; i++)
		{
			newDS.enqueue(Integer.valueOf(i));
		}
		System.out.println(newDS.toString());
		
		System.out.println("Shuffling...");
		newDS.shuffle();
		System.out.println(newDS.toString());
		System.out.println("Dequeuing 2 and enqueuing 1");
		Integer bogus = newDS.dequeue();
		bogus = newDS.dequeue();
		newDS.enqueue(Integer.valueOf(22));
		System.out.println(newDS.toString());
		System.out.println("Shuffling again");
		newDS.shuffle();
		System.out.println(newDS.toString());
	}
}
