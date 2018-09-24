package com.baies.BC.jsons;

import java.util.ArrayList;
import java.util.List;

public class jsonNode {
	 public jsonNode(){
		 
	 }
	 
	 public String   symbol = "";
	 public Integer  w = 150;
	 public Integer  h = 100;
	 public String   bonds = "1";
	 public String   id = "";
	 public List<String> states = new ArrayList<String>();
	 public List<Double> probs = new ArrayList<Double>();
	 
	 @Override
		public String toString() {
			String s = "";
			s += " symbol: " + symbol;
			s += " id: " + id; 
			s += " " + states.toString();
			s += " " + probs.toString(); 
			return s;
		}
}
