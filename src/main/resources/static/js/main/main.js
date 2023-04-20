/* banner.html */
HTMLCollection.prototype.forEach = Array.prototype.forEach;
const banner = document.querySelector('div.banner');
const imageDiv = document.querySelectorAll('div.banner div');
const lastImageDiv = document.createElement('div');
const firstImageDiv = document.createElement('div');
const next = document.querySelector('div.next');
const prev = document.querySelector('div.prev');
const buttons = document.querySelectorAll('.buttons button');

let checkArrow = false;
let count = 1;
let auto = setInterval(autoSlide, 2000);
let temp = buttons[0];
const pageNow = document.querySelector('#page-now');

HTMLCollection.prototype.forEach = Array.prototype.forEach;
buttons.forEach((button) => {
    button.addEventListener('click', () => {
        clearInterval(auto);
        count = parseInt(button.innerHTML);
        changeButtonStyle();
        banner.style.transition = 'transform 0.3s';
        banner.style.transform = `translate(${-1200 * count}px)`;
        auto = setInterval(autoSlide, 2000);
    });
});

imageDiv.forEach(
    (div, i) => (div.style.backgroundImage = `url(/css/main/images/00${i + 1}.jpg)`)
);

banner.appendChild(lastImageDiv);
lastImageDiv.style.backgroundImage = `url(/css/main/images/001.jpg)`;

banner.insertBefore(firstImageDiv, document.querySelector('div.banner div'));
firstImageDiv.style.backgroundImage = `url(/css/main/images/00${imageDiv.length}.jpg)`;

banner.style.transform = `translate(-1200px)`;

function changeButtonStyle() {
    if (temp) {
        temp.style.background = 'white';
        temp.style.color = 'black';
    }
    buttons[count - 1].style.background = 'black';
    buttons[count - 1].style.color = 'white';
    temp = buttons[count - 1];
    pageNow.innerHTML = count;
}

function autoSlide() {
    banner.style.transition = 'transform 0.3s';
    banner.style.transform = `translate(${-1200 * ++count}px)`;
    console.log(count);
    if (count == 5) {
        count = 1;
        setTimeout(function () {
            banner.style.transition = 'transform 0s';
            banner.style.transform = 'translate(-1200px)';
        }, 300);
    }
    changeButtonStyle();
}

prev.addEventListener('click', function () {
    if (checkArrow) {
        return;
    }
    checkArrow = true;
    clearInterval(auto);
    banner.style.transition = 'transform 0.3s';
    banner.style.transform = `translate(${-1200 * --count}px)`;
    if (count == 0) {
        count = 4;
        setTimeout(function () {
            banner.style.transition = 'transform 0s';
            banner.style.transform = `translate(${-1200 * imageDiv.length}px)`;
        }, 300);
    }
    changeButtonStyle();
    auto = setInterval(autoSlide, 2000);
    setTimeout(() => {
        checkArrow = false;
    }, 300);
});



next.addEventListener('click', function () {
    if (checkArrow) {
        return;
    }
    checkArrow = true;
    clearInterval(auto);
    banner.style.transition = 'transform 0.3s';
    banner.style.transform = `translate(${-1200 * ++count}px)`;
    if (count == 5) {
        count = 1;
        setTimeout(function () {
            banner.style.transition = 'transform 0s';
            banner.style.transform = 'translate(-1200px)';
        }, 300);
    }
    changeButtonStyle();
    auto = setInterval(autoSlide, 2000);
    setTimeout(() => {
        checkArrow = false;
    }, 300);
});

/* 최근 음식 게시물 */
/* const recentFoodInfo = document.querySelector('recent-food-info-wrap');

recentFoodInfo.setAttribute('class','hover'); */



/* 랜덤추천 메뉴 */
const imageRandomDiv = document.querySelectorAll('ul.random-menu-banner-list div');
const randomNext = document.querySelector('.random-menu-banner-right-arrow');
const randomPrev = document.querySelector('.random-menu-banner-left-arrow');
const randomBanner = document.querySelector('ul.random-menu-banner-list');
globalThis.displayNext = 0;
let count2 = 0;
var randomImageCount = 0;
let checkArrow2 = false;
randomPrev.style.display = "none";

randomPrev.addEventListener('click', function () {
    if(randomImageCount != 0) {
        if (checkArrow2) {
            return;
        }
        checkArrow2 = true;
        randomBanner.style.transition = 'transform 0.3s';
        randomBanner.style.transform = `translate(${305 * ++count2}px)`;
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
});

randomNext.addEventListener('click', function () {
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
        randomBanner.style.transform = `translate(${305 * --count2}px)`;
        setTimeout(() => {
            checkArrow2 = false;
        }, 30);
    }
});

/* 최근 게시물 */
var timeOut = null;

