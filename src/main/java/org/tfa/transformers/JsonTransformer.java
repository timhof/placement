package org.tfa.transformers;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import spark.ResponseTransformer;

public class JsonTransformer implements ResponseTransformer {
 
    private Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation()
    		.registerTypeAdapterFactory(new EnumAdapterFactory())
    		.create();
 
    @Override
    public String render(Object model) {
        return gson.toJson(model);
    }
 
}