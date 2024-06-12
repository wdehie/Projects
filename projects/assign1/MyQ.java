// CS 0445 Summer 2024
// Assignment 1 MyQ<T> interface
// Carefully read the specifications for each of the operations and
// implement them correctly in your MultiDS<T> class.

// The MyQ<T> interface extends the author's QueueInterface<T> and
// adds two new methods, size() and capacity().  These methods are
// very simple, returning the number of items in the queue (size) and 
// the length of the underlying array (capacity).

// For the details of the queue operations, see QueueInterface<T>.  Note
// that you will need QueueInterface.java in order to use this interface.
// You will also need EmptyQueueException.java, since some methods in 
// QueueInterface may throw this exception.

// For this assignment, you MUST implement the interface with the
// following requirements: 
// 1) The underlying data must be a simple Java array
// 2) No Java collection classes may be used in this implementation.  All of
//    your methods must act directly on the underlying array
// 3) Your enqueue() and dequeue() methods must require only a single data assignment
//    each. In other words, there should be no shifting to create or to fill
//    a space in your array.  To see how to implement your queue in this way,
//    see the Assignment 1 document and also read Section 11.7-11.16 of your text. 
//    You are not required to use this exact implementation but it should be similar.
// 4) The enqueue() method must always succeed (barring some extreme event). This
//    means that your implementation must have a way to dynamically resize your
//    underlying array when necessary.  Be careful about resizing -- it should not
//    affect the logical ordering of the MyQ.

public interface MyQ<T> extends QueueInterface<T>
{
	
	// Return the number of items currently in the MyQ.  Determining the
	// length of a queue can sometimes be very useful.
	public int size();
	
	// Return the length of the underlying data structure which is storing the
	// data.  In an array implementation, this will be the length of the array.
	// This method would not normally be part of a queue but is included here to
	// enable testing of your resizing operation.
	public int capacity();

}