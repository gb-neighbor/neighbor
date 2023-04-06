/*admin.js부분*/
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


/*html에 있던 부분*/
const $changePageTags = $(".page-button");
const $searchButton = $("#search-button");
const $replySearch = $(".reply-search");
page = 1;
let keyword = null;
load(page, keyword);
// 검색창에서 키보드를 눌렀을 때
$('.reply-search-form').on('keydown', function(e) {
    if (e.keyCode == 13) { // Enter 키를 눌렀을 때
        e.preventDefault(); // 기본 이벤트 막기
    }
});
function showList(replyDTOS) {
    replyDTOS = replyDTOS || [];
    console.log("showlist" + page);
    const $results = $('.checkbox-group');
    var text = "";
    var content = "";
    replyDTOS.forEach(reply => {
        content += `
            <div class="item-list-contents">
                <div class="checkbox-zip">
                    <input type="checkbox" name="checkbox" id="check1" class="normal" value="">
                </div>
                <div class="reply-num">${reply.replyId}</div>
                <div class="board-num">${reply.boardId}</div>
                <div class="region">${reply.replyRegionKo}</div>
                <div class="writer-nick">${reply.memberNickname}</div>
                <div class="reply-content">
                    <span id="${reply.replyId}" onclick="openModal(${reply.replyId})">${reply.replyContent}</span>
                    <div id="my_modal${reply.replyId}" class="my_modal">
                        <a class="modal_close_btn">✖</a>
                        <div class="comment-writer"> 작성자 : <span>${reply.memberNickname}</span></div>
                        <div class="comment-detail"> 내용 : <span>${reply.replyContent}</span></div>
                    </div>
                </div>
                <div class="reply-day">${reply.replyRegisterDate}</div>
            </div>`
    });
    $('.item-list-contents').remove();
    if ($("#check-all").prop("checked")) {
        $("#check-all").prop("checked", false);
    }
    $results.append(content);

} // showlist 끝

// 페이지를 텍스트로 가져오기 성공!
function showPage(pageDTO) {
    console.log("showpage" + page);
    const $btns = $('.page-button-box');
    const criteria = pageDTO.criteria;
    let text = "";
    $btns.empty(); // 이전에 생성한 페이지 버튼 제거
    if (pageDTO.prev) {
        text += `<a class="page-button" data-page="${pageDTO.startPage - 1}"><code><</code></a>`;
    }
    for (let i = pageDTO.startPage; i <= pageDTO.endPage; i++) {
        if (criteria.page === i) {
            text += `<a class="page-button" data-page="${i}">${i}</a>`;
        } else {
            text += `<a class="page-button" data-page="${i}">${i}</a>`;
        }
    }
    if (pageDTO.next) {
        text += `<a class="page-button" data-page="${pageDTO.endPage + 1}"><code>></code></a>`;
    }
    $btns.append(text);
    $("input[name='page']").val(criteria.page);

    // 클릭 시 css가 변하는 이벤트 추가, css가 제대로 작동하는지 확인 필요. 최종 전에 확인하기
    $('.page-button').on('click', function(e) {
        e.preventDefault();
        page = $(this).data('page');
        keyword = $replySearch.val();
        load(page,keyword);
        $('.page-button.active').removeClass('active');
        $(this).addClass('active');
    }).on('mouseenter', function() {
        $(this).css('cursor', 'pointer');
    }).on('mouseleave', function() {
        $(this).css('cursor', 'auto');
    });
}

$searchButton.on("click", function() {
    page = 1;
    keyword = $replySearch.val();
    load(page,keyword);
});

$replySearch.on("keyup", function() {
    page = 1;
    keyword = $replySearch.val();
    load(page,keyword);
});

function load(page, keyword) {
    $.ajax({
        url: `/admin/reply/page/keyword`,
        type: "get",
        data: {
            page: page,
            keyword: keyword
        },
        success: function (result) {
            console.log("load" + page);
            // replyDTO
            $('.total-mentor-num').html(result.replyTotal);
            showList(result.replyDTOS);
            showPage(result.pageDTO);
        }
    });
}

/* delete 버튼 클릭 시 ajax실행*/

$('.admin-delete-check').on('click', function () {
    var checkedIds = new Array();
    // 체크 박스 체크된 값
    $('input:checkbox[name=checkbox]:checked').parent().parent().find('.reply-num').each(function () {
        console.log(this.innerText);
        checkedIds.push(this.innerText);
    });
    $.ajax({
        url: "/admin/reply/delete",
        type: "delete",
        data: {
            "checkedIds": checkedIds,
        },
        success: function () {
            load(page, keyword);
        }
    });
});

/*삭제 버튼 모달*/
// 모달창 열기 함수
function openModalDelete() {
    var checkedItems = [];
    var message = document.getElementById("delete-check");
    // 체크박스 요소들을 가져온다.
    var checkboxes = document.querySelectorAll('.item-list-contents input[type="checkbox"]');
    // 체크된 항목이 없으면 모달을 띄웁니다.

    checkboxes.forEach(function(checkbox) {
        if (checkbox.checked) {
            checkedItems.push(checkbox.value); // 체크된 항목의 부모 요소인 item-list-contents를 배열에 추가합니다.
        }
    });
    if (checkedItems.length === 0) {
        message.innerText = "선택된 항목이 없습니다.";
        document.getElementById("admin-my-modal").style.display = "block";
        document.getElementsByClassName("admin-delete-cancel")[0].style.display = "none";
        return;
    } else {
        message.innerText = "정말 삭제하시겠습니까?";
        document.getElementById("admin-my-modal").style.display = "block";
        document.getElementsByClassName("admin-delete-cancel")[0].style.display = "inline-block";
    }
}

// 모달창 닫기 함수
function closeModal() {
    document.getElementById("admin-my-modal").style.display = "none";
}

// 삭제 함수
function deleteItem() {
    // 여기에 삭제 작업 코드를 작성
    // 삭제 작업 후 모달창을 닫는 코드
    closeModal();
}


