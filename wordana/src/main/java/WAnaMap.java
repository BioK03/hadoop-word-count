import java.io.IOException;
import java.util.Arrays;
import java.util.StringTokenizer;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;


public class WAnaMap extends Mapper<Object, Text, Text, Text> {
	
	protected void map(Object key, Text value, Context context) throws IOException, InterruptedException {

        // Un StringTokenizer va nous permettre de parcourir chacun des mots de la ligne qui est passée
        // à notre opération MAP.
        StringTokenizer tok = new StringTokenizer(value.toString(), "\n");
        while (tok.hasMoreTokens()) {
            Text word = new Text(tok.nextToken());
            // On renvoie notre couple (clef;valeur): le mot courant suivi de la valeur 1 (définie dans la constante ONE).
            context.write(sortLetters(word), word);
        }
    }
	
	protected Text sortLetters(Text input) {
		String sinput = input.toString();
		int[] soutput = new int[sinput.length()];
		
		for (int i=0; i<sinput.length(); i++){
			soutput[i] = (int) sinput.charAt(i);
		}
		
		Arrays.sort(soutput);
		
		String output = "";
		
		for (int i=0; i<soutput.length; i++){
			output += (char)soutput[i];
		}
		
		return new Text(output);
	}
	
}
