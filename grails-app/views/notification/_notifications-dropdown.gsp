<%@ page import="com.mini.asaas.notification.Notification" %>

<style>
atlas-icon-button {
    position: absolute;
    right: 1rem;
    top: 1rem;
}

#notification-list atlas-empty-state {
    padding: 1rem 0;
}
</style>

<atlas-icon-button
	icon="bell"
	class="js-notification-bell-button"
	size="3x"
	data-atlas-dropdown="notificationDropdown"
	badge-number="${count ?: 0}"
	hoverable=""
	${count > 0 ? "show-badge" : ""}
	tooltip-placement="bottom"
	tooltip-trigger="hover focus"
	active=""
	aria-expanded="true">
</atlas-icon-button>
<atlas-dropdown
	class="js-notification-dropdown"
	loading-text="Estamos carregando os alertas"
	id="notificationDropdown"
	header="Alertas"
	max-height="400"
	width="400"
	no-gap=""
	auto-close=""
	auto-close-trigger="outside">
	<div id="notification-area">
		<div id="notification-list" class="js-notification-list" data-url="/notification/list">
			<g:if test="${count}">
				<g:each in="${notifications}" var="notification">
					<notificationTagLib:card notification="${notification}" />
				</g:each>
			</g:if>
			<g:else>
				<atlas-empty-state small="" icon="celebrate" header="Sem notificações!"></atlas-empty-state>
			</g:else>
		</div>
	</div>
	<atlas-notification-card
		hidden=""
		class="js-error-list-alerts-item hide"
		icon="alert-triangle"
		header="Não foi possível carregar os alertas."
		description="Tente novamente.">
	</atlas-notification-card>
</atlas-dropdown>