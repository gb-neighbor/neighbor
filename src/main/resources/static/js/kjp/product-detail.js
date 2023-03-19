// 모달창 열기
var modal = document.getElementById("modal");
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
cancelBtn.onclick = function() {
    var title = document.getElementById("title").value;
    modal.style.display = "none";
}

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
