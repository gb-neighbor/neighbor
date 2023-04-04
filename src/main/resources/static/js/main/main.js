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

$(document).ready(function(){
    $('.recent-food-picture').hover(function() {
        var $this = $(this);
        console.log(`1 : ` + $(this));
        $this.find('.recent-food-info-wrap').stop().fadeIn(300);
        $this.find('.recent-food-image').stop().css('filter', 'brightness(50%)');
    }, function() {
        var $this = $(this);
        console.log(`2 : ` + $(this));
        $this.find('.recent-food-info-wrap').stop().fadeOut(500);
        $this.find('.recent-food-image').stop().css('filter', 'brightness(100%)');
    }).each(function() {
        var $this = $(this);
        console.log(`3 : ` + $(this));
        var $infoWrap = $this.find('.recent-food-info-wrap');
        $infoWrap.data('timeout', null);
    });
  
    $('.recent-food-info-wrap').mouseenter(function() {
        var $this = $(this);
        console.log(`4 : ` + $(this));
        var $picture = $this.parent('.recent-food-picture');
        clearTimeout($picture.data('timeout'));
    }).mouseleave(function() {
        var $this = $(this);
        console.log(`5 : ` + $(this));
        var $picture = $this.parent('.recent-food-picture');
        $picture.data('timeout', setTimeout(function() {
        $this.stop().fadeOut(500);
        $picture.find('.recent-food-image').stop().css('filter', 'brightness(100%)');
        }, 500));
    });
});


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

// /* 내가 쓴 게시물 다 넣기 */
// let rescentLists = "";
// let mainFile = (mainRecentDTO.boardFileStatus
// for (let i = 0; i < mainRecentDTO.length; i++) {
//     rescentLists +=
//         `
//         <li >
//             <!--th:href="@{/board/detail(boardId=${regionAllRecentList.boardId})}"-->
//             <a  class="recent-food">
//                 <div class="recent-food-picture-wrapper">
//                     <div class="recent-food-picture">
//                         <img class="recent-food-image" src="/board-files/${}${}" />
//                         <div class="recent-food-info-wrap">
//                             <p class="recent-food-picture-title">${mainRecentDTO[i].boardTitle}</p>
//                             <a href="글쓴이 링크">
//                                 <div class="recent-food-user-picture" style="background-image: url('/css/main/images/004.jpg');"></div>
//                                 <p class="recent-food-user-nickname">${mainRecentDTO[i].memberNickname}</p>
//                             </a>
//                         </div>
//                     </div>
//                 </div>
//             </a>
//         </li>
//        `;
// }
// $("ul.list-outer").append(boards);




