/*
Created by: Omkar Vaidya
HuffmanNode class provides node structure for objects in the HuffmanTree.
It consist of key value and pointers to the left and right child.
Additionally, it consist of a huffCode which is a String value representing the
address of the node from the root of the Huffman Tree.
*/

public class HuffmanNode {

  int value;
  HuffmanNode left, right;
  String huffCode;

  //Constructors
  public HuffmanNode(int value) {
    this.value = value;
    left = null;
    right = null;
    huffCode = "";
  }

  public HuffmanNode(int value, String huff) {
    this.value = value;
    left = null;
    right = null;
    huffCode = huff;
  }

  public HuffmanNode(int value, HuffmanNode left, HuffmanNode right) {
    this.value = value;
    this.left = left;
    this.right = right;
    huffCode = "";
  }

  //Printing the huffman tree rooted at this node
  public void print() {
    if (left != null)
      left.print();
    System.out.println(value + " - " + huffCode);
    if (right != null)
      right.print();
  }

}
