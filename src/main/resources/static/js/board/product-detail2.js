function controllImgSize(){
    if($("#expandedImg").attr('src', "/image/white.png")){
        $("#expandedImg").css("width", "30%")
        $("#expandedImg").css("height", "30%")
    } else{
        $("#expandedImg").css("width", "100%")
        $("#expandedImg").css("height", "100%")
    }
}
function moveToSpot(link, spot) {
    console.log($('.board-navigation').children()[0]);
    $(link).parent().children("a").removeClass("current");
    $(link).addClass('current');
    let location = $(spot)[0].offsetTop - 700;
    console.log(location)
    window.scrollTo({top: location, behavior: "smooth"});
}

function myFunction(imgs) {
    // Get the expanded image
    var expandImg = document.getElementById('expandedImg');
    // Get the image text
    var imgText = document.getElementById('imgtext');
    // Use the same src in the expanded image as the image being clicked on from the grid
    expandImg.src = imgs.src;
    // Use the value of the alt attribute of the clickable image as text inside the expanded image
    imgText.innerHTML = imgs.alt;
    // Show the container element (hidden with CSS)
    expandImg.parentElement.style.display = 'block';
}

/* 글자수 */
function getLength(str) {
    var max_byte = str.length;
    var str_len = document.form.reply.value.length;

    if (max_byte == 1001) {
        modalMessage = '1000자를 넘길 수 없습니다.';
        showWarnModal(modalMessage);
        document.form.content.value = document.form.content.value.substring(0, str_len - 1);
        document.form.txtlen.value = max_byte - 1;
        return;
    } else {
        document.form.txtlen.value = max_byte;
    }
}

/* 모달창 */
let modalCheck;

function showWarnModal(modalMessage) {
    modalCheck = false;
    $('div#content-wrap').html(modalMessage);
    $('div.warn-modal').css('animation', 'popUp 0.5s');
    $('div.modal').css('display', 'flex').hide().fadeIn(500);
    setTimeout(function () {
        modalCheck = true;
    }, 500);
}

$('div.modal').on('click', function () {
    if (modalCheck) {
        $('div.warn-modal').css('animation', 'popDown 0.5s');
        $('div.modal').fadeOut(500);
    }
});

    $('.review-write').click(function () {
        if ($('.review-my').css('display') == 'none') {
            $.ajax({
                url: `/replies/checks/${boardIdForReply}`,
                success: function (check) {
                    console.log(check)
                    if(!check){
                        showWarnModal();
                        $(".review-my-textarea").val("리뷰는 한번만 작성할 수 있습니다. 신중하게 작성해주세요")
                    }
                }
            });
            // 숨겨진 상태면 보이게 함
            $('.review-my').show(); // review-my 클래스 전체를 보이도록 수정
        }

    // 취소 버튼 클릭 시
    $('.review-my-score-cancel').click(function () {
        $('.review-my').hide(); // review-my 클래스 전체를 숨기도록 수정
        replyInput.val('');
        $(".review-my-form input[type=hidden]").remove();
        $("ul.replyImg li").remove();
        stars.forEach((star) => ($(star).attr('src', '/css/main/images/grey-star.png')))
    });
});

const stars = document.querySelectorAll('.review-my-star');

stars.forEach((star) => {
    star.addEventListener('click', () => {
        const index = star.getAttribute('data-index');

        // 클릭된 star 이미지와 그 이전 이미지의 모든 grey-star 이미지를 변경
        for (let i = 1; i <= index; i++) {
            const img = document.querySelector(`.review-my-star[data-index="${i}"]`);
            img.setAttribute('src', '/css/board/images/star.png');
        }

        // 클릭된 star 이미지 이후의 grey-star 이미지를 변경
        for (let i = parseInt(index) + 1; i <= 5; i++) {
            const img = document.querySelector(`.review-my-star[data-index="${i}"]`);
            img.setAttribute('src', '/css/board/images/grey-star.png');
        }
    });
});

/* *************************************모달***************************************** */

/* 모달창 js */

function modal(name, id) {
    var zIndex = 998;
    var modal = $(name + id);

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
        .on('click', function () {
            bg.hide();
            modal.hide();
            $messageBox.children().remove();
            globalThis.page = 1;
            $('#write-section' + id).val('');
            document.getElementById("text_length" + id).value = 0;
        });
}

const warnModalBack = $(".modal_background");
warnModalBack.on("click", function () {
    warnModalBack.hide();
    $(".review-write").hide()
    $(".review-write-form").hide()
    $(".review-my").hide()
})

