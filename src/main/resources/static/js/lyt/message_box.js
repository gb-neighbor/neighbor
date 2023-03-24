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

function openModalBanner(num){ /* 괄호에 num으로 받기 */
    modal('.my_modal', num);
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
        contentValue = contentValue.substring(0, str_len-1);
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