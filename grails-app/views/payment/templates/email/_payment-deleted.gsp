<%@ page import="com.mini.asaas.utils.CpfCnpjUtils" %>
<!doctype html>
<html lang="pt-br">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<meta http-equiv="X-UA-Compatible" content="IE=edge" />
		<meta name="viewport" content="width=device-width, initial-scale=1" />
		<title>Cobrança Cancelada - Mini Asaas</title>

		<style>
        * {
            margin: 0;
            padding: 0;
            box-sizing: border-box;
        }

        body, html {
            height: 100%;
        }

        .container {
            width: 100%;
            max-width: 600px;
            margin: 0 auto;
            padding: 20px;
        }

        .header {
            height: 54px;
            background-color: #0030b9;
            display: flex;
            justify-content: center;
            align-items: center;
        }

        .header--logo {
            height: 36px;
            margin: 0.25rem auto 0;
            object-fit: contain;
        }

        .main {
            padding: 1rem;
        }

        .main h2, .main p {
            margin-bottom: 1rem;
        }

        .btn-link {
            display: inline-block;
            padding: 10px 20px;
            background-color: #0030b9;
            color: #fff !important;
            text-decoration: none;
            border-radius: 5px;
            margin-top: 2rem;
        }

        .btn-link:hover {
            background-color: #0020b9;
        }

        .details-group {
            margin-top: 2rem;
        }

        .details-group h3 {
            margin-bottom: 1rem;
        }

        .details-group > ul li {
            list-style: none !important;
        }

        .details-group > ul li:not(:first-child) {
            margin-top: 0.5rem;
        }

        .helper {
            margin-top: 2rem;
            font-size: 0.8rem;
            color: #666;
            font-style: italic;
        }

        footer {
            margin-top: 2rem;
            text-align: center;
        }
		</style>
	</head>

	<body>
		<div class="container">
			<header class="header">
				<img
					class="header--logo"
					src="https://ci3.googleusercontent.com/meips/ADKq_Na3rzr-X1iaRzx_vziBtmsyPBx2XQuka7kT_QAZiuufZ1a13igtStihxTU84j6MvdLhEBJCNfpJuFVgsjSz5mCpuu1HeVRcwnro155iKLwX9qup-Q7bO2XZY6jvkLMK4ZX7rhIh4DkUEEx7IKia5A=s0-d-e1-ft#https://www.asaas.com/assets/emails/asaas-name-white-11af35809d5ae4dcd235671ff4bf79e1.png"
					alt="Mini Asaas" loading="lazy" />
			</header>
			<div class="main">
				<h2>Olá, ${payment.payer.name}!</h2>

				<div>
					<p>
						Uma de suas cobranças foi cancelada. Confira os detalhes abaixo e entre em contato com o vendedor para mais informações.
					</p>

					<section class="details-group">
						<h3>Detalhes da Cobrança</h3>
						<ul>
							<li>
								<strong>Valor:</strong> ${formatNumber(number: payment.value, type: "currency", currencyCode: "BRL")}
							</li>
							<li>
								<strong>Vencimento:</strong>${formatDate(date: payment.dueDate, format: "dd/MM/yyyy")}
							</li>
							<li><strong>Descrição:</strong> ${payment.description}</li>
							<li><strong>Forma de Pagamento:</strong> ${payment.billingType.getLabel()}</li>
						</ul>
					</section>

					<section class="details-group">
						<h3>Detalhes do Cobrador</h3>
						<ul>
							<li><strong>Nome:</strong> ${payment.customer.name}</li>
							<li><strong>E-mail:</strong> ${payment.customer.email}</li>
							<li>
								<strong>${payment.customer.personType.isNatural() ? "CPF" : "CNPJ"}:</strong> ${CpfCnpjUtils.formatCpfCnpj(payment.customer.cpfCnpj)}
							</li>
						</ul>
					</section>

					<section class="details-group">
						<h3>Detalhes do Vendedor</h3>
						<ul>
							<li><strong>Nome:</strong> ${user.name}</li>
							<li><strong>E-mail:</strong> ${user.email}</li>
						</ul>
					</section>
				</div>

				<p class="helper">Não reconhece essa cobrança? Entre em contato com o vendedor para mais informações.</p>
			</div>
			<footer>
				<p>Enviado por Equipe Mini Asaas 💙</p>
			</footer>
		</div>
	</body>
</html>