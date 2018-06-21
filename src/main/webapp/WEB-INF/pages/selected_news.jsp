<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>TrulyNews</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="static/css/page-style.css" rel="stylesheet" type="text/css" media="screen"/>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body>

<div class="container-fluid">
    <div class="row content">
        <div class="col-sm-3 sidenav">
            <a title="TrulyNews" href='<c:url value="/main"/>'>
                <img height="130px" src="static/pics/truly.png">
            </a>
            <hr>
            <ul class="nav nav-pills nav-stacked">
                <li class="active"><a href="#section1">Home</a></li>
                <li><a href="#section2">Friends</a></li>
                <li><a href="#section3">Family</a></li>
                <li><a href="#section3">Photos</a></li>
            </ul>
            <br>
        </div>

        <div class="col-sm-9">
            <form:form action="/openEditMode" modelAttribute="model">
                <h4>
                    <small>RECENT POSTS</small>
                </h4>
                <hr>
                <h2>${model.title}</h2>
                <h5><span class="glyphicon glyphicon-time"></span> ${model.date}
                </h5>
                <h5><span class="label label-danger">Incidents</span> <span
                        class="label label-primary">Cataclysms</span>
                </h5><br>
                <h5>${model.content}</h5>
                <div style="float:left;padding-right:8px;">
                    <button type="submit" class="btn btn-primary" name="id" value=${model.id}>Edit mode</button>
                </div>
            </form:form>
            <form:form action="/deleteNews" method="post" modelAttribute="model">
                <button type="submit" class="btn btn-danger" name="id" value=${model.id}>Delete</button>
            </form:form>
            <hr>
            <form:form action="/addComment" method="post" modelAttribute="model">
                <div class="form-group">
                    <h4>Enter your login-email:</h4>
                    <form:textarea path="commentAuthor" rows="1" cssStyle="width: 33%; border-radius: 3px"/>
                    <h4>Leave a Comment:</h4>
                    <form:textarea path="commentContent" rows="3" cssStyle="width: 100%; border-radius: 3px"/>
                </div>
                <button type="submit" class="btn btn-success" name="id" value=${model.id}>Submit</button>
            </form:form>
            <hr>
            <c:forEach items="${model.DTOCommentList}" var="comment">
                <div style="float:right;padding-right:16px;">
                    <div class="checkbox-primary">
                        <label><input name="deleteCommentCheckbox" type="checkbox" form="form1" value=${comment.id}> Deletion label</label>
                    </div>
                </div>
                <h5>${comment.commentAuthor}</h5>
                <h6><span class="glyphicon glyphicon-time"></span>${comment.commentDate}</h6>
                <h4>${comment.commentContent}</h4>
                <br>
            </c:forEach>
        </div>
        <hr>
        <div style="float:right;padding-right:16px; padding-bottom: 16px">
            <form name="news" id="form1" action="/deleteCommentNews" method="post">
                <button type="submit" class="btn btn-danger" name="newsId" value="${model.id}">Deleted selected comment</button>
            </form>
        </div>
    </div>
</div>

<footer class="container-fluid">
    <p align="center">KazTrulyNews</br>
        EPAM Systems &copy; 2018</p>
</footer>

</body>
</html>
