package com.stx.sofc.dashboard.dao;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.stx.sofc.dashboard.vo.DashboardVO;
import com.stx.sofc.dashboard.vo.EquipInfoVO;
import com.stx.sofc.dashboard.vo.InstallInfoVO;

@Repository
public class DashBoardSystemDetailDAOImpl implements DashBoardSystemDetailDAO{

	@Inject
	private SqlSession sqlSession;
	
	private static String namespace = "com.stx.sofc.mappers.dashboard.systemDetail";	
	
//	@Override
//	public String systemDetailEffi(DashboardVO vo) throws Exception {
//
//		return sqlSession.selectOne(namespace + ".systemDetailEffi", vo);
//	}
	
	@Override
	public DashboardVO systemDetailMeasure(DashboardVO vo) throws Exception {

		return sqlSession.selectOne(namespace + ".systemDetailMeasure", vo);
	}	
	
	
	@Override
	public List<DashboardVO> systemDetailMeasureList(DashboardVO vo) throws Exception {

		return sqlSession.selectList(namespace + ".systemDetailMeasureList", vo);
	}
	
	@Override
	public List<DashboardVO> systemDetailMeasureHourList(DashboardVO vo) throws Exception {

		return sqlSession.selectList(namespace + ".systemDetailMeasureHourList", vo);
	}
	
	@Override
	public List<InstallInfoVO> installInfo(InstallInfoVO vo) throws Exception {

		return sqlSession.selectList(namespace + ".installInfo", vo);
	}	
	
	@Override
	public int insertInstallInfo(InstallInfoVO vo) throws Exception {

		return sqlSession.insert(namespace + ".insertInstallInfo", vo);
	}

	@Override
	public int deleteInstallInfo(DashboardVO vo) throws Exception {

		return sqlSession.delete(namespace + ".deleteInstallInfo", vo);
	}
	
	@Override
	public List<EquipInfoVO> equipInfo(EquipInfoVO vo) throws Exception {

		return sqlSession.selectList(namespace + ".equipInfo", vo);
	}	
	
	@Override
	public int insertEquipInfo(EquipInfoVO vo) throws Exception {

		return sqlSession.insert(namespace + ".insertEquipInfo", vo);
	}
	
	@Override
	public int deleteEquipInfo(DashboardVO vo) throws Exception {

		return sqlSession.delete(namespace + ".deleteEquipInfo", vo);
	}
	
	@Override
	public List<DashboardVO> systemInfo(DashboardVO vo) throws Exception {

		return sqlSession.selectList(namespace + ".systemInfo", vo);
	}	
	
	@Override
	public int updateSystemInfo(DashboardVO vo) throws Exception {

		return sqlSession.insert(namespace + ".updateSystemInfo", vo);
	}
	
	@Override
	public int systemCnt(DashboardVO vo) throws Exception {

		return sqlSession.selectOne(namespace + ".systemCnt", vo);
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
