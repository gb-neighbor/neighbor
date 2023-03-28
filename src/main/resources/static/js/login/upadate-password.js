/* update-password.html */

const $passwordInput = $("#new-password");
const $passwordCheck = $("#new-password-check");
const $errPassword = $(".err-password");
const $errPasswordCheck = $(".err-password-check")
let checkAll = [false, false]

/* 비밀번호 입력값 체크 */
$passwordInput.keyup(function(e){
    $value = $passwordInput.val();
    let number = $value.search(/[0-9]/g);
    let english = $value.search(/[a-z]/ig);
    let spece = $value.search(/[`~!@@#$%^&*|₩₩₩'₩";:₩/?]/gi);
    // 아무것도 입력하지 않았을때
    if(!$value){
        $errPassword.text("비밀번호를 입력해주세요");
    } else if($value.length<10 || $value.length>20){
        $errPassword.text("비밀번호는 10자 이상 20자 이하로 입력해주세요")
        checkAll[0] = false;
    } else if($value.search(/\s/) != -1){
        $errPassword.text("비밀번호는 공백 없이 입력해주세요")
        checkAll[0] = false;
    } else if(number < 0 || english < 0 || spece < 0){
        $errPassword.text("영문,숫자, 특수문자를 혼합하여 입력해주세요.")
        checkAll[0] = false;
    } else{
        $errPassword.text("")
        checkAll[0] = true;
    }

    if(checkAll[0] && checkAll[1]){
        $(".change-button").attr("disabled", false)
    } else{
        $(".change-button").attr("disabled", true)
    }
})

/* 새로운 비밀번호 확인 인풋테그 감지해서 값 비교 */
$passwordInput.keyup(function(e){
    if($passwordInput.val() == $passwordCheck.val()){
        $errPasswordCheck.text("")
        checkAll[1] = true;
    } else{
        $errPasswordCheck.text("비밀번호가 다릅니다")
        checkAll[1] = false;
    }

    if(checkAll[0] && checkAll[1]){
        $(".change-button").attr("disabled", false)
    } else{
        $(".change-button").attr("disabled", true)
    }
})


/* 비밀번호 확인 인풋테그 감지해서 값 비교 */
$passwordCheck.keyup(function(e){
    if($passwordInput.val() == $passwordCheck.val()){
        $errPasswordCheck.text("")
        checkAll[1] = true;
    } else{
        $errPasswordCheck.text("비밀번호가 다릅니다")
        checkAll[1] = false;
    }

    if(checkAll[0] && checkAll[1]){
        $(".change-button").attr("disabled", false)
    } else{
        $(".change-button").attr("disabled", true)
    }
})
/* 비밀번호 암호화 */
function sendPassword(){
    location.replace(location.href);
    $("input[name='memberPassword']").val(btoa($("input[name='memberPassword']").val()));
    document.joinForm.submit();
}