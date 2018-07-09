package com.jpmorgan.instructions.processor;

import com.jpmorgan.instructions.data.DataParser;
import com.jpmorgan.instructions.pojo.Instruction;
import com.jpmorgan.instructions.store.InstructionStore;

/**
 * main class for processing the data instructions into a list of Instructions
 */
public class MessageProcessor {

    DataParser dataParser = new DataParser();

    public void processDataSample(String dataSample){
        InstructionStore instructionStore = InstructionStore.getInstance();

        String[] lines = dataSample.split("\n");

        for (String dataLine: lines) {
            Instruction instruction = processDataLine(dataLine);
            if (instruction != null){
                //System.out.println(instruction);
                instructionStore.add(instruction);
            }
        }
        System.out.println("Finished adding data");
    }

    // THIS IS POINTLESS!!!
    public Instruction processDataLine(String dataLine){
        Instruction instruction = dataParser.parseLine(dataLine);
        return instruction;
    }
}
