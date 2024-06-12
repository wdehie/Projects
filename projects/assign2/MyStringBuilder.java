// CS 0445 Summer 2024
// Read this class and its comments very carefully to make sure you implement
// the class properly.  Note the items that are required and that cannot be
// altered!  Generally speaking you will implement your MyStringBuilder using
// a circular, doubly linked list of nodes.  See more comments below on the
// specific requirements for the class.

// You should use this class as the starting point for your implementation. 
// Note that all of the methods are listed -- you need to fill in the method
// bodies.  Note that you may want to add one or more private methods to help
// with your implementation -- that is fine.

// For more details on the general functionality of most of these methods, 
// see the specifications of the similar method in the StringBuilder class.  
public class MyStringBuilder
{
	// These are the only two instance variables you are allowed to have.
	// See details of CNode class below.  In other words, you MAY NOT add
	// any additional instance variables to this class.  However, you may
	// use any method variables that you need within individual methods.
	// But remember that you may NOT use any variables of any other
	// linked list class or of the predefined StringBuilder or 
	// StringBuffer class in any place in your code.  You may only use the
	// String class where it is an argument or return type in a method.
	private CNode firstC;	// reference to front of list.  This reference is necessary
							// to keep track of the list
	private int length;  	// number of characters in the list

	// You may also add any additional private methods that you need to
	// help with your implementation of the public methods.

	// Create a new MyStringBuilder initialized with the chars in String s
	// Note: The implementation of this method is part of Recitation Exercise 4.
	// See Recitation Exercise 4 for some information and hints about the method.
	// If you are unable to complete it, you may obtain the solution for the 
	// Recitation Exercise from the course Canvas site.
	public MyStringBuilder(String s)

	{
		if (s == null || s.length() == 0)  // special case for empty String
		{
			firstC = null;
			length = 0;
		}
		else
		{
			firstC = new CNode(s.charAt(0));  // create first node
			length = 1;
			CNode currNode = firstC;
			// Iterate through remainder of the String, creating a new
			// node at the end of the list for each character.  Note
			// how the nodes are being linked and the current reference
			// being moved down the list.
			for (int i = 1; i < s.length(); i++)
			{
				CNode newNode = new CNode(s.charAt(i));  // create Node
				currNode.next = newNode;  	// link new node after current
				newNode.prev = currNode;	// line current before new node
				currNode = newNode;			// move down the list
				length++;
			}
			// After all nodes are created, connect end back to front to make
			// list circular
			currNode.next = firstC;
			firstC.prev = currNode;
		}
	}

	// Return the entire contents of the current MyStringBuilder as a String
	// For this method you should do the following:
	// 1) Create a character array of the appropriate length
	// 2) Fill the array with the proper characters from your MyStringBuilder
	// 3) Return a new String using the array as an argument, or
	//    return new String(charArray);
	// Note: The implementation of this method is part of Recitation Exercise 4.
	// See Recitation Exercise 4 for some information and hints about the method.
	// If you are unable to complete it, you may obtain the solution for the 
	// Recitation Exercise from the course Canvas site.
	public String toString()
	{
		char [] c = new char[length];
		int i = 0;
		CNode currNode = firstC;
		
		// Since list is circular, we cannot look for null in our loop.
		// Instead we count within our while loop to access each node.
		// Note that in this code we don't even access the prev references
		// since we are simply moving from front to back in the list.
		while (i < length)
		{
			c[i] = currNode.data;
			i++;
			currNode = currNode.next;
		}
		return new String(c);
	}

