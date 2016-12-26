
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.util.GenericOptionsParser;


public class WAna {
	
	public static void main(String[] args) throws Exception {
		Configuration conf = new Configuration();
		String[] ourArgs = new GenericOptionsParser(conf, args).getRemainingArgs();
		Job job = Job.getInstance(conf, "Compteur d'anagrammes v1.0");
		
		job.setJarByClass(WAna.class);
        job.setMapperClass(WAnaMap.class);
        job.setReducerClass(WAnaReduce.class);
        
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(Text.class);
        
        FileInputFormat.addInputPath(job, new Path(ourArgs[0]));
        FileOutputFormat.setOutputPath(job, new Path(ourArgs[1]));

        if (job.waitForCompletion(true))
            System.exit(0);
        System.exit(-1);
        
        
	}
	
}
