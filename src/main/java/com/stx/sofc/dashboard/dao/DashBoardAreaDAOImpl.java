package com.stx.sofc.dashboard.dao;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.stx.sofc.dashboard.vo.DashboardVO;

@Repository
public class DashBoardAreaDAOImpl implements DashBoardAreaDAO{

	@Inject
	private SqlSession sqlSession;
	
	private static String namespace = "com.stx.sofc.mappers.dashboard.area";
	
	@Override
	public List<DashboardVO> areaEffiGraph(DashboardVO vo) throws Exception {

		return sqlSession.selectList(namespace + ".areaEffiGraph", vo);
	}
	
	@Override
	public DashboardVO areaMeasure(DashboardVO vo) throws Exception {

		return sqlSession.selectOne(namespace + ".areaMeasure", vo);
	}
	
	@Override
	public List<DashboardVO> areaList(DashboardVO vo) throws Exception {

		return sqlSession.selectList(namespace + ".areaList", vo);
	}
	
	@Override
	public List<DashboardVO> areaNameList(DashboardVO vo) throws Exception {

		return sqlSession.selectList(namespace + ".areaNameList", vo);
	}
	
	@Override
	public List<DashboardVO> areaMeasureList(DashboardVO vo) throws Exception {

		return sqlSession.selectList(namespace + ".areaMeasureList", vo);
	}
	
	@Override
	public List<DashboardVO> areaMeasureHourList(DashboardVO vo) throws Exception {

		return sqlSession.selectList(namespace + ".areaMeasureHourList", vo);
	}
	
	@Override
	public int insertAreaInfo(DashboardVO vo) throws Exception {

		return sqlSession.insert(namespace + ".insertAreaInfo", vo);
	}	
	
	@Override
	public int deleteAreaInfo(DashboardVO vo) throws Exception {

		return sqlSession.delete(namespace + ".deleteAreaInfo", vo);
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
}
