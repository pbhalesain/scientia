
<%@ page import="org.oplm.core.model.business.Document" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'document.label', default: 'Document')}" />
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
                        
                            <g:sortableColumn property="id" title="${message(code: 'document.id.label', default: 'Id')}" />
                        
                            <g:sortableColumn property="CheckedOutSuperseded" title="${message(code: 'document.CheckedOutSuperseded.label', default: 'Checked Out Superseded')}" />
                        
                            <g:sortableColumn property="checkedOut" title="${message(code: 'document.checkedOut.label', default: 'Checked Out')}" />
                        
                            <th><g:message code="document.createdBy.label" default="Created By" /></th>
                   	    
                            <g:sortableColumn property="dateCreated" title="${message(code: 'document.dateCreated.label', default: 'Date Created')}" />
                        
                            <g:sortableColumn property="description" title="${message(code: 'document.description.label', default: 'Description')}" />
                        
                        </tr>
                    </thead>
                    <tbody>
                    <g:each in="${documentInstanceList}" status="i" var="documentInstance">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        
                            <td><g:link action="show" id="${documentInstance.id}">${fieldValue(bean: documentInstance, field: "id")}</g:link></td>
                        
                            <td><g:formatBoolean boolean="${documentInstance.CheckedOutSuperseded}" /></td>
                        
                            <td><g:formatBoolean boolean="${documentInstance.checkedOut}" /></td>
                        
                            <td>${fieldValue(bean: documentInstance, field: "createdBy")}</td>
                        
                            <td><g:formatDate date="${documentInstance.dateCreated}" /></td>
                        
                            <td>${fieldValue(bean: documentInstance, field: "description")}</td>
                        
                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
            <div class="paginateButtons">
                <g:paginate total="${documentInstanceTotal}" />
            </div>
        </div>
    </body>
</html>
