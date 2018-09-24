package com.baies.BC.inter;
  
import com.baies.BC.inter.drawhtml.ContinuousNodeHtml;
import com.baies.BC.inter.drawhtml.DiscreteNodeHtml; 
import com.baies.BC.util.BC_Util;

import edu.gmu.seor.cps2.CPSCompilerMain;
import edu.gmu.seor.cps2.datastructure.EDBUnit;
 

public class CPS_Reasoner {
	public CPS_Reasoner() { 
	}
	
	public String compile_to_BN_HTML(String sbn, String evs) {
		CPSCompilerMain cpsMain = new CPSCompilerMain(); 
		cpsMain.InitCompiler(); 
		//System.out.println(sbn);
		cpsMain.compile(sbn + evs +" run ( ANY);");
		 
		EDBUnit nodes = cpsMain.edb.get("ROOT.ENGINES." + cpsMain.selectedEngine + ".NODES");
		EDBUnit ISA = nodes.getRel("ISA");

		String Gnodes = "";
		String Glinks = "";
		
		ContinuousNodeHtml cHtml = new ContinuousNodeHtml();
		DiscreteNodeHtml dHtml = new DiscreteNodeHtml();
		 
		for (String str : ISA.getMap().keySet()) {
			EDBUnit node = ISA.map.get(str); 
			EDBUnit type = node.get("INFO.TYPE"); 
			EDBUnit evidence = node.getSub("EVIDENCE");
			EDBUnit parents = node.get("PARENTS");
			EDBUnit children = node.get("CHILDREN");
			EDBUnit bel = node.getCopied("BEL");  
			EDBUnit states = node.getCopied("STATES"); 
			 
			//make Gnodes 
			if (type.getData().equalsIgnoreCase("Discrete")) {
				if (evidence != null && !evidence.getName().isEmpty()){
					Gnodes += "\r\n";
					Gnodes += str + "\r\n";
					Gnodes += "[labelType=\"html\"\r\n" + 
							  "		label=\"\r\n" + 
							  "		<div style='width:200px;height:20px; solid #000; border-bottom: 1px solid gray; text-align:center; overflow: hidden; font-weight: bold;'>\r\n";
					Gnodes += "<font color = 'black'>" + str + "</div>\r\n";
				 
					Gnodes += dHtml.fillDiscreteProbs_for_EV("", evidence.getName(), states.getNext());
					  
					Gnodes += "\" style=\"fill: #cce6ff; font-weight: bold\"];"; 
				} else {
					Gnodes += "\r\n";
					Gnodes += str + "\r\n";
					Gnodes += "[labelType=\"html\"\r\n" + 
							  "		label=\"\r\n" + 
							  "		<div style='width:200px;height:20px; solid #000; border-bottom: 1px solid gray; text-align:center; overflow: hidden; font-weight: bold;'>\r\n";
					Gnodes += "<font color = 'black'>" + str + "</div>\r\n";
				
					int high = dHtml.findHighestState(0, 0, 0.0, bel.getNext());
					
					Gnodes += dHtml.fillDiscreteStates(0, high, "", bel.getNext()); 
					
					Gnodes += "\" style=\"fill: #ffffff; font-weight: bold\"];"; 		
				}
			} else if (type.getData().equalsIgnoreCase("Continuous")) {
				if (evidence != null && !evidence.getName().isEmpty()){
					Gnodes += "\r\n";
					Gnodes += str + "\r\n";
					Gnodes += "[labelType=\"html\"\r\n" + 
							  "		label=\"\r\n" + 
							  "		<div style='width:200px;height:20px; solid #000; border-bottom: 1px solid gray; text-align:center; overflow: hidden; font-weight: bold;'>\r\n";
					Gnodes += "<font color = 'black'>" + str + "</div>\r\n";
				 
					Gnodes += cHtml.get(node);
					
					Gnodes += "\" style=\"fill: #ffffff; font-weight: bold\"];"; 	 
				} else {
					Gnodes += "\r\n";
					Gnodes += str + "\r\n";
					Gnodes += "[labelType=\"html\"\r\n" + 
							  "		label=\"\r\n" + 
							  "		<div style='width:200px;height:20px; solid #000; border-bottom: 1px solid gray; text-align:center; overflow: hidden; font-weight: bold;'>\r\n";
					Gnodes += "<font color = 'black'>" + str + "</div>\r\n";
				 
					Gnodes += cHtml.get(node);
					
					Gnodes += "\" style=\"fill: #ffffff; font-weight: bold\"];"; 		
				}
			}

			//make Glinks
			String link = makeGlinks("", str, children.getNext());
			if (!link.isEmpty()){
				Glinks += link;
				Glinks += "\r\n";
			}
		}
		  
		String graph = "digraph {" + "\r\n";         
		graph += "node [rx=5 ry=5 labelStyle=\"font: 300 14px 'Helvetica Neue', Helvetica\"]" + "\r\n";
		graph += "edge [labelStyle=\"font: 300 14px 'Helvetica Neue', Helvetica\"] " + "\r\n";
		graph += Gnodes + "\r\n";
		graph += Glinks + "\r\n"; 
		graph += "} " ; 
		
		return graph;
	}
	 
