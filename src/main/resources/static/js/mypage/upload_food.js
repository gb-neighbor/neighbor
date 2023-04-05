/* 위로가기 버튼 */
var topBtn = document.getElementById("topBtn");

window.onscroll = function() {
    scrollFunction()
};

function scrollFunction() {
    if (document.body.scrollTop > 20 || document.documentElement.scrollTop > 20) {
        topBtn.style.display = "block";
    } else {
        topBtn.style.display = "none";
    }
}

function topFunction() {
    document.body.scrollTop = 0;
    document.documentElement.scrollTop = 0;
}

topBtn.addEventListener("click", topFunction);

/*******************************************************************************************/

const $listBox = $(".my_food_content");

globalThis.page=1;


const foodService=(function(){
    function list(callback){
        $.ajax({
            url:"/mypage/myFoodList/"+memberId+"/"+globalThis.page,
            dataType: "json",
            method: "post",
            success: function(myfoods){
                if(callback){
                    callback(myfoods);
                }
            }
        });
    }

    return {list:list};
})();

foodService.list(showMyfoods);



$(window).scroll(
    function() {
        if (Math.ceil($(window).scrollTop()) == $(document).height() - $(window).height() - 312) {
            globalThis.page++;
            console.log("here2");
            console.log("page: "+globalThis.page);
            foodService.list(showMyfoods);
        }
    }
);


function showMyfoods(myfoods){

    let myfoodList = "";
    myfoods.forEach(myfood => {
        console.log(myfood);
        myfoodList += `
            <div class="content_wrap">
                <div class="product_thumnail">
                    <a href="/board/detail/${myfood.boardId}" class="product_detail_link">`
        if(`${myfood.boardStatus}`==1){
            myfoodList += `<img class="product_thumbnail_image" src="/board-files/display?fileName=boards/${myfood.fileMainPath}/${myfood.fileMainUuid}_${myfood.fileMainName}" style="opacity: 0.8; filter: brightness(0.5);">
                           <div class="label-spot">
                                <label for="parent-block" >판매종료</label>`
        } else{
            myfoodList += `<img class="product_thumbnail_image" src="/board-files/display?fileName=boards/${myfood.fileMainPath}/${myfood.fileMainUuid}_${myfood.fileMainName}">
                           <div class="label-spot">`
        }
        //
        // if(!myfood.boardStatus){
        //     myfoodList += ``
        // }
        myfoodList += `</div>
                        </a>
                    </div>
                    <div class="product_info">
                        <span class="areA">
                            <a class="product_title" href="/board/detail/${myfood.boardId}">${myfood.boardTitle}</a>
                        </span>
                        <span class="price">${myfood.boardPrice}</span>
                        <div class="score">`

        for (let i = 0; i < `${myfood.avgScore}`; i++) {
            myfoodList += "<img src='/css/mypage/images/star.png'>"
        }
        for (let i = 0; i < (5-`${myfood.avgScore}`); i++) {
            myfoodList += "<img src='/css/mypage/images/grey-star.png'>"
        }
         myfoodList += `<span class="reply_num">(${myfood.totalReply})</span>
                    </div>
                </div>
            </div>
        `;
    })
    $listBox.append(myfoodList);
}

