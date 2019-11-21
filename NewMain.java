/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.io.Serializable;

/**
 *
 * @author hecto
 */
public class NewMain implements Serializable {
    public static List<Vertex> nodes = new ArrayList<Vertex>();
    public static List<Edge> edges = new ArrayList<Edge>();
    public static MST mst = new MST();
    public static logic.LinkedList activities = new logic.LinkedList();
    public static logic.LinkedList resources = new logic.LinkedList();
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        try{
            FileInputStream myFileInputStream = new FileInputStream("C:\\Users\\hecto\\Documents\\MTP\\nodes.ser");
            ObjectInputStream myObjectInputStream = new ObjectInputStream(myFileInputStream);
            nodes = (List)myObjectInputStream.readObject(); 
            myObjectInputStream.close();
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
        
        try{
            FileInputStream myFileInputStream = new FileInputStream("C:\\Users\\hecto\\Documents\\MTP\\edges.ser");
            ObjectInputStream myObjectInputStream = new ObjectInputStream(myFileInputStream);
            edges = (List)myObjectInputStream.readObject(); 
            myObjectInputStream.close();
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
        
        try{
            FileInputStream myFileInputStream = new FileInputStream("C:\\Users\\hecto\\Documents\\MTP\\MST.ser");
            ObjectInputStream myObjectInputStream = new ObjectInputStream(myFileInputStream);
            mst = (MST)myObjectInputStream.readObject(); 
            myObjectInputStream.close();
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
        
        try{
            FileInputStream myFileInputStream = new FileInputStream("C:\\Users\\hecto\\Documents\\MTP\\activities.ser");
            ObjectInputStream myObjectInputStream = new ObjectInputStream(myFileInputStream);
            activities = (logic.LinkedList)myObjectInputStream.readObject(); 
            myObjectInputStream.close();
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
        
        try{
            FileInputStream myFileInputStream = new FileInputStream("C:\\Users\\hecto\\Documents\\MTP\\resources.ser");
            ObjectInputStream myObjectInputStream = new ObjectInputStream(myFileInputStream);
            resources = (logic.LinkedList)myObjectInputStream.readObject(); 
            myObjectInputStream.close();
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
        
        GUI.Hub gui = new GUI.Hub();
        gui.setVisible(true);
        
        gui.addWindowListener(new WindowAdapter(){
        public void windowClosing(WindowEvent e){
                    terminate();
                    gui.dispose();
                }
            });
        }
    
    public static void addEdge(String laneId, int sourceLocNo, int destLocNo, int duration){
        Edge lane = new Edge(laneId,nodes.get(sourceLocNo), nodes.get(destLocNo), duration);
        edges.add(lane);
    }
    
    public static void addMSTEdge(String nameA, int IDA, String nameB, int IDB, int cost){
        GraphNode source = new GraphNode(nameA, IDA);
        GraphNode destination = new GraphNode(nameB, IDB);
        mst.addEdge(source, destination, cost);
    }
     
    public static void addVertex(String id, String name){
         Vertex location = new Vertex(id, name);
         nodes.add(location);
     }
    
    public static void terminate(){
        System.out.println(edges.size());
        try{
           FileOutputStream myFileOutputStream = new FileOutputStream("C:\\Users\\hecto\\Documents\\MTP\\nodes.ser");
           ObjectOutputStream myObjectOutputStream = new ObjectOutputStream(myFileOutputStream);
           myObjectOutputStream.writeObject(nodes);
           myObjectOutputStream.close();
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
        
        try{
           FileOutputStream myFileOutputStream = new FileOutputStream("C:\\Users\\hecto\\Documents\\MTP\\edges.ser");
           ObjectOutputStream myObjectOutputStream = new ObjectOutputStream(myFileOutputStream);
           myObjectOutputStream.writeObject(edges);
           myObjectOutputStream.close();
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
        
        try{
           FileOutputStream myFileOutputStream = new FileOutputStream("C:\\Users\\hecto\\Documents\\MTP\\MST.ser");
           ObjectOutputStream myObjectOutputStream = new ObjectOutputStream(myFileOutputStream);
           myObjectOutputStream.writeObject(mst);
           myObjectOutputStream.close();
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
        
        try{
           FileOutputStream myFileOutputStream = new FileOutputStream("C:\\Users\\hecto\\Documents\\MTP\\activities.ser");
           ObjectOutputStream myObjectOutputStream = new ObjectOutputStream(myFileOutputStream);
           myObjectOutputStream.writeObject(activities);
           myObjectOutputStream.close();
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
        
        try{
           FileOutputStream myFileOutputStream = new FileOutputStream("C:\\Users\\hecto\\Documents\\MTP\\resources.ser");
           ObjectOutputStream myObjectOutputStream = new ObjectOutputStream(myFileOutputStream);
           myObjectOutputStream.writeObject(resources);
           myObjectOutputStream.close();
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
    }
}
