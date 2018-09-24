package com.baies.BC.db;

import com.baies.BC.jsons.jsonBNInfo;
import com.baies.BC.jsons.jsonBNInfoList;
import com.mongodb.MongoClient; 
import com.mongodb.client.MongoDatabase; 
import com.mongodb.client.MongoCollection;
import org.bson.Document;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer; 
 
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.ServerAddress;

import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoCollection;

import org.bson.Document;
import java.util.Arrays;
import com.mongodb.Block;

import com.mongodb.client.MongoCursor;
import static com.mongodb.client.model.Filters.*;
import com.mongodb.client.result.DeleteResult;
import static com.mongodb.client.model.Updates.*;
import com.mongodb.client.result.UpdateResult; 


public class BN_DB {

	public BN_DB() { 
	}
	  
	public static void insert(jsonBNInfo bn) { 
		MongoClient mongoClient = new MongoClient();
		MongoDatabase database = mongoClient.getDatabase("mydb");
		MongoCollection<Document> collection = database.getCollection("test");
		  
		//jsonBNInfoList jsonBNs = new jsonBNInfoList();
		//jsonBNs.list.add(bn); 
		
		 Document doc = bn.getMongDBDoc();
		 System.out.println(doc);
		
		 collection.insertOne(doc);
		  
//		 for (Document myDoc: collection.find()) {
//			 System.out.println(myDoc.toJson());	 
//		 } 
	}
	
	public void dropAllData() { 
		MongoClient mongoClient = new MongoClient();
		MongoDatabase database = mongoClient.getDatabase("mydb");
		MongoCollection<Document> collection = database.getCollection("test");
		
		// Delete All documents from collection using DBCursor
		collection.drop();
	}
	