warnModalBack.on("click", function () {
    const modal = $(".modal_background");
    modal.hide();
})

function showWarnModal() {
    const warnModal = $(".modal-warn");
    warnModalBack.css("display", "block");
    warnModal.css("width", "300px")
    warnModal.css("height", "300px")
    warnModal.css("position", "fixed")

}
/*********************************************************************************************/

function getLength(num) {
    var contentValue = document.getElementById("write-section" + num).value;
    var txtlenValue = document.getElementById("text_length" + num);
    var max_byte = contentValue.length;
    var str_len = contentValue.length;

    if (max_byte == 1001) {
        modalMessage = "1000자를 넘길 수 없습니다.";
        showWarnModal(modalMessage);
        document.getElementById("write-section" + num).value = document.getElementById("write-section" + num).value.substring(0, str_len - 1);
        txtlenValue.value = max_byte - 1;
        return;
    } else {
        txtlenValue.value = max_byte;
    }

}


/* **************************************banner***************************************** */
HTMLCollection.prototype.forEach = Array.prototype.forEach;
/* 랜덤추천 메뉴 */
const imageRandomDiv = document.querySelectorAll('ul.row img');
const randomNext = document.querySelector('.next');
const randomPrev = document.querySelector('.prev');
const randomBanner = document.querySelector('ul.row');
globalThis.displayNext = 0;
let count2 = 0;
var randomImageCount = 0;
let checkArrow2 = false;
randomPrev.style.display = "none";


// translate 만지기
/*function pushPrev() {
    if(randomImageCount != 0) {
        if (checkArrow2) {
            return;
        }
        checkArrow2 = true;
        randomBanner.style.transition = 'transform 0.3s';
        /!* randomBanner.style.transform = `translate(${148.4925 * ++count2}px)`; *!/
        randomBanner.style.transform = `translate(${149.1 * ++count2}px)`;
        randomImageCount--;
        setTimeout(() => {
            checkArrow2 = false;
        }, 30);
    }
    if(randomImageCount==0) {
        randomPrev.style.display = "none";
    }
    if(globalThis.displayNext==1) {
        randomNext.style.display = "block";
    }
	console.log(globalThis.displayNext)
	console.log(count2)
	console.log(randomImageCount)
	console.log(checkArrow2)
};

function pushNext() {
    if(randomImageCount== boardDTO.length) {
        randomNext.style.display = "none";
        displayNext = 1;
    }

    if(randomImageCount != boardDTO.length) {
        randomImageCount++;
        if (checkArrow2) {
            return;
        }
        if(randomImageCount!=0) {
            randomPrev.style.display = "block";
        }
        checkArrow2 = true;
        randomBanner.style.transition = 'transform 0.3s';
        /!* randomBanner.style.transform = `translate(${148.4925 * --count2}px)`; *!/
        randomBanner.style.transform = `translate(${149.1 * --count2}px)`;
        setTimeout(() => {
            checkArrow2 = false;
        }, 30);
    }
};*/
console.log(boardDTO.files.length)
if (boardDTO.files.length < 6) {
    randomNext.style.display = "none";
    console.log("들어어어어옴")
}

function pushPrev() {
    if (randomImageCount !== 0) {
        if (checkArrow2) {
            return;
        }
        checkArrow2 = true;
        randomBanner.style.transition = 'transform 0.3s';
        randomBanner.style.transform = `translate(${149.1 * ++count2}px)`;
        randomImageCount--;
        setTimeout(() => {
            checkArrow2 = false;
        }, 30);
    }
    if (randomImageCount === 0) {
        randomPrev.style.display = "none";
    }
    if (boardDTO.length > 4) {
        if (displayNext === 1) {
            randomNext.style.display = "block";
        }
    } else {
        randomNext.style.display = "none";
    }
    if (boardDTO.files.length > 3 && randomImageCount < boardDTO.files.length - 1) {
        randomNext.style.display = "block";
    }
};

