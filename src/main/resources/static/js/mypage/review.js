function collapse(element) {
    var before = document.getElementsByClassName("active")[0]               // 기존에 활성화된 버튼
    if (before && document.getElementsByClassName("active")[0] != element) {  // 자신 이외에 이미 활성화된 버튼이 있으면
        //before.nextElementSibling.style.maxHeight = null;   // 기존에 펼쳐진 내용 접고
        // before.classList.remove("active");                  // 버튼 비활성화
    }
    element.classList.toggle("active");         // 활성화 여부 toggle

    var content = element.nextElementSibling;
    if (content.style.maxHeight != 0) {         // 버튼 다음 요소가 펼쳐져 있으면
        content.style.maxHeight = null;         // 접기
    } else {
        content.style.maxHeight = content.scrollHeight + "px";  // 접혀있는 경우 펼치기
    }
}


$(document).ready(function() {
    // 검색창에서 키보드를 눌렀을 때
    $('.faq_search').on('keydown', function(e) {
        if (e.keyCode == 13) { // Enter 키를 눌렀을 때
            e.preventDefault(); // 기본 이벤트 막기
        }
    });
});

/************************************************************************************/

var page=1;
var keyword=$("#search_text").val();

const replyService=(function(){
    function list(callback){
        $.ajax({
            url: "review/list/"+memberId+"/"+page,
            data: {keyword: keyword},
            dataType: "json",
            method: "post",
            success: function(replyDTO){
                if(callback){
                    callback(replyDTO);
                }
            }
        });
    }
    return {list:list};
})();


function showReplys(replyDTO){
    let replyList = "";
    replyDTO.replyDTOS.forEach(reply => {
        replyList += `
                <ul class="my_review">
                <li class="review_num">${reply.replyId}</li>
                <li class="review_my_title">
                    <a class="detail_link" href="/board/detail/${reply.boardId}">
                        ${reply.boardTitle}
                    </a>
                </li>
                <li class="review_my_content">
                    <a class="detail_link" href="/board/detail/${reply.boardId}">
                       ${reply.replyContent}
                    </a>
                </li>`
            for (let i = 0; i < `${reply.replyScore}`; i++) {
                replyList += "<img src='/css/mypage/images/star.png'>"
            }
            for (let i = 0; i < (5-`${reply.replyScore}`); i++) {
                replyList += "<img src='/css/mypage/images/grey-star.png'>"
            }
        replyList +=`
            <li class="review_date">${reply.replyUpdateDate}</li>
        </ul>
        `;
    });
    $(".reply-list-container").html(replyList);
    showPage(replyDTO.pageDTO);
}

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
    let $page = $(".page-button-box").children(".page-button");
    /*페이지 이동을 위함*/
    $page.on('click', function(e) {
        e.preventDefault();
        page = $(this).text();
        replyService.list(showReplys);
        $('.page-button.active').removeClass('active');
        $(this).addClass('active');
    }).on('mouseenter', function() {
        $(this).css('cursor', 'pointer');
    }).on('mouseleave', function() {
        $(this).css('cursor', 'auto');
    });
}

replyService.list(showReplys);

$(".search_btn").on("click", function(){
    page=1;
    keyword=$("#search_text").val();
    replyService.list(showReplys);
});
