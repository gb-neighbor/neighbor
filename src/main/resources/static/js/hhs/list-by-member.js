/* list-by-member.js */
/* 판매 종료 된 제품이면 status 검사 후 label 띄워주기, 아래는 label 띄우는 코드 */

const $labelSpot=$(".label-spot");
const $boardThumb=$(".board-thumb");

function addLabel(){
    $labelSpot.append(`<label for="parent-block">판매종료</label>`);
    $boardThumb.css("opacity", 0.8).css("filter", "brightness(0.5)");
}

// addLabel();

  /****************************tothetop 버튼******************************* */
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


