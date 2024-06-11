<%@ page import="com.mini.asaas.utils.StringUtils; com.mini.asaas.utils.CpfCnpjUtils; com.mini.asaas.utils.DateFormatUtils" %>
<html>
    <head>
        <title>Recibo de pagamento</title>
        <meta name="layout" content="basic">

        <asset:stylesheet src="auth/login.css"/>
        <asset:stylesheet src="payment/receipt.css"/>
    </head>

    <body>
        <header class="header">
            <asset:image
                    class="header--logo"
                    src="asaas-white.svg"
                    alt="Logomarca Asaas"
                    title="Asaas Gestão Financeira"/>
        </header>

        <atlas-form-panel header="Comprovante de pagamento" class="container">
            <atlas-illustration name="bank" size="xs"></atlas-illustration>
            <h2>R$${StringUtils.fromBigDecimal(payment.value)}</h2>

            <div class="details">
                <h3>Sobre a transação:</h3>
                <p>Identificador: ${payment.publicId}</p>
                <p>Data da transação: ${DateFormatUtils.formatWithTime(payment.paymentDate)}</p>
                <p>Descrição: ${payment.description}</p>
                <p>Forma de pagamento: ${payment.billingType.getLabel()}</p>

                <h3>Quem pagou:</h3>
                <p>Nome: ${payment.payer.name}</p>
                <p>CPF/CNPJ: ${CpfCnpjUtils.formatCpfCnpj(payment.payer.cpfCnpj)}</p>

                <h3>Quem recebeu:</h3>
                <p>Nome: ${payment.customer.name}</p>
                <p>CPF/CNPJ: ${CpfCnpjUtils.formatCpfCnpj(payment.customer.cpfCnpj)}</p>
                <p>Instituição: Mini Asaas S.A.</p>
            </div>
        </atlas-form-panel>
    </body>
</html>
