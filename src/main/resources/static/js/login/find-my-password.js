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

})
