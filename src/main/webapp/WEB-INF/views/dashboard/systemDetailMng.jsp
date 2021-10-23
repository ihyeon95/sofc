<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%  
	response.setHeader("Cache-Control","no-store");  
	response.setHeader("Pragma","no-cache");  
	response.setDateHeader("Expires",0);  
	if (request.getProtocol().equals("HTTP/1.1"))
        response.setHeader("Cache-Control", "no-cache");
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="x" uri="http://java.sun.com/jsp/jstl/xml"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<html>

<head>
    <meta charset="utf-8">
	<meta http-equiv="x-ua-compatible" content="ie=edge">
    <title>STX중공업 SOFC 모니터링 시스템</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    
    <!-- jquery latest version -->
    <script src="/resources/assets/js/vendor/jquery-2.2.4.min.js"></script>
    
    <link rel="stylesheet" href="/resources/assets/css/bootstrap.min.css">
    <link rel="stylesheet" href="/resources/assets/css/font-awesome.min.css">

	<!-- s:toast-ui -->    
    <link rel='stylesheet' type='text/css' href='/resources/assets/dist/tui-chart.css'/>
    <link rel='stylesheet' type='text/css' href='/resources/assets/css/codemirror.css'/>
    <link rel='stylesheet' type='text/css' href='/resources/assets/css/lint.css'/>
    <link rel='stylesheet' type='text/css' href='/resources/assets/css/neo.css'/>
    <!-- <link rel='stylesheet' type='text/css' href='./css/example.css'/> -->
    <!-- e:toast-ui -->
    
    <link rel="stylesheet" href="/resources/assets/css/export.css" type="text/css" media="all" />
    <!-- others css -->
    
    <link rel="stylesheet" href="/resources/assets/css/themify-icons.css">
    <link rel="stylesheet" href="/resources/assets/css/default-css.css">
    <link rel="stylesheet" href="/resources/assets/css/styles.css">
    <link rel="stylesheet" href="/resources/assets/css/responsive.css">

	<!-- custom css -->
	<link rel="stylesheet" href="/resources/assets/css/common.css">
    
</head>