// $(document).ready(function(){
//     $('.recent-food-picture').hover(function() {
//         var $this = $(this);
//         console.log(`1 : ` + $(this));
//         $this.find('.recent-food-info-wrap').stop().fadeIn(300);
//         $this.find('.recent-food-image').stop().css('filter', 'brightness(50%)');
//     }, function() {
//         var $this = $(this);
//         console.log(`2 : ` + $(this));
//         $this.find('.recent-food-info-wrap').stop().fadeOut(500);
//         $this.find('.recent-food-image').stop().css('filter', 'brightness(100%)');
//     }).each(function() {
//         var $this = $(this);
//         console.log(`3 : ` + $(this));
//         var $infoWrap = $this.find('.recent-food-info-wrap');
//         $infoWrap.data('timeout', null);
//     });
//
//     $('.recent-food-info-wrap').mouseenter(function() {
//         var $this = $(this);
//         console.log(`4 : ` + $(this));
//         var $picture = $this.parent('.recent-food-picture');
//         clearTimeout($picture.data('timeout'));
//     }).mouseleave(function() {
//         var $this = $(this);
//         console.log(`5 : ` + $(this));
//         var $picture = $this.parent('.recent-food-picture');
//         $picture.data('timeout', setTimeout(function() {
//         $this.stop().fadeOut(500);
//         $picture.find('.recent-food-image').stop().css('filter', 'brightness(100%)');
//         }, 500));
//     });
// });


// function changeImage(e) {
//     $('.recent-food-picture').hover(function() {
//         var $this = $(e);
//         console.log(`1 : ` + $(this));
//         $this.find('.recent-food-info-wrap').stop().fadeIn(300);
//         $this.find('.recent-food-image').stop().css('filter', 'brightness(50%)');
//     }, function() {
//         var $this = $(this);
//         console.log(`2 : ` + $(this));
//         $this.find('.recent-food-info-wrap').stop().fadeOut(500);
//         $this.find('.recent-food-image').stop().css('filter', 'brightness(100%)');
//     }).each(function() {
//         var $this = $(this);
//         console.log(`3 : ` + $(this));
//         var $infoWrap = $this.find('.recent-food-info-wrap');
//         $infoWrap.data('timeout', null);
//     });
//
//     $('.recent-food-info-wrap').mouseenter(function() {
//         var $this = $(this);
//         console.log(`4 : ` + $(this));
//         var $picture = $this.parent('.recent-food-picture');
//         clearTimeout($picture.data('timeout'));
//     }).mouseleave(function() {
//         var $this = $(this);
//         console.log(`5 : ` + $(this));
//         var $picture = $this.parent('.recent-food-picture');
//         $picture.data('timeout', setTimeout(function() {
//             $this.stop().fadeOut(500);
//             $picture.find('.recent-food-image').stop().css('filter', 'brightness(100%)');
//         }, 500));
//     });
// };

/* 밝아지는거 but recent-food-info-wrap이 사라질 때 같이 밝기 조절이 된다. */
/* $(document).ready(function() {
    $('.recent-food-picture').hover(function() {
      var $this = $(this);
      $this.find('.recent-food-info-wrap').stop().fadeIn(300);
      $this.find('.recent-food-image').stop().animate({
        opacity: 0.5
      }, 300);
    }, function() {
      var $this = $(this);
      $this.find('.recent-food-info-wrap').stop().fadeOut(500);
      $this.find('.recent-food-image').stop().animate({
        opacity: 0.8
      }, 500);
    }).each(function() {
      var $this = $(this);
      $this.find('.recent-food-info-wrap').hover(function() {
        clearTimeout(timeOut);
      }, function() {
        var $this = $(this);
        timeOut = setTimeout(function() {
          $this.stop().fadeOut(500);
          $this.parent('.recent-food-picture').find('.recent-food-image').stop().animate({
            opacity: 0.8
          }, 500);
        }, 500);
      });
    });
}); */

function addEventListeners() {
    $('.recent-food-content ul').on('mouseenter', '.recent-food-picture', function() {
        var $this = $(this);
        $this.find('.recent-food-info-wrap').stop().fadeIn(300);
        $this.find('.recent-food-image').stop().css('filter', 'brightness(50%)');
    }).on('mouseleave', '.recent-food-picture', function() {
        var $this = $(this);
        $this.find('.recent-food-info-wrap').stop().fadeOut(500);
        $this.find('.recent-food-image').stop().css('filter', 'brightness(100%)');
    }).find('.recent-food-picture').each(function() {
        var $this = $(this);
        var $infoWrap = $this.find('.recent-food-info-wrap');
        $infoWrap.data('timeout', null);
    });

    $('.recent-food-content ul').on('mouseenter', '.recent-food-info-wrap', function() {
        var $this = $(this);
        var $picture = $this.parent('.recent-food-picture');
        clearTimeout($picture.data('timeout'));
    }).on('mouseleave', '.recent-food-info-wrap', function() {
        var $this = $(this);
        var $picture = $this.parent('.recent-food-picture');
        $picture.data('timeout', setTimeout(function() {
            $this.stop().fadeOut(500);
            $picture.find('.recent-food-image').stop().css('filter', 'brightness(100%)');
        }, 500));
    });

}

addEventListeners();



/* 맨 위로 가는 버튼 */
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

// 랜덤 추천 메뉴로 가기
const goBottom = document.getElementById("goBottom");

