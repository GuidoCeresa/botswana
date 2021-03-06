<!--Created by Algos s.r.l.-->
<!--Date: mag 2012-->
<!--Questo file è stato installato dal plugin AlgosBase-->
<!--Tipicamente NON verrà più sovrascritto dalle successive release del plugin-->
<!--in quanto POTREBBE essere personalizzato in questa applicazione-->
<!--Se vuoi che le prossime release del plugin sovrascrivano questo file,-->
<!--perdendo tutte le modifiche effettuate qui,-->
<!--regola a true il flag di controllo flagOverwrite©-->
<!--flagOverwrite = false-->

<%@ page import="botswana.Localita" %>
<!doctype html>
<html>
<head>
  <meta name="layout" content="main">
  <g:set var="entityName" value="${message(code: 'localita.label', default: 'Localita')}" />
  <title><g:message code="default.create.label" args="[entityName]" /></title>
</head>
<body>
<a href="#create-localita" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
<div class="nav" role="navigation">
  <ul>
	<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
	<li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
  </ul>
</div>
<div id="create-localita" class="content scaffold-create" role="main">
  <h1><g:message code="default.create.label" args="[entityName]" /></h1>
  <g:if test="${flash.message}">
	<div class="message" role="status">${flash.message}</div>
  </g:if>
  <g:hasErrors bean="${localitaInstance}">
	<ul class="errors" role="alert">
	  <g:eachError bean="${localitaInstance}" var="error">
		<li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></li>
	  </g:eachError>
	</ul>
  </g:hasErrors>
  <g:form action="save" >
  <fieldset class="form">
	<g:render template="form"/>
  </fieldset>
  <fieldset class="buttons">
	<g:submitButton name="create" class="save" value="${message(code: 'default.button.create.label', default: 'Create')}" />
  </fieldset>
  </g:form>
</div>
</body>
</html>
