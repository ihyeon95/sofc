package com.stx.sofc.dashboard.dao;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import com.stx.sofc.dashboard.vo.DashboardVO;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.stx.sofc.dashboard.vo.SystemContVO;

@Repository
public class DashBoardSystemContDAOImpl implements DashBoardSystemContDAO{

	@Inject
	private SqlSession sqlSession;
	
	private static String namespace = "com.stx.sofc.mappers.dashboard.systemCont";	
		
	@Override
	public SystemContVO systemContMeasure(SystemContVO vo) throws Exception {

		return sqlSession.selectOne(namespace + ".systemContMeasure", vo);
	}

	@Override
	public int selectIRemoteStatus(DashboardVO vo) throws Exception {

		return sqlSession.selectOne(namespace + ".selectIRemoteStatus", vo);
	}
	
	@Override
	public List<SystemContVO> systemMeasureExcelDownload(SystemContVO vo) throws Exception {

		return sqlSession.selectList(namespace + ".systemMeasureExcelDownload", vo);
	}

	@Override
	public List<Map<String, BigDecimal>> selectPreAccumulateWattProduce(SystemContVO vo) throws Exception {
		return sqlSession.selectList(namespace + ".selectPreAccumulateWattProduce", vo);
	}
	
}
