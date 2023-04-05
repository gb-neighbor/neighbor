/* 모달창 js */

var isScrolling = false;
function modal(name, id) {
    var zIndex = 998;
    var modal = $(name+id);
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
        .find('#modal_close_btn'+ globalThis.targetNum)
        .on('click', function() {
            bg.hide();
            modal.hide();
            // $("#write-section" + globalThis.targetNum).val('');
            // $("#text_length" + globalThis.targetNum).val('0');
            // $("#box_text" + globalThis.targetNum).children().remove();
            // globalThis.page =1;
            location.href="/mypage/message_box";

            // globalThis.listPage =1;
            // messageService.getMessageListByMemberId(showMessageRooms);
        });
}

$('.modalAdd').on('click', function() {
    // 모달창 띄우기
    modal($('.my_modalAdd'));
});


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

/*********************************************************************************************/
var boardId;
var targetId;
globalThis.targetNum;

var $messageBox;
var $infoBox;

globalThis.page;
globalThis.listPage =1;

function getMessageRoom(target, board, messageRoomId) {
    globalThis.page = 1;
    targetId = target;
    boardId = board;
    globalThis.targetNum = messageRoomId;
    $messageBox = $("#box_text" + globalThis.targetNum);

    globalThis.scrollHeight = $messageBox[0].scrollHeight;
    $infoBox = $('#box_top' + globalThis.targetNum);


    messageService.targetInfo(showTargetInfo);
    console.log(globalThis.page + "init");
    messageService.list(showMessage);


    $messageBox.scroll(function() {
        console.log(globalThis.page + "-1sadasdasd");
        if ($messageBox.scrollTop() == 0) {
            globalThis.page++;

            messageService.list(showMessage);

            console.log(globalThis.page + "-2sadsadsad");
        }
    });


}


function refreshClicked(){
    $("#box_text" + globalThis.targetNum).children().remove();
    globalThis.page =1;
    messageService.list(showMessage);

    $messageBox.scrollTop(globalThis.scrollHeight);
}

function openModalBanner(num){ /* 괄호에 num으로 받기 */
    modal('#my_modal', num);
}

const messageService=(function(){
    function list(callback){
        $.ajax({
            url: "/messages/detail/"+boardId+"/"+memberId+"/"+targetId+"/"+globalThis.page,
            dataType: "json",
            method: "post",
            success: function(messages){
                if(messages!=null){
                    if(callback){
                        callback(messages);
                    }
                }
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
                openModalBanner(globalThis.targetNum);
            }
        });
    }

    function getMessageListByMemberId(callback){
        $.ajax({
            url:"/messages/list/"+memberId+"/"+globalThis.listPage,
            dataType:"json",
            method:"post",
            success: function(messageRooms){
                if(callback){
                    callback(messageRooms);
                }
            }
        });
    }

    function changePurchaseStatus(boardId){
        console.log("boardId: " +boardId);
        console.log("memberId: " +memberId);
        $.ajax({
            url:"/messages/status/"+boardId+"/"+memberId,
            dataType:"json",
            method:"post",
            success:function(){}
        });
    }

    return {list:list, targetInfo:targetInfo, getMessageListByMemberId:getMessageListByMemberId, changePurchaseStatus:changePurchaseStatus};
})();


messageService.getMessageListByMemberId(showMessageRooms);

$(document).on("click", ".send_btn", function() {
    console.log("어떤뗴!")
    if($("#write-section" + globalThis.targetNum).val()){
        let messageVO = {
            boardId: boardId,
            messageSenderId: memberId,
            messageGetterId: targetId,
            messageContent: $("#write-section" + globalThis.targetNum).val()
        };
        globalThis.page=1;
        $.ajax({
            url: "/messages/insert",
            type: 'POST',
            data: JSON.stringify(messageVO),
            contentType: 'application/json; charset=utf-8',
            dataType: 'json',
            success: function(data) {
                $("#write-section" + globalThis.targetNum).val('');
                $("#text_length"+globalThis.targetNum).val('0');
                messageService.list(showMessage);
            }
        });
    }else{
        alert("message cannot be null")
    }
});


