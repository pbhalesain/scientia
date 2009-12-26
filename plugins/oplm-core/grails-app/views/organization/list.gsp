
<%@ page import="org.oplm.core.model.admin.Organization" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'organization.label', default: 'Organization')}" />
        <title><g:message code="default.list.label" args="[entityName]" /></title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLink(uri: '/')}">Home</a></span>
            <span class="menuButton"><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></span>
        </div>
        <div class="body">
            <h1><g:message code="default.list.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <div class="list">
                <table>
                    <thead>
                        <tr>
                        
                            <g:sortableColumn property="id" title="${message(code: 'organization.id.label', default: 'Id')}" />
                        
                            <g:sortableColumn property="address" title="${message(code: 'organization.address.label', default: 'Address')}" />
                        
                            <g:sortableColumn property="dateCreated" title="${message(code: 'organization.dateCreated.label', default: 'Date Created')}" />
                        
                            <g:sortableColumn property="description" title="${message(code: 'organization.description.label', default: 'Description')}" />
                        
                            <g:sortableColumn property="lastUpdated" title="${message(code: 'organization.lastUpdated.label', default: 'Last Updated')}" />
                        
                            <g:sortableColumn property="name" title="${message(code: 'organization.name.label', default: 'Name')}" />
                        
                        </tr>
                    </thead>
                    <tbody>
                    <g:each in="${organizationInstanceList}" status="i" var="organizationInstance">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        
                            <td><g:link action="show" id="${organizationInstance.id}">${fieldValue(bean: organizationInstance, field: "id")}</g:link></td>
                        
                            <td>${fieldValue(bean: organizationInstance, field: "address")}</td>
                        
                            <td><g:formatDate date="${organizationInstance.dateCreated}" /></td>
                        
                            <td>${fieldValue(bean: organizationInstance, field: "description")}</td>
                        
                            <td><g:formatDate date="${organizationInstance.lastUpdated}" /></td>
                        
                            <td>${fieldValue(bean: organizationInstance, field: "name")}</td>
                        
                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
            <div class="paginateButtons">
                <g:paginate total="${organizationInstanceTotal}" />
            </div>
        </div>
    </body>
</html>