	public void insert2(jsonBNInfo bn) { 
		MongoClient mongoClient = new MongoClient();
		MongoDatabase database = mongoClient.getDatabase("mydb");
		MongoCollection<Document> collection = database.getCollection("test");
		
		// Delete All documents from collection using DBCursor
		collection.drop();
		
//		jsonBNInfo jsonBN = new jsonBNInfo();
//		jsonBN.authors.put("JP");
//		jsonBN.keywords.put("Weather");
//		
//		jsonBNInfo jsonBN1 = new jsonBNInfo();
//		jsonBN.title = "X Net";
//		jsonBN.authors.put("JP");
//		jsonBN.keywords.put("Weather");
//		jsonBN.script = "defineNode(A_1, DescriptionC0);{defineState(Discrete, a1, a2); p( A_1 ) = { a1: 0.2; a2: 0.8;  }  } 			defineNode(B_1, DescriptionC2);{defineState(Discrete, b1, b2); p( B_1 ) = { b1: 0.6; b2: 0.4;  }  } defineNode(X_1, DescriptionC0);{ defineState(Discrete, x1, x2); p( X_1 | A_1, B_1 ) = if( A_1 == a1 && B_1 == b1 ) { x1: 0.3; x2: 0.7; } else if ( A_1 == a1 && B_1 == b2 ) { x1: 0.1; x2: 0.9; }  else if ( A_1 == a2 && B_1 == b1 ) { x1: 0.5; x2: 0.5; }  else if ( A_1 == a2 && B_1 == b2 ) { x1: 0.8; x2: 0.2; }   } defineNode(Y_1, DescriptionC0);{ defineState(Discrete, y1, y2); p( Y_1 | X_1 ) = if( X_1 == x1) { y1: 0.14; y2: 0.86; } else if ( X_1 == x2 ) { y1: 0.28; y2: 0.72; }  }defineNode(Z_1, DescriptionC0); 	{ defineState(Discrete, z1, z2, z3, z4, z5, z6, z7, z8, z9, z10); p( Z_1 | X_1 ) = if( X_1 == x1) { z1: 0.1; z2: 0.1; z3: 0.1; z4: 0.1; z5: 0.1; z6: 0.1; z7: 0.1; z8: 0.1; z9: 0.1;  z10: 0.1; } else if ( X_1 == x2 ) { z1: 0.58; z2: 0.22; z3: 0.0; z4: 0.0; z5: 0.0; z6: 0.0; z7: 0.0; z8: 0.0; z9: 0.0;  z10: 0.2; }  }";
//		
//		jsonBNInfo jsonBN2 = new jsonBNInfo();
//		jsonBN.title = "Asia Net";
//		jsonBN.authors.put("K");
//		jsonBN.keywords.put("Weather");
//		jsonBN.script = "defineNode(A, VisitToAsia); {  	defineState(Discrete, visit, noVisit);  	p( A ) = { visit:0.01; noVisit:0.99; }   }  	 defineNode(S, Smoking); { 	defineState(Discrete, smoker, nonSmoker); 	p( S ) = { smoker:0.5; nonSmoker:0.5; } } 		defineNode(L, LungCancer);{ 	defineState(Discrete, present, absent); 	p( L | S ) = if( S == smoker ) { present:0.1; absent:0.9; }  	else if( S == nonSmoker ){ present:0.01; absent:0.99; }  } defineNode(T, Tuberculosis);{ 	defineState(Discrete, present,absent); 	p( T | A ) = if( A == visit ){ present:0.05; absent:0.95; }  	else if(  A == noVisit ){ present:0.01; absent:0.99; }  } defineNode(B, Bronchitis);{ 	defineState(Discrete, present,absent); 	p( B | S ) = if( S == smoker ){ present:0.6; absent:0.4; }  	else if(  S == nonSmoker ){ present:0.3; absent:0.7; }  } 	defineNode(E, TuberculosisOrCancer);{ 	defineState(Discrete, true, false); 	p( E | T, L) = if(   T == present &&  L == present ){ true:1.0; false:0.0; }  	else if(   T == present && L == absent ){ true:1.0; false:0.0; } 	else if( T == absent && L == present ){ true:1.0; false:0.0; }  	else if( T == absent && L == absent  ){ true:0.0; false:1.0; }  } 	defineNode(X, XrayResult);		{ defineState(Discrete, abnormal, normal); 	p( X | E ) = if( E == true ){ abnormal:0.98; normal:0.02; }  	else if( E == false ){ abnormal:0.05; normal:0.95; }  }  	 defineNode(D, Dyspnoea);{ defineState(Discrete, present,absent); 	p( D | B, E) = if(  B == present && E == true ){ present:0.9; absent:0.1; }  	else if(  B == present && E == false ){ present:0.8; absent:0.2; }	else if(  B == absent && E == true ){ present:0.7; absent:0.3; }  	else if(  B == absent && E == false  ){ present:0.1; absent:0.9; }  }\r\n" ; 
		 
		jsonBNInfoList jsonBNs = new jsonBNInfoList();
//		jsonBNs.list.add(jsonBN);
//		jsonBNs.list.add(jsonBN1);
//		jsonBNs.list.add(jsonBN2);
		  
		 List<Document> docs = new ArrayList<Document>();

		 for (jsonBNInfo j : jsonBNs.list) {
			 docs.add(j.getMongDBDoc());
		 }
		 
		 collection.insertMany(docs);
		  
//		 for (Document myDoc: collection.find()) {
//			 System.out.println(myDoc.toJson());	 
//		 } 
	}
	
	public void mdb( ) { 
		MongoClient mongoClient = new MongoClient();
		MongoDatabase database = mongoClient.getDatabase("mydb");
		MongoCollection<Document> collection = database.getCollection("test");
		
		 Document doc = new Document("name", "MongoDB")
	                .append("type", "database")
	                .append("count", 1)
	                .append("versions", Arrays.asList("v3.2", "v3.0", "v2.6"))
	                .append("info", new Document("x", 203).append("y", 102));
		 
		 collection.insertOne(doc);
		  
		 for (Document myDoc: collection.find()) {
			 System.out.println(myDoc.toJson());	 
		 } 
	}


