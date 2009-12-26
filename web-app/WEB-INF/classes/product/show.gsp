
<%@ page import="org.oplm.core.model.business.Product" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'product.label', default: 'Product')}" />
        <title><g:message code="default.show.label" args="[entityName]" /></title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLink(uri: '/')}">Home</a></span>
            <span class="menuButton"><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></span>
            <span class="menuButton"><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></span>
        </div>
        <div class="body">
            <h1><g:message code="default.show.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <div class="dialog">
                <table>
                    <tbody>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="product.id.label" default="Id" /></td>
                            
                            <td valign="top" class="value">${fieldValue(bean: productInstance, field: "id")}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="product.checkedOut.label" default="Checked Out" /></td>
                            
                            <td valign="top" class="value"><g:formatBoolean boolean="${productInstance?.checkedOut}" /></td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="product.checkedOutAs.label" default="Checked Out As" /></td>
                            
                            <td valign="top" class="value"><g:link controller="product" action="show" id="${productInstance?.checkedOutAs?.id}">${productInstance?.checkedOutAs?.encodeAsHTML()}</g:link></td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="product.checkedOutFrom.label" default="Checked Out From" /></td>
                            
                            <td valign="top" class="value"><g:link controller="product" action="show" id="${productInstance?.checkedOutFrom?.id}">${productInstance?.checkedOutFrom?.encodeAsHTML()}</g:link></td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="product.checkedOutSuperseded.label" default="Checked Out Superseded" /></td>
                            
                            <td valign="top" class="value"><g:formatBoolean boolean="${productInstance?.checkedOutSuperseded}" /></td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="product.createdBy.label" default="Created By" /></td>
                            
                            <td valign="top" class="value"><g:link controller="user" action="show" id="${productInstance?.createdBy?.id}">${productInstance?.createdBy?.encodeAsHTML()}</g:link></td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="product.dateCreated.label" default="Date Created" /></td>
                            
                            <td valign="top" class="value"><g:formatDate date="${productInstance?.dateCreated}" /></td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="product.description.label" default="Description" /></td>
                            
                            <td valign="top" class="value">${fieldValue(bean: productInstance, field: "description")}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="product.documents.label" default="Documents" /></td>
                            
                            <td valign="top" style="text-align: left;" class="value">
                                <ul>
                                <g:each in="${productInstance.documents}" var="d">
                                    <li><g:link controller="productDocument" action="show" id="${d.id}">${d?.encodeAsHTML()}</g:link></li>
                                </g:each>
                                </ul>
                            </td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="product.frozen.label" default="Frozen" /></td>
                            
                            <td valign="top" class="value"><g:formatBoolean boolean="${productInstance?.frozen}" /></td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="product.isMajorVersion.label" default="Is Major Version" /></td>
                            
                            <td valign="top" class="value"><g:formatBoolean boolean="${productInstance?.isMajorVersion}" /></td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="product.lastUpdated.label" default="Last Updated" /></td>
                            
                            <td valign="top" class="value"><g:formatDate date="${productInstance?.lastUpdated}" /></td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="product.lastUpdatedBy.label" default="Last Updated By" /></td>
                            
                            <td valign="top" class="value"><g:link controller="user" action="show" id="${productInstance?.lastUpdatedBy?.id}">${productInstance?.lastUpdatedBy?.encodeAsHTML()}</g:link></td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="product.majorVersion.label" default="Major Version" /></td>
                            
                            <td valign="top" class="value">${fieldValue(bean: productInstance, field: "majorVersion")}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="product.minorVersion.label" default="Minor Version" /></td>
                            
                            <td valign="top" class="value">${fieldValue(bean: productInstance, field: "minorVersion")}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="product.name.label" default="Name" /></td>
                            
                            <td valign="top" class="value">${fieldValue(bean: productInstance, field: "name")}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="product.number.label" default="Number" /></td>
                            
                            <td valign="top" class="value">${fieldValue(bean: productInstance, field: "number")}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="product.productMaster.label" default="Product Master" /></td>
                            
                            <td valign="top" style="text-align: left;" class="value">
                                <ul>
                                <g:each in="${productInstance.productMaster}" var="p">
                                    <li><g:link controller="productMaster" action="show" id="${p.id}">${p?.encodeAsHTML()}</g:link></li>
                                </g:each>
                                </ul>
                            </td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="product.superseded.label" default="Superseded" /></td>
                            
                            <td valign="top" class="value"><g:formatBoolean boolean="${productInstance?.superseded}" /></td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="product.usesProducts.label" default="Uses Products" /></td>
                            
                            <td valign="top" style="text-align: left;" class="value">
                                <ul>
                                <g:each in="${productInstance.usesProducts}" var="u">
                                    <li><g:link controller="productStructure" action="show" id="${u.id}">${u?.encodeAsHTML()}</g:link></li>
                                </g:each>
                                </ul>
                            </td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="product.versionedAs.label" default="Versioned As" /></td>
                            
                            <td valign="top" class="value"><g:link controller="product" action="show" id="${productInstance?.versionedAs?.id}">${productInstance?.versionedAs?.encodeAsHTML()}</g:link></td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="product.versionedFrom.label" default="Versioned From" /></td>
                            
                            <td valign="top" class="value"><g:link controller="product" action="show" id="${productInstance?.versionedFrom?.id}">${productInstance?.versionedFrom?.encodeAsHTML()}</g:link></td>
                            
                        </tr>
                    
                    </tbody>
                </table>
            </div>
            <div class="buttons">
                <g:form>
                    <g:hiddenField name="id" value="${productInstance?.id}" />
                    <span class="button"><g:actionSubmit class="edit" action="edit" value="${message(code: 'default.button.edit.label', default: 'Edit')}" /></span>
                    <span class="button"><g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" /></span>
                </g:form>
            </div>
        </div>
    </body>
</html>
