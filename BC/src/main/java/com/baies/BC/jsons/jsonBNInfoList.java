package com.baies.BC.jsons;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.bson.Document;

public class jsonBNInfoList {
	 public String list_id = "id1";
	 
	 public List<jsonBNInfo> list = new ArrayList<jsonBNInfo>();

	 public Document getMongDBDoc(){ 
		 Document doc = new Document("list_id", list_id);
		 List<Document> docs = new ArrayList<Document>();

		 for (jsonBNInfo j : list) {
			 docs.add(j.getMongDBDoc());
		 }
		 
		 return doc;
	 } 
	 
	 
	@Override
	public String toString() {
		String s = "list_id " + list_id;
		s += "\r\n";
		for (jsonBNInfo n : list){
			s += n.toString();
			s += "\r\n";
		}
		
		return s;
	} 
}
