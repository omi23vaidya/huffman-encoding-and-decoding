/*
Created by: Omkar Vaidya
The Encoder program takes one value (filename) as input in command line arguments.
It will then build a frequency table for this file, build a four way cache optimized heap and then
using huffman coding, creates a compressed file named encoded.bin in binary form. It also creates
the huffman code table named code_table.txt which should be used for reconstructing the original file.
*/
import java.util.Scanner;
import java.io.FileReader;
import java.util.Arrays;
import java.io.FileNotFoundException;
import java.lang.ArrayIndexOutOfBoundsException;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedReader;

import java.io.IOException;
import java.io.FileReader;
import java.io.OutputStream;
import java.io.FileOutputStream;

public class encoder {

  public static void main (String[] args) throws Exception {

    try {
    String filename = args[0];
    int count = 0;
    int heapSize;
    int[] frequencyTable = new int[1000000];
    Arrays.fill(frequencyTable, 0);

    //------------------------------------------------------------------//

    //System.out.println("------------------------------------------------------");
    //Read the input file and update frequencies
    float total = 0;
    float start = System.nanoTime();
    Scanner scan = new Scanner(new FileReader(filename));
    while (scan.hasNext())
    {
      if ((frequencyTable[scan.nextInt()]++) == 0)
        count++;
    }
    scan.close();
    float stop = System.nanoTime();
    stop = (stop - start) / 1000000;
    total += Math.round(stop);
    System.out.println("  Frequency Table Build Time : " + Math.round(stop) + " milliseconds");

    //------------------------------------------------------------------//

    //Build a cache optimized heap using the frequency table
    start = System.nanoTime();
    DaryHeap myHeap = new DaryHeap(count, 4, 3);

    for (int i=0; i<1000000; i++) {
      if (frequencyTable[i] > 0)
        myHeap.insert(i, frequencyTable[i]);
    }
    frequencyTable = null;
    stop = System.nanoTime();
    stop = (stop - start) / 1000000;
    total += Math.round(stop);
    System.out.println("  Heap Build Time            : " + Math.round(stop) + " milliseconds");

    //------------------------------------------------------------------//

    start = System.nanoTime();
    TreeBuilder myTreeBuilder = new TreeBuilder(myHeap);
    HuffmanTree huffTree = myTreeBuilder.getHuffmanTree();
    String[] huffCodes = myTreeBuilder.getHuffCodeTable();
    stop = System.nanoTime();
    stop = (stop - start) / 1000000;
    total += Math.round(stop);
    System.out.println("  Huffman Tree Building Time    : " + Math.round(stop) + " milliseconds");

    start = System.nanoTime();
    
	
	BufferedWriter output;
	
	
	//CodeTableWriter myCodeTableWriter = new CodeTableWriter("code_table.txt", huffTree.getRoot());
	//filename = "code_table.txt";
	//root = huffTree.getRoot();
	
	
    createFile("code_table.txt", huffTree.getRoot());
    stop = System.nanoTime();
    stop = (stop - start) / 1000000;
    total += Math.round(stop);
    System.out.println("  Writing Code Table File Time : " + Math.round(stop) + " milliseconds");

    start = System.nanoTime();
	
	String inputfilename;
	String outputfilename;
	//String[] huffCodes;
	StringBuilder binaryString = new StringBuilder("");
	
    //BinaryFileWriter myBinaryFileWriter = new BinaryFileWriter(filename,"encoded.bin",huffCodes);
    inputfilename = filename;
	outputfilename = "encoded.bin";
	//huffCodes = huffCodes;
	
	encode(huffCodes, inputfilename, binaryString, outputfilename);
    stop = System.nanoTime();
    stop = (stop - start) / 1000000;
    total += Math.round(stop);
    System.out.println("  Writing Encoded Bin File     : " + Math.round(stop) + " milliseconds");
    System.out.println("  Total Time                 : " + Math.round(total) + " milliseconds");
    System.out.println("------------------------------------------------------");
  }

  catch(FileNotFoundException e) {
    System.out.println("File not found. Please provide correct filename as command line argument.");
  }
  catch(ArrayIndexOutOfBoundsException e) {
    System.out.println("Please provide the filename which you want to encode");
  }
  catch (Exception e) {
    e.printStackTrace();
  }

  }
  
  
  
  public static void encode(String[] huffCodes, String inputfilename, StringBuilder binaryString, String outputfilename) {

    try {
      BufferedReader bf = new BufferedReader(new FileReader(inputfilename));

      String str;
      while(((str=bf.readLine())!=null) && (!(str.replace(" ","").equals(""))))
      {
        binaryString.append(huffCodes[Integer.parseInt(str)]);
      }

      OutputStream opStream = new FileOutputStream(outputfilename);
      opStream.write(formBinary(binaryString.toString()));
      opStream.flush();
      opStream.close();
    }

    catch(IOException e) {
      e.printStackTrace();
    }
  }
  
   protected static byte[] formBinary(String s)
  {
      int sLen = s.length();
      byte[] toReturn = new byte[(sLen + Byte.SIZE - 1) / Byte.SIZE];
      char c;
      for( int i = 0; i < sLen; i++ )
          if( (c = s.charAt(i)) == '1' )
              toReturn[i / Byte.SIZE] = (byte) (toReturn[i / Byte.SIZE] | (0x80 >>> (i % Byte.SIZE)));
          else if ( c != '0' )
              throw new IllegalArgumentException();
      return toReturn;
  }
  
  
  public static void createFile(String filename, HuffmanNode root) {

    try {
	BufferedWriter output;
      output = new BufferedWriter(new FileWriter(new File(filename)));
      printInOrder(root, output);
      output.close();
    }
    catch(IOException e) {
      e.printStackTrace();
    }
  }
  
  
  public static void printInOrder(HuffmanNode t, BufferedWriter output) {
    if (t == null)
      return;
    printInOrder(t.left, output);
    if (t.value != -1) {
      try {
        output.write(t.value + " " + t.huffCode + "\n");
      }
      catch (IOException e) {
        e.printStackTrace();
      }
    }
    printInOrder(t.right, output);
  }
  
}
