<!doctype html>
<html lang="pt-br">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
    <title>
    <g:layoutTitle default="Mini Asaas"/>
    </title>
    <meta name="viewport" content="width=device-width, initial-scale=1"/>

    <asset:link rel="icon" href="logo.png" type="image/x-ico"/>
    <asset:stylesheet rel="stylesheet" type="text/css" href="global.css"></asset:stylesheet>

    <link
            rel="stylesheet"
            href="https://atlas.asaas.com/atlas.min.css"
            crossorigin="anonymous">
    <script
            defer
            src="https://atlas.asaas.com/atlas.min.js"
            crossorigin="anonymous"
    ></script>

    <g:layoutHead/>
</head>

<body>
    <atlas-screen>
        <g:render template="/utils/sidebar" />
        <atlas-page class="js-atlas-page">
            <atlas-page-header
                    slot="header"
                    page-name="${pageProperty(name: "body.page-title")}">
                <atlas-breadcrumb slot="breadcrumb"></atlas-breadcrumb>
            </atlas-page-header>

            <atlas-page-content slot="content" class="js-atlas-content">
                <g:layoutBody />
            </atlas-page-content>
        </atlas-page>
    </atlas-screen>
</body>
</html>

