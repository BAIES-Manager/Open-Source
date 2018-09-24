package com.baies.BC.jsons;

import java.util.ArrayList;
import java.util.List;

public class jsonEvs {

	class jsonEv{
		public String node ="";
		public String state ="";
		public Double prob = 0.0;
		public jsonEv() {
			
		}
		
		@Override
		public String toString() {
			String s = "";
			s += " node: :" + node; 
			s += " state: :" + state;
			s += " prob: :" + prob;
			
			return s;
		}
	}
	
	public List<jsonEv> list = new ArrayList<jsonEv>();
	
	public jsonEvs() { 
	} 
	
	public void addEv(String n, String s, Double p){
		jsonEv ev = new jsonEv();
		ev.node = n; ev.state = s; ev.prob = p;
		list.add(ev);
	}
	
	
	@Override
	public String toString() {
		String s = "";
		for (jsonEv n : list){
			s += n.toString();
			s += "\r\n";
		}
		
		return s;
	}
}