	public String search_to_json(String kws) { 
		// Get keywords
		System.out.println("kw:" + kws);
		
		String evidences = "";
		StringTokenizer st = new StringTokenizer(kws,",");  
	    while (st.hasMoreTokens()) {  
	    	String singleEv = st.nextToken(); 
	    }   
	    
		String Gnodes = "";
		String Glinks = "";
//		
//		for (String str : ISA.getMap().keySet()) { 
//			//make Gnodes
//			Gnodes += "{";
//			Gnodes += q + "symbol" + q + ":"  +q + str + q + "," ;
//			Gnodes += q + "w" + q + ":"  +q + "150" + q + "," ;
//			Gnodes += q + "h" + q + ":"  +q + "100" + q + "," ;
//			Gnodes += q + "bonds" + q + ":"  +q + "1" + q + "," ;
//			Gnodes += q + "id" + q + ":"  +q + "n_"+ str + q + "," ;
//			Gnodes += q + "states" + q + ": [ "  + fillStates("", bel.getNext()) + "]," ;
//			Gnodes += q + "probs" + q + ": [ "  + fillProbs("", bel.getNext()) + "]," ;
//			Gnodes += q + "ev" + q + ": "  + q + "null" + q + " " ;
//			Gnodes += "} " ;
//			Gnodes += ",\r\n";
// 
//		}
//		 
//		Gnodes = Gnodes.substring(0,Gnodes.lastIndexOf(',')); 
//		Glinks = Glinks.substring(0, Glinks.lastIndexOf(',')); 
//		
//		String graph = "";            
//		graph += "{";
//		graph += q + "Gnodes" + q + ":["   + Gnodes +  "]," ;
//		graph += "\r\n"; 
//		graph += q + "Glinks" + q + ":["   + Glinks +  "]" ; 
//		graph += "} " ;
//		graph += "\r\n"; 
		
		//System.out.println(graph);
		
		MongoClient mongoClient = new MongoClient();
		MongoDatabase database = mongoClient.getDatabase("mydb");
		MongoCollection<Document> collection = database.getCollection("test");
		 
		String json = "[";
		for (Document myDoc: collection.find()) {
			json += myDoc.toJson() +", "+ "\r\n"; 	 
		}
		
		json = json.substring(0, json.lastIndexOf(','));
		json += "]";
		 
		return json;
	}
	
	public String search_to_text(String kws) { 
		// Get keywords
		System.out.println("kw:" + kws);
		
		StringTokenizer st = new StringTokenizer(kws,",");  
	    while (st.hasMoreTokens()) {  
	    	String singleEv = st.nextToken(); 
	    }   

		MongoClient mongoClient = new MongoClient();
		MongoDatabase database = mongoClient.getDatabase("mydb");
		MongoCollection<Document> collection = database.getCollection("test");
		 
		String ret = "";
		for (Document myDoc: collection.find()) {
			String title = myDoc.getString("title");
			String authors = myDoc.getString("authors");
			String date = myDoc.getString("date");
			String description = myDoc.getString("description");
			
			ret += title + ":" + authors +" "+ date +" "+ description +" " + "\r\n"; 	 
		}
		
//		 Document doc = new Document("id", id)
//                 .append("title", title)
//                 .append("description", description)
//                 .append("reference", reference) 
//                 .append("links", links)
//                 .append("date", date)
//                 .append("script", script)			                 
//                 .append("image", image)
//                 .append("authors", authors)
//                 .append("keywords", keywords);
		 
		return ret;
	}
	
	
	public String get_bn(String name) { 
		// Get keywords
		System.out.println("Get BN Name:" + name);
		
		MongoClient mongoClient = new MongoClient();
		MongoDatabase database = mongoClient.getDatabase("mydb");
		MongoCollection<Document> collection = database.getCollection("test");
		Document myDoc = collection.find(eq("title", name)).first(); 
		String ret = myDoc.getString("script");
		 
		
//		 Document doc = new Document("id", id)
//                 .append("title", title)
//                 .append("description", description)
//                 .append("reference", reference) 
//                 .append("links", links)
//                 .append("date", date)
//                 .append("script", script)			                 
//                 .append("image", image)
//                 .append("authors", authors)
//                 .append("keywords", keywords);
		 
		return ret;
	}
	
	
	
	
	public static void main(String[] args) {
//		new BN_DB().insert2(null); 
	} 
}
