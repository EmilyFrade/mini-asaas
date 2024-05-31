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