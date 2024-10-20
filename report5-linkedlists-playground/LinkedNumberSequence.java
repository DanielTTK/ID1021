// LinkedNumberSequence.java

/****************************************************************
 * 
 * LinkedNumberSequence represents a sequence of real numbers.
 * Such a sequence is defined by the interface NumberSequence.
 * The class uses linked nodes to store the numbers in the sequence.
 * 
 * Author
 * Fadil Galjic
 * 
 ****************************************************************/

public class LinkedNumberSequence {
	private class Node {
		public double number;
		public Node next;

		public Node(double number) {
			this.number = number;
			next = null;
		}
	}

	// the first node in the node-sequence
	private Node first;

	// create the sequence
	public LinkedNumberSequence(double[] numbers) {
		if (numbers.length < 2)
			throw new IllegalArgumentException("not a sequence");

		first = new Node(numbers[0]);
		Node n = first;
		for (int i = 1; i < numbers.length; i++) {
			n.next = new Node(numbers[i]);
			n = n.next;
		}
	}

	// toString returns the character string representing this
	// sequence
	public String toString() {
		String s = "";
		Node n = first;
		while (n.next != null) {
			s = s + n.number + ", ";
			n = n.next;
		}
		s = s + n.number;

		return s;
	}

	// add code here
	// length returns the number of numbers in this sequence.
	public int length() {
		int numberOfElements = 0;
		Node n = first;
		while (n != null) {
			numberOfElements++;
			n = n.next;
		}
		return numberOfElements;
	}

	// upperBound returns an upper bound for this sequence.
	public double upperBound() {
		double top = -10000;

		Node n = first; // n becomes first
		while (n.next != null) {
			if (top <= n.number) {
				top = n.number;
			}
			n = n.next;
		}

		return top; // returns 0 if no numbers
	}

	// lowerBound returns a lower bound for this sequence.
	public double lowerBound() {
		double bottom = 10000.0;

		Node n = first;
		while (n.next != null) {
			if (bottom >= n.number) {
				bottom = n.number;
			}
			n = n.next;
		}

		return bottom; // returns 0 if no numbers
	}

	// numberAt returns the number at the specified position
	// in this sequence.
	// The method throws IndexOutOfBoundsException if the
	// position is wrong.
	public double numberAt(int position)
			throws IndexOutOfBoundsException {
		Node n = first;

		int j = length();
		if (j < position - 1 || position < 0) {
			throw new IndexOutOfBoundsException("Position " + position + " out of bounds");
		}

		for (int i = 0; i < position; i++) {
			n = n.next;
		}
		return n.number;

	}

	// positionOf returns the position of the first occurance of
	// the specified number in this sequence.
	// If the number is not present in the sequence, -1 is returned.
	public int positionOf(double number) {
		int i = 1;
		Node n = first;
		while (n.number != number) {
			n = n.next;
			i++;
		}
		return i;
	}

	// isIncreasing returns true if this sequence is increasing,
	// else false is returned.
	public boolean isIncreasing() {
		boolean isTrue = false;
		Node n = first;
		while (n.next != null) {
			if ((n.next.number - n.number) < n.number) {
				isTrue = true;
			} else {
				isTrue = false;
				break;
			}
			n = n.next;
		}
		return isTrue;
	}

	// isDecreasing returns true if this sequence is decreasing,
	// else false is returned.
	public boolean isDecreasing() {
		boolean isTrue = false;
		Node n = first;
		while (n.next != null) {
			if ((n.next.number - n.number) > n.number) {
				isTrue = true;
			} else {
				isTrue = false;
				break;
			}
			n = n.next;
		}
		return isTrue;
	}

	// contains returns true if this sequence contains the
	// specified number, else false is returned.
	public boolean contains(double number) {
		boolean isTrue = false;
		Node n = first;

		while (n.next != null) {
			if (n.number == number) {
				isTrue = true;
				break;
			}
			n = n.next;
		}
		return isTrue;
	}

	// add adds the specified number to the end of this sequence.
	public void add(double number) {
		Node node = new Node(number);
		if (first == null) // if it's empty, we add node to first
			first = node;
		else {
			Node n = first; // n becomes first
			while (n.next != null)
				n = n.next; // scrolls to last node, while there is a next, n becomes next node
			n.next = node; // after we found last node, we go one extra step and add 'node' to it.
		}
	}

	// insert inserts the given number at the specified position
	// in this sequence.
	// The method throws IndexOutOfBoundsException if the
	// position is wrong.
	public void insert(int position, double number)
			throws IndexOutOfBoundsException {
		// Node n = first;
		int j = length();
		if (j < position - 1 || position < 0) {
			throw new IndexOutOfBoundsException("Position " + position + " out of bounds");
		}

		Node node = new Node(number);
		if (position == 0) {
			node.next = first;
			first = node;
		} else {
			Node n = first;
			for (int i = 1; i < position; i++) {
				n = n.next; // iterate until we get one element away from var 'position'
			}
			node.next = n.next; // the inserted nodes' next becomes the previous nodes' next
			n.next = node; // and the previous nodes' next points to inserted node
		}
	}

	// removeAt removes the number at the specified position
	// in this sequence.
	// The method throws IndexOutOfBoundsException if the
	// position is wrong.
	// The method throws IllegalStateException if there are
	// just two numbers in the sequence.
	public void removeAt(int position)
			throws IndexOutOfBoundsException, IllegalStateException {
		int j = length();
		if (j < (position - 1) || position < 0) {
			throw new IndexOutOfBoundsException("position " + position + " out of bounds");
		} else if (j <= 2) {
			throw new IllegalStateException("length " + j + " is equal or less than 2");
		}
		Node n = first;
		for (int i = 1; i < position; i++) {
			n = n.next; // iterate until we get one element away from var 'position'
		}
		n.next = n.next.next; // skips one node and refers directly to node after the one we want to remove.
	}

	// asArray returns an array containing all of the numbers in
	// this sequence, in the same order as in the sequence.
	public double[] asArray() {
		Node n = first;
		double[] array = new double[length()];
		for (int i = 0; i < array.length; i++) {
			array[i] = n.number;
			n = n.next;
		}
		return array;
	}
}