	// Create a new MyStringBuilder initialized with the chars in array s. 
	// You may NOT create a String from the parameter and call the first
	// constructor above.  Rather, you must build your MyStringBuilder by
	// accessing the characters in the char array directly.  However, you
	// can approach this in a way similar to the other constructor.
	public MyStringBuilder(char [] s)
	{	if (s == null || s.length == 0) {  // handle null or empty case
            this.firstC = null;
            this.length = 0;
        } else {
            this.firstC = new CNode(s[0]);  // initialize first node
            CNode curr = this.firstC;
            for (int i = 1; i < s.length; i++) {  // traverse
                CNode newNode = new CNode(s[i]);
                curr.next = newNode;
                newNode.prev = curr;
                curr = newNode;
            }
            // making it circular doubly linked list
            curr.next = this.firstC;
            this.firstC.prev = curr;
            this.length = s.length;
        }

	}
	
	// Copy constructor -- make a new MyStringBuilder from an old one.  Be sure
	// that you make new nodes for the copy (traversing the nodes in the original
	// MyStringBuilder as you do)
	public MyStringBuilder(MyStringBuilder old)
	{
        if (old == null || old.length == 0) {  // dandle null or empty input
            this.firstC = null;
            this.length = 0;
        } else {
            CNode temp = old.firstC;
            this.firstC = new CNode(temp.data);  // initialize first node
            CNode currNew = this.firstC;
            CNode currOld = temp.next;

            for (int i = 1; i < old.length; i++) {  // traverse 
                CNode newNode = new CNode(currOld.data);
                currNew.next = newNode;
                newNode.prev = currNew;
                currNew = newNode;
                currOld = currOld.next;
            }

            // making it circular doubly linked list
            currNew.next = this.firstC;
            this.firstC.prev = currNew;
            this.length = old.length;
        }
	}
	
	// Create a new empty MyStringBuilder
	public MyStringBuilder()
	{
		this.firstC = null;
        this.length = 0;
	}
	
	// Append MyStringBuilder b to the end of the current MyStringBuilder, and
	// return the current MyStringBuilder.  Be careful for special cases!  Note
	// that you cannot simply link the two MyStringBuilders together -- that is
	// very simple but it will intermingle the two objects, which you do not want.
	// Thus, you should copy the data in argument b to the end of the current
	// MyStringBuilder.
	public MyStringBuilder append(MyStringBuilder b) {
		if (b == null || b.length == 0) { //handle empty or null
			return this;
		}
		if (this.length == 0) {
			this.firstC = b.firstC;//assign reference
			this.length = b.length;
		} else {
			CNode lastNodeThis = this.firstC.prev; //get last node
			CNode lastNodeB = b.firstC.prev;
			lastNodeThis.next = b.firstC; //update pointer and lnk
			b.firstC.prev = lastNodeThis;
			lastNodeB.next = this.firstC;
			this.firstC.prev = lastNodeB;
	
			this.length += b.length;
		}
		return this;
	}	
	

	// Append String s to the end of the current MyStringBuilder, and return
	// the current MyStringBuilder.  Be careful for special cases!
	public MyStringBuilder append(String s) {
		if (s == null || s.length() == 0) { //check null or empty
			return this;
		}
		if (this.length == 0) {
			this.firstC = new CNode(s.charAt(0)); //initialize if empty
			CNode curr = this.firstC;
	
			for (int i = 1; i < s.length(); i++) {
				CNode newNode = new CNode(s.charAt(i));
				curr.next = newNode;
				newNode.prev = curr;
				curr = newNode;
			}
			curr.next = this.firstC;//update pointer
			this.firstC.prev = curr;
			this.length = s.length();
		} else {
			CNode lastNode = this.firstC.prev;
			for (int i = 0; i < s.length(); i++) { //iterate
				CNode newNode = new CNode(s.charAt(i));
				lastNode.next = newNode; //update pointer
				newNode.prev = lastNode;
				lastNode = newNode;
			}
		//circular doubly linked list
			lastNode.next = this.firstC;
			this.firstC.prev = lastNode;
			this.length += s.length();
		}
		return this;
	}	
	

