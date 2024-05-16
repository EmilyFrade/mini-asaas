<html>
<head>
  <title>Pagadores deletados</title>
  <meta name="layout" content="main">
</head>

<body page-title="Pagadores deletados">
<atlas-panel>
  <g:if test="${ payerList }">
    <atlas-table has-actions>
      <atlas-table-header slot="header">
        <atlas-table-col>
          Nome
        </atlas-table-col>
        <atlas-table-col>
          E-mail
        </atlas-table-col>
      </atlas-table-header>

      <atlas-table-body slot="body">
        <g:each var="payer" in="${ payerList }">
          <atlas-table-row href="${createLink(controller: "payer", action: "show", id: payer.id)}">
            <atlas-table-col>
              ${payer.name}
            </atlas-table-col>
            <atlas-table-col>
              ${payer.email}
            </atlas-table-col>

            <atlas-table-col>
              <atlas-button-group>
                <atlas-button icon="pencil" type="ghost" size="sm"></atlas-button>
                <atlas-button icon="refresh" type="ghost" size="sm" theme="danger"
                              href="${createLink(controller: "payer", action: "deleteRestore", params: [id: payer.id])}"></atlas-button>
              </atlas-button-group>
            </atlas-table-col>
          </atlas-table-row>
        </g:each>
      </atlas-table-body>
    </atlas-table>
  </g:if>
  <g:else>
    <atlas-empty-state
            illustration="schedule-user-avatar"
            header="Sem pagadores deletados">
            Aqui você pode vizualizar os pagadores que foram deletados.
      <atlas-button
              description="Lista de pagadores"
              href="${createLink(controller: "payer", action: "index")}"
              slot="button"
      ></atlas-button>
    </atlas-empty-state>
  </g:else>
</atlas-panel>
</body>
</html>