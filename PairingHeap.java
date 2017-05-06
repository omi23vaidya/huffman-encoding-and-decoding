/*
Created by: Omkar Vaidya
Implementation of Pairing heap.
*/

public class PairingHeap {

  PairingHeapNode root;
  int size;

  //------------------------------------------------------------//

  public PairingHeap () {
    root = null;
    size = 0;
  }

  //------------------------------------------------------------//

  public boolean isEmpty() {
    return (root == null);
  }

  public void flush() {
    root = null;
    size = 0;
  }

  public void printHeap() {
    System.out.print("\n");
    print(root);
    System.out.print("\n");
  }

  public void print(PairingHeapNode hn) {
    if (hn == null)
      return;
    print(hn.child);
    System.out.print(" " + hn.frequency());
    print(hn.right);
  }

  //------------------------------------------------------------//

  public int getMinFrequency() {
    return root.frequency();
  }

  public int size() {
    return size;
  }

  public HuffmanNode getHuffmanTreeAtRoot () {
    return root.pHuff;
  }

  //------------------------------------------------------------//


    public void insert (int v, int f) {
        PairingHeapNode hn = new PairingHeapNode(v,f);
        insert(hn);
    }

    public void insert (PairingHeapNode hn) {

        if (isEmpty()) {
          root = hn;
        }

        else if (getMinFrequency() > hn.frequency()) {
          hn.setChild(root);
          root.left = hn;
          root = hn;
        }

        else {
            hn.right = root.child;
            if (root.child != null)
              root.child.left = hn;
            root.child = hn;
            hn.left = root;
        }
        size++;
      }

    public PairingHeapNode deleteMin() {
      if (isEmpty()) {
        System.out.println("Heap is empty");
        return null;
      }

      else if (root.child == null) {
          PairingHeapNode min = root;
          root = null;
          size--;
          return min;
      }

      PairingHeapNode min = root;
      PairingHeapNode child = root.child;
      PairingHeapNode temp = child;
      int count = 0;

      while (temp != null) {
        count++;
        temp = temp.right;
      }

      if (count%2 != 0)
        count++;

      PairingHeapNode[] passOne = new PairingHeapNode[count];

      temp = child;
      for (int i=0; i<count; i++) {
        passOne[i] = temp;
        if (temp != null)
          temp = temp.right;
      }

      for (int i=0; i<count; i++) {
        if (passOne[i] != null) {
          passOne[i].left = null;
          passOne[i].right = null;
        }
      }

      for (int i = 0; i < count; i=i+2) {
        passOne[i] = meldHeaps(passOne[i], passOne[i+1]);
      }

      temp = passOne[count-2];
      for (int i=count-4; i>=0; i=i-2) {
        temp = meldHeaps(temp, passOne[i]);
      }
      root = temp;
      size--;
      return min;
    }

    //------------------------------------------------------------//

    public PairingHeapNode meldHeaps(PairingHeapNode heap1, PairingHeapNode heap2) {
      if (heap1 == null)
        return heap2;
      else if (heap2 == null)
        return heap1;
      else {
        if (heap1.frequency() < heap2.frequency()) {
          heap2.right = heap1.child;
          if (heap1.child != null)
            heap1.child.left = heap2;
          heap1.child = heap2;
          heap2.left = heap1;
          return heap1;
        }
        else {
          heap1.right = heap2.child;
          if (heap2.child != null)
            heap2.child.left = heap1;
          heap2.child = heap1;
          heap1.left = heap2;
          return heap2;
        }
      }
    }

    //------------------------------------------------------------//

}
