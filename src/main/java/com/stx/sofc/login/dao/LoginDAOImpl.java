package com.stx.sofc.login.dao;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.stx.sofc.login.vo.LoginVO;

@Repository
public class LoginDAOImpl implements LoginDAO{

	@Inject
	private SqlSession sqlSession;
	
	private static String namespace = "com.stx.sofc.mappers.login";	
	
	 
	@Override
	public int authInfoCnt(LoginVO vo) throws Exception {

		return sqlSession.selectOne(namespace + ".authInfoCnt", vo);
	}
	
	@Override
	public int authInfoPwdCnt(LoginVO vo) throws Exception {

		return sqlSession.selectOne(namespace + ".authInfoPwdCnt", vo);
	}
	
	@Override
	public LoginVO loginProcess(LoginVO vo) throws Exception {

		return sqlSession.selectOne(namespace + ".loginProcess", vo);
	}	
	
}
