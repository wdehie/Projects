// CS 0445 Fall Summer 2024
// Read this class and its comments very carefully to make sure you implement
// the class properly.  The data and public methods in this class are identical
// to those in MyStringBuilder. The only difference in this class and that from
// Assignment 2 is that your methods must not contain any loops.

// You cannot change the data or add any instance variables.  However, you may 
// (and will need to) add some private methods.

// No iteration (i.e. no loops) is allowed in this implementation. 

// For more details on the general functionality of most of these methods, 
// see the specifications of the method in the StringBuilder class from Assignment 2.  
public class MyStringBuilder2
{
	// These are the only two instance variables you are allowed to have.
	// See details of CNode class below.  In other words, you MAY NOT add
	// any additional instance variables to this class.  However, you may
	// use any method variables that you need within individual methods.
	// But remember that you may NOT use any variables of any other
	// linked list class or of the predefined StringBuilder or 
	// or StringBuffer class in any place in your code. You may only use the
	// String class where it is an argument or return type in a method.
	private CNode firstC;	// reference to front of list. This reference is necessary
							// to keep track of the list
	private int length;  	// number of characters in the list

	// You may also add any additional private methods that you need to
	// help with your implementation of the public methods.
	
	// I have provided the code for a constructor and the toString() method
	// below. Use these methods to help you to better understand what you 
	// need to do for the remaining methods.

	// Constructor to make a new MyStringBuilder2 from a String. The constructor
	// itself is NOT recursive - however, it calls a recursive method to do the
	// actual set up work.  This should be your general approach for all of the
	// methods, since the recursive methods typically need extra parameters that
	// are not given in the specification.
	public MyStringBuilder2(String s)
	{
		if (s != null && s.length() > 0)
			makeBuilder(s, 0);
		else  // no String so initialize empty MyStringBuilder2
		{
			firstC = null;
			length = 0;
		}
	}

	// Recursive method to set up a new MyStringBuilder2 from a String
	private void makeBuilder(String s, int pos)
	{
		// Recursive case - we have not finished going through the String
		if (pos < s.length()-1)
		{
			// Note how this is done - we make the recursive call FIRST, then
			// add the node before it.  In this way EVERY node we add is
			// the front node, and it enables us to avoid having to make a
			// special test for the front node.  However, many of your
			// methods will proceed in the normal front to back way.
			makeBuilder(s, pos+1);
			CNode temp = new CNode(s.charAt(pos));
			CNode back = firstC.prev;
			temp.prev = back;
			back.next = temp;
			temp.next = firstC;
			firstC.prev = temp;
			firstC = temp;
			length++;
		}
		else if (pos == s.length()-1) // Special case for last char in String
		{					// This is a base case and initializes
							// firstC in a circular way
			firstC = new CNode(s.charAt(pos));
			firstC.next = firstC;
			firstC.prev = firstC;
			length = 1;
		}
		else	// This case should never be reached, due to the way the 
			// constructor is set up. However, I included it as a
		{	// safeguard (in case some other method calls this one)
			length = 0;
			firstC = null;
		}
	}

	// Again note that the specified method is not actually recursive - rather it
	// calls a recursive method to do the work.  Note that in this case we also
	// create a char array before the recursive call, then pass it as a 
	// parameter, then construct and return a new string from the char array.
	// Carefully think about the parameters you will be passing to your recursive
	// methods.  Through them you must be able to move through the list and
	// reduce the "problem size" with each call.
	public String toString()
	{
		char [] c = new char[length];
		getString(c, 0, firstC);
		return (new String(c));
	}

	// Here we need the char array to store the characters, the pos value to
	// indicate the current index in the array and the curr node to access 
	// the data in the actual MyStringBuilder2. Note that these rec methods
	// are private - the user of the class should not be able to call them.
	private void getString(char [] c, int pos, CNode curr)
	{
		if (pos < length) // Not at end of the list
		{
			c[pos] = curr.data;  // put next char into array
			getString(c, pos+1, curr.next);  // recurse to next node and 
		}                                      // next pos in array
	}

