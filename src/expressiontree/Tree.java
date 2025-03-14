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
* Class that represents a mathematical expression tree.
*/
import java.util.*;
import javax.swing.JOptionPane;
public class Tree {
    //Attributes
    private Node root;
    private String expression;
    //Builder
    /**
    * Constructor for the Tree class.
    * Initializes the unrooted, expressionless tree.
    */
    public Tree() {
        this.root = null;
        this.expression = null;
    }
    //Getter for root because it´ll be used to check if the tree was built
    /**
    * Gets the root of the tree.
    * @return The root node of the tree.
    */
    public Node getRoot() {
        return root;
    }
    //Function that will create the tree (structure)
    /**
    * Creates the tree from a mathematical expression in infix notation.
    * @return Root node of the generated expression tree.
    */
    private Node createTree() {
        /*
        Create 2 stacks
            operands for numbers/variables
            operator for +-/*^()
        The StringBuilder number will help me manage numbers with two or more
        digits
        openParentheses allows to control that every parentheses that´s open,
        it´s also closed to avoid malfunction
        */    
        Stack<Node> operands = new Stack<>();
        Stack<Character> operators = new Stack<>();
        StringBuilder number = new StringBuilder();
        int openParentheses = 0;
        
        //For to go throght the math expression
        for (char character : expression.toCharArray()) {
            /*
            First, check if the char is a digit (0-9)
            if it´s then we add it to the end of the string numberr
            */
            System.out.println("Elemento actual: " + character);
            System.out.println("Operandos: " + operands);
            System.out.println("Operadores: " + operators);
            if (Character.isDigit(character)) {
                number.append(character);
            } else {
                if (number.length() > 0) {
                    /*
                    if the string number has 1 or more chars on it, then we 
                    create the node and reset the length for number.
                    */
                    operands.push(new Node(number.toString()));
                    number.setLength(0);
                }
                if (character == '(') {
                    /*
                    if the char is a ( then we add the char to the stak operators
                    and increse openParentheses.
                    */
                    operators.push(character);
                    openParentheses++;
                } else if (assign(character) > 0) {
                    /*If we find the char is an operator, we call the 
                    function assign.
                    The conditional was built based on the fact that if the operator
                    isn´t valid then it will be -1
                    */
                    while (!operators.isEmpty() && operators.peek() != '(' && hierarchy(character, operators.peek())) {
                        /*
                        We will perform the following part of the code just if the stack of operator isn´t empty, 
                        we don´t find ) and the character in queue isn´t greater or equal than the one on the
                        top of the stack
                        */
                        if (operands.size() < 2) {
                            /*
                            If we have less than 2 numbers/variables to operate, then the expression isn´t valid.
                            For example. 2+ || 2+9*
                            We let the user know throught a dialog window.
                            */
                            JOptionPane.showMessageDialog(null, "Expresión inválida: falta de operandos.", 
                                                      "Error", JOptionPane.ERROR_MESSAGE);
                            return null; // get off the function
                        }
                        /*
                        If we supposs that instead of callen .pop() into both of the stacks
                        we have 3 nodes aux1, aux2, aux3 this is what we will be performing:
                            aux1 = new Node(String.valueOf(stack.pop()));
                            aux2 = list.pop();
                            aux3 = list.pop();
                            aux1.setLeftNode(aux3);
                            aux1.setRightNode(aux2);
                            operatos.push(aux1);
                        */
                        operands.push(buildSubtree(operators.pop(), operands.pop(), operands.pop()));
                    }
                    operators.push(character);
                } else if (character == ')') {
                    /*
                    if we find a ) we also validate that, at least we have a )
                    otherwise, we let the user know the expression isn´t valid
                    For example. 8+2)
                    */
                    if (openParentheses == 0) {
                        JOptionPane.showMessageDialog(null, "Expresión inválida: paréntesis de cierre sin apertura.", 
                                                  "Error", JOptionPane.ERROR_MESSAGE);
                        return null; // Get off the function
                    }
                    while (!operators.isEmpty() && operators.peek() != '(') {
                        if (operands.size() < 2) {
                            JOptionPane.showMessageDialog(null, "Expresión inválida: falta de operandos.", 
                                                      "Error", JOptionPane.ERROR_MESSAGE);
                            return null; // Get off the function
                        }
                        operands.push(buildSubtree(operators.pop(), operands.pop(), operands.pop()));
                    }
                    operators.pop();
                    openParentheses--;
                }
            }
            System.out.println("Operandos (después de procesar " + character + ")-> " + operands);
            System.out.println("Operadores (después de procesar " + character + ")-> " + operators);
        }
        /*
        If the stringBuilder number still has characters on it, we send then
        to the operands stack to make sure they are taken into account
        for the tree and the traverses.
        */
        if (number.length() > 0) {
            operands.push(new Node(number.toString()));
        }
        while (!operators.isEmpty()) {
            if (operands.size() < 2) {
                JOptionPane.showMessageDialog(null, "Expresión inválida: falta de operandos.", 
                                          "Error", JOptionPane.ERROR_MESSAGE);
                return null; // Salir del método
            }
            operands.push(buildSubtree(operators.pop(), operands.pop(), operands.pop()));
            }
            if (openParentheses != 0) {
                JOptionPane.showMessageDialog(null, "Expresión inválida: paréntesis de apertura sin cierre.", 
                                          "Error", JOptionPane.ERROR_MESSAGE);
                return null; // Salir del método
            }
        return operands.pop();
    }//End of the function createTree
        /*
        If the stringBuilder number still has characters on it, we send then
        to the operands stack to make sure they are taken into account
        for the tree and the traverses.
        */
        if (number.length() > 0) {
            operands.push(new Node(number.toString()));
        }
        while (!operators.isEmpty()) {
            if (operands.size() < 2) {
                JOptionPane.showMessageDialog(null, "Expresión inválida: falta de operandos.", 
                                          "Error", JOptionPane.ERROR_MESSAGE);
                return null; // Salir del método
            }
            operands.push(buildSubtree(operators.pop(), operands.pop(), operands.pop()));
            }
            if (openParentheses != 0) {
                JOptionPane.showMessageDialog(null, "Expresión inválida: paréntesis de apertura sin cierre.", 
                                          "Error", JOptionPane.ERROR_MESSAGE);
                return null; // Salir del método
            }
        return operands.pop();
    }//End of the function createTree
    //CreatreTree get the atributte expression and will be the one calling on recursively the function createTree
    public void CreateTree(String expression) {
        this.expression = expression;
        this.root = createTree();
    }
    /*
    buildSubtree will help us manage the construction of the tree. Mostly 
    when it comes to operators.
    It receives 3 atributtes: operator and each of the sons.
    The fucntions accomplishes the following:
        Creates a new node that stores and operator
        calls the setter for left and right node (could be a numeric value
        of null
    since it´s a return function, it returns a Node
    */
    /**
    * Constructs a subtree from an operator and two operands.
    * @param operator Mathematical operator.
    * @param right Right node.
    * @param left Left node.
    * @return Root node of the generated subtree.
    */
    private Node buildSubtree(char operator, Node right, Node left) {
        Node node = new Node(String.valueOf(operator));
        node.setLeftNode(left);
        node.setRightNode(right);
        return node;
    }
    /*
    Function that returns an integer assigned to each operator based on the 
    what is stablish on the switch (which is based on the hierarchy of the operators,
    being +- the lowest and ^ the highest.
    We also consider the posibility of there being and unknows operator, so
    we assign those the value of -1
    */
    /**
    * Assigns priority to mathematical operators.
    * @param operator Mathematical operator.
    * @return Operator priority value.
    */
    private static int assign(char operator) {
        switch (operator) {
            case '+': case '-':
                return 1;
                
            case '*': case '/':
                return 2;
                
            case '^':
                return 3;
            case ')':
                return 0;
            default:
                return -1;
        }
    }
    /*
    if the value of the operator that´s being evaluated is less than and unequal to the value
    of the operator on top of the stack of operators, the function returns true
    Otherwise, false
    */
    /**
    * Determines the operator hierarchy.
    * @param op1 Current operator.
    * @param op2 Operator at the top of the stack.
    * @return true if op1 has lower or equal priority than op2.
    */
    private static boolean hierarchy(char op1, char op2) {
        return assign(op1) <= assign(op2);
    }
    /*
    PreOrder = root, left, right
    Considering the order of Preorder, the function first get´s the root and 
    prints the value on it. Then it moves to the left Node and right Node
    recursevily. It also considers the possiblity of the root being null 
    (meaning the tree wasn´t built due to an exception)
    */
    /**
     * Function that recursively returns the pre-order traversal of the tree.
     * @param node The current node being traversed.
     * @return A string representation of the pre-order traversal.
     */
    public String getPreOrder(Node node) {
        if (node == null) return "";
        return node.value + " " + getPreOrder(node.leftNode) + getPreOrder(node.rightNode);
    }
    /*
    Function that will recursevily call getPreOrder and sent the atributte
    root.
    */
    /**
     * Calls the recursive getPreOrder function starting from the root.
     * @return A string representation of the pre-order traversal from the root.
     */
    public String getPreOrder() {
        return getPreOrder(root);
    }
    /**
     * Function that recursively returns the post-order traversal of the tree.
     * @param node The current node being traversed.
     * @return A string representation of the post-order traversal.
     */
    public String getPostOrder(Node node) {
        if (node == null) return "";
        return getPostOrder(node.leftNode) + getPostOrder(node.rightNode) + node.value + " ";
    }
    /**
     * Calls the recursive getPostOrder function starting from the root.
     * @return A string representation of the post-order traversal from the root.
     */
    public String getPostOrder() {
        return getPostOrder(root);
    }
    /*
    To print the tree so that it can be, somehow, understood by the user
    (even though there is no diagram) We used a List called lines
    to manage how many blank spaces should there be between the value of each
    node taking into account the height  and width of the tree
    */
    /**
    * Prints the expression tree in text format.
    * @return Text representation of the tree.
    */
    public String PrintTree() {
        List<List<String>> lines = new ArrayList<>();
        int height = calculateHeight(root);
        //2^(height+1). If height=0, then 2^1
        int width = (int) Math.pow(2, height + 1);
        /*
        The function fillLines needs to receive 5 attributes
        the root, level initialized as 0, width/2 to mark where to
        add the first descendants of the root (level 1), and width/4
         to mark where to add the second descendants of the root (level2)
        */
        fillLines(root, 0, width / 2, lines, width / 4);
        StringBuilder tree = new StringBuilder();
        //Goes through every line that´s into the list lines
        for (List<String> line : lines) {
            //Goes through every node that´s into every line, adding each node to tree -> tree += node
            for (String node : line) {
                tree.append(node);
            }
            //line break
            tree.append("\n");
        }
        //Returns the tree converting it to a String
        return tree.toString();
    }
    /**
    * Calculates the height of the tree.
    * @param node Root node of the tree.
    * @return Height of the tree.
    */
    private int calculateHeight(Node node) {
        if (node == null) {
            return 0;
        }
        //Goes from the top to the bottom of the left nodes
        int leftHeight = calculateHeight(node.getLeftNode());
        //Goes from the top to the bottom of the right nodes
        int rightHeight = calculateHeight(node.getRightNode());
        /*
        Since we are interested in the Heigth = level + 1
        We take the height as the greater value which can be given obtain
        from the left or right side
        */
        return 1 + Math.max(leftHeight, rightHeight);
    }
    private void fillLines(Node node, int level, int position, List<List<String>> lines, int space) {
        /*
        Recursive method that will fill up the tree on a nested list.
        Level indicates how depp is the tree considering root = 0
        position indicates the visual space a node occupies in a line
        Nested list that stores lines of the tree which represent a level of the tree
        space assigns the amount of blank spaces between each node in the same level reducing
        it´s value to the half while moving to lower levels
        */
        if (node == null) return;
        while (lines.size() <= level) {
            lines.add(new ArrayList<>());
        }
        List<String> currentLine = lines.get(level);
        while (currentLine.size() < position) {
            currentLine.add(" ");
        }
        currentLine.add(node.getValue());
        /*
        After processing the current node, the function does 2 recurseive calls for
        left and right nodes.
        For each, the function call the right/left node, position is adjustes to position-space
        and space is reduced to half (space/2)
        */
        fillLines(node.getLeftNode(), level + 1, position - space, lines, space / 2);
        fillLines(node.getRightNode(), level + 1, position + space, lines, space / 2);
    } 
}
