package com.baies.BC.inter;
  
import java.io.UnsupportedEncodingException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServlet; 
import javax.ws.rs.POST;
import javax.ws.rs.Path;

import org.json.JSONException;
import org.json.JSONObject;
 
import com.baies.BC.db.BN_DB;
import com.baies.BC.jsons.jsonBNInfo;
import com.baies.BC.util.BC_Util; 

/**
 * Servlet implementation class UpdateUsername
 */
@Path("RequestBN")
public class RequestBN extends HttpServlet { 
          
	private static final long serialVersionUID = 7296436501584398964L;
 
	@POST
	@Path("run")
	public String run(String reqJson) { 
		//get data from the web  
		if (reqJson == null || reqJson.isEmpty())
			return null;
		 
		JSONObject obj = new JSONObject(reqJson);
			 
		String reqMode = obj.getString("mode");
		String reqType = obj.getString("type");
		JSONObject reqData = obj.getJSONObject("data");
		String ret = "";
				
		DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss.SSS"); 
		String date = dateFormat.format(new Date()); 
		
		//////////////////////////////////////////////////////////////////////////////
		/// search 
		if (reqMode.equalsIgnoreCase("search")) {
			System.out.println("search bn!");
			if (reqType.equalsIgnoreCase("html")) {
				ret = new BN_DB().search_to_json(reqData.getString("srch-term"));
			    //ret = "[{ \"_id\" : { \"$oid\" : \"5b4ac46c2f9fac01a824dcb1\" }, \"name\" : \"MongoDB\", \"type\" : \"database\", \"count\" : 1, \"versions\" : [\"v3.2\", \"v3.0\", \"v2.6\"], \"info\" : { \"x\" : 203, \"y\" : 102 } }, { \"_id\" : { \"$oid\" : \"5b4ac46c2f9fac01a824dcb1\" }, \"name\" : \"MongoDB\", \"type\" : \"database\", \"count\" : 1, \"versions\" : [\"v3.2\", \"v3.0\", \"v2.6\"], \"info\" : { \"x\" : 203, \"y\" : 102 } }]";
			} else if (reqType.equalsIgnoreCase("text")) {
				ret = new BN_DB().search_to_text(reqData.getString("srch-term"));
				return ret;
			}
			
		} 
		
		//////////////////////////////////////////////////////////////////////////////
		/// register 
		else if (reqMode.equalsIgnoreCase("register")) {
			System.out.println("register bn!");  
			if (reqType.equalsIgnoreCase("html")) {
			}
			
			try {
				String script = java.net.URLDecoder.decode(reqData.getString("script"), "UTF-8"); 
 
				BN_DB.insert(new jsonBNInfo( 
						reqData.getString("title"),
						reqData.getString("description"),
						reqData.getString("reference"),
						reqData.getString("links"),
						date,
						script,
						reqData.getString("image"),
						reqData.getString("authors"),
						reqData.getString("keywords")
						)
					);
				
				ret = "\"The model was registered!\"";
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		} 
		
		//////////////////////////////////////////////////////////////////////////////
		/// draw 
		else if (reqMode.equalsIgnoreCase("draw")) {
			System.out.println("draw bn!");
			CPS_Reasoner reasoner = new CPS_Reasoner();
			 
			if (reqType.equalsIgnoreCase("html")) {
				String script;
				try {
				    script = java.net.URLDecoder.decode(reqData.getString("script"), "UTF-8"); 
					ret = reasoner.compile_to_BN_HTML(script, "");
					return ret;	// HTML for BN
				} catch (UnsupportedEncodingException e) { 
					e.printStackTrace();
				} catch (JSONException e) { 
					e.printStackTrace();
				} 
			}
		}
		
		//////////////////////////////////////////////////////////////////////////////
		/// update 
		else if (reqMode.equalsIgnoreCase("update")) {
			System.out.println("draw bn!");
			CPS_Reasoner reasoner = new CPS_Reasoner();
			String script = "";
			
			String sc = "";
			try { sc = reqData.getString("script"); } catch (JSONException e) { System.out.println("No script!, but ok"); }
			String name = "";
			try { name = reqData.getString("name"); } catch (JSONException e) { System.out.println("No name!, but ok"); }
			String ev = "";
			try { ev = reqData.getString("EV"); } catch (JSONException e) { System.out.println("No EV!, but ok"); }
									
			if (sc != null && !sc.isEmpty()) {
				try {
				    script = java.net.URLDecoder.decode(sc, "UTF-8"); 
					return ret;	// HTML for BN
				} catch (UnsupportedEncodingException e) { 
					e.printStackTrace();
				} 
			}
			
			if (name != null && !name.isEmpty()) {
				script = new BN_DB().get_bn(name);
			}

			if (ev != null && !ev.isEmpty()) {
			    ev = new BC_Util().getSBNdefineEvidence(ev);
			}
			
			if (reqType.equalsIgnoreCase("html")) {
				ret = reasoner.compile_to_BN_HTML(script, ev);
				return ret;	// HTML for BN
			} else if (reqType.equalsIgnoreCase("text")) {
				ret = reasoner.compile_to_BN_Text(script, ev);
				return ret;
			} else if (reqType.equalsIgnoreCase("json")) {
				ret = reasoner.compile_to_BN_Json(script, ev);
				return ret;
			}
		} 
		
		//////////////////////////////////////////////////////////////////////////////
		/// get 
		else if (reqMode.equalsIgnoreCase("get")) {
			System.out.println("get bn!"); 
			if (reqType.equalsIgnoreCase("text")) {
				ret = new BN_DB().get_bn(reqData.getString("name"));
				return ret;
			} 
		}  
		  
		String json = 	"{" + 
						"\"mode\":" + "\"" +  reqMode + "\"," +
						"\"type\":" + "\"" +  reqType + "\"," +
						"\"data\":" + ret +
						"}"; 
		
		return json;
	}  
}