	// Append char array c to the end of the current MyStringBuilder, and
	// return the current MyStringBuilder.  Be careful for special cases!
	public MyStringBuilder append(char[] c) {
		if (c == null || c.length == 0) { //check null or empty
			return this;
		}
		if (this.length == 0) { //handle 0 situation
			this.firstC = new CNode(c[0]);
			CNode curr = this.firstC;
			for (int i = 1; i < c.length; i++) { //iterate
				CNode newNode = new CNode(c[i]);//get node
				curr.next = newNode; //update pointer
				newNode.prev = curr;
				curr = newNode;
			}
			//make it circular doubly linked list
			curr.next = this.firstC;
			this.firstC.prev = curr;
			this.length = c.length;
		} else {
			CNode lastNode = this.firstC.prev;
			for (int i = 0; i < c.length; i++) { //same above
				CNode newNode = new CNode(c[i]);
				lastNode.next = newNode;
				newNode.prev = lastNode;
				lastNode = newNode;
			}
			lastNode.next = this.firstC; 
			this.firstC.prev = lastNode;
			this.length += c.length;
		}
		return this;
	}	
	

	// Append char c to the end of the current MyStringBuilder, and
	// return the current MyStringBuilder.  Be careful for special cases!
	public MyStringBuilder append(char c) {
		if (this.length == 0) { //check if empty
			this.firstC = new CNode(c); 
			this.firstC.next = this.firstC;//link
			this.firstC.prev = this.firstC;
			this.length = 1; //update length
		} else {
			CNode lastNode = this.firstC.prev;
			CNode newNode = new CNode(c);
			//link and make it circular
			lastNode.next = newNode;
			newNode.prev = lastNode;
			newNode.next = this.firstC;
			this.firstC.prev = newNode;
			this.length++; //update length
		}
		return this;
	}	
	
	// Return the character at location "index" in the current MyStringBuilder.
	// If index is invalid, throw an IndexOutOfBoundsException.
	public char charAt(int index) {
		if (index < 0 || index >= this.length) { //handle special case
			throw new IndexOutOfBoundsException();
		}
		CNode curr = this.firstC; //start at first
		for (int i = 0; i < index; i++) {
			curr = curr.next; //update pointer
		}
		return curr.data; //return data
	}
	

	// Delete the characters from index "start" to index "end" - 1 in the
	// current MyStringBuilder, and return the current MyStringBuilder.
	// If "start" is invalid or "end" <= "start" do nothing (just return the
	// MyStringBuilder as is).  If "end" is past the end of the MyStringBuilder, 
	// only remove up until the end of the MyStringBuilder. Be careful for 
	// special cases!
	public MyStringBuilder delete(int start, int end) {
		if (start < 0 || start >= this.length || end <= start) { //check special case
			return this;
		}
		if (end > this.length) { //adjust
			end = this.length;
		}
		CNode startNode = this.firstC;//get first
		for (int i = 0; i < start; i++) {
			startNode = startNode.next;
		}
		CNode endNode = startNode;//get end
		for (int i = start; i < end; i++) {
			endNode = endNode.next;
		}
		if (start == 0) { //set pointer
			this.firstC = endNode;
		} else {
			startNode.prev.next = endNode;
		}
		endNode.prev = startNode.prev; //update pointer
		this.length -= (end - start);
		if (this.length == 0) {
			this.firstC = null; //0 then null
		} else {
			this.firstC.prev.next = this.firstC;//make it a circular and doubly and linked list
			this.firstC.prev = this.firstC.prev;
		}
		return this;
	}
	
	

