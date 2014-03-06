
import java.util.ArrayList;
import java.util.List;

public class Layer {
	private int layerNumber;
	public List<Neuron> neurons = new ArrayList<Neuron>();
	//private Neuron LastNeuron = neurons.get(neurons.size() - 1);
	private int IndexOfLastNeuron = neurons.size() - 1;
	
	public Layer(){
		
	}
	
	public int getLayerNumber(){
		return this.layerNumber;
	}
	
	public void setLayerNumber(int number){
		this.layerNumber = number;
	}
	
	public void setNeuron(Neuron neuron){
		this.neurons.add(neuron);
	}
	
	public List<Neuron> getNeurons(){
		return neurons;
	}
	
	//public Neuron getLastNeuron(){
	//	return this.LastNeuron;
	//}
	
	public int getIndexOfLastNeuron(){
		return this.IndexOfLastNeuron;
	}
}