goBottom.addEventListener("click", function() {
    const currentPosition = window.pageYOffset;  // 현재 스크롤 위치
    const targetPosition = document.body.scrollHeight;  // 목표 스크롤 위치

    const distance = targetPosition - currentPosition;  // 스크롤 거리
    const duration = 1000;  // 애니메이션 시간 (1초)

    let startTime = null;
    let animationFrameId = null; // 추가된 부분

    function animation(currentTime) {
        if (startTime === null) startTime = currentTime;
        const timeElapsed = currentTime - startTime;
        const scrollDistance = easeInOutQuad(timeElapsed, currentPosition, distance, duration);
        window.scrollTo(0, scrollDistance);

        if (timeElapsed < duration) {
            animationFrameId = requestAnimationFrame(animation);
        } else {
            window.scrollTo(0, document.body.scrollHeight);
        }
    }

    function easeInOutQuad(t, b, c, d) {
        t /= d / 2;
        if (t < 1) return c / 2 * t * t + b;
        t--;
        return -c / 2 * (t * (t - 2) - 1) + b;
    }

    // 추가된 부분
    window.addEventListener("wheel", function(event) {
        if (animationFrameId !== null) {
            cancelAnimationFrame(animationFrameId);
            animationFrameId = null;
        }
    }, { passive: true });

    requestAnimationFrame(animation);
});




//
const boxes = document.querySelectorAll('.area-change');

boxes.forEach(box => {
    box.addEventListener('click', () => {
        const className = box.getAttribute('class');
        const clickedElement = event.target;
        const elementContent = clickedElement.textContent;

        console.log(`클릭한 요소: ${clickedElement.tagName}, 내용: ${elementContent}`);

        handleClick(elementContent); // 클릭된 요소의 클래스 이름을 인자로 넘겨 함수 호출
    });
});


const areaLinks = document.querySelectorAll(".area-change");

areaLinks.forEach(function(areaLink) {
    areaLink.addEventListener("click", function() {
        // 기존에 "active" 클래스가 적용된 요소를 찾아서 클래스를 제거합니다.
        const activeLink = document.querySelector(".active");
        activeLink.classList.remove("active");

        // 클릭된 요소에 "active" 클래스를 추가합니다.
        this.parentElement.classList.add("active");
    });
});


//메인 지역별 최근 게시물
function handleClick(elementContent){
    let selectNum = 0;
    let regionArr = ["전체","강남구", "강동구", "강북구", "강서구", "관악구", "광진구", "구로구",
        "금천구", "노원구", "도봉구", "동대문구", "동작구", "마포구", "서대문구", "서초구",
        "성동구", "성북구", "송파구", "양천구", "영등포구", "용산구", "은평구", "종로구",
        "중구", "중랑구"];

    for (let i = 0; i <= 25; i++){
        if(elementContent === regionArr[i]){
            selectNum = i;
        }
    }
    console.log(selectNum);

    $.ajax({
    url:"/main/region-recent" ,
    data: {"memberRegion": selectNum},
    success: function(regionSelectRecentLists){
        appendList(regionSelectRecentLists);

    }
    });
};

const $ul = $(".recent-food-list");

function appendList(regionSelectRecentLists) {
    let boardText3 = '';
    if(regionSelectRecentLists[0] == null){
        boardText3 += `<h3 style="display: flex; align-items: center; justify-content: center; padding-top: 110px; padding-bottom: 110px; font-size: 30px; font-weight: 500; ">게시물이 존재하지 않습니다.</h3>`
    }else {
        regionSelectRecentLists.forEach(regionSelectRecentList => {
            boardText3 += `
                <li> 
                    <a href="/board/detail/${regionSelectRecentList.boardId}" class="recent-food">
                        <div class="recent-food-picture-wrapper">
                            <div class="recent-food-picture">
                                <img class="recent-food-image" src="/board-files/display?fileName=/boards/${regionSelectRecentList.boardFilePath}/${regionSelectRecentList.boardFileUuid}_${regionSelectRecentList.boardFileOriginalName}" />
                                <div class="recent-food-info-wrap">
                                    <p class="recent-food-picture-title" >${regionSelectRecentList.boardTitle}</p>
                                    <a href="board/list/member/${regionSelectRecentList.memberId}"/>
                                        <div class="recent-food-user-picture">
                                            <img class="recent-food-profile-image" src="/members/display?fileName=${regionSelectRecentList.memberProfilePath}/${regionSelectRecentList.memberProfileUuid}_${regionSelectRecentList.memberProfileOriginalName}" style=" width: 100%; height: 100%;"/>
                                        </div>
                                        <p class="recent-food-user-nickname" >${regionSelectRecentList.memberNickname}</p>
                                    </a>
                                </div>
                            </div>
                        </div>
                    </a>
                </li>
            `;
            let profileUrl = '/members/display?fileName=' + regionSelectRecentList.memberProfilePath + '/t_' + regionSelectRecentList.memberProfileUuid + '_' + regionSelectRecentList.memberProfileOriginalName;
            $('.recent-food-content ul').find('.recent-food-user-picture').css('background-image', 'url(' + profileUrl + ')');
        });
    }

    $ul.html(boardText3);

}



