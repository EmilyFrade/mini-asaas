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
                                        name="originalValue"
                                        id="originalValue"
                                        value="${params.originalValue}"
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

                        <atlas-row>
                            <atlas-col lg="6">
                                <atlas-percentage
                                        decimal-precision="0"
                                        label="Valor percentual da multa"
                                        name="interestPercentual"
                                        id="interestPercentual"
                                        value="${params.interestPercentual}">
                                </atlas-percentage>
                                <atlas-caption muted="">A multa será somada ao valor da parcela caso o pagamento seja feito após a data do vencimento.</atlas-caption>
                            </atlas-col>
                            <atlas-col lg="6">
                                <atlas-percentage
                                        decimal-precision="0"
                                        label="Valor percentual do desconto"
                                        name="discountPercentual"
                                        id="discountPercentual"
                                        value="${params.discountPercentual}">
                                </atlas-percentage>
                                <atlas-caption muted="">Conceda desconto para incentivar seu cliente a realizar o pagamento antes do vencimento. </atlas-caption>
                            </atlas-col>
                        </atlas-row>
                    </atlas-grid>

                    <atlas-button id="calculateButton" description="Calcular"></atlas-button>

                    <atlas-grid>
                        <atlas-row>
                            <atlas-col lg="4">
                                <atlas-money
                                        label="Valor da multa"
                                        name="interestValue"
                                        id="interestValue"
                                        value="${params.interestValue}"
                                        readonly
                                        required>
                                </atlas-money>
                            </atlas-col>
                            <atlas-col lg="4">
                                <atlas-money
                                        label="Valor do desconto"
                                        name="discountValue"
                                        id="discountValue"
                                        value="${params.discountValue}"
                                        readonly
                                        required>
                                </atlas-money>
                            </atlas-col>
                            <atlas-col lg="4">
                                <atlas-money
                                        label="Valor final"
                                        name="netValue"
                                        id="netValue"
                                        value="${params.netValue}"
                                        readonly
                                        required>
                                </atlas-money>
                            </atlas-col>
                        </atlas-row>
                    </atlas-grid>

                    <atlas-button submit="true" description="Finalizar"></atlas-button>
                </atlas-form>
            </atlas-page-content>

        </atlas-page>

        <asset:javascript src="calculate-values-payment.js" />
    </body>
</html>
