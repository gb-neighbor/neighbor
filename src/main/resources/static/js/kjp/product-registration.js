/*
const input = document.getElementById('detail-file');
const thumbnailContainer = document.querySelector('.thumbnail-container');
const detailImg = document.querySelector('.detail-img');
const detailContainer = document.querySelector('.detail-container');
const detailSpan = document.querySelector('.detail-span');
const thumbnail = document.getElementById('thumbnail');

input.addEventListener('change', function() {
    const file = input.files[0];
    const reader = new FileReader();

    reader.addEventListener('load', function() {
        const image = new Image();
        image.src = reader.result;

        image.addEventListener('load', function() {
            const width = image.width;
            const height = image.height;
            const contentWidth = thumbnailContainer.offsetWidth;
            const contentHeight = thumbnailContainer.offsetHeight;

            let newWidth = width;
            let newHeight = height;

            if (width > contentWidth) {
                newWidth = contentWidth;
                newHeight = (contentWidth / width) * height;
            }

            if (newHeight > contentHeight) {
                newHeight = contentHeight;
                newWidth = (contentHeight / height) * width;
            }

            thumbnail.src = reader.result;
            detailContainer.style.border = '0';
            detailImg.style.display = 'none';
            detailSpan.style.display = 'none';
            thumbnail.style.display = 'block';
            thumbnail.style.width = newWidth + 'px';
            thumbnail.style.height = newHeight + 'px';
        });

        thumbnailContainer.style.display = 'block';
    });


    reader.readAsDataURL(file);
});




const deleteButton = document.getElementById('delete-button');
const thumbnailImg = document.getElementById('thumbnail-img');
const inputFile = document.getElementById('detail-file');

// delete button 클릭 이벤트 등록
$('#delete-button').on('click', function() {
    // 썸네일 이미지와 업로드된 파일 초기화
    $('#thumbnail').attr('src', '').hide();
    $('#detail-file').val('');
    $('.thumbnail-container').hide();
    detailImg.style.display = 'block';
    detailSpan.style.display = 'block';
    detailContainer.style.border = '1px solid #d9d9d9';
});


$(function() {
    $('#cover-file').change(function() {
        var file = $(this)[0].files[0];
        var reader = new FileReader();
        reader.onload = function(e) {
            $('.cover-photo').css('background-image', 'url(' + e.target.result + ')' , ('z-index','20'));
            $('.cover-txt').hide();
        };
        reader.readAsDataURL(file);
    });
});

let imgtotal = 0;
// '사진을 추가하세요!' 버튼 클릭 시 이벤트 핸들러 등록
$('.add-button').on('click', function() {
    var fileInput = $('<input type="file" name="image">');
    if($('#thumbnail-container1').children().length > 5){
        alert("사진은 6개까지 추가할 수 있습니다.")
        fileInput.value = '';
        thumbnail.style.display = 'none';
        imageCount--;
    }
    imgtotal++;

    fileInput.on('change', function() {
        // 파일 객체 가져오기
        var file = this.files[0];

        // FileReader 객체 생성
        var reader = new FileReader();

        // 파일 읽기 완료 시 이벤트 핸들러 등록
        reader.onload = function(event) {
            // 미리보기 이미지 생성 및 썸네일 요소에 추가
            var thumbnail = $('<img>').attr('src', event.target.result);
            $('#thumbnail-container1').append(thumbnail);
        };

        // 파일 읽기 시작
        reader.readAsDataURL(file);
    });

    // 파일 업로드 input 요소 표시
    fileInput.click();
});





const fileInput = document.getElementById("cover-file");
const coverImage = document.querySelector(".cover-img");

fileInput.addEventListener("change", function() {
    const file = this.files[0];
    const reader = new FileReader();

    reader.addEventListener("load", function() {
        const img = new Image();
        img.src = reader.result;

        img.addEventListener("load", function() {
            const canvas = document.createElement("canvas");
            const ctx = canvas.getContext("2d");

            canvas.width = coverImage.offsetWidth;
            canvas.height = coverImage.offsetHeight;

            ctx.drawImage(img, 0, 0, canvas.width, canvas.height);

            coverImage.style.backgroundImage = "url(" + canvas.toDataURL() + ")";
            coverImage.style.width = "500px";
            coverImage.style.height = "500px";
            coverImage.style.top = "0";
            coverImage.style.left = "325px";
        });
    });

    if (file) {
        reader.readAsDataURL(file);
    }
});

*/
/* --------------------------------------------------------------------------------------------------- js썸네일*/
/* 메인 사진 추가 썸네일*/
FileList.prototype.forEach = Array.prototype.forEach;
globalThis.arrayFile2 = new Array();
globalThis.j = 0;
const dataTransfer = new DataTransfer();
$("input[id='cover-file']").on("change", function() {
    const $files2 = $("input[id=cover-file]")[0].files;
    // console.log($files[0])
//    파일 객체에 접근함
    let formData = new FormData();
    Array.from($files2).forEach(file => globalThis.arrayFile2.push(file));
    // 파일 Array의 file들을 하나씩 담아줌
    console.log(globalThis.arrayFile2)
    $files2.forEach(file => {
        formData.append("file", file)
    });
    $.ajax({
        url: "/board-files/upload",
        type: "post",
        data: formData,
        contentType: false,
        processData: false,
        success: function (uuid) {
            globalThis.uuid = uuid;
            console.log(globalThis.uuid)

            $files2.forEach((file, i) => {
                $("span.cover-txt").hide();
                $("div.cover-img").hide();
                $(".img").append(`<img src="/board-files/display?fileName=${toStringByFormatting(new Date())}/t_${uuid}_${file.name}" class="mainPhoto">`);
                $(".img img").css("width", "50%");
                $(".img img").css("height", "100%");
            });

            $("input[id='cover-file']")[0].files = dataTransfer.files;
            let text2 = "";
            console.log("uuid는 " + globalThis.uuids)

            $files2.forEach(file => {
                text2 +=
                    `
                    <input type="hidden" name="fileMainName" value="${file.name}">
                    <input type="hidden" name=".fileMainUuid" value="${globalThis.uuid}">
                    <input type="hidden" name=".fileMainPath" value="${toStringByFormatting(new Date())}">
                    <input type="hidden" name=".fileMainSize" value="${file.size}">
                    `
                i++;
            });
            $("form[name='board']").append(text2);
        }
    });
});




