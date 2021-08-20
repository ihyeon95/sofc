package com.stx.sofc.dashboard.dao;

import com.stx.sofc.dashboard.vo.EmailVO;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import javax.inject.Inject;
import java.util.List;

@Repository
public class DashBoardAdminDAOImpl implements DashBoardAdminDAO{

	@Inject
	private SqlSession sqlSession;
	
	private static String namespace = "com.stx.sofc.mappers.admin";

	@Override
	public List<EmailVO> emailList() throws Exception {

		return sqlSession.selectList(namespace + ".emailList");
	}

	@Override
	public int insertEmail(EmailVO vo) throws Exception {

		return sqlSession.insert(namespace + ".insertEmail", vo);
	}


}
