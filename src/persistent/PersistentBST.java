package project3;


public class PersistentBST {

	/**
	 * Variables for Persistent Binary Search Tree (PBST)
	 * Creates or initializes root, Persistent Binary Search Tree (PBST), time, node count, current link, array of strings
	 * for first name, last name, and letters. Creates array of first and last name
	 */
	private Link root;
	int size = 24;
	private Link[] treeArray;
	PersistentBST bst;
	int currentTime;
	private int currentNodes = 0;
	Link current;
	String value;
	private String string;
	private String[] letters = new String[8];
	String[] firstNames = new String[18];
	String[] LastNames = new String[18];
	int firstNameCount = 0;
	int lastNameCount = 0;
	private String[] namesSplit = new String[2];
	Link someLink;
	private int currentTreeSize = 0;


	/**
	 * Creates Array of Links for Persistent Binary Search Tree with size determining size of array
	 * @param string - String of letters or names to be cleaned, split, and added to PBST in sorted order
	 */
	public PersistentBST(String string) {
		treeArray = new Link[size];
		currentTime = 0;
		this.setString(string);
	}

	/**
	 * Receives string; cleans, splits, and parses string according to key; 
	 * Passes cleaned and split string, key, and value to be added to PBST
	 * @param string - String to be cleaned, and split according to key value pair
	 */
	public void insert(String string) {
		String[] letterSplit = null;

		if(getString().equals("first") || getString().equals("last")) {
			ParseJSON parse = new ParseJSON(string);
			String test = parse.parseNames(string);
			namesSplit = test.split("\\s+"); // split with multiple spaces
		}

		else if(getString().equals("A") || getString().equals("B") || getString().equals("C") || getString().equals("D")) {
			ParseJSON parse = new ParseJSON(string);
			String test2 = parse.parseLetters(string);
			letterSplit = test2.split("\\s+");
			letters = getLetters(letterSplit);
		}
		else {
			System.out.println("STRING: "+getString());
		}

		String key = getString();
		String value = null;

		if(namesSplit != null && key.contains("first")) {
			value = namesSplit[0];
			firstNames[firstNameCount++] = value;
			buildTree(key, value, string);
		}
		else if(namesSplit != null && key.contains("last")) {
			value = namesSplit[1];
			buildTree(key, value, string);
		}

		else if(letters != null && key.contains("A")) {
			value = letters[1];
			buildTree(key, value, string);
		}
		else if(letters != null && key.contains("B")) {
			value = letters[3];
			buildTree(key, value, string);
		}
		else if(letters != null && key.contains("C")) {
			value = letters[5];
			buildTree(key, value, string);
		}
		else if(letters != null && key.contains("D")) {
			value = letters[7];;
			buildTree(key, value, string);
		}
	}

	/**
	 * Receives string to delete. Returns if PBST is null. If PBST is not null, cleans, and parses and splits string 
	 * according to key & value. Next passes the string, key, value, and root of PBST to delete(treeArray, key, value, string)
	 * @param string
	 */
	public void delete(String string) {

		if(treeArray == null) {
			System.out.println("SORRY it's empty");
			return;
		}
		else {
			String[] letterSplit = null;

			if(getString().equals("first") || getString().equals("last")) {
				ParseJSON parse = new ParseJSON(string);
				String test = parse.parseNames(string);
				namesSplit = test.split("\\s+"); // split with multiple spaces
			}
			else if(getString().equals("A") || getString().equals("B") || getString().equals("C") || getString().equals("D")) {
				ParseJSON parse = new ParseJSON(string);
				String test2 = parse.parseLetters(string);
				letterSplit = test2.split("\\s+");
				letters = getLetters(letterSplit);
			}

			String key = getString();
			String value = null;

			if(namesSplit != null && key.contains("first")) {
				value = namesSplit[0];
				firstNames[firstNameCount++] = value;
				Link deleted = delete(treeArray[currentTime], key, value, string);
				currentTime = currentTime();
				currentTime++;
				treeArray[currentTime] = deleted;
			}
			else if(namesSplit != null && key.contains("last")) {
				value = namesSplit[1];
				Link deleted = delete(treeArray[currentTime], key, value, string);
				currentTime = currentTime();
				currentTime++;
				treeArray[currentTime] = deleted;
			}

			else if(letters != null && key.contains("A")) {
				value = letters[1];
				Link deleted = delete(treeArray[currentTime], key, value, string);
				currentTime = currentTime();
				currentTime++;
				treeArray[currentTime] = deleted;
			}
			else if(letters != null && key.contains("B")) {
				value = letters[3];
				Link deleted = delete(treeArray[currentTime], key, value, string);
				currentTime = currentTime();
				currentTime++;
				treeArray[currentTime] = deleted;
			}
			else if(letters != null && key.contains("C")) {
				value = letters[5];
				Link deleted = delete(treeArray[currentTime], key, value, string);
				currentTime = currentTime();
				currentTime++;
				treeArray[currentTime] = deleted;
			}
			else if(letters != null && key.contains("D")) {
				value = letters[7];
				Link deleted = delete(treeArray[currentTime], key, value, string);
				currentTime = currentTime();
				currentTime++;
				treeArray[currentTime] = deleted;
			}
		}
	}

