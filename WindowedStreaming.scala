package curso_spark.streaming

import org.apache.spark._
import org.apache.spark.streaming._


object WindowedStreamingNetworkWordCount {
	def main(args: Array[String]) {
		// Create a local StreamingContext with two working thread and batch interval of 1 second.
		// The master requires 2 cores to prevent from a starvation scenario.

		val conf = new SparkConf().setMaster("local[*]").setAppName("WindowedStreamingNetworkWordCount")
		val ssc = new StreamingContext(conf, Seconds(1))

		// Create a DStream that will connect to hostname:port, like localhost:9999
		val lines = ssc.socketTextStream("localhost", 9999)

		// Split each line into words
		val words = lines.flatMap(_.split(" "))

		// Count each word in each batch
		val pairs = words.map(word => (word, 1))
		// Generating word counts over the last 30 seconds of data, every 10 seconds
		val windowedWordCounts = pairs.reduceByKeyAndWindow((a:Int,b:Int) => (a + b), Seconds(30), Seconds(10))


		// Print the first ten elements of each RDD generated in this DStream to the console
		windowedWordCounts.print()

		ssc.start()             // Start the computation
		ssc.awaitTermination()  // Wait for the computation to terminate
	}
}