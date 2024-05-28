<!doctype html>
<html lang="pt-br">
  <head>
    <meta name="layout" content="basic" />
    <title>Mini Asaas | Criar Conta</title>
    <asset:stylesheet src="customer/create.css" />
  </head>

  <body>
    <div class="create-customer-top">
      <g:render template="/templates/banner" />
      <g:render template="/templates/customer/create/progress-bar" />
    </div>

    <div class="main-container">
      <main class="main">
        <div>
          <g:if test="${flash.status}">
            <atlas-alert type="${flash.status}" message="${flash.message}"></atlas-alert>
          </g:if>
        </div>
        <atlas-form
          id="create-customer-form"
          method="post"
          action="${createLink(controller: "customer", action: "postCreate")}">
          <g:render template="/templates/customer/create/step-one" />
          <g:render template="/templates/customer/create/step-two" />
        </atlas-form>
      </main>
    </div>


    <asset:javascript src="customer/create/create.js" />
  </body>
</html>