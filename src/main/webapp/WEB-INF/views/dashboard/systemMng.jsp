<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%  
	response.setHeader("Cache-Control","no-store");  
	response.setHeader("Pragma","no-cache");  
	response.setDateHeader("Expires",0);  
	if (request.getProtocol().equals("HTTP/1.1"))
        response.setHeader("Cache-Control", "no-cache");
%>
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
    
</head>

<body>
	<input type="hidden" id="startPage" name="startPage" value="1">	
	<input type="hidden" id="visiblePages" name="visiblePages" value="6">
	
	<input type="hidden" id="startPage1" name="startPage1" value="1">	
	<input type="hidden" id="visiblePages1" name="visiblePages1" value="5">
	
	<form id="systemForm">
		<input type="hidden" id="iCityNum" name="iCityNum" value="${iCityNum}">
		<input type="hidden" id="sCityName" name="sCityName" value="${sCityName}">
		<input type="hidden" id="iAreaNum" name="iAreaNum" value="${iAreaNum}">
		<input type="hidden" id="sAreaName" name="sAreaName" value="${sAreaName}">
		<input type="hidden" id="iSiteNum" name="iSiteNum" value="${iSiteNum}">
		<input type="hidden" id="sSiteName" name="sSiteName" value="${sSiteName}">
		<input type="hidden" id="sAuth" name="sAuth" value="${sAuth}">
		<input type="hidden" id="iSysNum" name="iSysNum">
		<input type="hidden" id="sSystemName" name="sSystemName">
		<input type="hidden" id="iRtuNum" name="iRtuNum">
		<input type="hidden" id="iBdNum" name="iBdNum">
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
				<div class="page-title-area">
					<div class="row align-items-center">
						<div class="col-sm-12 ">
							<div class="breadcrumbs-area clearfix">
								<h4 class="page-title pull-left mt-2">설치 사이트 화면</h4>
								<ul class="breadcrumbs pull-left mt-2">
									<c:if test="${sAuth eq 'ROLE_SU'}">
										<li><a href="javascript:fnClickMain();">전국</a></li>
										<li><a href="javascript:fnClickCity();">${sCityName}</a></li>
										<li><a href="javascript:fnClickArea();">${sAreaName}</a></li>
									</c:if>
									<li><span>${sSiteName}</span></li>
								</ul>
								<ul class="pull-right mt-2">
								    <span><a href="/logoutProcess">LogOut</a></span>
								</ul>
							</div>
						</div>
					</div>
				</div>
				<!-- page title area end -->
				<div class="main-content-inner">
					<!-- sales report area start -->
					<div class="sales-report-area mt-5 mb-5">
						<div class="row">
							<div class="col-md-5">
							    <div class="single-report mb-xs-30">
							        <div class="card">
							            <div class="card-body">
							                <div class="single-table">
							                	<div id="systemList">
							                	
							                	</div>
							                </div>
							            </div>
							        </div>
							    </div>
							</div>
							<div class="col-md-4">
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
							<div class="col-md-3">
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
										<h4 class="header-title">사이트 운영현황</h4>
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
							            
							            <h4 class="header-title mb-0">사이트 발전량 그래프</h4>
							            
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
		
		<!-- Modal -->
		<div class="modal fade" id="systemInfoModal">
			<div class="modal-dialog modal-dialog-centered" role="document">
				<div class="modal-content">
					<div class="modal-header">
						<h5 class="modal-title">시스템 추가</h5>
						<button type="button" class="close" data-dismiss="modal"><span>&times;</span></button>
					</div>
					<div class="modal-body">
						<div class="form-row">
							<div class="col-md-4 mb-3">
							    <label for="iRtuNumModal" class="col-form-label">Rtu 번호</label>
							    <input class="form-control" type="text" value="" id="iRtuNumModal">
							</div>
							<div class="col-md-4 mb-3">
							    <label for="iBdNumModal" class="col-form-label">Board 번호</label>
							    <input class="form-control" type="text" value="" id="iBdNumModal">
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
						<button type="button" class="btn btn-secondary" data-dismiss="modal">최소</button>
						<button type="button" class="btn btn-primary" onclick="javascript:fnSystemInfoInsert();">저장</button>
					</div>
				</div>
			</div>
		</div>
		<div class="modal fade" id="audiInfoModal">
			<div class="modal-dialog modal-dialog-centered" role="document">
				<div class="modal-content">
					<div class="modal-header">
						<h5 class="modal-title">수용가 정보 입력화면</h5>
						<button type="button" class="close" data-dismiss="modal"><span>&times;</span></button>
					</div>
					<div class="modal-body">
						<div class="form-row">
							<div class="col-md-6 mb-3">
							    <label for="sAudiName" class="col-form-label">수용가 이름</label>
							    <input class="form-control" type="text" value="" id="sAudiName">
							</div>
							<div class="col-md-6 mb-3">
							    <label for="sAudiCall" class="col-form-label">수용가 핸드폰 번호</label>
							    <input class="form-control" type="text" value="" id="sAudiCall">
							</div>
							<div class="col-md-4 mb-3">
							    <label for="sAudiDo" class="col-form-label">시도</label>
							    <input class="form-control" type="text" value="" id="sAudiDo">
							</div>
							<div class="col-md-4 mb-3">
							    <label for="sAudiCity" class="col-form-label">시군</label>
							    <input class="form-control" type="text" value="" id="sAudiCity">
							</div>
							<div class="col-md-4 mb-3">
							    <label for="sAudiArea" class="col-form-label">읍면동</label>
							    <input class="form-control" type="text" value="" id="sAudiArea">
							</div>
							<div class="col-md-6 mb-3">
							    <label for="sAudiBiz" class="col-form-label">해당사업</label>
							    <input class="form-control" type="text" value="" id="sAudiBiz">
							</div>
							<div class="col-md-6 mb-3">
							    <label for="sAudiBuilding" class="col-form-label">건물형태</label>
							    <input class="form-control" type="text" value="" id="sAudiBuilding">
							</div>
						</div>
		            </div>
					<div class="modal-footer">
						<button type="button" class="btn btn-secondary" data-dismiss="modal">취소</button>
						<button type="button" class="btn btn-primary" onclick="javascript:fnAudiInfoInsert();">저장</button>
					</div>
				</div>
			</div>
		</div>
		<div class="modal fade bd-example-modal-sm" id="systemDeleteCheckModal">
			<div class="modal-dialog modal-sm">
				<div class="modal-content">
					<div class="modal-header">
					    <!-- <h5 class="modal-title">경고</h5> -->
					    <button type="button" class="close" data-dismiss="modal"><span>&times;</span></button>
					</div>
					<div class="modal-body">
						<div id="sSystemNameDel"></div> 발전기를 삭제하시겠습니까?
						<input type="hidden" id="iSysNumModal">
					</div>
					<div class="modal-footer">
					    <button type="button" class="btn btn-secondary" data-dismiss="modal">취소</button>
					    <button type="button" class="btn btn-primary" onclick="javascript:fnDeleteSystemInfo();">삭제</button>
					</div>
				</div>
			</div>
		</div>
		
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
			
			systemList.list();
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
    		
		var systemList = {
			// 조회 데이터 영역
			list : function(){
				
				var params = {
					"iCityNum" : $('#iCityNum').val(),
					"iAreaNum" : $('#iAreaNum').val(),
					"iSiteNum" : $('#iSiteNum').val(),
					"startPage" : $('#startPage').val(),
					"visiblePages" :  $('#visiblePages').val()
				};
				
				$.ajax({
					url : '/dashboard/system/list'
					,data : params
					,dataType : 'html'
					,type : 'post'				
					,success : function(data){
						$('#systemList').html(data);
					}
				    ,beforeSend : function(xhr) {
				        xhr.setRequestHeader("AJAX", true);
				        //-TODO : LOADING IMG
				    }
				    ,error : function(xhr,status,error) {
				        //-TODO : LOADING IMG 제거
				        if(xhr.status == 401) {
				            alert("인증에 실패 했습니다. 메인 페이지로 이동합니다.");
				            location.href = "/dashboard/system/mng";
				        } else if(xhr.status == 403) {
				            alert("세션이 만료가 되었습니다. 메인 페이지로 이동합니다.");
				            location.href = "/dashboard/system/mng";
				        } else {
				        	alert("세션이 만료가 되었습니다. 메인 페이지로 이동합니다.");
				        	location.href = "/dashboard/system/mng";
				            //alert("["+xhr.status+"]오류입니다.\n");
				    		return;	
				        }
				    }
				})
			}
		}
		
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
				            location.href = "/dashboard/system/mng";
				        } else if(xhr.status == 403) {
				            alert("세션이 만료가 되었습니다. 메인 페이지로 이동합니다.");
				            location.href = "/dashboard/system/mng";
				        } else {
				        	alert("세션이 만료가 되었습니다. 메인 페이지로 이동합니다.");
				        	location.href = "/dashboard/system/mng";
				            //alert("["+xhr.status+"]오류입니다.\n");
				    		return;	
				        }
				    }
				})
			}
		}
	
		function fnMeasureSearch() {
			
			var params = {
					"iCityNum" : $('#iCityNum').val(),
					"iAreaNum" : $('#iAreaNum').val(),
					"iSiteNum" : $('#iSiteNum').val()
			};
			
			$.ajax({
				type : 'post',
				url : '/dashboard/system/measureList',
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
				        location.href = "/dashboard/system/mng";
				    } else if(xhr.status == 403) {
				        alert("세션이 만료가 되었습니다. 메인 페이지로 이동합니다.");
				        location.href = "/dashboard/system/mng";
				    } else {
				        alert("["+xhr.status+"]오류입니다.\n");
				        location.href = "/dashboard/system/mng";
						return;	
				    }
				},
			    success : function(data) {
			    	
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
			    		$('#totEfficient').html(data.measure.cumulativeEfficiency < 0 ? "0 %" : Math.round(data.measure.cumulativeEfficiency) + " %");
			    		
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
						        width: 550,
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
							        //max: 1.2,
							        max: data.systemCnt * 1.2,
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
			    	
			    	
			    	/* var iTdWattMeter = 0;
			    	var iTdFL1 = 0;
			    	var iTdFL2 = 0;
			    	
			    	if(data.result == "success") {
			    		
			    		$('#pdAccumulateWattProduce').html(data.pdAccumulateWattProduce + " kWh");
			    		$('#tdAccumulateWattProduce1').html(data.tdAccumulateWattProduce - data.pdAccumulateWattProduce < 0 ? "0 kWh" : data.tdAccumulateWattProduce - data.pdAccumulateWattProduce + " kWh");
			    		$('#tdEfficient').html(data.tdEfficient + " %");
			    		
			    		$('#totEfficient').html(data.totEfficient < 0 ? "0 %" : Math.floor(data.totEfficient * 100) / 100 + " %" );
			    		
			    		$('#tdAccumulateHeatConsume').html(data.tdAccumulateHeatConsume + " kWh");
			    		
			    		$('#tdAccumulateWattProduce2').html(data.tdAccumulateWattProduce - data.pdAccumulateWattProduce < 0 ? "0 kWh" : data.tdAccumulateWattProduce - data.pdAccumulateWattProduce + " kWh");
			    		$('#tdWattReduction').html(data.tdAccumulateWattProduce - data.pdAccumulateWattProduce < 0 ? "0 tCO2" : Math.floor((data.tdAccumulateWattProduce - data.pdAccumulateWattProduce) * data.lhv * 100 ) / 100 + " tCO2");
			    		$('#fCapaWatt').html(data.fCapa + " kW");
			    		$('#tdAccumulateWattProduce3').html(data.tdAccumulateWattProduce + " kWh");
			    		
			    		$('#pdAccumulateHeatProduce1').html(data.tdAccumulateHeatProduce - data.pdAccumulateHeatProduce < 0 ? "0 kWh" : data.tdAccumulateHeatProduce - data.pdAccumulateHeatProduce + " kWh");
			    		$('#tdHeatReduction').html(data.tdAccumulateHeatProduce - data.pdAccumulateHeatProduce < 0 ? "0 kWh" : Math.floor((data.tdAccumulateHeatProduce - data.pdAccumulateHeatProduce) * data.lhv * 100 ) / 100 + " tCO2");
			    		$('#fCapaHeat').html(data.fCapaSolar + " kWh");
			    		$('#pdAccumulateHeatProduce2').html(data.tdAccumulateHeatProduce + " MWh");
			    		
			    		$('#iNormal').html(data.iNormal + " 개소");
			    		$('#iWait').html(data.iWait + " 개소");
			    		$('#iBreakDown').html(data.iBreakDown + " 개소");
			    		$('#iPreparing').html(data.iPreparing + " 개소");
			    		$('#iBoardStatusTot').html(data.iBoardStatusTot + " 개소");
			    		
			    	}
			    	
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
					        width: 550,
					        height: 400,
					        format: '1,000'
					    },
					    yAxis: {
					        title: 'City'
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
					    series:  data.wattSubProduceArray
					};
					var options1 = {
						 chart: {
						        width: 400,
						        height: 540
						    },
						    yAxis: {
						    	min: 0,
						        //max: data.systemCnt * 1.2,
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
					
					var theme1 = {
						xAxis: {
					        label: {
					            fontSize: 10
					        }
					    }
					};
					// For apply theme
					
					tui.chart.registerTheme('myTheme', theme1);
					options1.theme = 'myTheme';
					var chart = tui.chart.lineChart(container1, data1, options1); */					
			    }
			});
		}
		
		function fnAddSystem(){
			
			if($('#sAuth').val() == 'ROLE_USER'){
				alert('수정 권한이 없습니다.');
				return;
			}
			
			$('#systemInfoModal').modal();
		}
		

		function fnClickAudiInfo(){
			
			if($('#sAuth').val() == 'ROLE_USER'){
				alert('수정 권한이 없습니다.');
				return;
			}
			
			var params = {
					"iSiteNum" : $('#iSiteNum').val()
			};
			
			$.ajax({
				type : 'post',
				url : '/dashboard/system/audiInfoConfirm',
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
				        location.href = "/dashboard/system/mng";
				    } else if(xhr.status == 403) {
				        alert("세션이 만료가 되었습니다. 메인 페이지로 이동합니다.");
				        location.href = "/dashboard/system/mng";
				    } else {
				        alert("["+xhr.status+"]오류입니다.\n");
				        location.href = "/dashboard/system/mng";
						return;	
				    }
				},
				success : function(data) {
					if(data.result == "success") {
						if(data.audiInfoCnt != 0){
							$('#sAudiNum').val(data.audiInfo[0].sAudiNum);
							$('#sAudiName').val(data.audiInfo[0].sAudiName);
							$('#sAudiCall').val(data.audiInfo[0].sAudiCall);
							$('#sAudiDo').val(data.audiInfo[0].sAudiDo);
							$('#sAudiCity').val(data.audiInfo[0].sAudiCity);
							$('#sAudiArea').val(data.audiInfo[0].sAudiArea);
							$('#sAudiBiz').val(data.audiInfo[0].sAudiBiz);
							$('#sAudiBuilding').val(data.audiInfo[0].sAudiBuilding);
						}
						$('#audiInfoModal').modal();
					}
				}
			});
		}
		
		function fnAudiInfoInsert(){
			if($('#sAudiName').val() == "" ||
			   $('#sAudiCall').val() == "" ||
			   $('#sAudiDo').val() == "" ||
			   $('#sAudiCity').val() == "" ||
			   $('#sAudiArea').val() == "" ||
			   $('#sAudiBiz').val() == "" ||
			   $('#sAudiBuilding').val() == ""
			) {
				alert("값을 모두 입력해주세요.");
			}else{
				var params = {
						"iSiteNum" : $('#iSiteNum').val(),
						"sSiteName" : $('#sSiteName').val(),
						"sAudiName" : $('#sAudiName').val(),
						"sAudiCall" : $('#sAudiCall').val(),
						"sAudiDo" : $('#sAudiDo').val(),
						"sAudiCity" : $('#sAudiCity').val(),
						"sAudiArea" : $('#sAudiArea').val(),
						"sAudiBiz" : $('#sAudiBiz').val(),
						"sAudiBuilding" : $('#sAudiBuilding').val()
				};
				
				$.ajax({
					type : 'post',
					url : '/dashboard/system/insertAudiInfo',
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
					        location.href = "/dashboard/system/mng";
					    } else if(xhr.status == 403) {
					        alert("세션이 만료가 되었습니다. 메인 페이지로 이동합니다.");
					        location.href = "/dashboard/system/mng";
					    } else {
					        alert("["+xhr.status+"]오류입니다.\n");
					        location.href = "/dashboard/system/mng";
							return;	
					    }
					},
					success : function(data) {
						if(data.result == "success") {
							$('#audiInfoModal').modal('hide');
						}else{
							alert('저장에 실패했습니다.');
						}
					}
				});
			}
		}
		
		function fnSystemInfoInsert(){
			
			if($('#iRtuNumModal').val() == "" ||
			   $('#iBdNumModal').val() == "" ||
			   $('#sSystemNameModal').val() == "" ||
			   $('#fCapa').val() == "" ||
			   $('#fCapaGeo').val() == "" ||
			   $('#fCapaSolar').val() == ""
			) {
				alert("값을 모두 입력해주세요.");
			}else{
				var params = {
						"iCityNum" : $('#iCityNum').val(),
						"iAreaNum" : $('#iAreaNum').val(),
						"iSiteNum" : $('#iSiteNum').val(),
						"iRtuNum" : $('#iRtuNumModal').val(),
						"iBdNum" : $('#iBdNumModal').val(),
						"sSystemName" : $('#sSystemNameModal').val(),
						"fCapa" : $('#fCapa').val(),
						"fCapaGeo" : $('#fCapaGeo').val(),
						"fCapaSolar" : $('#fCapaSolar').val(),
						"fLhv" : $('#fLhv').val(),
						"fCO2" : $('#fCO2').val()
				};
				
				$.ajax({
					type : 'post',
					url : '/dashboard/system/insertSystemInfo',
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
					        location.href = "/dashboard/system/mng";
					    } else if(xhr.status == 403) {
					        alert("세션이 만료가 되었습니다. 메인 페이지로 이동합니다.");
					        location.href = "/dashboard/system/mng";
					    } else {
					        alert("["+xhr.status+"]오류입니다.\n");
					        location.href = "/dashboard/system/mng";
							return;	
					    }
					},
					success : function(data) {
						if(data.result == "success") {
							systemList.list();
							fnMeasureSearch();
							
							$('#systemInfoModal').modal('hide');
						}else{
							alert('저장에 실패했습니다.');
						}
					}
				});
			}
		}
		
		function fnDeleteSystemInfoCheck(iSysNum, sSysName){
			
			$('#sSystemNameDel').html(sSysName);
			$('#iSysNumModal').val(iSysNum);
			
			$('#systemDeleteCheckModal').modal();

		}
		
		function fnDeleteSystemInfo(){
			
			var params = {
					"iSysNum" : $('#iSysNumModal').val()
			};
			
			$.ajax({
				type : 'post',
				url : '/dashboard/system/deleteSystemInfo',
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
				        location.href = "/dashboard/system/mng";
				    } else if(xhr.status == 403) {
				        alert("세션이 만료가 되었습니다. 메인 페이지로 이동합니다.");
				        location.href = "/dashboard/system/mng";
				    } else {
				        alert("["+xhr.status+"]오류입니다.\n");
				        location.href = "/dashboard/system/mng";
						return;	
				    }
				},
				success : function(data) {
					if(data.result == "success") {
						systemList.list();
						fnMeasureSearch();
						
						$('#systemDeleteCheckModal').modal('hide');
						
					}else{
						alert('저장에 실패했습니다.');
					}
				}
			});
		}
		
		function fnClickMain(){
			
			location.href = "/dashboard/city/mng";
			
		}
		
		function fnClickCity(){
			
			$("#systemForm").attr({action:'/dashboard/area/mng', method:'post'}).submit();
		}
		
		function fnClickArea(){
			
			$("#systemForm").attr({action:'/dashboard/site/mng', method:'post'}).submit();
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