	/**
	 * Receives entire PBST, and key, value and string to remove from entire PBST. 
	 * Recursively searches through tree for string to remove. If string is found then replace it with 
	 * next link with string
	 * @param tree - entire PBST structure starting from root
	 * @param key - key that string is sorted by
	 * @param value - key's value
	 * @param string - entire string to remove (along with link)
	 * @return - link to return
	 */
	public Link delete(Link tree, String key, String value, String string) {

		if (tree == null) {
			return null;
		}

		if (tree.getValue().compareTo(value) == 0) {

			if (tree.leftChild == null) {
				return tree.rightChild;
			}
			else if (tree.rightChild == null) {
				return tree.leftChild;
			}
			else {
				if(tree.rightChild.leftChild == null) {
					Link newTree = new Link(tree.getRightChild().stringElement, tree.getRightChild().getKey(), tree.getRightChild().getValue(), tree.getLeftChild(), tree.getRightChild().getRightChild());
					return newTree;
				}    
				else {
					Link newTree = new Link(tree.getRightChild().stringElement, tree.getRightChild().getKey(), tree.getRightChild().getValue(), tree.getRightChild().getLeftChild(), tree.getRightChild().getRightChild());
					newTree.setElement(removeSmallest(newTree.rightChild));
					return newTree;
				}
			}
		}

		else  if (value.compareTo(tree.value) < 0) {
			Link newTree = new Link(tree.stringElement, tree.getKey(), tree.getValue(), delete(tree.leftChild, key, value, string), tree.getRightChild());
			return newTree;
		} 
		else {
			Link newTree = new Link(tree.stringElement, tree.getKey(), tree.getValue(), tree.getLeftChild(), delete(tree.rightChild, key, value, string));
			return newTree;
		}
	}  

	/**
	 * Receives entire tree, continually searches smallest element and returns that element
	 * @param tree
	 * @return
	 */
	public String removeSmallest(Link tree) {
		if (tree.leftChild.leftChild == null) {
			String smallest = tree.leftChild.stringElement();
			tree.leftChild = tree.leftChild.rightChild;
			return smallest;
		} 
		else {
			return removeSmallest(tree.leftChild);
		}
	}


	/**
	 * Initial PBST tree build. If PBST is null, creates new link and sets as root. 
	 * If PBST not null, set root as last link, create new link with new string, then send last link (now root) and new link to new buildTree().
	 * Next set newlink as root
	 * @param key - key to sort by (first, last, letter)
	 * @param value - value to sort by
	 * @param entireString - The entire string
	 */
	public void buildTree(String key, String value, String entireString) {
		if (currentTime < size) {
			if (currentTime == 0) {
				Link root = new Link(entireString, key, value, null, null);
				currentTime = currentTime();
				currentTime++;
				treeArray[currentTime] = root;
				
			}
			else {
				Link root = new Link(treeArray[currentTime].stringElement(), treeArray[currentTime].key, treeArray[currentTime].value, null, null);
				Link newLink = new Link(entireString, key, value, null, null);
				buildTree(treeArray[currentTime], root, newLink);
				currentTime = currentTime();
				currentTime++;
				treeArray[currentTime] = root;
			}
		}
	}

