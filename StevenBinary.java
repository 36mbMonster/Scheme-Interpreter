/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg152parserstuff;

import java.util.ArrayList;
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
    ArrayList<Node> trees;
    int scopeLevel;
    
    StevenBinary() {
        root = null;
        scopeLevel = 0;
    }

    StevenBinary(Scanner x) {
        scopeLevel = 0;
        symbol_table = new TreeMap<String, String>();
        fileScanner = x;
        root = new Node();
        String[] initialize = fileScanner.nextToken();
        if (initialize[0] == null) {
            return;
        }
        trees = new ArrayList<Node>();
        add(initialize[0], initialize[1], root);
    }

    private void add(String nextData, String identifier, Node startNode) {
        if (!identifier.equalsIgnoreCase("symbol") && !identifier.equalsIgnoreCase("number")
                && !identifier.equalsIgnoreCase("reserved_word") && !identifier.equalsIgnoreCase("word")) {
            //ADD TO SYMBOL TABLE HERE
            symbol_table.put(nextData, null);
        }
        Node currentNode = startNode;
        if (nextData.equals(")")) {
            return;
        }
        //if the next token is a ( then create left node and recurse until end of level
        if (nextData.equals("(")) {
            scopeLevel++;
            //if you encounter a ( in the root node then ignore it
            if (currentNode == root) {
                String[] nextInput = fileScanner.nextToken();
                nextData = nextInput[0];
                add(nextData, nextInput[1], root);
                //add root node to tree
                trees.add(root);
            }
            //if it's not the root node, recurse left
            else{
                currentNode.left = new Node();
                String[] nextInput = fileScanner.nextToken();
                nextData = nextInput[0];
                trees.add(currentNode.left);
                add(nextData, nextInput[1], currentNode.left);
            }
            /*String[] nextInput = fileScanner.nextToken();
             currentNode.left = new Node();
             //if there is no data in the current node
             if (currentNode.left.data == null) {
             add(nextInput[0], nextInput[1], currentNode.left);
             } //if there is already data in the current node, create a right node and put data in it
             else {
             currentNode.right = new Node();
             add(nextData, identifier, currentNode.right);
             }*/
        } //if it's not a left paren then set car left and recurse right
        else {
            currentNode.left = new Node();
            currentNode.left.data = nextData;
            String[] nextInput = fileScanner.nextToken();
            nextData = nextInput[0];
            currentNode.right = new Node();
            add(nextData, nextInput[1], currentNode.right);
        }
    }


    void print(Node n) {
        if (n == root) {
            if(n == null)
            {
                return;
            }
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