function showMessageRooms(messageRooms){
    let rooms = "";
    messageRooms.forEach(room => {
        rooms += `
                <li class="message_list">
                    <button class="detail_btn" onclick="getMessageRoom(${room.targetId}, ${room.boardId}, ${room.messageRoomId})">
                    </button>
                    <div class="message_box_wrapper">
                        <div class="profile_wrap">
                            <div class="message_box_profile">
                                <a href="/board/detail/${room.boardId}">
                                    <img class="message_box_profile_image" src="/board-files/display?fileName=boards/${room.boardProfilePath}/${room.boardProfileUuid}_${room.boardProfileOriginalName}">
                                </a>
                            </div>
                        </div>
                        <div class="message_box_detail">
                            <div class="detail_message">
                                <a href="/board/list/member/${room.targetId}" class="nick_name">${room.targetNickname}</a>
                            </div>
                            <div class="message_box_info">
                                <p class="board_title">${room.boardTitle}</p>
                                <p class="message_box_time">${room.latestRegisterDate}</p>
                            </div>
                        </div>
                    </div>
                    <form class="purchase_complete_form">
                        <div class="purchase_complete_wrap">`
        if(`${room.sellerId}`== memberId) {
            console.log("boardStatus: "+`${room.boardStatus}`)
            console.log("purchaseStatus: "+`${room.purchaseStatus}`)
            rooms += !`${room.boardStatus}` ?
                    `<p id="purchase_complete_message${room.messageRoomId}" class="complete_message" style="display: block; color: red;">판매종료</p>`
                : `${room.purchaseStatus}`!='null' ?
                    `<p id="purchase_complete_message${room.messageRoomId}" class="complete_message" style="display: block; color: red;">거래종료</p>`
                : `<p id="purchase_complete_message${room.messageRoomId}" class="complete_message" style="display: block; color:#009a3e;">거래중</p>`;
        }else{
            rooms += `${room.purchaseStatus}`!='null' ?
                `<p id="purchase_complete_message${room.messageRoomId}" class="complete_message" style="display: block; color: red;">거래종료</p>`
                : `<p id="purchase_complete_message${room.messageRoomId}" class="complete_message" style="color: red;">거래종료</p>
                            <button type="button" id="purchase_done${room.messageRoomId}" class="purchase_complete_btn"
                                    onclick="changeToText(${room.messageRoomId}, ${room.boardId})">구매완료
                            </button>`;
        }
        rooms += `</div>
                    </form>
                    <div  id="my_modal${room.messageRoomId}" class="my_modal2 this_modal">
                        <a class="modal_close_btn" id="modal_close_btn${room.messageRoomId}">✖</a>
                        <div class="box_top" id="box_top${room.messageRoomId}"></div>
                        <div class="box_text" id="box_text${room.messageRoomId}"></div>
    
                        <form class="send_form${room.messageRoomId}" name="form" method="post">
                            <div class="text-container">
                                <textarea class="write-section"  id="write-section${room.messageRoomId}" name="messageContent" cols="20" rows="5"
                                     onKeydown="getLength(${room.messageRoomId})"  onKeyUp="getLength(${room.messageRoomId})" maxlength="1001"></textarea>
                                <div class="text_send_wrap">
                                    <input  id="text_length${room.messageRoomId}" class="count_letter" type="text" size="3" name="txtlen" value="0"
                                           readonly>
                                    <input disabled class="count_letter_max" type="text" size="10" value="/1000" readonly>
                                    <button type="button" id="send_btn_${room.messageRoomId}" class="send_btn">전송</button>
                                </div>
                            </div>
                        </form>
                    </div>
                </li>`;
    });
    $("#message_box_list").append(rooms);
}


function showTargetInfo(Infos){

    let target = `
		<div class="profile_image_section">
            <img class="profile_image" src="/members/display?fileName=${Infos.targetInfo.memberProfilePath}/${Infos.targetInfo.memberProfileUuid}_${Infos.targetInfo.memberProfileOriginalName}">
        </div>
          <p class="detail_nick_name">${Infos.targetInfo.memberNickname}</p>
        <div class="title_refresh_wrap">
            <h3 class="detail_title">
                ${Infos.boardTitle}
            </h3>
            <div class="refresh_image_wrap" onclick="refreshClicked()">
                <img class="refresh_image" src="/css/board/images/refresh_btn.png">
            </div>
        </div>
        <div class="div_for_margin"></div>
        <a class="go_to_board" href="/board/detail/${Infos.boardId}">
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
            messagesList=`
        <div class="my_text_wrap">
          <div class="my_text">
            <p class="my_text_content">${message.messageContent}</p>
          </div>
          <div class="my_text_time">
            <p class="message_box_time">${message.messageRegisterDate}</p>
          </div>
        </div>`+messagesList;
        }else{
            messagesList=`
        <div class="opponent_text_wrap">
          <div class="opponent_text">
            <p class="opponent_text_content">${message.messageContent}</p>
          </div>
          <div class="opponent_text_time">
            <p class="message_box_time">${message.messageRegisterDate}</p>
          </div>
        </div>`+messagesList;
        }
    });
    globalThis.page == 1 ? $messageBox.html(messagesList) : $messageBox.prepend(messagesList);
}

/*********************************************************************************************/
/* 버튼 텍스트로 변경 */

function changeToText(number, boardId){
    $("#purchase_done"+number).css("display","none");
    $("#purchase_complete_message"+number).css("display","block");
    messageService.changePurchaseStatus(boardId);
}



/************************************************************************************************/

$(window).scroll(
    function() {
        console.log("Math.ceil($(window).scrollTop())"+Math.ceil($(window).scrollTop()));
        console.log("$(document).height()"+$(document).height());
        console.log("$(window).height()"+$(window).height());
        console.log("$(document).height() - $(window).height()"+($(document).height() - $(window).height()));

        if (Math.ceil($(window).scrollTop()) == $(document).height() - $(window).height()-232) {
            globalThis.listPage++;
            messageService.getMessageListByMemberId(showMessageRooms);
        }
    }
);