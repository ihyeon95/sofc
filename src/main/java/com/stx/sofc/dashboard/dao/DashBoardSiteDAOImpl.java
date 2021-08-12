package com.stx.sofc.dashboard.dao;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.stx.sofc.dashboard.vo.DashboardVO;

@Repository
public class DashBoardSiteDAOImpl implements DashBoardSiteDAO{

	@Inject
	private SqlSession sqlSession;
	
	private static String namespace = "com.stx.sofc.mappers.dashboard.site";	
	
	@Override
	public List<DashboardVO> siteEffiGraph(DashboardVO vo) throws Exception {

		return sqlSession.selectList(namespace + ".siteEffiGraph", vo);
	}
	
	@Override
	public DashboardVO siteMeasure(DashboardVO vo) throws Exception {

		return sqlSession.selectOne(namespace + ".siteMeasure", vo);
	}
	
	@Override
	public List<DashboardVO> siteList(DashboardVO vo) throws Exception {

		return sqlSession.selectList(namespace + ".siteList", vo);
	}
	
	@Override
	public List<DashboardVO> siteNameList(DashboardVO vo) throws Exception {

		return sqlSession.selectList(namespace + ".siteNameList", vo);
	}	
	 
	@Override
	public List<DashboardVO> siteMeasureList(DashboardVO vo) throws Exception {

		return sqlSession.selectList(namespace + ".siteMeasureList", vo);
	}
	
	@Override
	public List<DashboardVO> siteMeasureHourList(DashboardVO vo) throws Exception {

		return sqlSession.selectList(namespace + ".siteMeasureHourList", vo);
	}
	
	@Override
	public List<DashboardVO> measureStatusList(DashboardVO vo) throws Exception {

		return sqlSession.selectList(namespace + ".measureStatusList", vo);
	}	

	@Override
	public int insertSiteInfo(DashboardVO vo) throws Exception {

		return sqlSession.insert(namespace + ".insertSiteInfo", vo);
	}	
	
	@Override
	public int deleteSiteInfo(DashboardVO vo) throws Exception {

		return sqlSession.delete(namespace + ".deleteSiteInfo", vo);
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
}
