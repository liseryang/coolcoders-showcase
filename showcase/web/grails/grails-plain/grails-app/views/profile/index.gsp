<%@ page import="net.coolcoders.showcase.Gender; net.coolcoders.showcase.Message" %>
<html>
<head>
  <title><g:message code="default.register.label"/></title>
  <meta name="layout" content="main"/>
</head>
<body>

<content tag="breadcrump">
  <span class="menuButton"><a class="home" href="${createLink(uri: '/')}"><g:message code="default.message.label"/></a></span>
  <span class="menuButton"><a class="profile" href="${createLink(action: "index")}"><g:message code="default.profile.label"/></a></span>
</content>
<h2><g:message code="profile.heading"/></h2>


<div>

  <g:form action="update">
    <div class="dialog center singleBorder">
      <table>
        <tr>
          <td colspan="2">
            <g:hasErrors bean="${userInstance}">
              <div class="errors">
                <ul>
                  <g:eachError var="err" bean="${userInstance}">
                    <li><g:message error="${err}"/></li>
                  </g:eachError>
                </ul>
              </div>
            </g:hasErrors>
          </td>
        </tr>
        <tr class="prop">
          <td class="name">
            <label for="username"><g:message code="user.username.label"/></label>
          </td>
          <td class="value ${hasErrors(bean: userInstance, field: 'username', 'errors')}">
            <g:textField name="username" value="${userInstance.username}"/>
          </td>
        </tr>
        <tr class="prop">
          <td class="name">
            <label for="fullname"><g:message code="user.fullname.label"/></label>
          </td>
          <td class="value ${hasErrors(bean: userInstance, field: 'fullname', 'errors')}">
            <g:textField name="fullname" value="${userInstance.fullname}"/>
          </td>
        </tr>
        <tr class="prop">
          <td class="name">
            <label for="email"><g:message code="user.email.label"/></label>
          </td>
          <td class="value ${hasErrors(bean: userInstance, field: 'email', 'errors')}">
            <g:textField name="email" value="${userInstance.email}"/>
          </td>
        </tr>
        <tr class="prop">
          <td class="name">
            <label for="email"><g:message code="user.gender.label"/></label>
          </td>
          <td class="value ${hasErrors(bean: userInstance, field: 'gender', 'errors')}">
            <g:radioGroup name="gender" values="${Gender.values()}" labels="${Gender.values()*.i18nKey}" value="${userInstance.gender}">
              <p>${it.radio} <g:message code="${it.label}"/></p>
            </g:radioGroup>
          </td>
        </tr>
        <tr class="prop">
          <td class="name">
            <label for="password"><g:message code="user.password.label"/></label>
          </td>
          <td class="value ${hasErrors(bean: userInstance, field: 'password', 'errors')}">
            <g:passwordField name="password" value="${userInstance.password}"/>
          </td>
        </tr>
        <tr class="prop">
          <td class="name">
            <label for="repassword"><g:message code="user.repassword.label"/></label>
          </td>
          <td class="value ${hasErrors(bean: userInstance, field: 'repassword', 'errors')}">
            <g:passwordField name="repassword" value="${userInstance.repassword}"/>
          </td>
        </tr>
        <tr class="prop">
          <td class="name">
            <label for="birthday"><g:message code="user.birthday.label"/></label>
          </td>
          <td class="value ${hasErrors(bean: userInstance, field: 'birthday', 'errors')}">
            <g:datePicker name="birthday" value="${userInstance.birthday}" noSelection="['':'-Choose-']" precision="day"/>
          </td>
        </tr>
        <tr class="prop">
          <td class="name">
            <label for="categoryIds"><g:message code="user.categories.label"/></label>
          </td>
          <td class="value ${hasErrors(bean: userInstance, field: 'categories', 'errors')}">
            <g:each in="${availableCategories}" var="categoryInstance">
              <p><g:checkBox name="categoryIds" value="${categoryInstance.id}" checked="${userInstance.categories.any{it.id == categoryInstance.id}}"/>&nbsp;${categoryInstance.name}</p>
            </g:each>
          </td>
        </tr>
      </table>
      <div class="pageActions"><g:actionSubmit value="Update" action="update"/></div>
    </div>
  </g:form>
</div>

</body>
</html>