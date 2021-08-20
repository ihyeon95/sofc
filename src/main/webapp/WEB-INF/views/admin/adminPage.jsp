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

</head>
<body>
    <div class="main-content">
        <div class="sales-report-area mt-2 mb-2">
            <div class="col-md-12">
                <!-- overview area start -->
                <div class="card">
                    <div class="card-body" style="overflow:auto;">
                        <div class="d-flex justify-content-between align-items-center mt-2">
                            <h4 class="header-title mb-0  mt-2">이메일 추가</h4>
                        </div>
                        <br>


                        <form class="needs-validation" action='<c:url value="/admin/insertEmail"/>' method="post">
                            <div class="form-row">
                                <div class="col-md-5 mb-3">
                                    <label for="sName">이름</label>
                                   <input type="text" class="form-control" id="sName" name="sName" required="">
                                </div>
                                <div class="col-md-7 mb-3">
                                    <label for="sEmail">이메일</label>
                                    <input type="text" class="form-control" id="sEmail" name="sEmail" required="">
                                </div>
                            </div>
                            <button class="btn btn-primary" type="button" onclick="javascript:fnEmailInsert();">등록</button>
                        </form>



                    </div>
                </div>
                <!-- overview area end -->
            </div>
        </div>


        <div class="sales-report-area mt-2 mb-2">
            <div class="col-md-12">
                <!-- overview area start -->
                <div class="card">
                    <div class="card-body" style="overflow:auto;">
                        <div class="d-flex justify-content-between align-items-center mt-2">
                            <h4 class="header-title mb-0  mt-2">이메일 리스트</h4>
                        </div>
                       <br>

<%--                        <div class="pull-right">--%>
<%--                            <button type="button" class="btn btn-info btn-xs mb-3" data-toggle="modal" onclick="javascript:fnEmailInsert();">추가</button>--%>
<%--                        </div>--%>

                        <div class="table-responsive" style="height:350px;">
                            <table class="table text-center ">
                                <thead class="bg-dark">
                                <tr class="text-white">
                                    <th scope="col">이름</th>
                                    <th scope="col">이메일</th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:choose>
                                    <c:when test="${fn:length(emailList) == 0}">
                                        <tr>
                                            <td colspan="5" class="blank_area type_white">
                                                조회된 데이터가 없습니다.
                                            </td>
                                        </tr>
                                    </c:when>
                                    <c:otherwise>
                                        <c:forEach var="emailList" items="${emailList}" varStatus="status">
                                            <tr>
                                                <td><c:out value="${emailList.sName}"/></td>
                                                <td><c:out value="${emailList.sEmail}"/></td>
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

    <!-- bootstrap 4 js -->
    <script src="/resources/assets/js/bootstrap.min.js"></script>
    <script src="/resources/assets/js/owl.carousel.min.js"></script>
    <script src="/resources/assets/js/metisMenu.min.js"></script>
    <script src="/resources/assets/js/jquery.slimscroll.min.js"></script>
    <script src="/resources/assets/js/jquery.slicknav.min.js"></script>

    <script type="text/javascript">

        function fnEmailInsert(){

            if($('#sName').val() == "" || $('#sEmail').val() == "") {
                alert("값을 모두 입력해주세요.");
            }else{
                var params = {
                    "sName" : $('#sName').val(),
                    "sEmail" : $('#sEmail').val()
                };

                $.ajax({
                    type : 'post',
                    url : '/admin/insertEmail',
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
                            cityList.list();
                            fnMeasureSearch();

                            $('#sName').val("");
                            $('#sEmail').val("");
                        }else{
                            alert('저장에 실패했습니다.');
                        }
                    }
                });
            }
        }
    </script>


</body>
</html>
