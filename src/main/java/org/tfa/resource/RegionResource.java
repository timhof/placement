package org.tfa.resource;

import org.tfa.service.RegionService;
import org.tfa.transformers.JsonTransformer;

import static spark.Spark.get;
import static spark.Spark.post;
import static spark.Spark.put;

public class RegionResource {

	private static final String API_CONTEXT = "/api/v1";
	 
    private final RegionService regionService;
 
    public RegionResource(RegionService regionService) {
        this.regionService = regionService;
        setupEndpoints();
    }
 
    private void setupEndpoints() {
       
        get(API_CONTEXT + "/regions/:id", "application/json", (request, response)
 
                -> regionService.find(request.params(":id")), new JsonTransformer());
 
        get(API_CONTEXT + "/regions", "application/json", (request, response)
 
                -> regionService.findAll(), new JsonTransformer());

    }
}
