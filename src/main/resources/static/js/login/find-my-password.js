const $email = $(".email");
const $helpEmail = $(".err-email")

var regExp = /^([\w-\.]+)@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.)|(([\w-]+\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\]?)$/;

let checkAll = [false];

/* 모든 정보가 있어야만 클릭 가능*/
function changeButton(){
    if(checkAll[0]){
        $(".change-button").attr("disabled", false);
    } else {
        $(".change-button").attr("disabled", true);
    }
}

$email.keyup(function(){
    if( !$email.val() ){
        $helpEmail.text("이메일을 입력해주세요.")
        $helpEmail.css("color", "red")
        $helpEmail.css("font-size", "15px")
        $helpEmail.css("margin-bottom", "5px")
        $email.focus();
    } else if (!regExp.test($email.val())) {
        $helpEmail.text("이메일이 유효하지 않습니다.")
        $helpEmail.css("color", "red")
        $helpEmail.css("font-size", "15px")
        $helpEmail.css("margin-bottom", "5px")
        $email.focus();
    } else {
        $helpEmail.text("");
        checkAll[0] = true
        changeButton();
    }

});

// 모달창
let modalCheck;
function showWarnModal(modalMessage){
    modalCheck = false;
    $("div#content-wrap").html(modalMessage)
    $("div.warn-modal").css("animation", "popUp 0.5s");
    $("div.modal").css("display", "flex").hide().fadeIn(500);
    setTimeout(function(){modalCheck = true;}, 500);
    console.log("들ㅇ옴")
}

$(".confirm-btn").on("click", function(){
    if(modalCheck){
        $("div.warn-modal").css("animation", "popDown 0.5s");
        $("div.modal").fadeOut(500);
    }
});

let findForm = $("form[name='find-password']");

const $modalOn = $(".change-button");
const $confirm = $(".confirm-btn");

$modalOn.on('click', function (e) {
    e.preventDefault();
    let modalMessage = '';

    modalMessage = "이메일을 확인해주세요.";
    showWarnModal(modalMessage);

});

$confirm.on('click', function () {
    console.log("또들어옴")
    findForm.submit();
});
