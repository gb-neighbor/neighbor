/* join.html
    login.html
*/

/*로그인 비밀번호 암호화*/
function sendLogin(){
    location.replace(location.href);
    $('.id').val(btoa($('.id').val()));
    document.loginForm.submit();
    }


/* 회원가입시 비밀번호 암호화 */
function sendJoin(){
    location.replace(location.href);
    $(".password").val(btoa($(".password").val()));
    document.loginForm.submit();
    }


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
let checkAll = [false, false, false, false, false, false, false, false, false, false, false, false];
/* 순서대로 
    0 -> 비밀번호를 정확히 입력했다면 true
    1 -> 비밀번호와 비밀번호 확인이 같다면 true
    2 -> 닉네임
    3 -> 이름
    4 -> 생일 
    5 -> 핸드폰 번호
    6 -> 이메일
    7 -> 아이디
    8 -> 전체동의
    9 -> 아이디 중복
    10 -> 이메일 중복
    11 -> 닉네임 중복
*/

/* 프로필 사진 썸네일 */
const profile = document.querySelector('input[type=file]');
const profileDiv = document.querySelector('label[for=profile] div.img');
const profileClose = document.querySelector('label[for=profile] .close');


profileClose.addEventListener('click', function (e) {
    e.preventDefault();
    profile.value = "";
    profileClose.style.display="none";
    profileDiv.style.backgroundImage =  `url('/css/login/images/profile2.png')`;
    $(".camera").show();
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
            $(".camera").hide();
        } else {
            profileDiv.style.backgroundImage = `url('/css/login/images/profile2.png')`;
            $(".camera").show();
        }
    };
});
/* -------------------------------아이디----------------------------------------------- */
$(".identification").on("blur", function () {
    if(!$(this).val()){
        $(".id-err").text("아이디를 입력해주세요");
        checkAll[7] = false;
    } else {
        $(".id-err").text("");
        checkAll[7] = true;
        changeButton();
    }
})


/* ---------------------------------------비밀번호----------------------------------- */
const $passwordInput = $(".pw");
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
        changeButton();
    }
})


/* 새로운 비밀번호 확인 인풋테그 감지해서 값 비교 */
$passwordInput.keyup(function(e){
    if($passwordInput.val() == $passwordCheck.val()){
        $errPasswordCheck.text("")
        checkAll[1] = true;
        changeButton();
    } else{
        $errPasswordCheck.text("비밀번호가 다릅니다")
        checkAll[1] = false;
    }
})


/* 비밀번호 확인 인풋테그 감지해서 값 비교 */
$passwordCheck.keyup(function(e){
    if($passwordInput.val() == $passwordCheck.val()){
        $errPasswordCheck.text("")
        checkAll[1] = true;
        changeButton();
    } else{
        $errPasswordCheck.text("비밀번호가 다릅니다")
        checkAll[1] = false;
    }
})


/* ----------------------------비밀번호------------------------------ */


/* 닉네임 */
$errNick = $(".err-nickname")
$(".nick-name").on("keyup",function(){
    if ($(this).val().length > 8){
        alert("최대 8자까지 입력 가능합니다.");
         $(this).val($(this).val().substring(0, 9));
         $('.nick-name').html(9);

        } else if($(this).val()){
            $errNick.text("")
            checkAll[2] = true;
            changeButton();

        }
        
        if(!$(this).val()){
            checkAll[2] = false;
            $errNick.text("닉네임을 입력해주세요")
        } else if($(this).val()){
            $errNick.text("")
            checkAll[2] = true;
            changeButton();

        }
})

/* 이름엔 영어와 한글만 가능하게 */
$(".name").on("keyup", function() {
    $(this).val($(this).val().replace(/[^(ㄱ-힣a-zA-Z)]/gi, ''));
    if($(this).val() != ""){
        checkAll[3] = true;
        changeButton();
    } else {
        checkAll[3] = false;
    }
 });

 /* 생일 */
 let regBirth = /^(19[0-9][0-9]|20\d{2})(0[0-9]|1[0-2])(0[1-9]|[1-2][0-9]|3[0-1])$/;

 $(".birth").on("keyup", function() {
    if($(this).val() != "" && $(this).val().length == 8 && regBirth.test($(this).val())){
        checkAll[4] = true;
        $(".err-birth").text("");
        changeButton();
    } else {
        checkAll[4] = false;
        $(".err-birth").text("생일을 정확히 입력해주세요")
    }
 });

/* 핸드폰 번호 */
/* 글자수에 따라서 검사 */
let regPhone= /^\d{2,3}-?\d{3,4}-?\d{4}$/;
$(".phone").on("keyup", function(){
    if($(this).val() != "" && $(this).val().length == 11 && regPhone.test($(this).val())){
        checkAll[5] = true;
        $(".err-phone").text("");
        changeButton();
    } else {
        checkAll[5] = false;
        $(".err-phone").text("핸드폰 번호를 재대로 입력해주세요")
    }
})


/* 지역 */
/* $("#region").on("keyup", function(){
    if(!$(this).val()){
        checkAll[7] = false;
        $(".err-region").text("지역을 입력해주세요");
    } else{
        checkAll[7] = true;
        $(".err-region").text("");
        changeButton();
    }
}) */




/* 이메일 */
const $email = $(".email");
const $helpEmail = $(".err-email")

var regExp = /^([\w-\.]+)@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.)|(([\w-]+\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\]?)$/;

