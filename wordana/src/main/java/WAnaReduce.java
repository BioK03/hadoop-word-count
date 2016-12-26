import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;


public class WAnaReduce extends Reducer<Text, Text, Text, Text> {
	
	public void reduce(Text key, Iterable<Text> values, Context context) throws IOException, InterruptedException {
        Iterator<Text> i = values.iterator();
        
        ArrayList<Text> texts = new ArrayList();
        String occ = "";
        while (i.hasNext()){
        	Text temp = i.next();
        	texts.add(temp);
        	occ += " "+temp;
        }
        	
        context.write(key, new Text("Occurences : "+occ));
    }
}
