<#import "themelayout.ftl" as base>
<@base.themelayout active="fichajes">

<div class="container mb-4">

    <#list fichajes as fichaje>
        ${fichaje.created} - ${fichaje.tipoFichaje}
    </#list>

    <#list validaciones as validacion>
        ${validacion}
    </#list>

</div>


</@base.themelayout>