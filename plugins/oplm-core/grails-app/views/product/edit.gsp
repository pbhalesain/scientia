
<%@ page import="org.oplm.core.model.business.Product" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'product.label', default: 'Product')}" />
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
            <g:hasErrors bean="${productInstance}">
            <div class="errors">
                <g:renderErrors bean="${productInstance}" as="list" />
            </div>
            </g:hasErrors>
            <g:form method="post" >
                <g:hiddenField name="id" value="${productInstance?.id}" />
                <g:hiddenField name="version" value="${productInstance?.version}" />
                <div class="dialog">
                    <table>
                        <tbody>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="checkedOut"><g:message code="product.checkedOut.label" default="Checked Out" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: productInstance, field: 'checkedOut', 'errors')}">
                                    <g:checkBox name="checkedOut" value="${productInstance?.checkedOut}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="checkedOutAs"><g:message code="product.checkedOutAs.label" default="Checked Out As" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: productInstance, field: 'checkedOutAs', 'errors')}">
                                    <g:select name="checkedOutAs.id" from="${org.oplm.core.model.business.Product.list()}" optionKey="id" value="${productInstance?.checkedOutAs?.id}"  />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="checkedOutFrom"><g:message code="product.checkedOutFrom.label" default="Checked Out From" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: productInstance, field: 'checkedOutFrom', 'errors')}">
                                    <g:select name="checkedOutFrom.id" from="${org.oplm.core.model.business.Product.list()}" optionKey="id" value="${productInstance?.checkedOutFrom?.id}"  />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="checkedOutSuperseded"><g:message code="product.checkedOutSuperseded.label" default="Checked Out Superseded" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: productInstance, field: 'checkedOutSuperseded', 'errors')}">
                                    <g:checkBox name="checkedOutSuperseded" value="${productInstance?.checkedOutSuperseded}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="createdBy"><g:message code="product.createdBy.label" default="Created By" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: productInstance, field: 'createdBy', 'errors')}">
                                    <g:select name="createdBy.id" from="${org.oplm.core.model.admin.User.list()}" optionKey="id" value="${productInstance?.createdBy?.id}"  />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="dateCreated"><g:message code="product.dateCreated.label" default="Date Created" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: productInstance, field: 'dateCreated', 'errors')}">
                                    <g:datePicker name="dateCreated" precision="day" value="${productInstance?.dateCreated}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="description"><g:message code="product.description.label" default="Description" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: productInstance, field: 'description', 'errors')}">
                                    <g:textField name="description" value="${productInstance?.description}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="documents"><g:message code="product.documents.label" default="Documents" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: productInstance, field: 'documents', 'errors')}">
                                    <g:select name="documents" from="${org.oplm.core.model.business.ProductDocument.list()}" multiple="yes" optionKey="id" size="5" value="${productInstance?.documents}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="frozen"><g:message code="product.frozen.label" default="Frozen" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: productInstance, field: 'frozen', 'errors')}">
                                    <g:checkBox name="frozen" value="${productInstance?.frozen}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="isMajorVersion"><g:message code="product.isMajorVersion.label" default="Is Major Version" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: productInstance, field: 'isMajorVersion', 'errors')}">
                                    <g:checkBox name="isMajorVersion" value="${productInstance?.isMajorVersion}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="lastUpdated"><g:message code="product.lastUpdated.label" default="Last Updated" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: productInstance, field: 'lastUpdated', 'errors')}">
                                    <g:datePicker name="lastUpdated" precision="day" value="${productInstance?.lastUpdated}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="lastUpdatedBy"><g:message code="product.lastUpdatedBy.label" default="Last Updated By" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: productInstance, field: 'lastUpdatedBy', 'errors')}">
                                    <g:select name="lastUpdatedBy.id" from="${org.oplm.core.model.admin.User.list()}" optionKey="id" value="${productInstance?.lastUpdatedBy?.id}"  />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="majorVersion"><g:message code="product.majorVersion.label" default="Major Version" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: productInstance, field: 'majorVersion', 'errors')}">
                                    <g:textField name="majorVersion" value="${fieldValue(bean: productInstance, field: 'majorVersion')}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="minorVersion"><g:message code="product.minorVersion.label" default="Minor Version" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: productInstance, field: 'minorVersion', 'errors')}">
                                    <g:textField name="minorVersion" value="${fieldValue(bean: productInstance, field: 'minorVersion')}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="name"><g:message code="product.name.label" default="Name" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: productInstance, field: 'name', 'errors')}">
                                    <g:textField name="name" value="${productInstance?.name}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="number"><g:message code="product.number.label" default="Number" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: productInstance, field: 'number', 'errors')}">
                                    <g:textField name="number" value="${productInstance?.number}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="productMaster"><g:message code="product.productMaster.label" default="Product Master" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: productInstance, field: 'productMaster', 'errors')}">
                                    <g:select name="productMaster" from="${org.oplm.core.model.business.ProductMaster.list()}" multiple="yes" optionKey="id" size="5" value="${productInstance?.productMaster}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="superseded"><g:message code="product.superseded.label" default="Superseded" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: productInstance, field: 'superseded', 'errors')}">
                                    <g:checkBox name="superseded" value="${productInstance?.superseded}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="usesProducts"><g:message code="product.usesProducts.label" default="Uses Products" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: productInstance, field: 'usesProducts', 'errors')}">
                                    <g:select name="usesProducts" from="${org.oplm.core.model.business.ProductStructure.list()}" multiple="yes" optionKey="id" size="5" value="${productInstance?.usesProducts}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="versionedAs"><g:message code="product.versionedAs.label" default="Versioned As" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: productInstance, field: 'versionedAs', 'errors')}">
                                    <g:select name="versionedAs.id" from="${org.oplm.core.model.business.Product.list()}" optionKey="id" value="${productInstance?.versionedAs?.id}"  />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="versionedFrom"><g:message code="product.versionedFrom.label" default="Versioned From" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: productInstance, field: 'versionedFrom', 'errors')}">
                                    <g:select name="versionedFrom.id" from="${org.oplm.core.model.business.Product.list()}" optionKey="id" value="${productInstance?.versionedFrom?.id}"  />
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
