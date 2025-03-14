/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package expressiontree;

/**
 *
 * @author maryori
 */
/**
* Class that represents a node in the expression tree.
*/
public class Node {
    //Attributes
    String value;
    //izquierda = left || derecha = right
    public Node leftNode,rightNode;
    //Builders
    /**
    * Constructor that initializes the node with a given value.
    * @param value The value of the node.
    */
    public Node(String value){
        this.value = value;
        this.leftNode = null;
        this.rightNode = null;
    }
    public Node(){
        this.leftNode = null;
        this.rightNode = null;
    }
    //Getters and Setters 
    public String getValue() {
        return value;
    }
    public void setValue(String value) {
        this.value = value;
    }
    public Node getLeftNode() {
        return leftNode;
    }
    public void setLeftNode(Node leftNode) {
        this.leftNode = leftNode;
    }
    public Node getRightNode() {
        return rightNode;
    }
    public void setRightNode(Node rightNode) {
        this.rightNode = rightNode;
    } 
}
