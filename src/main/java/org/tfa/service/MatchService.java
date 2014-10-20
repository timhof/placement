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
import org.tfa.model.Match;
import org.tfa.model.Region;
import org.tfa.model.TimePeriod;
import org.tfa.model.Vacancy;

import com.google.gson.Gson;

public class MatchService{


	public List<Match> findCmMatches(Cm cm, TimePeriod timePeriod){
		
		List<Match> matchList = new ArrayList<Match>();
		
		StringBuilder sql = new StringBuilder();
		sql.append("select cmvm.*, v.* from cmvacancymap cmvm, vacancy v");
		sql.append(" where cmvm.active = 'Y'");
		sql.append(" and v.vacancyid = cmvm.vacancykey");
		sql.append(" and v.timeperiodkey = " + timePeriod.getTimePeriodId());
		sql.append(" and cmvm.cmkey =" + cm.getCmId());
		
		ResultSet rs = DAOManager.getInstance().executeQuery(sql.toString());
		try {
			while (rs.next()) {
				
				Vacancy vacancy = new Vacancy();
				vacancy.setVacancyId(rs.getInt("vacancyId"));
				vacancy.setName(rs.getString("name"));
				
				Match match = new Match();
				match.setMatchId(rs.getInt("cmvacancymapid"));
				match.setCm(cm);
				match.setVacancy(vacancy);
				
				matchList.add(match);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return matchList;
	}
}
