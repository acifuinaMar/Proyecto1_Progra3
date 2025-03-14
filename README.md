# Documentation

## `public Tree()`

Constructor for the Tree class. Initializes the unrooted, expressionless tree.

## `public Node getRoot()`

Gets the root of the tree.

 * **Returns:** The root node of the tree.

## `private Node createTree()`

Creates the tree from a mathematical expression in infix notation.

 * **Returns:** Root node of the generated expression tree.

## `private Node buildSubtree(char operator, Node right, Node left)`

Constructs a subtree from an operator and two operands.

 * **Parameters:**
   * `operator` — Mathematical operator.
   * `right` — Right node.
   * `left` — Left node.
 * **Returns:** Root node of the generated subtree.

## `private static int assign(char operator)`

Assigns priority to mathematical operators.

 * **Parameters:** `operator` — Mathematical operator.
 * **Returns:** Operator priority value.

## `private static boolean hierarchy(char op1, char op2)`

Determines the operator hierarchy.

 * **Parameters:**
   * `op1` — Current operator.
   * `op2` — Operator at the top of the stack.
 * **Returns:** true if op1 has lower or equal priority than op2.

## `public String getPreOrder(Node node)`

Function that recursively returns the pre-order traversal of the tree.

 * **Parameters:** `node` — The current node being traversed.
 * **Returns:** A string representation of the pre-order traversal.

## `public String getPreOrder()`

Calls the recursive getPreOrder function starting from the root.

 * **Returns:** A string representation of the pre-order traversal from the root.

## `public String getPostOrder(Node node)`

Function that recursively returns the post-order traversal of the tree.

 * **Parameters:** `node` — The current node being traversed.
 * **Returns:** A string representation of the post-order traversal.

## `public String getPostOrder()`

Calls the recursive getPostOrder function starting from the root.

 * **Returns:** A string representation of the post-order traversal from the root.

## `public String PrintTree()`

Prints the expression tree in text format.

 * **Returns:** Text representation of the tree.

## `private int calculateHeight(Node node)`

Calculates the height of the tree.

 * **Parameters:** `node` — Root node of the tree.
 * **Returns:** Height of the tree.

## `public class Node`

Class that represents a node in the expression tree.

## `public Node(String value)`

Constructor that initializes the node with a given value.

 * **Parameters:** `value` — The value of the node.
