package com.baies.BC.jsons;

import java.util.ArrayList;
import java.util.List;

public class jsonNodes {
	private List<jsonNode> list = new ArrayList<jsonNode>();

	@Override
	public String toString() {
		String s = "";
		for (jsonNode n : list){
			s += n.toString();
			s += "\r\n";
		}
		
		return s;
	} 
}
