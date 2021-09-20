<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="x" uri="http://java.sun.com/jsp/jstl/xml"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<script type="text/javascript">
	$(function(){
		//--페이지 셋팅
		var totalPage1 = ${totalPage1}; //전체 페이지
		var startPage1 = ${startPage1}; //현재 페이지

		var pagination1 = '<nav aria-label=""><ul class="pagination justify-content-end">';

		//--페이지네이션에 항상 10개가 보이도록 조절
		var forStart1 = 0;
		var forEnd1 = 0;

		if ((startPage1 - 5) < 1) {
			forStart1 = 1;
		} else {
			forStart1 = startPage1 - 5;
		}

		if (forStart1 == 1) {
			if (totalPage1 > 9) {
				forEnd1 = 10;
			} else {
				forEnd1 = totalPage1;
			}
		} else {
			if ((startPage1 + 4) > totalPage1) {
				forEnd1 = totalPage1;

				if (forEnd1 > 9) {
					forStart1 = forEnd1 - 9
				}

			} else {
				forEnd1 = startPage1 + 4;
			}
		}
		//--페이지네이션에 항상 10개가 보이도록 조절

		if(totalPage1 >= 1){
			
			var prePage1 = startPage1 - 10;
			
			if(prePage1 < 1){
				prePage1 = 1;
			}

			/* pagination1 += '<li class="mr-3"><a href="javascript:pageMove1('+prePage1+');"><i class="fa fa-chevron-left mt-3"></i></a></li>'; */
			
			if(startPage1 >= 2){

				pagination1 += '<li class="page-item "><a class="page-link" href="javascript:pageMove1('+prePage1+');">←</a></li>'
			}else{

				pagination1 += '<li class="page-item disabled"><a class="page-link" href="" tabindex="-1">←</a></li>'
			}
		}
		
		//전체 페이지 수를 받아 돌린다.
		for (var i = forStart1; i <= forEnd1; i++) {
			if (startPage1 == i) {				
				pagination1 += '<li class="page-item active"><a class="page-link" href="#">' + i + ' <span class="sr-only">(current)</span></a></li>';
			} else {
				pagination1 += '<li class="page-item"><a class="page-link" href="javascript:pageMove1(' +i+ ')">' + i + '</a></li>';
			}
		}

		if(totalPage1 >= 1){
			
			var nextPage1 = startPage1 + 10;
			
			if(totalPage1 < nextPage1){
				nextPage1 = totalPage1;
			}
			
			/* pagination1 += '<li class="ml-3"><a href="javascript:pageMove1('+nextPage1+');"><i class="fa fa-chevron-right mt-3"></i></a></li>'; */
			
			if(startPage1 != totalPage1){
				pagination1 += '<li class="page-item"><a class="page-link" href="javascript:pageMove1('+nextPage1+');">→</a></li>';
			}else{
				pagination1 += '<li class="page-item disabled"><a class="page-link" href="" tabindex="-1">→</a></li>';
			}
		}
		
		pagination1 += "</ul></nav>";
		
		//하단 페이지 부분에 붙인다.
		$("#list_paging1").append(pagination1);
		//--페이지 셋팅
	})
	
	function pageMove1(page){
		var visiblePages1 = 5;//리스트 보여줄 페이지
		var totalPage1 = ${totalPage1};
		
		if(totalPage1 < page){
			$('#startPage1').val(totalPage1);//보고 싶은 페이지
			
		}else if(page <= 0){
			$('#startPage1').val(1);//보고 싶은 페이지
		}else{
			$('#startPage1').val(page);//보고 싶은 페이지
		}
		$('#visiblePages1').val(visiblePages1);	
		
		eventList.list();
		
	}
	
</script>

<div class="table-responsive" style="height:450px;">
    <table class="table" >
    	<colgroup>
            <col width="30%">
            <col width="30%">
            <col width="*">
        </colgroup> 
        <thead class="bg-dark">
            <tr class="text-white" style="text-align:center;">
                <th scope="col">일자</th>
                <th scope="col">시스템</th>
                <th scope="col">로그</th>
            </tr>
        </thead>
        <tbody>
        	<c:choose>
				<c:when test="${fn:length(eventList) == 0}">
					<tr>
						<td colspan="3" class="blank_area text-center type_white">
							조회된 데이터가 없습니다.
						</td>
					</tr>
				</c:when>
				<c:otherwise>
					<c:forEach var="eventList" items="${eventList}" varStatus="status">
						<tr>
							<th class="text-center" scope="row">
								<fmt:parseDate var="trsfDttm" value="${eventList.eventDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
								<fmt:formatDate value="${trsfDttm }" pattern="yyyy-MM-dd HH:mm"/>
							</th>
							<td class="text-center">
								<c:out value="${eventList.sSiteName}"/><br>
								<c:out value="${eventList.sSystemName}"/>
							</td>
							<td class="text-center">
							<!-- <td style="text-align:left; word-break: keep-all;"> -->
								비정상
								<%-- <c:out value="${eventList.sMessage}"/> --%>
							</td>
						</tr>
					</c:forEach>
				</c:otherwise>
			</c:choose>
        </tbody>
    </table>
</div>

<div id="list_paging1">
	
</div>

