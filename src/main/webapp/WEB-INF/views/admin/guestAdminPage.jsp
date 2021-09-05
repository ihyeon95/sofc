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

</head>
<body>
    <form id="adminForm">
        <input type="hidden" id="iCityNum" name="iCityNum">
        <input type="hidden" id="sCityName" name="sCityName">
        <input type="hidden" id="sAuth" name="sAuth" value="${sAuth}">
    </form>
    <form id="excelAdminForm">
        <input type="hidden" id="sUserId" name="sUserId">
    </form>

    <div class="main-content">
        <div id="top_menu">
            <h4 class="page-title pull-left mt-2">게스트 관리 화면</h4>
            <ul class="right_menu">
<%--                <li class="user"><strong>${sUserId}</strong> 님</li>--%>
<%--                <li class="email_setting"><a href="javascript:fnGuestAdminPage();">게스트 관리</a></li>--%>
                <li class="main_page"><a href="javascript:fnCityMngPage();">메인 화면</a></li>
                <li class="email_setting"><a href="javascript:fnEmailAdminPage();">이메일 관리</a></li>
                <li class="sign_out"><a href="/logoutProcess" class="btn_signout">LogOut</a></li>
            </ul>
        </div>

        <div class="sales-report-area mt-2 mb-2">
            <div class="col-md-12">
                <!-- overview area start -->
                <div class="card">
                    <div class="card-body" style="overflow:auto;">
                        <div class="d-flex justify-content-between align-items-center mt-2">
                            <h4 class="header-title mb-0  mt-2">게스트 리스트</h4>
                        </div>
                        <br>

                        <div class="pull-right">
                            <button type="button" class="btn btn-info btn-xs mb-3" data-toggle="modal" data-target="#cityInfoModal">추가</button>
