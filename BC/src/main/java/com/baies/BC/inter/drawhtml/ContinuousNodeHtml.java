package com.baies.BC.inter.drawhtml;
 
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

import edu.gmu.seor.cps2.datastructure.EDBUnit;
 
  

/*
 * 1. Graphical Node
 * The return HTML:  
 *  
	<svg width='200' height='180'  style='border:0px'>
		<text x='150' y='30' fill='royalblue'>M = 10</text>
		<text x='150' y='50' fill='royalblue'>V = 10</text>
		<text x='10' y='180' fill='black'>10</text>
		<text x='180' y='180' fill='black'>10</text>
  		<polygon points='10, 159 ...  239, 159 ' style='fill:none;stroke:royalblue;stroke-width:1' />
	</svg>

 * 2. Textual Node
 * The return HTML:  

	<div style='color:royalblue; font-size:15px '>Mean: 3023.000 </div>
	<div style='color:royalblue; font-size:15px'>Variance: 3023.000 </div>

*/
 
public class ContinuousNodeHtml {
	ArrayList<Double> listMean 	= null;
	ArrayList<Double> listVar 	= null;
	ArrayList<Double> listWeight = null;
	Double gMean = 0.0; 
	Double gVar = 0.0;
	Double gEvidence = 0.0;
	boolean hasEvidence = false;
	Double minRange = 0.0;
	Double maxRange = 0.0;
	Double widthRange = 0.0;
	Double heightRange = 0.0;
	Double width = 200.0;
	Double height = 150.0; 
	Double GAP = 10.0; 
	
	public ContinuousNodeHtml() { 
	}
	
	public String get(EDBUnit node) {
		updateGMMData(node);
		
		String html = "";  
//		html = drawGMM_text(); 
		html = drawGMM();
		
		return html;
	}
	
	public Double getMaxRange() {return maxRange; } 
	public Double getWidthRange() {return widthRange; } 
	public Double getHeightRange() {return heightRange; }
	public Double getWidth() {return width; }
	public Double getHeight() {return height; }
	public Double getHeadHeight() {return 10.0; }
	public String getHtmlText(String s, Double x, Double y, String c) {return "<text font-size='17' x='"+x+"' y='"+y+"' fill='"+c+"' >"+s+"</text>";}
	public String getHtmlText(String s, String c) {return "<div style='color:"+c+"; font-size:15px;'>"+s+"</div>";}
  
	public String drawGMM_text(){
		NumberFormat nf;
		nf = NumberFormat.getInstance(Locale.US);
		nf.setMaximumFractionDigits(2);
		
		String html = "";
		String str;
		//draw mean string
		str = nf.format(gMean);
		html += getHtmlText("Mean = " + str, "royalblue");
				
		//draw STD string
//		str = nf.format(gVar);
//		html += getHtmlText("Variance = " + str, "royalblue");
		
		return html;
	}
	
	public String drawGMM(){
		initGMMRange();  
		
		String html = "<svg font-size='17' width='" + width +"' height='" + height + "'  style='border:0px'>";
		 
		NumberFormat nf;
		nf = NumberFormat.getInstance(Locale.US);
		nf.setMaximumFractionDigits(2);
		String str;
		
		Double width = getWidth()-GAP;
		Double height = (Double) (getHeight() - 40 ); 
		Double scaledH = height/heightRange;
		Double scaledW = widthRange/width; 
		String polyLines = "";
		
		if (!hasEvidence) { 
			//create gmm curves 
			for( Integer X = GAP.intValue(); X < width; X+=2 ){	
				Double y = 0.0;
				Double x = (X-GAP/2+1 )*scaledW  + minRange;
				Integer Y = 0;		
				
				for( int i = 0; i < listMean.size(); i++) {
					Double m = listMean.get(i);                   
					Double v = listVar.get(i);
					Double w = listWeight.get(i);
					//
					//    weight             -(x-m)^2
					//(-------------) * exp(-------------)
					// sqr(2*pi*var)           2*var
					//
					y += scaledH*(w*(1/Math.sqrt(2*Math.PI*v))*Math.exp(-(x-m)*(x-m)/(2*v)));
				}
				 
				Y = (int)(-y + height + getHeadHeight());
								  
				polyLines += X + ", " + Y + " ";
				 
			} 
			 
			str = nf.format(gMean);  
			html += getHtmlText(str, (double)((int)(width/2)-(str.length()*5)/2), height + 16 + getHeadHeight(), "black");
 
			str = nf.format(minRange); 
			html += getHtmlText(str, (double)((int)(width/7)-(str.length()*5)/2), height + 16 + getHeadHeight(), "black");
 
			str = nf.format(maxRange); 
			html += getHtmlText(str, (double)((int)(width*6/7)-(str.length()*5)/2), height + 16 + getHeadHeight(), "black");
  
			//draw mean string
			str = nf.format(gMean);
			html += getHtmlText("M = " + str, (double)(getWidth() - 50), 20.0 + getHeadHeight(), "royalblue");
					
			//draw STD string
			str = nf.format(gVar);
			html += getHtmlText("V = " + str, (double)(getWidth() - 50), 40.0 + getHeadHeight(), "royalblue");
			
		} 
		else if (hasEvidence) {
			Integer X = GAP.intValue();
			Double x = (X-GAP/2+1 )*scaledW  + minRange;
			Integer Y = (int)(height + getHeadHeight());
			Double half = (X-GAP/2+1 ) + width/2;
			
			polyLines += X + ", " + Y+ " " + 
						 half  + ", " + Y+ " "+
						 half  + ", " + getHeadHeight()+ " "+
						 half  + ", " + Y+ " "+
						 width  + ", " + Y+ " ";
			
			str = nf.format(gEvidence); 
			html += getHtmlText(str, (double)(half-(str.length()*5)/2), height + 16 + getHeadHeight(), "black");
		}
		
		html += "<polygon points='" + polyLines + "' style='fill:none;stroke:royalblue;stroke-width:1' />";
		
		return html;
	}
	 
