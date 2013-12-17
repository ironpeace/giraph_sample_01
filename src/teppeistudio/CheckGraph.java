package teppeistudio;

import org.apache.giraph.edge.Edge;
import org.apache.giraph.graph.Vertex;
import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.FloatWritable;
import org.apache.hadoop.io.LongWritable;

public class CheckGraph extends Vertex<LongWritable, DoubleWritable, FloatWritable, DoubleWritable>{

	@Override
	public void compute(Iterable<DoubleWritable> messages) {
		
		System.out.print(" superstep:" + this.getSuperstep());
		System.out.print(" vertex_id:" + this.getId());
		System.out.print(" vertex_val:" + this.getValue());
		System.out.println(" edgenum:" + this.getNumEdges());
		
		for (Edge<LongWritable, FloatWritable> edge : getEdges()) {
			System.out.print(" edgeTargetId:" + edge.getTargetVertexId());
			System.out.println(" edgeValue:" + edge.getValue());
		}
		this.voteToHalt();
	}

}
