import java.util.HashSet;

public class Chapter2 {

	public static void main(String[] args) {
		LinkedList x = new LinkedList();
		x.addElementToTheEndandReturnNewElement(1);
		x.addElementToTheEndandReturnNewElement(2);
		x.addElementToTheEndandReturnNewElement(3);
		x.addElementToTheEndandReturnNewElement(2);
		x.addElementToTheEndandReturnNewElement(5);
		

		x.isPaladrom();
		
	}
}

class LinkedList {
	int intValue;
	String stringValue;
	LinkedList next = null;

	public LinkedList addElementToTheEndandReturnNewElement(int value) {
		LinkedList newElement = new LinkedList();
		newElement.intValue = value;
		LinkedList traverse = this;
		while (traverse.next != null) {
			traverse = traverse.next;
		}
		traverse.next = newElement;
		return newElement;
	}
	
	public void addElementToTheEnd(int value) {
		LinkedList newElement = new LinkedList();
		newElement.intValue = value;
		LinkedList traverse = this;
		while (traverse.next != null) {
			traverse = traverse.next;
		}
		traverse.next = newElement;
	}

	public void addElementAtTheBegining(int value) {
		LinkedList newElement = new LinkedList();
		newElement.intValue = value;
		newElement.next = this.next;
		this.next = newElement;
	}

	public void deleteAllOccuranceOfElement(int value) {
		LinkedList traverse = this;
		while (traverse.next != null) {
			if (traverse.next.intValue == value) {
				traverse.next = traverse.next.next;
			} else {
				traverse = traverse.next;
			}
		}
	}

	/**
	 * question 1
	 */
	public void removeDuplicate() {
		// Start from begining,
		// check the element and then delete all its occurance

		LinkedList traverse = this;
		while (traverse.next != null) {
			int valueToBeDeleted = traverse.next.intValue;
			traverse.next.deleteAllOccuranceOfElement(valueToBeDeleted);
			traverse = traverse.next;
		}
	}

	/**
	 * question 2
	 */
	public void findKElementFromLast(int k) {
		// find the length
		// calculate the kth last element

		// another method use 2 pointers, let them walk together with k distance
		// between them
		int length = this.length();
		int kthElement = length - k - 1;
		if (kthElement >= 0) {
			LinkedList traverse = this.next;
			int traverseCount = kthElement;
			while (traverseCount > 0) {
				traverse = traverse.next;
				traverseCount--;
			}
			System.out.print("the element is" + traverse.intValue);
		} else {
			System.out.println("there is no such an element");
		}

	}

	/**
	 * question 3
	 * 
	 * @return
	 */
	public void deleteNodeInTheMiddle() {
		// we are deleting if the length is odd number
		int length = this.length();
		if (length > 1 && length % 2 != 0) {
			int middleElement = length / 2;

			LinkedList traverse = this;
			int traverseCount = middleElement;
			while (traverseCount > 0) {
				traverse = traverse.next;
				traverseCount--;
			}
			traverse.next = traverse.next.next;
		}

	}

	/**
	 * question 4
	 * 
	 * @return
	 */
	public LinkedList partitionAroundX(int x) {
		LinkedList newList = new LinkedList();
		LinkedList traverse = this.next;
		while (traverse != null) {
			if (traverse.intValue < x) {
				newList.addElementAtTheBegining(traverse.intValue);
			} else {
				newList.addElementToTheEnd(traverse.intValue);
			}
			traverse = traverse.next;
		}
		return newList;

	}

	/**
	 * question 5, adding number in reverse order
	 * 
	 * @param first
	 * @param second
	 * @return
	 */
	public LinkedList addNumbers(LinkedList first, LinkedList second) {
		// we assum here that the numbers has the same length
		LinkedList result = new LinkedList();
		LinkedList travesreFirst = first.next;
		LinkedList traverseSecond = second.next;
		int valueForNextRound = 0;
		while (travesreFirst != null && traverseSecond != null) {
			int sum = travesreFirst.intValue + traverseSecond.intValue;
			result.addElementAtTheBegining((sum + valueForNextRound) % 10);
			valueForNextRound = (sum + valueForNextRound) / 10;
			travesreFirst = travesreFirst.next;
			traverseSecond = traverseSecond.next;
		}
		if (valueForNextRound != 0) {
			result.addElementAtTheBegining(valueForNextRound);
		}
		return result;
	}

	/**
	 * question 5, adding number right order
	 * 
	 * @return
	 */

	private void addNumbersRightOrderHelper(LinkedList first,
			LinkedList second, LinkedList result, int[] extra) {
		// we assum here that the numbers has the same length
		if (first.next != null) {
			addNumbersRightOrderHelper(first.next, second.next, result, extra);
		}
		int sum = first.intValue + second.intValue;
		result.addElementAtTheBegining((sum + extra[0]) % 10);
		extra[0] = (sum + extra[0]) / 10;
	}

	public LinkedList addNumbersRightOrder(LinkedList first, LinkedList second) {
		// we assum here that the numbers has the same length
		LinkedList result = new LinkedList();
		int[] extra = new int[] { 0 };
		addNumbersRightOrderHelper(first.next, second.next, result, extra);
		if (extra[0] != 0) {
			result.addElementAtTheBegining(extra[0]);
		}
		return result;

	}

	/**
	 * question 6
	 */
	public void findCircularBeginning() {
		// we will use a hashtable here to detect the point
		// we will put the address of the node in the hashtable

		HashSet<LinkedList> x = new HashSet<LinkedList>();
		LinkedList traverse = this.next;
		while (traverse != null) {
			if (x.contains(traverse)) {
				System.out.println(traverse.intValue);
				break;
			} else {
				x.add(traverse);
				traverse = traverse.next;
			}
		}
		System.out.println("there is no loop");

	}
	
	/**
	 *question 6; another solution is to use 2 pointers, check the solution
	 * @return
	 */
	public void findCircularStart(){
		LinkedList traverseSlow = this;
		LinkedList traverseFast = this;
		while (traverseSlow.next !=null && traverseFast.next!=null && traverseFast.next.next !=null){
			traverseSlow = traverseSlow.next;
			traverseFast = traverseFast.next.next;
			if (traverseSlow == traverseFast){
				LinkedList newTravel = this;
				while (newTravel != traverseSlow){
					newTravel = newTravel.next;
					traverseSlow = traverseSlow.next;
				}
				System.out.print(newTravel.intValue);
				break;
			}
		}
	}
	
	/**
	 * question 7, solve the recursive way
	 */
	public void isPaladrom(){
		boolean[]result=new boolean[]{true};
		LinkedList[] first = new LinkedList[1];
		first[0]=this.next;
		isPalandromRecursive(this.next, first, result);
		System.out.println(result[0]);
	}
	
	public void isPalandromRecursive(LinkedList element, LinkedList[] firstElment, boolean[] result){
		if (element != null){
			isPalandromRecursive(element.next, firstElment, result);
		} else {
			return;
		}
		if(element.intValue != firstElment[0].intValue){
			result[0] = false;
		}
		firstElment[0] = firstElment[0].next;
	}

	public int length() {
		LinkedList traverse = this;
		int length = 0;
		while (traverse.next != null) {
			length++;
			traverse = traverse.next;
		}
		return length;
	}

	public void printList() {
		LinkedList traverse = this;
		while (traverse.next != null) {
			traverse = traverse.next;
			System.out.print(traverse.intValue + " ");
		}
	}
}