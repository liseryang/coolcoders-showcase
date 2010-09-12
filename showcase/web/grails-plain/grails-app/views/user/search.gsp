<%@ page import="net.coolcoders.showcase.Message" %>
<html>
<head>
  <title>Friends of ${session.currentUser.username}</title>
  <meta name="layout" content="main"/>
</head>
<body>

<content tag="breadcrump">
  <span class="menuButton"><a class="home" href="${createLink(uri: '/')}"><g:message code="default.message.label"/></a></span>
  <span class="menuButton"><a class="following" href="${createLink(action: "following")}"><g:message code="default.following.label"/></a></span>
</content>
<div>
  <g:form action="search">
    <g:textField name="queryText" value="${queryText}"/>
    <g:submitButton name="search" value="Search"/>
  </g:form>
</div>
<div class="list">
  <table style="width:400px;">
    <thead>
    <tr>
      <th>
        <g:message code="user.username.label"/>
      </th>
      <th>
        <g:message code="user.post.count.label"/>
      </th>
      <th>
        <g:message code="default.actions.label"/>
      </th>
    </tr>
    </thead>
    <tbody>
    <g:each in="${userResultList}" var="userInstance">
      <tr>
        <td>
          ${userInstance.username.encodeAsHTML()}
        </td>
        <td>
          ${Message.countByUser(userInstance)}
        </td>
        <td>
          <g:if test="${currentUser.following.any {it.id == userInstance.id}}">
            <g:link action="unfollow" id="${userInstance.id}"><g:message code="default.button.unfollow.label"/></g:link>
          </g:if>
          <g:else>
            <g:link action="follow" id="${userInstance.id}"><g:message code="default.button.follow.label"/></g:link>
          </g:else>

        </td>
      </tr>
    </g:each>
    </tbody>
  </table>
</div>

</body>
</html>