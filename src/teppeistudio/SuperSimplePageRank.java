package teppeistudio;

import org.apache.giraph.edge.Edge;
import org.apache.giraph.graph.Vertex;
import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.FloatWritable;
import org.apache.hadoop.io.LongWritable;

public class SuperSimplePageRank extends Vertex<LongWritable, DoubleWritable, FloatWritable, DoubleWritable>{

	@Override
	public void compute(Iterable<DoubleWritable> messages) {
		
		System.out.print(" superstep:" + this.getSuperstep());
		System.out.print(" vertex_id:" + this.getId());
		System.out.print(" vertex_val:" + this.getValue());
		System.out.println(" edgenum:" + this.getNumEdges());
		
		double sumEdge = 0;
		for (Edge<LongWritable, FloatWritable> edge : getEdges()) {
//			System.out.print(" edgeTargetId:" + edge.getTargetVertexId());
//			System.out.println(" edgeValue:" + edge.getValue());
			sumEdge += edge.getValue().get();
		}
		
		if(this.getSuperstep() > 0){
			double sum = 0.0;
			for(DoubleWritable msg : messages){
				sum += msg.get();
			}
			this.setValue(new DoubleWritable(sum));
		}

		if(this.getSuperstep() < 20){
//			this.sendMessageToAllEdges(new DoubleWritable(this.getValue().get() / this.getNumEdges()));
			for (Edge<LongWritable, FloatWritable> edge : getEdges()) {
				double msgdbl = this.getValue().get() * (edge.getValue().get() / sumEdge);
				DoubleWritable msg = new DoubleWritable(msgdbl);
				this.sendMessage(edge.getTargetVertexId(), msg);				
			}
		}else{
			this.voteToHalt();
		}
	}

}
