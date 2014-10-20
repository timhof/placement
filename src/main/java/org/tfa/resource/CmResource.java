package org.tfa.resource;

import org.tfa.service.CmService;
import org.tfa.transformers.JsonTransformer;

import static spark.Spark.get;
import static spark.Spark.post;
import static spark.Spark.put;

public class CmResource {

	private static final String API_CONTEXT = "/api/v1";
	 
    private final CmService cmService;
 
    public CmResource(CmService cmService) {
        this.cmService = cmService;
        setupEndpoints();
    }
 
    private void setupEndpoints() {
       
        get(API_CONTEXT + "/cms/:id", "application/json", (request, response)
 
                -> cmService.find(request.params(":id")), new JsonTransformer());
        
        post(API_CONTEXT + "/cms", "application/json", (request, response)
        		 
                -> cmService.search(request.body()), new JsonTransformer());

    }
}
