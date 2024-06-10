<%@ page import="com.mini.asaas.atlas.AtlasTheme; com.mini.asaas.atlas.AtlasIcon" %>
<style>
.notification-item {
    position: relative;
}

.notification-item atlas-button {
    position: absolute;
    top: 0;
    right: 0;
    z-index: 1;
}

.notification-item atlas-notification-card[data-read="true"] {
    background-color: gray;
}
</style>

<div class="notification-item">
    <atlas-notification-card
        id="${notification.id}"
        icon="${icon.getValue()}"
        overlay-icon="${overlayIcon.getValue()}"
        overlay-theme="${overlayTheme.getValue()}"
        header="${notification.title}"
        description="${notification.message}"
        link-path="${notification.link.getPath()}"
        link-text="Ver mais"
        data-read="${notification.isRead}">
    </atlas-notification-card>
</div>