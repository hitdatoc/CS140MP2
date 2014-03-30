import java.io.*;
import java.util.*;

public class Test{

	public static void main(String[] args){
	
		File f = new File(System.getProperty("user.dir"));
		File[] files = f.listFiles();
		
		ArrayList<String> names = new ArrayList<String>();
		
		for(int i = 0 ; i < files.length; i++){
		
			String s = files[i].getName();
			
			
			if(s.matches("(.*)(.png)")){
				System.out.println(s);
			}
		
		}
		
		System.out.println(files[0]);
	
	}}