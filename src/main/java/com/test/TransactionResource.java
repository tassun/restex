package com.test;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

@Path("transaction")
@SuppressWarnings("unchecked")
public class TransactionResource {

	@POST
    @Path("/create")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response create(String params) {
    	Responser result = new Responser("transaction","create");
    	System.out.println("params="+params);
    	try {
    		JSONParser parser = new JSONParser();
    		JSONObject json = (JSONObject)parser.parse(params);
    		TTransaction trx = new TTransaction();
    		trx.setUserid("tester");
    		trx.obtain(json);
    		try(java.sql.Connection connection = TTransaction.getConnection()) {
    			int rows = trx.create(connection);
    			System.out.println("rows = "+rows);
    			if(rows>0) {
    				result.body().put("transid",trx.getTransid());
    			}
    		} catch(Exception ex) { throw ex; }
    	} catch(Exception ex) {
    		ex.printStackTrace();
    		result.compose(ex,"500");
    	}
        return Response.ok(result).build();    	    	    	    	
    }

    @GET
    @Path("/retrieve/{transid}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Responser retrieve(@PathParam("transid") String transid) {
    	Responser result = new Responser("transaction","retrieve");
    	System.out.println("transid="+transid);
    	try {
    		TTransaction trx = new TTransaction();
    		trx.setUserid("tester");
    		trx.setTransid(transid);
    		try(java.sql.Connection connection = TTransaction.getConnection()) {
    			int rows = trx.retrieve(connection);
    			System.out.println("rows = "+rows);
    			if(rows>0) {
    				result.body().put("transid",trx.getTransid());
    				result.body().put("transdate",trx.getTransdate());
    				result.body().put("journalid",trx.getJournalid());
    				result.body().put("transno",trx.getTransno());
    				result.body().put("amount",trx.getAmount());
    				result.body().put("remark",trx.getRemark());
    				result.body().put("journalname",trx.getJournalname());
    				result.body().put("journaltype",trx.getJournaltype());
    			}
    		} catch(Exception ex) { throw ex; }
    	} catch(Exception ex) {
    		ex.printStackTrace();
    		result.compose(ex,"500");
    	}
        return result;    	    	    	    	
    }
    
	@PUT
    @Path("/update")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Responser update(String params) {
    	Responser result = new Responser("transaction","update");
    	System.out.println("params="+params);
    	try {
    		JSONParser parser = new JSONParser();
    		JSONObject json = (JSONObject)parser.parse(params);
    		TTransaction trx = new TTransaction();
    		trx.setUserid("tester");
    		trx.obtain(json);
    		try(java.sql.Connection connection = TTransaction.getConnection()) {
    			int rows = trx.update(connection);
    			System.out.println("rows = "+rows);
    		} catch(Exception ex) { throw ex; }
    	} catch(Exception ex) {
    		ex.printStackTrace();
    		result.compose(ex,"500");
    	}
        return result;    	    	    	    	
    }

	@DELETE
    @Path("/delete")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Responser delete(@QueryParam("transid") String transid) {
    	Responser result = new Responser("transaction","delete");
    	System.out.println("transid="+transid);
    	try {
    		TTransaction trx = new TTransaction();
    		trx.setUserid("tester");
    		trx.setTransid(transid);
    		try(java.sql.Connection connection = TTransaction.getConnection()) {
    			int rows = trx.delete(connection);
    			System.out.println("rows = "+rows);
    		} catch(Exception ex) { throw ex; }
    	} catch(Exception ex) {
    		ex.printStackTrace();
    		result.compose(ex,"500");
    	}
        return result;    	    	    	    	
    }
	
	@POST
    @Path("/find")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Responser find(String params) {
    	Responser result = new Responser("transaction","find");
    	System.out.println("params="+params);
    	try {
    		JSONParser parser = new JSONParser();
    		JSONObject json = (JSONObject)parser.parse(params);
    		TTransaction trx = new TTransaction();
    		trx.setUserid("tester");
    		trx.obtain(json);
    		JSONArray records = new JSONArray();
    		result.body().put("transdate",trx.getTransdate());
    		result.body().put("records",records);
    		try(java.sql.Connection connection = TTransaction.getConnection()) {
    			java.util.List<TTransaction> trxlist = trx.find(connection);
    			System.out.println("rows = "+trxlist==null?0:trxlist.size());
    			if(trxlist!=null) {
    				for(TTransaction trn : trxlist) {
    					JSONObject js = new JSONObject();
        				js.put("transid",trn.getTransid());
        				js.put("transdate",trn.getTransdate());
        				js.put("journalid",trn.getJournalid());
        				js.put("transno",trn.getTransno());
        				js.put("amount",trn.getAmount());
        				js.put("remark",trn.getRemark());
        				js.put("journalname",trn.getJournalname());
        				js.put("journaltype",trn.getJournaltype());
        				records.add(js);
    				}
    			}
    		} catch(Exception ex) { throw ex; }
    	} catch(Exception ex) {
    		ex.printStackTrace();
    		result.compose(ex,"500");
    	}
        return result;    	    	    	    	
    }
	
}
