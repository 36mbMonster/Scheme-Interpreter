/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg152parserstuff;

import java.util.ArrayList;
import java.util.Stack;

/**
 *
 * @author Steven
 */
public class Backend {
    Stack<ActivationRecord> runTimeStack = new Stack();
    ArrayList<ActivationRecord> runTimeDisplay = new ArrayList<ActivationRecord>();
    void push(ActivationRecord addedRecord)
    {
        runTimeStack.add(addedRecord);
        if(runTimeDisplay.get(addedRecord.scope) != null)
        {
            addedRecord.prevRecord = runTimeDisplay.get(addedRecord.scope);
            runTimeDisplay.set(addedRecord.scope, addedRecord);
        }
    }
}
