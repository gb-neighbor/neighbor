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

/* 모달창 */
/* 탈퇴버튼 클릭했을 때 */
/*삭제 버튼 모달*/
// 모달창 열기 함수
function openModalDelete() {
    document.getElementById("user-my-modal").style.display = "block";
}

// 모달창 닫기 함수
function closeModal() {
    document.getElementById("user-my-modal").style.display = "none";
}

// 삭제 함수
function deleteItem() {
    // 여기에 삭제 작업 코드를 작성
    // 삭제 작업 후 모달창을 닫는 코드
    closeModal();
}

// 탈퇴하기 버튼에 대한 이벤트 처리
document.querySelector('.leave_btn').addEventListener('click', function(e) {
    e.preventDefault(); // 폼 전송을 막음
    openModal();
});

// 모달창 내의 버튼에 대한 이벤트 처리
const modalContent = document.querySelector('.user-modal-content');
modalContent.querySelector('.user-delete-check').addEventListener('click', deleteItem);
modalContent.querySelector('.user-delete-cancel').addEventListener('click', closeModal);