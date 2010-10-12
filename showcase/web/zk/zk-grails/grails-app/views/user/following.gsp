<html>
<head>
  <z:head zul="/net/coolcoders/showcase/following.zul"/>
  <title>Messages</title>
  <meta name="layout" content="main"/>
</head>
<body>

<content tag="breadcrump">
  <span class="menuButton"><a class="home" href="${createLink(uri: '/')}"><g:message code="default.message.label"/></a></span>
</content>


<g:if test="${flash.error}">
  <div class="message">${flash.error}</div>
</g:if>
<div class="center">
  <z:body zul="/net/coolcoders/showcase/following.zul"/>
</div>

</body>
</html>