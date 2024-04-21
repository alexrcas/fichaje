<#macro localdatemacro fecha="">
<#if fecha?has_content>
${fecha?date?string('dd/MM')}
<#else>
-
</#if>
</#macro>