<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="x" uri="http://java.sun.com/jsp/jstl/xml"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
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
    <link href="//cdn.jsdelivr.net/gh/gitbrent/bootstrap4-toggle@3.4.0/css/bootstrap4-toggle.min.css" rel="stylesheet">

</head>
<body>
<form id="adminForm">
    <input type="hidden" id="iCityNum" name="iCityNum">
    <input type="hidden" id="sCityName" name="sCityName">
    <input type="hidden" id="sAuth" name="sAuth" value="${sAuth}">
</form>
<form id="excelAdminForm">
    <input type="hidden" id="sUserId" name="sUserId" value="${sUserId}">
</form>

<div class="main-content">
    <div id="top_menu">
        <h4 class="page-title pull-left mt-2">게스트 엑셀 관리 화면</h4>
        <ul class="right_menu">
            <%--                <li class="user"><strong>${sUserId}</strong> 님</li>--%>
            <%--                <li class="email_setting"><a href="javascript:fnGuestAdminPage();">게스트 관리</a></li>--%>
            <li class="main_page"><a href="javascript:fnCityMngPage();">메인 화면</a></li>
            <li class="email_setting"><a href="javascript:fnEmailAdminPage();">이메일 관리</a></li>
            <li class="sign_out"><a href="/logoutProcess" class="btn_signout">LogOut</a></li>
        </ul>
    </div>

    <div id="container">
            <!-- overview area start -->

            <div id="content">
                <div class="main_title_wp">
                    <h2 class="main_title">게스트 엑셀 관리</h2>
                </div>

                <div class="detail_wrap">
                    <table class="table_detail">
                        <colgroup>
                            <col width="180px">
                            <col width="">
                        </colgroup>
                        <tbody id="sensorListGrid">
                        <c:forEach var="excelList" items="${excelList}" varStatus="status">
                            <tr>
                                <th scope="row">개질연료 유량계</th>
                                <td><input type="checkbox" id="toggle1" ${excelList.column02 eq 1 ? 'checked' : ''} data-toggle="toggle" data-style="ios" data-width="25" data-height="15"></td>
                                <th scope="row">버너연료 유량계</th>
                                <td><input type="checkbox" id="toggle2" ${excelList.column03 eq 1 ? 'checked' : ''} data-toggle="toggle" data-style="ios" data-width="25" data-height="15"></td>
                                <th scope="row">공기 유량계</th>
                                <td><input type="checkbox" id="toggle3" ${excelList.column04 eq 1 ? 'checked' : ''} data-toggle="toggle" data-style="ios" data-width="25" data-height="15"></td>
                                <th scope="row">물펌프 속도</th>
                                <td><input type="checkbox" id="toggle4" ${excelList.column05 eq 1 ? 'checked' : ''} data-toggle="toggle" data-style="ios" data-width="25" data-height="15"></td>
                            </tr>
                            <tr>
                                <th scope="row">스택 온도계</th>
                                <td><input type="checkbox" id="toggle5" ${excelList.column06 eq 1 ? 'checked' : ''} data-toggle="toggle" data-style="ios" data-width="25" data-height="15"></td>
                                <th scope="row">개질기 온도계</th>
                                <td><input type="checkbox" id="toggle6" ${excelList.column07 eq 1 ? 'checked' : ''} data-toggle="toggle" data-style="ios" data-width="25" data-height="15"></td>
                                <th scope="row">버너 온도계</th>
                                <td><input type="checkbox" id="toggle7" ${excelList.column08 eq 1 ? 'checked' : ''} data-toggle="toggle" data-style="ios" data-width="25" data-height="15"></td>
                                <th scope="row">셀상부 온도계</th>
                                <td><input type="checkbox" id="toggle8" ${excelList.column09 eq 1 ? 'checked' : ''} data-toggle="toggle" data-style="ios" data-width="25" data-height="15"></td>
                                <th scope="row">HX HOT(IN) 온도계</th>
                                <td><input type="checkbox" id="toggle9" ${excelList.column10 eq 1 ? 'checked' : ''} data-toggle="toggle" data-style="ios" data-width="25" data-height="15"></td>
                                <th scope="row">HX HOT(OUT) 온도계</th>
                                <td><input type="checkbox" id="toggle10" ${excelList.column11 eq 1 ? 'checked' : ''} data-toggle="toggle" data-style="ios" data-width="25" data-height="15"></td>
                            </tr>
                            <tr>
                                <th scope="row">HX COLD(IN) 온도계</th>
                                <td><input type="checkbox" id="toggle11" ${excelList.column12 eq 1 ? 'checked' : ''} data-toggle="toggle" data-style="ios" data-width="25" data-height="15"></td>
                                <th scope="row">폐열회수 온도계</th>
                                <td><input type="checkbox" id="toggle12" ${excelList.column13 eq 1 ? 'checked' : ''} data-toggle="toggle" data-style="ios" data-width="25" data-height="15"></td>
                                <th scope="row">배기가스 온도계</th>
                                <td><input type="checkbox" id="toggle13" ${excelList.column14 eq 1 ? 'checked' : ''} data-toggle="toggle" data-style="ios" data-width="25" data-height="15"></td>
                                <th scope="row">스택예비 온도계</th>
                                <td><input type="checkbox" id="toggle14" ${excelList.column15 eq 1 ? 'checked' : ''} data-toggle="toggle" data-style="ios" data-width="25" data-height="15"></td>
                                <th scope="row">저탕조 온도계</th>
                                <td><input type="checkbox" id="toggle15" ${excelList.column16 eq 1 ? 'checked' : ''} data-toggle="toggle" data-style="ios" data-width="25" data-height="15"></td>
                            </tr>
                            <tr>
                                <th scope="row">개질연료 압력계</th>
                                <td><input type="checkbox" id="toggle16" ${excelList.column17 eq 1 ? 'checked' : ''} data-toggle="toggle" data-style="ios" data-width="25" data-height="15"></td>
                                <th scope="row">배기가스 압력계</th>
                                <td><input type="checkbox" id="toggle17" ${excelList.column18 eq 1 ? 'checked' : ''} data-toggle="toggle" data-style="ios" data-width="25" data-height="15"></td>

                            </tr>
                            <tr>
                                <th scope="row">DC 전류</th>
                                <td><input type="checkbox" id="toggle18" ${excelList.column21 eq 1 ? 'checked' : ''} data-toggle="toggle" data-style="ios" data-width="25" data-height="15"></td>
                                <th scope="row">DC 전압</th>
                                <td><input type="checkbox" id="toggle19" ${excelList.column22 eq 1 ? 'checked' : ''} data-toggle="toggle" data-style="ios" data-width="25" data-height="15"></td>
                                <th scope="row">DC 발전량</th>
                                <td><input type="checkbox" id="toggle20" ${excelList.column23 eq 1 ? 'checked' : ''} data-toggle="toggle" data-style="ios" data-width="25" data-height="15"></td>
                                <th scope="row">당일누적발전량</th>
                                <td><input type="checkbox" id="toggle21" ${excelList.column27 eq 1 ? 'checked' : ''} data-toggle="toggle" data-style="ios" data-width="25" data-height="15"></td>
                            </tr>
                            <tr>
                                <th scope="row">열생산량</th>
                                <td><input type="checkbox" id="toggle22" ${excelList.column29 eq 1 ? 'checked' : ''} data-toggle="toggle" data-style="ios" data-width="25" data-height="15"></td>
                                <th scope="row">누적 열생산량</th>
                                <td><input type="checkbox" id="toggle23" ${excelList.column30 eq 1 ? 'checked' : ''} data-toggle="toggle" data-style="ios" data-width="25" data-height="15"></td>
                                <th scope="row">누적 열소비량</th>
                                <td><input type="checkbox" id="toggle24" ${excelList.column31 eq 1 ? 'checked' : ''} data-toggle="toggle" data-style="ios" data-width="25" data-height="15"></td>
                                <th scope="row">전일 누적 열 생산량</th>
                                <td><input type="checkbox" id="toggle25" ${excelList.column32 eq 1 ? 'checked' : ''} data-toggle="toggle" data-style="ios" data-width="25" data-height="15"></td>
                            </tr>

                        </c:forEach>
                        </tbody>
                    </table>
                </div>

                <div class="btn_bottom">
                    <button type="button" class="buttons color" onclick="fncUpdate(); return false;">저장</button>
                    <button type="button" class="buttons cancle" onclick="fnGuestAdminPage(); return false;">취소</button>
                </div>
            </div>



                </div>
            </div>
            <!-- overview area end -->
    </div>
