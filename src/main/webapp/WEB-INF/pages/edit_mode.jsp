<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
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
            <form:form method="post" action="/addUpNews" modelAttribute="newsModel">
                <h4>
                    <medium>Add/Edit mode</medium>
                </h4>
                <hr>
                <div>
                    <form:label path="date">Edit news date</form:label>
                </div>
                <form:textarea path="date" rows="1" cssStyle="width: 100%; border-radius: 3px"/>
                <hr>
                <div>
                    <form:label path="title">Edit news title</form:label>
                </div>
                <form:textarea path="title" rows="2" cssStyle="width: 100%; border-radius: 3px"/>
                <hr>
                <div>
                    <form:label path="brief">Edit news brief</form:label>
                </div>
                <form:textarea path="brief" rows="3" cssStyle="width: 100%; border-radius: 3px"/>
                <hr>
                <div>
                    <form:label path="content">Edit news content</form:label>
                </div>
                <form:textarea path="content" rows="20" style="width: 100%; border-radius: 3px"/>

                <button type="submit" class="btn btn-primary" name="id" value=${newsModel.id}>Add/Edit news</button>
                <br><br>
            </form:form>
        </div>
    </div>
</div>

<footer class="container-fluid">
    <p align="center">KazTrulyNews</br>
        EPAM Systems &copy; 2018</p>
</footer>

</body>
</html>
