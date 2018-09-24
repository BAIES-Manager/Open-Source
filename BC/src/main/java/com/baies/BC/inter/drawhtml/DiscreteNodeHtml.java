package com.baies.BC.inter.drawhtml;
 
import java.text.DecimalFormat;

import edu.gmu.seor.cps2.datastructure.EDBUnit;
 
 
public class DiscreteNodeHtml { 
	
	public DiscreteNodeHtml() { 
	}
	
	public String fillDiscreteProbs_for_EV(String states, String ev, EDBUnit belNext) { 
		if (belNext == null){ 
			return states;
		}
		
		String stag = " 		<div style=' solid #000; width:200px; margin: 0px;  '>\r\n" + 
				  "				<div style=' solid #000; width:90px;  margin: 4px;  overflow: hidden; align=right; float:left; '>\r\n";
		
		states += stag + belNext.getName() + "</div> \r\n";
		 
		
		if (belNext.getName().equalsIgnoreCase(ev)){
			Double prob = 1.0;
			String ptag = "		<div style=' solid #000; background:linear-gradient(to right, #b3d9ff, #3399ff); "
						+ "width:"+ (prob * 90) +"px; margin: 4px;  "
						//+ "text-shadow: 1px 1px white; "
						+ "float:left; '>";

			DecimalFormat df = new DecimalFormat("##0");
			
			String strProb = df.format(prob * 100); 
			states += ptag + "&nbsp;" + strProb + "%" + "</div> \r\n";

		} else {  
			String ptag = "		<div style=' solid #000; background:linear-gradient(to right, #b3d9ff, #3399ff); "
						+ "width:0px; margin: 4px;  "
						//+ "text-shadow: 1px 1px white; "
						+ "float:left; '>";
			
			states += ptag + "&nbsp;" + "0%"+ "</div> \r\n";
		}
		
		states += "</div> \r\n";
		 		
		return fillDiscreteProbs_for_EV(states, ev, belNext.getNext());
	}
	 
	public int findHighestState(int index, int highestIndex, Double preProb, EDBUnit belNext) { 
		if (belNext == null){ 
			return highestIndex;
		} 
		
		Double prob = belNext.getDataByDouble();
		
		if (prob > preProb) {
			highestIndex = index;
			preProb = prob;
		} 
		 
		return findHighestState(++index, highestIndex, preProb, belNext.getNext());
	}
	
	public String fillDiscreteStates(int index, int highestIndex, String states, EDBUnit belNext) { 
		if (belNext == null){
			//System.out.println(states); 
			//states = states.substring(0, states.lastIndexOf(','));
			return states;
		}
		
		String stag = " 		<div style=' solid #000; width:200px; margin: 0px;  '>\r\n" + 
					  "				<div style=' solid #000; width:90px; margin: 4px;  overflow: hidden; align=right; float:left; '>\r\n";
		
		states += stag + belNext.getName() + "</div> \r\n";
		
		Double prob = belNext.getDataByDouble();
		
		String color = "#5cb85c , #5cb85c";
		
		if (highestIndex == index) {
			color = "#f0ad4e , #f0ad4e";
		}
		
		String ptag = "		<div style=' solid #000; background:linear-gradient(to right, " + color +"); "
							+ "width:"+ (prob * 90) +"px; margin: 4px;  "
									//+ "text-shadow: 1px 1px white; "
									+ "float:left; '>";
		
		DecimalFormat df = new DecimalFormat("##0.00");
		
		String strProb = df.format(prob * 100); //TempMathFunctions.safeDoubleAsString(prob * 100);
		states += ptag + "&nbsp;" + strProb + "%" + "</div> \r\n";
		states += "</div> \r\n";
		 
		return fillDiscreteStates(++index, highestIndex, states, belNext.getNext());
	}
	
	public String getColor(int i) {
		// 85% 	60%
//		int color = i%4;
//		if (color == 0) { return "#5bc0de , #5bc0de";} else
//		if (color == 1) { return "#5cb85c , #5cb85c";} else
//		if (color == 2) { return "#f0ad4e , #f0ad4e";} else
//		if (color == 3) { return "#d9534f , #d9534f";}   
		
		return "#cce0ff, #80b3ff";
	} 
}