</div>
<style>
    .toggle.ios, .toggle-on.ios, .toggle-off.ios { border-radius: 20rem; }
    .toggle.ios .toggle-handle { border-radius: 20rem; }
</style>

<!-- bootstrap 4 js -->
<script src="/resources/assets/js/bootstrap.min.js"></script>
<script src="/resources/assets/js/owl.carousel.min.js"></script>
<script src="/resources/assets/js/metisMenu.min.js"></script>
<script src="/resources/assets/js/jquery.slimscroll.min.js"></script>
<script src="/resources/assets/js/jquery.slicknav.min.js"></script>

<script src="//cdn.jsdelivr.net/gh/gitbrent/bootstrap4-toggle@3.4.0/js/bootstrap4-toggle.min.js"></script>
<script type="text/javascript">
    function fncReset(){
        console.log($('#toggle1').prop('checked')==true?1:0);
        console.log($('#toggle1').prop('checked'));
        console.log($('#toggle1').prop('checked'));
    }

    function fncUpdate(){
        var params = {
            "sUserId" : $('#sUserId').val(),
            "column02" : ($('#toggle1').prop('checked')==true?1:0),
            "column03" : ($('#toggle2').prop('checked')==true?1:0),
            "column04" : ($('#toggle3').prop('checked')==true?1:0),
            "column05" : ($('#toggle4').prop('checked')==true?1:0),
            "column06" : ($('#toggle5').prop('checked')==true?1:0),
            "column07" : ($('#toggle6').prop('checked')==true?1:0),
            "column08" : ($('#toggle7').prop('checked')==true?1:0),
            "column09" : ($('#toggle8').prop('checked')==true?1:0),
            "column10" : ($('#toggle9').prop('checked')==true?1:0),
            "column11" : ($('#toggle10').prop('checked')==true?1:0),
            "column12" : ($('#toggle11').prop('checked')==true?1:0),
            "column13" : ($('#toggle12').prop('checked')==true?1:0),
            "column14" : ($('#toggle13').prop('checked')==true?1:0),
            "column15" : ($('#toggle14').prop('checked')==true?1:0),
            "column16" : ($('#toggle15').prop('checked')==true?1:0),
            "column17" : ($('#toggle16').prop('checked')==true?1:0),
            "column18" : ($('#toggle17').prop('checked')==true?1:0),
            "column21" : ($('#toggle18').prop('checked')==true?1:0),
            "column22" : ($('#toggle19').prop('checked')==true?1:0),
            "column23" : ($('#toggle20').prop('checked')==true?1:0),
            "column27" : ($('#toggle21').prop('checked')==true?1:0),
            "column29" : ($('#toggle22').prop('checked')==true?1:0),
            "column30" : ($('#toggle23').prop('checked')==true?1:0),
            "column31" : ($('#toggle24').prop('checked')==true?1:0),
            "column32" : ($('#toggle25').prop('checked')==true?1:0)

        };

        $.ajax({
            type : 'post',
            url : '/admin/updateExcelInfo',
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
                    location.href = "/dashboard/city/mng";
                } else if(xhr.status == 403) {
                    alert("세션이 만료가 되었습니다. 메인 페이지로 이동합니다.");
                    location.href = "/dashboard/city/mng";
                } else {
                    alert("["+xhr.status+"]오류입니다.\n");
                    location.href = "/dashboard/city/mng";
                    return;
                }
            },
            success : function(data) {
                if(data.result == "success") {

                    $("#adminForm").attr({action:'<c:url value="/admin/guestAdminPage"/>', method:'post'}).submit();
                }else{
                    alert('저장에 실패했습니다.');
                }
            }
        });
    }

    function fnCityMngPage(){
        location.href = "/dashboard/city/mng";
    }

    function fnEmailAdminPage(){
        $("#adminForm").attr({action:'<c:url value="/admin/emailAdminPage"/>', method:'post'}).submit();
    }

    function fnGuestAdminPage(){
        $("#adminForm").attr({action:'<c:url value="/admin/guestAdminPage"/>', method:'post'}).submit();
    }

</script>

</body>
</html>
