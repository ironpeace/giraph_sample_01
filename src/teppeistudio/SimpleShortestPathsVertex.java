package teppeistudio;

import org.apache.giraph.Algorithm;
import org.apache.giraph.conf.LongConfOption;
import org.apache.giraph.edge.Edge;
import org.apache.giraph.graph.Vertex;
import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.FloatWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.log4j.Logger;

@Algorithm(
    name = "Shortest paths",
    description = "Finds all shortest paths from a selected vertex"
)
public class SimpleShortestPathsVertex extends
    Vertex<LongWritable, DoubleWritable, FloatWritable, DoubleWritable> {

	public static final LongConfOption SOURCE_ID = new LongConfOption("SimpleShortestPathsVertex.sourceId", 0);
	//private static final Logger LOG = Logger.getLogger(SimpleShortestPathsVertex.class);

	private boolean isSource() {
		return getId().get() == SOURCE_ID.get(getConf());
	}

	@Override
	public void compute(Iterable<DoubleWritable> messages) {
		if (getSuperstep() == 0) {
			setValue(new DoubleWritable(Double.MAX_VALUE));
		}
		double minDist = isSource() ? 0d : Double.MAX_VALUE;
		for (DoubleWritable message : messages) {
			minDist = Math.min(minDist, message.get());
		}

		System.out.println("superstep " + getSuperstep() + " Vertex " + getId() + " got minDist = " + minDist + " vertex value = " + getValue());
		
		if (minDist < getValue().get()) {
			setValue(new DoubleWritable(minDist));
			for (Edge<LongWritable, FloatWritable> edge : getEdges()) {
				double distance = minDist + edge.getValue().get();
				System.out.println("superstep " + getSuperstep() + "Vertex " + getId() + " sent to " + edge.getTargetVertexId() + " = " + distance);
				sendMessage(edge.getTargetVertexId(), new DoubleWritable(distance));
			}
		}
	    voteToHalt();
	}
}
