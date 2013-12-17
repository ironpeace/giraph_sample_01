package teppeistudio;

import org.apache.giraph.graph.Vertex;
import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.FloatWritable;
import org.apache.hadoop.io.LongWritable;

public class Simplest extends Vertex<LongWritable, DoubleWritable, FloatWritable, DoubleWritable>{

	@Override
	public void compute(Iterable<DoubleWritable> messages) {
		if(this.getSuperstep() == 0){
			System.out.println("******************** 1st superstep :: value : " + this.getValue().toString());
			this.sendMessageToAllEdges(this.getValue());
		}else{
			System.out.println("******************** " + this.getSuperstep() + "th superstep :: value : " + this.getValue().toString());
			for (DoubleWritable message : messages) {
				System.out.println("******************** message : " + message.get());
			}
			this.voteToHalt();
		}
	}
}
