/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


import java.util.TreeMap;

/**
 *
 * @author Steven
 */
public class StevenBinary {

    private class Node {

        String data;
        Node left;
        Node right;

        Node() {
            data = null;
            left = null;
            right = null;
        }
        /*
         Node(String nodeCarData, String nodeCdrData) {
         carData = nodeCarData;
         cdrData = nodeCdrData;
         left = null;
         right = null;
         }*/
    }
    Node root;
    Scanner fileScanner;
    TreeMap<String, String> symbol_table;

    StevenBinary() {
        root = null;
    }

    StevenBinary(Scanner x) {
        symbol_table = new TreeMap<String, String>();
        fileScanner = x;
        root = new Node();
        
        String[] initialize = fileScanner.nextToken();
        
        while(initialize[0] != null)
        {     	  
      	  add(initialize[0], initialize[1], root);
      	  initialize = fileScanner.nextToken();
        }
    }

    private void add(String nextData, String identifier, Node startNode) {
        if (!identifier.equalsIgnoreCase("symbol") && !identifier.equalsIgnoreCase("number")
                && !identifier.equalsIgnoreCase("reserved_word") && !identifier.equalsIgnoreCase("word")) {
            //ADD TO SYMBOL TABLE HERE
            symbol_table.put(nextData, null);
        }
        Node currentNode = startNode;
        //if the next token is a ( then create left node and recurse
        if (nextData.equals("(")) {
            String[] nextInput = fileScanner.nextToken();
            if (nextData.equals(")")) {
                return;
            }
            currentNode.left = new Node();
            add(nextInput[0], nextInput[1], currentNode.left);
        }
        //if there is no data in the current node
        if (currentNode.data == null) {
            currentNode.data = nextData;
        } //if there is already data in the current node, create a right node and put data in it
        else {
            currentNode.right = new Node();
            add(nextData, identifier, currentNode.right);
        }
    }

    void print(Node n) {
        if (n == root) {
            System.out.print("(");
        }
        //if it's a leaf
        if (n.left == null && n.right == null) {
            System.out.print(n.data + " ");
            return;
        }

        if (n.left != null && n.left.data == null) {
            System.out.println();
            System.out.print("(");
            print(n.left);
        } else //n.left is not null AND there is data there
        {
            print(n.left);
        }

        if (n.right == null) {
            System.out.print(")");
            return;
        } else //(n.right != null)
        {
            print(n.right);
        }
    }
}
