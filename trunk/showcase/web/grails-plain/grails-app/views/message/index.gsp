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
  <div id="messageUpdateBox">
    <g:textArea name="content"/>
    <g:actionSubmit value="Send" action="create"/>
  </div>
</g:form>

<g:if test="${messages}">
  <div id="messageList">
    <table>
      <g:each in="${messages}" var="messageInstance">
        <tr>
          <td class="messageDate">
            <g:formatDate date="${messageInstance?.created}" style="SHORT" type="datetime"/>
          </td>
          <td class="messageUser">
            ${messageInstance?.user?.username}
          </td>
          <td class="messageContent">
            ${messageInstance?.content}
          </td>
        </tr>
      </g:each>
    </table>
  </div>
  <div>
    <g:link action="following" controller="user"><g:message code="default.viewfriends.label"/> </g:link> 
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