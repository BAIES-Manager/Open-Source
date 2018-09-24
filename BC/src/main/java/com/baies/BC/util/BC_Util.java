package com.baies.BC.util;

import java.util.StringTokenizer;

public class BC_Util {

	public BC_Util() { 
	}
	
	public String getSBNdefineEvidence(String ev) {
		String evidence = "";
		StringTokenizer st = new StringTokenizer(ev,";");  
	    while (st.hasMoreTokens()) {  
	    	String singleEv = st.nextToken();
	    	
	    	StringTokenizer st2 = new StringTokenizer(singleEv,"=");
	    	if (st2 == null || !st2.hasMoreElements())
	    		break;
	    	
	    	String node = st2.nextToken();
	    	
	    	if (!st2.hasMoreElements())
	    		break;
	    	
	    	String value = st2.nextToken();
	    	
	    	evidence += "defineEvidence( " + node + "," + value + "); ";
	    }   
	    return evidence;
	}

	public String removeLastString(String str, String finding) {
		int last = str.lastIndexOf(finding);
		str = str.substring(0, last);		
	    return str;
	}

}
