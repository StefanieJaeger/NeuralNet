/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package neuralnet;

import java.util.List;

/**
 *
 * @author Stefanie
 */
public class OutputNeuron extends Neuron {
    private List<Connection> incommingConnections;
    
    public OutputNeuron(){
    }
    
    public void addConnections(List<Connection> connections){
        this.incommingConnections = connections;
    
    }
}
