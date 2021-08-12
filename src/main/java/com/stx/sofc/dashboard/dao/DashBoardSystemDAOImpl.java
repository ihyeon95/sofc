package com.stx.sofc.dashboard.dao;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.stx.sofc.dashboard.vo.DashboardVO;

@Repository
public class DashBoardSystemDAOImpl implements DashBoardSystemDAO{

	@Inject
	private SqlSession sqlSession;
	
	private static String namespace = "com.stx.sofc.mappers.dashboard.system";
	
	@Override
	public DashboardVO systemMeasure(DashboardVO vo) throws Exception {

		return sqlSession.selectOne(namespace + ".systemMeasure", vo);
	}
	
	@Override
	public List<DashboardVO> measureStatusList(DashboardVO vo) throws Exception {

		return sqlSession.selectList(namespace + ".measureStatusList", vo);
	}	

	@Override
	public List<DashboardVO> systemList(DashboardVO vo) throws Exception {

		return sqlSession.selectList(namespace + ".systemList", vo);
	}

	
	@Override
	public int systemListCnt(DashboardVO vo) throws Exception {

		return sqlSession.selectOne(namespace + ".systemListCnt", vo);
	}	

	@Override
	public List<DashboardVO> systemMeasureList(DashboardVO vo) throws Exception {

		return sqlSession.selectList(namespace + ".systemMeasureList", vo);
	}

	@Override
	public int systemEffi(DashboardVO vo) throws Exception {

		return sqlSession.selectOne(namespace + ".systemEffi", vo);
	}	
	
	@Override
	public List<DashboardVO> systemMeasureHourList(DashboardVO vo) throws Exception {

		return sqlSession.selectList(namespace + ".systemMeasureHourList", vo);
	}
	
	@Override
	public List<DashboardVO> audiInfo(DashboardVO vo) throws Exception {

		return sqlSession.selectList(namespace + ".audiInfo", vo);
	}	
	
	@Override
	public int insertAudiInfo(DashboardVO vo) throws Exception {

		return sqlSession.insert(namespace + ".insertAudiInfo", vo);
	}
	
	@Override
	public int deleteAudiInfo(DashboardVO vo) throws Exception {

		return sqlSession.delete(namespace + ".deleteAudiInfo", vo);
	}

	@Override
	public int insertSystemInfo(DashboardVO vo) throws Exception {

		return sqlSession.insert(namespace + ".insertSystemInfo", vo);
	}	
	
	@Override
	public int deleteSystemInfo(DashboardVO vo) throws Exception {

		return sqlSession.delete(namespace + ".deleteSystemInfo", vo);
	}	
	
	@Override
	public List<DashboardVO> systemRtuIdList(DashboardVO vo) throws Exception {

		return sqlSession.selectList(namespace + ".systemRtuIdList", vo);
	}
	
	@Override
	public String tdAccumulateWattProduceAvg(DashboardVO vo) throws Exception {
		
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
