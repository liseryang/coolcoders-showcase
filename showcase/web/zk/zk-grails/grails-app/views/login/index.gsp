<html>
<head>
  <z:head zul="/net/coolcoders/showcase/login.zul"/>
  <title>Login</title>
  <meta name="layout" content="main"/>
</head>
<body>

<content tag="breadcrump">
  <span class="menuButton"><a class="home" href="${createLink(uri: '/')}"><g:message code="default.login.label"/></a></span>
</content>


<g:if test="${flash.error}">
  <div class="message">${flash.error}</div>
</g:if>
<div class="login">
  <div class="dialog center singleBorder">
    <z:body zul="/net/coolcoders/showcase/login.zul"/>
  </div>
</div>

</body>
</html>