// This is the node class for the HuffmanNode program. This creates a HuffmanNode
// object.

import java.io.*;
import java.util.*;

public class HuffmanNode implements Comparable<HuffmanNode> {
   // either null or represents a character -left pointer
   public HuffmanNode left;
   // either null or represents a character
   public HuffmanNode right;
   // ascii of the character or null (for example, "a"'s ascii is 097
   public int data;
   // how often character shows up (count)
   public int freq;

   public HuffmanNode(int freq, int data) {
      this(freq, data, null, null);
   }

   public HuffmanNode(int freq, int data, HuffmanNode left, HuffmanNode right) {
      this.freq = freq;
      this.data = data;
      this.left = left;
      this.right = right;
   }

   public int compareTo(HuffmanNode other) {
      return this.freq-other.freq;
   }
}
