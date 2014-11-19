package org.tfa.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.tfa.db.DAOManager;
import org.tfa.dto.CmSearch;
import org.tfa.dto.InputDTO;
import org.tfa.dto.RegionSearch;
import org.tfa.dto.TimePeriodSearch;
import org.tfa.model.Cm;
import org.tfa.model.Region;
import org.tfa.model.TimePeriod;
import org.tfa.model.HiringManager.EntityType;

import com.google.gson.Gson;

public class CmService{


	public CmSearch search(String body){

		CmSearch cmSearchInput = new Gson().fromJson(body, CmSearch.class);
		TimePeriodService timePeriodService = new TimePeriodService();
		
		Map<String, InputDTO> corpsYearInputMap = new HashMap<String, InputDTO>();
		Map<String, InputDTO> stageInputMap = new HashMap<String, InputDTO>();
		Map<String, InputDTO> stepInputMap = new HashMap<String, InputDTO>();
		Map<String, InputDTO> releaseCodeInputMap = new HashMap<String, InputDTO>();
		Map<String, InputDTO> releaseStepInputMap = new HashMap<String, InputDTO>();
		
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
		sql.append("select cmps.cmplacementstatusid, d.stage, d.step, cm.releasecode, cm.releasestep, ");
		sql.append("cm.corpsyear, p.personid, p.firstname || ' ' || p.lastname as name, cm.cmid ");
		sql.append("from cmplacementstatus cmps, cm cm, person p, disposition d ");
		sql.append("where cmps.active = 'Y' "); 
		sql.append("and cmps.cmkey = cm.cmid ");
		sql.append("and p.personid = cm.personkey ");
		sql.append("and d.personkey = p.personid ");
		sql.append("and d.iscurrent = 'Y' ");
		sql.append("and cmps.timePeriodKey = " + timePeriod.getTimePeriodId());
		sql.append(" and cmps.placementRegionKey = " + cmSearchInput.getSelectedRegion().getValue());
		if(cmSearchInput.getCorpsYearsInput() != null){
        	sql.append(" and " + DAOManager.createInCondition("corpsyear", cmSearchInput.getCorpsYearsInput(), false));
        }
		if(cmSearchInput.getStagesInput() != null){
        	sql.append(" and (" + DAOManager.createInCondition("stage", cmSearchInput.getStagesInput(), true));
        	if (cmSearchInput.getStagesInput().stream().anyMatch(ti -> (ti.getSelected() && ti.getValue().equals("null")))) {
        		sql.append(" or stage is null or stage = ''");
        	}
        	sql.append(") ");
        }
		if(cmSearchInput.getStepsInput() != null){
        	sql.append(" and (" + DAOManager.createInCondition("step", cmSearchInput.getStepsInput(), true));
        	if (cmSearchInput.getStepsInput().stream().anyMatch(ti -> (ti.getSelected() && ti.getValue().equals("null")))) {
        		sql.append(" or step is null or step = ''");
        	}
        	sql.append(") ");
        }
		if(cmSearchInput.getReleaseCodesInput() != null){
        	sql.append(" and (" + DAOManager.createInCondition("releasecode", cmSearchInput.getReleaseCodesInput(), true));
        	if (cmSearchInput.getReleaseCodesInput().stream().anyMatch(ti -> (ti.getSelected() && ti.getValue().equals("null")))) {
        		sql.append(" or releasecode is null or releasecode = ''");
        	}
        	sql.append(") ");
        }
		if(cmSearchInput.getReleaseStepsInput() != null){
        	sql.append(" and (" + DAOManager.createInCondition("releasestep", cmSearchInput.getReleaseStepsInput(), true));
        	if (cmSearchInput.getReleaseStepsInput().stream().anyMatch(ti -> (ti.getSelected() && ti.getValue().equals("null")))) {
        		sql.append(" or releasestep is null or releasestep = ''");
        	}
        	sql.append(") ");
        }
		sql.append(" order by name");
		ResultSet rs = DAOManager.getInstance().executeQuery(sql.toString());
		try {
			while (rs.next()) {
				
				String stage = rs.getString("stage");
				String step = rs.getString("step");
				String releaseCode = rs.getString("releasecode");
				String releaseStep = rs.getString("releasestep");
				
				Cm cm = new Cm();
				cm.setCmPlacementStatusId(rs.getInt("cmplacementstatusid"));
				cm.setName(rs.getString("name"));
				cm.setCmId(rs.getInt("cmid"));
				cm.setPersonId(rs.getInt("personid"));
				cm.setCorpsYear(rs.getInt("corpsyear"));
				cm.setStage(stage);
				cm.setStep(step);
				cm.setReleaseCode(releaseCode);
				cm.setReleaseStep(rs.getString("releasestep"));

				cmList.add(cm);
				
				String corpsYearStr = String.valueOf(rs.getInt("corpsyear"));
				corpsYearInputMap.put(corpsYearStr, new InputDTO(corpsYearStr, corpsYearStr));
				
				if(stage == null || "".equals(stage)){
					stage = "null";
				}
				stageInputMap.put(stage, new InputDTO(stage, stage));
				
				if(step == null || "".equals(step)){
					step = "null";
				}
				stepInputMap.put(step, new InputDTO(step, step));
				
				if(releaseCode == null || "".equals(releaseCode)){
					releaseCode = "null";
				}
				releaseCodeInputMap.put(releaseCode, new InputDTO(releaseCode, releaseCode));
				
				if(releaseStep == null || "".equals(releaseStep)){
					releaseStep = "null";
				}
				releaseStepInputMap.put(releaseStep, new InputDTO(releaseStep, releaseStep));

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		cmSearchInput.setCmList(cmList);
		
		if(cmSearchInput.getCorpsYearsInput() == null){
			cmSearchInput.setCorpsYearsInput(new ArrayList<InputDTO>(corpsYearInputMap.values()));
        }
		if(cmSearchInput.getStagesInput() == null){
			cmSearchInput.setStagesInput(new ArrayList<InputDTO>(stageInputMap.values()));
        }
		if(cmSearchInput.getStepsInput() == null){
			cmSearchInput.setStepsInput(new ArrayList<InputDTO>(stepInputMap.values()));
        }
		if(cmSearchInput.getReleaseCodesInput() == null){
			cmSearchInput.setReleaseCodesInput(new ArrayList<InputDTO>(releaseCodeInputMap.values()));
        }
		if(cmSearchInput.getReleaseStepsInput() == null){
			cmSearchInput.setReleaseStepsInput(new ArrayList<InputDTO>(releaseStepInputMap.values()));
        }
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
