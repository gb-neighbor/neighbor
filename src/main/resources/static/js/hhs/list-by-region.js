/* list-by-region.html */
const $radios= $(".input-selection");

// index 번호가 지역구 번호
const $region_list = ["전체", "강남구", "강동구", "강북구", "강서구", "관악구", "광진구", "구로구", 
                    "금천구", "노원구", "도봉구", "동대문구", "동작구", "마포구", "서대문구", "서초구", 
                    "성동구", "성북구", "송파구", "양천구", "영등포구", "용산구", "은평구", "종로구", 
                    "중구", "중랑구" ];

// 지도에서 선택한 지역구 표시할 곳
const $selected_region = $(".selected-region");


// 초기화 버튼 누르면 선택 초기화 (전체 선택 default)
function resetMap(){
    $radios.each((i, e) => {
        console.log(e);
        e.checked = false;
        $selected_region.text($region_list[0]);
    });
}

// 지도에서 선택 한 지역구 리스트 제목으로 반환
function locationClick(num){
    $selected_region.text($region_list[num]);
}

// 드롭다운에서 선택한 지역구 반환
$(".select-region-drop").change(function() {
    var selectedValue = $(this).val();
    $("input[type='radio'][name='regionMap'][value='" + selectedValue + "']").prop("checked", true);
});

// select 요소 변경 시 input[type=radio] 요소 선택 상태 변경 함수
function changeInputState() {
    var selectVal = $('.select-region-drop').val(); // select 요소의 현재 값 가져오기
    $('input[type=radio]').each(function() {
      if ($(this).val() === selectVal) { // 현재 select 요소 값과 일치하는 input[type=radio] 요소를 찾으면
        $(this).prop('checked', true); // 해당 input[type=radio] 요소를 선택한 상태로 변경
        locationClick($(this).val());
      }
    });
  }
  
  // input[type=radio] 요소 선택 상태 변경 시 select 요소 값 변경 함수
  function changeSelectState() {
    var radioVal = $('input[type=radio]:checked').val(); // 선택된 input[type=radio] 요소의 값 가져오기
    $('.select-region-drop').val(radioVal); // 해당 값으로 select 요소를 변경
  }
  
  // select 요소 변경 이벤트 등록
  $('.select-region-drop').on('change', changeInputState);
  
  // input[type=radio] 요소 선택 상태 변경 이벤트 등록
  $('input[type=radio]').on('change', changeSelectState);

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


















































