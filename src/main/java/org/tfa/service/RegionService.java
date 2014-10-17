package org.tfa.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.tfa.db.DAOManager;
import org.tfa.model.Region;

import com.google.gson.Gson;

public class RegionService {
 
    public List<Region> findAll() {
        List<Region> regions = new ArrayList<>();
        ResultSet rs = DAOManager.getInstance().executeQuery("select * from region");
        try {
			while (rs.next()) {
				Region region = new Region();
				region.setRegionId(rs.getInt("regionid"));
				region.setName(rs.getString("name"));
				region.setCode(rs.getString("regioncode"));
				
				regions.add(region);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return regions;
    }
    
    public Region find(String regionId){
        Region region = null;
        ResultSet rs = DAOManager.getInstance().executeQuery("select * from region where regionid=" + regionId);
        try {
			while (rs.next()) {
				region = new Region();
				region.setRegionId(rs.getInt("regionid"));
				region.setName(rs.getString("name"));
				region.setCode(rs.getString("code"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return region;
    }
}
