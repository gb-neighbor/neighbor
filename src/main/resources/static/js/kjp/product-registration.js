const input = document.querySelector('.input-price');
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
    input.value = input.value.replace(",", "")
    form.submit();

})


/* --------------------------------------------------------------------------------------------------- js썸네일*/
/* 메인 사진 추가 썸네일*/
FileList.prototype.forEach = Array.prototype.forEach;
//사진 객체를 담을 array
let files = new Array();
//마지막에 보낼 array
globalThis.arrayFile2 = new Array();
globalThis.j = 0;
const dataTransfer = new DataTransfer();
$("input[id='cover-file']").on("change", function() {
    const $files2 = $("input[id=cover-file]")[0].files[0];
    console.log($files2)
//    파일 객체에 접근함
    let formData = new FormData();
    globalThis.arrayFile2.push($files2);
    // 파일 Array의 file들을 하나씩 담아줌
    console.log(globalThis.arrayFile2)
    formData.append("file", $files2)
    $.ajax({
        url: "/board-files/uploadM",
        type: "post",
        data: formData,
        contentType: false,
        processData: false,
        success: function (uuid) {
            globalThis.uuid = uuid;
            console.log(globalThis.uuid)
                $("span.cover-txt").hide();
                $("div.cover-img").hide();
                $(".img").append(`<img src="/board-files/display?fileName=${toStringByFormatting(new Date())}/m_${uuid}_${$files2.name}" class="mainPhoto">`);
                $(".img img").css("width", "50%");
                $(".img img").css("height", "100%");


            $("input[id='cover-file']")[0].files = dataTransfer.files;
            let text2 = "";
                text2 =
                    `
                    <input type="hidden" name="fileMainName" value="${$files2.name}">
                    <input type="hidden" name="fileMainUuid" value="${globalThis.uuid}">
                    <input type="hidden" name="fileMainPath" value="${toStringByFormatting(new Date())}">
                    <input type="hidden" name="fileMainSize" value="${$files2.size}">
                    `
            $("form[name='board']").append(text2);

            let boardFileVO1 = new Object();
            boardFileVO1.fileOriginalName = $files2.name;
            boardFileVO1.filePath = toStringByFormatting(new Date());
            boardFileVO1.fileSize = $files2.size;
            boardFileVO1.fileUuid = globalThis.uuid;

            files.push(boardFileVO1);

            console.log(boardFileVO1.fileSize)
            console.log(boardFileVO1.filePath)
            console.log(boardFileVO1.fileUuid)
            console.log(boardFileVO1.fileOriginalName)
        }
    });
});


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
        url: "/board-files/uploadD",
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

            $files.forEach((file, i) => {
                let boardFileVO2 = new Object();
                boardFileVO2.fileOriginalName = file.name;
                boardFileVO2.fileUuid = globalThis.uuids[i];
                boardFileVO2.filePath = toStringByFormatting(new Date());
                boardFileVO2.fileSize = file.size;
                files.push(boardFileVO2);
                console.log(boardFileVO2.fileUuid)
            });
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


