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
    <g:each in="${followingUsers}" var="followingUser">
      <tr>
        <td>
          ${followingUser.username.encodeAsHTML()}
        </td>
        <td>
          ${Message.countByUser(followingUser)}
        </td>
        <td>
          <g:link action="unfollow" id="${followingUser.id}"><g:message code="default.button.unfollow.label"/></g:link>
        </td>
      </tr>
    </g:each>
    </tbody>
  </table>
</div>
<g:link action="search"><g:message code="default.button.search.label"/></g:link>
</body>
</html>