$email.keyup(function(){
    if( !$email.val() ){ 
        $helpEmail.text("이메일을 입력해주세요")
        $errEmail.css('color', 'red')
        $email.focus();
     } else if (!regExp.test($email.val())) { 
           $helpEmail.text("이메일이 유효하지 않습니다.")
            $errEmail.css('color', 'red')
            $email.focus();
            checkAll[6] = false;
        } else {
            $helpEmail.text("");
            checkAll[6] = true;
            changeButton();

        }
        
})


console.log($(".check span"))
/* 이용약관 */
$(".check span").on("click", function(){
    /* 클릭하면 체크로 바뀌게 */
    let i = $(this).index() - 1;
    console.log(i)
    console.log($(this).attr("class"))
    if($(this).attr("class") == "span-check"){
        $(this).attr("class", "span-checked");
    } else{
        $(this).attr("class", "span-check");
    }
    
    /*만약 3개가 다 체크되어있다면 check-all은 checked로 바뀜*/
    if($(".check span").eq(0).attr("class") == "span-checked" && $(".check span").eq(1).attr("class") == "span-checked"){
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
        checkAll[8] = true;
        changeButton()
    } else {
        $(".check-all span").attr("class", "span-check")
        $(".check span").attr("class", "span-check")
        checkAll[8] = false;
    }
})

const $joinHelp = $(".id-err");
const $joinInputId = $('.identification');
var value = $joinInputId.val();

/*아이디 중복*/
$joinInputId.on("blur",function(){$.ajax({
    url:"/members/checkId" ,
    data: {"memberIdentification": $joinInputId.val()},
    success: function(result){
        let message;
        if(result == 1) {
            message = "중복된 아이디입니다.";
            $joinHelp.css('color', 'red')
            $joinHelp.css('display', 'block');
            checkAll[9] = false;

        }else if($joinInputId.val().length < 1){
            $joinHelp.css('display', 'block');
            $joinHelp.css('color', 'red');
            message = "필수 입력 사항입니다";
            checkAll[9] = false;

        }else{
            message = "사용 가능한 아이디입니다.";
            $joinHelp.css('display', 'block');
            $joinHelp.css('color', '#2bb673');
            checkAll[9] = true;
            changeButton();


        }
        $joinHelp.text(message);
        console.log("checkID 들어옴")
        console.log($joinInputId);
        console.log(checkAll[9]);

    }
});
});

/*이메일 중복*/
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
            checkAll[10] = false;

        } else if($inputEmail.val().length < 1){
            $errEmail.css('display', 'block');
            $errEmail.css('color', 'red');
            message = "필수 입력 사항입니다";
            checkAll[10] = false;

        } else if(!regExp.test($inputEmail.val())){
            $errEmail.css('display', 'block');
            $errEmail.css('color', 'red');
            message = "이메일 형식에 맞춰주세요";
            checkAll[10] = false;

        } else{
            message = "사용 가능한 이메일입니다.";
            $errEmail.css('display', 'block');
            $errEmail.css('color', '#2bb673');
            checkAll[10] = true;
            changeButton();
        }

        $errEmail.text(message);
        console.log("checkEamil 들어옴")
        console.log($inputEmail);
        console.log(checkAll[10]);
    }
});
});

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
            checkAll[11] = false;

        }else if($joinInputId.val().length < 1){
            $errorNickname.css('display', 'block');
            $errorNickname.css('color', 'red');
            message = "필수 입력 사항입니다";
            checkAll[11] = false;
        }else {
            message = "사용 가능한 닉네임입니다.";
            $errorNickname.css('display', 'block');
            $errorNickname.css('color', '#2bb673');
            checkAll[11] = true;
            changeButton();
        }
        $errorNickname.text(message);
        console.log("checkNickname 들어옴");
        console.log($joinInputNickname);
        console.log(checkAll[11]);

    }
});
});


/* 모든 정보가 있어야만 클릭 가능*/
function changeButton(){
    if(checkAll[0] && checkAll[1] && checkAll[2] && checkAll[3] && checkAll[4] && checkAll[5] && checkAll[6] && checkAll[7] && checkAll[8] && checkAll[9] && checkAll[10] && checkAll[11]){
        $(".join").attr("disabled", false);
    } else {
        $(".join").attr("disabled", true);
    }
}



/* 첫번째 모달 */
const modal1 = document.querySelector("#modal-agree")
function show1 () {
    modal1.style.display = "flex";
}

const closeBtn1 = modal1.querySelector("#modal-agree .close-area")
    closeBtn1.addEventListener("click", e => {
    modal1.style.display = "none"
})



/* 두번째 모달 */
const modal2 = document.querySelector("#modal-privacy")
function show2 () {
    modal2.style.display = "flex";
}

const closeBtn2 = modal2.querySelector("#modal-privacy .close-area")
    closeBtn2.addEventListener("click", e => {
    modal2.style.display = "none"
})


/* 모달창 아닌 부분을 클릭했을 시 사라짐 */
modal1.addEventListener("click", function(e){
    if(e.target === modal1){
        modal1.style.display = "none";
    }
})
modal2.addEventListener("click", function(e){
    if(e.target === modal2){
        modal2.style.display = "none";
    }
})

/*파일*/

function leftPad(value) {
    if (value >= 10) {
        return value;
    }

    return `0${value}`;
}

function toStringByFormatting(source, delimiter = '/') {
    const year = source.getFullYear();
    const month = leftPad(source.getMonth() + 1);
    const day = leftPad(source.getDate());

    return [year, month, day].join(delimiter);
}



