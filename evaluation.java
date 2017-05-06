/*
	Created by: Omkar Vaidya 
	Evaluation class is used to evaluate which of the following priority queue structures gives the best performance:
	1. binary heap 2. 4-ary heap 3. 4-way cache optimized heap 4. Pairing heap. This program takes a filename
	as input which will be evaluated. It builds a frequency table using a frequencyTable array and using this array, builds
	different heaps and the Huffman Tree using those heap structures. We run the construction of heap & tree 
	in a loop for ten times so as to get a better judgement for analysis of time.
*/

import java.util.Scanner;
import java.io.FileReader;
import java.lang.Exception;
import java.util.Arrays;
import java.io.FileNotFoundException;
import java.lang.ArrayIndexOutOfBoundsException;
public class evaluation {

  public static void main (String[] args) throws Exception {

    try {
    //------------------------------------------------------------------//
	//tracking the size of frequencyTable
    int count = 0;
    
	//int heapSize;
    //Initialize the frequency table that will be used to build the heap
	int[] frequencyTable = new int[1000000];
	
	//initializing frequencyTable array
    Arrays.fill(frequencyTable, 0);

    //------------------------------------------------------------------//

    //Read the input file and update frequencies
    Scanner scan = new Scanner(new FileReader(args[0]));
    System.out.println("Evaluating....\n");
    //System.out.println("------------------------------------------------------");
    System.out.println("Frequency Table being built...");
    float start = System.nanoTime();
    while (scan.hasNext())
    {
      if ((frequencyTable[scan.nextInt()]++) == 0)
        count++;
    }
    scan.close();
    float stop = System.nanoTime();
    stop = (stop - start) / 1000000;
    System.out.println("Frequency Built: " + Math.round(stop) + " milliseconds");

    //------------------------------------------------------------------//

    //Build a binary heap using the frequency table
    System.out.println("\nEvaluating Binary Heap:");
	
	/*Program for d-ary heap which has 3 parameters: number of values to be encoded, number of children each node can have
	(eg. 2 for binary heap, 4 for 4 way heap ) and a shift value (used for cache optimized 4-way heap)
	*/
    DaryHeap binaryheap = new DaryHeap(count, 2, 0);
    start = System.nanoTime();

    for (int j=0; j<10; j++) {

        //Populate the binary heap
        for (int i=0; i<1000000; i++) {
          if (frequencyTable[i] > 0)
            binaryheap.insert(i, frequencyTable[i]);
        }

        //Building the huffman tree
        HuffmanNode treeleft, treeright, treeparent;
        Node heapleft, heapright, heapparent;

        while (binaryheap.size() > 1) {
           heapleft = binaryheap.deleteMin();
           heapright = binaryheap.deleteMin();

           if (heapleft.pHuff == null)
            treeleft = new HuffmanNode(heapleft.value());
           else
            treeleft = heapleft.pHuff;

           if (heapright.pHuff == null)
            treeright = new HuffmanNode(heapright.value());
           else
            treeright = heapright.pHuff;

           treeparent = new HuffmanNode(-1, treeleft, treeright);
           heapparent = new Node(-1, heapleft.frequency() + heapright.frequency(), treeparent);
           binaryheap.insert(heapparent);
        }
        treeparent = binaryheap.getHuffmanTreeAtRoot();

        //Empty the binary heap
        binaryheap.flush();
    }

    stop = System.nanoTime();
    stop = (stop - start) / 1000000;
    System.out.println("Time using binary heap: " + Math.round(stop) + " milliseconds");

    //------------------------------------------------------------------//

    //Build a four heap using the frequency table
    System.out.println("\nEvaluating Four-ary Heap...");
    DaryHeap fourheap = new DaryHeap(count, 4, 0);
    start = System.nanoTime();

    for (int j=0; j<10; j++) {

        //Populate the four heap
        for (int i=0; i<1000000; i++) {
          if (frequencyTable[i] > 0)
            fourheap.insert(i, frequencyTable[i]);
        }

        //Build the huffman tree
        HuffmanNode treeleft, treeright, treeparent;
        Node heapleft, heapright, heapparent;

        while (fourheap.size() > 1) {
           heapleft = fourheap.deleteMin();
           heapright = fourheap.deleteMin();

           if (heapleft.pHuff == null)
            treeleft = new HuffmanNode(heapleft.value());
           else
            treeleft = heapleft.pHuff;

           if (heapright.pHuff == null)
            treeright = new HuffmanNode(heapright.value());
           else
            treeright = heapright.pHuff;

           treeparent = new HuffmanNode(-1, treeleft, treeright);
           heapparent = new Node(-1, heapleft.frequency() + heapright.frequency(), treeparent);
           fourheap.insert(heapparent);
        }
        treeparent = fourheap.getHuffmanTreeAtRoot();

        //Empty the four heap
        fourheap.flush();
    }

    stop = System.nanoTime();
    stop = (stop - start) / 1000000;
    System.out.println("Time using 4-ary heap: " + Math.round(stop) + " milliseconds");

    //------------------------------------------------------------------//

    //Build a cache optimized four heap using the frequency table
    System.out.println("\nEvaluating 4-way Cache Optimized Heap...");
    DaryHeap fourwaycacheheap = new DaryHeap(count, 4, 3);
    start = System.nanoTime();

    for (int j=0; j<10; j++) {

        //Populate the cache optimized four heap
        for (int i=0; i<1000000; i++) {
          if (frequencyTable[i] > 0)
            fourwaycacheheap.insert(i, frequencyTable[i]);
        }

        //Build the huffman tree
        HuffmanNode treeleft, treeright, treeparent;
        Node heapleft, heapright, heapparent;

        while (fourwaycacheheap.size() > 1) {
           heapleft = fourwaycacheheap.deleteMin();
           heapright = fourwaycacheheap.deleteMin();

           if (heapleft.pHuff == null)
            treeleft = new HuffmanNode(heapleft.value());
           else
            treeleft = heapleft.pHuff;

           if (heapright.pHuff == null)
            treeright = new HuffmanNode(heapright.value());
           else
            treeright = heapright.pHuff;

           treeparent = new HuffmanNode(-1, treeleft, treeright);
           heapparent = new Node(-1, heapleft.frequency() + heapright.frequency(), treeparent);
           fourwaycacheheap.insert(heapparent);
        }
        treeparent = fourwaycacheheap.getHuffmanTreeAtRoot();

        //Empty the cache optimized four heap
        fourwaycacheheap.flush();
    }

    stop = System.nanoTime();
    stop = (stop - start) / 1000000;
    System.out.println("Time using 4-Way cache optimized heap: " + Math.round(stop) + " milliseconds");

    //------------------------------------------------------------------//

    //Build a pairing  heap using the frequency table
    System.out.println("\nEvaluating Pairing Heap...");
    PairingHeap pairingheap = new PairingHeap();
    start = System.nanoTime();

    for (int j=0; j<10; j++) {

        //Populate the pairing heap
        for (int i=0; i<1000000; i++) {
          if (frequencyTable[i] > 0)
            pairingheap.insert(i, frequencyTable[i]);
        }

        //Build the huffman tree
        HuffmanNode treeleft, treeright, treeparent;
        PairingHeapNode heapleft, heapright, heapparent;

        while (pairingheap.size() > 1) {
           heapleft = pairingheap.deleteMin();
           heapright = pairingheap.deleteMin();

           if (heapleft.pHuff == null)
            treeleft = new HuffmanNode(heapleft.value());
           else
            treeleft = heapleft.pHuff;

           if (heapright.pHuff == null)
            treeright = new HuffmanNode(heapright.value());
           else
            treeright = heapright.pHuff;

           treeparent = new HuffmanNode(-1, treeleft, treeright);
           heapparent = new PairingHeapNode(-1, heapleft.frequency() + heapright.frequency(), treeparent);
           pairingheap.insert(heapparent);
        }
        treeparent = pairingheap.getHuffmanTreeAtRoot();

        //Empty the pairing heap
        pairingheap.flush();
    }

    stop = System.nanoTime();
    stop = (stop - start) / 1000000;
    System.out.println("Time using pairing heap: " + Math.round(stop) + " milliseconds");
    System.out.println("------------------------------------------------------");
    //------------------------------------------------------------------//
  }
  catch(FileNotFoundException e) {
    System.out.println("File not found. Please provide correct filename as command line argument.");
  }
  catch(ArrayIndexOutOfBoundsException e) {
    System.out.println("Please provide input filename");
  }
  catch (Exception e) {
    e.printStackTrace();
  }
  }
}
