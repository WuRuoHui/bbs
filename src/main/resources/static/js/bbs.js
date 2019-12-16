function postComment() {
    var commentContent = $(".comment-content").val();
    var commentParentId = $(".comment-parentId").val();
    console.log(commentContent);
    console.log(commentParentId);

    $.ajax({
        type: "POST",
        contentType: "application/json",
        url: "/comment",
        data: JSON.stringify({
            content: commentContent,
            parentId: commentParentId,
            type: 1
        }),
        success: function (data) {
            if (data.code == 200) {
                $("#commentSection").hide();
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