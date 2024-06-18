<%@ page import="com.mini.asaas.user.RoleAuthority" %>
<!doctype html>
<html>
    <head>
        <meta name="layout" content="main"/>
        <title>Adicionar usuário</title>
    </head>

    <body>
        <atlas-panel header="Adicionar usuário">
            <g:if test="${flash.message}">
                <atlas-alert type="${flash.status}" message="${flash.message}"></atlas-alert>
            </g:if>

            <atlas-form class="form" method="POST" action="${createLink(controller: "user", action: "save")}">
                <atlas-grid>
                    <atlas-row>
                        <atlas-col lg="6">
                            <atlas-input
                                    label="Nome"
                                    name="name"
                                    value="${params.name}"
                                    required >
                            </atlas-input>
                        </atlas-col>
                        <atlas-col lg="6">
                            <atlas-input
                                    label="Email"
                                    name="email"
                                    value="${params.email}"
                                    required >
                            </atlas-input>
                        </atlas-col>
                    </atlas-row>

                    <atlas-row>
                        <atlas-col lg="6">
                            <atlas-select
                                    label="Qual é a função do usuário?"
                                    placeholder="Selecione uma função"
                                    name="roleAuthority"
                                    value="${params.roleAuthority}"
                                    required>
                                <g:each in="${RoleAuthority.values()}" var="roleAuthority">
                                    <atlas-option label="${roleAuthority.getLabel()}" value="${roleAuthority.name()}"></atlas-option>
                                </g:each>
                            </atlas-select>
                        </atlas-col>
                        <atlas-col lg="6">
                            <atlas-password-input
                                    name="password"
                                    label="Senha"
                                    value="${params.password}"
                                    required>
                            </atlas-password-input>
                        </atlas-col>
                    </atlas-row>
                </atlas-grid>

                <atlas-button submit="true" description="Cadastrar" type="filled"></atlas-button>
            </atlas-form>
        </atlas-panel>
    </body>
</html>