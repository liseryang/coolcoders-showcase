<html>
<head>
  <z:head zul="/net/coolcoders/showcase/profile.zul"/>
  <title><g:message code="default.profile.label"/></title>
  <meta name="layout" content="main"/>
</head>
<body>

<content tag="breadcrump">
  <span class="menuButton"><a class="home" href="${createLink(uri: '/')}"><g:message code="default.message.label"/></a></span>
  <span class="menuButton"><a class="profile" href="${createLink(action: "index")}"><g:message code="default.profile.label"/></a></span>
</content>
<h2><g:message code="profile.heading"/></h2>



<div class="dialog center singleBorder">
  <z:body zul="/net/coolcoders/showcase/profile.zul"/>
</div>

</body>
</html>