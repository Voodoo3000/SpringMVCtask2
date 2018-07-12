<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
        </div>
        <div style="float: left; padding-left: 16px">
            <h3>Hello ${user}</h3>
        </div>
        <br>
        <div class="col-sm-9">
            <form method="post" action="/addUpNews">
                <h4>
                    <medium>Add/Edit mode</medium>
                </h4>
                <hr>
                <div>
                    <h5>Edit news date:</h5>
                </div>
                <textarea name="date" rows="1" style="width: 100%; border-radius: 3px" required>${newsModel.date}</textarea>
                <hr>
                <div>
                    <h5>Edit news title:</h5>
                </div>
                <textarea name="title" rows="2" style="width: 100%; border-radius: 3px" required>${newsModel.title}</textarea>
                <hr>
                <div>
                    <h5>Edit news brief:</h5>
                </div>
                <textarea name="brief" rows="3" style="width: 100%; border-radius: 3px" required>${newsModel.brief}</textarea>
                <hr>
                <div>
                    <h5>Edit news content:</h5>
                </div>
                <textarea name="content" rows="20" style="width: 100%; border-radius: 3px">${newsModel.content}</textarea>
                <button type="submit" class="btn btn-primary" name="newsId" value="${newsModel.id}">Add/Edit news</button>
                <br><br>
            </form>
        </div>
    </div>
</div>

<footer class="container-fluid">
    <p align="center">TrulyNews</br>
        EPAM Systems &copy; 2018</p>
</footer>

</body>
</html>
