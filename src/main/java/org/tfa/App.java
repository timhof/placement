package org.tfa;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.tfa.db.DAOManager;
import org.tfa.resource.RegionResource;
import org.tfa.service.RegionService;

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
    }
}
