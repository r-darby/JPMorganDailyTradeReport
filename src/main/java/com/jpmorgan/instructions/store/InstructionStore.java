package com.jpmorgan.instructions.store;

import com.jpmorgan.instructions.pojo.Instruction;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Singleton class to hold the list of Instruction objects
 * Can only add to the list
 * (or clear it for testing purposes)
 */
public class InstructionStore {

    private static List<Instruction> instructionList = new ArrayList<>();

    private static InstructionStore instance = null;
    protected InstructionStore() {
        // Exists only to defeat instantiation.
    }

    public static InstructionStore getInstance() {
        if(instance == null) {
            instance = new InstructionStore();
        }
        return instance;
    }

    public static boolean add(Instruction instruction){
        if(instruction != null){
            return instructionList.add(instruction);
        }
        return false;
    }

    public static List<Instruction> getInstructionList(){
        return Collections.unmodifiableList(instructionList);
    }

    // for testing
    public static void clearStore(){
        instructionList.clear();
    }
}