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
import org.tfa.dto.VacancySearch;
import org.tfa.model.Cm;
import org.tfa.model.Region;
import org.tfa.model.TimePeriod;
import org.tfa.model.Vacancy;

import com.google.gson.Gson;

public class VacancyService{


	public VacancySearch search(String body){

		VacancySearch vacancySearchInput = new Gson().fromJson(body, VacancySearch.class);
		
		//Set TimePeriodsInput if not already set
		if(vacancySearchInput.getTimePeriodsInput() == null){
			TimePeriodSearch timePeriodSearch = new TimePeriodService().search(null);
			List<InputDTO> timePeriodsInput = new ArrayList<InputDTO>();
			for(TimePeriod timePeriod : timePeriodSearch.getTimePeriodList()){
				timePeriodsInput.add(new InputDTO(timePeriod.getName(), timePeriod.getTimePeriodId(), false));
			}
			vacancySearchInput.setTimePeriodsInput(timePeriodsInput);
			
			//Set default selected time period
			if(timePeriodsInput.size() > 0){
				vacancySearchInput.setSelectedTimePeriod(timePeriodsInput.iterator().next());
			}
		}
		if(vacancySearchInput.getRegionsInput() == null){
			RegionSearch regionSearch = new RegionService().search(null);
			List<InputDTO> regionsInput = new ArrayList<InputDTO>();
			for(Region region : regionSearch.getRegionList()){
				regionsInput.add(new InputDTO(region.getName(), region.getRegionId(), false));
			}
			if(regionsInput.size() > 0){
				regionsInput.iterator().next().setSelected(true);
			}
			vacancySearchInput.setRegionsInput(regionsInput);
			
			//Set default selected region
			if(regionsInput.size() > 0){
				vacancySearchInput.setSelectedRegionId(regionsInput.iterator().next());
			}
		}
	
		List<Vacancy> vacancyList = new ArrayList<Vacancy>();

		StringBuilder sql = new StringBuilder();
		sql.append("select v.vacancyid, v.name, pp.name as pp_name, s.name as school_name ");
		sql.append(" from vacancy v, placementpartner pp, school s ");
		sql.append("where v.active = 'Y' "); 
		sql.append("and v.timePeriodKey = " + vacancySearchInput.getSelectedTimePeriod().getValue());
		sql.append(" and v.regionKey = " + vacancySearchInput.getSelectedRegion().getValue());
		sql.append(" and v.placementpartnerkey = pp.placementpartnerid (+)");
		sql.append(" and v.schoolkey = s.schoolid (+)");
		sql.append(" order by v.name");
		ResultSet rs = DAOManager.getInstance().executeQuery(sql.toString());
		try {
			while (rs.next()) {
				Vacancy vacancy = new Vacancy();
				vacancy.setVacancyId(rs.getInt("vacancyId"));
				vacancy.setName(rs.getString("name"));
				vacancy.setPlacementPartner(rs.getString("pp_name"));
				vacancy.setSchool(rs.getString("school_name"));

				vacancyList.add(vacancy);

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		vacancySearchInput.setVacancyList(vacancyList);
		
		
		return vacancySearchInput;
	}

	public Vacancy find(String cmplacementstatusid){
		Vacancy vacancy = null;
		ResultSet rs = DAOManager.getInstance().executeQuery("select * from vacancy where vacancyid=" + cmplacementstatusid);
		try {
			while (rs.next()) {
				vacancy = new Vacancy();
				vacancy.setVacancyId(rs.getInt("vacancyid"));
				vacancy.setName(rs.getString("name"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return vacancy;
	}
}
