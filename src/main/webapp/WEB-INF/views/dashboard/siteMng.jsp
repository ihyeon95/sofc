<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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

	<!-- custom css -->
	<link rel="stylesheet" href="/resources/assets/css/common.css">
</head>

<body>
	<input type="hidden" id="startPage" name="startPage" value="1">	
	<input type="hidden" id="visiblePages" name="visiblePages" value="6">
	
	<input type="hidden" id="startPage1" name="startPage1" value="1">	
	<input type="hidden" id="visiblePages1" name="visiblePages1" value="5">
	
	
	<form id="siteForm">
		<input type="hidden" id="iCityNum" name="iCityNum" value="${iCityNum}">
		<input type="hidden" id="sCityName" name="sCityName" value="${sCityName}">
		<input type="hidden" id="iAreaNum" name="iAreaNum" value="${iAreaNum}">
		<input type="hidden" id="sAreaName" name="sAreaName" value="${sAreaName}">
		<input type="hidden" id="iSiteNum" name="iSiteNum" >
		<input type="hidden" id="sSiteName" name="sSiteName" >
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
					<h4 class="page-title pull-left mt-2">지역 화면</h4>
					<ul class="breadcrumbs pull-left mt-2">
						<li><a href="javascript:fnClickMain();">전국</a></li>
						<li><a href="javascript:fnClickCity();">${sCityName}</a></li>
						<li><span>${sAreaName}</span></li>
					</ul>
					<ul class="right_menu">
						<li class="sign_out"><a href="/logoutProcess" class="btn_signout">LogOut</a></li>
					</ul>
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
							                	<div id="siteList">
							                	
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
										<h4 class="header-title">지역 운영현황</h4>
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
							            
							            <h4 class="header-title mb-0">지역 발전량 그래프</h4>
							            
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
		<div class="modal fade" id="siteInfoModal">
			<div class="modal-dialog modal-dialog-centered" role="document">
				<div class="modal-content">
					<div class="modal-header">
						<h5 class="modal-title">공장 추가</h5>
						<button type="button" class="close" data-dismiss="modal"><span>&times;</span></button>
					</div>
					<div class="modal-body">
						<div class="form-row">
							<div class="col-md-12 mb-3">
							    <label for="iRtuNum" class="col-form-label">공장명</label>
							    <input class="form-control" type="text" value="" id="sSiteNameText">
							</div>
							
						</div>
		            </div>
					<div class="modal-footer">
						<button type="button" class="btn btn-secondary" data-dismiss="modal">취소</button>
						<button type="button" class="btn btn-primary" onclick="javascript:fnSiteInfoInsert();">저장</button>
					</div>
				</div>
			</div>
		</div>
		<div class="modal fade bd-example-modal-sm" id="siteDeleteCheckModal">
			<div class="modal-dialog modal-sm">
				<div class="modal-content">
					<div class="modal-header">
					    <!-- <h5 class="modal-title">경고</h5> -->
					    <button type="button" class="close" data-dismiss="modal"><span>&times;</span></button>
					</div>
					<div class="modal-body">
						<div id="sSiteNameDel"></div> 공장을 삭제하시겠습니까?
						<input type="hidden" id="iSysNum">
					</div>
					<div class="modal-footer">
					    <button type="button" class="btn btn-secondary" data-dismiss="modal">취소</button>
					    <button type="button" class="btn btn-primary" onclick="javascript:fnDeleteSiteInfo();">삭제</button>
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
			
			siteList.list();
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
    		
		var siteList = {
			// 조회 데이터 영역
			list : function(){
				
				var params = {
					"iCityNum" : $('#iCityNum').val(),
					"iAreaNum" : $('#iAreaNum').val(),
					"startPage" : $('#startPage').val(),
					"visiblePages" :  $('#visiblePages').val()
				};
				
				$.ajax({
					url : '/dashboard/site/list'
					,data : params
					,dataType : 'html'
					,type : 'post'				
					,success : function(data){
						$('#siteList').html(data);
					}
				    ,beforeSend : function(xhr) {
				        xhr.setRequestHeader("AJAX", true);
				        //-TODO : LOADING IMG
				    }
				    ,error : function(xhr,status,error) {
				        //-TODO : LOADING IMG 제거
				        if(xhr.status == 401) {
				            alert("인증에 실패 했습니다. 메인 페이지로 이동합니다.");
				            location.href = "/dashboard/site/mng";
				        } else if(xhr.status == 403) {
				            alert("세션이 만료가 되었습니다. 메인 페이지로 이동합니다.");
				            location.href = "/dashboard/site/mng";
				        } else {
				        	alert("세션이 만료가 되었습니다. 메인 페이지로 이동합니다.");
				        	location.href = "/dashboard/site/mng";
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
				            location.href = "/dashboard/site/mng";
				        } else if(xhr.status == 403) {
				            alert("세션이 만료가 되었습니다. 메인 페이지로 이동합니다.");
				            location.href = "/dashboard/site/mng";
				        } else {
				        	alert("세션이 만료가 되었습니다. 메인 페이지로 이동합니다.");
				        	location.href = "/dashboard/site/mng";
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
					"iAreaNum" : $('#iAreaNum').val()
			};
			
			$.ajax({
				type : 'post',
				url : '/dashboard/site/measureList',
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
				        location.href = "/dashboard/site/mng";
				    } else if(xhr.status == 403) {
				        alert("세션이 만료가 되었습니다. 메인 페이지로 이동합니다.");
				        location.href = "/dashboard/site/mng";
				    } else {
				        alert("["+xhr.status+"]오류입니다.\n");
				        location.href = "/dashboard/site/mng";
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
						            name: 'Site',
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
						        title: 'Site'
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
					            name: 'Site',
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
					        title: 'Site'
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
					var chart = tui.chart.lineChart(container1, data1, options1);					 */
			    }
			});
		}
		
		function fnClickMain(){
			
			location.href = "/dashboard/city/mng";
			
		}
		
		function fnClickCity(){
			
			$("#siteForm").attr({action:'/dashboard/area/mng', method:'post'}).submit();
		}

		function fnSiteInfoInsert(){
			
			if($('#sSiteNameText').val() == "") {
				alert("값을 모두 입력해주세요.");
			}else{
				var params = {
						"iCityNum" : $('#iCityNum').val(),
						"iAreaNum" : $('#iAreaNum').val(),
						"sSiteName" : $('#sSiteNameText').val()
				};
				
				$.ajax({
					type : 'post',
					url : '/dashboard/site/insertSiteInfo',
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
					        location.href = "/dashboard/site/mng";
					    } else if(xhr.status == 403) {
					        alert("세션이 만료가 되었습니다. 메인 페이지로 이동합니다.");
					        location.href = "/dashboard/site/mng";
					    } else {
					        alert("["+xhr.status+"]오류입니다.\n");
					        location.href = "/dashboard/site/mng";
							return;	
					    }
					},
					success : function(data) {
						if(data.result == "success") {
							siteList.list();
							fnMeasureSearch();
							
							$('#siteInfoModal').modal('hide');
						}else{
							alert('저장에 실패했습니다.');
						}
					}
				});
			}
		}
		
		function fnDeleteSiteInfoCheck(iSiteNum, sSiteName){
			
			$('#sSiteNameDel').html(sSiteName);
			$('#iSiteNum').val(iSiteNum);
			
			$('#siteDeleteCheckModal').modal();
		}
		
		function fnDeleteSiteInfo(){
			
			var params = {
					"iSiteNum" : $('#iSiteNum').val()
			};
			
			$.ajax({
				type : 'post',
				url : '/dashboard/site/deleteSiteInfo',
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
				        location.href = "/dashboard/site/mng";
				    } else if(xhr.status == 403) {
				        alert("세션이 만료가 되었습니다. 메인 페이지로 이동합니다.");
				        location.href = "/dashboard/site/mng";
				    } else {
				        alert("["+xhr.status+"]오류입니다.\n");
				        location.href = "/dashboard/site/mng";
						return;	
				    }
				},
				success : function(data) {
					if(data.result == "success") {
						siteList.list();
						fnMeasureSearch();
						
						$('#siteDeleteCheckModal').modal('hide');
						
					}else{
						alert('저장에 실패했습니다.');
					}
				}
			});
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
