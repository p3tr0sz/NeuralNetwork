import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Main {

	public static void main(String[] args) throws Exception{
		// TODO Auto-generated method stub

		List<Integer> topology = new ArrayList<Integer>();
		List<Double> targetvals = new ArrayList<Double>();
		List<Double> inputvals = new ArrayList<Double>();
		List<Double> resultvals = new ArrayList<Double>();

		int linecounter = 1;
				
			//read topology from a file
			InputStream is = InputStream.class.getResourceAsStream("/testdata/XOR.txt");
			Scanner filescanner = new Scanner(is);
			while(filescanner.hasNextLine()){
			
				String line = filescanner.nextLine();
				Scanner linescanner = new Scanner(line);
				linescanner.useDelimiter("\t");
				
				if(linecounter == 1){

				while(linescanner.hasNext()){
					topology.add(Integer.valueOf(linescanner.next()));
				}
				}
				else if((linecounter != 1) && (linecounter % 2 == 0)){
					while(linescanner.hasNext()){
						inputvals.add(Double.valueOf(linescanner.next()));
					}
				}
				else if((linecounter != 1) && (linecounter % 2 != 0)){
					while(linescanner.hasNext()){
						targetvals.add(Double.valueOf(linescanner.next()));
					}
				}
				
			linecounter++;
			linescanner.close();
			}
			
		filescanner.close();
		is.close();
		System.out.println(topology.toString() + "\n" + inputvals.toString() + "\n" + targetvals.toString() + "\n");
		
		Net myNet = new Net(topology);
		myNet.feedForward(inputvals);
		myNet.getResults(resultvals);
		System.out.println(resultvals.toString() + "\n");
		System.out.println(targetvals.toString() + "\n");


	}

}
