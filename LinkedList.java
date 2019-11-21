package logic;

import java.io.Serializable;

/**
 * Class Linked List
 * @author hecto
 */
public class LinkedList implements Serializable{
	
	//private attributes, public methods
	private Node head;
	private Node current;
	private Node tail;
	private int position;
	private int size;
        
	/**
	 * Predetermined constructor
	 */
	public LinkedList() {
		this.head = new Node();
		this.current = this.head;
		this.tail = this.head;
		this.size = 0;
		this.position = -1;
	}

        /**
         * Returns the head of the list
         * @return 
         */
        public Node getHead() {
            return head;
        }

        /**
         * Sets the head of the list
         * @param head 
         */
        public void setHead(Node head) {
            this.head = head;
        }

        /**
         * Returns the current of the list
         * @return 
         */
        public Node getCurrent() {
            return current;
        }

        /**
         * Sets the current of the list
         * @param current 
         */
        public void setCurrent(Node current) {
            this.current = current;
        }

        /**
         * Returns the tail of the list
         * @return 
         */
        public Node getTail() {
            return tail;
        }

        /**
         * Sets the tail of the list
         * @param tail 
         */
        public void setTail(Node tail) {
            this.tail = tail;
        }
	
	/**
	 * Adds a new element to the list in the position of the current
	 * @param element
	 */
	public void insert(Object element) {
		//Creates a new node with the object passed as argument as its element
		Node newNode = new Node(element, this.current.getNext());
                // asigns the attribute "next" of the node in which the current is currently positioned to be the new node
		this.current.setNext(newNode);
		//checks if the current is at the end of the list
		if (this.current == this.tail) {
                    // now the tail is the next node of the tail
			this.tail = tail.getNext();
		}
                // increases size of list
		this.size++;
		
	}
	
        /**
         * Adds element at the end of the list
         * @param element 
         */
	public void append(Object element) {
		// Creates a new node with the object passed as argument as its element
		Node newNode = new Node(element);
                // Rearranges the tail
		this.tail.setNext(newNode);
		this.tail = newNode;
                // increases size of list
		this.size++;
	}
	
        /**
         * Returns the size of the list
         * @return 
         */
	public int getSize() {
		return this.size;
	}
}
