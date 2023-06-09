function collapse(element) {
    let $img = $(".collapse-button")
    var before = document.getElementsByClassName("active")[0]               // 기존에 활성화된 버튼
    if (before && document.getElementsByClassName("active")[0] != element) {  // 자신 이외에 이미 활성화된 버튼이 있으면
        before.nextElementSibling.style.maxHeight = null;   // 기존에 펼쳐진 내용 접고
        before.classList.remove("active");                  // 버튼 비활성화
    }
    element.classList.toggle("active");         // 활성화 여부 toggle
    $img.attr("src", "/css/admin/images/minus.png");
    $img.css("width", "15px");
    $img.css("height", "15px");
    $img.css("margin-left", "60px");

    var content = element.nextElementSibling;
    if (content.style.maxHeight != 0) {         // 버튼 다음 요소가 펼쳐져 있으면
        content.style.maxHeight = null;         // 접기
        $img.attr("src", "/css/admin/images/plus.png");
        $img.css("width", "30px");
        $img.css("height", "40px");
        $img.css("margin-left", "50px");

    } else {
        content.style.maxHeight = content.scrollHeight + "px";  // 접혀있는 경우 펼치기
    }
}

var Target = document.getElementById("clock");
function clock() {
    var time = new Date();

    var month = time.getMonth();
    var date = time.getDate();
    var day = time.getDay();
    var week = ['일', '월', '화', '수', '목', '금', '토'];

    var hours = time.getHours();
    var minutes = time.getMinutes();
    var seconds = time.getSeconds();

    Target.innerText =
        `${month + 1}월 ${date}일 ${week[day]}요일 ` +
        `${hours < 10 ? `0${hours}` : hours}:${minutes < 10 ? `0${minutes}` : minutes}:${seconds < 10 ? `0${seconds}` : seconds}`;

}
clock();
setInterval(clock, 1000); // 1초마다 실행

// 체크박스 전체 선택
$(".checkbox-group").on("click", "#check-all", function () {
    $(this).parents(".checkbox-group").find('input').prop("checked", $(this).is(":checked"));
});

// 체크박스 개별 선택
$(".checkbox-group").on("click", ".normal", function() {
    var is_checked = true;

    $(".checkbox-group .normal").each(function(){
        is_checked = is_checked && $(this).is(":checked");
    });

    $("#check-all").prop("checked", is_checked);
});


var check = $("input[type='checkbox']");
check.click(function(){
    $(this).parents(".right-contents").children(".status").toggle();
});


function modal(id) {
    var zIndex = 9998;
    var modal = $('#my_modal' + id);
    console.log(modal.html());

    // 모달 div 뒤에 희끄무레한 레이어
    var bg = $('<div>')
        .css({
            position: 'fixed',
            zIndex: zIndex,
            left: '0px',
            top: '0px',
            width: '100%',
            height: '100%',
            overflow: 'auto',
            // 레이어 색갈은 여기서 바꾸면 됨
            backgroundColor: 'rgba(0,0,0,0.4)'
        })
        .appendTo('body');

    modal
        .show()
        // 닫기 버튼 처리, 시꺼먼 레이어와 모달 div 지우기
        .find('.modal_close_btn')
        .on('click', function() {
            bg.remove();
            modal.hide();
        })
        .end()
        .css({
            position: 'absolute',
            boxShadow: '0 4px 8px 0 rgba(0, 0, 0, 0.2), 0 6px 20px 0 rgba(0, 0, 0, 0.19)',

            // 시꺼먼 레이어 보다 한칸 위에 보이기
            zIndex: zIndex + 1,

            // div center 정렬
            top: '50%',
            left: '50%',
            transform: 'translate(-50%, -50%)',
            msTransform: 'translate(-50%, -50%)',
            webkitTransform: 'translate(-50%, -50%)'
        });
}


/* manage-reply 게시글 내용 누르면 모달 */
function openModal(id) {
    console.log(id);
    modal(id);
}

