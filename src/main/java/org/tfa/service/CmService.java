package org.tfa.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.tfa.db.DAOManager;
import org.tfa.dto.CmSearch;
import org.tfa.dto.InputDTO;
import org.tfa.dto.RegionSearch;
import org.tfa.dto.TimePeriodSearch;
import org.tfa.model.Cm;
import org.tfa.model.Region;
import org.tfa.model.TimePeriod;

import com.google.gson.Gson;

public class CmService{


	public CmSearch search(String body){

		CmSearch cmSearchInput = new Gson().fromJson(body, CmSearch.class);
		MatchService matchService = new MatchService();
		TimePeriodService timePeriodService = new TimePeriodService();
		
		//Set TimePeriodsInput if not already set
		if(cmSearchInput.getTimePeriodsInput() == null){
			TimePeriodSearch timePeriodSearch = new TimePeriodService().search(null);
			List<InputDTO> timePeriodsInput = new ArrayList<InputDTO>();
			for(TimePeriod timePeriod : timePeriodSearch.getTimePeriodList()){
				timePeriodsInput.add(new InputDTO(timePeriod.getName(), timePeriod.getTimePeriodId(), false));
			}
			cmSearchInput.setTimePeriodsInput(timePeriodsInput);
			
			//Set default selected time period
			if(timePeriodsInput.size() > 0){
				cmSearchInput.setSelectedTimePeriod(timePeriodsInput.iterator().next());
			}
		}
		if(cmSearchInput.getRegionsInput() == null){
			RegionSearch regionSearch = new RegionService().search(null);
			List<InputDTO> regionsInput = new ArrayList<InputDTO>();
			for(Region region : regionSearch.getRegionList()){
				regionsInput.add(new InputDTO(region.getName(), region.getRegionId(), false));
			}
			if(regionsInput.size() > 0){
				regionsInput.iterator().next().setSelected(true);
			}
			cmSearchInput.setRegionsInput(regionsInput);
			
			//Set default selected region
			if(regionsInput.size() > 0){
				cmSearchInput.setSelectedRegionId(regionsInput.iterator().next());
			}
		}
	
		TimePeriod timePeriod = timePeriodService.getTimePeriod(cmSearchInput.getSelectedTimePeriod().getValue());
		List<Cm> cmList = new ArrayList<Cm>();

		StringBuilder sql = new StringBuilder();
		sql.append("select cmps.cmplacementstatusid, p.firstname || ' ' || p.lastname as name, cm.cmid ");
		sql.append("from cmplacementstatus cmps, cm cm, person p ");
		sql.append("where cmps.active = 'Y' "); 
		sql.append("and cmps.cmkey = cm.cmid ");
		sql.append("and p.personid = cm.personkey ");
		sql.append("and cmps.timePeriodKey = " + timePeriod.getTimePeriodId());
		sql.append(" and cmps.placementRegionKey = " + cmSearchInput.getSelectedRegion().getValue());
		sql.append(" order by name");
		ResultSet rs = DAOManager.getInstance().executeQuery(sql.toString());
		try {
			while (rs.next()) {
				Cm cm = new Cm();
				cm.setCmPlacementStatusId(rs.getInt("cmplacementstatusid"));
				cm.setName(rs.getString("name"));
				cm.setCmId(rs.getInt("cmid"));

				cmList.add(cm);

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		cmSearchInput.setCmList(cmList);
		
//		for(Cm cm : cmList){
//			cm.setMatchList(matchService.findCmMatches(cm, timePeriod));
//		}
		
		return cmSearchInput;
	}

	public Cm find(String cmplacementstatusid){
		Cm cm = null;
		ResultSet rs = DAOManager.getInstance().executeQuery("select * from cmplacementstatus where cmplacementstatusid=" + cmplacementstatusid);
		try {
			while (rs.next()) {
				cm = new Cm();
				cm.setCmPlacementStatusId(rs.getInt("cmplacementstatusid"));
				cm.setName(rs.getString("name"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return cm;
	}
}
