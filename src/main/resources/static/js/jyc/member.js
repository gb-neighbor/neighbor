/* join.html 
    login.html
*/


/* login.html 이메일 저장 버튼 클릭시 alert 이벤트 */
$(".checkbox").on("click", function(){
    if($(this).is(":checked")){
        alert("개인정보보호를 위해 개인 기기에서만 사용하세요")
    } else{
    }

})






/* join.html */

/* 엔터이벤트 막기 */
$('input').keydown(function(event) {
    if (event.keyCode === 13) {
      event.preventDefault();
    };
  });


/* 인풋테그에 값이 있으면 순서대로 true로 변경 */
let checkAll = [false, false, false, false, false, false, false];
/* 순서대로 
    0 -> 비밀번호를 정확히 입력했다면 true
    1 -> 비밀번호와 비밀번호 확인이 같다면 true
    2 -> 닉네임에 값이 있다면 true
    3 - > 이름에 값이 있다면 true
*/

/* 프로필 사진 썸네일 */
const profile = document.querySelector('input[type=file]');
const profileDiv = document.querySelector('label[for=profile] div.img');
const profileClose = document.querySelector('label[for=profile] .close');


profileClose.addEventListener('click', function (e) {
    e.preventDefault();
    profile.value = "";
    profileClose.style.display="none";
    profileDiv.style.backgroundImage =  `url('../../templates/jyc/images/profile.png')`;
    profileDiv.style.width = "100%";
    profileDiv.style.display = "block";
});


profile.addEventListener('change', function (e) {
    this.style.display = 'none';
    let reader = new FileReader();
    reader.readAsDataURL(e.target.files[0]);

    reader.onload = function (e) {
        let result = e.target.result;
        if (result.includes('image')) {
            profileDiv.style.backgroundImage = `url('${result}')`;
            profileClose.style.display = "inline-block";
            profileDiv.style.width = "100%";
        } else {
            profileDiv.style.backgroundImage = `url('../../templates/jyc/images/profile.png')`;
        }
    };
});
/* -------------------------------아이디----------------------------------------------- */
$("#join-identification").on("blur", function () {
    if(!$(this).val()){
        $(".id-err").text("아이디를 입력해주세요");
    } else {
        $(".id-err").text("");
    }
})


/* ---------------------------------------비밀번호----------------------------------- */
const $passwordInput = $("#join-password");
const $passwordCheck = $("#join-check-password");
const $errPassword = $(".err-password");
const $errPasswordCheck = $(".err-password-check")

/* 비밀번호 입력값 체크 */
$passwordInput.keyup(function(e){
    $value = $passwordInput.val();
    let number = $value.search(/[0-9]/g);
    let english = $value.search(/[a-z]/ig);
    let spece = $value.search(/[`~!@@#$%^&*|₩₩₩'₩";:₩/?]/gi);
    // 아무것도 입력하지 않았을때
    if($value== ""){
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
   
   

})
/* 새로운 비밀번호 확인 인풋테그 감지해서 값 비교 */
$passwordInput.keyup(function(e){
    if($passwordInput.val() == $passwordCheck.val()){
        $errPasswordCheck.text("")
        checkAll[0] = true;
    } else{
        $errPasswordCheck.text("비밀번호가 다릅니다")
        checkAll[0] = false;
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
})


/* ----------------------------비밀번호------------------------------ */


/* 닉네임 */
$errNick = $(".err-nickname")
$("#nickname").on("keyup",function(){
    if ($(this).val().length > 8){
        alert("최대 8자까지 입력 가능합니다.");
         $(this).val($(this).val().substring(0, 9));
         $('#nick_name').html(9);

        } else if($(this).val() != ""){
            checkAll[2] = true;

        } else if(!$(this).val()){
            checkAll[2] = false;
            $errNick.text("닉네임을 입력해주세요")
        }
})

/* 이름엔 영어와 한글만 가능하게 */
$("#name").on("keyup", function() {
    $(this).val($(this).val().replace(/[^(ㄱ-힣a-zA-Z)]/gi, ''));
    if($(this).val() != ""){
        checkAll[3] = true;
    } else {
        checkAll[3] = false;
    }
 });

 /* 생일 */
 let regBirth = /^(19[0-9][0-9]|20\d{2})(0[0-9]|1[0-2])(0[1-9]|[1-2][0-9]|3[0-1])$/;

 $("#birth").on("keyup", function() {
    if($(this).val() != "" && $(this).val().length == 8 && regBirth.test($(this).val())){
        checkAll[4] = true;
        $(".err-birth").text("");
    } else {
        checkAll[4] = false;
        $(".err-birth").text("생일을 정확히 입력해주세요")
    }
 });

/* 핸드폰 번호 */
/* 글자수에 따라서 검사 */
let regPhone= /^\d{2,3}-?\d{3,4}-?\d{4}$/;
$("#phone").on("keyup", function(){
    if($(this).val() != "" && $(this).val().length == 11 && regPhone.test($(this).val())){
        checkAll[5] = true;
        $(".err-phone").text("");
    } else {
        checkAll[5] = false;
        $(".err-phone").text("핸드폰 번호를 재대로 입력해주세요")
    }
})


/* 지역 */
/*$("#region").click(function () {
    new daum.Postcode({
        oncomplete: function(data) {
            // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분입니다.
            // 예제를 참고하여 다양한 활용법을 확인해 보세요.
        }
    });

})*/

console.log($("#region"))



/* 이메일 */
const $email = $("#email");
const $helpEmail = $(".err-email")

var regExp = /^([\w-\.]+)@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.)|(([\w-]+\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\]?)$/;

$email.keyup(function(){
    if( !$email.val() ){ 
        $helpEmail.text("이메일을 입력해주세요")
        $email.focus(); 
     } else if (!regExp.test($email.val())) { 
           $helpEmail.text("이메일이 유효하지 않습니다.")
           $email.focus();
            checkAll[6] = false;
        } else {
            $helpEmail.text("");
            checkAll[6] = true;

        }
        
})

/* 이용약관 */
$(".check span").on("click", function(){
    /* 클릭하면 체크로 바뀌게 */
    let i = $(this).index() - 1;
    if($(this).eq(i).attr("class") == "span-check"){
        $(this).eq(i).attr("class", "span-checked");
    } else{
        $(this).eq(i).attr("class", "span-check");
    }
    
    /*만약 3개가 다 체크되어있다면 check-all은 checked로 바뀜*/
    if($(".check span").eq(0).attr("class") == "span-checked" && $(".check span").eq(1).attr("class") == "span-checked" && $(".check span").eq(2).attr("class") == "span-checked"){
        $(".check-all span").attr("class", "span-checked")
    } else {
        $(".check-all span").attr("class", "span-check")
    }
})


/* 전체 체크를 누르면 다 체크되도록 */
$(".check-all span").on("click", function () {
    if($(".check-all span").attr("class") == "span-check"){
        $(".check-all span").attr("class", "span-checked")
        $(".check span").attr("class", "span-checked")
    } else {
        $(".check-all span").attr("class", "span-check")
        $(".check span").attr("class", "span-check")
    }
})

/* 모든 정보가 있어야만 클릭 가능*/
if(checkAll[0] && checkAll[1] && checkAll[2] && checkAll[3] && checkAll[4] && checkAll[5] && checkAll[6]){
    $(".join").attr("disabled", "false")
} else {
    $(".join").attr("disabled", "true")
}



/* 모달 */
function show () {
    document.querySelector(".modal").className = "modal show";
}