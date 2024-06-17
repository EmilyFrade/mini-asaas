<html>
	<head>
		<title>Mini Asaas - Resumo</title>
		<meta name="layout" content="main">
		<asset:stylesheet src="dashboard/index" />
	</head>

	<body>
		<atlas-heading size="h2" muted>Resumo</atlas-heading>
		<atlas-grid>
			<atlas-row>
				<atlas-col lg="4" md="2">
					<div class="summary-card">
						<header class="summary-card-header">
							<atlas-icon name="user" size="2x"></atlas-icon>
							<atlas-heading size="h6" white>Clientes</atlas-heading>
						</header>
						<ul class="summary-card-body">
							<li class="summary-card-item">
								<atlas-heading
									size="h2"
									class="summary-card-item-value">
									${payers.count.inDay}
								</atlas-heading>
								<atlas-text class="summary-card-item-title">Em Dia</atlas-text>
							</li>
							<li class="summary-card-item">
								<atlas-heading
									size="h2"
									class="summary-card-item-value">
									${payers.count.delinquent}
								</atlas-heading>
								<atlas-text class="summary-card-item-title">Inadimplentes</atlas-text>
							</li>
						</ul>
					</div>
				</atlas-col>
				<atlas-col lg="4" md="2">
					<div class="summary-card">
						<header class="summary-card-header">
							<atlas-icon name="money" size="2x"></atlas-icon>
							<atlas-heading size="h6" white>Cobranças</atlas-heading>
						</header>
						<ul class="summary-card-body">
							<li class="summary-card-item">
								<atlas-heading
									size="h2"
									class="summary-card-item-value">
									${payments.count.total}
								</atlas-heading>
								<atlas-text class="summary-card-item-title">Total</atlas-text>
							</li>
							<li class="summary-card-item">
								<atlas-heading
									size="h2"
									class="summary-card-item-value">
									${payments.count.overdue}
								</atlas-heading>
								<atlas-text class="summary-card-item-title">Vencidas</atlas-text>
							</li>
							<li class="summary-card-item">
								<atlas-heading
									size="h2"
									class="summary-card-item-value">
									${payments.count.received}
								</atlas-heading>
								<atlas-text class="summary-card-item-title">Recebidas</atlas-text>
							</li>
						</ul>
					</div>
				</atlas-col>
				<atlas-col lg="4" md="2">
					<div class="summary-card">
						<header class="summary-card-header">
							<atlas-icon name="monitor-chart-up" size="2x"></atlas-icon>
							<atlas-heading size="h6" white>Faturamento</atlas-heading>
						</header>
						<ul class="summary-card-body">
							<li class="summary-card-item">
								<atlas-heading
									size="h2"
									class="summary-card-item-value">
									${g.formatNumber(number: payments.revenue.expected, type: "currency", currencyCode: "BRL")}
								</atlas-heading>
								<atlas-text class="summary-card-item-title">Previsto</atlas-text>
							</li>
							<li class="summary-card-item">
								<atlas-heading
									size="h2"
									class="summary-card-item-value">
									${g.formatNumber(number: payments.revenue.received, type: "currency", currencyCode: "BRL")}
								</atlas-heading>
								<atlas-text class="summary-card-item-title">Recebidos</atlas-text>
							</li>
						</ul>
					</div>
				</atlas-col>
			</atlas-row>
		</atlas-grid>
	</body>
</html>