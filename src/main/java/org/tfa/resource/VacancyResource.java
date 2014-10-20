package org.tfa.resource;

import org.tfa.service.VacancyService;
import org.tfa.transformers.JsonTransformer;

import static spark.Spark.get;
import static spark.Spark.post;
import static spark.Spark.put;

public class VacancyResource {

	private static final String API_CONTEXT = "/api/v1";
	 
    private final VacancyService vacancyService;
 
    public VacancyResource(VacancyService vacancyService) {
        this.vacancyService = vacancyService;
        setupEndpoints();
    }
 
    private void setupEndpoints() {
       
        get(API_CONTEXT + "/vacancies/:id", "application/json", (request, response)
 
                -> vacancyService.find(request.params(":id")), new JsonTransformer());
        
        post(API_CONTEXT + "/vacancies", "application/json", (request, response)
        		 
                -> vacancyService.search(request.body()), new JsonTransformer());

    }
}
