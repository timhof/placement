package org.tfa.resource;

import org.tfa.service.HiringManagerService;
import org.tfa.transformers.JsonTransformer;

import static spark.Spark.get;
import static spark.Spark.post;
import static spark.Spark.put;

public class HiringManagerResource {

	private static final String API_CONTEXT = "/api/v1";
	 
    private final HiringManagerService hiringManagerService;
 
    public HiringManagerResource(HiringManagerService hiringManagerService) {
        this.hiringManagerService = hiringManagerService;
        setupEndpoints();
    }
 
    private void setupEndpoints() {
       
        post(API_CONTEXT + "/hiringManagers", "application/json", (request, response)
        		 
                -> hiringManagerService.search(request.body()), new JsonTransformer());

    }
}
