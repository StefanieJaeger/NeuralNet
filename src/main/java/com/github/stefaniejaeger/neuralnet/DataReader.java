/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.stefaniejaeger.neuralnet;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author jaeger
 */
public class DataReader {
    private String filePath;
    private List<List<Double>> trainingData;
    private List<List<Integer>> expectedResults;
    
    public DataReader(String path) {
        this.filePath = path;
        trainingData = new ArrayList<>();
        //first part in file is always training data, so we prepare
        trainingData.add(new ArrayList<>());
        expectedResults = new ArrayList<>();
    }
    
    public List<Test> getTestData() {
        readFileContent();
        List<Test> tests = new ArrayList<>();
        for(int i = 0; i < expectedResults.size(); i++) {
            tests.add(new Test(trainingData.get(i), expectedResults.get(i)));
            //System.out.println(new Test(trainingData.get(i), expectedResults.get(i)).toString());
        }
        return tests;
    }
    
    public List<List<Double>> getTrainingData() {
        readFileContent();
        return trainingData;
    }
    
    public List<List<Integer>> getExpectedResults() {
        readFileContent();
        return expectedResults;
    }
    
    private void readFileContent() {
        try{
            FileReader file = new FileReader(filePath);
            BufferedReader bufferedReader = new BufferedReader(file);
            String line;
            boolean isTrainingData = true;
            while ((line = bufferedReader.readLine()) != null) {
                if (line.length() == 0) {
                    isTrainingData = !isTrainingData;
                    if (isTrainingData) {
                        trainingData.add(new ArrayList<>());
                    } else {
                        expectedResults.add(new ArrayList<>());
                    }
                    continue;
                }
                
                if (isTrainingData) {
                    trainingData.get(trainingData.size()-1).addAll(getDataItemsFromLine(line));
                } else {
                    expectedResults.get(expectedResults.size()-1).addAll(getResultItemsFromLine(line));
                }
            }
        } catch(IOException ioEx){
            
        }     
    }
    
    private List<Double> getDataItemsFromLine(String line) {
        List<String> items = Arrays.asList(line.split(","));
        List<Double> dataItems = new ArrayList<>();
        for (String item : items) {
            dataItems.add(Double.parseDouble(item));
        }
        return dataItems;
    }
    
    private List<Integer> getResultItemsFromLine(String line) {
        List<String> items = Arrays.asList(line.split(","));
        List<Integer> resultItems = new ArrayList<>();
        for (String item : items) {
            resultItems.add(Integer.parseInt(item));
        }
        return resultItems;        
    }
}
