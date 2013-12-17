$HADOOP_HOME/bin/hadoop \
	org.apache.giraph.GiraphRunner teppeistudio.SuperSimplePageRank \
	-vif org.apache.giraph.io.formats.JsonLongDoubleFloatDoubleVertexInputFormat \
	-vip /user/hduser/input/tiny_graph2.txt \
	-of org.apache.giraph.io.formats.IdWithValueTextOutputFormat \
	-op /user/hduser/output/supersimple \
	-w 1