	// Create a new MyStringBuilder2 initialized with the chars in array s
	public MyStringBuilder2(char [] s)
	{
		if (s == null || s.length == 0) { // handle null or empty case
		this.firstC = null;
		this.length = 0;
		} else {
		this.length = s.length;
		this.firstC = new CNode(s[0]); // initialize first node
		CNode last = createListRecursively(this.firstC, s, 1); // recursive call to build the list
		// making it circular doubly linked list
		last.next = this.firstC;
		this.firstC.prev = last;
		}
	}

	private CNode createListRecursively(CNode curr, char[] s, int index) {
		if (index < s.length) {
			CNode newNode = new CNode(s[index]);
			curr.next = newNode;
			newNode.prev = curr;
			return createListRecursively(newNode, s, index + 1);
		} else {
			return curr; // return the last node created
		}
	}

	// Copy constructor -- make a new MyStringBuilder2 from an old one.  Be sure
	// that you make new nodes for the copy.
	public MyStringBuilder2(MyStringBuilder2 old)
	{
		if (old == null || old.length == 0) {  // handle null or empty input
            this.firstC = null;
            this.length = 0;
        } else {
            this.length = old.length;
            this.firstC = new CNode(old.firstC.data);  // initialize first node
            copyListRecursively(this.firstC, old.firstC.next, old.firstC); // start recursion
        }
    }

    private CNode copyListRecursively(CNode currNew, CNode currOld, CNode oldFirst) {
        if (currOld != oldFirst) { // stop when we have looped back to the first node
            CNode newNode = new CNode(currOld.data);
            currNew.next = newNode;
            newNode.prev = currNew;
            return copyListRecursively(newNode, currOld.next, oldFirst);
        } else {
            currNew.next = this.firstC; // complete the circle
            this.firstC.prev = currNew;
            return currNew;
        }
	}
	
	// Create a new empty MyStringBuilder2
	public MyStringBuilder2()
	{
		this.firstC = null;
        this.length = 0;
	}

	// Append MyStringBuilder2 b to the end of the current MyStringBuilder2, and
	// return the current MyStringBuilder2.  Be careful for special cases!
	public MyStringBuilder2 append(MyStringBuilder2 b)
	{
		if (b == null || b.length == 0) { // handle empty or null
            return this;
        }
        if (this.length == 0) {
            this.firstC = new CNode(b.firstC.data);
            this.length = b.length;
            appendBrecursively(this.firstC, b.firstC.next, b.firstC);
        } else {
            CNode lastNodeThis = this.firstC.prev; // get last node of this
            CNode firstNodeB = new CNode(b.firstC.data);
            lastNodeThis.next = firstNodeB;
            firstNodeB.prev = lastNodeThis;
            appendBrecursively(firstNodeB, b.firstC.next, b.firstC);
            this.length += b.length;
        }
        return this;
    }

    private void appendBrecursively(CNode currNew, CNode currOld, CNode oldFirst) {
        if (currOld != oldFirst) {
            CNode newNode = new CNode(currOld.data);
            currNew.next = newNode;
            newNode.prev = currNew; //update
            appendBrecursively(newNode, currOld.next, oldFirst); //call recursion
        } else {
            currNew.next = this.firstC;
            this.firstC.prev = currNew;
        }
	}

	// Append String s to the end of the current MyStringBuilder2, and return
	// the current MyStringBuilder2.  Be careful for special cases!
	public MyStringBuilder2 append(String s)
	{
		if (s == null || s.length() == 0) { // check null or empty
			return this;
		}
		if (this.length == 0) {
			this.firstC = new CNode(s.charAt(0)); // initialize if empty
			this.length = s.length();
			appendSRecursively(this.firstC, s, 1);
		} else {
			CNode lastNode = this.firstC.prev; // get last node
			this.length += s.length();
			appendSRecursively(lastNode, s, 0);
		}
		return this;
	}

