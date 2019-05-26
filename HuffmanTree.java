// This program shows how text files can be compressed by using
// a coding scheme based on the frequency of characters

import java.io.*;
import java.util.*;

public class HuffmanTree {
   // root for HuffmanTree
   private HuffmanNode overallRoot;
   
   // accepts array of mapped character counts. creates priority
   // queue and stores a character and its count as a HuffmanNode object.
   // priority queue will organize them into ascending order
   // accepts array of characters and their corresponding frequencies as param
   public HuffmanTree(int[] count) {
     
      // priotity queue to store nodes
      Queue<HuffmanNode> numbers = new PriorityQueue<HuffmanNode>();
      // create new HuffmanNode object for every character in array
      for(int i = 0; i < count.length; i++) {
         if (count[i] > 0) {
         // add the new HuffmanNode object to priority queue
         numbers.add(new HuffmanNode(count[i], i));
         }
      }
 
      numbers.add(new HuffmanNode(1, count.length));
      // takes the 2 HuffmanNodes with the lowest frequencies and
      // creates a new HuffmanNode whose frequency is sum of the two 
      // we are combining. add the subtree back to queue. 
      while (numbers.size() > 1) {
         HuffmanNode leftNode = numbers.remove();
         HuffmanNode rightNode = numbers.remove();
         numbers.add(new HuffmanNode(leftNode.freq + rightNode.freq, 0, leftNode, rightNode));
      }
      // when down to one tree, set this as the overall root for the entire tree structure
      overallRoot = numbers.remove();
   }
   
   // write tree to given output stream
   public void write(PrintStream output) {
      if (overallRoot != null) {
      // send to helper method to use recursion to write to file
      writeHelp(output, overallRoot, "");
      }
   }
   
   // helper method for write method, checks to see if we've reached a leaf node
   // if we have, print ASCII value of char and its code (recursive steps in tree)
   // to that character. if not then recurse adding left and right node
   public void writeHelp(PrintStream output, HuffmanNode root, String find) {
     //if we've reached a leaf node
      if(root.left == null && root.right == null) {
         // print the data of the node and its Huffman Code to file
         output.println(root.data);
         output.println(find);
      } else {
         // use recursion to test different paths to find leaf node by adding 1 and 0 to 
         // codes
         writeHelp(output, root.left, find + "0");
         writeHelp(output, root.right, find + "1");
      }
   }
   
   // reconstructs the HuffmanTree to decode encoded file (scanner is passed coded
   // tree stored in standard format, output we created with MakeCode)
   public HuffmanTree(Scanner input) {
      while (input.hasNextLine()) {
         // charval is the ASCII value
         int charVal = Integer.parseInt(input.nextLine()); 
         // this is the binary string/ tree path for the character
         String path = input.nextLine();
         // send ASCII character and binary string to helper method to piece tree together
         overallRoot = constructHelp(overallRoot, path, charVal);
      }
   }

   // frequencies don't matter because Tree/paths has already been constructed beforehand
   // reconstruct the nodes
   private HuffmanNode constructHelp(HuffmanNode root, String path, int charVal) {
      // if length of the binary string is 0, write the character at the leaf node
      if (path.length() == 0) {
         return new HuffmanNode(0, charVal);
      }
      // if node is empty, create empty HuffmanNode
      if (root == null) {
         root = new HuffmanNode(0, 0);
      } 
      // if next character in String is equal to 0, remove the char from String
      // and traverse down the left node
      if (path.charAt(0) == '0') { // creates leaf node containing letter
         root.left = constructHelp(root.left, path.substring(1), charVal);
      // if next character in String is equal to 1, remove the char from String
      // and traverse down the right node
      } else { // continue recursively creating nodes
         root.right = constructHelp(root.right, path.substring(1), charVal);
      }
      return root;
   }

   public void decode(BitInputStream input, PrintStream output, int eof) {
      HuffmanNode current = overallRoot;
      // while character value does not equal eof value
      while (current.data != eof) {
         // if this is a leaf node, print out the character 
         if (current.left == null && current.right == null) {
            output.write(current.data);
            current = overallRoot;
         } else if (input.readBit() == 0) {
            current = current.left;
         } else {
            current = current.right;
         }
      }
   }
}




  
 
