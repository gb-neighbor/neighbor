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

/************************************************************************************/

globalThis.page=1;

const replyService=(function(){
    function list(callback){
        $.ajax({
            url: "review/list/"+memberId+"/"+globalThis.page,
            dataType: "json",
            method: "post",
            success: function(replys){
                if(callback){
                    callback(replys);
                }
            }
        });
    }
    return {list:list};
})();


function showReplys(replys){
    let replyList = "";
    replys.forEach(reply => {
        replyList += `
                <ul class="my_review">
                <li class="review_num">${reply.replyId}</li>
                <li class="review_my_title">
                    <a class="detail_link" href="">
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
}

replyService.list(showReplys);