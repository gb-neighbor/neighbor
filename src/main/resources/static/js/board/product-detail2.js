function moveToSpot(link, spot) {
	console.log($('.board-navigation').children()[0]);
	$(link).parent().children("a").removeClass("current");
	$(link).addClass('current');
	let location = $(spot)[0].offsetTop-700;
    console.log(location)
	window.scrollTo({ top: location, behavior: "smooth" });
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
	var str_len = document.form.content.value.length;

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

$(document).ready(function () {
	$('.review-write').click(function () {
		if ($('.review-my').css('display') == 'none') {
			// 숨겨진 상태면 보이게 함
			$('.review-my').show(); // review-my 클래스 전체를 보이도록 수정
		}
	});

	// 취소 버튼 클릭 시
	$('.review-my-score-cancel').click(function () {
		$('.review-my').hide(); // review-my 클래스 전체를 숨기도록 수정
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
	var modal = $(name+id);

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
			$messageBox.children().remove();
			globalThis.page =1;
			$('#write-section'+id).val('');
			document.getElementById("text_length"+id).value = 0;
		});
}



/*********************************************************************************************/

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
		document.getElementById("write-section"+num).value = document.getElementById("write-section"+num).value.substring(0, str_len-1);
		txtlenValue.value = max_byte-1;
		return;
	}
	else
	{
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
	if (boardDTO.length > 3) {
		if (displayNext === 1) {
			randomNext.style.display = "block";
		}
	} else {
		randomNext.style.display = "none";
	}
};

function pushNext() {
	if (randomImageCount === boardDTO.length) {
		randomNext.style.display = "none";
		displayNext = 1;
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
			displayNext = 1;
		} else {
			randomNext.style.display = "block";
			displayNext = 0;
		}
	} else {
		randomNext.style.display = "none";
	}
};

/* ***************************tothetop 버튼******************************* */
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

/*********************************************************************************************/
const $messageBox = $(".box_text");
const $infoBox = $('.box_top');

globalThis.page = 1;
globalThis.isScrollDown = true;
globalThis.scrollHeight = $messageBox[0].scrollHeight;

function getMessageRoom(){
	messageService.targetInfo(showTargetInfo);
	messageService.list(showMessage);
	$messageBox.scrollTop(globalThis.scrollHeight);
}


$messageBox.scroll(function() {
	if ($messageBox.scrollTop() == 0) {
		// 스크롤이 맨 위에 도달하면 가상 요소를 생성하여 추가
		globalThis.page++;
		globalThis.isScrollDown = false;
		globalThis.scrollHeight;
		messageService.list(showMessage);
	}
});

function refreshClicked(){
	globalThis.page=1;
	messageService.list(showMessage);
	globalThis.scrollHeight = $messageBox[0].scrollHeight;
	$messageBox.scrollTop(globalThis.scrollHeight);
}


function openModalBanner(num){ /* 괄호에 num으로 받기 */
	modal('.my_modal', num);
}

const messageService=(function(){
	function list(callback){
		$.ajax({
			url: "/messages/detail/"+boardId+"/"+memberId+"/"+targetId+"/"+globalThis.page,
			dataType: "json",
			method: "post",
			success: function(messages){
				if(messages!=null){
					if(callback){
						callback(messages);
					}
				}
			}
		});
	}

	function targetInfo(callback){
		$.ajax({
			url: "/messages/targetInfo/"+targetId +"/"+boardId,
			dataType: "json",
			method: "post",
			success: function(Infos){
				if(callback){
					callback(Infos);
				}
				openModalBanner(2);
			}
		});
	}


	return {list:list, targetInfo:targetInfo};
})();

$('.send_form').submit(function(e) {
	e.preventDefault();
	if($("#write-section2").val()){
		let messageVO = {
			boardId: boardId,
			messageSenderId: memberId,
			messageGetterId: targetId,
			messageContent: $("#write-section2").val()
		};
		globalThis.page=1;
		$.ajax({
			url: "/messages/insert",
			type: $(this).attr('method'),
			data: JSON.stringify(messageVO),
			contentType: 'application/json; charset=utf-8',
			dataType: 'json',
			success: function(data) {
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
	}else{
		alert("message cannot be null")
	}
});

function showTargetInfo(Infos){

	let target = `
		<div class="profile_image_section">
            <img class="profile_image" src="">
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
        <a class="go_to_board" href="/board">
            <p class="go_to_board_text">상세보기&nbsp</p>
            <p class="go_to_board_text right_text">></p>
        </a>
	`;
	$infoBox.html(target);
}

function showMessage(messages){
	let messagesList = "";
	messages.forEach(message => {
		if(message.messageSenderId==memberId){
			messagesList=`
        <div class="my_text_wrap">
          <div class="my_text">
            <p class="my_text_content">${message.messageContent}</p>
          </div>
          <div class="my_text_time">
            <p class="message_box_time">${message.messageRegisterDate}</p>
          </div>
        </div>`+messagesList;
		}else{
			messagesList=`
        <div class="opponent_text_wrap">
          <div class="opponent_text">
            <p class="opponent_text_content">${message.messageContent}</p>
          </div>
          <div class="opponent_text_time">
            <p class="message_box_time">${message.messageRegisterDate}</p>
          </div>
        </div>`+messagesList;
		}
	});

	globalThis.page == 1 ? $messageBox.html(messagesList) : $messageBox.prepend(messagesList);

}

/*********************************************************************************************/

/* 보드에서 디테일 페이지 작업 */
let files = boardDTO.files;
let mainFile = files.filter(file => file.boardFileStatus);
let restFile = files.filter(file=> !file.boardFileStatus);
console.log(mainFile)
console.log(mainFile[0].boardFilePath)
function insertPhoto(files){
}
console.log()
let thumbNails = '';
/* 메인파일 넣는 코드 */
$(".product-img-container img").attr("src", `/board-files/display?fileName=boards/${mainFile[0].boardFilePath}/${mainFile[0].boardFileUuid}_${mainFile[0].boardFileOriginalName}`);
/* 나머지 파일 넣는 코드 */
$("#expandedImg").attr("src",`/board-files/display?fileName=boards/${restFile[0].boardFilePath}/${restFile[0].boardFileUuid}_${restFile[0].boardFileOriginalName}` )

for (let i = 0; i < restFile.length; i++) {
	thumbNails +=
		`
		<li class="column">
           <img src= "/board-files/display?fileName=boards/${restFile[i].boardFilePath}/${restFile[i].boardFileUuid}_${restFile[i].boardFileOriginalName}" onclick="myFunction(this);">
        </li>
	`;
}
$(".paging-container .row").append(thumbNails)

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