	/**
	 * Takes in last PBST structure (oldTree) currentLink (last link or root), and newLink and 
	 * recursively adds newLink and connects to oldTree to make new tree
	 * @param oldTree - the entire structure with all the connections from previous links added
	 * @param curr - the last link added
	 * @param newLink - the new link created
	 */
	private void buildTree(Link oldTree, Link curr, Link newLink) {
		
		if (curr == null) {
			return;
		}
		if (oldTree.leftChild == null && newLink.value.compareTo(curr.value) <= 0) {	
			curr.leftChild = newLink;
			curr.rightChild = oldTree.rightChild;
			return;
		}
		if (oldTree.rightChild == null && newLink.value.compareTo(curr.value) > 0) {
			curr.rightChild = newLink;
			curr.leftChild = oldTree.leftChild;
			return;
		}
		if (newLink.value.compareTo(curr.value) <= 0) {
			curr.rightChild = oldTree.rightChild;
			curr.leftChild = new Link(oldTree.leftChild.stringElement, oldTree.leftChild.key, oldTree.leftChild.value, null, null);
			buildTree(oldTree.leftChild, curr.leftChild, newLink);
		}
		else {
			curr.leftChild = oldTree.leftChild;
			curr.rightChild = new Link(oldTree.rightChild.stringElement, oldTree.rightChild.key, oldTree.rightChild.value, null, null);
			buildTree(oldTree.rightChild, curr.rightChild, newLink);
		}
	}


	/**
	 * If the linkArray is too small, then create new linkArray twice size of original, 
	 * put all elements from last PBST in new linkArray and return new linkArray
	 * @param tree - entire original PBST structure
	 * @return newArray - return new PBST structure 
	 */
	public Link[] doubleArray(Link[] tree) {

		Link[] newArray = new Link[tree.length * 2];
		System.out.println("New Array Length: " + newArray.length);
		for (int i = 0; i < tree.length; i++) {
			newArray[i] = tree[i];
		}
		return newArray;
	}

	/**
	 * Returns array of 8 strings of letters
	 * @return letters - stringArray of letters
	 */
	public String[] getLetters() {
		return letters;
	}

	/**
	 * Receives string of letters to be further cleaned
	 * @param letterSplit
	 * @return newLetters - stringArray of letters
	 */
	public String[] getLetters(String[] letterSplit) {

		int count = 0;
		String[] newLetters = getLetters();

		for(String a : letterSplit) {
			if(a.length() > 3) {
				char[] split3 = a.toCharArray();
				String temp1 = "";
				String temp2 = "";
				for(int i = 0; i < a.length(); i++) {
					if(i < 3) {
						temp1 += split3[i];
					}
					else {
						temp2 += split3[i];
					}
				}
				newLetters[count] = temp1;
				count++;
				newLetters[count] = temp2;
				count++;
			}
			else {
				newLetters[count] = a;
				count++;
			}
		}
		return newLetters;
	}

	/**
	 * Receives string and time, and returns either true if string is in PBST at that time, or false if not
	 * @param elem - String to be searched
	 * @param time - time or position that string is placed in PBST
	 * @return hey - true if in PBST, false if not
	 */
	public boolean find(String elem, int time) {
		boolean hey = find(treeArray[time], elem);
		return hey;
	}

	/**
	 * Receives string and returns true if string is in PBST at current time, or false if not
	 * @param elem - string to be searched
	 * @return hey - Returns true if string is in PBST at current time, or false if not
	 */
	public boolean find(String elem) {
		boolean hey = find(treeArray[currentTime], elem);
		return hey;
	}

	/**
	 * Continually searches through PBST for string element.
	 * @param tree - entire PBST
	 * @param elem - Entire string element
	 * @return true if stringElement is in PBST, false if not
	 */
	boolean find(Link tree, String elem) {
		if (tree == null) {
			return false;
		}
		if (tree.stringElement().equals(elem)) {
			return true;
		}
		return find(tree.getLeftChild(), elem) || find(tree.getRightChild(),elem);
	}

	public int currentTime() {
		return currentTime;
	}

	/**
	 * Returns size of PBST at specific time
	 * @param time - position of PBST
	 * @return size of PBST at specific time
	 */
	public int size(int time) {
		return sizeHelper(treeArray[time]);
	}

