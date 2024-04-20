<#macro localdatetimemacro fecha="">
<#if fecha?has_content>
${fecha?datetime?string('HH:mm')}
<#else>
-
</#if>
</#macro>