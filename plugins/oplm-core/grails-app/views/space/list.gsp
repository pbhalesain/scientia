
<%@ page import="org.oplm.core.model.admin.Space" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'space.label', default: 'Space')}" />
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
                        
                            <g:sortableColumn property="id" title="${message(code: 'space.id.label', default: 'Id')}" />
                        
                            <th><g:message code="space.createdBy.label" default="Created By" /></th>
                   	    
                            <g:sortableColumn property="dateCreated" title="${message(code: 'space.dateCreated.label', default: 'Date Created')}" />
                        
                            <g:sortableColumn property="hostType" title="${message(code: 'space.hostType.label', default: 'Host Type')}" />
                        
                            <g:sortableColumn property="lastUpdated" title="${message(code: 'space.lastUpdated.label', default: 'Last Updated')}" />
                        
                            <g:sortableColumn property="location" title="${message(code: 'space.location.label', default: 'Location')}" />
                        
                        </tr>
                    </thead>
                    <tbody>
                    <g:each in="${spaceInstanceList}" status="i" var="spaceInstance">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        
                            <td><g:link action="show" id="${spaceInstance.id}">${fieldValue(bean: spaceInstance, field: "id")}</g:link></td>
                        
                            <td>${fieldValue(bean: spaceInstance, field: "createdBy")}</td>
                        
                            <td><g:formatDate date="${spaceInstance.dateCreated}" /></td>
                        
                            <td>${fieldValue(bean: spaceInstance, field: "hostType")}</td>
                        
                            <td><g:formatDate date="${spaceInstance.lastUpdated}" /></td>
                        
                            <td>${fieldValue(bean: spaceInstance, field: "location")}</td>
                        
                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
            <div class="paginateButtons">
                <g:paginate total="${spaceInstanceTotal}" />
            </div>
        </div>
    </body>
</html>
