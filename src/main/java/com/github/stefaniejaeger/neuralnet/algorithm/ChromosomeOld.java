package com.github.stefaniejaeger.neuralnet.algorithm;

import java.util.List;

/**
 *
 * @author jaeger
 */
public class ChromosomeOld {
    public List<Double> dna;
    
    public ChromosomeOld(List<Double> dna/*, int length*/){
        this.dna = dna;
    }

    @Override
    public String toString() {
        String sDna = "";
        for(Double molecule : dna)
            sDna += ", " + molecule.toString();
        return sDna.replaceFirst(", ", "");
    }
}
