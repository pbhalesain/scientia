
<%@ page import="org.oplm.core.model.admin.Space" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'space.label', default: 'Space')}" />
        <title><g:message code="default.create.label" args="[entityName]" /></title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLink(uri: '/')}">Home</a></span>
            <span class="menuButton"><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></span>
        </div>
        <div class="body">
            <h1><g:message code="default.create.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${spaceInstance}">
            <div class="errors">
                <g:renderErrors bean="${spaceInstance}" as="list" />
            </div>
            </g:hasErrors>
            <g:form action="save" method="post" >
                <div class="dialog">
                    <table>
                        <tbody>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="createdBy"><g:message code="space.createdBy.label" default="Created By" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: spaceInstance, field: 'createdBy', 'errors')}">
                                    <g:select name="createdBy.id" from="${org.oplm.core.model.admin.User.list()}" optionKey="id" value="${spaceInstance?.createdBy?.id}"  />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="dateCreated"><g:message code="space.dateCreated.label" default="Date Created" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: spaceInstance, field: 'dateCreated', 'errors')}">
                                    <g:datePicker name="dateCreated" precision="day" value="${spaceInstance?.dateCreated}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="hostType"><g:message code="space.hostType.label" default="Host Type" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: spaceInstance, field: 'hostType', 'errors')}">
                                    <g:textField name="hostType" value="${spaceInstance?.hostType}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="lastUpdated"><g:message code="space.lastUpdated.label" default="Last Updated" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: spaceInstance, field: 'lastUpdated', 'errors')}">
                                    <g:datePicker name="lastUpdated" precision="day" value="${spaceInstance?.lastUpdated}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="location"><g:message code="space.location.label" default="Location" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: spaceInstance, field: 'location', 'errors')}">
                                    <g:textField name="location" value="${spaceInstance?.location}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="name"><g:message code="space.name.label" default="Name" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: spaceInstance, field: 'name', 'errors')}">
                                    <g:textField name="name" value="${spaceInstance?.name}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="owner"><g:message code="space.owner.label" default="Owner" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: spaceInstance, field: 'owner', 'errors')}">
                                    <g:textField name="owner" value="${spaceInstance?.owner}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="ownerType"><g:message code="space.ownerType.label" default="Owner Type" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: spaceInstance, field: 'ownerType', 'errors')}">
                                    <g:textField name="ownerType" value="${spaceInstance?.ownerType}" />
                                </td>
                            </tr>
                        
                        </tbody>
                    </table>
                </div>
                <div class="buttons">
                    <span class="button"><g:submitButton name="create" class="save" value="${message(code: 'default.button.create.label', default: 'Create')}" /></span>
                </div>
            </g:form>
        </div>
    </body>
</html>
