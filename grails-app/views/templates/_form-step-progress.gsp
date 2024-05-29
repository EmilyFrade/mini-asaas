<%
    // Componente responsável por renderizar uma barra de progresso com checkpoints
    
    // @param startStep - Inteiro que representa a etapa inicial do formulário
    // @param totalSteps - Inteiro que representa o total de etapas do formulário
    // @param formId - String que representa o id do formulário a ser observado por este componente
%>

<asset:stylesheet src="form-step-progress.css" />

<%
    def startProgressBarValue = ((startStep - 1) / (totalSteps - 1)) * 100
%>

<section class="progress-area" data-form-id="${formId}">
    <div class="progress">
        <g:each in="${1..totalSteps}" var="step">
            <div class="progress-checkpoint" ${step <= startStep ? 'data-active=true' : ''}></div>
        </g:each>
        <progress class="progress-bar" value="${startProgressBarValue}" max="100"></progress>
    </div>
</section>

<asset:javascript src="form-step-progress-control.js" />