<%@ page import="com.mini.asaas.utils.StringUtils; com.mini.asaas.payment.BillingType; com.mini.asaas.utils.DateFormatUtils" %>
<html>
    <head>
        <title>Detalhes da cobrança</title>
        <meta name="layout" content="main">
    </head>

    <body>
        <g:if test="${flash.message}">
            <atlas-alert type="${flash.status}" message="${flash.message}"></atlas-alert>
        </g:if>

        <atlas-form-panel header="Detalhes da cobrança" description="" submit-button-label=""
                          action="${createLink(controller: "payment", action: "update", params: [id: payment.id])}"
                          method="post">
            <atlas-input
                    value="${payment.id}"
                    name="id"
                    hidden>
            </atlas-input>

            <g:if test="${!payment.deleted}">
                <atlas-button slot="actions" description="Editar" icon="pencil" data-panel-start-editing="true"></atlas-button>
                <atlas-button slot="actions" description="Excluir" icon="trash" theme="danger"
                              href="${createLink(controller: "payment", action: "delete", params: [id: payment.id])}">
                </atlas-button>
            </g:if>
            <g:else>
                <atlas-button slot="actions" description="Restaurar" icon="refresh" theme="danger"
                              href="${createLink(controller: "payment", action: "restore", params: [id: payment.id])}">
                </atlas-button>
            </g:else>

            <atlas-grid>
                <atlas-row>
                    <atlas-col lg="6">
                        <atlas-input
                                label="Cliente"
                                name="customerId"
                                value="${payment.customerId}"
                                required
                                readonly>
                        </atlas-input>
                    </atlas-col>
                    <atlas-col lg="6">
                        <atlas-input
                                name="payerId"
                                value="${payment.payerId}"
                                hidden>
                        </atlas-input>
                        <atlas-input
                                label="Pagador"
                                value="${payment.payer.name}"
                                required
                                readonly>
                        </atlas-input>
                    </atlas-col>
                </atlas-row>

                <atlas-row>
                    <atlas-col lg="2">
                        <atlas-money
                                label="Valor da cobrança"
                                name="value"
                                id="value"
                                value="${StringUtils.fromBigDecimal(payment.value)}"
                                required>
                        </atlas-money>
                    </atlas-col>
                    <atlas-col lg="10">
                        <atlas-input
                                label="Descrição"
                                type="text"
                                name="description"
                                value="${payment.description}">
                        </atlas-input>
                    </atlas-col>
                </atlas-row>

                <atlas-row>
                    <atlas-col lg="6">
                        <atlas-datepicker
                                label="Data de vencimento"
                                value="${DateFormatUtils.format(payment.dueDate)}"
                                name="dueDate"
                                required>
                        </atlas-datepicker>
                    </atlas-col>
                    <atlas-col lg="6">
                        <atlas-select
                                label="Forma de pagamento"
                                placeholder="Selecione uma opção"
                                name="billingType"
                                value="${payment.billingType}"
                                required>
                            <g:each in="${BillingType.values()}" var="type">
                                <atlas-option label="${type.getLabel()}" value="${type.name()}"></atlas-option>
                            </g:each>
                        </atlas-select>
                    </atlas-col>
                </atlas-row>
            </atlas-grid>
        </atlas-form-panel>
    </body>
</html>