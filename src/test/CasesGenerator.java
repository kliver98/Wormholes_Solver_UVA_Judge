package test;

import java.io.*;
import java.util.*;

/**
 * @author Joe Hernandez - Christian Tamayo- Kliver Daniel Giron
 */
public class CasesGenerator {
	
	public static final int cases = 200;
	public static final int stars = 1000;
	public static final int holes = 2001;
	public static final int wI = -1000;
	public static final int wF = 1001;
	public static Random random;

	public static void Generator() throws IOException {
		random = new Random();
		
		int t = random.nextInt(cases);
		
		StringBuffer sb = new StringBuffer();
		BufferedWriter out = new BufferedWriter(new FileWriter("./documents/CasosPruebas/CasoPrueba"));
		
		sb.append(t+"\n");
		while (t-->0) {
			HashSet<String> map = new HashSet<>();
			
			int s = random.nextInt(stars)+1;
			int h = random.nextInt(holes);
			
			long es = s*(s-1);
			
			if (es<h) 
				while (es<h) 
					h = random.nextInt(holes);
			
			sb.append(s+" "+h+"\n");
			
			for (int i = 0; i < h; i++) {
				int ini = 0;
				int fin = 0;
				int w = wI+random.nextInt((wF)-wI);
				
				while (((ini = random.nextInt(s))!=(fin = random.nextInt(s))) && map.contains(ini+","+fin)) {
					ini = random.nextInt(s);
					fin = random.nextInt(s);
					w = wI+random.nextInt((wF)-wI);
				}
				
				map.add(ini+","+fin);
				
				sb.append(ini+" "+fin+" "+w+"\n");
			}
		}
		
		out.write(sb.toString());
		out.close();
	}
	
	public static void main(String[] args) throws IOException {
		Generator();
		System.out.println("OK");
	}
}
