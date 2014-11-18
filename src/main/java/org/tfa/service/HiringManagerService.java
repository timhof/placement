package org.tfa.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.tfa.db.DAOManager;
import org.tfa.dto.InputDTO;
import org.tfa.dto.HiringManagerSearch;
import org.tfa.dto.RegionSearch;
import org.tfa.model.HiringManager;
import org.tfa.model.HiringManager.EntityType;
import org.tfa.model.Region;
import org.tfa.model.Vacancy;

import com.google.gson.Gson;

public class HiringManagerService{

    public HiringManagerSearch search(String body){
    	
    	HiringManagerSearch hiringManagerSearchInput = new Gson().fromJson(body, HiringManagerSearch.class);
    	
    	if(hiringManagerSearchInput.getRegionsInput() == null){
			RegionSearch regionSearch = new RegionService().search(null);
			List<InputDTO> regionsInput = new ArrayList<InputDTO>();
			for(Region region : regionSearch.getRegionList()){
				regionsInput.add(new InputDTO(region.getName(), region.getRegionId(), false));
			}
			if(regionsInput.size() > 0){
				regionsInput.iterator().next().setSelected(true);
			}
			hiringManagerSearchInput.setRegionsInput(regionsInput);
			
			//Set default selected region
			if(regionsInput.size() > 0){
				hiringManagerSearchInput.setSelectedRegionId(regionsInput.iterator().next());
			}
		}
    	
    	List<HiringManager> hiringManagerList = new ArrayList<HiringManager>();
    	EnumSet<EntityType> entityTypeSet = EnumSet.noneOf(EntityType.class);
    	
        StringBuilder sql = new StringBuilder();
        sql.append("select p.personid, p.firstname || ' ' || p.lastname as name, pp.name as placementpartner_name, omd.orgmembershipdatumid, omd.value ");
        sql.append("from placementpartner pp , orgmembership om, orgmembershipdatum omd,  person p  ");
        		sql.append("where relationshiptype='HM' and om.active='Y' "); 
        		sql.append("and om.organizationkey = pp.organizationkey "); 
        				sql.append("and omd.orgmembershipkey = om.orgmembershipid "); 
        						sql.append("and omd.propertydescriptorkey=4 "); 
        								sql.append("and p.personid = om.personkey "); 
        		sql.append("and pp.regionkey=" + hiringManagerSearchInput.getSelectedRegion().getValue());
        sql.append(" order by p.lastname");
        ResultSet rs = DAOManager.getInstance().executeQuery(sql.toString());
        try {
			while (rs.next()) {
				HiringManager hiringManager = new HiringManager();
				hiringManager.setHiringManagerId(rs.getInt("personid"));
				hiringManager.setName(rs.getString("name"));
				hiringManager.setRole(rs.getString("value"));
				hiringManager.setEntityType(EntityType.PLACEMENT_PARTNER);
				hiringManager.setEntityName(rs.getString("placementpartner_name"));
				hiringManagerList.add(hiringManager);

				entityTypeSet.add(EntityType.PLACEMENT_PARTNER);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
        
        sql = new StringBuilder();
        sql.append("select p.personid, p.firstname || ' ' || p.lastname as name, s.name as school_name, omd.orgmembershipdatumid, omd.value ");
        sql.append("from school s , orgmembership om, orgmembershipdatum omd,  person p  ");
        		sql.append("where relationshiptype='HM' and om.active='Y' "); 
        		sql.append("and om.organizationkey = s.organizationkey "); 
        				sql.append("and omd.orgmembershipkey = om.orgmembershipid "); 
        						sql.append("and omd.propertydescriptorkey=4 "); 
        								sql.append("and p.personid = om.personkey "); 
        		sql.append("and s.regionkey=" + hiringManagerSearchInput.getSelectedRegion().getValue());
        sql.append(" order by p.lastname");
        rs = DAOManager.getInstance().executeQuery(sql.toString());
        try {
			while (rs.next()) {
				HiringManager hiringManager = new HiringManager();
				hiringManager.setHiringManagerId(rs.getInt("personid"));
				hiringManager.setName(rs.getString("name"));
				hiringManager.setRole(rs.getString("value"));
				hiringManager.setEntityType(EntityType.SCHOOL);
				hiringManager.setEntityName(rs.getString("school_name"));
				hiringManagerList.add(hiringManager);

				entityTypeSet.add(EntityType.SCHOOL);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
        
        hiringManagerSearchInput.setHiringManagerList(hiringManagerList);
  
        return hiringManagerSearchInput;
    }
}
