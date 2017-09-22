# huffman-encoding-and-decoding
Used Huffman coding to reduce data size while transferring enormous amount of data. First step is to identify which of the following priority queue structures: binary heap, 4-way cache optimized heap and pairing heap gives the best performance for generating Huffman codes. The encoder reads the input file and gives a compressed version of input file and code table. The decoder takes the encoded message and code table as input, constructs decode tree and generates a decoded message from the decode tree.

Steps to run the program:
1. After cloning the project, compile all .java files 
2. You can use sample_input_small.txt and sample_input_large.txt files for testing. 
3. Create similar input files which contain an integer value of range 0 to 999999 on each line 
4. Run the following .java files in the following order:
  1. java evaluation input_file.txt
  2. java encoder input_file.txt
  3. java decoder encoded.bin code_table.txt
