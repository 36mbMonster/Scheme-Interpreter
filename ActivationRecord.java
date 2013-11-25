/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg152parserstuff;

import java.util.TreeMap;

/**
 *
 * @author Steven
 */
public class ActivationRecord {
    int scope;
    TreeMap<String, String> table;
    ActivationRecord prevRecord;
    ActivationRecord(TreeMap<String, String> symbolTable, int scopeLevel)
    {
        table = symbolTable;
        scope = scopeLevel;
        prevRecord = null;
    }
}
