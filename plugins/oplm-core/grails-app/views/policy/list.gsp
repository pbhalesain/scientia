
<%@ page import="org.oplm.core.model.admin.Policy" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'policy.label', default: 'Policy')}" />
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
                        
                            <g:sortableColumn property="id" title="${message(code: 'policy.id.label', default: 'Id')}" />
                        
                            <g:sortableColumn property="behaviour" title="${message(code: 'policy.behaviour.label', default: 'Behaviour')}" />
                        
                            <g:sortableColumn property="dateCreated" title="${message(code: 'policy.dateCreated.label', default: 'Date Created')}" />
                        
                            <g:sortableColumn property="lastUpdated" title="${message(code: 'policy.lastUpdated.label', default: 'Last Updated')}" />
                        
                            <g:sortableColumn property="name" title="${message(code: 'policy.name.label', default: 'Name')}" />
                        
                            <g:sortableColumn property="type" title="${message(code: 'policy.type.label', default: 'Type')}" />
                        
                        </tr>
                    </thead>
                    <tbody>
                    <g:each in="${policyInstanceList}" status="i" var="policyInstance">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        
                            <td><g:link action="show" id="${policyInstance.id}">${fieldValue(bean: policyInstance, field: "id")}</g:link></td>
                        
                            <td>${fieldValue(bean: policyInstance, field: "behaviour")}</td>
                        
                            <td><g:formatDate date="${policyInstance.dateCreated}" /></td>
                        
                            <td><g:formatDate date="${policyInstance.lastUpdated}" /></td>
                        
                            <td>${fieldValue(bean: policyInstance, field: "name")}</td>
                        
                            <td>${fieldValue(bean: policyInstance, field: "type")}</td>
                        
                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
            <div class="paginateButtons">
                <g:paginate total="${policyInstanceTotal}" />
            </div>
        </div>
    </body>
</html>
