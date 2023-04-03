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



/* 비밀번호 정규식 */
const passwordNumberRegex = /[0-9]/g;
const passwordEnglishRegex = /[a-z]/gi;
const passwordSpecialCharacterRegex = /[`~!@@#$%^&*|₩₩₩'₩";:₩/?]/gi;
const $warnText = $('p.warn-text');
const $regexText = $('p.password-regex');
const $inputs = $('input[type="password"]');
/* 기존 비밀번호 입력칸 */
const $originalPassword = $('.original-password');
/* 새로운 비밀번호 입력칸 */
const $passwordInput = $('.new-password');
/* 새로운 비밀번호 한번더 입력칸 */
const $checkInput = $('.new-password-check');
/* 변경하기 버튼 */
const $changeButton = $('.change-button');

let regexMessage = '위 비밀번호와 일치하지 않습니다. 다시 입력해주세요.';
let passwordCheck;
let passwordCheckAll = [false, false, false];

$('.modal').hide();

/* 기존 비밀번호 검사 */
$originalPassword.on('blur', function () {
    let value = $(this).val();

    if (!value) {
        passwordCheck = false;
        passwordCheckAll[0] = passwordCheck;
        return;
    }

    passwordCheckAll[0] = true;
});

/* 새로운 비밀번호 검사 */
/* $passwordInput.on('blur', function () {
  let value = $(this).val();

  if (!value) {
    $regexText.css('color', 'rgb(153, 153, 153)');
    $passwordInput.css('border', '1px solid rgb(238, 238, 238)');
    passwordCheck = false;
    passwordCheckAll[1] = passwordCheck;
    return;
  } */

/* 정규식 검사 */
/*  let numberCheck = value.search(passwordNumberRegex);
 let englishCheck = value.search(passwordEnglishRegex);
 let specialCharacterCheck = value.search(passwordSpecialCharacterRegex);

 var condition1 =
   numberCheck >= 0 &&
   englishCheck >= 0 &&
   englishCheck >= 0 &&
   specialCharacterCheck >= 0 &&
   specialCharacterCheck >= 0 &&
   numberCheck >= 0;
 var condition2 = value.length > 9 && value.length < 21;
 var condition3 = value.search(/\s/) < 0; */

/* 정규식 검사 통과하면 true */
/*  passwordCheck = condition1 && condition2 && condition3;

 passwordCheckAll[1] = passwordCheck;

 if (!passwordCheck) {
   $regexText.css('color', 'rgb(222, 28, 34)');
   $passwordInput.css('border', '1px solid rgb(222, 28, 34)');
   passwordCheckAll[1] = passwordCheck;
 } else {
   $regexText.css('color', 'rgb(153, 153, 153)');
   $passwordInput.css('border', '1px solid rgb(238, 238, 238)');
 }
}); */

/* 새로운 비밀번호 한번 더 입력 검사 */
/* $checkInput.on('blur', function () {
  let value = $(this).val();

  if (value != $passwordInput.val()) {
    $warnText.show();
    $warnText.text(regexMessage);
    $checkInput.css('border', '1px solid rgb(222, 28, 34)');
    passwordCheck = false;
    passwordCheckAll[2] = passwordCheck;
  } else {
    $warnText.hide();
    passwordCheck = true;
    passwordCheckAll[2] = passwordCheck;
  }

  passwordCheckAll[2] = passwordCheck;

  if (!passwordCheck) {
    $warnText.show();
    $warnText.text(regexMessage);
    $checkInput.css('border', '1px solid rgb(222, 28, 34)');
    passwordCheckAll[2] = passwordCheck;
  } else {
    $warnText.hide();
    $checkInput.css('border', '1px solid rgb(238, 238, 238)');
  }
}); */

/* 입력한 값들이 모두 true라면 변경하기 버튼 활성화 */
/* $inputs.on('blur', function () {
  if (passwordCheckAll.filter((check) => check).length == 1) {
    $changeButton.attr('disabled', false);
    return;
  }
  $changeButton.attr('disabled', true);
});  */

$changeButton.on('click', function () {
    let modalMessage = '';
    /* 기존 비밀번호 검사 후 false일 때 모달창 */
    modalMessage = '기존 비밀번호가 일치하지 않습니다.';
    showWarnModal(modalMessage);
    /* 기존 비밀번호 검사 후 true일 때 모달창 */
    /* modalMessage = "변경되었습니다.";
      showWarnModal(modalMessage); */
});

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


const fileDOM = document.querySelector('#file');
const previews = document.querySelectorAll('.image-box');

fileDOM.addEventListener('change', () => {
    const reader = new FileReader();
    reader.onload = ({ target }) => {
        previews[0].src = target.result;
    };
    reader.readAsDataURL(fileDOM.files[0]);
});

/*정규식*/


/* 모든 정보가 있어야만 클릭 가능*/
function changeButton(){
    if(checkAll[0] && checkAll[1] && checkAll[2] && checkAll[3] && checkAll[4] && checkAll[5] && checkAll[6]){
        $(".change-button").attr("disabled", false);
        $(".password-check").attr("disabled", false);
    } else {
        $(".change-button").attr("disabled", true);
        $(".password-check").attr("disabled", true);
    }
}

/* 인풋테그에 값이 있으면 순서대로 true로 변경 */
let checkAll = [false, false, false, false, false, false];
/* 순서대로
    0 -> 닉네임
    1 -> 닉네임 중복
    2 -> 생일
    3 -> 핸드폰 번호
    4 -> 핸드폰 번호 중복
    5 -> 이메일
    6 -> 이메일 중복
*/

/* 닉네임 */
$errNick = $(".err-nickname")
$(".nick-name").on("keyup",function(){
    if ($(this).val().length > 8){
        alert("최대 8자까지 입력 가능합니다.");
        $(this).val($(this).val().substring(0, 9));
        $('.nick-name').html(9);

    } else if($(this).val()){
        $errNick.text("")
        checkAll[0] = true;
        changeButton();

    }

    if(!$(this).val()){
        checkAll[0] = false;
        $errNick.text("닉네임을 입력해주세요")
        $errNick.css('color', 'red')
    } else if($(this).val()){
        $errNick.text("")
        checkAll[0] = true;
        changeButton();

    }
})

/*닉네임 중복*/
const $joinInputNickname = $(".nick-name");
const $errorNickname = $(".err-nickname");

$joinInputNickname.on("blur",function(){$.ajax({
    url:"/members/checkNickname" ,
    data: {"memberNickname": $joinInputNickname.val()},
    success: function(result){
        let message;

        if(result == 1){
            message = "중복된 닉네임입니다.";
            $errorNickname.css('color', 'red')
            $errorNickname.css('display', 'block');
            checkAll[1] = false;

        }else if($joinInputNickname.val().length < 1){
            $errorNickname.css('display', 'block');
            $errorNickname.css('color', 'red');
            message = "필수 입력 사항입니다";
            checkAll[1] = false;
        }else {
            message = "사용 가능한 닉네임입니다.";
            $errorNickname.css('display', 'block');
            $errorNickname.css('color', '#2bb673');
            checkAll[1] = true;
            changeButton();
        }
        $errorNickname.text(message);
    }
});
});

let regPhone= /^01([0|1|6|7|8|9])-?([0-9]{3,4})-?([0-9]{4})$/;
$(".phone").on("keyup", function(){
    if($(".phone").val() != "" && $(".phone").val().length == 11 && regPhone.test($(".phone").val())) {
        checkAll[2] = true;
        $(".err-phone").text("");
        changeButton();
    } else {
        checkAll[2] = false;
        $(".err-phone").text("핸드폰 번호를 제대로 입력해주세요")
        $(".err-phone").css('color', 'red')
    }
})

//휴대폰 중복
const $joinInputPhoneNum = $(".phone");
const $errorPhoneNum = $(".err-phone");

$joinInputPhoneNum.on("blur",function(){$.ajax({
    url:"/members/checkPhone" ,
    data: {"memberPhone": $(".phone").val()},
    success: function(result){
        let message;

        if(result == 1){
            message = "중복된 번호입니다.";
            $errorPhoneNum.css('color', 'red')
            $errorPhoneNum.css('display', 'block');
            checkAll[3] = false;

        }else if($joinInputPhoneNum.val().length < 1){
            $errorPhoneNum.css('display', 'block');
            $errorPhoneNum.css('color', 'red');
            message = "필수 입력 사항입니다";
            checkAll[3] = false;

        }else if (!regPhone.test($(".phone").val())) {
            $(".err-phone").text("번호 형식이 유효하지 않습니다.")
            $(".err-phone").css('color', 'red')
            checkAll[3] = false;
        }else {
            message = "사용 가능한 번호입니다.";
            $errorPhoneNum.css('display', 'block');
            $errorPhoneNum.css('color', '#2bb673');
            checkAll[3] = true;
            changeButton();
        }
        $errorPhoneNum.text(message);
    }
});
});


//이메일
const $email = $(".email");
const $helpEmail = $(".err-email")

var regExp = /^([\w-\.]+)@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.)|(([\w-]+\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\]?)$/;

$email.keyup(function(){
    if( !$email.val() ){
        $helpEmail.text("이메일을 입력해주세요")
        $errEmail.css('color', 'red')
        $email.focus();
        checkAll[4] = false;
    } else if (!regExp.test($email.val())) {
        $helpEmail.text("이메일이 유효하지 않습니다.")
        $errEmail.css('color', 'red')
        $email.focus();
        checkAll[4] = false;
    } else {
        $helpEmail.text("");
        checkAll[4] = true;
        changeButton();

    }

})

//이메일 중복
const $inputEmail = $(".email");
const $errEmail = $(".err-email");

$inputEmail.on("blur", function(){$.ajax({
    url:"/members/checkEmail" ,
    data: {"memberEmail": $inputEmail.val()},
    success: function(result){
        let message;
        if(result == 1){
            message = "중복된 이메일입니다.";
            $errEmail.css('color', 'red')
            $errEmail.css('display', 'block');
            checkAll[5] = false;

        } else if($inputEmail.val().length < 1){
            $errEmail.css('display', 'block');
            $errEmail.css('color', 'red');
            message = "필수 입력 사항입니다";
            checkAll[5] = false;

        } else if(!regExp.test($inputEmail.val())){
            $errEmail.css('display', 'block');
            $errEmail.css('color', 'red');
            message = "이메일 형식에 맞춰주세요";
            checkAll[5] = false;

        } else{
            message = "사용 가능한 이메일입니다.";
            $errEmail.css('display', 'block');
            $errEmail.css('color', '#2bb673');
            checkAll[5] = true;
            changeButton();
        }

        $errEmail.text(message);
    }
});
});

/* 생일 */
let regBirth = /^(19[0-9][0-9]|20\d{2})(0[0-9]|1[0-2])(0[1-9]|[1-2][0-9]|3[0-1])$/;

$(".birth").on("keyup", function() {
    if($(this).val() != "" && $(this).val().length == 8 && regBirth.test($(this).val())){
        checkAll[6] = true;
        $(".err-birth").text("");
        changeButton();
    } else {
        checkAll[6] = false;
        $(".err-birth").text("생일을 정확히 입력해주세요")
        $(".err-birth").css('color', 'red')
    }
});