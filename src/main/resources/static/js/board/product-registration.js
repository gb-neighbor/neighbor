const input = document.querySelector('.input-price');
let flag = [false, false, false, false] /* 모든 정보를 잘 입력했는지 */
input.addEventListener('keyup', function(e) {
    let value = e.target.value;
    value = Number(value.replaceAll(',', ''));
    if(isNaN(value)) {
        input.value = 0;
    }else {
        const formatValue = value.toLocaleString('ko-KR');
        input.value = formatValue;
    }
})
let form = document.querySelector("form[name=board]")
$("form").on("submit", function (e) {
    e.preventDefault();
    if(flag[0] && flag[1] && flag[2] && flag[3]){
        input.value = input.value.replaceAll(",", "")
        input.value = parseInt(input.value)
        form.submit();
    }

})


/* --------------------------------------------------------------------------------------------------- js썸네일*/
FileList.prototype.forEach = Array.prototype.forEach;
//사진 객체를 담을 array
let files = new Array();
//마지막에 보낼 array
globalThis.arrayFile2 = new Array();
globalThis.j = 0;
const dataTransfer = new DataTransfer();
$("input[id='cover-file']").on("change", function() {
    const $files2 = $("input[id=cover-file]")[0].files[0];
    flag[3] = true;
    console.log($files2)
//    파일 객체에 접근함
    let formData = new FormData();
    globalThis.arrayFile2.push($files2);
    // 파일 Array의 file들을 하나씩 담아줌
    console.log(globalThis.arrayFile2)
    formData.append("file", $files2)
    $.ajax({
        url: "/board-files/upload",
        type: "post",
        data: formData,
        contentType: false,
        processData: false,
        success: function (uuid) {
            globalThis.uuid = uuid;
            console.log(globalThis.uuid)
            $("span.cover-txt").hide();
            $("div.cover-img").hide();
            $(".img").html(`<img src="/board-files/display?fileName=boards/${toStringByFormatting(new Date())}/${uuid}_${$files2.name}" class="mainPhoto">`);
            $(".img img").css("width", "50%");
            $(".img img").css("height", "100%");

            const dataTransfer = new DataTransfer();
            dataTransfer.items.add($files2);
            $("input[id='cover-file']").files = dataTransfer.files;
            let text2 =
                    `
                    <input type="hidden" name="fileMainName" value="${$files2.name}">
                    <input type="hidden" name="fileMainUuid" value="${globalThis.uuid}">
                    <input type="hidden" name="fileMainPath" value="${toStringByFormatting(new Date())}">
                    <input type="hidden" name="fileMainSize" value="${$files2.size}">
                    <input type="hidden" name="fileMainStatus" value=true>
                    `
            $("form[name='board']").append(text2);
        }
    });
});


FileList.prototype.forEach = Array.prototype.forEach;
globalThis.arrayFile = new Array();
globalThis.i = 0;
let text= "";
$(".add-button").on("click", function (e) {
    if (!$('.img img').length) {
        e.preventDefault()
        console.log("들어옴")
        showWarnModal();
        $(".cover-label").blur()
        $(this).blur();
        return;
    } else{

    }
})

$("input[id='detail-file']").on("change", function(e) {
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
                $("#thumbnail-container1").append(`<img src="/board-files/display?fileName=boards/${toStringByFormatting(new Date())}/t_${uuids[i]}_${file.name}">`);
                $(".add-button").css("margin-top", "172px");
            });
            const dataTransfer = new DataTransfer();
            $("input[id='detail-file']")[0].files = dataTransfer.files;
            let text = "";
            console.log("uuid는 " + globalThis.uuids)

            $files.forEach(file => {
                text +=
                    `
                    <input type="hidden" name="files[${i}].boardFileOriginalName" value="${file.name}">
                    <input type="hidden" name="files[${i}].boardFileUuid" value="${globalThis.uuids[i]}">
                    <input type="hidden" name="files[${i}].boardFilePath" value="${toStringByFormatting(new Date())}">
                    <input type="hidden" name="files[${i}].boardFileSize" value="${file.size}">
                    `
                i++;
            });
            i=0;
            $("form[name='board']").append(text);
        }
    });
});

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
/* 제목 */
$(".title").on("blur", function () {
    if($(this).val() != ''){
        flag[0] = true;
    } else {
        flag[0] = false;
        showWarnModal();
    }
})

$(".input-price").on("blur", function () {
    if($(this).val() != ''){
        flag[1] = true;
    } else {
        flag[1] = false;
        showWarnModal();
    }
})

$("textarea[name='boardContent']").on("blur", function () {
    if($(this).val() != ''){
        flag[2] = true;
    } else {
        flag[2] = false;
        showWarnModal();
    }
})

/***************************************************/
const warnModalBack = $(".modal_background");
warnModalBack.on("click", function () {
    warnModalBack.hide();
    $(".review-write").hide()
    $(".review-write-form").hide()
    $(".review-my").hide()
})

warnModalBack.on("click", function () {
    const modal = $(".modal_background");
    modal.hide();
})

function showWarnModal() {
    const warnModalBack = $('.modal_background');
    const warnModal = $('.modal-warn');
    warnModalBack.css("display", "block");
    warnModal.css("display", "block");
}

