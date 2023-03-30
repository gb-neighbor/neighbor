/* 모달창 js */

function modal(name, id) {
    var zIndex = 998;
    var modal = $(name+id);
    console.log(modal);
    // 모달 div 뒤에 희끄무레한 레이어
    var bg = $('.modal_background')
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
        .show();

    modal
        .css({
            position: 'fixed',
            boxShadow: '0 4px 8px 0 rgba(0, 0, 0, 0.2), 0 6px 20px 0 rgba(0, 0, 0, 0.19)',

            // 시꺼먼 레이어 보다 한칸 위에 보이기
            zIndex: zIndex + 1,

            // div center 정렬
            top: '50%',
            left: '50%',
            transform: 'translate(-50%, -50%)',
            msTransform: 'translate(-50%, -50%)',
            webkitTransform: 'translate(-50%, -50%)'
        })
        .show()
        // 닫기 버튼 처리, 시꺼먼 레이어와 모달 div 지우기
        .find('.modal_close_btn')
        .on('click', function() {
            bg.hide();
            modal.hide();
            $('#write-section'+id).val('');
            document.getElementById("text_length"+id).value = 0;
        });
}

$('.modalAdd').on('click', function() {
    // 모달창 띄우기
    modal($('.my_modalAdd'));
});


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


/* 글자수 */
/*   function getLength()
  {
   var contentValue = document.getElementById("write-section").value;
   var txtlenValue = document.getElementById("txt-len").value;
   var max_byte = contentValue.length;
   var str_len = contentValue.length;

   if (max_byte == 1001)
   {
    modalMessage = "1000자를 넘길 수 없습니다.";
    showWarnModal(modalMessage);
    contentValue = contentValue.substring(0, str_len-1);
    txtlenValue = max_byte-1;
    return;
   }
   else
   {
    txtlenValue = max_byte;
   }

  } */



function getLength(num)
{
    var contentValue = document.getElementById("write-section"+num).value;
    var txtlenValue = document.getElementById("text_length"+num);
    var max_byte = contentValue.length;
    var str_len = contentValue.length;

    if (max_byte == 1001)
    {
        modalMessage = "1000자를 넘길 수 없습니다.";
        showWarnModal(modalMessage);
        document.getElementById("write-section"+num).value = document.getElementById("write-section"+num).value.substring(0, str_len-1);
        txtlenValue.value = max_byte-1;
        return;
    }
    else
    {
        txtlenValue.value = max_byte;
    }

}

/* 모달창 */
let modalCheck;
function showWarnModal(modalMessage){
    modalCheck = false;
    $("div#content-wrap").html(modalMessage)
    $("div.warn-modal").css("animation", "popUp 0.5s");
    $("div.modal").css("display", "flex").hide().fadeIn(500);
    setTimeout(function(){modalCheck = true;}, 500);
}

$("div.modal").on("click", function(){
    if(modalCheck){
        $("div.warn-modal").css("animation", "popDown 0.5s");
        $("div.modal").fadeOut(500);
    }
});

/* 버튼 텍스트로 변경 */

function changeToText(number){
    document.getElementById("purchase_done"+number).style.display ="none";
    document.getElementById("purchase_complete_message"+number).style.display ="block";
}

/*********************************************************************************************/
const $messageBox = $(".box_text");
const $infoBox = $('.box_top');


var targetId;
var boardId;


function getMessageRoom(target, board){
    messageService.targetInfo(showTargetInfo);
    messageService.list(showMessage);
    targetId =target;
    boardId =board;
}



function openModalBanner(num){ /* 괄호에 num으로 받기 */
    modal('#my_modal', num);
}

const messageService=(function(){
    function list(callback){
        $.ajax({
            url: "/messages/detail/"+boardId+"/"+memberId,
            dataType: "json",
            method: "post",
            success: function(messages){
                console.log(targetId)
                console.log(boardId)
                if(callback){
                    callback(messages);
                }
                $messageBox.scrollTop($messageBox.scrollHeight);
            }
        });
    }

    function targetInfo(callback){
        $.ajax({
            url: "/messages/targetInfo/"+targetId +"/"+boardId,
            dataType: "json",
            method: "post",
            success: function(Infos){
                if(callback){
                    callback(Infos);
                }
                openModalBanner(targetId);
            }
        });
    }
    return {list:list, targetInfo:targetInfo};
})();


$('.send_form').submit(function(e) {
    e.preventDefault();
        let messageVO = {
            boardId: boardId,
            messageSenderId: memberId,
            messageGetterId: targetId,
            messageContent: $("#write-section" + targetId).val()
        };
        console.log("들어옴");   
        $.ajax({
            url: "/messages/insert",
            type: $(this).attr('method'),
            data: JSON.stringify(messageVO),
            contentType: 'application/json; charset=utf-8',
            dataType: 'json',
            success: function(data) {
                console.log(data)
                console.log( $("text_length"+targetId).val());
                console.log("들어옴");
                $("#write-section2").val('');
                $("text_length"+targetId).val('0');
                messageService.list(showMessage);
            }
        });

});



function showTargetInfo(Infos){

    let target = `
		<div class="profile_image_section">
            <img class="profile_image" src="">
        </div>
        <p class="detail_nick_name">${Infos.targetInfo.memberNickname}</p>
        <div class="title_refresh_wrap">
            <h3 class="detail_title">
                ${Infos.boardTitle}
            </h3>
            <div class="refresh_image_wrap" onclick="messageService.list(showMessage)">
                <img class="refresh_image" src="/css/board/images/refresh_btn.png">
            </div>
        </div>
        <div class="div_for_margin"></div>
        <a class="go_to_board" href="/board">
            <p class="go_to_board_text">상세보기&nbsp</p>
            <p class="go_to_board_text right_text">></p>
        </a>
	`;
    $infoBox.html(target);
}

function showMessage(messages){
    let messagesList = "";
    messages.forEach(message => {
        if(message.messageSenderId==memberId){
            messagesList+=`
        <div class="my_text_wrap">
          <div class="my_text">
            <p class="my_text_content">${message.messageContent}</p>
          </div>
          <div class="my_text_time">
            <p class="message_box_time">${message.messageRegisterDate}</p>
          </div>
        </div>`;
        }else{
            messagesList+=`
        <div class="opponent_text_wrap">
          <div class="opponent_text">
            <p class="opponent_text_content">${message.messageContent}</p>
          </div>
          <div class="opponent_text_time">
            <p class="message_box_time">${message.messageRegisterDate}</p>
          </div>
        </div>`;
        }
    });
    $messageBox.html(messagesList);
}

/*********************************************************************************************/
