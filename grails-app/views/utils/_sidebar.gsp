<atlas-sidebar slot="sidebar">
    <atlas-sidebar-menu slot="body">
        <atlas-sidebar-menu-item
                icon="users"
                value="clients-group"
                text="Pagadores"
                ${ controllerName == "payer" ? "active" : "" }>
            <atlas-sidebar-menu-item
                    icon="users"
                    value="clients-group"
                    text="Lista de Pagadores"
                    href="${createLink(controller: "payer", action: "index")}"
                    ${ controllerName == "payer" && actionName == "index" ? "active" : "" }
            ></atlas-sidebar-menu-item>
        </atlas-sidebar-menu-item>
    </atlas-sidebar-menu>
</atlas-sidebar>