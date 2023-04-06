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


/*html에 같이 있던 부분*/
const $changePageTags = $(".page-button");
const $searchButton = $("#search-button");
const $boardSearch = $(".board-search");
let page = 1;
let page2 = 1;
let keyword = null;
load(page, keyword);
let count = 1;

// 검색창에서 키보드를 눌렀을 때
$('.board-search-form').on('keydown', function(e) {
    if(count==1) {
        page = 1;
    } else {
        page2 = 1;
    }
    if (e.keyCode == 13) { // Enter 키를 눌렀을 때
        e.preventDefault(); // 기본 이벤트 막기
    }
});

function showList(boardDTOS) {
    const $results = $('.checkbox-group');
    var text = "";
    var content = "";
    boardDTOS.forEach(board => {
        content +=  `
                        <div class="item-list-contents">
                            <div class="checkbox-zip">
                                <input type="checkbox" id="check1" class="normal" value="${board.boardId}">
                            </div>
                            <div class="board-num">${board.boardId}</div>
                            <div class="user-region">${board.boardRegionKo}</div>
                            <div class="user-name">${board.memberNickname}</div>
                            <div class="board-contents">
                                <span id="${board.boardId}" onclick="openModal(${board.boardId})">${board.boardContent}</span>
                                <div id="my_modal${board.boardId}" class="my_modal">
                                    <a class="modal_close_btn">✖</a>
                                    <div class="comment-writer"> 작성자 : <span>${board.memberNickname}</span></div>
                                    <div class="comment-title"> 제목 : <span>${board.boardTitle}</span></div>
                                    <div class="comment-detail"> 내용 : <span>${board.boardContent}</span></div>
                                </div>
                            </div>
                            <div class="board-register-date">${board.boardRegisterDate}</div>
                            <div class="status status-false">${board.boardSaleStatus}</div>
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

    /*페이지 이동을 위함*/
    $('.page-button').on('click', function(e) {
        e.preventDefault();
        if(count == 1) {
            page = $(this).data('page');
            keyword = $boardSearch.val();
            load(page,keyword);
        } else {
            page2 = $(this).data('page2');
            keyword = $boardSearch.val();
            load2(page2,keyword);
        }
        $('.page-button.active').removeClass('active');
        $(this).addClass('active');
    }).on('mouseenter', function() {
        $(this).css('cursor', 'pointer');
    }).on('mouseleave', function() {
        $(this).css('cursor', 'auto');
    });
}


$searchButton.on("click", function() {
    keyword = $boardSearch.val();
    if(count == 1) {
        load(page,keyword);
    } else {
        load2(page2,keyword);
    }

});

$boardSearch.on("keyup", function() {
    keyword = $boardSearch.val();
    if(count == 1) {
        load(page,keyword);
    } else {
        load2(page2,keyword);
    }
});

// 처음 페이지 목록을 불러옴.
function load(page, keyword) {
    $.ajax({
        url: `/admin/board/page/keyword`,
        type: "get",
        data: {
            page: page,
            keyword: keyword
        },
        success: function (result) {
            // replyDTO
            $('.total-mentor-num').html(result.boardTotal);
            showList(result.boardDTOS);
            showPage(result.pageDTO);
        }
    });
}

/* delete 버튼 클릭 시 ajax실행*/
$('.admin-delete-check').on('click', function () {
    var checkedItems = [];

// 체크박스 요소들을 가져온다.
    var checkboxes = document.querySelectorAll('.item-list-contents input[type="checkbox"]');

// 체크박스들을 순회하며 체크된 항목들을 배열에 담는다.
    checkboxes.forEach(function(checkbox) {
        if (checkbox.checked) {
            checkedItems.push(checkbox.value); // 체크된 항목의 부모 요소인 item-list-contents를 배열에 추가합니다.
        }
    });
    console.log(checkedItems);

    for(let i = 0; i<checkedItems.length; i++){
        console.log(checkedItems[i]);
        $.ajax({
            type : "DELETE",
            url : "/admin/board/delete",
            data : {
                "checkedId" : checkedItems[i]
            },
            success : function() {
                if(count == 1) {
                    load(page, keyword);
                } else {
                    load2(page2,keyword);
                }
            }
        });
    }
});


function showListWait(boardDTOS) {
    const $results = $('.checkbox-group');
    var text = "";
    var content = "";
    boardDTOS.forEach(board => {
        content +=  `
                        <div class="item-list-contents">
                            <div class="checkbox-zip">
                                <input type="checkbox" id="check1" class="normal" value="${board.boardId}">
                            </div>
                            <div class="board-num">${board.boardId}</div>
                            <div class="user-region">${board.boardRegionKo}</div>
                            <div class="user-name">${board.memberNickname}</div>
                            <div class="board-contents">
                                <span id="${board.boardId}" onclick="openModal(${board.boardId})">${board.boardContent}</span>
                                <div id="my_modal${board.boardId}" class="my_modal">
                                    <a class="modal_close_btn">✖</a>
                                    <div class="comment-writer"> 작성자 : <span>${board.memberNickname}</span></div>
                                    <div class="comment-title"> 제목 : <span>${board.boardTitle}</span></div>
                                    <div class="comment-detail"> 내용 : <span>${board.boardContent}</span></div>
                                </div>
                            </div>
                            <div class="board-register-date">${board.boardRegisterDate}</div>
                            <div class="status status-false">${board.boardSaleStatus}</div>
                        </div>`



    });
    $('.item-list-contents').remove();
    if ($("#check-all").prop("checked")) {
        $("#check-all").prop("checked", false);
    }
    $results.append(content);

}


function showPageWait(pageDTO) {
    const $btns = $('.page-button-box');
    const criteria = pageDTO.criteria;
    let text = "";
    $btns.empty(); // 이전에 생성한 페이지 버튼 제거
    if (pageDTO.prev) {
        text += `<a class="page-button" data-page2="${pageDTO.startPage - 1}"><code><</code></a>`;
    }
    for (let i = pageDTO.startPage; i <= pageDTO.endPage; i++) {
        if (criteria.page === i) {
            text += `<a class="page-button" data-page2="${i}">${i}</a>`;
        } else {
            text += `<a class="page-button" data-page2="${i}">${i}</a>`;
        }
    }
    if (pageDTO.next) {
        text += `<a class="page-button" data-page2="${pageDTO.endPage + 1}"><code>></code></a>`;
    }
    $btns.append(text);
    $("input[name='page']").val(criteria.page);
    /*페이지 이동을 위함*/
// 페이지 이동 버튼 클릭 이벤트 핸들러 추가
    $('.page-button').on('click', function(e) {
        e.preventDefault();
        page2 = $(this).data('page2');
        keyword = $boardSearch.val();
        load2(page2, keyword);
        $('.page-button.active').removeClass('active');
        $(this).addClass('active');
    });
}

function load2(page2, keyword) {
    $('.total-mentor-num').empty();
    $('.list-container').empty();
    $('.page-button-box').empty();
    $.ajax({
        url: `/admin/board/page/keyword/status`,
        type: "get",
        data: {
            page: page2,
            keyword: keyword
        },
        success: function (resultWait) {
            $('.total-mentor-num').html(resultWait.boardTotal);
            showListWait(resultWait.boardDTOS);
            showPageWait(resultWait.pageDTO);
        }
    });
}


$('.watch-all-board-button, .watch-waiting-board-button').click(function() {
    $('.board-search-form input').val("");

    /*판매 대기중인 목록을 불러오는 ajax*/
    $('.watch-waiting-board').on('click',function() {
        count = 0;
        page2 = 1;
        keyword = null;
        load2(page2,keyword);
    });

    $('.watch-all-board').on('click',function() {
        count = 1;
        page = 1;
        keyword = null;
        load(page,keyword);
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