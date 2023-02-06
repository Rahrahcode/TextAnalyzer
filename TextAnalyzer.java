import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.TreeMap;
import java.util.Map.Entry;

public class TextAnalyzer
{
public static void main(String[] args) throws FileNotFoundException {
	//Treemap sorting
	TreeMap<String, Integer> tree = new TreeMap<String, Integer>();
	
	//Read txt file
	Scanner txtFilee = new Scanner (new File("/Users/rahiemhylton/Desktop/textFile.txt"));
	
	while (txtFilee.hasNext()) {
		String word = txtFilee.next();
		if (tree.containsKey(word)) {
			int count = tree.get(word) + 1;
			tree.put(word, count);
		}
		else {
			tree.put(word, 1);
			}
		}
	txtFilee.close();//close file stream
	
	for (Entry<String, Integer> stats : tree.entrySet()) { //set out elements in treemap
		System.out.println(stats);
		
		}
	}
}