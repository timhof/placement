package org.tfa;

import org.tfa.resource.CmResource;
import org.tfa.resource.HiringManagerResource;
import org.tfa.resource.RegionResource;
import org.tfa.resource.VacancyResource;
import org.tfa.service.CmService;
import org.tfa.service.HiringManagerService;
import org.tfa.service.RegionService;
import org.tfa.service.VacancyService;

import static spark.Spark.*;

public class App 
{
	private static final String IP_ADDRESS = "localhost";
    private static final int PORT = 8080;
 
    public static void main(String[] args) throws Exception {
        setIpAddress(IP_ADDRESS);
        setPort(PORT);
        staticFileLocation("/public");
        new RegionResource(new RegionService());
        new CmResource(new CmService());
        new VacancyResource(new VacancyService());
        new HiringManagerResource(new HiringManagerService());
    }
}
