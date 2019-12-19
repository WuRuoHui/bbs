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
    var $subCommentContainer = $("#comment-" + contentId);
    if (!$subCommentContainer.hasClass("show")) {
        console.log($("#comment-" + contentId).attr("class"))
        $(e).addClass("changeColor");
        if ($subCommentContainer.children().length != 2) {
        } else {
            $.getJSON("/comment/" + contentId, function (data) {
                $.each(data.data, function (index, comment) {
                    var $littleImgElement = $("<img/>", {
                        "class": "mr-3 rounded littleImg",
                        "src": comment.user.avatarUrl
                    });
                    var $mediaBodyElement = $("<div/>", {
                        "class": "media-body",
                        "style": "padding-top: 5px"
                    }).append($("<h6/>", {
                        "class": "mt-0",
                        html: comment.user.name
                    }));
                    $mediaBodyElement.append($("<span/>", {
                        html: comment.content
                    })).append($("<span/>", {
                        "class": "float-right",
                        html:moment(comment.gmtCreate).format('YYYY-MM-DD')
                    }))
                    var $mediaElement = $("<div/>", {
                        "class": "media",
                    });
                    $mediaElement.append($littleImgElement);
                    $mediaElement.append($mediaBodyElement);
                    var $commentElement = $("<div/>", {
                        "class": "card card-body",
                        "style": "margin-bottom: 5px;margin-top: 5px",
                    })
                    $commentElement.append($mediaElement)
                    $subCommentContainer.prepend($commentElement);
                });
            });

        }
    } else {
        $(e).removeClass("changeColor");
    }
}