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

/* mypageUpdate.html */

const $changeButtons = $('.change-button');
const $saveButtons = $('.save-button');
const $inputUsers = $('.change-value');
const birthRegex = /^(19[0-9][0-9]|20\d{2}).?(0[0-9]|1[0-2]).?(0[1-9]|[1-2][0-9]|3[0-1])$/;
let check;

$saveButtons.hide();

/* 생일, 닉네임 정규식 추가해야함 */
/* $inputUsers.eq(1).on('focus', function () {
  $(this).val($(this).val().replaceAll('.', ''));
});

$inputUsers.eq(1).on('blur', function () {
  check = false;
  check = birthRegex.test($(this).val());
  if (check) {
    $(this).val($(this).val().replace(/^(\d{4})(\d{2})(\d{2})$/, `$1.$2.$3`));
    return;
    alert("생년월일을 확인하세요.");
  }
}); */

$changeButtons.on('click', function () {
    let i = $changeButtons.index($(this));
    $inputUsers.eq(i).attr('disabled', false);
    $inputUsers.eq(i).focus();
    $(this).hide();
    $saveButtons.eq(i).show();
});

$saveButtons.on('click', function () {
    let i = $saveButtons.index($(this));
    $inputUsers.eq(i).attr('disabled', true);
    $(this).hide();
    $changeButtons.eq(i).show();
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
