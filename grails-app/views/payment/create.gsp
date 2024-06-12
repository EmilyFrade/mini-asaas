<%@ page import="com.mini.asaas.payment.BillingType" %>
<!doctype html>
<html>
    <head>
        <meta name="layout" content="main"/>
        <title>Criar cobrança</title>
    </head>

    <body>
        <atlas-page class="js-atlas-page">
            <atlas-page-header page-name="Dados da cobrança" slot="header"></atlas-page-header>

            <atlas-page-content slot="content" class="js-atlas-content">
                <g:if test="${flash.message}">
                    <atlas-alert type="${flash.status}" message="${flash.message}"></atlas-alert>
                </g:if>

                <atlas-form class="form" method="POST" action="${createLink(controller: "payment", action: "save")}">
                    <atlas-grid>
                        <atlas-row>
                            <atlas-col lg="6">
                                <atlas-select
                                        label="Cliente"
                                        placeholder="Selecione um cliente"
                                        name="customerId"
                                        value="${params.customerId}"
                                        required>
                                    <g:each in="${customerList}" var="customer">
                                        <atlas-option label="${customer.name}" value="${customer.id}"></atlas-option>
                                    </g:each>
                                </atlas-select>
                            </atlas-col>
                            <atlas-col lg="6">
                                <atlas-select
                                        label="Pagador"
                                        placeholder="Selecione um pagador"
                                        name="payerId"
                                        value="${params.payerId}"
                                        required>
                                    <g:each in="${payerList}" var="payer">
                                        <atlas-option label="${payer.name}" value="${payer.id}"></atlas-option>
                                    </g:each>
                                </atlas-select>
                            </atlas-col>
                        </atlas-row>

                        <atlas-row>
                            <atlas-col lg="2">
                                <atlas-money
                                        label="Valor da cobrança"
                                        name="value"
                                        id="value"
                                        value="${params.value}"
                                        required>
                                </atlas-money>
                            </atlas-col>
                            <atlas-col lg="10">
                                <atlas-input
                                        label="Descrição"
                                        type="text"
                                        name="description"
                                        value="${params.description}">
                                </atlas-input>
                            </atlas-col>
                        </atlas-row>

                        <atlas-row>
                            <atlas-col lg="6">
                                <atlas-datepicker
                                        label="Data de vencimento"
                                        value="${params.dueDate}"
                                        name="dueDate"
                                        required>
                                </atlas-datepicker>
                            </atlas-col>
                            <atlas-col lg="6">
                                <atlas-select
                                        label="Forma de pagamento"
                                        placeholder="Selecione uma opção"
                                        name="billingType"
                                        value="${params.billingType}"
                                        required>
                                    <g:each in="${BillingType.values()}" var="type">
                                        <atlas-option label="${type.getLabel()}" value="${type.name()}"></atlas-option>
                                    </g:each>
                                </atlas-select>
                            </atlas-col>
                        </atlas-row>
                    </atlas-grid>

                    <atlas-button submit="true" description="Finalizar"></atlas-button>
                </atlas-form>
            </atlas-page-content>
        </atlas-page>
    </body>
</html>
