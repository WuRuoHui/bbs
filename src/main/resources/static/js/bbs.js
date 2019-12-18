/*
* 登录验证
* */
function postComment() {
    var commentContent = $(".comment-content").val();
    var commentParentId = $(".comment-parentId").val();
    console.log(commentContent);
    console.log(commentParentId);
    comment2target(commentParentId, 1, commentContent);
}

function comment2target(targetId, type, content) {
    var commentContent = $(".comment-content").val();
    var commentParentId = $(".comment-parentId").val();
    console.log(commentContent);
    console.log(commentParentId);

    $.ajax({
        type: "POST",
        contentType: "application/json",
        url: "/comment",
        data: JSON.stringify({
            content: content,
            parentId: targetId,
            type: type
        }),
        success: function (data) {
            if (data.code == 200) {
                $("#commentSection").hide();
                window.location.reload();
            } else {
                if (data.code == 2003) {
                    var isLogin = confirm("您未登录，要登录吗？");
                    if (isLogin) {
                        window.open("https://github.com/login/oauth/authorize?client_id=0edababf293cfd3a6950&redirect_uri=http://localhost:8887/callback&scope=user&state=1")
                        window.localStorage.setItem("closable", true);
                    }
                } else {
                    alert(data.message);
                }
            }
            console.log(data);
        },
        dataType: "json"
    });
}

function comment(e) {
    var contentId = $(e).attr("data-id");
    var content = $("#input-" + contentId).val();
    comment2target(contentId, 2, content);
}

function changeColor(e) {
    var contentId = $(e).attr("data-id");
    if (!$("#comment-" + contentId).hasClass("show")) {
        console.log($("#comment-" + contentId).attr("class"))
        $(e).addClass("changeColor");
    } else {
        $(e).removeClass("changeColor");
    }
}