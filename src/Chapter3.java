import java.util.ArrayList;

public class Chapter3 {
	public static void main(String[] args) throws Exception {
		Animal x= new Cat();
		Animal y = new Cat();
		Animal x1= new Cat();
		Animal y1 = new Cat();
		Animal x2= new Cat();
		Animal y2 = new Cat();
		Animal x3= new Cat();
		Animal y3 = new Dog();
		Animal x4= new Dog();
		Animal y4 = new Dog();
		Animal x5= new Dog();
		Animal y5 = new Dog();
		
		AnimalShelter test = new AnimalShelter();
		test.enqueue(x);
		test.enqueue(y);
		test.enqueue(x1);
		test.enqueue(y1);
		test.enqueue(x2);
		test.enqueue(y2);
		test.enqueue(x3);
		test.enqueue(x4);
		test.enqueue(y4);
		test.enqueue(x5);
		test.enqueue(y5);
		System.out.println(test.popAnimal("Dog").getName());
		System.out.println(test.popAnimal("Dog").getName());
		System.out.println(test.popAnimal("Dog").getName());
		System.out.println(test.popAny().getName());
		System.out.println(test.popAny().getName());
		System.out.println(test.popAny().getName());
		System.out.println(test.popAny().getName());
		System.out.println(test.popAny().getName());
		test.printAnimals();
		
		
		
	}

	public void hanoi(int disk, int from, int to, int spare) {
		if (disk == 1) {
			System.out.println("Move disk from " + from + " to " + to);
			return;
		}
		hanoi(disk - 1, from, spare, to);
		System.out.println("Move disk from " + from + " to " + to);
		hanoi(disk - 1, spare, to, from);
	}
	
	

}


/**
 * question 1
 * 
 * @author UC193514
 *
 */

class ThreeStacksFixedSize {
	int[] stack;
	int size;
	int[] stackElments = new int[] { 0, 0, 0 };

	public ThreeStacksFixedSize(int stackSize) {
		this.size = stackSize;
		this.stack = new int[stackSize * 3];
	}

	public void push(int value, int stackIndex) {
		if (this.stackElments[stackIndex] >= size) {
			throw new RuntimeException("Stack is full");
		} else {
			this.stackElments[stackIndex]++;
			this.stack[this.getArrayIndex(stackIndex)] = value;
		}
	}

	public int getArrayIndex(int stackIndex) {
		int index = stackIndex * this.size + this.stackElments[stackIndex] - 1;
		return index;
	}

	public int pop(int stackIndex) {
		if (this.stackElments[stackIndex] == 0) {
			throw new RuntimeException("Stack is empty");
		} else {
			int elments = this.stack[this.getArrayIndex(stackIndex)];
			this.stackElments[stackIndex]--;
			return elments;
		}
	}

	public void printStack() {
		for (int i = 0; i < this.stack.length; i++) {
			System.out.print(this.stack[i] + " ");
		}
	}
}

/**
 * question 2
 * 
 * @author UC193514
 *
 */

class StackWithMin {
	class Element {
		int value;
		int min;
	}

	ArrayList<Element> stack = new ArrayList<Element>();

	public void push(int value) {
		Element x = new Element();
		x.value = value;
		try {
			Element topElement = this.peekTop();
			if (x.value < topElement.value) {
				x.min = x.value;
			} else {
				x.min = topElement.min;
			}
		} catch (RuntimeException xx) {
			x.min = x.value;
		}
		this.stack.add(x);
	}

	public int pop() {
		if (this.stack.size() > 0) {
			return this.stack.remove(this.stack.size() - 1).value;
		} else {
			throw new RuntimeException();
		}
	}

	public int getMin() {
		if (this.stack.size() > 0) {
			return this.stack.get(this.stack.size() - 1).min;
		} else {
			throw new RuntimeException();
		}
	}

	public Element peekTop() {
		if (this.stack.size() > 0) {
			return this.stack.get(this.stack.size() - 1);
		} else {
			throw new RuntimeException();
		}
	}

	public void printStack() {
		for (Element x : this.stack) {
			System.out.println(x.value + "  " + x.min);
		}
	}
}

/**
 * question 3, many stacks
 * 
 * @author UC193514
 *
 */

class StackWithManyStacks {

	ArrayList<ArrayList<Integer>> stack = new ArrayList<ArrayList<Integer>>();
	int stackSize;

	public StackWithManyStacks(int size) {
		this.stackSize = size;
	}

	public void push(int value) {
		if (this.stack.size() > 0
				&& this.stack.get(this.stack.size() - 1).size() < this.stackSize) {
			this.stack.get(this.stack.size() - 1).add(value);
		} else {
			ArrayList<Integer> newStack = new ArrayList<Integer>();
			newStack.add(value);
			this.stack.add(newStack);
		}
	}

	public int pop() {
		if (this.stack.size() > 0) {
			ArrayList<Integer> lastStack = this.stack
					.get(this.stack.size() - 1);
			int value = lastStack.remove(lastStack.size() - 1);
			if (lastStack.size() == 0) {
				this.stack.remove(this.stack.size() - 1);
			}
			return value;
		} else {
			throw new RuntimeException();
		}
	}

	public void printStack() {
		for (ArrayList<Integer> x : this.stack) {
			for (Integer element : x) {
				System.out.print(element + " ");
			}
			System.out.println();
		}
	}

}




