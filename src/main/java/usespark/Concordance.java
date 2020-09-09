package usespark;

import org.apache.spark.sql.SparkSession;
import scala.Tuple2;

import java.util.regex.Pattern;

public class Concordance {
    public static void main(String[] args) {
        final Pattern WORD_BOUNDARY = Pattern.compile("\\W+");

        SparkSession.builder().appName("Concordance").master("local").getOrCreate()
                .sparkContext()
                .textFile("PrideAndPrejudice.txt", 1)
                .toJavaRDD()
                .map(String::toLowerCase)
                .flatMap(l -> WORD_BOUNDARY.splitAsStream(l).iterator())
                .filter(w -> !w.isEmpty())
                .mapToPair(w -> new Tuple2<>(w, 1))
                .aggregateByKey(0, (v1, v2) -> v1 + v2, (v1, v2) -> v1 + v2)
                .mapToPair(t -> new Tuple2<>(t._2, t._1))
                .sortByKey(false)
                .map(t -> String.format("%20s : %d", t._2, t._1))
                .take(200)
                .forEach(System.out::println);
    }
}