	private void appendSRecursively(CNode curr, String s, int index) {
		if (index < s.length()) {
			CNode newNode = new CNode(s.charAt(index));
			curr.next = newNode;
			newNode.prev = curr;
			appendSRecursively(newNode, s, index + 1);
		} else {
			curr.next = this.firstC;
			this.firstC.prev = curr;
		}
	}

	// Append char array c to the end of the current MyStringBuilder2, and
	// return the current MyStringBuilder2.  Be careful for special cases!
	public MyStringBuilder2 append(char [] c)
	{
		if (c == null || c.length == 0) {//special condition
			return this;
		}
		if (this.length == 0) { 
			this.firstC = new CNode(c[0]);
			this.length = c.length;
			appendRecursively(this.firstC, c, 1);//call
		} else {
			CNode lastNode = this.firstC.prev;
			this.length += c.length;
			appendRecursively(lastNode, c, 0);//call
		}
		return this;
	}

    private void appendRecursively(CNode curr, char[] c, int index) {
		if (index < c.length) {
			CNode newNode = new CNode(c[index]);
			curr.next = newNode;
			newNode.prev = curr;//update
			appendRecursively(newNode, c, index + 1); //call
		} else {
			curr.next = this.firstC;
			this.firstC.prev = curr;//update
		}
    }

	// Append char c to the end of the current MyStringBuilder2, and
	// return the current MyStringBuilder2.  Be careful for special cases!
	public MyStringBuilder2 append(char c)
	{
		if (this.length == 0) { // check if empty
            this.firstC = new CNode(c); 
            this.firstC.next = this.firstC; // link
            this.firstC.prev = this.firstC;
            this.length = 1; // update length
        } else {
            appendCRecursively(this.firstC, c); // call the recursive helper
        }
        return this;
    }

    private void appendCRecursively(CNode current, char c) {
        if (current.next == this.firstC) { // base case: last node
            CNode newNode = new CNode(c);
            newNode.next = this.firstC;
            newNode.prev = current;
            current.next = newNode;
            this.firstC.prev = newNode;
            this.length++; // update length
        } else {
            appendCRecursively(current.next, c); // recursive call
        }
    }

	// Return the character at location "index" in the current MyStringBuilder2.
	// If index is invalid, throw an IndexOutOfBoundsException.
	public char charAt(int index)
	{
		if (index < 0 || index >= this.length) { // handle special case
            throw new IndexOutOfBoundsException();
        }
        return charAtRecursively(this.firstC, index);
    }

    private char charAtRecursively(CNode curr, int index) {
        if (index == 0) {
            return curr.data; // base case: index is 0
        }
        return charAtRecursively(curr.next, index - 1); // recursive call with decremented index
    }

	// Delete the characters from index "start" to index "end" - 1 in the
	// current MyStringBuilder2, and return the current MyStringBuilder2.
	// If "start" is invalid or "end" <= "start" do nothing (just return the
	// MyStringBuilder2 as is). If "end" is past the end of the MyStringBuilder2, 
	// only remove up until the end of the MyStringBuilder2. Be careful for 
	// special cases!
	public MyStringBuilder2 delete(int start, int end) {
		if (start < 0 || start >= this.length || end <= start) {
			return this; // Base case: invalid indices or no deletion needed
		}
		if (end > this.length){
			end = this.length;
		}
		// Recursive case: find the nodes at start and end indices
		CNode startNode = findNode(this.firstC, 0, Math.max(0, start)); // Ensure non-negative start index
   		CNode endNode = findNode(this.firstC, 0, Math.min(this.length, end)); // Ensure valid end index

		// Update pointers to remove nodes
		if (start == 0) {
			this.firstC = endNode;
		} else {
			startNode.prev.next = endNode;
		}
		endNode.prev = startNode.prev;
		this.length -= (end - start);
	
		// Adjust circular references
		if (this.length == 0) {
			this.firstC = null;
		} else {
			this.firstC.prev.next = this.firstC;
			this.firstC.prev = this.firstC.prev;
		}
	
		return this;
	}
	
