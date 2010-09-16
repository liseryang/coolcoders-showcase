<html>
<head>
  <title>Login</title>
  <meta name="layout" content="main"/>
</head>
<body>

<content tag="breadcrump">
  <span class="menuButton"><a class="home" href="${createLink(uri: '/')}"><g:message code="default.login.label"/></a></span>
</content>

<g:hasErrors bean="${loginCommandInstance}">
  <ul>
    <g:eachError var="err" bean="${loginCommandInstance}">
      <li>${err}</li>
    </g:eachError>
  </ul>
</g:hasErrors>
<g:if test="${flash.error}">
  <div class="message">${flash.error}</div>
</g:if>
<div class="login">
  <div class="dialog center singleBorder">
    <g:form action="login">
      <table width="100%">
        <tr class="prop">
          <td class="name"><label for="username"><g:message code="user.username.label"/>:</label></td>
          <td class="value"><g:textField name="username" value="${loginCommandInstance.username}"/></td>
        </tr>
        <tr class="prop">
          <td class="name"><label for="password"><g:message code="user.password.label"/>:</label></td>
          <td class="value"><g:passwordField name="password"/></td>
        </tr>
        <tr>
          <td></td>
          <td style="text-align:left;">
            <input type="submit" value="${message(code: 'default.login.label')}"/>
          </td>
        </tr>
        <tr>
          <td colspan="2">
            <div class="loginHelp">
              <g:message code="login.help.message"/>
              <pre>
                abaumgartner / test123
                anerlich/test123
                jmihelko / test123
                pschneider-manzell / test123
              </pre>
            </div>
            <g:link controller="register"><g:message code="default.register.label"/></g:link>
          </td>
        </tr>
      </table>
    </g:form>
  </div>
</div>

</body>
</html>