/*
Created by: Omkar Vaidya
PairingHeapNode class provides node structure for objects in the Pairing Heap.
It consist of key value and its frequency and a pointer to a HuffmanNode
to assist during the construction of the Huffman tree.
*/

public class PairingHeapNode {

  int value;
  int frequency;
  PairingHeapNode child;
  PairingHeapNode left;
  PairingHeapNode right;
  HuffmanNode pHuff;

  //Constructors
  public PairingHeapNode(int value, int frequency) {
    this.value = value;
    this.frequency = frequency;
    child = null;
    left = null;
    right = null;
    pHuff = null;
  }

  public PairingHeapNode(int value, int frequency, HuffmanNode pHuff) {
    this.value = value;
    this.frequency = frequency;
    child = null;
    left = null;
    right = null;
    this.pHuff = pHuff;
  }

  //Get Functions
  public int value() {
    return value;
  }

  public int frequency() {
    return frequency;
  }

  public PairingHeapNode child() {
    return child;
  }

  public PairingHeapNode left() {
    return left;
  }

  public PairingHeapNode right() {
    return right;
  }

  //Set Functions
  public void setChild(PairingHeapNode child) {
    this.child = child;
  }

  public void setLeft(PairingHeapNode left) {
    this.left = left;
  }

  public void setRight(PairingHeapNode right) {
    this.right = right;
  }

}
