<!doctype html>
<html>
<head>
    <meta name="layout" content="main"/>
    <title>Home</title>
</head>
<body>
    <atlas-sidebar slot="sidebar">
        <atlas-sidebar-menu slot="body">
            <atlas-sidebar-menu-item
                    icon="users"
                    value="clients-group"
                    text="Cadastrar pagador"
                    href="/payer/index"
                    active=""
            ></atlas-sidebar-menu-item>
        </atlas-sidebar-menu>
    </atlas-sidebar>

    <atlas-page class="js-atlas-page">
        <atlas-page-header slot="header" page-name="Detalhes do cliente">
            <atlas-breadcrumb slot="breadcrumb">
                <atlas-breadcrumb-item text="Detalhes do cliente" icon="home"></atlas-breadcrumb-item>
            </atlas-breadcrumb>
        </atlas-page-header>
        <atlas-page-content slot="content" class="js-atlas-content">
        </atlas-page-content>
    </atlas-page>
</body>
</html>

