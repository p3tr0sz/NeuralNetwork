import java.util.Random;


public class Connection {

	private double weight;
	private double deltaweight;
	private Random generator = new Random();
	
	public Connection(){
		setWeight(generator.nextDouble());
	}
	
	public void setWeight(double w){
		this.weight = w;
	}
	
	public double getWeight(){
		return weight;
	}
	
	public void setDeltaWeight(double dw){
		this.deltaweight = dw;
	}
	
	public double getDeltaWeight(){
		return deltaweight;
	}
}
