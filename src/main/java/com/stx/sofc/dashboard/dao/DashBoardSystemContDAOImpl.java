package com.stx.sofc.dashboard.dao;

import java.util.List;

import javax.inject.Inject;

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
	public List<SystemContVO> systemMeasureExcelDownload(SystemContVO vo) throws Exception {

		return sqlSession.selectList(namespace + ".systemMeasureExcelDownload", vo);
	}	
	
}