function pushNext() {
    if (randomImageCount === boardDTO.length) {
        randomNext.style.display = "none";
        globalThis.displayNext = 1;
    }

    if (randomImageCount !== boardDTO.length) {
        randomImageCount++;
        if (checkArrow2) {
            return;
        }
        if (randomImageCount !== 3) {
            randomPrev.style.display = "block";
        }
        checkArrow2 = true;
        randomBanner.style.transition = 'transform 0.3s';
        randomBanner.style.transform = `translate(${149.1 * --count2}px)`;
        setTimeout(() => {
            checkArrow2 = false;
        }, 30);
    }
    if (boardDTO.length > 3) {
        if (randomImageCount === boardDTO.length - 1) {
            randomNext.style.display = "none";
            globalThis.displayNext = 1;
        } else {
            randomNext.style.display = "block";
            globalThis.displayNext = 0;
        }
    } else {
        randomNext.style.display = "none";
    }
    if (boardDTO.files.length >= 4 && randomImageCount > 0) {
        randomPrev.style.display = "block";
    } else {
        randomPrev.style.display = "none";
    }
}

/* ***************************tothetop 버튼******************************* */
var topBtn = document.getElementById("topBtn");

window.onscroll = function () {
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

/*********************************************************************************************/
const $messageBox = $(".box_text");
const $infoBox = $('.box_top');

globalThis.page = 1;
globalThis.isScrollDown = true;
globalThis.scrollHeight = $messageBox[0].scrollHeight;

function getMessageRoom() {
    messageService.targetInfo(showTargetInfo);
    messageService.list(showMessage);
    $messageBox.scrollTop(globalThis.scrollHeight);
}
var scrollPos;

$messageBox.scroll(function () {
    if ($messageBox.scrollTop() == 0) {
        // 스크롤이 맨 위에 도달하면 가상 요소를 생성하여 추가
        globalThis.page++;
        globalThis.isScrollDown = false;
        globalThis.scrollHeight;
        messageService.list(showMessage);
    }
});

function refreshClicked() {
    globalThis.page = 1;
    messageService.list(showMessage);
    globalThis.scrollHeight = $messageBox[0].scrollHeight;
    $messageBox.scrollTop($messageBox[0].scrollHeight);
}


function openModalBanner(num) { /* 괄호에 num으로 받기 */
    modal('.my_modal', num);
}

const messageService = (function () {
    function list(callback) {
        $.ajax({
            url: "/messages/detail/" + boardId + "/" + memberId + "/" + targetId + "/" + globalThis.page,
            dataType: "json",
            method: "post",
            success: function (messages) {
                if (messages != null) {
                    if (callback) {
                        callback(messages);
                    }
                }
                if(globalThis.page ==1){
                    $messageBox.scrollTop($messageBox[0].scrollHeight);
                    scrollPos=$messageBox[0].scrollHeight;
                }else{
                    $messageBox.scrollTop($messageBox[0].scrollHeight-scrollPos);
                    scrollPos=$messageBox[0].scrollHeight;
                }
            }
        });
    }

    function targetInfo(callback) {
        $.ajax({
            url: "/messages/targetInfo/" + targetId + "/" + boardId,
            dataType: "json",
            method: "post",
            success: function (Infos) {
                if (callback) {
                    callback(Infos);
                }
                openModalBanner(2);
            }
        });
    }


    return {list: list, targetInfo: targetInfo};
})();

$('.send_form').submit(function (e) {
    e.preventDefault();
    if ($("#write-section2").val()) {
        let messageVO = {
            boardId: boardId,
            messageSenderId: memberId,
            messageGetterId: targetId,
            messageContent: $("#write-section2").val()
        };
        globalThis.page = 1;
        $.ajax({
            url: "/messages/insert",
            type: $(this).attr('method'),
            data: JSON.stringify(messageVO),
            contentType: 'application/json; charset=utf-8',
            dataType: 'json',
            success: function (data) {
                $("#write-section2").val('');
                $("#text_length2").val('0');

                messageService.list(showMessage);
                if (globalThis.isScrollDown) {
                    $messageBox.scrollTop($messageBox[0].scrollHeight);
                } else {
                    $messageBox.scrollTop(globalThis.scrollHeight);
                }
            }
        });
    } else {
        alert("message cannot be null")
    }
});

function showTargetInfo(Infos) {

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

function showMessage(messages) {
    let messagesList = "";
    messages.forEach(message => {
        if (message.messageSenderId == memberId) {
            messagesList = `
        <div class="my_text_wrap">
          <div class="my_text">
            <p class="my_text_content">${message.messageContent}</p>
          </div>
          <div class="my_text_time">
            <p class="message_box_time">${message.messageRegisterDate}</p>
          </div>
        </div>` + messagesList;
        } else {
            messagesList = `
        <div class="opponent_text_wrap">
          <div class="opponent_text">
            <p class="opponent_text_content">${message.messageContent}</p>
          </div>
          <div class="opponent_text_time">
            <p class="message_box_time">${message.messageRegisterDate}</p>
          </div>
        </div>` + messagesList;
        }
    });

    globalThis.page == 1 ? $messageBox.html(messagesList) : $messageBox.prepend(messagesList);

}

/*********************************************************************************************/
$(".review-my-score-cancel").click(function () {
    replyInput.val('');
    $('.review-my').hide();
    $(".review-my-form input[type=hidden]").remove();
    $("ul.replyImg").remove();
    stars.forEach((star) => ($(star).attr('src', '/css/main/images/grey-star.png')))
})
/*FileList.prototype.forEach = Array.prototype.forEach;
globalThis.arrayFile = new Array();
let replyOriginalNameArray = new Array();
let replyFileSize = new Array();
let formData = new FormData();
globalThis.i = 0;
let text = "";

$("input[type=file]").on("change", function () {
    const $files = $("input[type=file]")[0].files;
    // console.log($files)
    // 파일을 선택하지 않은 경우 처리
    if ($files.length === 0) {
        return;
    }
    Array.from($files).forEach(file => globalThis.arrayFile.push(file));
    $files.forEach(file => {
        console.log(file)
        replyOriginalNameArray.push(file.name)
        replyFileSize.push(file.size)
        formData.append("file", file)
    });
    $.ajax({
        url: "/reply-files/upload",
        type: "post",
        data: formData,
        contentType: false,
        processData: false,
        success: function (uuids) {
            console.log(uuids)
            globalThis.uuids = uuids;
            const dataTransfer = new DataTransfer();
            $files.forEach((file, i) => {
                $(".replyImg").append(`<li><img src="/board-files/display?fileName=replies/${toStringByFormatting(new Date())}/t_${uuids[i]}_${file.name}"></li>`);
            })
            $("input[id='files']")[0].files = dataTransfer.files;
            $files.forEach(file => {
                text +=
                    `
        <input type="hidden" name="files[${i}].replyFileOriginalName" value="${file.name}">
        <input type="hidden" name="files[${i}].replyFileUuid" value="${globalThis.uuids[i]}">
        <input type="hidden" name="files[${i}].replyFilePath" value="${toStringByFormatting(new Date())}">
        <input type="hidden" name="files[${i}].replyFileSize" value="${file.size}">
        `
                globalThis.i++;
            });
            globalThis.i = 0;
        }
    });
});*/

FileList.prototype.forEach = Array.prototype.forEach;
globalThis.arrayFile = new Array();
globalThis.i = 0;
let replyOriginalNameArray = new Array();
let replyFileSize = new Array();

$("input[id='files']").on("change", function() {
    const $files = $("input[id=files]")[0].files;
    if ($files.length === 0) {
        return;
    }
//    파일 객체에 접근함
    let formData = new FormData();
    Array.from($files).forEach(file => globalThis.arrayFile.push(file));
    // 파일 Array의 file들을 하나씩 담아줌
    $files.forEach(file => {
        replyOriginalNameArray.push(file.name)
        replyFileSize.push(file.size)
        formData.append("file", file)
    });
    $.ajax({
        url: "/reply-files/upload",
        type: "post",
        data: formData,
        contentType: false,
        processData: false,
        success: function (uuids) {
            globalThis.uuids = uuids;
            console.log(globalThis.uuids)

            $files.forEach((file, i) => {
                $(".replyImg").html(`<li><img src="/board-files/display?fileName=/replies/${toStringByFormatting(new Date())}/t_${uuids[i]}_${file.name}"></li>`);
                $(".add-button").css("margin-top", "172px");
            });
            const dataTransfer = new DataTransfer();
            $("input[id='files']")[0].files = dataTransfer.files;
            let text = "";
            console.log("uuid는 " + globalThis.uuids)
            i=0;
        }
    });
});


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

/********************************************************************************************/
/* 보드에서 디테일 페이지 작업 */
let boardFiles = boardDTO.files;
let mainFile = boardFiles.filter(file => file.boardFileStatus);
let restFile = boardFiles.filter(file => !file.boardFileStatus);

function insertPhoto(files) {
}
console.log(mainFile.length)
console.log()
let thumbNails = '';
if(mainFile.length != 0){
    /* 메인파일 넣는 코드 */
    $(".product-img-container img").attr("src", `/board-files/display?fileName=boards/${mainFile[0].boardFilePath}/${mainFile[0].boardFileUuid}_${mainFile[0].boardFileOriginalName}`);
}
if(restFile.length != 0){
    /* 나머지 파일 넣는 코드 */
    $("#expandedImg").attr("src", `/board-files/display?fileName=boards/${restFile[0].boardFilePath}/${restFile[0].boardFileUuid}_${restFile[0].boardFileOriginalName}`)
} else{
    $("#expandedImg").css("display", "none")
}

for (let i = 0; i < restFile.length; i++) {
    thumbNails +=
        `
		<li class="column">
           <img src= "/board-files/display?fileName=boards/${restFile[i].boardFilePath}/${restFile[i].boardFileUuid}_${restFile[i].boardFileOriginalName}" onclick="myFunction(this);">
        </li>
	`;
}
$(".paging-container .row").html(thumbNails)

$(".total-review-score").append(generateStarHtml(boardDTO.avgScore))
$(".review-average-star-wrap").append(generateStarHtml(boardDTO.avgScore))


/* 별점 생성코드 */
function generateStarHtml(avgScore) {
    let stars = '';
    for (let j = 0; j < 5; j++) {
        if (j < avgScore) {
            if (j < 5) {
                stars += "<img src='/css/main/images/star.png'>";
            }
        } else {
            stars += "<img src='/css/main/images/grey-star.png'>";
        }
    }
    return stars;
}


/* -------------------------------------디테일 끝 */
/* 댓글 시작 */
let pathArray = window.location.pathname.split('/');
let boardId2 = pathArray.pop();
let replyScore = 0;
const stars2 = document.querySelectorAll('.review-my-star');
const replyInput = $(".review-my-textarea");
/* 별점 가져오기 */
stars2.forEach((star) =>
    star.addEventListener(("click"), function () {
        replyScore = star.getAttribute('data-index')
    })
)

/*$("input[type=file]").change(function () {
	let replyFile = $("input[type=file]")[0].files;
		replyFile.forEach((file, i) => {
		let replyFileVO = new Object();
			replyFileVO.fileOriginalName = file.name;
			replyFileVO.fileUuid = globalThis.uuids[i];
			replyFileVO.filePath = toStringByFormatting(new Date());
			replyFileVO.fileSize = file.size;
			replyFileVO.boardId = boardId2;
			replyFileArray.push(replyFileVO);
	});
	console.log(replyFileArray)
	console.log(globalThis.uuids[0])
})*/
/* 리뷰작성 버튼을 눌렀을때 한번만 작성할 수 있도록 */




/* 등록버튼을 눌렀을때  */
$('.review-my-score-submit').on('click', function () {
    console.log(globalThis.uuids)
    let replyFileArray = new Array();

    if (globalThis.uuids != null) {
        for (let i = 0; i < replyOriginalNameArray.length; i++) {
            let replyFileVO = new Object();
            replyFileVO.replyFileOriginalName = replyOriginalNameArray[i];
            replyFileVO.replyFileUuid = globalThis.uuids[i];
            replyFileVO.replyFilePath = toStringByFormatting(new Date());
            replyFileVO.replyFileSize = replyFileSize[i];
            replyFileArray.push(replyFileVO);
        }
    }
    let $value = $(".review-my-textarea").val()
    let jsonObject = {
        replyScore: parseInt(replyScore),
        replyContent: $value,
        files: replyFileArray
    };

    $.ajax({
        type: 'POST',
        url: `/replies/upload/${boardId2}`,
        data: JSON.stringify(jsonObject),
        contentType: 'application/json; charset=UTF-8',
        success: function (data) {
            globalThis.i = 0;
            replyOriginalNameArray = new Array();
            replyFileSize = new Array();
            globalThis.uuids = new Array();
            replyInput.val('');
            $('.review-my').hide();
            $(".review-my-form input[type=hidden]").remove();

            $("ul.replyImg li").remove();
            stars.forEach((star) => ($(star).attr('src', '/css/main/images/grey-star.png')))

            $.ajax({
                url: `/replies/lists/${boardIdForReply}/1`, // 1페이지만 가져오기
                method: 'post',
                success: function (replyDTOList) {
                    const $ul = $("ul.review-list");
                    $ul.empty(); // 기존 댓글 모두 지우기
                    let replies = '';
                    replyDTOList.forEach(reply => {
                        const starsForReply = generateStarHtml(reply.replyScore);
                        const thumbsForReply = generateThumbsHtml(reply.files);
                        replies +=
                            `
        		<li>
                        <div class="review-user-info">
                            <p class="review-user-nickname">${reply.memberNickname}</p>
                            <p class="review-score-wrap">
                                    <span class="stars">
                                    ${starsForReply}
                                    </span></p>
                            <p class="review-date">${reply.replyUpdateDate}</p>
                        </div>
                        <div class="review-photo">
                            <div class="review-photo-flex-wrap">
                                ${thumbsForReply}
                            </div>
                        </div>
                        <!-- 사진 끝 -->
                        <div class="review-letters">
                            ${reply.replyContent}
                        </div>
                    </li>
                    <!-- 게시물 하나 끝 -->
       `;
                    });
                    $ul.append(replies);
                    getProfileImage();
                }
            })
        }
    });
})

/* --------------------------------- 업로드 -----------------------*/


/* 무한스크롤 */
let page = 1;
let pathArrayForReply = window.location.pathname.split('/');
let boardIdForReply = pathArrayForReply.pop();
const $ul = $("ul.review-list");

const replyService = (() => {
    function getList(callback) {
        $.ajax({
            url: `/replies/lists/${boardIdForReply}/${page}`,
            method: 'post',
            success: function (replyDTOList) {
                console.log(replyDTOList)
                if (callback) {
                    callback(replyDTOList);
                }
            }
        });
    }

    return {getList: getList};
})();

function appendList() {
    replyService.getList(replyDTOList => {
        let replies = '';
        replyDTOList.forEach(reply => {
            const starsForReply = generateStarHtml(reply.replyScore);
            const thumbsForReply = generateThumbsHtml(reply.files);
            replies +=
                `
        		<li>
                        <div class="review-user-info">
                            <p class="review-user-nickname">${reply.memberNickname}</p>
                            <p class="review-score-wrap">
                                    <span class="stars">
                                    ${starsForReply}
                                    </span></p>
                            <p class="review-date">${reply.replyUpdateDate}</p>
                        </div>
                        <div class="review-photo">
                            <div class="review-photo-flex-wrap">
                                ${thumbsForReply}
                            </div>
                        </div>
                        <!-- 사진 끝 -->
                        <div class="review-letters">
                            ${reply.replyContent}
                        </div>
                    </li>
                    <!-- 게시물 하나 끝 -->
       `;
        });
        if (replyDTOList.length === 0) { // 불러올 데이터가 없으면
            $(window).off('scroll'); // 스크롤 이벤트를 막음
            return;
        }
        $ul.append(replies);
        getProfileImage();
    });


}

appendList();

$(window).scroll(function () {
    let zoomLevel = $('body').css('zoom');
    if (zoomLevel === '0.8') {
        if (Math.ceil($(window).scrollTop() / (zoomLevel)) + Math.ceil($(window).height() / zoomLevel) + 1 > $(document).height()) {
            page++;
            appendList();
            console.log(page)
            getProfileImage();
        }
    }
});

/* 썸네일 사진 생성 코드 */
function generateThumbsHtml(files) {
    let thumbs = '';
    files.forEach(file => {
        console.log(file.boardFileOriginalName)
            thumbs +=
                `
             <div class="review-photo-flex-inner">
                 <a href="" class="review-img-hover">
                     <div class="review-img-wrap">
                         <div class="review-img-inner" 
                data-reply-file-path="${file.replyFilePath}" 
                data-reply-file-uuid="${file.replyFileUuid}"
                data-reply-file-original-name="${file.replyFileOriginalName}">
            </div>
                     </div>
                 </a>
             </div>
        `;

        });
    return thumbs;
}

/* 무한스크롤 */
function getProfileImage() {
    $(document).ready(function () {
        $('.review-img-inner').each(function (i) {
            let replyFilePath = $(this).data('reply-file-path');
            let replyFileUuid = $(this).data('reply-file-uuid');
            let replyFileOriginalName = $(this).data('reply-file-original-name');
            let replyUrl = '/board-files/display?fileName=' + '/replies/' + replyFilePath + '/t_' + replyFileUuid + '_' + replyFileOriginalName;
            $(this).css('background-image', 'url(' + replyUrl + ')');
        });
    });
}
/* ========================= */

/* 판매종료 */
function sell(){
    $.ajax({
        url: `/board/sell/${boardIdForReply}`,
        success: function () {
            $(".closeSale").remove();
            location.reload();
        }
    });
}