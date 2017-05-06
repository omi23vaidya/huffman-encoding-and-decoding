/*
Created by: Omkar Vaidya
This program uses encoded.bin and code_table.txt files to reconstruct
the huffman tree and the original file.
*/
import java.io.File;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.lang.ArrayIndexOutOfBoundsException;

import java.io.InputStream;
import java.io.FileInputStream;

public class decoder {

  public static void main(String[] args) throws Exception {

    try {

      int data;
      String path;
      HuffmanNode hRoot = new HuffmanNode(-1);
      HuffmanTree hTree = new HuffmanTree(hRoot);

      File outputfile = new File("decoded.txt");
      String code_filename = args[1];
      File code_file = new File(args[1]);
      String binary_filename = args[0];

      //System.out.println("------------------------------------------------------");
      float total = 0;
      float start = System.nanoTime();
      // Scanner scan = new Scanner(code_file);
      Scanner scan = new Scanner(new BufferedReader(new FileReader(code_filename)));

      while(scan.hasNext()) {
        data = scan.nextInt();
        path = scan.next();
        hTree.add(data, path);
      }
      scan.close();
      float stop = System.nanoTime();
      stop = (stop - start) / 1000000;
      total += stop;
      System.out.println("  Huffman Tree Build Time    : " + Math.round(stop) + " milliseconds");

      start = System.nanoTime();
	  String inputfilename;
      //BinaryFileReader myBinaryFileReader = new BinaryFileReader(binary_filename);
	  inputfilename = binary_filename;
	  
      StringBuilder binaryString = read(inputfilename);
      stop = System.nanoTime();
      stop = (stop - start) / 1000000;
      total += stop;
      System.out.println("  Binary File Read Time      : " + Math.round(stop) + " milliseconds");

      start = System.nanoTime();
      StringBuilder s = hTree.build(binaryString.toString());
      stop = System.nanoTime();
      stop = (stop - start) / 1000000;
      total += stop;
      System.out.println("  Build Original String Time : " + Math.round(stop) + " milliseconds");

      start = System.nanoTime();
      BufferedWriter bwr = new BufferedWriter(new FileWriter(outputfile));
      bwr.write(s.toString());
      bwr.flush();
      bwr.close();
      stop = System.nanoTime();
      stop = (stop - start) / 1000000;
      total += stop;
      System.out.println("  Write Decoded File Time    : " + Math.round(stop) + " milliseconds");
      System.out.println("  Total Time                 : " + Math.round(total) + " milliseconds");
      System.out.println("------------------------------------------------------");
    }
    catch(FileNotFoundException e) {
      System.out.println("File not found. Please provide correct filenames as command line argument.");
      e.printStackTrace();
    }
    catch(ArrayIndexOutOfBoundsException e) {
      System.out.println("Please provide filenames for encoded binary file and the code table file as input.");
    }
    catch (Exception e) {
      e.printStackTrace();
    }
  }
  public static StringBuilder read(String inputfilename) {
    StringBuilder binaryString = new StringBuilder("");
    try {
      InputStream inputStream = new FileInputStream(new File(inputfilename));
      byte[] b;

      while (inputStream.available() > 1) {
        int n = inputStream.available();
        b = new byte[n];
        inputStream.read(b);
        binaryString.append(toBinaryString(b));
      }
      inputStream.close();
    }

    catch(IOException e) {
      e.printStackTrace();
    }
    finally {
        return binaryString;
    }
  }
  
  
  public static String toBinaryString(byte[] bytes)
  {
    StringBuilder sb = new StringBuilder(bytes.length * Byte.SIZE);
    for( int i = 0; i < Byte.SIZE * bytes.length; i++ )
        sb.append((bytes[i / Byte.SIZE] << i % Byte.SIZE & 0x80) == 0 ? '0' : '1');
    return sb.toString();
  }
  
}
