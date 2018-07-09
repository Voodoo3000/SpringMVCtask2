<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html lang="en">
<head>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.3/css/bootstrap.min.css"
          integrity="sha384-Zug+QiDoJOrZ5t4lssLdxGhVrurbmBWopoEl+M6BdEfwnCJZtKxi1KgxUyJq13dy" crossorigin="anonymous">
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Signup Template for Bootstrap</title>

    <!-- Bootstrap core CSS -->
    <link href="../../dist/css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="signin.css" rel="stylesheet">
</head>

<body class="text-center">
<div class="row" style="height: 150px;"></div>
<div class="row">
    <div class="col-5"></div>
    <div class="col-2">
        <a title="TrulyNews" href='<c:url value="/main"/>'>
            <img height="90px" src="/static/pics/truly.png">
        </a>
        <form name='f' action="/addUpdateUser" method="post">
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
            <img class="mb-4" src="https://getbootstrap.com/assets/brand/bootstrap-solid.svg" alt="" width="72"
                 height="72">
            <h1 class="h3 mb-3 font-weight-normal">Please sign up</h1>
            <label for="Email" class="sr-only">Email</label>
            <input type="email" id="Email" name="email" class="form-control" placeholder="Email address" required
                   autofocus>
            <label for="inputPassword" class="sr-only">Password</label>
            <input type="password" id="inputPassword" name="password" class="form-control" placeholder="Password"
                   required>
            <label for="inputRePassword" class="sr-only">Repeat Password</label>
            <input type="password" id="inputRePassword" name="rePassword" class="form-control"
                   placeholder="Repeat password" required>
            <label for="FirstName" class="sr-only">First name</label>
            <input type="text" id="FirstName" name="firstName" class="form-control" placeholder="First name" required
                   autofocus>
            <label for="LastName" class="sr-only">Last name</label>
            <input type="text" id="LastName" name="lastName" class="form-control" placeholder="Last name" required
                   autofocus>
            <input class="btn btn-lg btn-primary btn-block" name="submit" type="submit" value="submit">Sign in</input>
            <p class="mt-5 mb-3 text-muted">&copy; 2017-2018</p>
        </form>
    </div>
    <div class="col-2">
        <div class="row" style="height: 170px;"></div>
        <c:forEach items="${errorMessages}" var="message">
            <p style="font-size: 12px; color: red">${message}</p>
            <hr style="border-color: red">
        </c:forEach>
    </div>
    <div class="col-3">
    </div>
</div>
</body>
</html>