$(".submit-button").on("click", function(){
    const $files = $("input[name=file]")[0].files;
    let files = new Array();

    console.log(globalThis.uuids + "uuid왜 안나옴")

    $files.forEach((file, i) => {
        let fileVO = new Object();
        fileVO.fileOriginalName = file.name;
        fileVO.fileUuid = globalThis.uuids[i];
        fileVO.filePath = toStringByFormatting(new Date());
        fileVO.fileSize = file.size;
        fileVO.boardId = 5;
        files.push(fileVO);
    });
})



FileList.prototype.forEach = Array.prototype.forEach;
globalThis.arrayFile = new Array();
globalThis.i = 0;
let text= "";
$("input[id='detail-file']").on("change", function() {
    const $files = $("input[id=detail-file]")[0].files;
    // console.log($files[0])
//    파일 객체에 접근함
    let formData = new FormData();
    Array.from($files).forEach(file => globalThis.arrayFile.push(file));
    // 파일 Array의 file들을 하나씩 담아줌
    console.log(globalThis.arrayFile)
    $files.forEach(file => {
        formData.append("file", file)
    });
    $.ajax({
        url: "/board-files/upload",
        type: "post",
        data: formData,
        contentType: false,
        processData: false,
        success: function (uuids) {
            globalThis.uuids = uuids;
            console.log(globalThis.uuids)

            $files.forEach((file, i) => {
                $("#thumbnail-container1").append(`<img src="/board-files/display?fileName=${toStringByFormatting(new Date())}/t_${uuids[i]}_${file.name}">`);
                $(".add-button").css("margin-top", "172px");
            });

            const dataTransfer = new DataTransfer();
            $("input[id='detail-file']")[0].files = dataTransfer.files;
            console.log(dataTransfer.files);
            let text = "";
            console.log("uuid는 " + globalThis.uuids)

            $files.forEach(file => {
                text +=
                    `
                    <input type="hidden" name="files[${i}].fileDetailName" value="${file.name}">
                    <input type="hidden" name="files[${i}].fileDetailUuid" value="${globalThis.uuids[i]}">
                    <input type="hidden" name="files[${i}].fileDetailPath" value="${toStringByFormatting(new Date())}">
                    <input type="hidden" name="files[${i}].fileDetailSize" value="${file.size}">
                    `
                i++;
            });
            $("form[name='board']").append(text);
        }
    });
});


$(".submit-button").on("submit", function () {
    const $files = $("input[id='detail-file']")[0].files;
    let files = new Array();

    $files.forEach((file, i) => {
        let boardFileVO = new Object();
        boardFileVO.fileOriginalName = file.name;
        boardFileVO.fileUuid = globalThis.uuids[i];
        boardFileVO.filePath = toStringByFormatting(new Date());
        boardFileVO.fileSize = file.size;
        boardFileVO.fileType = file.type.startsWith("image");
        files.push(boardFileVO);
    });
})




/*****************************************************/
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
/*****************************************************/