	public String compile_to_BN_Text(String sbn, String evs) {
		CPSCompilerMain cpsMain = new CPSCompilerMain(); 
		cpsMain.InitCompiler(); 
		cpsMain.compile(sbn + evs +" run (ANY);");
		
		EDBUnit nodes = cpsMain.edb.get("ROOT.ENGINES." + cpsMain.selectedEngine + ".NODES");
		EDBUnit ISA = nodes.getRel("ISA");

		String ret = "";
		for( String str : ISA.getMap().keySet() ){ 
			EDBUnit node = ISA.map.get(str);
			EDBUnit type = node.get("INFO.TYPE");  
			EDBUnit evidence = node.get("EVIDENCE"); 
			EDBUnit cpt = node.getCopied("CPT"); 
			EDBUnit bel = node.get("BEL");
			 
			ret += str + ":" + "\r\n";
			if( evidence != null ){
				ret += evidence.printOnAString("         ")+ "\r\n";
			}else
			if( bel != null ){
				ret += bel.printOnAString("         ")+ "\r\n"; 
			} 
		}
		 
		return ret;
	}
	
	public String compile_to_BN_Json(String sbn, String evs) {
		CPSCompilerMain cpsMain = new CPSCompilerMain(); 
		cpsMain.InitCompiler();  
		cpsMain.compile(sbn + evs +" run (ANY);");
		 
		EDBUnit nodes = cpsMain.edb.get("ROOT.ENGINES." + cpsMain.selectedEngine + ".NODES");
		EDBUnit ISA = nodes.getRel("ISA");

		//
		//	[ 
		//	  {"Name" : "A", "type":"bel_dis", "Param": {"a1":"0.6", "a2":"0.4"}},
		//	  {"Name" : "B", "type":"ev_dis", "Param": {"Evidence":"a1"}},
		//	  {"Name" : "C", "type":"bel_con", "Param": {"Mu":"1", "Sigma":"10"}}, 
		//	  {"Name" : "D", "type":"ev_con", "Param": {"Evidence":"1"}}
		//  ]
		String ret = "[" +  "\r\n";
		for( String str : ISA.getMap().keySet() ){ 
			EDBUnit node = ISA.map.get(str);
			EDBUnit type = node.get("INFO.TYPE");  
			EDBUnit evidence = node.get("EVIDENCE"); 
			EDBUnit cpt = node.getCopied("CPT"); 
			EDBUnit bel = node.get("BEL");
			String node_type = "";
			String param = ""; 
			
			ret += "{\"Name\":" + "\"" + str + "\"" + ", ";
			 
			if (type.getData().equalsIgnoreCase("Discrete")) {
				if (evidence != null && !evidence.getName().isEmpty()){
					node_type = "ev_dis";	
					
					param = "\"Evidence\":\"" + evidence.getData() + "\""; 
				} else {
					node_type = "bel_dis";
										
					EDBUnit pa = bel.getNext();
					while(pa != null) {
						param += "\"" + pa.getName() + "\":";
						param += "\"" + pa.getDataByDouble()+ "\",";
						pa = pa.getNext();
					}	
					
					param = new BC_Util().removeLastString(param, ",");
					
				}
			} else if (type.getData().equalsIgnoreCase("Continuous")) {
				if (evidence != null && !evidence.getName().isEmpty()){
					node_type = "ev_con";	
					
					param = "\"Evidence\":\"" + evidence.getDataByDouble() + "\""; 
					
				} else {
					node_type = "bel_con";
					
					param = "\"Mean\":\"" + bel.get("MU").getMatrixData() + "\", ";
					param+= "\"Variance\":\"" + bel.get("SIGMA").getMatrixData() + "\""; 
				}
			} 
			
			ret += "\"type\":\""+ node_type +"\", ";
			ret += "\"Param\":" + "{" + param + "}},"; 				  
			ret += "\r\n";
		}
		
		ret = new BC_Util().removeLastString(ret, ",") + "\r\n";
		
		ret += "]";
		 
		return ret;
	}
	 
	public String makeGlinks(String links, String node, EDBUnit childrenNext) { 
		if (childrenNext == null){
			if (links.isEmpty()) 
				return "";
			return links;
		}
		
		links += node + "->" + childrenNext.getName() + ";";
		
		return makeGlinks(links, node, childrenNext.getNext());
	} 
	   
	public static void main(String[] args) {
		CPS_Reasoner test = new CPS_Reasoner();   
	} 
}
