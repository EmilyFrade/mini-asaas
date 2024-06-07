<%@ page import="com.mini.asaas.utils.DateFormatUtils; com.mini.asaas.utils.StringUtils" %>
<html>
    <head>
        <title>Minhas Cobranças</title>
        <meta name="layout" content="main">
    </head>

    <body page-title="Minhas Cobranças">
        <atlas-panel>
            <g:if test="${paymentList}">
                <atlas-toolbar>
                    <atlas-button
                            icon="plus"
                            description="Criar cobrança"
                            href="${createLink(controller: "payment", action: "create")}"
                            slot="actions">
                    </atlas-button>
                </atlas-toolbar>
                <atlas-table has-actions>
                    <atlas-table-header slot="header">
                        <atlas-table-col>Nome do pagador</atlas-table-col>
                        <atlas-table-col>Valor</atlas-table-col>
                        <atlas-table-col>Forma de pagamento</atlas-table-col>
                        <atlas-table-col>Data de vencimento</atlas-table-col>
                        <atlas-table-col>Status</atlas-table-col>
                    </atlas-table-header>

                    <atlas-table-body slot="body">
                        <g:each var="payment" in="${paymentList}">
                            <atlas-table-row href="${createLink(controller: "payment", action: "show", id: payment.id)}">
                                <atlas-table-col>${payment.payer.name}</atlas-table-col>
                                <atlas-table-col>R$${StringUtils.fromBigDecimal(payment.value)}</atlas-table-col>
                                <atlas-table-col>${payment.billingType.getLabel()}</atlas-table-col>
                                <atlas-table-col>${DateFormatUtils.format(payment.dueDate)}</atlas-table-col>
                                <atlas-table-col>
                                    <atlas-badge text="${payment.status.getLabel()}" theme="${payment.status.theme}"></atlas-badge>
                                </atlas-table-col>
                            </atlas-table-row>
                        </g:each>
                    </atlas-table-body>
                </atlas-table>
            </g:if>
            <g:else>
                <atlas-empty-state
                        illustration="flow-money-coins"
                        header="Sem cobranças cadastradas">
                        Comece a cobrar seus clientes com o Mini Asaas.
                    <atlas-button
                            icon="plus"
                            description="Criar cobrança"
                            href="${createLink(controller: "payment", action: "create")}"
                            slot="button"></atlas-button>
                </atlas-empty-state>
            </g:else>
        </atlas-panel>
    </body>
</html>