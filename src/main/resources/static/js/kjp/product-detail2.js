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
			img.setAttribute('src', '../../static/css/kdh/images/star.png');
		}

		// 클릭된 star 이미지 이후의 grey-star 이미지를 변경
		for (let i = parseInt(index) + 1; i <= 5; i++) {
			const img = document.querySelector(`.review-my-star[data-index="${i}"]`);
			img.setAttribute('src', '../../static/css/kdh/images/grey-star.png');
		}
	});
});

/**************************************모달***************************************** */
// 모달창 열기
var modal = document.getElementById("message-modal");
var openBtn = document.getElementById("openBtn");
openBtn.onclick = function() {
    modal.style.display = "block";
}

// 모달창 닫기
var closeBtn = document.getElementsByClassName("close")[0];
var cancelBtn = document.getElementById("cancelBtn");
closeBtn.onclick = function() {
    modal.style.display = "none";
}
// cancelBtn.onclick = function() {
//     var title = document.getElementById("title").value;
//     modal.style.display = "none";
// }

// 쪽지 보내기
// var form = document.getElementById("noteForm");
// form.addEventListener("submit", function(event) {
//   event.preventDefault(); // 폼 전송 막기
//   var title = document.getElementById("title").value;
//   var content = document.getElementById("content").value;
//   console.log("제목:", title);
//   console.log("내용:", content);
//   modal.style.display = "none"; // 모달창 닫기
// });

/* 글자수 */
function getLength2(str) {
	var max_byte = str.length;
	var str_len = document.messageForm.messageContent.value.length;

	if (max_byte == 1001) {
		modalMessage = '1000자를 넘길 수 없습니다.';
		showWarnModal(modalMessage);
		document.messageForm.messageContent.value = document.messageForm.messageContent.value.substring(0, str_len - 1);
		document.messageForm.messagetxtlen.value = max_byte - 1;
		return;
	} else {
		document.messageForm.messagetxtlen.value = max_byte;
	}
}

/*********************************************************************************/
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

function pushPrev() {
    if(randomImageCount != 0) {
        if (checkArrow2) {
            return;
        }
        checkArrow2 = true;
        randomBanner.style.transition = 'transform 0.3s';
        randomBanner.style.transform = `translate(${148.4925 * ++count2}px)`;
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
};

function pushNext() {
    if(randomImageCount==3) {
        randomNext.style.display = "none";
        displayNext = 1;
    }

    if(randomImageCount != 4) {
        randomImageCount++;
        if (checkArrow2) {
            return;
        }
        if(randomImageCount!=0) {
            randomPrev.style.display = "block";
        }
        checkArrow2 = true;
        randomBanner.style.transition = 'transform 0.3s';
        randomBanner.style.transform = `translate(${148.4925 * --count2}px)`;
        setTimeout(() => {
            checkArrow2 = false;
        }, 30);
    }
};