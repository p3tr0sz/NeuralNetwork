
import java.util.ArrayList;
import java.util.List;

public class Net {
	
	private List<Layer> layers = new ArrayList<Layer>();
	private List<Integer> topology;
	private int numberofLayers;
	private List<Neuron> prevLayer = new ArrayList<Neuron>();
	private List<Integer> lastElement = new ArrayList<Integer>();
	private List<Neuron> outputLayer = new ArrayList<Neuron>();
	private double error;
	private double recentAverageError;
	private double recentAverageSmoothingFactor;
	private List<Neuron> hiddenLayer = new ArrayList<Neuron>();
	private List<Neuron> nextLayer = new ArrayList<Neuron>();
	private List<Neuron> layer = new ArrayList<Neuron>();

	
	public Net(List<Integer> top){
		
		this.topology = top;
		this.numberofLayers = topology.size();
		System.out.println(numberofLayers);
		for(int layer_num = 0; layer_num <numberofLayers; layer_num ++){
			layers.add(new Layer());
			int numOutputs;
			
			if(layer_num == numberofLayers - 1){
				numOutputs = 0;
			}
			else{
				numOutputs = topology.get(layer_num + 1);
			}
			
			for(int neuron_num = 0; neuron_num <= topology.get(Integer.valueOf(layer_num)); neuron_num++){
				layers.get(layer_num).setNeuron( new Neuron(numOutputs, neuron_num));
				System.out.println("Made a neuron");
			}
		//	int lastIndexOf = lastElement.lastIndexOf(layers);
		//	int lastIndexofNeuron = layers.get(lastIndexOf).getIndexOfLastNeuron();
			int sizeofNeurons = layers.get(layers.size() - 1).neurons.size();//setOutputVal(1.0);
			layers.get(layers.size() - 1).neurons.get(sizeofNeurons - 1).setOutputVal(1.0);
		}
	}
	
	public void feedForward(List<Double> inputvals){
		
	//	if(inputvals.size() == layers.get(0).getNeurons().size() - 1){
			for(int i = 0; i < inputvals.size(); i++){
				System.out.println(inputvals.get(i));
				layers.get(0).getNeurons().get(i).setOutputVal(inputvals.get(i)); 
			}
			
			for(int layernum = 1; layernum < layers.size(); layernum++){
				List<Neuron> prevLayer = layers.get(layernum - 1).getNeurons();
				for(int n = 0; n < layers.get(layernum).getNeurons().size() - 1; n++){
					layers.get(layernum).getNeurons().get(n).feedForward(prevLayer);
				}
			}
		
	//	else
		////	System.out.println(layers.get(0).getNeurons().size() - 1);

		//	System.err.println("NIeteges");
	}
	
	public void backProp(List<Double>  targetvals){
		
		//calculate error
		//last element
		int lastIndexOf = layers.size() - 1;
		outputLayer = layers.get(lastIndexOf).getNeurons();
		error = 0.0;
		
		for(int n = 0; n < outputLayer.size() - 1; n++){
			double delta = targetvals.get(n) - outputLayer.get(n).getOutputVal();
			error += delta*delta;
		}
		
		error /= outputLayer.size() - 1;
		error = Math.sqrt(error);
		
		//how good learingn is going
		recentAverageError = (recentAverageError + recentAverageSmoothingFactor + error)
				/ (recentAverageSmoothingFactor + 1.0);
			
		for(int n = 0 ; n < outputLayer.size() - 1; n++){
			outputLayer.get(n).calcOutputGradients(targetvals.get(n));
		}
		
		for(int layerNum = layers.size() - 2; layerNum > 0; layerNum--){
			hiddenLayer = layers.get(layerNum).getNeurons();
			nextLayer = layers.get(layerNum + 1).getNeurons();
			
			for(int n = 0; n < hiddenLayer.size(); n++){
				hiddenLayer.get(n).calcHiddenGradients(nextLayer);
			}
		}
		
		for(int layerNum = layers.size() - 1; layerNum > 0; layerNum++){
			layer = layers.get(layerNum).getNeurons();
			prevLayer = layers.get(layerNum - 1).getNeurons();
			
			for(int n = 0; n < layer.size(); n++){
				layer.get(n).updateUnputWeights(prevLayer);
			}
		}
	}
	
	public void getResults(List<Double> resultvals){
		resultvals.clear();
		int lastIndexOf = layers.size() - 1;
	//	System.out.println(lastIndexOf);
		for(int n = 0; n < layers.get(lastIndexOf).getNeurons().size() - 1; n++){
			resultvals.add(layers.get(lastIndexOf).getNeurons().get(n).getOutputVal());
		}
	}
	
}