	public void updateGMMData(EDBUnit node){ 
		gEvidence = 0.0;
		hasEvidence = false;
		
		if( listMean == null ){
			listMean = new ArrayList<Double>();
			listVar = new ArrayList<Double>();
			listWeight = new ArrayList<Double>();
		}
		else{
			listMean.clear();
			listVar.clear();
			listWeight.clear();
		} 
		  
		EDBUnit BEL = node.get("BEL");
		EDBUnit PI = node.get("MSG.PI." + node.getName()); 
		EDBUnit EVIDENCE = node.get("EVIDENCE");
//		EDBUnit EVIDENCE2 = EDB.This().get("ROOT.NODES." + node.getName() +".EVIDENCE");
	   
		if( EVIDENCE != null && EVIDENCE.hasChildren() ){
			gEvidence = EVIDENCE.getDataByDouble();
			hasEvidence = true;   
		//	this.addFinding(0); 
		}else
//		if( EVIDENCE2 != null && EVIDENCE2.hasChildren() ){
//			gEvidence = EVIDENCE2.getDataByDouble();
//			hasEvidence = true; 
//		//	this.addFinding(0); 
//		}else
		{
//			this.resetEvidence();
			gEvidence = 0.0;
			hasEvidence = false; 
		}
 
		if( BEL!= null && hasEvidence == false ){
			if (PI == null) {
				gMean = BEL.get("MU").getMatrixData();
				gVar = BEL.get("SIGMA").getMatrixData();
				addGMMData( gMean, gVar, 1.0 );
			} else { 
				if( PI.get("MU").getMatrix().getColumnDimension() == 1){
					if( BEL.get("MU") != null && BEL.get("SIGMA") != null ){
						gMean = BEL.get("MU").getMatrixData();
						gVar = BEL.get("SIGMA").getMatrixData();
						addGMMData( gMean, gVar, 1.0 );
					}else{
						gMean = PI.get("MU").getMatrixData();
						gVar = PI.get("SIGMA").getMatrixData();
						addGMMData( gMean, gVar, 1.0 );
					}
				}
				else{ 
					EDBUnit gm_bel = node.get("GM_BEL");
					if (gm_bel != null ){ 
						for( int i = 0; i < gm_bel.get("MU").getMatrix().getColumnDimension(); i++ ){
							addGMMData( gm_bel.get("MU").getMatrix().get(0,i), gm_bel.get("SIGMA").getMatrix().get(0,i), gm_bel.get("GMMPRIOR").getMatrix().get(0,i));
			 			}
					
						if( BEL.get("MU") != null )	gMean = BEL.get("MU").getMatrixData();
						if( BEL.get("SIGMA") != null )	gVar = BEL.get("SIGMA").getMatrixData();
					} 
					else {
						for( int i = 0; i < PI.get("MU").getMatrix().getColumnDimension(); i++ ){
							addGMMData( PI.get("MU").getMatrix().get(0,i), PI.get("SIGMA").getMatrix().get(0,i), PI.get("GMMPRIOR").getMatrix().get(0,i));
			 			}
					
						if( BEL.get("MU") != null )	gMean = BEL.get("MU").getMatrixData();
						if( BEL.get("SIGMA") != null )	gVar = BEL.get("SIGMA").getMatrixData();
					}
				}
			}
		} 
	}  
	
	public void addGMMData( Double m, Double var, Double w){
		listMean.add(m);
		listVar.add(var);
		listWeight.add(w);
	} 
	  
	public void initGMMRange(){
		//find width 
		if( listMean.size() ==  1 ){ 
			maxRange = gMean + 4*(Math.sqrt(gVar));
			minRange = gMean - 4*(Math.sqrt(gVar));  
		}
		else{ 
			minRange = Double.MAX_VALUE;
			maxRange = Double.MIN_VALUE;
			for( int i = 0; i < listMean.size(); i++) {
				minRange = Math.min( minRange, listMean.get(i)-4 * Math.sqrt(listVar.get(i)) );
				maxRange = Math.max( maxRange, listMean.get(i)+4 * Math.sqrt(listVar.get(i)) ); 
			}
		}
		
		widthRange = maxRange - minRange;
		heightRange = 0.0;
		double g = (maxRange - minRange)/(getWidth()-GAP*2);
		
		for( double x = minRange ; x < maxRange ; x+=g ){
			double y = 0.0; 
			for( int i = 0; i < listMean.size(); i++) {
				Double m = listMean.get(i);
				Double s = listVar.get(i);
				Double w = listWeight.get(i); 
				y += (w*(1/Math.sqrt(2*Math.PI*s))*Math.exp(-(x-m)*(x-m)/(2*s)));
			}
			
			heightRange = Math.max( heightRange, y );
		}  
	} 
}
