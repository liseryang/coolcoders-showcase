<%@ page import="net.coolcoders.showcase.Gender; net.coolcoders.showcase.Message" %>
<html>
<head>
  <z:head zul="/net/coolcoders/showcase/register.zul"/>
  <title><g:message code="default.register.label"/></title>
  <meta name="layout" content="main"/>
</head>
<body>

<content tag="breadcrump">
  <span class="menuButton"><a class="home" href="${createLink(uri: '/')}"><g:message code="default.message.label"/></a></span>
  <span class="menuButton"><a class="register" href="${createLink(action: "index")}"><g:message code="default.register.label"/></a></span>
</content>
<h2><g:message code="register.heading"/></h2>


<div class="dialog center singleBorder searchUserBox">
  <z:body zul="/net/coolcoders/showcase/register.zul"/>
</div>

</body>
</html>