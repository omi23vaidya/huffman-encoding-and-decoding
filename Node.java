/*
Created by: Omkar Vaidya
Node class provides node structure for objects in the DaryHeap.
It consist of value and its frequency.
*/

public class Node {

  int value;
  int frequency;
  HuffmanNode pHuff;

  //Constructors
  public Node (int k, int f) {
    value = k;
    frequency = f;
    pHuff = null;
  }

  public Node (int k, int f, HuffmanNode p) {
    value = k;
    frequency = f;
    pHuff = p;
  }

  //Setter Function
  public void incrementFrequency () {
    frequency++;
  }

  //Getter Functions
  public int frequency() {
    return frequency;
  }

  public int value() {
    return value;
  }

}
