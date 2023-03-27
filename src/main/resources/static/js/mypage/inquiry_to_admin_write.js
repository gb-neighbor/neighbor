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


const body = document.querySelector('html');
const select = document.querySelector(`[data-role="selectBox"]`);
// const values = select.querySelector(`[date-value="optValue"]`);
// const option = select.querySelector('ul');
// const opts = option.querySelectorAll('li');

/* 셀렉트영역 클릭 시 옵션 숨기기, 보이기 */
// function selects(e){
//     e.stopPropagation();
//     option.setAttribute('style',`top:${select.offsetHeight}px`)
//     if(option.classList.contains('hide')){
//         option.classList.remove('hide');
//         option.classList.add('show');
//     }else{
//         option.classList.add('hide');
//         option.classList.remove('show');
//     }
//     selectOpt();
// }

/* 옵션선택 */
// function selectOpt(){
//     opts.forEach(opt=>{
//         const innerValue = opt.innerHTML;
//         function changeValue(){
//             values.innerHTML = innerValue;
//         }
//         opt.addEventListener('click',changeValue)
//     });
// }

/* 렌더링 시 옵션의 첫번째 항목 기본 선택 */
// function selectFirst(){
//     const firstValue = opts[0].innerHTML;
//     values.innerHTML = `${firstValue}`
// }

/* function firstColor(){;
    if(values.contains('first')){;
        select.style.color = 'green';
    };
}; */

/* 옵션밖의 영역(=바디) 클릭 시 옵션 숨김 */
// function hideSelect(){
//     if(option.classList.contains('show')){
//         option.classList.add('hide');
//         option.classList.remove('show');
//     }
// }

// selectFirst();
// select.addEventListener('click',selects);
// body.addEventListener('click',hideSelect);

// function collapse(element) {
//     var before = document.getElementsByClassName("active")[0]               // 기존에 활성화된 버튼
//     if (before && document.getElementsByClassName("active")[0] != element) {  // 자신 이외에 이미 활성화된 버튼이 있으면
//         //before.nextElementSibling.style.maxHeight = null;   // 기존에 펼쳐진 내용 접고
//         // before.classList.remove("active");                  // 버튼 비활성화
//     }
//     element.classList.toggle("active");         // 활성화 여부 toggle
//
//     var content = element.nextElementSibling;
//     if (content.style.maxHeight != 0) {         // 버튼 다음 요소가 펼쳐져 있으면
//         content.style.maxHeight = null;         // 접기
//     } else {
//         content.style.maxHeight = content.scrollHeight + "px";  // 접혀있는 경우 펼치기
//     }
// }




function writeScript(){;
    var form1 = document.inquiryForm;
    let modalMessage = '';


    if (form1.title.value=="") {
        modalMessage = "제목을 입력해주세요.";
        showWarnModal(modalMessage);
        return;
    }
    if (form1.content.value=="") {
        modalMessage = "내용을 입력해주세요.";
        showWarnModal(modalMessage);
        return;
    }


    form1.submit();
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