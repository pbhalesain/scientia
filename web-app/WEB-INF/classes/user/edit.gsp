
<%@ page import="org.oplm.core.model.admin.User" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'user.label', default: 'User')}" />
        <title><g:message code="default.edit.label" args="[entityName]" /></title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLink(uri: '/')}">Home</a></span>
            <span class="menuButton"><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></span>
            <span class="menuButton"><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></span>
        </div>
        <div class="body">
            <h1><g:message code="default.edit.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${userInstance}">
            <div class="errors">
                <g:renderErrors bean="${userInstance}" as="list" />
            </div>
            </g:hasErrors>
            <g:form method="post" >
                <g:hiddenField name="id" value="${userInstance?.id}" />
                <g:hiddenField name="version" value="${userInstance?.version}" />
                <div class="dialog">
                    <table>
                        <tbody>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="active"><g:message code="user.active.label" default="Active" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: userInstance, field: 'active', 'errors')}">
                                    <g:textField name="active" value="${userInstance?.active}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="address"><g:message code="user.address.label" default="Address" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: userInstance, field: 'address', 'errors')}">
                                    
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="createdDocuments"><g:message code="user.createdDocuments.label" default="Created Documents" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: userInstance, field: 'createdDocuments', 'errors')}">
                                    <g:select name="createdDocuments" from="${org.oplm.core.model.business.Document.list()}" multiple="yes" optionKey="id" size="5" value="${userInstance?.createdDocuments}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="createdProducts"><g:message code="user.createdProducts.label" default="Created Products" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: userInstance, field: 'createdProducts', 'errors')}">
                                    <g:select name="createdProducts" from="${org.oplm.core.model.business.Product.list()}" multiple="yes" optionKey="id" size="5" value="${userInstance?.createdProducts}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="dateCreated"><g:message code="user.dateCreated.label" default="Date Created" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: userInstance, field: 'dateCreated', 'errors')}">
                                    <g:datePicker name="dateCreated" precision="day" value="${userInstance?.dateCreated}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="email"><g:message code="user.email.label" default="Email" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: userInstance, field: 'email', 'errors')}">
                                    <g:textField name="email" value="${userInstance?.email}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="lastUpdated"><g:message code="user.lastUpdated.label" default="Last Updated" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: userInstance, field: 'lastUpdated', 'errors')}">
                                    <g:datePicker name="lastUpdated" precision="day" value="${userInstance?.lastUpdated}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="lastUpdatedProducts"><g:message code="user.lastUpdatedProducts.label" default="Last Updated Products" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: userInstance, field: 'lastUpdatedProducts', 'errors')}">
                                    <g:select name="lastUpdatedProducts.id" from="${org.oplm.core.model.business.Product.list()}" optionKey="id" value="${userInstance?.lastUpdatedProducts?.id}"  />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="locked"><g:message code="user.locked.label" default="Locked" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: userInstance, field: 'locked', 'errors')}">
                                    <g:textField name="locked" value="${userInstance?.locked}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="passwdExpired"><g:message code="user.passwdExpired.label" default="Passwd Expired" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: userInstance, field: 'passwdExpired', 'errors')}">
                                    <g:textField name="passwdExpired" value="${userInstance?.passwdExpired}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="password"><g:message code="user.password.label" default="Password" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: userInstance, field: 'password', 'errors')}">
                                    <g:textField name="password" value="${userInstance?.password}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="spaces"><g:message code="user.spaces.label" default="Spaces" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: userInstance, field: 'spaces', 'errors')}">
                                    <g:select name="spaces" from="${org.oplm.core.model.admin.Space.list()}" multiple="yes" optionKey="id" size="5" value="${userInstance?.spaces}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="team"><g:message code="user.team.label" default="Team" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: userInstance, field: 'team', 'errors')}">
                                    <g:select name="team" from="${org.oplm.core.model.admin.Team.list()}" multiple="yes" optionKey="id" size="5" value="${userInstance?.team}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="updatedDocuments"><g:message code="user.updatedDocuments.label" default="Updated Documents" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: userInstance, field: 'updatedDocuments', 'errors')}">
                                    <g:select name="updatedDocuments" from="${org.oplm.core.model.business.Document.list()}" multiple="yes" optionKey="id" size="5" value="${userInstance?.updatedDocuments}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="userRole"><g:message code="user.userRole.label" default="User Role" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: userInstance, field: 'userRole', 'errors')}">
                                    <g:select name="userRole.id" from="${org.oplm.core.model.admin.UserRole.list()}" optionKey="id" value="${userInstance?.userRole?.id}"  />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="username"><g:message code="user.username.label" default="Username" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: userInstance, field: 'username', 'errors')}">
                                    <g:textField name="username" value="${userInstance?.username}" />
                                </td>
                            </tr>
                        
                        </tbody>
                    </table>
                </div>
                <div class="buttons">
                    <span class="button"><g:actionSubmit class="save" action="update" value="${message(code: 'default.button.update.label', default: 'Update')}" /></span>
                    <span class="button"><g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" /></span>
                </div>
            </g:form>
        </div>
    </body>
</html>
