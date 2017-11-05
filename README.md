# spark-streaming

Simple spark streaming examples

####Dependencies

* Spark 2.1.0
* sbt

####Build

	sbt compile; sbt package

####Basic word count

	spark-submit --class=curso_spark.streaming.StreamingNetworkWordCount target/scala-2.11/basicstreaming_2.11-1.0.jar
	
####Windowed word count
	spark-submit --class=curso_spark.streaming.WindowedStreamingNetworkWordCount target/scala-2.11/basicstreaming_2.11-1.0.jar