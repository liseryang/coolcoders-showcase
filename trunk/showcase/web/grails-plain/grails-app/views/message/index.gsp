<html>
<head>
  <title>Messages</title>
  <meta name="layout" content="main"/>
</head>
<body>

<content tag="breadcrump">
  <span class="menuButton"><a class="home" href="${createLink(uri: '/')}"><g:message code="default.message.label"/></a></span>
</content>

<g:form action="create" controller="message">
  <div class="dialog center singleBorder" id="messageUpdateBox">
    <p>
      <g:message code="message.create.label"/>
    </p>
    <g:textArea name="content"/>
    <p>
      <g:actionSubmit value="Send" action="create"/>
    </p>
  </div>
</g:form>

<g:if test="${messages}">
  <div id="messageList">
    <g:each in="${messages}" var="messageInstance">
      <div class="messageEntry clearfix">
        <div class="messageDate"><g:formatDate date="${messageInstance?.created}" style="SHORT" type="datetime"/></div>
        <div class="messageUser">${messageInstance?.user?.username}</div>
        <div class="messageContent">${messageInstance?.content}</div>
      </div>
    </g:each>

    <div>
      <div style="text-align:center">
        <g:if test="${prevPageAvailable}"><g:link action="index" params="${[offset:offset-pageSize]}"><g:message code="paging.prev"/></g:link></g:if>
        <g:if test="${nextPageAvailable}"><g:link action="index" params="${[offset:offset+pageSize]}"><g:message code="paging.next"/></g:link></g:if>

      </div>
    </div>
  </div>
  <div class="pageActions">
    <g:link action="following" controller="user"><g:message code="default.viewfriends.label"/></g:link>
  </div>
</g:if>
<g:else>
  <g:if test="${currentUser.following}">
    <div class="lazyFriends">
      <p><g:message code="message.list.lazyfriends"/></p>
      <g:link controller="user" action="following"><g:message code="default.findnewfriends.label"/></g:link>
    </div>
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