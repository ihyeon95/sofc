<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="x" uri="http://java.sun.com/jsp/jstl/xml"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<script type="text/javascript">
	$(function(){
		//--페이지 셋팅
		var totalPage = ${totalPage}; //전체 페이지
		var startPage = ${startPage}; //현재 페이지

		var pagination = '<nav aria-label=""><ul class="pagination justify-content-end">';

		//--페이지네이션에 항상 10개가 보이도록 조절
		var forStart = 0;
		var forEnd = 0;

		if ((startPage - 5) < 1) {
			forStart = 1;
		} else {
			forStart = startPage - 5;
		}

		if (forStart == 1) {
			if (totalPage > 9) {
				forEnd = 10;
			} else {
				forEnd = totalPage;
			}
		} else {
			if ((startPage + 4) > totalPage) {
				forEnd = totalPage;

				if (forEnd > 9) {
					forStart = forEnd - 9
				}

			} else {
				forEnd = startPage + 4;
			}
		}
		//--페이지네이션에 항상 10개가 보이도록 조절

		if(totalPage >= 1){
			
			var prePage = startPage - 10;
			
			if(prePage < 1){
				prePage = 1;
			}
			
			/* pagination += '<li class="mr-3"><a href="javascript:pageMove('+prePage+');"><i class="fa fa-chevron-left mt-3"></i></a></li>'; */
			
			if(startPage >= 2){
				pagination += '<li class="page-item "><a class="page-link" href="javascript:pageMove('+prePage+');">Previous</a></li>'
			}else{
				pagination += '<li class="page-item disabled"><a class="page-link" href="" tabindex="-1">Previous</a></li>'
			}
		}
		
		//전체 페이지 수를 받아 돌린다.
		for (var i = forStart; i <= forEnd; i++) {
			if (startPage == i) {				
				pagination += '<li class="page-item active"><a class="page-link" href="#">' + i + ' <span class="sr-only">(current)</span></a></li>';
			} else {
				pagination += '<li class="page-item"><a class="page-link" href="javascript:pageMove(' +i+ ')">' + i + '</a></li>';
			}
		}

		if(totalPage >= 1){
			
			var nextPage = startPage + 10;
			
			if(totalPage < nextPage){
				nextPage = totalPage;
			}
			
			/* pagination += '<li class="ml-3"><a href="javascript:pageMove('+nextPage+');"><i class="fa fa-chevron-right mt-3"></i></a></li>'; */
			
			if(startPage != totalPage){
				pagination += '<li class="page-item"><a class="page-link" href="javascript:pageMove('+nextPage+');">Next</a></li>';
			}else{
				pagination += '<li class="page-item disabled"><a class="page-link" href="" tabindex="-1">Next</a></li>';
			}
		}
		
		pagination += "</ul></nav>";
		
		//하단 페이지 부분에 붙인다.
		$("#list_paging").append(pagination);
		//--페이지 셋팅
	})
	
	function pageMove(page){
		var visiblePages = 6;//리스트 보여줄 페이지
		var totalPage = ${totalPage};
		
		if(totalPage < page){
			$('#startPage').val(totalPage);//보고 싶은 페이지
			
		}else if(page <= 0){
			$('#startPage').val(1);//보고 싶은 페이지
		}else{
			$('#startPage').val(page);//보고 싶은 페이지
		}
		$('#visiblePages').val(visiblePages);	
		
		systemList.list();
		
	}
	
	function fnClickSystem(iSysNum, sSystemName, iRtuNum, iBdNum){
		
		var sAuth = $("#sAuth").val();
		
		$("#iSysNum").val(iSysNum);
		$("#sSystemName").val(sSystemName);
		$("#iRtuNum").val(iRtuNum);
		$("#iBdNum").val(iBdNum);
		
		if(sAuth == "ROLE_SU"){
			$("#systemForm").attr({action:'<c:url value="/dashboard/systemCont/mng"/>', method:'post'}).submit();	
		}else{
			$("#systemForm").attr({action:'<c:url value="/dashboard/systemDetail/mng"/>', method:'post'}).submit();
		}
	}
</script>

<div class="pull-left">
	<!-- <button type="button" class="btn btn-info btn-xs mb-3" data-toggle="modal" data-target="#systemInfoModal">추가</button> -->
	<button type="button" class="btn btn-info btn-xs mb-3" onclick="javascript:fnAddSystem();">추가</button>
</div>
<div class="mr-0 pull-right">
	<button type="button" class="btn btn-info btn-xs mb-3" onclick="javascript:fnClickAudiInfo();" >수용가 정보 입력</button>
</div>

<div class="table-responsive"  style="height:350px;">
    <table class="table text-center ">
        <thead class="bg-dark">
            <tr class="text-white">
                <th scope="col">이름</th>
                <th scope="col">개소</th>
                <th scope="col">설비용량[kW]</th>
                <th scope="col">이용률[%]</th>
                <th scope="col">action</th>
            </tr>
        </thead>
        <tbody>
        	<c:choose>
				<c:when test="${fn:length(systemList) == 0}">
					<tr>
						<td colspan="5" class="blank_area type_white">
							조회된 데이터가 없습니다.
						</td>
					</tr>
				</c:when>
				<c:otherwise>
					<c:forEach var="systemList" items="${systemList}" varStatus="status">
						<tr>
							<th scope="row" style="word-break: keep-all;"><a href="javascript:fnClickSystem(${systemList.iSysNum}, '${systemList.sSystemName}', ${systemList.iRtuNum}, ${systemList.iBdNum});"><c:out value="${systemList.sSystemName}"/></a></th>
							<td><c:out value="${systemList.sSysCnt}"/></td>
							<td><c:out value="${systemList.fCapa}"/></td>
							<td><fmt:formatNumber value="${systemList.tdEfficiency}" pattern="###"/></td>
							<td><i class="ti-trash" onclick="fnDeleteSystemInfoCheck(${systemList.iSysNum}, '${systemList.sSystemName}');"></i></td>
						</tr>
					</c:forEach>
				</c:otherwise>
			</c:choose>
        </tbody>
    </table>
</div>

<div id="list_paging">
	
</div>

