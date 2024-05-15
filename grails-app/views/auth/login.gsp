<!doctype html>
<html>
<head>
  <meta name="layout" content="main" />
  <title>Mini Asaas | Login</title>
</head>
<body>
  <h1>Login</h1>
  <g:if test="${flash.message}">
    <span style="color: red">${flash.message}</span>
  </g:if>
  <br />
  <br />
  <form method="post" action="${createLink(controller: "auth", action: "doLogin")}">
    <label for="email">Email</label>
    <input type="email" id="email" name="email" value="${params.email}" autocomplete="off" required />

    <label for="password">Senha</label>
    <input type="password" id="password" name="password" value="${params.password}" autocomplete="off" required />

    <button type="submit">Acessar conta</button>
  </form>
</body>