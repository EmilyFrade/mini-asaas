<html>
<head>
  <title>Lista de pagadores</title>
  <meta name="layout" content="main">
</head>
<body page-title="Lista de pagadores">
<atlas-panel>
  <g:if test="${ payerList }">
    <atlas-toolbar>
      <atlas-button
              icon="plus"
              description="Adicionar pagador"
              href="${createLink(controller: "payer", action: "register")}"
              slot="actions"
      ></atlas-button>
      <atlas-button
              icon="refresh"
              theme="danger"
              description="Restaurar pagador"
              href="${createLink(controller: "payer", action: "restore")}"
              slot="actions"
      ></atlas-button>
    </atlas-toolbar>

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
                <atlas-button icon="trash" type="ghost" size="sm" theme="danger"
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
            header="Sem pagadores cadastrados">
            Aqui você pode cadastrar os pagadores que deseja utilizar em suas transações.
      <atlas-button
              icon="plus"
              description="Adicionar pagador"
              href="${createLink(controller: "payer", action: "register")}"
              slot="button"
      ></atlas-button>
    </atlas-empty-state>
  </g:else>
</atlas-panel>
</body>
</html>