/**
 * question 4 hanoi
 * @author Hassan Jamous
 *
 */
class HanoiStack {
	
	//we use int function to point where the function should continue
	class HanoiElement {
		int disk;
		int from;
		int to;
		int spare;
		int function;

		public HanoiElement(int disk, int from, int to, int spare, int function) {
			this.disk = disk;
			this.from = from;
			this.to = to;
			this.spare = spare;
			this.function = function;
		}
	}

	ArrayList<HanoiElement> elements = new ArrayList<HanoiElement>();

	public void push(HanoiElement element) {
		elements.add(element);
	}

	public HanoiElement pop() {
		if (elements.size() > 0) {
			return elements.remove(elements.size() - 1);
		} else {
			throw new RuntimeException("stack is empty");
		}
	}

	public void printStack() {
		for (HanoiElement x : elements) {
			System.out.print(x + " ");
		}
	}

	public boolean isEmpty() {
		return this.elements.size() > 0 ? false : true;
	}

	public void hanoi(int disk, int from, int to, int spare) {
		HanoiStack stack = new HanoiStack();
		stack.push(new HanoiElement(disk, from, to, spare, 0));
		while (!stack.isEmpty()) {
			HanoiElement h = stack.pop();
			if (h.disk == 1) {
				System.out.println("Move disk from " + h.from + " to " + h.to);
				continue;
			} else if (h.disk != 1 && h.function == 0) {
				h.function = 1;
				stack.push(h);
				stack.push(new HanoiElement(h.disk - 1, h.from, h.spare, h.to,
						0));

			} else if (h.disk != 1 && h.function == 1) {
				System.out.println("Move disk from " + h.from + " to " + h.to);
				stack.push(new HanoiElement(h.disk - 1, h.spare, h.to, h.from,
						0));
			}
		}

	}

}

/**
 * question 5
 * @author Hassan Jamous
 *
 */
class Queue2Stacks {
	Stack stack1 = new Stack();
	Stack stack2 = new Stack();
	
	public void enqueue(int value){
		stack1.push(value);
	}
	
	public int dequeue() {
		while(stack1.isEmpty() == false) {
			stack2.push(stack1.pop());
		}
		int element = stack2.pop();
		while (stack2.isEmpty()==false) {
			stack1.push(stack2.pop());
		}
		return element;
	}
}

/**
 * question 6
 * @author Hassan Jamous
 *
 */
class SortStack {
	public static Stack sortStack(Stack toBeSorted) {
		Stack spare = new Stack();
		while (toBeSorted.isEmpty()==false) {
			int currentElement = toBeSorted.pop();
			try {
				int smallElement = spare.peek();
				if (currentElement>=smallElement){
					spare.push(currentElement);
				} else {
					while (spare.isEmpty()==false){
						toBeSorted.push(spare.pop());
					}
					spare.push(currentElement);
				}
			} catch (RuntimeException x) {
				spare.push(currentElement);
			}
		}
		
		return spare;
	}

}

/*
 * question 7
 */
class Animal {
	String name;
	public Animal(String name){
		this.name = name;
	}
	public String getName(){
		return name;
	}
}
class Dog extends Animal {
	public Dog(){
		super("Dog");
	}
}
class Cat extends Animal {
	public Cat(){
		super("Cat");
	}
}
class AnimalShelter {
	
	ArrayList<Animal> queue = new ArrayList<Animal>();
	
	public void enqueue(Animal animal){
		queue.add(animal);
	}
	public Animal popAny(){
		if (queue.size()>0) {
			return queue.remove(0);
		} else {
			throw new RuntimeException();
		}
	}
	public Animal popAnimal(String name) {
		ArrayList<Animal> helper = new ArrayList<Animal>();
		Animal theRightDog = null;
		while(queue.size()!=0) {
			Animal animal = queue.remove(0);
			if (animal.getName().equals(name)){
				while (helper.size()!=0){
					queue.add(0,helper.remove(helper.size()-1));
				}
				theRightDog = animal;
				break;
			} else {
				helper.add(animal);
			}
		}
		if (theRightDog != null) {
			return theRightDog;
		} else {
			throw new RuntimeException();
		}
	}
	
	public void printAnimals(){
		for (Animal x : this.queue){
			System.out.print(x.getName()+" ");
		}
	}
	
}
class Stack {
	ArrayList<Integer> elements = new ArrayList<Integer>();

	public void push(int element) {
		elements.add(element);
	}

	public int pop(){
		if (!this.isEmpty()) {
			return elements.remove(elements.size() - 1);
		} else {
			throw new RuntimeException("stack is empty");
		}
	}
	
	public int peek() {
		if (!this.isEmpty()) {
			return elements.get(elements.size() - 1);
		} else {
			throw new RuntimeException("stack is empty");
		}
	}
	
	public boolean isEmpty() {
		return this.elements.size() > 0 ? false : true;
	}

	public void printStack() {
		for (Integer x : elements) {
			System.out.print(x + " ");
		}
	}
	
	
}

class Queue {
	ArrayList<Integer> elements = new ArrayList<Integer>();

	public void inqueue(int element) {
		elements.add(element);
	}

	public int dequeue() throws Exception {
		if (elements.size() > 0) {
			return elements.remove(0);
		} else {
			throw new Exception("stack is empty");
		}
	}

	public void printQueue() {
		for (Integer x : elements) {
			System.out.print(x + " ");
		}
	}

}