<body>
	<!-- Modal -->
	<div class="modal fade" id="installInfoModal">
		<div class="modal-dialog modal-dialog-centered" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h4 class="modal-title">발전원 설치 정보</h4>
					<button type="button" class="close" data-dismiss="modal"><span>&times;</span></button>
				</div>
				<div class="modal-body">
					<div class="form-row">
						<div class="col-md-4 mb-3">
						    <label>시스템명</label>
						    <br>
						    <label class="header-title">${sSystemName}</label>
						</div>
						<div class="col-md-4 mb-3">
						    
						</div>
						<div class="col-md-4 mb-3">
						    
						</div>
						
						<div class="col-md-4 mb-3">
						    <label for="sSystemLoca" class="col-form-label">설치 위치</label>
						    <input class="form-control" type="text" value="" id="sSystemLoca">
						</div>
						<div class="col-md-4 mb-3">
						    <label for="sPreCheckDate" class="col-form-label">사용전 검사일</label>
						    <input class="form-control" type="text" value="" id="sPreCheckDate">
						</div>
						<div class="col-md-4 mb-3">
						    <label for="sConnect" class="col-form-label">계통 접속</label>
						    <input class="form-control" type="text" value="" id="sConnect">
						</div>
						<div class="col-md-4 mb-3">
						    <label for="sConfirmDate" class="col-form-label">설치 확인일</label>
						    <input class="form-control" type="text" value="" id="sConfirmDate">
						</div>
						<div class="col-md-4 mb-3">
						    <label for="sInstallDate" class="col-form-label">최초 설치일</label>
						    <input class="form-control" type="text" value="" id="sInstallDate">
						</div>
						<div class="col-md-4 mb-3">
						    <label for="sExpireDate" class="col-form-label">AS 만료일</label>
						    <input class="form-control" type="text" value="" id="sExpireDate">
						</div>
					</div>
	            </div>
				<div class="modal-footer">
					<button type="button" class="btn btn-secondary" data-dismiss="modal">취소</button>
					<button type="button" class="btn btn-secondary" onclick="javascript:fnInstallInfoClear();">초기화</button>
					<button type="button" class="btn btn-primary" onclick="javascript:fnInstallInfoInsert();">저장</button>
				</div>
			</div>
		</div>
	</div>
	<div class="modal fade" id="equipInfoModal">
		<div class="modal-dialog modal-dialog-centered" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h4 class="modal-title">발전 설비 제원</h4>
					<button type="button" class="close" data-dismiss="modal"><span>&times;</span></button>
				</div>
				<div class="modal-body">
					<div class="form-row">
						<div class="col-md-4 mb-3">
						    <label>시스템명</label>
						    <br>
						    <label class="header-title">${sSystemName}</label>
						</div>
						<div class="col-md-4 mb-3">
						</div>
						<div class="col-md-4 mb-3">
						</div>
						
						<div class="col-md-4 mb-3">
						    <label for="iInverterCapa" class="col-form-label">설비용량[KW]</label>
						    <input class="form-control" type="text" value="" id="iInverterCapa">
						</div>
						<div class="col-md-4 mb-3">
						    <label for="sInverterMFR" class="col-form-label">인버터-제조사</label>
						    <input class="form-control" type="text" value="" id="sInverterMFR">
						</div>
						<div class="col-md-4 mb-3">
						    <label for="sInverterName" class="col-form-label">인버터-모델명</label>
						    <input class="form-control" type="text" value="" id="sInverterName">
						</div>
						
						<div class="col-md-12 mt-3">
						    <label class="header-title">AS 출장 내역</label>
						</div>
						<div class="col-md-4 mb-3">
						    <label for="sAsDate" class="col-form-label">AS 출장 일자</label>
						    <input class="form-control" type="text" value="" id="sAsDate">
						</div>
						<div class="col-md-8 mb-3">
						    <label for="sAsReason" class="col-form-label">AS 출장 사유</label>
						    <input class="form-control" type="text" value="" id="sAsReason">
						</div>
						
					</div>
	            </div>
				<div class="modal-footer">
					<button type="button" class="btn btn-secondary" data-dismiss="modal">취소</button>
					<button type="button" class="btn btn-secondary" onclick="javascript:fnEquipInfoClear();">초기화</button>
					<button type="button" class="btn btn-primary" onclick="javascript:fnEquipInfoInsert();">저장</button>
				</div>
			</div>
		</div>
	</div>
	<!-- Modal -->
	<div class="modal fade" id="systemInfoModal">
		<div class="modal-dialog modal-dialog-centered" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title">시스템 수정</h5>
					<button type="button" class="close" data-dismiss="modal"><span>&times;</span></button>
				</div>
				<div class="modal-body">
					<div class="form-row">
						<div class="col-md-4 mb-3">
						    <label for="iRtuNumModal" class="col-form-label">Rtu 번호</label>
						    <!-- <input class="form-control" type="text" value="" id="iRtuNumModal"> -->
						    <span id="iRtuNumModal" class="form-control"></span>
						</div>
						<div class="col-md-4 mb-3">
						    <label for="iBdNumModal" class="col-form-label">Board 번호</label>
						    <!-- <input class="form-control" type="text" value="" id="iBdNumModal"> -->
						    <span id="iBdNumModal" class="form-control"></span>
						</div>
						<div class="col-md-4 mb-3">
						    <label for="sSystemNameModal" class="col-form-label">시스템이름</label>
						    <input class="form-control" type="text" value="" id="sSystemNameModal">
						</div>
						<div class="col-md-4 mb-3">
						    <label for="fCapa" class="col-form-label">설비용량</label>
						    <input class="form-control" type="text" value="" id="fCapa">
						</div>
						<div class="col-md-4 mb-3">
						    <label for="fCapaGeo" class="col-form-label">지열 설비용량</label>
						    <input class="form-control" type="text" value="" id="fCapaGeo">
						</div>
						<div class="col-md-4 mb-3">
						    <label for="fCapaSolar" class="col-form-label">태양열 설비용량</label>
						    <input class="form-control" type="text" value="" id="fCapaSolar">
						</div>
						<div class="col-md-6 mb-3">
						    <label for="fLhv" class="col-form-label">LHV</label>
						    <input class="form-control" type="text" value="" id="fLhv">
						</div>
						<div class="col-md-6 mb-3">
						    <label for="fCO2" class="col-form-label">CO2 저감량</label>
						    <input class="form-control" type="text" value="" id="fCO2">
						</div>
					</div>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-secondary" data-dismiss="modal">취소</button>
					<button type="button" class="btn btn-primary" onclick="javascript:fnSystemInfoUpdate();">저장</button>
				</div>
			</div>
		</div>
	</div>

	<input type="hidden" id="startPage1" name="startPage1" value="1">	
	<input type="hidden" id="visiblePages1" name="visiblePages1" value="5">
	
	<form id="systemDetailForm">
		<input type="hidden" id="iCityNum" name="iCityNum" value="${iCityNum}">
		<input type="hidden" id="sCityName" name="sCityName" value="${sCityName}">
		<input type="hidden" id="iAreaNum" name="iAreaNum" value="${iAreaNum}">
		<input type="hidden" id="sAreaName" name="sAreaName" value="${sAreaName}">
		<input type="hidden" id="iSiteNum" name="iSiteNum" value="${iSiteNum}">
		<input type="hidden" id="sSiteName" name="sSiteName" value="${sSiteName}">
		<input type="hidden" id="sAuth" name="sAuth" value="${sAuth}">
		<input type="hidden" id="iSysNum" name="iSysNum" value="${iSysNum}">
		<input type="hidden" id="sSystemName" name="sSystemName" value="${sSystemName}">
		<input type="hidden" id="iRtuNum" name="iRtuNum" value="${iRtuNum}">
		<input type="hidden" id="iBdNum" name="iBdNum" value="${iBdNum}">
	</form>
		<!-- preloader area start -->
		<div id="preloader">
		    <div class="loader"></div>
		</div>
		<!-- preloader area end -->
		<!-- page container area start -->
		<div class="page-container sbar_collapsed">
		    
		    <!-- main content area start -->
			<div class="main-content">
			    
				<!-- header area end -->
				<!-- page title area start -->
				<div id="top_menu">
					<h4 class="page-title pull-left mt-2">시스템 화면</h4>
