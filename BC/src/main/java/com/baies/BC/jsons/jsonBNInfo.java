package com.baies.BC.jsons;
 
import java.util.Arrays; 

import org.bson.Document;
import org.json.JSONArray;

public class jsonBNInfo {
	 public String id = "id1";
	 public String title = "Weather_BN";
	 public String description = "This supplies graphical forecasts from numerical weather models.";
	 public String reference = "";
	 public String links = "https://www.baies.org/";
	 public String date = "7/12/2018";
	 public String script = "defineNode(A_3, DescriptionC0); {defineState(Discrete, a1, a2);  p( A_3 ) = { a1: 0.2; a2: 0.8;  }   }  defineNode(B_3, DescriptionC2); {defineState(Discrete, b1, b2); p( B_3 ) = { b1: 0.6; b2: 0.4;  }   }  defineNode(X_3, DescriptionC0);{ defineState(Discrete, x1, x2); p( X_3 | A_3, B_3 ) = if( A_3 == a1 && B_3 == b1 ) { x1: 0.3; x2: 0.7; } else if ( A_3 == a1 && B_3 == b2 ) { x1: 0.1; x2: 0.9; }  else if ( A_3 == a2 && B_3 == b1 ) { x1: 0.5; x2: 0.5; }  else if ( A_3 == a2 && B_3 == b2 ) { x1: 0.8; x2: 0.2; }    } ";
	 public String image = "http://localhost:8080/BayesCloud/data/img/img1.PNG";
	 public String authors = "";
	 public String keywords = "";
	 //public JSONArray authors = new JSONArray();
	 //public JSONArray keywords = new JSONArray();
	 
	 public jsonBNInfo(){
		 
	 }
	 
	 public jsonBNInfo( String title_,
						String description_ ,
						String reference_ , 
						String links_ ,
						String date_ ,
						String script_ ,
						String image_ ,
						String authors_ ,
						String keywords_ ){

		 title = title_;
		 description = description_; 
		 reference = reference_;
		 links = links_;
		 date = date_ ;
		 script = script_;
		 image = image_;
		 authors = authors_;
		 keywords = keywords_; 
	 }
 
	 public Document getMongDBDoc(){
		 Document doc = new Document("id", id)
			                 .append("title", title)
			                 .append("description", description)
			                 .append("reference", reference) 
			                 .append("links", links)
			                 .append("date", date)
			                 .append("script", script)			                 
			                 .append("image", image)
			                 .append("authors", authors)
			                 .append("keywords", keywords);
		 
		 return doc;
	 } 
	 
	 @Override
		public String toString() {
			String s = "";
			s += " id: " + id;
			s += " title: " + title; 
			s += " description: " +description;
			s += " links: " + links;
			s += " date: " + date; 
			s += " script: " + script; 
			s += " image: " + image; 
			s += " " + authors.toString();
			s += " " + keywords.toString(); 
			return s;
		}
}
