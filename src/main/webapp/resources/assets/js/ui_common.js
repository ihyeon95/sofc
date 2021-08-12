/* jquery ui 달력 */
jQuery(function($) {
	$.datepicker.regional['ko'] = {
		closeText : '닫기',
		prevText : '이전달',
		nextText : '다음달',
		currentText : '오늘',
		monthNames : [ '1월', '2월', '3월', '4월', '5월', '6월', '7월', '8월', '9월',
				'10월', '11월', '12월' ],
		monthNamesShort : [ '1월', '2월', '3월', '4월', '5월', '6월', '7월', '8월',
				'9월', '10월', '11월', '12월' ],
		dayNames : [ '일', '월', '화', '수', '목', '금', '토' ],
		dayNamesShort : [ '일', '월', '화', '수', '목', '금', '토' ],
		dayNamesMin : [ '일', '월', '화', '수', '목', '금', '토' ],
		dateFormat : 'yy-mm-dd',
		showMonthAfterYear : true,
		changeYear : true,
		changeMonth : true,
		showOn : 'button',
		buttonImage : '/resources/img/ico_calender.png',
		buttonImageOnly : true
	};
	$.datepicker.setDefaults($.datepicker.regional['ko']);
});

$(document).ready(function(){
	
	//GNB
	$("#header .gnbMenu > li > a").mouseenter(function(e){
		e.preventDefault();
        if($(this).parent().children("div").length > 0){ 
            if($(this).parent().hasClass("active")){
				$("#overlay").hide();
                $("#header .gnbMenu > li").removeClass("active");
				$(this).parent().removeClass("active");
                $("#header").removeClass("over");
            }else{
				$("#overlay").show();
                $("#header .gnbMenu > li").removeClass("active");
                $(this).parent().addClass("active");
                $("#header").addClass("over");
            }
        }
    });	
	
	$("#header").mouseleave(function(){
	    $("#header .gnbMenu > li").removeClass("active");
	    $("#header").removeClass("over");
	    $("#overlay").hide();
	});

	tb_list(); // 20191126 추가
	selectTime(); // 20191126 추가
});


function fixDataOnWheel(){
    if(event.wheelDelta < 0){
        DataScroll.doScroll('scrollbarDown');
    }else{
        DataScroll.doScroll('scrollbarUp');
    }
    dataOnScroll();
}

function dataOnScroll() {
    right_header.scrollLeft = right_contents.scrollLeft;
}

//마스크 띄우기
function wrapWindowByMask() { 

	var mask = $("#lay_mask");

	//화면의 높이와 너비를 구한다. 
	var maskHeight = $(document).height();
	var maskWidth = $(window).width();

	//마스크의 높이와 너비를 화면 것으로 만들어 전체 화면을 채운다. 
	mask.css({'width':maskWidth,'height':maskHeight});
	mask.show();
}
// 사이즈 리사이징
function ResizingLayer() {
	if($(".PopupLayer").css("visibility") == "visible") {
		//화면의 높이와 너비를 구한다. 
		var maskHeight = $(document).height();
		var maskWidth = $(window).width();

		//마스크의 높이와 너비를 화면 것으로 만들어 전체 화면을 채운다. 
		$("#lay_mask").css({'width':maskWidth,'height':maskHeight});
		//$('#header').css({'width':maskWidth}); // 20131119 최창원 수정 헤더의 넓이 값을 우선 빼 봤음.

		$(".PopupLayer").each(function () {
			var left = ( $(window).scrollLeft() + ($(window).width() - $(this).width()) / 2 );
			var top = ( $(window).scrollTop() + ($(window).height() - $(this).height()) / 2 );

			if(top<0) top = 0;
			if(left<0) left = 0;

			$(this).css({"left":left, "top":top});
		});
	}
}

