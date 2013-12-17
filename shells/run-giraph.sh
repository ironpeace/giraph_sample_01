$HADOOP_HOME/bin/hadoop org.apache.giraph.GiraphRunner teppeistudio.SimpleShortestPathsVertex \
	-vif org.apache.giraph.io.formats.JsonLongDoubleFloatDoubleVertexInputFormat \
	-vip /user/hduser/input/tiny_graph1.txt \
	-of org.apache.giraph.io.formats.IdWithValueTextOutputFormat \
	-op /user/hduser/output/shortestpaths1 \
	-w 1