<%--					<ul class="breadcrumbs pull-left mt-2">--%>
<%--						<c:choose>--%>
<%--							<c:when test="${sAuth eq 'ROLE_SU'}">--%>
<%--								<li><a href="javascript:fnClickMain();">전국</a></li>--%>
<%--								<li><a href="javascript:fnClickCity();">${sCityName}</a></li>--%>
<%--								<li><a href="javascript:fnClickArea();">${sAreaName}</a></li>--%>
<%--								<li><a href="javascript:fnClickSite();">${sSiteName}</a></li>--%>
<%--								&lt;%&ndash;											<li><a href="javascript:fnClickSystemCont();">${sSystemName} 제어</a></li>	&ndash;%&gt;--%>
<%--							</c:when>--%>
<%--							<c:otherwise>--%>
<%--								<li><a href="javascript:fnClickSite();">${sSiteName}</a></li>--%>
<%--							</c:otherwise>--%>
<%--						</c:choose>--%>
<%--						<li><span>${sSystemName}</span></li>--%>
<%--					</ul>--%>
					<ul class="right_menu">
						<li class="sign_out"><a href="/logoutProcess" class="btn_signout">LogOut</a></li>
					</ul>
				</div>

				<div id="content">
					<p class="breadcrumb_navi">
						<i class="fa fa-home fa-13x" aria-hidden="true"></i>&nbsp;
						<c:choose>
							<c:when test="${sAuth eq 'ROLE_SU'}">
								<a href="javascript:fnClickMain();">전국</a>&nbsp;&gt;&nbsp;
								<a href="javascript:fnClickCity();">${sCityName}</a>&nbsp;&gt;&nbsp;
								<a href="javascript:fnClickArea();">${sAreaName}</a>&nbsp;&gt;&nbsp;
								<a href="javascript:fnClickSite();">${sSiteName}</a>&nbsp;&gt;&nbsp;
							</c:when>
							<c:otherwise>
								<li><a href="javascript:fnClickSite();">${sSiteName}</a></li>&nbsp;&gt;&nbsp;
							</c:otherwise>
						</c:choose>
						<span class="current">${sSystemName}</span>
					</p>
					<%--					<p class="breadcrumb"><a href="#" class="home" title="Go Main">main</a> &gt; <span class="">장비관리</span> &gt; <span class="current">지역 설정</span></p>--%>
				</div>

				<!-- page title area end -->
				<div class="main-content-inner">
					<!-- sales report area start -->
					<div class="sales-report-area mb-5">
						<div class="row">
							<div class="col-md-2">
	                            <div>
	                                <button type="button" class="btn btn-secondary btn-lg btn-block" onclick="javascript:fnClickInstallInfo();">발전원 설치정보</button>
	                                <button type="button" class="btn btn-secondary btn-lg btn-block" onclick="javascript:fnClickEquipInfo();">발전 설비 제원</button>
	                                <button type="button" class="btn btn-secondary btn-lg btn-block" onclick="javascript:fnClickSystemInfo();">시스템 수정</button>
									<c:if test="${sAuth eq 'ROLE_SU'}">
										<button type="button" class="btn btn-secondary btn-lg btn-block" onclick="javascript:fnClickSystem();">제어기 화면</button>
									</c:if>
									<div>
										<c:set var="now" value="<%=new java.util.Date(new java.util.Date().getTime() - 60*60*24*1000) %>" />
										<!-- 일주일 전 JAVA DATE 계산을 하면된다.   -->
										<c:set var="tenDaysAgo" value="<%=new java.util.Date(new java.util.Date().getTime() - 60*60*24*1000*10) %>" />

										<c:set var="today"><fmt:formatDate value="${now}" pattern="yyyy-MM-dd" /></c:set>
										<c:set var="tenDaysAgo1"><fmt:formatDate value="${tenDaysAgo}" pattern="yyyy-MM-dd" /></c:set>

										<c:if test="${sAuth ne 'ROLE_SU'}">
											<div class="card">
												<div class="card-body">
													<h4 class="header-title">엑셀 다운로드</h4>
													<form id="form1" name="form1" method="post" >
														<input type="hidden" id="sUserId" name="sUserId" value="stxhi">
														<input type="hidden" id="rtuId" name="rtuId" value="${iRtuNum}">
														<input type="hidden" id="sSystemNameExcel" name="sSystemNameExcel" value="${sSystemName}">
														<div class="form">
															<div class="col-md-12">
																<label for="searchRequstDeBgn" class="col-form-label">시작일</label>
																<input class="form-control" type="date" value="<c:out value="${tenDaysAgo1}" />" id="searchRequstDeBgn" name="searchRequstDeBgn">
															</div>
															<div class="col-md-12">
																<label for="searchRequstDeEnd" class="col-form-label">종료일</label>
																<input class="form-control" type="date" value="<c:out value="${today}" />" id="searchRequstDeEnd" name="searchRequstDeEnd">
															</div>
														</div>
														<br>

														<button type="button" class="btn btn-secondary btn-lg btn-block" onclick="doExcelDownloadProcess()">다운로드</button>
													</form>
												</div>
											</div>
											<div id ="time" style="float: right; text-align: right;"></div>
										</c:if>
									</div>
	                            </div>
	                        </div>
							<div class="col-md-3">
							     <!-- overview area start -->
							   <div class="card">
							       <div class="card-body" style="overflow:auto;">
							           <div class="d-flex justify-content-between align-items-center mt-2">
							               <h4 class="header-title mb-0  mt-2">발전 효율[%]</h4>
							           </div>
							           <!-- <div id="ambarchart3" class="mt-3"></div> -->
							           <div id="effiChart"></div>
							       </div>
							   </div>
							   <!-- overview area end -->
							</div>
							<div class="col-md-5">
	                            <div class="card">
							       <div class="card-body" style="height: 500px;">
							           	<div>
											<img src="/resources/img/systemDetail.png">
							           </div>
							       </div>
							   </div>
	                        </div>
							<div class="col-md-2">
								<div class="card">
									<div class="card-body">
										<div class="trad-history">
											<div class="tab-content" id="myTabContent">
												<div class="tab-pane fade show active" id="buy_order" role="tabpanel">
													<div class="table-responsive">
														<ul class="dbkit-table1">
															<li>
																<div>금일 발전량</div>
																<div class="flRight amount" id ="tdAccumulateWattProduce1"></div>
															</li>
															<li>
																<div>금일 발전 효율</div>
																<div class="flRight amount" id ="tdEfficient"></div>
															</li>
															<li>
																<div>금일 사용열량</div>
																<div class="flRight amount" id ="tdAccumulateHeatConsume"></div>
															</li>
															<li>
																<div>전일 누적 발전량</div>
																<div class="flRight amount" id ="pdAccumulateWattProduce"></div>
															</li>
															<li>
																<div>누적 효율</div>
																<div class="flRight amount" id ="totEfficient"></div>
															</li>
														</ul>
													</div>
												</div>
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
					<!-- sales report area end -->
					
					<div class="sales-report-area mt-5 mb-5">
						<div class="row">
							<div class="col-md-2">
								<div class="card">
									<div class="card-body">
										<h4 class="header-title">전기에너지</h4>
										<div class="trad-history">
											<div class="tab-content" id="myTabContent">
												<div class="tab-pane fade show active" id="buy_order" role="tabpanel">
													<div class="table-responsive">
														<ul class="dbkit-table1">
															<li>
																<div>금일</br>발전량</div>
																<div class="flRight amount" id ="tdAccumulateWattProduce2"></div>
															</li>
															<li>
																<div>금일</br>CO2 저감량</div>
																<div class="flRight amount" id ="tdWattReduction"></div>
															</li>
															<li>
																<div>설비용량</div>
																<div class="flRight amount" id ="fCapaWatt"></div>
															</li>
															<li>
																<div>누적</br>발전량</div>
																<div class="flRight amount" id ="tdAccumulateWattProduce3"></div>
															</li>
														</ul>
													</div>
												</div>
											</div>
										</div>
									</div>
								</div>
							</div>
							<div class="col-md-2">
								 <div class="card">
									<div class="card-body">
										<h4 class="header-title">열 에너지</h4>
										<div class="trad-history">
											<div class="tab-content" id="myTabContent">
												<div class="tab-pane fade show active" id="buy_order" role="tabpanel">
													<div class="table-responsive">
														<ul class="dbkit-table1">
															<li>
																<div>금일</br>생산량</div>
																<div class="flRight amount" id ="pdAccumulateHeatProduce1"></div>
															</li>
															<li>
																<div>금일</br>CO2 저감량</div>
																<div class="flRight amount" id ="tdHeatReduction"></div>
															</li>
															<li>
																<div>설비용량</div>
																<div class="flRight amount" id ="fCapaHeat"></div>
															</li>
															<li>
																<div>누적</br>생산량</div>
																<div class="flRight amount" id ="pdAccumulateHeatProduce2"></div>
															</li>
														</ul>
													</div>
												</div>
											</div>
										</div>
									</div>
								</div>
							</div>
							<div class="col-md-2">
								<div class="card">
									<div class="card-body">
										<h4 class="header-title">시스템 운영현황</h4>
										<div class="trad-history">
											<div class="tab-content" id="myTabContent">
												<div class="tab-pane fade show active" id="buy_order" role="tabpanel">
													<div class="table-responsive">
														<ul class="dbkit-table1">
															<li>
																<div>정상</div>
																<div class="flRight amount" id ="iNormal"></div>
															</li>
															<li>
																<div>대기</div>
																<div class="flRight amount" id ="iWait"></div>
															</li>
															<li>
																<div>고장</div>
																<div class="flRight amount" id ="iBreakDown"></div>
															</li>
															<li>
																<div>준비중</div>
																<div class="flRight amount" id ="iPreparing"></div>
															</li>
															<li>
																<div>총개소</div>
																<div class="flRight amount" id ="iBoardStatusTot"></div>
															</li>
														</ul>
													</div>
												</div>
											</div>
										</div>
									</div>
								</div>
							</div>
							<div class="col-md-3">
							    <div class="card">
							        <div class="card-body" style="overflow:auto;">
							            
							            <h4 class="header-title mb-0">시스템 발전량 그래프</h4>
							            
							            <!-- <div id="amlinechart4"></div> -->
							            <div id='chart-area'></div>
							            
							        </div>
							    </div>
							</div>
							<div class="col-md-3">
							    <div class="single-report mb-xs-30">
							        <div class="card">
							            <div class="card-body">
							            	<h4 class="header-title">이벤트 내역</h4>
							                <div class="single-table">
							                	<div id="eventList">
							                	
							                	</div>
							                </div>
							            </div>
							        </div>
							    </div>
							    <div id ="time" style="float: right; text-align: right;">
							    	
							    </div>
							</div>
						</div>
					</div>
				</div>
			</div>
			<!-- main content area end -->
		</div>
		<!-- page container area end -->
		
		<!-- bootstrap 4 js -->
		<script src="/resources/assets/js/bootstrap.min.js"></script>
		<script src="/resources/assets/js/owl.carousel.min.js"></script>
		<script src="/resources/assets/js/metisMenu.min.js"></script>
		<script src="/resources/assets/js/jquery.slimscroll.min.js"></script>
		<script src="/resources/assets/js/jquery.slicknav.min.js"></script>
		
		<!-- <script src="/resources/assets/js/serial.js"></script>
		<script src="/resources/assets/js/export.min.js"></script>
		<script src="/resources/assets/js/light.js"></script> -->
		
		<!-- s:toast-ui -->
		<script type='text/javascript' src='/resources/assets/js/raphael.js'></script>
		<script type='text/javascript' src='/resources/assets/js/core.js'></script>
		<script src='/resources/assets/dist/tui-chart.js'></script>
		<!-- e:toast-ui -->
		
		
		<script type="text/javascript">

	    
		$(function(){
			
			eventList.list();
			// 데이터 조회
			fnMeasureSearch();
			
			// 10초 주기로 데이터 재조회
			setInterval(function(){
				fnMeasureSearch();
				eventList.list();
			},10000);
			
		})
    	
		var aJsonArray = new Array();
		var aJson = new Object();
    
		var sJson = "";
    		
		
		var eventList = {
			// 조회 데이터 영역
			list : function(){
				
				var params = {
					"iCityNum" : $('#iCityNum').val(),
					"iAreaNum" : $('#iAreaNum').val(),
					"iSiteNum" : $('#iSiteNum').val(),
					"startPage" : $('#startPage1').val(),
					"visiblePages" :  $('#visiblePages1').val()
				};
				
				$.ajax({
					url : '/dashboard/city/eventList'
					,data : params
					,dataType : 'html'
					,type : 'post'				
					,success : function(data){
						$('#eventList').html(data);
					}
				    ,beforeSend : function(xhr) {
				        xhr.setRequestHeader("AJAX", true);
				        //-TODO : LOADING IMG
				    }
				    ,error : function(xhr,status,error) {
				        //-TODO : LOADING IMG 제거
				        if(xhr.status == 401) {
				            alert("인증에 실패 했습니다. 메인 페이지로 이동합니다.");
				            location.href = "/dashboard/systemDetail/mng";
				        } else if(xhr.status == 403) {
				            alert("세션이 만료가 되었습니다. 메인 페이지로 이동합니다.");
				            location.href = "/dashboard/systemDetail/mng";
				        } else {
				        	alert("세션이 만료가 되었습니다. 메인 페이지로 이동합니다.");
				        	location.href = "/dashboard/systemDetail/mng";
				            //alert("["+xhr.status+"]오류입니다.\n");
				    		return;	
				        }
				    }
				})
			}
		}
		
		function fnMeasureSearch() {
			
			var params = {
					"iRtuNum" : $('#iRtuNum').val()
			};
			
			$.ajax({
				type : 'post',
				url : '/dashboard/systemDetail/measureList',
				data : params,
				global : false,
				dataType : 'JSON',
				beforeSend : function(xhr) {
				    xhr.setRequestHeader("AJAX", true);
				    //-TODO : LOADING IMG
				}
				,error : function(xhr,status,error) {
				    //-TODO : LOADING IMG 제거
				    if(xhr.status == 401) {
				        alert("인증에 실패 했습니다. 메인 페이지로 이동합니다.");
				        location.href = "/dashboard/systemDetail/mng";
				    } else if(xhr.status == 403) {
				        alert("세션이 만료가 되었습니다. 메인 페이지로 이동합니다.");
				        location.href = "/dashboard/systemDetail/mng";
				    } else {
				        alert("["+xhr.status+"]오류입니다.\n");
				        location.href = "/dashboard/systemDetail/mng";
						return;	
				    }
				},
			    success : function(data) {
			    	
			    	var sSystemName = "";
			    	var iTdWattMeter = 0;
			    	var iTdFL1 = 0;
			    	var iTdFL2 = 0;
			    	
			    	if(data.result == "success") {
			    		
			    		//금일 발전량
			    		$('#tdAccumulateWattProduce1').html((data.measure.tdCumulativeWatt / 1000).toFixed(2)+ " kWh");
			    		
			    		//금일 발전 효율
			    		$('#tdEfficient').html(data.measure.tdEfficiency < 0 ? "0 %" : Math.round(data.measure.tdEfficiency) + " %");
			    		
			    		//금일 사용열량
			    		$('#tdAccumulateHeatConsume').html((data.measure.tdAccumulateHeatConsume / 1000).toFixed(2) + " kWh");
			    		
			    		//전일 누적 발전량
			    		$('#pdAccumulateWattProduce').html((data.measure.pdCumulativeWatt / 1000).toFixed(1) + " kWh");
			    		
			    		//누적 효율
						$('#totEfficient').html(data.measure.cumulativeEfficiency < 0 ? "0 %" : (data.measure.cumulativeEfficiency > 0 ? "100 %" : Math.round(data.measure.cumulativeEfficiency) + " %"));
			    		
			    		//전기에너지 금일 발전량
			    		$('#tdAccumulateWattProduce2').html((data.measure.tdCumulativeWatt / 1000).toFixed(2) + " kWh");
			    		
			    		//전기에너지 금일 CO2 저감량
			    		$('#tdWattReduction').html(data.measure.tdReductionCo2Watt + " tCO2");
			    		
			    		//전기에너지 금일 설비용량
			    		$('#fCapaWatt').html(Math.floor(data.measure.fCapa) + " kW");
			    		
			    		//전기에너지 누적발전량
			    		$('#tdAccumulateWattProduce3').html((data.measure.cumulativeWatt / 1000).toFixed(1) + " kWh");
			    		
			    		//열 에너지 금일 발전량
			    		$('#pdAccumulateHeatProduce1').html((data.measure.tdAccumulateHeatProduce / 1000).toFixed(2) + " kWh");
			    		
			    		//열 에너지 금일 CO2저감량
			    		$('#tdHeatReduction').html(data.measure.tdReductionCo2Heat + " tCO2");

			    		//열 에너지 설비용량
			    		$('#fCapaHeat').html(Math.floor(data.measure.fCapaSolar) + " kWh");
			    		
			    		//열 에너지 누적발전량
			    		$('#pdAccumulateHeatProduce2').html((data.measure.accumulateHeatProduce  / 1000000).toFixed(1) + " MWh");
			    		
			    		
			    		$('#iNormal').html(data.iNormal + " 개소");
			    		$('#iWait').html(data.iWait + " 개소");
			    		$('#iBreakDown').html(data.iBreakDown + " 개소");
			    		$('#iPreparing').html(data.iPreparing + " 개소");
			    		$('#iBoardStatusTot').html(data.iBoardStatusTot + " 개소");
			    		
			    		$('#time').html("조회 시각 : " + data.measure.timestamp);
			    		
			    		var container = document.getElementById('effiChart');
						
						container.innerHTML = "";
						
						var chartData = {
						    categories: data.effiChartObjJson,
						    series: [
						        {
						            name: 'System',
						            data: data.effiChartAmtJson
						        }
						    ]
						};
						var options = {
						    chart: {
						        width: 400,
						        height: 400,
						        format: '1,000'
						    },
						    yAxis: {
						        title: 'System'
						    },
						    xAxis: {
						    	min: 0,
						        max: 100,
						        title: '%'
						    },
						    legend: {
						    	visible: false,
						        align: 'right'
						    },
						     series: {
						         showLabel: true
						     }
						};
						
						// For apply theme
						// tui.chart.registerTheme('myTheme', theme);
						// options.theme = 'myTheme';
						tui.chart.barChart(container, chartData, options);
						
			    		var container1 = document.getElementById('chart-area');
						
						container1.innerHTML = "";
						
						var data1 = {
						    categories: data.wattProduceHourArray,
						    series: data.wattSubProduceArray
						};
						var options1 = {
							 chart: {
							        width: 400,
							        height: 540
							    },
							    yAxis: {
							    	min: 0,
							        max: 1.2,
							        title: '발전량'
							    },
							    xAxis: {
							        title: 'Time',
							        pointOnColumn: true
							    },
							    series: {
							    	spline: true,
							        showDot: false,
							        zoomable: false
							    },
							    tooltip: {
							        suffix: 'K'
							    },
							    legend: {
							    	visible: false,
							        align: 'bottom'
							    }
							    
						};
						
						var theme = {
								xAxis: {
							        label: {
							            fontSize: 10
							        },
							    },
								series:{
									colors: [
							            '#00A9FF'
							        ]
								}
							};
						// For apply theme
						tui.chart.registerTheme('myTheme', theme);
						options1.theme = 'myTheme';
						var chart = tui.chart.lineChart(container1, data1, options1);
			    		
			    	}
			    }
			});
		}
		
		function fnClickInstallInfo(){
			
			if($('#sAuth').val() == 'ROLE_USER'){
				alert('수정 권한이 없습니다.');
				return;
			}
			
			var params = {
					"iRtuNum" : $('#iRtuNum').val()
			};
			
			$.ajax({
				type : 'post',
				url : '/dashboard/systemDetail/installInfoConfirm',
				data : params,
				global : false,
				dataType : 'JSON',
				beforeSend : function(xhr) {
				    xhr.setRequestHeader("AJAX", true);
				    //-TODO : LOADING IMG
				}
				,error : function(xhr,status,error) {
				    //-TODO : LOADING IMG 제거
				    if(xhr.status == 401) {
				        alert("인증에 실패 했습니다. 메인 페이지로 이동합니다.");
				        location.href = "/dashboard/systemDetail/mng";
				    } else if(xhr.status == 403) {
				        alert("세션이 만료가 되었습니다. 메인 페이지로 이동합니다.");
				        location.href = "/dashboard/systemDetail/mng";
				    } else {
				        alert("["+xhr.status+"]오류입니다.\n");
				        location.href = "/dashboard/systemDetail/mng";
						return;	
				    }
				},
				success : function(data) {
					if(data.result == "success") {
						if(data.installInfoCnt != 0){
							$('#sSystemLoca').val(data.installInfo[0].sSystemLoca);
							$('#sConnect').val(data.installInfo[0].sConnect);
							$('#sInstallDate').val(data.installInfo[0].sInstallDate);
							$('#sPreCheckDate').val(data.installInfo[0].sPreCheckDate);
							$('#sConfirmDate').val(data.installInfo[0].sConfirmDate);
							$('#sExpireDate').val(data.installInfo[0].sExpireDate);
						}
						$('#installInfoModal').modal();
					}
				}
			});
		}
		
		function fnInstallInfoInsert(){
			
			if($('#sSystemLoca').val() == "" ||
			   $('#sPreCheckDate').val() == "" ||
			   $('#sConnect').val() == "" ||
			   $('#sConfirmDate').val() == "" ||
			   $('#sInstallDate').val() == "" ||
			   $('#sExpireDate').val() == ""
			) {
				alert("값을 모두 입력해주세요.");
			}else{
				var params = {
						"iRtuNum" : $('#iRtuNum').val(),
						"sSystemLoca" : $('#sSystemLoca').val(),
						"sPreCheckDate" : $('#sPreCheckDate').val(),
						"sConnect" : $('#sConnect').val(),
						"sConfirmDate" : $('#sConfirmDate').val(),
						"sInstallDate" : $('#sInstallDate').val(),
						"sExpireDate" : $('#sExpireDate').val()
				};
				
				$.ajax({
					type : 'post',
					url : '/dashboard/systemDetail/insertInstallInfo',
					data : params,
					global : false,
					dataType : 'JSON',
					beforeSend : function(xhr) {
					    xhr.setRequestHeader("AJAX", true);
					    //-TODO : LOADING IMG
					}
					,error : function(xhr,status,error) {
					    //-TODO : LOADING IMG 제거
					    if(xhr.status == 401) {
					        alert("인증에 실패 했습니다. 메인 페이지로 이동합니다.");
					        location.href = "/dashboard/systemDetail/mng";
					    } else if(xhr.status == 403) {
					        alert("세션이 만료가 되었습니다. 메인 페이지로 이동합니다.");
					        location.href = "/dashboard/systemDetail/mng";
					    } else {
					        alert("["+xhr.status+"]오류입니다.\n");
					        location.href = "/dashboard/systemDetail/mng";
							return;	
					    }
					},
					success : function(data) {
						if(data.result == "success") {
							$('#installInfoModal').modal('hide');
						}else{
							alert('저장에 실패했습니다.');
						}
					}
				});
				
			}
		}
		
		function fnInstallInfoClear(){
			$('#sSystemLoca').val("");
			$('#sConnect').val("");
			$('#sInstallDate').val("");
			$('#sPreCheckDate').val("");
			$('#sConfirmDate').val("");
			$('#sExpireDate').val("");
		}
		
		function fnClickEquipInfo(){
			
			if($('#sAuth').val() == 'ROLE_USER'){
				alert('수정 권한이 없습니다.');
				return;
			}
			
			var params = {
					"iRtuNum" : $('#iRtuNum').val()
			};
			
			$.ajax({
				type : 'post',
				url : '/dashboard/systemDetail/equipInfoConfirm',
				data : params,
				global : false,
				dataType : 'JSON',
				beforeSend : function(xhr) {
				    xhr.setRequestHeader("AJAX", true);
				    //-TODO : LOADING IMG
				}
				,error : function(xhr,status,error) {
				    //-TODO : LOADING IMG 제거
				    if(xhr.status == 401) {
				        alert("인증에 실패 했습니다. 메인 페이지로 이동합니다.");
				        location.href = "/dashboard/systemDetail/mng";
				    } else if(xhr.status == 403) {
				        alert("세션이 만료가 되었습니다. 메인 페이지로 이동합니다.");
				        location.href = "/dashboard/systemDetail/mng";
				    } else {
				        alert("["+xhr.status+"]오류입니다.\n");
				        location.href = "/dashboard/systemDetail/mng";
						return;	
				    }
				},
				success : function(data) {
					if(data.result == "success") {
						if(data.equipInfoCnt != 0){
							$('#iInverterCapa').val(data.equipInfo[0].iInverterCapa);
							$('#sInverterMFR').val(data.equipInfo[0].sInverterMFR);
							$('#sInverterName').val(data.equipInfo[0].sInverterName);
							$('#sAsDate').val(data.equipInfo[0].sAsDate);
							$('#sAsReason').val(data.equipInfo[0].sAsReason);
						}
						$('#equipInfoModal').modal();
					}
				}
			});
		}
		
		function fnEquipInfoInsert(){
			
			if($('#iInverterCapa').val() == "" ||
			   $('#sInverterMFR').val() == "" ||
			   $('#sInverterName').val() == "" ||
			   $('#sAsDate').val() == "" ||
			   $('#sAsReason').val() == "" 
			) {
				alert("값을 모두 입력해주세요.");
			}else{
				var params = {
						"iRtuNum" : $('#iRtuNum').val(),
						"iInverterCapa" : $('#iInverterCapa').val(),
						"sInverterMFR" : $('#sInverterMFR').val(),
						"sInverterName" : $('#sInverterName').val(),
						"sAsDate" : $('#sAsDate').val(),
						"sAsReason" : $('#sAsReason').val()
				};
				
				$.ajax({
					type : 'post',
					url : '/dashboard/systemDetail/insertEquipInfo',
					data : params,
					global : false,
					dataType : 'JSON',
					beforeSend : function(xhr) {
					    xhr.setRequestHeader("AJAX", true);
					    //-TODO : LOADING IMG
					}
					,error : function(xhr,status,error) {
					    //-TODO : LOADING IMG 제거
					    if(xhr.status == 401) {
					        alert("인증에 실패 했습니다. 메인 페이지로 이동합니다.");
					        location.href = "/dashboard/systemDetail/mng";
					    } else if(xhr.status == 403) {
					        alert("세션이 만료가 되었습니다. 메인 페이지로 이동합니다.");
					        location.href = "/dashboard/systemDetail/mng";
					    } else {
					        alert("["+xhr.status+"]오류입니다.\n");
					        location.href = "/dashboard/systemDetail/mng";
							return;	
					    }
					},
					success : function(data) {
						if(data.result == "success") {
							$('#equipInfoModal').modal('hide');
						}else{
							alert('저장에 실패했습니다.');
						}
					}
				});
			}
		}

		function fnClickSystem() {
           $("#systemDetailForm").attr({action:'<c:url value="/dashboard/systemCont/mng"/>', method:'post'}).submit();
		}

		function fnClickSystemInfo(){
			
			if($('#sAuth').val() == 'ROLE_USER'){
				alert('수정 권한이 없습니다.');
				return;
			}
			
			var params = {
					"iRtuNum" : $('#iRtuNum').val()
			};
			
			$.ajax({
				type : 'post',
				url : '/dashboard/systemDetail/systemInfoConfirm',
				data : params,
				global : false,
				dataType : 'JSON',
				beforeSend : function(xhr) {
				    xhr.setRequestHeader("AJAX", true);
				    //-TODO : LOADING IMG
				}
				,error : function(xhr,status,error) {
				    //-TODO : LOADING IMG 제거
				    if(xhr.status == 401) {
				        alert("인증에 실패 했습니다. 메인 페이지로 이동합니다.");
				        location.href = "/dashboard/systemDetail/mng";
				    } else if(xhr.status == 403) {
				        alert("세션이 만료가 되었습니다. 메인 페이지로 이동합니다.");
				        location.href = "/dashboard/systemDetail/mng";
				    } else {
				        alert("["+xhr.status+"]오류입니다.\n");
				        location.href = "/dashboard/systemDetail/mng";
						return;	
				    }
				},
				success : function(data) {
					if(data.result == "success") {
						if(data.systemInfoCnt != 0){
							$('#iRtuNumModal').html(data.systemInfo[0].iRtuNum);
							$('#iBdNumModal').html(data.systemInfo[0].iBdNum);
							$('#sSystemNameModal').val(data.systemInfo[0].sSystemName);
							$('#fCapa').val(data.systemInfo[0].fCapa);
							$('#fCapaGeo').val(data.systemInfo[0].fCapaGeo);
							$('#fCapaSolar').val(data.systemInfo[0].fCapaSolar);
							$('#fLhv').val(data.systemInfo[0].fLhv);
							$('#fCO2').val(data.systemInfo[0].fCO2);
							
						}
						$('#systemInfoModal').modal();
					}
				}
			});
		}
		
		function fnSystemInfoUpdate(){
			
			if($('#sSystemNameModal').val() == "" ||
				$('#fCapa').val() == "" ||
				$('#fCapaGeo').val() == "" ||
				$('#fCapaSolar').val() == "" ||
				$('#fLhv').val() == "" ||
				$('#fCO2').val() == "" 
			) {
				alert("값을 모두 입력해주세요.");
			}else{
				var params = {
						"iRtuNum" : $('#iRtuNum').val(),
						"sSystemName" : $('#sSystemNameModal').val(),
						"fCapa" : $('#fCapa').val(),
						"fCapaGeo" : $('#fCapaGeo').val(),
						"fCapaSolar" : $('#fCapaSolar').val(),
						"fLhv" : $('#fLhv').val(),
						"fCO2" : $('#fCO2').val(),
				};
				
				$.ajax({
					type : 'post',
					url : '/dashboard/systemDetail/updateSystemInfo',
					data : params,
					global : false,
					dataType : 'JSON',
					beforeSend : function(xhr) {
					    xhr.setRequestHeader("AJAX", true);
					    //-TODO : LOADING IMG
					}
					,error : function(xhr,status,error) {
					    //-TODO : LOADING IMG 제거
					    if(xhr.status == 401) {
					        alert("인증에 실패 했습니다. 메인 페이지로 이동합니다.");
					        location.href = "/dashboard/systemDetail/mng";
					    } else if(xhr.status == 403) {
					        alert("세션이 만료가 되었습니다. 메인 페이지로 이동합니다.");
					        location.href = "/dashboard/systemDetail/mng";
					    } else {
					        alert("["+xhr.status+"]오류입니다.\n");
					        location.href = "/dashboard/systemDetail/mng";
							return;	
					    }
					},
					success : function(data) {
						if(data.result == "success") {
							$('#systemInfoModal').modal('hide');
						}else{
							alert('저장에 실패했습니다.');
						}
					}
				});
			}
		}
		
		
		
		function fnEquipInfoClear(){
			$('#iInverterCapa').val("");
			$('#sInverterMFR').val("");
			$('#sInverterName').val("");
			$('#sAsDate').val("");
			$('#sAsReason').val("");
		}
		
		function fnClickMain(){
			
			location.href = "/dashboard/city/mng";
			
		}
		
		function fnClickCity(){
			
			$("#systemDetailForm").attr({action:'/dashboard/area/mng', method:'post'}).submit();
		}
		
		function fnClickArea(){
			
			$("#systemDetailForm").attr({action:'/dashboard/site/mng', method:'post'}).submit();
		}
		
		function fnClickSite(){
			$("#systemDetailForm").attr({action:'/dashboard/system/mng', method:'post'}).submit();
		}
		
		function fnClickSystemCont(){
			$("#systemDetailForm").attr({action:'/dashboard/systemCont/mng', method:'post'}).submit();
		}

		function doExcelDownloadProcess(){
			var f = document.form1;
			f.action = "/dashboard/systemCont/guestDownloadMeasureExcelFile";
			f.submit();
		}
		
		
	</script>
    
    <!-- all line chart activation -->
    <!-- <script src="/resources/assets/js/line-chart.js"></script> -->
    <!-- others plugins -->
    <script src="/resources/assets/js/plugins.js"></script>
    <script src="/resources/assets/js/scripts.js"></script>
    
    <script src='/resources/assets/js/jshint.js'></script>
	<script src='/resources/assets/js/codemirror.js'></script>
	<script src='/resources/assets/js/matchbrackets.js'></script>
	<script src='/resources/assets/js/active-line.js'></script>
	<script src='/resources/assets/js/javascript.js'></script>
	<script src='/resources/assets/js/lint.js'></script>
	<script src='/resources/assets/js/javascript-lint.js'></script>
	
    
    
</body>

</html>
