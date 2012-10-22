<%--Created by Algos s.r.l.--%>
<%--Date: mag 2012--%>
<%--Questo file è stato installato dal plugin AlgosBase--%>
<%--Tipicamente NON verrà più sovrascritto dalle successive release del plugin--%>
<%--in quanto POTREBBE essere personalizzato in questa applicazione--%>
<%--Se vuoi che le prossime release del plugin sovrascrivano questo file,--%>
<%--perdendo tutte le modifiche effettuate qui,--%>
<%--regola a true il flag di controllo flagOverwrite©--%>
<%--flagOverwrite = false--%>

<!doctype html>
<html>
<head>
    <meta name="layout" content="main"/>
    <title>Welcome to Grails</title>
    <style type="text/css" media="screen">
    #status {
        background-color: #eee;
        border: .2em solid #fff;
        margin: 2em 2em 1em;
        padding: 1em;
        width: 12em;
        float: left;
        -moz-box-shadow: 0px 0px 1.25em #ccc;
        -webkit-box-shadow: 0px 0px 1.25em #ccc;
        box-shadow: 0px 0px 1.25em #ccc;
        -moz-border-radius: 0.6em;
        -webkit-border-radius: 0.6em;
        border-radius: 0.6em;
    }

    .ie6 #status {
        display: inline; /* float double margin fix http://www.positioniseverything.net/explorer/doubled-margin.html */
    }

    #status ul {
        font-size: 0.9em;
        list-style-type: none;
        margin-bottom: 0.6em;
        padding: 0;
    }

    #status li {
        line-height: 1.3;
    }

    #status h1 {
        text-transform: uppercase;
        font-size: 1.1em;
        margin: 0 0 0.3em;
    }

    #page-body {
        margin: 2em 1em 1.25em 18em;
    }

    h2 {
        margin-top: 1em;
        margin-bottom: 0.3em;
        font-size: 1em;
    }

    p {
        line-height: 1.5;
        margin: 0.25em 0;
    }

    #controller-list ul {
        list-style-position: inside;
    }

    #controller-list li {
        line-height: 1.3;
        list-style-position: inside;
        margin: 0.25em 0;
    }

    @media screen and (max-width: 480px) {
        #status {
            display: none;
        }

        #page-body {
            margin: 0 1em 1em;
        }

        #page-body h1 {
            margin-top: 0;
        }
    }
    </style>
</head>

<body>

<a href="#page-body" class="skip"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>


<div id="page-body" role="complementary">
    <h1>Welcome to <g:meta name="app.name"/></h1>

    <div class="nav" role="navigation">
        <a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a>
        <g:link class="edit" action="importaTutto">Importa tutto da capo</g:link>
        <g:link class="edit" action="villaggiProva">Crea villaggio (prova)</g:link>
        <g:link class="edit" action="testVillaggi">Controlla le pagine dei villaggi esistenti (test)</g:link>
        <g:link class="edit" action="allVillaggi">Crea tutti i villaggi (definitivo)</g:link>
        <g:link class="edit" action="localitaProva">Crea localita (prova)</g:link>
        <g:link class="edit" action="testLocalita">Controlla le pagine delle località esistenti (test)</g:link>
        <g:link class="edit" action="allLocalita">Crea tutte le località (definitivo)</g:link>
        <g:link class="edit" action="liste">Lista dei villaggi/località per distretto</g:link>
    </div>

</div>

</body>
</html>
