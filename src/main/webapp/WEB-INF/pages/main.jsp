<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>TrulyNews</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="static/css/page-style.css" rel="stylesheet" type="text/css" media="screen"/>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <link rel="stylesheet" href="//cdnjs.cloudflare.com/ajax/libs/font-awesome/4.2.0/css/font-awesome.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body>

<div class="container-fluid">
    <div class="row content">
        <div class="col-sm-3 sidenav">
            <a title="TrulyNews" href='<c:url value="/main"/>'>
                <img src="static/pics/truly.png" height="130px">
            </a>
            <hr>
            <ul class="nav nav-pills nav-stacked">
                <li class="active"><a href="#section1">Recent</a></li>
                <li><a href="#section2">Politics</a></li>
                <li><a href="#section3">Incidents</a></li>
                <li><a href="#section3">Life</a></li>
            </ul>
        </div>
        <div style="float: left; padding-left: 14px">
            <h3>Hello ${user}</h3>
        </div>
        <br>
        <div>
            <c:if test="${pageContext.request.userPrincipal.name != null}">
                <div style="float:right; padding-right:24px">
                    <c:url var="logoutUrl" value="/logout"/>
                    <form action="${logoutUrl}" id="logout" method="post">
                        <input type="hidden" name="${_csrf.parameterName}"
                               value="${_csrf.token}"/>
                        <input class="btn btn-primary" type="submit" name="submit" value="Log Out">
                    </form>
                </div>
            </c:if>
            <c:if test="${pageContext.request.isUserInRole('ROLE_ADMIN')}">
                <div style="float:right; padding-right:32px">
                    <a href='<c:url value="/openAddNews"/>' type="button" class="btn btn-success">Add News</a>
                </div>
            </c:if>
            <c:if test="${pageContext.request.userPrincipal.name == null}">
                <div style="float:right; padding-right:24px;">
                    <a href='<c:url value="/openRegisterPage"/>' type="button" class="btn btn-primary">Sign Up</a>
                </div>
                <div style="float:right; padding-right:24px;">
                    <a href='<c:url value="/openLoginPage"/>' type="button" class="btn btn-primary">Sign In</a>
                </div>
            </c:if>
        </div>

        <div class="col-sm-9">
            <c:forEach items="${newsDTOMap}" var="entry">
                <form method="post" action="/openSelectedNews">
                    <input type="hidden" name="${_csrf.parameterName}"
                           value="${_csrf.token}"/>
                    <h4>
                        <small>RECENT POSTS</small>
                    </h4>
                    <hr>
                    <div style="float:right;padding-right:16px;">
                        <c:if test="${pageContext.request.isUserInRole('ROLE_ADMIN')}">
                        <div class="checkbox-primary">
                            <label><input name="deleteNewsCheckbox" type="checkbox" form="form1"
                                          value=${entry.value.id}> Deletion label</label>
                        </div>
                        </c:if>
                    </div>
                    <h2>${entry.value.title}</h2>
                    <h5><span class="glyphicon glyphicon-time"></span> ${entry.value.date}
                    </h5>
                    <h5><span class="label label-danger">Incidents</span> <span
                            class="label label-primary">Cataclysms</span>
                    </h5><br>
                    <h5>${entry.value.brief}</h5>
                    <input type="hidden" name="newsId" value="${entry.value.id}"/>
                    <button type="submit" class="btn btn-info">Read more</button>
                    <br><br>
                </form>
            </c:forEach>
            <hr>
        </div>
        <div style="float:right;padding-right:16px; padding-bottom: 16px">
            <c:if test="${pageContext.request.isUserInRole('ROLE_ADMIN')}">
            <form name="news" id="form1" action="/deleteNews" method="post">
                <input type="hidden" name="${_csrf.parameterName}"
                       value="${_csrf.token}"/>
                <button type="submit" class="btn btn-danger">Deleted selected news</button>
            </form>
            </c:if>
        </div>
    </div>
</div>

<footer class="container-fluid">
    <p align="center">TrulyNews</br>
        EPAM Systems &copy; 2018</p>
</footer>

</body>
</html>
