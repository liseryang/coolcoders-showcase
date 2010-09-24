<%@ page import="net.coolcoders.showcase.UserSortAttribute; net.coolcoders.showcase.User; net.coolcoders.showcase.Message" %>
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
<div class="dialog center singleBorder searchUserBox">
  <table style="width:400px;">
    <thead>
    <tr>
      <th>
        <g:link action="following" params="${[sort:UserSortAttribute.USERNAME.name(),order:nextSortOrder,max:pageSize]}">
          <g:message code="user.username.label"/>
        </g:link>
      </th>
      <th>
        <g:link action="following" params="${[sort:UserSortAttribute.MESSAGE_COUNT.name(),order:nextSortOrder,max:pageSize]}">
          <g:message code="user.post.count.label"/>
        </g:link>
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
          ${Message.countByCreator(followingUser)}
        </td>
        <td>
          <g:link action="unfollow" id="${followingUser.id}"><g:message code="default.button.unfollow.label"/></g:link>
        </td>
      </tr>
    </g:each>
    </tbody>
  </table>
</div>
<div class="pageActions">
  <g:paginate controller="user" action="following" total="${totalCount}"/>
</div>
<div class="pageActions">
  <span><g:message code="paging.pagesize"/></span><g:select name="max" from="${[10,25,50,100]}" value="${pageSize}" onchange="location.href='${createLink(action:'following')}?max='+document.getElementById('max').value"/>
</div>
<div class="pageActions">
  <g:link action="search"><g:message code="default.button.search.label"/></g:link>

</div>
</body>
</html>