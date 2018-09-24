package com.baies.BC.learner;
 
import java.util.HashMap;
import java.util.List;
import java.util.Map;
  
import edu.cmu.tetrad.bayes.BayesIm;
import edu.cmu.tetrad.data.DataSet;
import edu.gmu.seor.cps2.CPSCompilerMain;
import edu.gmu.seor.cps2.datastructure.EDBUnit;
import mebn_ln.core.MFrag_Learning;
import mebn_rm.MEBN.MFrag.MFrag;
import mebn_rm.MEBN.MNode.MCNode;
import mebn_rm.MEBN.MNode.MDNode;
import mebn_rm.MEBN.MNode.MNode;
   

public class SBL_parameter_learner {
    public List<String> IPCs_Data = null;	//	IPC corresponding to columns in csv data (This can not have a redundant name) 
    public DataSet selectedData = null; 
    public DataSet data = null;
    Map<String, BayesIm> ipcIMs = new HashMap<String, BayesIm>();
     
	public SBL_parameter_learner() {		 
	}
	 
	public String run(String title, String sbn, String csv) {
		CPSCompilerMain cpsMain = new CPSCompilerMain(); 
		cpsMain.InitCompiler(); 
		cpsMain.compile(sbn + " run (UJT);");
		
		MFrag mFrag = new MFrag(title);
		
		EDBUnit nodes = cpsMain.edb.get("ROOT.ENGINES.UJT.NODES"); 
		EDBUnit ISA = nodes.getRel("ISA"); 
		for (String str : ISA.getMap().keySet()) {
			EDBUnit node = ISA.map.get(str); 
			EDBUnit type = node.get("INFO.TYPE"); 
			EDBUnit evidence = node.getSub("EVIDENCE"); 
			
			if( evidence == null ){
				if( type.getData().equalsIgnoreCase("Discrete") ){
					MDNode mnode = new MDNode(mFrag, str);
					mnode.columnName = str;
					mFrag.arrayResidentNodes.add(mnode);
					 
				}else
				if( type.getData().equalsIgnoreCase("Continuous") ){ 
					MCNode mnode = new MCNode(mFrag, str);
					mnode.columnName = str;
					mFrag.arrayResidentNodes.add(mnode);
				}
			} 
		}
		
		mFrag.updateCLDs();
		
		for (String str2 : ISA.getMap().keySet()) {
			EDBUnit node = ISA.map.get(str2);  
			EDBUnit parents = node.get("PARENTS"); 
			
			MNode mnode = mFrag.getMNode(str2);
			
			EDBUnit parent = parents.getNext();
			while(parent != null) {
				MNode mparent = mFrag.getMNode(parent.getName());
				mnode.parentMNodes.add(mparent);
				parent = parent.getNext();
			} 
		}   
		
		mFrag.cvsFile = csv;
		
		new MFrag_Learning().run_operation(mFrag);
		 
		String ssbn_next = "";
		
		for (MNode mn : mFrag.getAllNodes()) { 
            String s = mn.getILD();
            ssbn_next += s;
        }
		
		return ssbn_next;
	}
}
