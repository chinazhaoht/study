package cn.xiaoneng.study.spark;


import cn.xiaoneng.cluster.IClusterListener;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.FlatMapFunction;

import java.util.Arrays;

/**
 * @author zhaoht
 * @date 2016/3/25 11:05
 */

public class SparkTest {
    public static void main(String[] args) {

        String logFile = "YOUR_SPARK_HOME/README.md";
        SparkConf sparkConf = new SparkConf().setAppName("test").setMaster("local");
        JavaSparkContext sc = new JavaSparkContext(sparkConf);
        JavaRDD<String> logData = sc.textFile(logFile).cache();
        JavaRDD<String> testLog ;

        JavaRDD words = logData.flatMap(
                new FlatMapFunction<String,String>() {
                    @Override
                    public Iterable<String> call(String s) throws Exception {

                        return Arrays.asList(s.split(" "));
                    }
                }

        );

    }
}
