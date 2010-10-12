<%@ page import="net.coolcoders.showcase.Message" %>
<html>
<head>
   <z:head zul="/net/coolcoders/showcase/findfriends.zul"/>
  <title>Friends of ${session.currentUser.username}</title>
  <meta name="layout" content="main"/>
</head>
<body>

<content tag="breadcrump">
  <span class="menuButton"><a class="home" href="${createLink(uri: '/')}"><g:message code="default.message.label"/></a></span>
  <span class="menuButton"><a class="following" href="${createLink(action: "following")}"><g:message code="default.following.label"/></a></span>
</content>
<g:if test="${flash.error}">
  <div class="message">${flash.error}</div>
</g:if>
<div class="dialog center singleBorder searchUserBox">
  <z:body zul="/net/coolcoders/showcase/findfriends.zul"/>
</div>


</body>
</html>