	/**
	 * Receives PBST and for each branch of tree it counts to return the size of tree
	 * @param current - the PBST
	 * @return size of PBST
	 */
	public int sizeHelper(Link current) {
		if(current == null) {
			return 0;
		}
		else {
			return (sizeHelper(current.getLeftChild()) + 1 + sizeHelper(current.getRightChild()));
		}
	}

	/**
	 * Returns size at current time
	 * @return - Returns size at current time
	 */
	int size() {
		return size(currentTime());
	}

	/**
	 * Receives time, and creates a string array of specific size at specific time to be sorted
	 * @param time - time or position in building PBST
	 * @return sortedArray - the sorted stringArray of PBST at specific time
	 */
	public String[] getAllElements(int time) {
		int arraySize = size(time);
		int index = 0;
		String[] sortedArray = new String[arraySize];
		if(treeArray[time] != null) {
			int hey = makeSortedArray(treeArray[time], sortedArray, index);
		}
		return sortedArray;
	}
	
	/**
	 * Returns a sorted string array of all elements at specific time
	 * @return - Returns a sorted string array of all elements at specific time
	 */
	public String[] getAllElements() {
		return getAllElements(currentTime());
	}

	/**
	 * Receives PBST, an empty stingArray to sort and an index of where to insert elements into stringArray
	 * @param current - when recursively going through PBST, current is the current position in the PBSTree
	 * @param sortedArray - the string array of elements inserted in order
	 * @param index - the index of the position in stringArray
	 * @return
	 */
	public int makeSortedArray(Link current, String[] sortedArray, int index) {
		if(current.leftChild != null) {
			index = makeSortedArray(current.getLeftChild(), sortedArray, index);
		}
		sortedArray[index++] = current.stringElement();
		if(current.rightChild != null) {
			index = makeSortedArray(current.getRightChild(), sortedArray, index);
		}
		return index;
	}

	public Link getRoot() {
		return treeArray[0];
	}

	public void setRoot(Link root) {
		this.root = root;
	}

	public void setCurrentNodes(int currentNodes) {
		this.currentNodes = currentNodes;
	}

	public int getCurrentNodes() {
		return currentNodes;
	}

	/**
	 * Goes through entire tree from root and prints out tree
	 * @param tree - PBST
	 * @param indent - indenting depending upon how far down element is on tree
	 */
	public void print(Link tree, int indent) {
		if(tree != null) {
			for(int i=0; i<indent; i++) {
				System.out.print("  ");
			}
			print(tree.getLeftChild(), indent+1);
			System.out.println(tree.stringElement() + " "+ tree.getValue());
			print(tree.getRightChild(), indent+1);
		}
	}

	public String getString() {
		return string;
	}

	public void setString(String string) {
		this.string = string;
	}

	public String[] getNamesSplit() {
		return namesSplit;
	}

	public void setNameSplit(String[] nameSplit) {
		this.namesSplit = nameSplit;
	}

	private Link[] getTree() {
		return treeArray;
	}

	public int getCurrentTreeSize() {
		return currentTreeSize;
	}

	public void setCurrentTreeSize(int currentTreeSize) {
		this.currentTreeSize = currentTreeSize;
	}

	/**
	 * Link class with stringElement, key, value, string, left and right child
	 */
	private class Link {

		String stringElement;
		String key;
		String value;
		Link leftChild;
		Link rightChild;

		Link(String stringElement, String key, String value, Link leftChild, Link rightChild) {
			this.stringElement = stringElement;
			this.key = key;
			this.value = value;
			this.setLeftChild(leftChild);
			this.setRightChild(rightChild);
		}	


		public String stringElement() {
			return stringElement;
		}
		void setElement(String element) {
			this.stringElement = element;
		}

		public Link getLeftChild() {
			return leftChild;
		}

		public void setLeftChild(Link leftChild) {
			this.leftChild = leftChild;
		}

		public Link getRightChild() {
			return rightChild;
		}

		public void setRightChild(Link rightChild) {
			this.rightChild = rightChild;
		}

		public String getKey() {
			return key;
		}

		public void setKey(String key) {
			this.key = key;
		}

		public String getValue() {
			return value;
		}

		public void setValue(String value) {
			this.value = value;
		}
	}
}