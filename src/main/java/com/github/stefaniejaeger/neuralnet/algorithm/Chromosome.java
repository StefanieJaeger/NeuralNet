/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.stefaniejaeger.neuralnet.algorithm;

import java.util.List;

/**
 *
 * @author jaeger
 */
public class Chromosome {
    public List<Double> dna;
    private int DnaLength;
    
    public Chromosome(List<Double> dna/*, int length*/){
        this.dna = dna;
        //DnaLength = length;
    }
    
    //TODO subscribe to dna change and make sure it doesn't exeed length

    
    @Override
    public String toString(){
        String sDna = "";
        for(Double molecule : dna){
            sDna += ", " + molecule.toString();
        }
        return sDna.replaceFirst(", ", "");
    }
}
