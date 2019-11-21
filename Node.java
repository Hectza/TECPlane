/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic;

import java.io.Serializable;

/**
 * Class node
 * @author hecto
 */
public class Node<T> implements Serializable{
                //private attributes, public methods
		private Object element;
		private Node next;
		
		//Constructors for the class
		public Node() {
			this.element = null;
			this.next = null;
		}
		
                //requires only the element
		public Node(Object element) {
			this.element = element;
			this.next = null;
		}
		
                //requires element and next
		public Node(Object element, Node next) {
			this.element = element;
			this.next = next;
		}
		
		/**
                 * Returns the object stored in the node
                 * @return 
                 */
		public Object getElement() {
			return this.element;
		}
		
                /**
                 * Sets the object of the node
                 * @param element 
                 */
		public void setElement(Object element) {
			this.element = element;
		}
		
                /**
                 * Obtains the next of the node
                 * @return 
                 */
		public Node getNext() {
			return this.next;
		}
		
                /**
                 * Sets the next of the node
                 * @param next 
                 */
		public void setNext(Node next) {
			this.next = next;	
		}
}
