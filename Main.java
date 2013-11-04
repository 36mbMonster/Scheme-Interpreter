/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


import java.io.File;
import java.io.IOException;

/**
 *
 * @author Steven
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        File input = new File("input.lisp");
        Scanner sc = new Scanner(input);
        StevenBinary listTree = new StevenBinary(sc);
    }
}
