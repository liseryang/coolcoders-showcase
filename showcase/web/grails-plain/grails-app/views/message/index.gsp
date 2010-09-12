<html>
<head>
  <title>Login</title>
  <meta name="layout" content="main"/>
</head>
<body>

<content tag="breadcrump">
  <span class="menuButton"><a class="home" href="${createLink(uri: '/')}"><g:message code="default.message.label"/></a></span>
</content>

<g:if test="${messages}">

</g:if>
<g:else>
  <g:if test="${currentUser.following}">
    <div class="lazyFriends"><g:message code="message.list.lazyfriends"/></div>
  </g:if>
  <g:else>
    <div class="noFriends">
      <p><g:message code="message.list.findsomefriends"/></p>
      <g:link controller="user" action="following"><g:message code="default.findnewfriends.label"/></g:link>
    </div>

  </g:else>
</g:else>


</body>
</html>