	// Helper method to find the node at a given index recursively
	private CNode findNode(CNode current, int currentIndex, int targetIndex) {
		if (currentIndex == targetIndex) {
			return current;
		}
		return findNode(current.next, currentIndex + 1, targetIndex);
	}
	

	// Delete the character at location "index" from the current
	// MyStringBuilder2 and return the current MyStringBuilder2.  If "index" is
	// invalid, do nothing (just return the MyStringBuilder2 as is).
	// Be careful for special cases!
	public MyStringBuilder2 deleteCharAt(int index) {
		if (index < 0 || index >= this.length) {
			return this;
		}
	
		// Call the recursive helper function
		this.firstC = deleteCharAtRecursive(this.firstC, index);
	
		this.length--;
		return this;
	}
	
	private CNode deleteCharAtRecursive(CNode curr, int index) {
		if (index == 0) {
			// base case: remove the first character
			return curr.next;
		}
	
		// recursive case: Move to the next character
		curr.next = deleteCharAtRecursive(curr.next, index - 1);
		return curr;
	}
		

	// Find and return the index within the current MyStringBuilder2 where
	// String str first matches a sequence of characters within the current
	// MyStringBuilder2.  If str does not match any sequence of characters
	// within the current MyStringBuilder2, return -1.  Think carefully about
	// what you need to do for this method before implementing it.
	public int indexOf(String str)
	{
		if (str == null || str.length() == 0 || this.length == 0) {
			return -1;
		}
	
		return indexOfRecursive(str, 0, this.firstC);
	}
	
	private int indexOfRecursive(String str, int index, CNode curr) {
		if (index + str.length() > this.length) {
			// check the remaining part of the list is shorter than the search string
			return -1;
		}
	
		if (matches(curr, str, 0)) {
			return index;
		}
	
		if (curr.next != null) {
			return indexOfRecursive(str, index + 1, curr.next);
		}
	
		return -1;
	}
	
	private boolean matches(CNode curr, String str, int strIndex) {
		if (strIndex == str.length()) {
			return true;
		}
	
		if (curr == null || curr.data != str.charAt(strIndex)) {
			return false;
		}
	
		return matches(curr.next, str, strIndex + 1); //call
	}

