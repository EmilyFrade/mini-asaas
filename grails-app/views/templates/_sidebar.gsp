<atlas-sidebar slot="sidebar">
    <atlas-sidebar-header slot="header">
        <atlas-button
                block=""
                icon="plus"
                description="Criar cobrança"
                href="${createLink(controller: "payment", action: "create")}"
                slot="actions">
        </atlas-button>
    </atlas-sidebar-header>

    <atlas-sidebar-menu slot="body">
        <atlas-sidebar-menu-item
                icon="users"
                value="clients-group"
                text="Pagadores"
            ${ controllerName == "payer" ? "active" : "" }>
            <atlas-sidebar-menu-item
                    icon="user"
                    text="Cadastrar pagador"
                    href="${createLink(controller: "payer", action: "create")}"
                ${ controllerName == "payer" && actionName == "create" ? "active" : "" }
            ></atlas-sidebar-menu-item>
            <atlas-sidebar-menu-item
                    icon="users"
                    value="clients-group"
                    text="Pagadores ativos"
                    href="${createLink(controller: "payer", action: "index")}"
                ${ controllerName == "payer" && actionName == "index" ? "active" : "" }
            ></atlas-sidebar-menu-item>
            <atlas-sidebar-menu-item
                    icon="users"
                    value="clients-group"
                    text="Pagadores inativos"
                    href="${createLink(controller: "payer", action: "restore")}"
                ${ controllerName == "payer" && actionName == "restore" ? "active" : "" }
            ></atlas-sidebar-menu-item>
        </atlas-sidebar-menu-item>
        <atlas-sidebar-menu-item
            icon="cog"
            value="profile-group"
            text="Meu Perfil"
            ${controllerName == "user" ? "active" : ""}>
            <atlas-sidebar-menu-item
              icon="user"
              text="Ver Perfil"
              href="${createLink(controller: "user", action: "show")}"
              ${controllerName == "user" && actionName == "show" ? "active" : ""}>
            </atlas-sidebar-menu-item>
          </atlas-sidebar-menu-item>
      </atlas-sidebar-menu>
</atlas-sidebar>