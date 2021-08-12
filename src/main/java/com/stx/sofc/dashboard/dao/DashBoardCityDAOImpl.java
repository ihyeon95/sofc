package com.stx.sofc.dashboard.dao;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.stx.sofc.dashboard.vo.DashboardVO;
import com.stx.sofc.dashboard.vo.EmailVO;
import com.stx.sofc.login.vo.LoginVO;

@Repository
public class DashBoardCityDAOImpl implements DashBoardCityDAO{

	@Inject
	private SqlSession sqlSession;
	
	private static String namespace = "com.stx.sofc.mappers.dashboard.city";	
	
	@Override
	public DashboardVO mainSwitch(LoginVO vo) throws Exception {
		return sqlSession.selectOne(namespace + ".mainSwitch", vo);
	}
	
	@Override
	public List<DashboardVO> cityEffiGraph(DashboardVO vo) throws Exception {
		return sqlSession.selectList(namespace + ".cityEffiGraph", vo);
	}
	
	@Override
	public DashboardVO cityMeasure(String getiRtuNum) throws Exception {

		return sqlSession.selectOne(namespace + ".cityMeasure", getiRtuNum);
	}
	
	@Override
	public List<DashboardVO> cityList(DashboardVO vo) throws Exception {
		return sqlSession.selectList(namespace + ".cityList", vo);
	}
	
	@Override
	public List<DashboardVO> cityNameList(DashboardVO vo) throws Exception {

		return sqlSession.selectList(namespace + ".cityNameList", vo);
	}
	
	@Override
	public List<DashboardVO> cityMeasureList(DashboardVO vo) throws Exception {

		return sqlSession.selectList(namespace + ".cityMeasureList", vo);
	}
	
	@Override
	public List<DashboardVO> cityMeasureHourList(DashboardVO vo) throws Exception {

		return sqlSession.selectList(namespace + ".cityMeasureHourList", vo);
	}
	
	@Override
	public int insertCityInfo(DashboardVO vo) throws Exception {

		return sqlSession.insert(namespace + ".insertCityInfo", vo);
	}	
	
	@Override
	public int deleteCityInfo(DashboardVO vo) throws Exception {

		return sqlSession.delete(namespace + ".deleteCityInfo", vo);
	}
	
	@Override
	public List<DashboardVO> measureStatusList(DashboardVO vo) throws Exception {

		return sqlSession.selectList(namespace + ".measureStatusList", vo);
	}	

	@Override
	public int systemEffi(DashboardVO vo) throws Exception {

		return sqlSession.selectOne(namespace + ".systemEffi", vo);
	}	
	
	@Override
	public int systemCnt(DashboardVO vo) throws Exception {

		return sqlSession.selectOne(namespace + ".systemCnt", vo);
	}
	
	@Override
	public List<DashboardVO> systemRtuIdList(DashboardVO vo) throws Exception {

		return sqlSession.selectList(namespace + ".systemRtuIdList", vo);
	}
	
	@Override
	public DashboardVO tdAccumulateWattProduceAvg(DashboardVO vo) throws Exception {
		
		return sqlSession.selectOne(namespace + ".tdAccumulateWattProduceAvg", vo);
	}

	@Override
	public String tdFLAvg(DashboardVO vo) throws Exception {

		return sqlSession.selectOne(namespace + ".tdFLAvg", vo);
	}

	@Override
	public String adAccumulateWattProduceAvg(DashboardVO vo) throws Exception {
		
		return sqlSession.selectOne(namespace + ".adAccumulateWattProduceAvg", vo);
	}
	
	@Override
	public String adFLAvg(DashboardVO vo) throws Exception {

		return sqlSession.selectOne(namespace + ".adFLAvg", vo);
	}	
	

	@Override
	public List<DashboardVO> eventList(DashboardVO vo) throws Exception {

		return sqlSession.selectList(namespace + ".eventList", vo);
	}
	
	@Override
	public int eventListCnt(DashboardVO vo) throws Exception {

		return sqlSession.selectOne(namespace + ".eventListCnt", vo);
	}	
	
	@Override
	public List<DashboardVO> eventAlarmList() throws Exception {

		return sqlSession.selectList(namespace + ".eventAlarmList");
	}

	@Override
	public int eventAlarmToday(DashboardVO vo) throws Exception {

		return sqlSession.selectOne(namespace + ".eventAlarmToday", vo);
	}

	@Override
	public List<EmailVO> emailList() throws Exception {

		return sqlSession.selectList(namespace + ".emailList");
	}	
	
	@Override
	public int updateEventAlarm(DashboardVO vo) throws Exception {
		
		return sqlSession.update(namespace + ".updateEventAlarm", vo);
	}
	
	@Override
	public DashboardVO eventSystemPath(String iRtuNum) throws Exception {

		return sqlSession.selectOne(namespace + ".eventSystemPath", iRtuNum);
	}	
	
	
	
}
