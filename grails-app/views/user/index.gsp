<html>
    <head>
        <title>Usuários do sistema</title>
        <meta name="layout" content="main">
    </head>

    <body>
        <atlas-panel>
            <g:if test="${flash.message}">
                <atlas-alert type="${flash.status}" message="${flash.message}"></atlas-alert>
            </g:if>

            <g:if test="${userList}">
                <atlas-toolbar>
                    <atlas-button
                            icon="plus"
                            description="Adicionar usuário"
                            href="${createLink(controller: "user", action: "create")}"
                            slot="actions">
                    </atlas-button>
                </atlas-toolbar>

                <atlas-table has-actions>
                    <atlas-table-header slot="header">
                        <atlas-table-col>Login</atlas-table-col>
                        <atlas-table-col>Nome</atlas-table-col>
                        <atlas-table-col>Papel no Mini Asaas</atlas-table-col>
                    </atlas-table-header>

                    <atlas-table-body slot="body">
                        <g:each var="user" in="${userList}">
                            <atlas-table-row>
                                <atlas-table-col>${user.email}</atlas-table-col>
                                <atlas-table-col>${user.name}</atlas-table-col>
                                <atlas-table-col>${user.roleAuthority.getLabel()}</atlas-table-col>
                                <atlas-table-col>
                                    <g:if test="${!user.deleted}">
                                        <atlas-button-group>
                                            <atlas-button icon="pencil" type="ghost" size="sm"
                                                          href="${createLink(controller: "user", action: "show", id: user.id)}"></atlas-button>
                                            <atlas-button icon="trash" type="ghost" size="sm" theme="danger"
                                                          href="${createLink(controller: "user", action: "delete", params: [id: user.id])}"></atlas-button>
                                        </atlas-button-group>
                                    </g:if>
                                    <g:else>
                                        <atlas-button icon="refresh" type="ghost" size="sm" theme="danger"
                                                      href="${createLink(controller: "user", action: "restore", params: [id: user.id])}"></atlas-button>
                                    </g:else>
                                </atlas-table-col>
                            </atlas-table-row>
                        </g:each>
                    </atlas-table-body>
                </atlas-table>
            </g:if>
            <g:else>
                <atlas-empty-state
                        illustration="schedule-user-avatar"
                        header="Sem usuários cadastrados">
                        Aqui você pode cadastrar os usuários que poderão utilizar o sistema.
                    <atlas-button
                            icon="plus"
                            description="Adicionar usuário"
                            href="${createLink(controller: "user", action: "create")}"
                            slot="button"></atlas-button>
                </atlas-empty-state>
            </g:else>
        </atlas-panel>
    </body>
</html>