package org.tfa.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.tfa.db.DAOManager;
import org.tfa.dto.InputDTO;
import org.tfa.dto.RegionSearch;
import org.tfa.model.Region;

import com.google.gson.Gson;

public class RegionService{

    public RegionSearch search(String body){
    	
    	RegionSearch regionSearchInput = new Gson().fromJson(body, RegionSearch.class);
    	if(regionSearchInput == null){
    		regionSearchInput = new RegionSearch();
    	}
        List<Region> regionList = new ArrayList<>();
        Map<String, InputDTO> regionTypesInputMap = new HashMap<String, InputDTO>();
        
        StringBuilder sql = new StringBuilder();
        sql.append("select * from region where active='Y'");
        boolean hasCondition = true;
        if(regionSearchInput.getRegionTypesInput() != null){
        	sql.append(hasCondition ? "and " : "where ");
        	sql.append(DAOManager.createInCondition("regiontype", regionSearchInput.getRegionTypesInput(), true));
        }
        sql.append(" order by name");
        ResultSet rs = DAOManager.getInstance().executeQuery(sql.toString());
        try {
			while (rs.next()) {
				Region region = new Region();
				region.setRegionId(rs.getInt("regionid"));
				region.setName(rs.getString("name"));
				region.setCode(rs.getString("regioncode"));
				
				regionList.add(region);
				
				String regionType = rs.getString("regiontype");
				regionTypesInputMap.put(regionType, new InputDTO(regionType, regionType));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
        
        regionSearchInput.setRegionList(regionList);
        if(regionSearchInput.getRegionTypesInput() == null){
        	regionSearchInput.setRegionTypesInput(new ArrayList<InputDTO>(regionTypesInputMap.values()));
        }
        return regionSearchInput;
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
