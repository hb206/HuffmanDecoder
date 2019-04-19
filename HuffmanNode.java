// This is the node class for the HuffmanNode program

import java.io.*;
import java.util.*;

public class HuffmanNode implements Comparable<HuffmanNode> {

   public HuffmanNode left;
   public HuffmanNode right;
   public int data;
   public int chars;

   public HuffmanNode(int data, int chars) {
      this(data, chars, null, null);
   }

   public HuffmanNode(int data, int chars, HuffmanNode left, HuffmanNode right) {
      this.data = data;
      this.chars = chars;
      this.left = left;
      this.right = right;
   }

   public int compareTo(HuffmanNode other) {
      return this.data-other.data;
   }
}