window.onresize = ResizingLayer;
//레이어 가운데 띄우고 마스크 띄우기
function toggleLayer( obj, s ) {

	var zidx = $("#lay_mask").css("z-index");
	if(s == "on") {
		//화면중앙에 위치시키기
		var left = ( $(window).scrollLeft() + ($(window).width() - obj.width()) / 2 );
		var top = ( $(window).scrollTop() + ($(window).height() - obj.height()) / 2 );
		
		// 높이가 0이하면 0으로 변경
		if(top<0) top = 0;
		if(left<0) left = 0;

		if($(".PopupLayer").length > 0) {  //20200117수정
			var layer_idx = Number(zidx) + 10;
		}

		$("#lay_mask").css("z-index", layer_idx);
		// console.log('on일경우 '+ $("#lay_mask").css('z-index'))
		obj.css({"left":left, "top":top, "z-index":layer_idx+1}).addClass("PopupLayer");  //20200117수정
		$("body").append(obj);

		wrapWindowByMask();//배경 깔기
		//obj.show();//레이어 띄우기
		obj.css('visibility','visible');//레이어 띄우기
		obj.css('overflow','visible');
		obj.css({"left":left, "top":top});

		if(obj.height() > $(window).height()){
			obj.css({"left":left, "top": $(window).scrollTop() + 20});
		}else{
			obj.css({"left":left, "top":top});
		}
	}

	if(s == "off") {
		if($(".PopupLayer").length > 1) {
			//var layer_idx = zidx - 10;
		} else {
			$("#lay_mask").hide();
		}

		obj.removeClass("PopupLayer").css('visibility','hidden');
		obj.css('top','-9999px');
	}

	if(s == "off2") { //레이어에서 다른 레이어를 띄울 경우 마스크는 안닫기 위한 처리
	  	//20200117수정
		if($(".PopupLayer").length > 1) {
			var layer_idx = zidx - 10;
		}
		$("#lay_mask").css("z-index", layer_idx);
		obj.removeClass("PopupLayer").css('visibility','hidden');
	}
}


// dateRegStart, dateRegEnd class에 달력모양 넣기
function _datePickerMade(){
	$("input.dateRegStart, input.dateRegEnd").datepicker();
	$("input.dateRegStart, input.dateRegEnd").mask("9999-99-99");
	$("input.dateRegStart").datepicker( "option", "dateFormat", "yy-mm-dd" );
	$("input.dateRegEnd").datepicker( "option", "dateFormat", "yy-mm-dd" );
	$("input.dateRegStart").val("");
	$("input.dateRegEnd").val("");
}

$(window).on("load", function(){
	if($('.dateRegStart, .dateRegEnd').length > 0){
		_datePickerMade();
	}
});




// 20191126 추가
function tb_list(){
	$('.tb_list tr').on('mouseover',function(){
		if($(this).find('.link').length > 0){
			$(this).addClass('hover');
		}
	})
	$('.tb_list tr').on('mouseleave',function(){
		$(this).removeClass('hover');
	})
}
//블럭선택
function showBlock(tg) {
	var ct_left =$(tg).parents('.block_wp').find('.ct_box.left');
	var ct_right = ct_left.next('.right');
	ct_left.addClass('on');
	$(tg).addClass('on');
	ct_right.find('.view.list').removeClass('on').siblings('.detail').addClass('on');
}
//블럭닫기
function closeBlock(tg) {
	var ct_left =$(tg).parents('.block_wp').find('.ct_box.left');
	var ct_right = ct_left.next('.right');
	ct_left.removeClass('on').find('.bx').removeClass('on')
	$(tg).addClass('on');
	ct_right.find('.view.detail').removeClass('on').siblings('.list').addClass('on');


	var pop = $('.pop_selectTimet');
	pop.removeClass('on').removeAttr('style')
	$('.dim.white').removeClass('on')
}
// 시간선택
function selectTime(){
	var pop = $('.pop_selectTimet');
	$('.block_wp .view.detail .b_time').on('click',function(){
		var td = $(this).parents('td');
		var thH = td.parents('.tb_list').find('thead th').outerHeight();
		var trIdx = td.parents('tr').index() + 1;
		var tdH = td.outerHeight() * trIdx;
		// var top = td.offset().top;
		var top = thH +tdH;
		var scllTop = $(this).parents('.tb_list.scroll').scrollTop();
		var offTop = td.offset().top;
		var tboffTop = td.parents('.tb_list').offset().top;

		if(scllTop == 0){
			pop.addClass('on').css('top', top)
		}else{
			pop.addClass('on').css('top', offTop - (tboffTop - 37))
		}
		$('.dim.white').addClass('on')
	})
	$('.pop_selectTimet .btn.gray').on('click',function(){
		pop.removeClass('on').removeAttr('style')
		$('.dim.white').removeClass('on')
	})
}