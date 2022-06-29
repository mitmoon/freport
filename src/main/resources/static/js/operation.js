$(document).ready(function() {
    $(".favili").click(function() {
        // 고정확장자 목록 전체 가져와서 PUT 처리
        let faviext_useyn = new Array();
        faviext_useyn['bat'] = "N";
        faviext_useyn['cmd'] = "N";
        faviext_useyn['com'] = "N";
        faviext_useyn['cpl'] = "N";
        faviext_useyn['exe'] = "N";
        faviext_useyn['scr'] = "N";
        faviext_useyn['js'] = "N";

        $(".favicheck:checked").each(function() {
            let check_id = $(this).val();
            faviext_useyn[check_id] = "Y";
        })

        var jsonData = [
            {
                "faviExt": "bat",
                "faviExtUseYn": faviext_useyn["bat"],
                "chgId": "batchModify"
            },
            {
                "faviExt": "cmd",
                "faviExtUseYn": faviext_useyn["cmd"],
                "chgId": "batchModify"
            },
            {
                "faviExt": "com",
                "faviExtUseYn": faviext_useyn["com"],
                "chgId": "batchModify"
            },
            {
                "faviExt": "cpl",
                "faviExtUseYn": faviext_useyn["cpl"],
                "chgId": "batchModify"
            },
            {
                "faviExt": "exe",
                "faviExtUseYn": faviext_useyn["exe"],
                "chgId": "batchModify"
            },
            {
                "faviExt": "scr",
                "faviExtUseYn": faviext_useyn["scr"],
                "chgId": "batchModify"
            },
            {
                "faviExt": "js",
                "faviExtUseYn": faviext_useyn["js"],
                "chgId": "batchModify"
            }
        ];

        $.ajax({
            type: 'PUT',
            dataType: "TEXT",
            contentType: "application/json; charset=UTF-8",
            data: JSON.stringify(jsonData),
            url: '/api/favi-ext/list'
        })
        .done(function (data) {
            alert("고정확장자 선택정보가 수정됐습니다.");
        })
        .fail(function (jqXHR, textStatus, errorThrown) {
            alert("다음 오류가 발생했습니다 - " + errorThrown);
        });
    });

    $("#custext-add").click(function() {
        // custext의 text 가져와서 add
        if ($("#custext").val() == "") {
            alert("커스텀 확장자를 입력하고 추가를 눌러주세요");
            return;
        }

        var jsonData = {
            "custExt": $("#custext").val(),
            "chgId": "adder"
        }

        $.ajax({
            type: 'POST',
            dataType: "TEXT",
            contentType: "application/json; charset=UTF-8",
            data: JSON.stringify(jsonData),
            url: '/api/cust-ext'
        })
        .done(function (data) {
            $("#custext").val("");
            location.reload();
        });
    })

    $('.custlibtn').click(function () {
        var jsonData = {
            "custExt": this.id,
            "chgId": "deleter"
        }
        $.ajax({
            type: 'DELETE',
            dataType: "TEXT",
            contentType: "application/json; charset=UTF-8",
            data: JSON.stringify(jsonData),
            url: '/api/cust-ext'
        })
        .done(function (data) {
            alert("클릭하신 커스텀 확장자가 삭제됩니다.");
            location.reload();
        });
    });
});