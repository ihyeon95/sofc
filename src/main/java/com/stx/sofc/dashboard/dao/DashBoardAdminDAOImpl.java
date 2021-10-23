package com.stx.sofc.dashboard.dao;

import com.stx.sofc.dashboard.vo.DashboardVO;
import com.stx.sofc.dashboard.vo.EmailVO;
import com.stx.sofc.dashboard.vo.excelVo;
import com.stx.sofc.dashboard.vo.guestVo;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import javax.inject.Inject;
import java.util.ArrayList;
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

	@Override
	public int deleteEmail(List<Integer> checkedEmailList) throws Exception {

		return sqlSession.delete(namespace + ".deleteEmail", checkedEmailList);
	}

	@Override
	public List<guestVo> guestList() throws Exception {

		return sqlSession.selectList(namespace + ".guestList");
	}

	@Override
	public int insertGuest(guestVo vo) throws Exception{
		return sqlSession.insert(namespace + ".insertGuest", vo);
	}

	@Override
	public int insertExcel(guestVo vo) throws Exception{
		return sqlSession.insert(namespace + ".insertExcel", vo);
	}

	@Override
	public int deleteGuest(List<String> checkedGuestList) throws Exception{
		return sqlSession.delete(namespace + ".deleteGuest", checkedGuestList);
	}

	@Override
	public int deleteExcel(List<String> checkedGuestList) throws Exception{
		return sqlSession.delete(namespace + ".deleteExcel", checkedGuestList);
	}

	@Override
	public List<excelVo> excelList(String sUserId) throws Exception {

		return sqlSession.selectList(namespace + ".excelList", sUserId);
	}

	@Override
	public int updateExcel(excelVo vo) throws Exception{
		return sqlSession.update(namespace + ".updateExcel", vo);
	}

	@Override
	public List<DashboardVO> cityNameList() throws Exception{
		return sqlSession.selectList(namespace + ".cityNameList");
	}

	@Override
	public List<DashboardVO> areaNameList(String iCityNum) throws Exception{
		return sqlSession.selectList(namespace + ".areaNameList", iCityNum);
	}

	@Override
	public List<DashboardVO> siteNameList(DashboardVO vo) throws Exception{
		return sqlSession.selectList(namespace + ".siteNameList", vo);
	}

}
