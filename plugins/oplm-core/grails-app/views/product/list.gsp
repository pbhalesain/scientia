
<%@ page import="org.oplm.core.model.business.Product" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'product.label', default: 'Product')}" />
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
                        
                            <g:sortableColumn property="id" title="${message(code: 'product.id.label', default: 'Id')}" />
                        
                            <g:sortableColumn property="checkedOut" title="${message(code: 'product.checkedOut.label', default: 'Checked Out')}" />
                        
                            <th><g:message code="product.checkedOutAs.label" default="Checked Out As" /></th>
                   	    
                            <th><g:message code="product.checkedOutFrom.label" default="Checked Out From" /></th>
                   	    
                            <g:sortableColumn property="checkedOutSuperseded" title="${message(code: 'product.checkedOutSuperseded.label', default: 'Checked Out Superseded')}" />
                        
                            <th><g:message code="product.createdBy.label" default="Created By" /></th>
                   	    
                        </tr>
                    </thead>
                    <tbody>
                    <g:each in="${productInstanceList}" status="i" var="productInstance">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        
                            <td><g:link action="show" id="${productInstance.id}">${fieldValue(bean: productInstance, field: "id")}</g:link></td>
                        
                            <td><g:formatBoolean boolean="${productInstance.checkedOut}" /></td>
                        
                            <td>${fieldValue(bean: productInstance, field: "checkedOutAs")}</td>
                        
                            <td>${fieldValue(bean: productInstance, field: "checkedOutFrom")}</td>
                        
                            <td><g:formatBoolean boolean="${productInstance.checkedOutSuperseded}" /></td>
                        
                            <td>${fieldValue(bean: productInstance, field: "createdBy")}</td>
                        
                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
            <div class="paginateButtons">
                <g:paginate total="${productInstanceTotal}" />
            </div>
        </div>
    </body>
</html>
