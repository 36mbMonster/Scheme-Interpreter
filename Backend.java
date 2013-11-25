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
    static Stack<ActivationRecord> runTimeStack = new Stack();
    static ArrayList<ActivationRecord> runTimeDisplay = new ArrayList<ActivationRecord>();
    static void push(ActivationRecord addedRecord)
    {
        runTimeStack.add(addedRecord);
        //if there's already a record of this scope level
        if(runTimeDisplay.get(addedRecord.scope) != null)
        {
            //set the previous of this record to the one that the run time display is pointing to
            addedRecord.prevRecord = runTimeDisplay.get(addedRecord.scope);
            //change the display pointer to this one
            runTimeDisplay.set(addedRecord.scope, addedRecord);
        }
        //if there's no record currently of this scope, display of this scope points to this record
        else
        {
            runTimeDisplay.add(addedRecord);
        }
    }
    //use the interpreter to execute each activation record in order?
    void execute()
    {
        
    }
}
