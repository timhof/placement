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

import com.google.gson.Gson;

public class TimePeriodService{


	public TimePeriodSearch search(String body){

		TimePeriodSearch timePeriodInput = new Gson().fromJson(body, TimePeriodSearch.class);
		if(timePeriodInput == null){
			timePeriodInput = new TimePeriodSearch();
		}
		List<TimePeriod> timePeriodList = new ArrayList<TimePeriod>();

		StringBuilder sql = new StringBuilder();
		sql.append("select * from timeperiod ");
		boolean hasCondition = false;
		if(timePeriodInput.getStartTimePeriodId() != null){
			sql.append(hasCondition ? "and " : "where ");
			sql.append("timePeriodId >= " + timePeriodInput.getStartTimePeriodId() + " ");
			hasCondition = true;
		}
		if(timePeriodInput.getEndTimePeriodId() != null){
			sql.append(hasCondition ? "and " : "where ");
			sql.append("timePeriodId <= " + timePeriodInput.getEndTimePeriodId() + " ");
			hasCondition = true;
		}

		sql.append("order by shortdescription desc");
		ResultSet rs = DAOManager.getInstance().executeQuery(sql.toString());
		try {
			while (rs.next()) {
				TimePeriod timePeriod = new TimePeriod();
				timePeriod.setTimePeriodId(rs.getInt("timeperiodid"));
				timePeriod.setDescription(rs.getString("shortdescription"));
				timePeriod.setName(rs.getString("name"));

				timePeriodList.add(timePeriod);

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		timePeriodInput.setTimePeriodList(timePeriodList);

		return timePeriodInput;
	}
	
	public TimePeriod getTimePeriod(String timePeriodId){
		
		TimePeriod timePeriod = null;
		ResultSet rs = DAOManager.getInstance().executeQuery("select * from timeperiod where timeperiodid=" + timePeriodId);
		try {
			while (rs.next()) {
				timePeriod = new TimePeriod();
				timePeriod.setTimePeriodId(rs.getInt("timeperiodid"));
				timePeriod.setDescription(rs.getString("shortdescription"));
				timePeriod.setName(rs.getString("name"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return timePeriod;
	}
}
