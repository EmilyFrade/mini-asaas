<!doctype html>
<html>
<head>
  <meta name="layout" content="main" />
  <title>Mini Asaas | Meu Perfil</title>
</head>
<body>
  <h1>Login</h1>
  <div>

    <label for="email">Email</label>
    <input
      type="email"
      id="email"
      name="email"
      value="${user.email}"
      readonly
      required
    />

    <label for="dateCreated">Cadastrado em: </label>
    <input
      type="text"
      id="dateCreated"
      name="dateCreated"
      value="${formatDate(date: user.dateCreated)}"
      readonly
      required
    />

    <label for="lastUpdated">Última atualização em: </label>
    <input
      type="text"
      id="lastUpdated"
      name="lastUpdated"
      value="${formatDate(date: user.lastUpdated)}"
      readonly
      required
    />

    <a href="/logout">Sair da Conta</a>
  </div>
</body>