	// Delete the character at location "index" from the current
	// MyStringBuilder and return the current MyStringBuilder.  If "index" is
	// invalid, do nothing (just return the MyStringBuilder as is).  If "index"
	// is the last character in the MyStringBuilder, go backward in the list in
	// order to make this operation faster (since the last character is simply
	// the previous of the first character)
	// Be careful for special cases!
	public MyStringBuilder deleteCharAt(int index) {
		if (index < 0 || index >= this.length) {
			return this;
		}
	
		if (this.length == 1) {
			this.firstC = null;
			this.length = 0;
			return this;
		}
	
		CNode curr = this.firstC;
		for (int i = 0; i < index; i++) {
			curr = curr.next;
		}
	
		if (curr == this.firstC) {
			this.firstC = curr.next;
		}
	
		curr.prev.next = curr.next;
		curr.next.prev = curr.prev;
	
		this.length--;
	
		return this;
	}	

	

	// Find and return the index within the current MyStringBuilder where
	// String str first matches a sequence of characters within the current
	// MyStringBuilder.  If str does not match any sequence of characters
	// within the current MyStringBuilder, return -1.  Think carefully about
	// what you need to do for this method before implementing it.
	public int indexOf(String str) {
		if (str == null || str.length() == 0 || this.length == 0) {
			return -1;
		}
	
		CNode curr = this.firstC;
		for (int i = 0; i < this.length; i++) {
			CNode temp = curr;
			int j;
			for (j = 0; j < str.length(); j++) {
				if (temp.data != str.charAt(j)) {
					break;
				}
				temp = temp.next;
			}
			if (j == str.length()) {
				return i;
			}
			curr = curr.next;
		}
	
		return -1;
	}
	

	// Insert String str into the current MyStringBuilder starting at index
	// "offset" and return the current MyStringBuilder.  if "offset" == 
	// length, this is the same as append.  If "offset" is invalid
	// do nothing.
	public MyStringBuilder insert(int offset, String str) {
		if (offset < 0 || offset > this.length || str == null) {
			return this; // invalid offset or null string
		}
	
		if (str.length() == 0) {
			return this; // nothing to insert
		}
	
		if (offset == this.length) {
			return this.append(str); // inserting at the end is the same as appending
		}
	
		CNode curr = this.firstC;
	
		// Traverse to the insertion point
		for (int i = 0; i < offset; i++) {
			curr = curr.next;
		}
	
		// Insert the new characters
		for (int i = 0; i < str.length(); i++) {
			CNode newNode = new CNode(str.charAt(i));
			newNode.next = curr;
			newNode.prev = curr.prev;
			curr.prev.next = newNode;
			curr.prev = newNode;
			if (i == 0 && offset == 0) {
				this.firstC = newNode; // update firstC if inserting at the beginning
			}
		}
	
		this.length += str.length();
		return this;
	}
	
	// Insert character c into the current MyStringBuilder at index
	// "offset" and return the current MyStringBuilder.  If "offset" ==
	// length, this is the same as append.  If "offset" is invalid, 
	// do nothing.
	public MyStringBuilder insert(int offset, char c) {
		if (offset < 0 || offset > this.length) { //check special case
			return this;

		}
	
		if (offset == this.length) {

			return this.append(c);
		}
	
		CNode curr = this.firstC;  //get first
		for (int i = 0; i < offset; i++) {
			curr = curr.next;

		}
	
		CNode newNode = new CNode(c);
		CNode prevNode = curr.prev;
		prevNode.next = newNode; //make it link and circular and doubly 
		newNode.prev = prevNode;
		newNode.next = curr;
		curr.prev = newNode;
	
		this.length++;
	
		return this;
	}	
	

	// Return the length of the current MyStringBuilder
	public int length()
	{
		return this.length;
	}

