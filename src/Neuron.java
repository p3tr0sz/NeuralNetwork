import java.util.ArrayList;
import java.util.List;

public class Neuron {

	private double noutputVal;
	private List<Connection> noutputWeights = new ArrayList<Connection>();
	private int myIndex;
	private double m_gradient;
	private static double eta = 0.15;
	private static double alpha = 0.5;
	
	public Neuron(int numOutputs, int my_Index) {
		for (int c = 0; c < numOutputs; c++) {
			noutputWeights.add(new Connection());

		}
		myIndex = my_Index;
	}

	public void setOutputVal(double noV) {
		this.noutputVal = noV;
	}

	public void feedForward(List<Neuron> prevLayer) {

		double sum = 0.0;

		for (int n = 0; n < prevLayer.size(); n++) {
			sum += prevLayer.get(n).getOutputVal()
					* prevLayer.get(n).noutputWeights.get(myIndex).getWeight();
		}
		
		setOutputVal(transferFunction(sum));

	}

	private double transferFunction(double x) {
		return Math.tanh(x);
	}
	
	private double transferFunctionDerivative(double x) {
		
		return 1.0 - x*x;
	}

	public double getOutputVal() {
		return noutputVal;
	}

	public void calcOutputGradients(Double double1) {
		double delta = double1 - noutputVal;
		m_gradient = delta * transferFunctionDerivative(noutputVal);
	}

	public void calcHiddenGradients(List<Neuron> nextLayer) {
		double dow = sumDOW(nextLayer) * transferFunctionDerivative(noutputVal);
	}

	private double sumDOW(List<Neuron> nextLayer) {
		// TODO Auto-generated method stub
		double sum = 0.0;
		
		for(int n = 0; n < nextLayer.size() - 1; n++){
			sum += noutputWeights.get(n).getWeight() * nextLayer.get(n).m_gradient;
		}
		return sum;
	}

	public void updateUnputWeights(List<Neuron> prevLayer) {
	
		for(int n = 0; n < prevLayer.size(); n++){
			Neuron nneuron = prevLayer.get(n);
			
			double oldDeltaWeight = nneuron.noutputWeights.get(myIndex).getDeltaWeight();
			
			double newDeltaWeight = eta * nneuron.getOutputVal()
					*m_gradient
					*alpha
					*oldDeltaWeight;
			
			nneuron.noutputWeights.get(myIndex).setDeltaWeight(newDeltaWeight);
			nneuron.noutputWeights.get(myIndex).setWeight(nneuron.noutputWeights.get(myIndex).getWeight() + newDeltaWeight);
		}
		
	}
	

}
