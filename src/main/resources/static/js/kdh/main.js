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
    (div, i) => (div.style.backgroundImage = `url(../../static/css/kdh/images/00${i + 1}.jpg)`)
);

banner.appendChild(lastImageDiv);
lastImageDiv.style.backgroundImage = `url(../../static/css/kdh/images/001.jpg)`;

banner.insertBefore(firstImageDiv, document.querySelector('div.banner div'));
firstImageDiv.style.backgroundImage = `url(../../static/css/kdh/images/00${imageDiv.length}.jpg)`;

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
    if (count == 6) {
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
        count = 5;
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
    if (count == 6) {
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
var showInfo = $(".recent-food-info-wrap");
var recentImg = $(".recent-food-image");
const pictureWrappers = $('.recent-food-picture-wrapper');



pictureWrappers.on('mouseover', function() {
  const parent = $(this).parent();
  const infoWraps = parent.find('.recent-food-info-wrap');
  infoWraps.each(function() {
    $(this).css('display', $(this).parent()[0] === parent[0] ? 'none' : 'block');
  });
});

pictureWrappers.on('mouseout', function() {
  const parent = $(this).parent();
  const infoWraps = parent.find('.recent-food-info-wrap');
  infoWraps.each(function() {
    $(this).css('display', $(this).parent()[0] === parent[0] ? 'block' : 'none');
  });
});


pictureWrappers.hover(function() {
    // 마우스 오버 이벤트 발생 시 실행될 코드
    const targetImg = $(this).find('.recent-food-image');
    targetImg.css('filter', 'brightness(0.5)');
  }, function() {
    // 마우스 아웃 이벤트 발생 시 실행될 코드
    const targetImg = $(this).find('.recent-food-image');
    targetImg.css('filter', 'brightness(1)');
  });
  
  showInfo.hover(function() {
    // 마우스 오버 이벤트 발생 시 실행될 코드
    const parent = $(this).parent();
    const targetImg = parent.find('.recent-food-image');
    targetImg.css('filter', 'brightness(0.5)');
  });


