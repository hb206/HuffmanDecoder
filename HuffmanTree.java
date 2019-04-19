// CSE 
// Assignment 8
// This program shows how text files can be compressed by using
// a coding scheme based on the frequency of characters

import java.io.*;
import java.util.*;

public class HuffmanTree {

   private HuffmanNode overallRoot;

   public HuffmanTree(int[] count) {
      Queue<HuffmanNode> numbers = new PriorityQueue<HuffmanNode>();
      for(int i = 0; i<count.length; i++) {
         if (count[i] > 0) {
         numbers.add(new HuffmanNode(count[i], i));
         }
      }
      numbers.add(new HuffmanNode(1, count.length));
      while (numbers.size() > 1) {
         HuffmanNode first = numbers.remove();
         HuffmanNode second = numbers.remove();
         HuffmanNode resultingAns = new HuffmanNode(first.data + second.data, 0, first, second);
         numbers.add(resultingAns);
      }
      overallRoot = numbers.remove();
   }
   
   public void write(PrintStream output) {
      if (overallRoot != null) {
      writeHelp(output, overallRoot, "");
      }
   }
   
   public void writeHelp(PrintStream output, HuffmanNode root, String find) {
      if(root.left == null) {
         output.println(root.chars);
         output.println(find);
      } else {
         writeHelp(output, root.left, find + "0");
         writeHelp(output, root.right, find + "1");
      }
   }
   
   public HuffmanTree(Scanner input) {
      while (input.hasNextLine()) {
         int charVal = Integer.parseInt(input.nextLine()); 
         String path = input.nextLine();
         overallRoot = constructHelp(overallRoot, path, charVal);
      }
   }

   private HuffmanNode constructHelp(HuffmanNode root, String path, int charVal) {
      if (path.length() == 0) {
         return new HuffmanNode(0, charVal);
      }
      if (root == null) {
         root = new HuffmanNode(0, 0);
      } 
      if (path.charAt(0) == '0') {
         root.left = constructHelp(root.left, path.substring(1), charVal);
      } else {
         root.right = constructHelp(root.right, path.substring(1), charVal);
      }
      return root;
   }
 
   public void decode(BitInputStream input, PrintStream output, int eof) {
      HuffmanNode current = overallRoot;
      while (current.chars != eof) {
         if (current.left == null && current.right == null) {
            output.write(current.chars);
            current = overallRoot;
         } else if (input.readBit() == 0) {
            current = current.left;
         } else {
            current = current.right;
         }
      }
   }
}



  
 
