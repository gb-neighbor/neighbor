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
FileList.prototype.forEach = Array.prototype.forEach;
globalThis.arrayFile = new Array();
globalThis.i = 0;

$("input[id=detail-file]").on("change", function(){
    const $files = $("input[id=detail-file]")[0].files;
    // console.log($files[0])
//    파일 객체에 접근함
    let formData = new FormData();
    Array.from($files).forEach(file => globalThis.arrayFile.push(file));
    // 파일 Array의 file들을 하나씩 담아줌
    $files.forEach(file => {
        formData.append("file", file)
    });

    $.ajax({
        url: "/boards/upload",
        type: "post",
        data: formData,
        contentType: false,
        processData: false,
        success: function (uuids) {
            globalThis.uuids = uuids;
            $files.forEach((file, i) => {
                    $("#thumbnail").append(`<span>><img src="/files/display?fileName=${toStringByFormatting(new Date())}/t_${uuids[i]}_${file.name}"></a></span>`);
            });
        }
    });


});
/*FileList.prototype.forEach = Array.prototype.forEach;
globalThis.arrayFile = new Array();
globalThis.i = 0;

$("input[id='thumbnail']").on("change", function(){
    const $files = $("input[name=file]")[0].files;
    let formData = new FormData();

    Array.from($files).forEach(file => globalThis.arrayFile.push(file));

    console.log(globalThis.arrayFile);

    $files.forEach(file => {
        formData.append("file", file)
    });
    $.ajax({
        url: "/files/upload",
        type: "post",
        data: formData,
        contentType: false,
        processData: false,
        success: function(uuids) {
            globalThis.uuids = uuids;
            $files.forEach((file, i) => {
                if(file.type.startsWith("image")){
                    $("#thumbnail").append(`<li><a href="/files/download?fileName=${toStringByFormatting(new Date())}/${uuids[i]}_${file.name}"><img src="/files/display?fileName=${toStringByFormatting(new Date())}/t_${uuids[i]}_${file.name}"></a></li>`);
                }else{
                    $("#thumbnail").append(`<li><a href="/files/download?fileName=${toStringByFormatting(new Date())}/${uuids[i]}_${file.name}"><img src="/attach.png" width="100"></a></li>`);
                }
            });
            /!********************************************************************!/
            /!*게시글 추가 부분*!/
            const dataTransfer = new DataTransfer();
            globalThis.arrayFile.forEach(file => dataTransfer.items.add(file));
            $("input[name='file']")[0].files = dataTransfer.files;
            console.log(dataTransfer.files);
            let text = "";
            $files.forEach(file => {
                text +=
                    `
                    <input type="hidden" name="files[${i}].fileName" value="${file.name}">
                    <input type="hidden" name="files[${i}].fileUuid" value="${globalThis.uuids[i]}">
                    <input type="hidden" name="files[${i}].filePath" value="${toStringByFormatting(new Date())}">
                    <input type="hidden" name="files[${i}].fileSize" value="${file.size}">
                    <input type="hidden" name="files[${i}].fileType" value="${file.type.startsWith("image")}">
                    `
                i++;
            });
            $("form[name='write-form']").append(text);
        }
    });
});*/



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
