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
    <title>srtdash - ICO Dashboard</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    
    <link rel="stylesheet" href="/resources/assets/css/bootstrap.min.css">
    <link rel="stylesheet" href="/resources/assets/css/font-awesome.min.css">
    
    <!-- amchart css -->
    <link rel="stylesheet" href="/resources/assets/css/export.css" type="text/css" media="all" />
    
    <!-- others css -->
    
    <link rel="stylesheet" href="/resources/assets/css/default-css.css">
    <link rel="stylesheet" href="/resources/assets/css/styles.css">
    <link rel="stylesheet" href="/resources/assets/css/responsive.css">
	<!-- custom css -->
	<link rel="stylesheet" href="/resources/assets/css/common.css">
</head>

<body>
    
    <form id="systemContForm">
		<input type="hidden" id="iCityNum" name="iCityNum" value="${iCityNum}">
		<input type="hidden" id="sCityName" name="sCityName" value="${sCityName}">
		<input type="hidden" id="iAreaNum" name="iAreaNum" value="${iAreaNum}">
		<input type="hidden" id="sAreaName" name="sAreaName" value="${sAreaName}">
		<input type="hidden" id="iSiteNum" name="iSiteNum" value="${iSiteNum}">
		<input type="hidden" id="sSiteName" name="sSiteName" value="${sSiteName}">
		<input type="hidden" id="iSysNum" name="iSysNum" value="${iSysNum}">
		<input type="hidden" id="sSystemName" name="sSystemName" value="${sSystemName}">
		<input type="hidden" id="iRtuNum" name="iRtuNum" value="${iRtuNum}">
		<input type="hidden" id="iBdNum" name="iBdNum" value="${iBdNum}">
		<input type="hidden" id="iRemoteStatus" name="iRemoteStatus" value="${iRemoteStatus}">
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
				<h4 class="page-title pull-left mt-2">연료전지 제어 화면</h4>
				<ul class="breadcrumbs pull-left mt-2">
					<li><a href="javascript:fnClickMain();">전국</a></li>
					<li><a href="javascript:fnClickCity();">${sCityName}</a></li>
					<li><a href="javascript:fnClickArea();">${sAreaName}</a></li>
					<li><a href="javascript:fnClickSite();">${sSiteName}</a></li>
					<li><a href="javascript:fnClickSystem();">${sSystemName}</a></li>
					<li><span>${sSystemName} 제어</span></li>
				</ul>
				<ul class="right_menu">
					<li class="sign_out"><a href="/logoutProcess" class="btn_signout">LogOut</a></li>
				</ul>
			</div>
            <!-- page title area end -->
            <div class="main-content-inner">
                <div class="sales-report-area mt-5 mb-5">
                    <div class="row">
                        <div class="col-md-3">
                            <div class="card">
                                <div class="card-body">
                                    <h4 class="header-title">펌프</h4>
                                    <div class="trad-history">
                                        <div class="tab-content" id="myTabContent">
                                            <div class="tab-pane fade show active" id="buy_order" role="tabpanel">
                                                <div class="table-responsive">
													<ul class="dbkit-table1">
														<li>
															<div>PUMP1 - 개질 연료 펌프</div>
															<div id="PUMP1" class="flRight">
																<%-- <c:choose>
																	<c:when test="${systemCont.PUMP1 eq 0.0 }">
																		0.00 LPM
																	</c:when>
																	<c:otherwise>
																		${systemCont.PUMP1} LPM
																	</c:otherwise>
																</c:choose> --%>
															</div>
														</li>
														<li>
															<div>PUMP2 - 버너 연료 펌프</div>
															<div id="PUMP2" class="flRight">
																<%-- <c:choose>
																	<c:when test="${systemCont.PUMP2 eq 0.0 }">
																		0.00 LPM
																	</c:when>
																	<c:otherwise>
																		${systemCont.PUMP2} LPM
																	</c:otherwise>
																</c:choose> --%>
															</div>
														</li>
														<li>
															<div>PUMP3 - 공기 펌프</div>
															<div id="PUMP3" class="flRight">
																<%-- <c:choose>
																	<c:when test="${systemCont.PUMP3 eq 0.0 }">
																		0 LPM
																	</c:when>
																	<c:otherwise>
																		<fmt:formatNumber value="${systemCont.PUMP3}" pattern="#,###"/> LPM
																	</c:otherwise>
																</c:choose> --%>
															</div>
														</li>
														<li>
															<div>PUMP4 - 개질 물 펌프</div>
															<div id="PUMP4" class="flRight">
																<%-- <c:choose>
																	<c:when test="${systemCont.PUMP4 eq 0.0 }">
																		0.0 CCM
																	</c:when>
																	<c:otherwise>
																		<fmt:formatNumber value="${systemCont.PUMP4}" pattern="#,###.#"/> CCM
																	</c:otherwise>
																</c:choose> --%>
															</div>
														</li>
													</ul>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>

                            <div class="card mt-5">
                                <div class="card-body">
                                    <h4 class="header-title">온도</h4>
                                    <div class="trad-history">
                                        <div class="tab-content" id="myTabContent">
                                            <div class="tab-pane fade show active" id="buy_order" role="tabpanel">
                                                <div class="table-responsive">
                                                    <ul class="dbkit-table1">
														<li>
															<div>TC01 - 스택 온도계</div>
															<div id="TC1" class="flRight">
																<%-- <c:choose>
																	<c:when test="${systemCont.TC1 eq 0.0 }">
																		ERR ℃
																	</c:when>
																	<c:otherwise>
																		<fmt:formatNumber value="${systemCont.TC1}" pattern="#,###"/> ℃
																	</c:otherwise>
																</c:choose> --%>
															</div>
														</li>
														<li>
															<div>TC02 - 개질기 온도계</div>
															<div id="TC2" class="flRight">
																<%-- <c:choose>
																	<c:when test="${systemCont.TC2 eq 0.0 }">
																		ERR ℃
																	</c:when>
																	<c:otherwise>
																		<fmt:formatNumber value="${systemCont.TC2}" pattern="#,###"/> ℃
																	</c:otherwise>
																</c:choose> --%>
															</div>
														</li>
														<li>
															<div>TC03 - 버너 온도계</div>
															<div id="TC3" class="flRight">
																<%-- <c:choose>
																	<c:when test="${systemCont.TC3 eq 0.0 }">
																		ERR ℃
																	</c:when>
																	<c:otherwise>
																		<fmt:formatNumber value="${systemCont.TC3}" pattern="#,###"/> ℃
																	</c:otherwise>
																</c:choose> --%>
															</div>
														</li>
														<li>
															<div>TC04 - 셀상부 온도계</div>
															<div id="TC4" class="flRight">
																<%-- <c:choose>
																	<c:when test="${systemCont.TC4 eq 0.0 }">
																		ERR ℃
																	</c:when>
																	<c:otherwise>
																		<fmt:formatNumber value="${systemCont.TC4}" pattern="#,###"/> ℃
																	</c:otherwise>
																</c:choose> --%>
															</div>
														</li>
														<li>
															<div>TC05 - HX HOT IN</div>
															<div id="TC5" class="flRight">
																<%-- <c:choose>
																	<c:when test="${systemCont.TC5 eq 0.0 }">
																		ERR ℃
																	</c:when>
																	<c:otherwise>
																		<fmt:formatNumber value="${systemCont.TC5}" pattern="#,###"/> ℃
																	</c:otherwise>
																</c:choose> --%>
															</div>
														</li>
														<li>
															<div>TC06 - HX HOT OUT</div>
															<div id="TC6" class="flRight">
																<%-- <c:choose>
																	<c:when test="${systemCont.TC6 eq 0.0 }">
																		ERR ℃
																	</c:when>
																	<c:otherwise>
																		<fmt:formatNumber value="${systemCont.TC6}" pattern="#,###"/> ℃
																	</c:otherwise>
																</c:choose> --%>
															</div>
														</li>
														<li>
															<div>TC07 - HX COLD OUT</div>
															<div id="TC7" class="flRight">
																<%-- <c:choose>
																	<c:when test="${systemCont.TC7 eq 0.0 }">
																		ERR ℃
																	</c:when>
																	<c:otherwise>
																		<fmt:formatNumber value="${systemCont.TC7}" pattern="#,###"/> ℃
																	</c:otherwise>
																</c:choose> --%>
															</div>
														</li>
														<li>
															<div>TC08 - 폐열회수 온도계</div>
															<div id="TC8" class="flRight">
																<%-- <c:choose>
																	<c:when test="${systemCont.TC8 eq 0.0 }">
																		ERR ℃
																	</c:when>
																	<c:otherwise>
																		<fmt:formatNumber value="${systemCont.TC8}" pattern="#,###"/> ℃
																	</c:otherwise>
																</c:choose> --%>
															</div>
														</li>
														<li>
															<div>TC09 - 배기가스 온도계</div>
															<div id="TC9" class="flRight">
																<%-- <c:choose>
																	<c:when test="${systemCont.TC9 eq 0.0 }">
																		ERR ℃
																	</c:when>
																	<c:otherwise>
																		<fmt:formatNumber value="${systemCont.TC9}" pattern="#,###"/> ℃
																	</c:otherwise>
																</c:choose> --%>
															</div>
														</li>
														<li>
															<div>TC10 - 스택예비 온도계</div>
															<div id="TC10" class="flRight">
																<%-- <c:choose>
																	<c:when test="${systemCont.TC10 eq 0.0 }">
																		ERR ℃
																	</c:when>
																	<c:otherwise>
																		<fmt:formatNumber value="${systemCont.TC10}" pattern="#,###"/> ℃
																	</c:otherwise>
																</c:choose> --%>
															</div>
														</li>
														<li>
															<div>TC11 - 저탕조 온도계</div>
															<div id="TC11" class="flRight">
																<%-- <c:choose>
																	<c:when test="${systemCont.TC11 eq 0.0 }">
																		ERR ℃
																	</c:when>
																	<c:otherwise>
																		<fmt:formatNumber value="${systemCont.TC11}" pattern="#,###"/> ℃
																	</c:otherwise>
																</c:choose> --%>
															</div>
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
                                <div class="card-body">
                                    <h4 class="header-title">유량계</h4>
                                    <div class="trad-history">
                                        <div class="tab-content" id="myTabContent">
                                            <div class="tab-pane fade show active" id="buy_order" role="tabpanel">
                                                <div class="table-responsive">
                                                    <ul class="dbkit-table1">
														<li>
															<div>FL1 - 개질 연료 유량계</div>
															<div id="FL1" class="flRight">
																<%-- <c:choose>
																	<c:when test="${systemCont.FL1 eq 0.0 }">
																		0.00 LPM
																	</c:when>
																	<c:otherwise>
																		${systemCont.FL1} LPM
																	</c:otherwise>
																</c:choose>	 --%>
															</div>
														</li>
														<li>
															<div>FL2 - 버너 연료 유량계</div>
															<div id="FL2" class="flRight">
																<%-- <c:choose>
																	<c:when test="${systemCont.FL2 eq 0.0 }">
																		0.00 LPM
																	</c:when>
																	<c:otherwise>
																		${systemCont.FL2} LPM
																	</c:otherwise>
																</c:choose>	 --%>
															</div>
														</li>
														<li>
															<div>FL3 - 공기 유량계</div>
															<div id="FL3" class="flRight">
																<%-- <c:choose>
																	<c:when test="${systemCont.FL3 eq 0.0 }">
																		0 LPM
																	</c:when>
																	<c:otherwise>
																		<fmt:formatNumber value="${systemCont.FL3}" pattern="#,###"/> LPM
																	</c:otherwise>
																</c:choose>	 --%>
															</div>
														</li>
														<li>
															<div>FL5 - 물펌프 속도</div>
															<div id="FL5" class="flRight">
																<%-- <c:choose>
																	<c:when test="${systemCont.FL5 eq 0.0 }">
																		0.0 CCM
																	</c:when>
																	<c:otherwise>
																		<fmt:formatNumber value="${systemCont.FL5}" pattern="#,###.#"/> CCM
																	</c:otherwise>
																</c:choose>	 --%>
															</div>
														</li>
													</ul>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>

                            <div class="card mt-5">
                                <div class="card-body">
                                    <h4 class="header-title">센서</h4>
                                    <div class="trad-history">
                                        <div class="tab-content" id="myTabContent">
                                            <div class="tab-pane fade show active" id="buy_order" role="tabpanel">
                                                <div class="table-responsive">
                                                    <ul class="dbkit-table1">
														<li>
															<div>SNH - 점화기</div>
															<div id="StatusSNH" class="flRight">
																<%-- <c:choose>
																	<c:when test="${systemCont.getStatusSNH() eq 1 }">
																		ON
																	</c:when>
																	<c:otherwise>
																		OFF
																	</c:otherwise>
																</c:choose> --%>
															</div>
														</li>
														<li>
															<div>3WAY1 - 저탕조 삼방 벨브</div>
															<div id="StatusThreeWayValve" class="flRight">
																<%-- <c:choose>
																	<c:when test="${systemCont.getStatusThreeWayValve() eq 1 }">
																		A
																	</c:when>
																	<c:otherwise>
																		B
																	</c:otherwise>
																</c:choose> --%>
															</div>
														</li>
														<li>
															<div>SEN1 - 순수탱크 저수위</div>
															<div id="StatusWaterLevelSensor" class="flRight">
																<%-- <c:choose>
																	<c:when test="${systemCont.getStatusWaterLevelSensor() eq 1 }">
																		HIGH
																	</c:when>
																	<c:otherwise>
																		LOW
																	</c:otherwise>
																</c:choose> --%>
															</div>
														</li>
														<li>
															<div>PT1 - 개질 연료 압력계</div>
															<div id="PT1" class="flRight">
																<%-- <fmt:formatNumber value="${systemCont.PT1}" pattern="#,###"/> mbar --%>
															</div>
														</li>
														<li>
															<div>PT2 - 배기가스 압력계</div>
															<div id="PT2" class="flRight">
																<%-- <fmt:formatNumber value="${systemCont.PT2}" pattern="#,###"/> pa --%>
															</div>
														</li>
													</ul>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>

                            <div class="card mt-5">
                                <div class="card-body">
                                    <div class="single-table">
                                        <div class="table-responsive">
                                            <table class="table text-center ">
                                                <thead class="bg-secondary">
                                                    <tr class="text-white">
                                                        <th scope="col">인버터 상태</th>
                                                    </tr>
                                                </thead>
                                                <tbody>
                                                    <tr>
                                                        <td>
                                                        	<div id="ErrorInverterErrorCode">
																<%-- <c:choose>
	                                                        		<c:when test="${systemCont.getErrorInverterErrorCode() eq 0 }">
	                                                        			정상
	                                                        		</c:when>
	                                                        		<c:when test="${systemCont.getErrorInverterErrorCode() ge 1 && systemCont.getErrorInverterErrorCode() le 16}">
	                                                        			에러 -1 - ${systemCont.getErrorInverterErrorCode()}
	                                                        		</c:when>
	                                                        		<c:when test="${systemCont.getErrorInverterErrorCode() ge 17 && systemCont.getErrorInverterErrorCode() le 24}">
	                                                        			에러 -2 - ${systemCont.getErrorInverterErrorCode()}
	                                                        		</c:when>
	                                                        		<c:otherwise>
	                                                        			통신에러
	                                                        		</c:otherwise>
	                                                        	</c:choose> --%>
															</div>
                                                        </td>
                                                    </tr>
                                                </tbody>
                                            </table>
                                        </div>
                                    </div>
                                </div>
                            </div>

                            <div class="card mt-5">
                                <div class="card-body">
                                    <div class="single-table">
                                        <div class="table-responsive">
                                            <table class="table text-center ">
                                                <thead class="bg-secondary">
                                                    <tr class="text-white">
                                                        <th scope="col">상태</th>
                                                    </tr>
                                                </thead>
                                                <tbody>
                                                    <tr>
                                                        <td>
                                                        	<div id="StatusOperationStatus">
                                                        		<%-- <c:choose>
	                                                        		<c:when test="${systemCont.getStatusOperationStatus() eq 3 }">
	                                                        			착화 공정
	                                                        		</c:when>
	                                                        		<c:when test="${systemCont.getStatusOperationStatus() eq 4 }">
	                                                        			승온 공정
	                                                        		</c:when>
	                                                        		<c:when test="${systemCont.getStatusOperationStatus() eq 5 }">
	                                                        			운전 공정
	                                                        		</c:when>
	                                                        		<c:when test="${systemCont.getStatusOperationStatus() eq 6 }">
	                                                        			종료 공정
	                                                        		</c:when>
	                                                        		<c:when test="${systemCont.getStatusOperationStatus() eq 7 }">
	                                                        			에러 공정
	                                                        		</c:when>
	                                                        		<c:otherwise>
	                                                        			- - -
	                                                        		</c:otherwise>
	                                                        	</c:choose> --%>
                                                        	</div>
                                                        </td>
                                                    </tr>
                                                </tbody>
                                            </table>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="col-md-3">
                            <div class="card">
                                <div class="card-body">
                                    <h4 class="header-title">밸브</h4>
                                    <div class="trad-history">
                                        <div class="tab-content" id="myTabContent">
                                            <div class="tab-pane fade show active" id="buy_order" role="tabpanel">
                                                <div class="table-responsive">
													<ul class="dbkit-table1">
														<li>
															<div>SOL1 - 개질 연료 밸브</div>
															<div id="StatusSOL1" class="flRight">
																<%-- <c:choose>
																	<c:when test="${systemCont.getStatusSOL1() eq 1}">
																		ON
																	</c:when>
																	<c:otherwise>
																		OFF
																	</c:otherwise>
																</c:choose> --%>
															</div>
														</li>
														<li>
															<div>SOL2 - 버너 연료 밸브</div>
															<div id="StatusSOL2" class="flRight">
																<%-- <c:choose>
																	<c:when test="${systemCont.getStatusSOL2() eq 1}">
																		ON
																	</c:when>
																	<c:otherwise>
																		OFF
																	</c:otherwise>
																</c:choose> --%>
															</div>
														</li>
														<li>
															<div>SOL4 - 안전 밸브</div>
															<div id="StatusSOL4" class="flRight">
																<%-- <c:choose>
																	<c:when test="${systemCont.getStatusSOL4() eq 1}">
																		ON
																	</c:when>
																	<c:otherwise>
																		OFF
																	</c:otherwise>
																</c:choose> --%>
															</div>
														</li>
														<li>
															<div>SOL5 - 개질 물 밸브</div>
															<div id="StatusSOL5" class="flRight">
																<%-- <c:choose>
																	<c:when test="${systemCont.getStatusSOL5() eq 1}">
																		ON
																	</c:when>
																	<c:otherwise>
																		OFF
																	</c:otherwise>
																</c:choose> --%>
															</div>
														</li>
														<li>
															<div>SOL6 - 저탕조 온수 배출 밸브</div>
															<div id="StatusSOL6" class="flRight">
																<%-- <c:choose>
																	<c:when test="${systemCont.getStatusSOL6() eq 1}">
																		ON
																	</c:when>
																	<c:otherwise>
																		OFF
																	</c:otherwise>
																</c:choose> --%>
															</div>
														</li>
													</ul>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>

                            <div class="card mt-5">
                                <div class="card-body">
                                    <h4 class="header-title">인버터</h4>
                                    <div class="trad-history">
                                        <div class="tab-content" id="myTabContent">
                                            <div class="tab-pane fade show active" id="buy_order" role="tabpanel">
                                                <div class="table-responsive">
                                                    <ul class="dbkit-table1">
														<li>
															<div>AC - 전류</div>
															<div id="ACAmpare" class="flRight">
																<%-- <c:choose>
																	<c:when test="${systemCont.getACAmpare() eq 0.0 }">
																		0.0 A
																	</c:when>
																	<c:otherwise>
																		<fmt:formatNumber value="${systemCont.getACAmpare()}" pattern="#,###.#"/> A
																	</c:otherwise>
																</c:choose> --%>
															</div>
														</li>
														<li>
															<div>AC - 전압</div>
															<div id="ACVoltage" class="flRight">
																<%-- <fmt:formatNumber value="${systemCont.getACVoltage()}" pattern="#,###.#"/> V --%>
															</div>
														</li>
														<li>
															<div>AC - 전력</div>
															<div id="ACWatt" class="flRight">
																<%-- <fmt:formatNumber value="${systemCont.getACWatt()}" pattern="#,###"/> W --%>
															</div>
														</li>
														<li>
															<div>DC - 전류</div>
															<div id="DCAmpare" class="flRight">
																<%-- <c:choose>
																	<c:when test="${systemCont.getDCAmpare() eq 0.0 }">
																		0.0 A
																	</c:when>
																	<c:otherwise>
																		<fmt:formatNumber value="${systemCont.getDCAmpare()}" pattern="#,###.#"/> A
																	</c:otherwise>
																</c:choose> --%>
															</div>
														</li>
														<li>
															<div>DC - 전압</div>
															<div id="DCVoltage" class="flRight">
																<%-- <c:choose>
																	<c:when test="${systemCont.getDCVoltage() eq 0.0 }">
																		0.0 V
																	</c:when>
																	<c:otherwise>
																		<fmt:formatNumber value="${systemCont.getDCVoltage()}" pattern="#,###.#"/> V
																	</c:otherwise>
																</c:choose> --%>
															</div>
														</li>
														<li>
															<div>DC - 전력</div>
															<div id="DCWatt" class="flRight">
																<%-- <fmt:formatNumber value="${systemCont.getDCWatt()}" pattern="#,###"/> W --%>
															</div>
														</li>
													</ul>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>

							<div class="card mt-5">
								<div class="card-body">
									<div class="single-table">
										<div class="table-responsive">
											<table class="table text-center ">
												<thead class="bg-secondary">
												<tr class="text-white">
													<th scope="col">원격제어</th>
												</tr>
												</thead>
												<tbody>
												<tr>
													<td>
														<div id="RemoteStatus">
														</div>
													</td>
												</tr>
												</tbody>
											</table>
										</div>
									</div>
								</div>
							</div>
                        </div>
                        <div class="col-md-3">
                            <div>
                                <button type="button" class="btn btn-info btn-lg btn-block">${sSystemName}</button>
                                <br>
                                <br>
                                <button type="button" class="btn btn-success btn-lg btn-block" onclick="openPopup(1)">계측값</button>
                                <button type="button" class="btn btn-success btn-lg btn-block" onclick="openPopup(2)">시뮬레이션(에러 생성)</button>
                                <button type="button" class="btn btn-success btn-lg btn-block" onclick="openPopup(3);">파라메터 변경</button>
                                <button type="button" class="btn btn-success btn-lg btn-block" onclick="openPopup(4);getGainOffset();">정확도 보정</button>                         
                                <br>
                                <br>
                                <button type="button" class="btn btn-secondary btn-lg btn-block" onclick="openPopup(5);getIgniting();">착화 공정</button>
                                <button type="button" class="btn btn-secondary btn-lg btn-block" onclick="openPopup(6);getHeating();">승온 공정</button>
                                <button type="button" class="btn btn-secondary btn-lg btn-block" onclick="openPopup(7);getControll(${iRtuNum},${iBdNum});">운전 공정</button>
                                <button type="button" class="btn btn-secondary btn-lg btn-block" onclick="openPopup(8);getEndWater();">종료 / 저탕조 공정</button>
                                <button type="button" class="btn btn-secondary btn-lg btn-block" onclick="openPopup(9);getError();">에러 조건</button>
                                <button type="button" class="btn btn-secondary btn-lg btn-block" onclick="openPopup(10)">발전 시작</button>
                                <button type="button" class="btn btn-secondary btn-lg btn-block" onclick="openPopup(11)">발전 종료</button>
								<button type="button" class="btn btn-secondary btn-lg btn-block" onclick="openPopup(12)">에러 종료</button>
                            </div>
                            <br>
                            <br>
                            <div>
                            	<c:set var="now" value="<%=new java.util.Date(new java.util.Date().getTime() - 60*60*24*1000) %>" />
								<!-- 일주일 전 JAVA DATE 계산을 하면된다.   -->
								<c:set var="tenDaysAgo" value="<%=new java.util.Date(new java.util.Date().getTime() - 60*60*24*1000*10) %>" />
								
								<c:set var="today"><fmt:formatDate value="${now}" pattern="yyyy-MM-dd" /></c:set>
								<c:set var="tenDaysAgo1"><fmt:formatDate value="${tenDaysAgo}" pattern="yyyy-MM-dd" /></c:set>
                            
                            	<div class="card">
	                                <div class="card-body">
	                                    <h4 class="header-title">엑셀 다운로드</h4>
	                                    <form id="form1" name="form1" method="post" >
	                                    	<input type="hidden" id="rtuId" name="rtuId" value="${iRtuNum}">
	                                    	<input type="hidden" id="sSystemNameExcel" name="sSystemNameExcel" value="${sSystemName}">
		                                    <div class="form-row">
		                                    	<div class="col-md-6">
			                                    	<label for="searchRequstDeBgn" class="col-form-label">시작일</label>
													<input class="form-control" type="date" value="<c:out value="${tenDaysAgo1}" />" id="searchRequstDeBgn" name="searchRequstDeBgn">
			                                    </div>
												<div class="col-md-6">
			                                    	<label for="searchRequstDeEnd" class="col-form-label">종료일</label>
													<input class="form-control" type="date" value="<c:out value="${today}" />" id="searchRequstDeEnd" name="searchRequstDeEnd">
			                                    </div>
		                                    </div>
		                                    <br>
	                                    
										    <button type="button" class="btn btn-secondary btn-lg btn-block" onclick="doExcelDownloadProcess()">다운로드</button>
										</form>
									</div>
								</div>
								<div id ="time" style="float: right; text-align: right;">
						    		
						    	</div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- main content area end -->
    </div>
    <!-- page container area end -->
    
    <!-- jquery latest version -->
    <script src="/resources/assets/js/vendor/jquery-2.2.4.min.js"></script>
    <!-- bootstrap 4 js -->
    <script src="/resources/assets/js/bootstrap.min.js"></script>
    <script src="/resources/assets/js/owl.carousel.min.js"></script>
    <script src="/resources/assets/js/metisMenu.min.js"></script>
    <script src="/resources/assets/js/jquery.slimscroll.min.js"></script>
    <script src="/resources/assets/js/jquery.slicknav.min.js"></script>

    <!-- others plugins -->
    <script src="/resources/assets/js/plugins.js"></script>
    <script src="/resources/assets/js/scripts.js"></script>
    
    <script type="text/javascript">

	    $(function(){

			if($('#iRemoteStatus').val() == '1'){
				$('#RemoteStatus').html("원격제어 불가");
			}else{
				$('#RemoteStatus').html("원격제어 가능");
			}

	    	fnMeasureSearch();
	    	
			// 10초 주기로 데이터 재조회
			setInterval(function(){
				fnMeasureSearch();
			},10000);
			
		})
    
		function fnMeasureSearch() {
			
			var params = {
					"rtuId" : $('#rtuId').val()
			};
			
			$.ajax({
				type : 'post',
				url : '/dashboard/systemCont/measure',
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
				        location.href = "/dashboard/systemCont/mng";
				    } else if(xhr.status == 403) {
				        alert("세션이 만료가 되었습니다. 메인 페이지로 이동합니다.");
				        location.href = "/dashboard/systemCont/mng";
				    } else {
				        alert("["+xhr.status+"]오류입니다.\n");
				        location.href = "/dashboard/systemCont/mng";
						return;	
				    }
				},
			    success : function(data) {
			    	
			    	if(data.systemCont != null){
			    		if(data.systemCont.pump1 == '0.0'){
				    		$('#PUMP1').html("0.00 LPM");
				    	}else{
				    		$('#PUMP1').html(data.systemCont.pump1 + " LPM");	
				    	}
				    	
				    	if(data.systemCont.pump2 == '0.0'){
				    		$('#PUMP2').html("0.00 LPM");
				    	}else{
				    		$('#PUMP2').html(data.systemCont.pump2 + " LPM");	
				    	}
				    	
				    	if(data.systemCont.pump3 == '0.0'){
				    		$('#PUMP3').html("0 LPM");
				    	}else{
				    		$('#PUMP3').html(Math.floor(data.systemCont.pump3) + " LPM");	
				    	}
				    	
				    	$('#PUMP4').html(data.systemCont.pump4 + " CCM");	
				    	
				    	if(data.systemCont.tc1 == '0.0'){
				    		$('#TC1').html("ERR ℃");
				    	}else{
				    		$('#TC1').html(Math.floor(data.systemCont.tc1) + " ℃");	
				    	}
				    	
				    	if(data.systemCont.tc2 == '0.0'){
				    		$('#TC2').html("ERR ℃");
				    	}else{
				    		$('#TC2').html(Math.floor(data.systemCont.tc2) + " ℃");	
				    	}
				    	
				    	if(data.systemCont.tc3 == '0.0'){
				    		$('#TC3').html("ERR ℃");
				    	}else{
				    		$('#TC3').html(Math.floor(data.systemCont.tc3) + " ℃");	
				    	}
				    	
				    	if(data.systemCont.tc4 == '0.0'){
				    		$('#TC4').html("ERR ℃");
				    	}else{
				    		$('#TC4').html(Math.floor(data.systemCont.tc4) + " ℃");	
				    	}
				    	
				    	if(data.systemCont.tc5 == '0.0'){
				    		$('#TC5').html("ERR ℃");
				    	}else{
				    		$('#TC5').html(Math.floor(data.systemCont.tc5) + " ℃");	
				    	}
				    	
				    	if(data.systemCont.tc6 == '0.0'){
				    		$('#TC6').html("ERR ℃");
				    	}else{
				    		$('#TC6').html(Math.floor(data.systemCont.tc6) + " ℃");	
				    	}
				    	
				    	if(data.systemCont.tc7 == '0.0'){
				    		$('#TC7').html("ERR ℃");
				    	}else{
				    		$('#TC7').html(Math.floor(data.systemCont.tc7) + " ℃");	
				    	}
				    	
				    	if(data.systemCont.tc8 == '0.0'){
				    		$('#TC8').html("ERR ℃");
				    	}else{
				    		$('#TC8').html(Math.floor(data.systemCont.tc8) + " ℃");	
				    	}
				    	
				    	if(data.systemCont.tc9 == '0.0'){
				    		$('#TC9').html("ERR ℃");
				    	}else{
				    		$('#TC9').html(Math.floor(data.systemCont.tc9) + " ℃");	
				    	}
				    	
				    	if(data.systemCont.tc10 == '0.0'){
				    		$('#TC10').html("ERR ℃");
				    	}else{
				    		$('#TC10').html(Math.floor(data.systemCont.tc10) + " ℃");	
				    	}
				    	
				    	if(data.systemCont.tc11 == '0.0'){
				    		$('#TC11').html("ERR ℃");
				    	}else{
				    		$('#TC11').html(Math.floor(data.systemCont.tc11) + " ℃");	
				    	}
				    	
				    	if(data.systemCont.fl1 == '0.0'){
				    		$('#FL1').html("0.00 LPM");
				    	}else{
				    		$('#FL1').html(data.systemCont.fl1 + " LPM");	
				    	}
				    	
				    	if(data.systemCont.fl2 == '0.0'){
				    		$('#FL2').html("0.00 LPM");
				    	}else{
				    		$('#FL2').html(data.systemCont.fl2 + " LPM");	
				    	}
				    	
				    	if(data.systemCont.fl3 == '0.0'){
				    		$('#FL3').html("0 LPM");
				    	}else{
				    		$('#FL3').html(Math.floor(data.systemCont.fl3) + " LPM");	
				    	}
				    	
				    	if(data.systemCont.fl5 == '0.0'){
				    		$('#FL5').html("0.0 CCM");
				    	}else{
				    		$('#FL5').html(data.systemCont.fl5 + " CCM");	
				    	}
				    	
				    	if(data.systemCont.statusSNH == '1'){
				    		$('#StatusSNH').html("ON");
				    	}else{
				    		$('#StatusSNH').html("OFF");	
				    	}
				    	
				    	if(data.systemCont.statusThreeWayValve == '1'){
				    		$('#StatusThreeWayValve').html("A");
				    	}else{
				    		$('#StatusThreeWayValve').html("B");	
				    	}
				    	
				    	if(data.systemCont.statusWaterLevelSensor == '1'){
				    		$('#StatusWaterLevelSensor').html("HIGH");
				    	}else{
				    		$('#StatusWaterLevelSensor').html("LOW");	
				    	}
				    	
				    	if(data.systemCont.pt1 == '0.0'){
				    		$('#PT1').html("0 mbar");
				    	}else{
				    		$('#PT1').html(Math.floor(data.systemCont.pt1) + " mbar");	
				    	}
			    		
				    	if(data.systemCont.pt2 == '0.0'){
				    		$('#PT2').html("0 pa");
				    	}else{
				    		$('#PT2').html(Math.floor(data.systemCont.pt2) + " pa");	
				    	}
				    	
				    	if(Number(data.systemCont.errorInverterErrorCode) == '0'){
				    		$('#ErrorInverterErrorCode').html("정상");
				    	}else if(Number(data.systemCont.errorInverterErrorCode) >= '1' && Number(data.systemCont.errorInverterErrorCode) <= '16'){
				    		$('#ErrorInverterErrorCode').html("에러1 - " + data.systemCont.errorInverterErrorCode);	
				    	}else if(Number(data.systemCont.errorInverterErrorCode) >= '17' && Number(data.systemCont.errorInverterErrorCode) <= '24'){
				    		$('#ErrorInverterErrorCode').html("에러2 - " + data.systemCont.errorInverterErrorCode);	
				    	}else{
				    		$('#ErrorInverterErrorCode').html("통신에러");
				    	}
				    	
				    	if(data.systemCont.statusOperationStatus == '3'){
				    		$('#StatusOperationStatus').html("착화 공정");	
				    	}else if(data.systemCont.statusOperationStatus == '4'){
				    		$('#StatusOperationStatus').html("승온 공정");
				    	}else if(data.systemCont.statusOperationStatus == '5'){
				    		$('#StatusOperationStatus').html("운전 공정");
				    	}else if(data.systemCont.statusOperationStatus == '6'){
				    		$('#StatusOperationStatus').html("종료 공정");
				    	}else if(data.systemCont.statusOperationStatus == '7'){
				    		$('#StatusOperationStatus').html("에러 공정");
				    	}else{
				    		$('#StatusOperationStatus').html("- - -");
				    	}
				    	
				    	if(data.systemCont.statusSOL1 == '1'){
				    		$('#StatusSOL1').html("ON");
				    	}else{
				    		$('#StatusSOL1').html("OFF");	
				    	}
				    	
				    	if(data.systemCont.statusSOL2 == '1'){
				    		$('#StatusSOL2').html("ON");
				    	}else{
				    		$('#StatusSOL2').html("OFF");	
				    	}
				    	
				    	if(data.systemCont.statusSOL4 == '1'){
				    		$('#StatusSOL4').html("ON");
				    	}else{
				    		$('#StatusSOL4').html("OFF");	
				    	}
				    	
				    	if(data.systemCont.statusSOL5 == '1'){
				    		$('#StatusSOL5').html("ON");
				    	}else{
				    		$('#StatusSOL5').html("OFF");	
				    	}
				    	
				    	if(data.systemCont.statusSOL6 == '1'){
				    		$('#StatusSOL6').html("ON");
				    	}else{
				    		$('#StatusSOL6').html("OFF");	
				    	}
				    	
				    	if(data.systemCont.statusSOL6 == '1'){
				    		$('#StatusSOL6').html("ON");
				    	}else{
				    		$('#StatusSOL6').html("OFF");	
				    	}
				    	
				    	if(data.systemCont.acampare == '0.0'){
				    		$('#ACAmpare').html("0.0 A");	
				    	}else{
				    		$('#ACAmpare').html(Math.floor(data.systemCont.acampare * 10) / 10 + " A");
				    	}
				    		
				    	if(data.systemCont.acvoltage == '0.0'){
				    		$('#ACVoltage').html("0.0 V");	
				    	}else{
				    		$('#ACVoltage').html(Math.floor(data.systemCont.acvoltage * 10) / 10 + " V");
				    	}
				    	
			   			$('#ACWatt').html(Math.floor(data.systemCont.acwatt) + " W");	

			   			if(data.systemCont.dcampare == '0.0'){
				    		$('#DCAmpare').html("0.0 A");	
				    	}else{
				    		$('#DCAmpare').html(Math.floor(data.systemCont.dcampare * 10) / 10 + " A");
				    	}
			   			
			   			if(data.systemCont.dcvoltage == '0.0'){
				    		$('#DCVoltage').html("0.0 V");	
				    	}else{
				    		$('#DCVoltage').html(Math.floor(data.systemCont.dcvoltage * 10) / 10 + " V");
				    	}
				    	
			   			$('#DCWatt').html(Math.floor(data.systemCont.dcwatt) + " W");
					
			   			$('#mDCVoltage').val(data.systemCont.dcvoltage);
			   			
			   			$('#mAccumulateHeatProduce').val(data.systemCont.accumulateHeatProduce);
			   			
			   			$('#mDCAmpare').val(data.systemCont.dcampare);
			   			
			   			$('#mAccumulateHeatConsume').val(data.systemCont.accumulateHeatConsume);
			   			
			   			$('#mDCWatt').val(data.systemCont.dcwatt);
			   			
			   			$('#mPreDayAccumulateHeatProduce').val(data.systemCont.preDayAccumulateHeatProduce);
			   			
			   			$('#mRearACVoltage').val(data.systemCont.rearACVoltage);
			   			
			   			$('#mInletTemp').val(data.systemCont.inletTemp);
			   			
			   			$('#mRearACAmpare').val(data.systemCont.rearACAmpare);
			   			
			   			$('#mOutletTemp').val(data.systemCont.outletTemp);
			   			
			   			$('#mRearACWatt').val(data.systemCont.rearACWatt);
			   			
			   			$('#mPowerFactor').val(data.systemCont.powerFactor);
			   			
			   			$('#mHeatProduce').val(data.systemCont.heatProduce);
			   			
			   			$('#mFreq').val(data.systemCont.freq);
			   			
			   			$('#mStatusOpONOFF').val(data.systemCont.statusOpONOFF);
			   			
			   			$('#mAccumulateWattProduce').val(data.systemCont.accumulateWattProduce);
			   			
			   			$('#time').html("조회 시각 : " + data.systemCont.timestamp);

			    	}
			    }
			});
		}
		
    	function openPopup(modalNum) {
			if($('#iRemoteStatus').val() == '1' && modalNum != 1){
				alert("원격제어가 불가능합니다.");
			}else{
				switch(modalNum) {
					case 1 :
						$('#measurementsModal').modal();		// 계측값
						break;
					case 2 :
						$('#simulation').modal();				// 시뮬레이션(에러생성)
						break;
					case 3 :
						$('#parameters').modal();				// 파라메터 변경
						break;
					case 4 :
						$('#accuracy').modal();					// 정확도 보정
						break;
					case 5 :
						$('#ignition').modal();					// 착화 공정
						break;
					case 6 :
						$('#temperature').modal();				// 승온 공정
						break;
					case 7 :
						$('#controll').modal();					// 운전 공정
						break;
					case 8 :
						$('#exit').modal();						// 종료/저탕조 공정
						break;
					case 9 :
						$('#error').modal();					// 에러 조건
						break;
					case 10 :
						if (confirm("발전을 시작하시겠습니까?") == true) {
							insertGeneratorMode(1,$('#iRtuNum').val(),$('#iBdNum').val());
						}
						break;
					case 11 :
						if (confirm("발전을 종료하시겠습니까?") == true) {
							insertGeneratorMode(0, $('#iRtuNum').val(), $('#iBdNum').val());
						}
						break;
					case 12 :
						if (confirm("에러 종료하시겠습니까?") == true) {
							insertErrorStopMode($('#iRtuNum').val(), $('#iBdNum').val());
						}
						break;
					default :
						alert("Popup Number Error!!");
						break;
				}
			}

    	}
    	
		function fnClickMain(){
			location.href = "/dashboard/city/mng";
		}
		
		function fnClickCity(){
			$("#systemContForm").attr({action:'/dashboard/area/mng', method:'post'}).submit();
		}
		
		function fnClickArea(){
			$("#systemContForm").attr({action:'/dashboard/site/mng', method:'post'}).submit();
		}
		
		function fnClickSite(){
			$("#systemContForm").attr({action:'/dashboard/system/mng', method:'post'}).submit();
		}
		
		function fnClickSystemDetail(){
			$("#systemContForm").attr({action:'/dashboard/systemDetail/mng', method:'post'}).submit();
		}
		
		function doExcelDownloadProcess(){
	        var f = document.form1;
	        f.action = "/dashboard/systemCont/downloadMeasureExcelFile";
	        f.submit();
	    }

		function fnClickSystem() {
			$("#systemContForm").attr({action:'<c:url value="/dashboard/systemDetail/mng"/>', method:'post'}).submit();
		}
    </script>
    <!-- modal popup 모음 -->
	<%@ include file="modalPopup.jsp" %>
</body>

</html>
