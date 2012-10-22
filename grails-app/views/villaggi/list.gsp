<!--Created by Algos s.r.l.-->
<!--Date: mag 2012-->
<!--Questo file è stato installato dal plugin AlgosBase-->
<!--Tipicamente NON verrà più sovrascritto dalle successive release del plugin-->
<!--in quanto POTREBBE essere personalizzato in questa applicazione-->
<!--Se vuoi che le prossime release del plugin sovrascrivano questo file,-->
<!--perdendo tutte le modifiche effettuate qui,-->
<!--regola a true il flag di controllo flagOverwrite©-->
<!--flagOverwrite = false-->


<%@ page import="botswana.Villaggi" %>
<!doctype html>
<html>
<head>
  <meta name="layout" content="main">
  <g:set var="entityName" value="${message(code: 'villaggi.label', default: 'Villaggi')}"/>
  <title><g:message code="default.list.label" args="[entityName]"/></title>
</head>

<body>
<a href="#list-villaggi" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>

<div class="nav" role="navigation">
  <ul>
	<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
	<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]"/></g:link></li>
      <li><g:link class="edit" action="importa">Import Iniziale</g:link></li>
  </ul>
</div>

<div id="list-villaggi" class="content scaffold-list" role="main">
  <h1><g:message code="default.list.label" args="[entityName]"/></h1>
  <g:if test="${flash.message}">
	<div class="message" role="status">${flash.message}</div>
  </g:if>
  <g:if test="${flash.messages}">
	<ul>
	  <g:each in="${flash.messages}">
		<div class="message" role="status">${it}</div>
	  </g:each>
	</ul>
  </g:if>
  <table>
	<thead>
	<tr>
	  
	  <g:sortableColumn property="id" title="${message(code: 'villaggi.id.label', default: 'Id')}"/>
	  
	  <g:sortableColumn property="csonome" title="${message(code: 'villaggi.csonome.label', default: 'Csonome')}"/>
	  
	  <g:sortableColumn property="nome" title="${message(code: 'villaggi.nome.label', default: 'Nome')}"/>
	  
	  <th><g:message code="villaggi.sottodistretto.label" default="Sottodistretto"/></th>
	  
	  <g:sortableColumn property="popolazione" title="${message(code: 'villaggi.popolazione.label', default: 'Popolazione')}"/>
	  
	  <g:sortableColumn property="localita" title="${message(code: 'villaggi.localita.label', default: 'Localita')}"/>
	  
	  <g:sortableColumn property="latitudine" title="${message(code: 'villaggi.latitudine.label', default: 'Latitudine')}"/>
	  
	  <g:sortableColumn property="longitudine" title="${message(code: 'villaggi.longitudine.label', default: 'Longitudine')}"/>
	  
	</tr>
	</thead>
	<tbody>
	<g:each in="${villaggiInstanceList}" status="i" var="villaggiInstance">
	  <tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
		
		<td><g:link action="show" id="${villaggiInstance.id}">${fieldValue(bean: villaggiInstance, field: "id")}</g:link></td>
		
		<td><g:link action="show"
					id="${villaggiInstance.id}">${fieldValue(bean: villaggiInstance, field: "csonome")}</g:link></td>
		
		<td><g:link action="show"
					id="${villaggiInstance.id}">${fieldValue(bean: villaggiInstance, field: "nome")}</g:link></td>
		
		<td><g:link action="show"
					id="${villaggiInstance.id}">${fieldValue(bean: villaggiInstance, field: "sottodistretto")}</g:link></td>
		
		<td><g:link action="show"
					id="${villaggiInstance.id}">${fieldValue(bean: villaggiInstance, field: "popolazione")}</g:link></td>
		
		<td><g:link action="show"
					id="${villaggiInstance.id}">${fieldValue(bean: villaggiInstance, field: "localita")}</g:link></td>
		
		<td><g:link action="show"
					id="${villaggiInstance.id}">${fieldValue(bean: villaggiInstance, field: "latitudine")}</g:link></td>
		
		<td><g:link action="show"
					id="${villaggiInstance.id}">${fieldValue(bean: villaggiInstance, field: "longitudine")}</g:link></td>
		
	  </tr>
	</g:each>
	</tbody>
  </table>

  <div class="pagination">
	<g:paginate total="${villaggiInstanceTotal}"/>
  </div>
</div>
</body>
</html>
