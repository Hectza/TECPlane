/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tecplane.proyecto2;

/**
 *
 * @author hecto
 */
public class Node<T> {
    //atributos
		
		private Object element;
		private Node next;
		
		//Constructores
		public Node() {
			this.element = null;
			this.next = null;
		}
		
		public Node(Object element) {
			this.element = element;
			this.next = null;
		}
		
		public Node(Object element, Node next) {
			this.element = element;
			this.next = next;
		}
		
		//m�todos
		
		public Object getElement() {
			return this.element;
		}
		
		public void setElement(Object element) {
			this.element = element;
		}
		
		public Node getNext() {
			return this.next;
		}
		
		public void setNext(Node next) {
			this.next = next;	
		}
}