	// Insert String str into the current MyStringBuilder2 starting at index
	// "offset" and return the current MyStringBuilder2.  if "offset" == 
	// length, this is the same as append.  If "offset" is invalid
	// do nothing.
		// Insert String str into the current MyStringBuilder starting at index
	// "offset" and return the current MyStringBuilder.  if "offset" == 
	// length, this is the same as append.  If "offset" is invalid
	// do nothing.
	public MyStringBuilder2 insert(int offset, String str) {
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

	// Insert character c into the current MyStringBuilder2 at index
	// "offset" and return the current MyStringBuilder2.  If "offset" ==
	// length, this is the same as append.  If "offset" is invalid, 
	// do nothing.
	public MyStringBuilder2 insert(int offset, char c)
	{
		if (offset < 0 || offset > this.length) {
			// Invalid offset, do nothing
			return this;
		}
	
		if (offset == this.length) {
			// If offset is equal to length, same as append
			return this.append(c);
		}
	
		// Start the recursive traversal from the first node
		this.firstC = insertCRecursive(this.firstC, offset, c);
	
		this.length++;
	
		return this;
	}
	
	private CNode insertCRecursive(CNode curr, int offset, char c) {
		if (offset == 0) {
			// Insert the new node here
			CNode newNode = new CNode(c);
			newNode.next = curr;
			curr.prev = newNode;
			return newNode;
		}
	
		// Recurse to the next node
		curr.next = insertCRecursive(curr.next, offset - 1, c);
		curr.next.prev = curr;
	
		return curr;
	}
	

	// Return the length of the current MyStringBuilder2
	public int length()
	{
		return this.length;
	}

	// Delete the substring from "start" to "end" - 1 in the current
	// MyStringBuilder2, then insert String "str" into the current
	// MyStringBuilder2 starting at index "start", then return the current
	// MyStringBuilder2.  If "start" is invalid or "end" <= "start", do nothing.
	// If "end" is past the end of the MyStringBuilder2, only delete until the
	// end of the MyStringBuilder2, then insert.  This method should be done
	// as efficiently as possible.  In particular, you may NOT simply call
	// the delete() method followed by the insert() method, since that will
	// require an extra traversal of the linked list.
	public MyStringBuilder2 replace(int start, int end, String str) {
    if (start < 0 || start >= this.length || end <= start) {
        return this; // base case: invalid start or end
    }

    if (end > this.length) {
        end = this.length; // adjust end if it exceeds length
    }

    CNode startNode = findNode(this.firstC, start); //get node
	//System.out.println("Node:"+startNode.data);
    CNode endNode = findNode(this.firstC, end);
	//System.out.println("End node:"+endNode.data);

    // remove the substring recursively
    removeSubstringRecursive(startNode, endNode);

    // insert the replacement string recursively
    if (start == this.length) { //if start at end, just append
        append(str);
    } else {
        insertStringRecursive(findNode(this.firstC, start), str); //call recursive mehtod
    }

    return this;
}

private CNode findNode(CNode node, int index) { //help to find node at index
    if (index == 0 || node == null) {
        return node;
    }
    return findNode(node.next, index - 1);
}

private void removeSubstringRecursive(CNode startNode, CNode endNode) {
    // base case: if startNode is null or reaches endNode
    if (startNode == null || startNode == endNode) {
        return;
    }
    // if startNode is the first node, update firstC
    if (startNode == this.firstC) {
        this.firstC = startNode.next;
    }
    // if startNode is not the first node
    if (startNode.prev != null) {
        startNode.prev.next = startNode.next;
    }
    // if startNode is not the last node
    if (startNode.next != null) {
        startNode.next.prev = startNode.prev;
    }
    this.length--;

    removeSubstringRecursive(startNode.next, endNode);
}

private void insertStringRecursive(CNode curr, String str) {
    if (str.isEmpty()) {
        return; //if nothing to insert
    }
    CNode newNode = new CNode(str.charAt(str.length() - 1)); // Insert last character
    newNode.next = curr;
    newNode.prev = curr.prev;
    curr.prev.next = newNode;
    curr.prev = newNode;
    if (curr == this.firstC) {
        this.firstC = newNode; // Update firstC if necessary
    }
    this.length++;
    insertStringRecursive(newNode, str.substring(0, str.length() - 1));
}


	// Return as a String the substring of characters from index "start" to
	// index "end" - 1 within the current MyStringBuilder2. This method is
	// implemented in In Class Exercise 3
	public String substring(int start, int end)
	{
		if (start < 0 || start >= this.length || end <= start || end > this.length) {
			return null;  // return null if start or end are invalid
		}
		StringBuilder builder = new StringBuilder();
		substringRecursive(start, end, this.firstC, builder);
		return builder.toString();
	}
	
	private void substringRecursive(int start, int end, CNode node, StringBuilder builder) {
		if (start == 0) {
			if (end == 0) {
				return; // base case: reached the end position
			}
			builder.append(node.data);
			substringRecursive(start, end - 1, node.next, builder); 
		} else {
			substringRecursive(start - 1, end - 1, node.next, builder); 
		}
	}

	// Return the entire contents of the current MyStringBuilder2 as a String
	// in the reverse of the order that it is stored. Recitation 5 should be
	// of great help for this method.
	public String revString() {
		char[] c = new char[this.length];
		revStringHelper(this.firstC.prev, c, 0);
		return new String(c);
	}
	
	private void revStringHelper(CNode curr, char[] c, int index) {
		if (curr == null|| index >= c.length) {
			return; // base case: reached the beginning of the list
		}
		c[index] = curr.data;
		revStringHelper(curr.prev, c, index + 1); //call
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
	// since it is an inner class, the MyStringBuilder2 class MAY access the
	// data and next fields directly.
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