<%--                            <button type="button" class="btn btn-info btn-xs mb-3" data-toggle="modal" onclick="javascript:fnGuestInsert();">추가</button>--%>
                            <button type="button" class="btn btn-info btn-xs mb-3" data-toggle="modal" onclick="javascript:fnGuestDelete();">삭제</button>
                        </div>

                        <div class="table-responsive" style="height:500px;">
                            <table class="table text-center ">
                                <thead class="bg-dark">
                                <tr class="text-white">
                                    <th scope="col">선택</th>
                                    <th scope="col">이름</th>
                                    <th scope="col">사용자 ID</th>
                                    <th scope="col">사용자 PW</th>
                                    <th scope="col">도시명</th>
                                    <th scope="col">지역명</th>
                                    <th scope="col">사이트명</th>
                                    <th scope="col">엑셀</th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:choose>
                                    <c:when test="${fn:length(guestList) == 0}">
                                        <tr>
                                            <td colspan="6" class="blank_area type_white">
                                                조회된 데이터가 없습니다.
                                            </td>
                                        </tr>
                                    </c:when>
                                    <c:otherwise>
                                        <c:forEach var="guestList" items="${guestList}" varStatus="status">
                                            <tr>
                                                <td><input type="checkbox" name="user_CheckBox" ></td>
                                                <td><c:out value="${guestList.sUserName}"/></td>
                                                <td><c:out value="${guestList.sUserId}"/></td>
                                                <td><c:out value="${guestList.sUserPassword}"/></td>
                                                <td><c:out value="${guestList.sCityName}"/></td>
                                                <td><c:out value="${guestList.sAreaName}"/></td>
                                                <td><c:out value="${guestList.sSiteName}"/></td>
                                                <td><button type="button" class="tdbtn" title="엑셀 관리" onclick="fnExcelAdmin('${guestList.sUserId}'); return false;">엑셀 관리</button></td>
                                            </tr>
                                        </c:forEach>
                                    </c:otherwise>
                                </c:choose>
                                </tbody>
                            </table>
                        </div>

                    </div>
                </div>
                <!-- overview area end -->
            </div>
        </div>
    </div>



    <div class="modal fade" id="cityInfoModal">
        <div class="modal-dialog modal-dialog-centered" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title">게스트 추가</h5>
                    <button type="button" class="close" data-dismiss="modal"><span>&times;</span></button>
                </div>
                <div class="modal-body">
                    <div class="form-row">
                        <div class="col-md-12 mb-3">
                            <label for="sUserName" class="col-form-label">이름</label>
                            <input class="form-control" type="text" value="" id="sUserName">

                            <label for="userId" class="col-form-label">사용자 ID</label>
                            <input class="form-control" type="text" value="" id="userId">

                            <label for="sUserPassword" class="col-form-label">사용자 PW</label>
                            <input class="form-control" type="text" value="" id="sUserPassword">

                            <label for="sCityName" class="col-form-label">도시명</label>
                            <select class="form-control" name="citySelect" id="citySelect" onchange="fnGetAreaName(this.value)">
                                <option value="option1" selected disabled hidden>--도시명--</option>
                                <c:forEach items="${cityNameList}" var="cityNameList">
                                    <option value="${cityNameList.iCityNum}"><c:out value="${cityNameList.sCityName}"/></option>
                                </c:forEach>
                            </select>

                            <label for="sAreaName" class="col-form-label">지역명</label>
                            <select class="form-control" name="areaSelect" id="areaSelect" onchange="fnGetSiteName(this.value)">
                                <option value="option2" selected disabled hidden>--지역명--</option>
                            </select>

                            <label for="sSiteName" class="col-form-label">사이트명</label>
                            <select class="form-control" name="siteSelect" id="siteSelect">
                                <option value="option3" selected disabled hidden>--사이트명--</option>
                            </select>
                        </div>

                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-dismiss="modal">취소</button>
                    <button type="button" class="btn btn-primary" onclick="javascript:fnGuestInfoInsert();">저장</button>
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

    <script type="text/javascript">

        function fnExcelAdmin(sUserId){

            $("#sUserId").val(sUserId);

            $("#excelAdminForm").attr({action:'<c:url value="/admin/excelAdminPage"/>', method:'post'}).submit();
        }

        function fnEmailAdminPage(){
            $("#adminForm").attr({action:'<c:url value="/admin/emailAdminPage"/>', method:'post'}).submit();
        }

        function fnGetAreaName(sParam){
            var $target = $("select[name='areaSelect']");

            $target.empty();
            if(sParam == ""){
                $target.append("<option value=''>--지역명--</option>");
                return;
            }

            $.ajax({
                type : 'post',
                url : '/admin/areaSelect',
                async : false,
                data : {iCityNum : sParam},
                dataType : 'JSON',
                success : function (jdata){
                    if(jdata.length == 0){
                        $target.append("<option value=''>--지역명--</option>");
                    }else{
                        $target.append("<option value='' selected disabled hidden>--지역명--</option>");
                        $(jdata).each(function (i){
                            $target.append("<option value='"+jdata[i].iAreaNum +"'>"+jdata[i].sAreaName +"</option>");
                        })
                    }
                }, error:function (xhr){
                    alert("error");
                    return;
                }
            });
        }

        function fnGetSiteName(sParam){

            var selectCity = $("#citySelect option:selected").val();
            var $target = $("select[name='siteSelect']");

            $target.empty();
            if(sParam == ""){
                $target.append("<option value=''>--사이트명--</option>");
                return;
            }

            console.log(sParam);
            console.log(selectCity);

            $.ajax({
                type : 'post',
                url : '/admin/siteSelect',
                async : false,
                data : {iAreaNum : sParam, iCityNum : selectCity},
                dataType : 'JSON',
                success : function (jdata){
                    if(jdata.length == 0){
                        $target.append("<option value=''>--사이트명--</option>");
                    }else{
                        $target.append("<option value='' selected disabled hidden>--사이트명--</option>");
                        $(jdata).each(function (i){
                            $target.append("<option value='"+jdata[i].iSiteNum +"'>"+jdata[i].sSiteName +"</option>");
                        })
                    }
                }, error:function (xhr){
                    alert("error");
                    return;
                }
            });
        }

        function fnGuestInfoInsert(){

            if($('#sUserName').val() == "" || $('#sUserId').val() == "" || $('#sUserPassword').val() == "" || $('#citySelect').val() == "" || $('#areaSelect').val() == "" || $('#siteSelect').val() == "") {
                alert("값을 모두 입력해주세요.");
            }else{
                var params = {
                    "sUserName" : $('#sUserName').val(),
                    "sUserId" : $('#sUserId').val(),
                    "sUserPassword" : $('#sUserPassword').val(),
                    "iCityNum" : $("#citySelect option:selected").val(),
                    "iAreaNum" : $("#areaSelect option:selected").val(),
                    "iSiteNum" : $("#siteSelect option:selected").val()
                };

                $.ajax({
                    type : 'post',
                    url : '/admin/insertGuestInfo',
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

                            $('#sUserName').val("");
                            $('#sUserId').val("");
                            $('#sUserPassword').val("");
                            $('#citySelect').find('option:first').attr('selected', 'selected');
                            $('#areaSelect').find('option:first').attr('selected', 'selected');
                            $('#siteSelect').find('option:first').attr('selected', 'selected');


                            $("#adminForm").attr({action:'<c:url value="/admin/guestAdminPage"/>', method:'post'}).submit();
                        }else{
                            alert('저장에 실패했습니다.');
                        }
                    }
                });
            }
        }


        function fnGuestDelete(){

            var checkedGuestList = [];
            var checkbox = $("input[name=user_CheckBox]:checked");

            checkbox.each(function(i) {
                var tr = checkbox.parent().parent().eq(i);
                var td = tr.children();
                checkedGuestList.push(td.eq(2).text());
            });
            console.log(checkedGuestList);
            if(checkedGuestList.length == 0) {
                alert("삭제할 게스트를 선택해주세요.");
            }else{
                var params = {
                    "checkedGuestList" : checkedGuestList
                };

                $.ajax({
                    type : 'post',
                    url : '/admin/deleteGuest',
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
                            alert('삭제에 실패했습니다.');
                        }
                    }
                });
            }
        }

        function fnCityMngPage(){
            location.href = "/dashboard/city/mng";
            <%--$("#adminForm").attr({action:'<c:url value="/dashboard/city/mng"/>', method:'GET'}).submit();--%>
        }
    </script>

</body>
</html>
