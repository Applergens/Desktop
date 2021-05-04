package util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class RequestUtils {
	
	public static String getJSON(String url1) throws IOException {
		
	    HttpURLConnection con = null;
	    
	    try {
	    	
	        URL url = new URL(url1);
	        
	        con = (HttpURLConnection) url.openConnection();
	        con.setRequestMethod("GET");
	        con.setRequestProperty("Content-length", "0");
	        con.setUseCaches(false);
	        con.setAllowUserInteraction(false);
	        con.connect();
	        
	        int status = con.getResponseCode();
	        
	        BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));
	        StringBuilder sb = new StringBuilder();
	        String line;
	        
	        while ((line = br.readLine()) != null) {
	        	
	        	sb.append(line+"\n");
	        	
	        }
	        
	        br.close();
	        return sb.toString();

	    } finally {
	    	
	       if (con != null) {
	    	   
	    	   con.disconnect();
	       }
	       
	    }
	    
	}
}