	// Delete the substring from "start" to "end" - 1 in the current
	// MyStringBuilder, then insert String "str" into the current
	// MyStringBuilder starting at index "start", then return the current
	// MyStringBuilder.  If "start" is invalid or "end" <= "start", do nothing.
	// If "end" is past the end of the MyStringBuilder, only delete until the
	// end of the MyStringBuilder, then insert.  This method should be done
	// as efficiently as possible.  In particular, you may NOT simply call
	// the delete() method followed by the insert() method, since that will
	// require an extra traversal of the linked list.
	public MyStringBuilder replace(int start, int end, String str) {
		if (start < 0 || start >= this.length || end <= start) {
			return this; // invalid start or end
		}
	
		if (end > this.length) {

			end = this.length; // adjust end if it exceeds length
		}
	
		// start
		CNode startNode = this.firstC;
		for (int i = 0; i < start; i++) {
			startNode = startNode.next;
		}
		// end
		CNode endNode = startNode;
		for (int i = start; i < end; i++) {
			endNode = endNode.next;
		}
		// remove the substring
		CNode curr = startNode;
		while (curr != endNode) {
			curr.prev.next = curr.next;
			curr.next.prev = curr.prev;
			curr = curr.next;
			this.length--;
		}
		// insert the replacement string
		for (int i = 0; i < str.length(); i++) {
			CNode newNode = new CNode(str.charAt(i));
			newNode.next = curr; //circularand linked
			newNode.prev = curr.prev;
			curr.prev.next = newNode;
			curr.prev = newNode;
			if (i == 0 && start == 0) {
				this.firstC = newNode; // update firstC if inserting at the beginning
			}
			this.length++;//update length
		}
		return this;
	}	

	

	// Return as a String the substring of characters from index "start" to
	// index "end" - 1 within the current MyStringBuilder.  For this method
	// you should do the following:
	// 1) Create a character array of the appropriate length
	// 2) Fill the array with the proper characters from your MyStringBuilder
	// 3) Return a new String using the array as an argument, or
	//    return new String(charArray);
	public String substring(int start, int end) {
		if (start < 0 || start >= this.length || end <= start || end > this.length) {
			return null;  // return null if start or end are invalid
		}
		char[] c = new char[end - start];
		CNode curr = this.firstC;
		for (int i = 0; i < start; i++) {
			curr = curr.next; //update pointer
		}
		for (int i = 0; i < c.length; i++) {
			c[i] = curr.data;
			curr = curr.next; //get data update pointer
		}
		return new String(c);
	}
	

	// Return as a String the reverse of the contents of the MyStringBuilder.  Note
	// that this does NOT change the MyStringBuilder in any way.  See substring()
	// above for the basic approach.
	public String revString() {
		if (this.length == 0) { //check 0 length
			return "";
		}
		char[] c = new char[this.length]; //get char
		CNode curr = this.firstC.prev;
		for (int i = 0; i < this.length; i++) {
			c[i] = curr.data;
			curr = curr.prev; //update pointer
		}
		return new String(c);
	}

	//Extra Credit part:
	public int lastIndexOf(String str) {
		if (str == null || str.length() == 0) {
			return -1; // empty or null substring
		}
		CNode curr = this.firstC.prev; // start from the last node
		int index = this.length - 1; // index of the last character
	
		while (curr != null) {
			if (endsWith(curr, str)) {
				return index - str.length() + 1; // index of the rightmost occurrence
			}
			curr = curr.prev;
			index--;
		}
		return -1; // substring not found
	}
	
	
	// Helper method to check if the substring ends at the specified node
	private boolean endsWith(CNode node, String str) {
		if (str.length() == 0) {
			return true; // empty substring matches any node
		}
	
		int strIndex = str.length() - 1;
		while (node != null && strIndex >= 0 && node.data == str.charAt(strIndex)) {
			node = node.prev;
			strIndex--;
		}
	
		return strIndex < 0; // all characters of the substring matched
	}	
	
	
	// You must use this inner class exactly as specified below.  Note that
	// since it is an inner class, the MyStringBuilder class MAY access the
	// data, next and prev fields directly.
	private class CNode
	{
		private char data;
		private CNode next, prev;

		public CNode(char c)
		{
			data = c;
			next = null;
			prev = null;
		}

		public CNode(char c, CNode n, CNode p)
		{
			data = c;
			next = n;
			prev = p;
		}
	}
}

