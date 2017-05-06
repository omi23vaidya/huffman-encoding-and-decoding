/*
Created by: Omkar Vaidya
DaryHeap is a Heap structure that can create a m-degree heap as well as m-degree cached optimized heap.
Here it is used to create a binary heap, 4 way heap and a 4 way cache optimized heap.
The PairingHeapNode contains a pointer to a HuffmanNode to store a link to corresponding node in Huffman Tree 
during the building process. It will otherwise have a null value. The degree indicates the max number of children each parent node 
will have in the heap whereas the shift will indicate at which place will the root be located in the ArrayList for a 
4-way cache optimized heap.
*/

import java.util.ArrayList; //Vector replaced by ArrayList to reduce execution time and as synchronization is not required here
public class DaryHeap {

  int size;
  int degree;
  int shift;
  ArrayList<Node> heap;//Vector<Node> heap;

  //Constructor parameters: (initial size of the array), degree (number of children) and shift (index of root)
  public DaryHeap (int size, int degree, int shift) {
    this.size = size;
    this.degree = degree;
    this.shift = shift;
    heap = new ArrayList<Node>(size+shift);//heap = new Vector<Node>(size+shift);
    for (int i = 0; i < shift; i++) {
      heap.add(new Node(-1, -1));
    }
  }

  //Check if the heap is empty
  public boolean isEmpty() {
    return (heap.size() <= shift);
  }

  //Returns the size of the heap
  public int size() {
    return (heap.size() - shift);
  }

  //Print the values in the heap
  public void printHeap () {
    for (int i = 0; i<heap.size(); i++) {
      System.out.print(" - " + heap.get(i).value() + "|" + heap.get(i).frequency());
    }
  }

  //Return huffman node pointer at root node
  public HuffmanNode getHuffmanTreeAtRoot() {
      if (isEmpty())
        return null;
      else
        return heap.get(shift).pHuff;
  }

  //-------------------------------------------------------------------------

  //Return the parent index of the child index passed as argument
  public int parent (int child) {
    return ((child - shift - 1) / degree) + shift;
  }

  //Return the Kth child's index of the parent index passed as argument
  public int kthChild (int parent, int k) {
    return ((parent - shift) * degree) + shift + k;
  }

  //-------------------------------------------------------------------------

  //Empty the heap
  public void flush() {
    heap.clear();//heap.removeAllElements();
    for (int i = 0; i < shift; i++) {
      heap.add(new Node(-1, -1));
    }
  }

  //Insert a new value-value pair
  public void insert (int k, int f) {
    heap.add(new Node(k,f));
    heapifyUp(heap.size() - 1);
  }

  public void insert (Node n) {
    heap.add(n);
    heapifyUp(heap.size() - 1);
  }

  //Deletes the min element and returns the node
  public Node deleteMin () {
    if (heap.size() <= shift) {
      System.out.println("Heap is empty.");
      return null;
    }
    else if (heap.size() - shift == 1)
    {
      return heap.remove(heap.size() - 1);
    }
    else {
      Node min = heap.get(shift);
      heap.set(shift, heap.get(heap.size() - 1));
      heap.remove(heap.size()-1);
      heapifyDown(shift);
      return min;
    }
  }

  //--------------------------------------------------------------------------

  //Returns the index of the minimum child
  public int minChild(int n) {
    int bestChild = kthChild(n, 1);
    if (bestChild >= heap.size())
      return bestChild;
    else {
        int iterator = 2;
        int child = bestChild + 1;
        while ((iterator <= degree) && (child < heap.size())) {
          if (heap.get(child).frequency() < heap.get(bestChild).frequency()) {
            bestChild = child;
          }
          child++;
          iterator++;
        }
      }
    return bestChild;
  }

  //--------------------------------------------------------------------------

  //Function to bubble up values that so as to maintain heap property
  public void heapifyUp (int child) {
    Node temp = heap.get(child);
    while ((child > shift) && (temp.frequency() < heap.get(parent(child)).frequency())) {
      heap.set(child, heap.get(parent(child)));
      child = parent(child);
    }
    heap.set(child,temp);
  }

  //Function to push down values that so as to maintain heap property
  public void heapifyDown (int parent) {
    int child;
    Node temp = heap.get(parent);
    while (kthChild(parent, 1) < heap.size()) {
      child = minChild(parent);
      if (heap.get(child).frequency() < temp.frequency()) {
          heap.set(parent, heap.get(child));
      }
      else
        break;
      parent = child;
    }

    heap.set(parent, temp);
  }

  //--------------------------------------------------------------------------

}
