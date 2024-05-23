<#import "themelayoutAdmin.ftl" as base>
<@base.themelayout active="anulaciones">
<#include "localdatemacro.ftl">
<#include "localdatetimemacro.ftl">


<div class="row">



    <div class="list-group">

        <#list solicitudesAnulacion as solicitud>
        <a class="list-group-item list-group-item-action flex-column align-items-start p-3 p-sm-4 mb-1" href="#!">
            <div class="d-flex flex-column flex-sm-row justify-content-between mb-1 mb-md-0">
                <small class="text-600"><@localdatemacro solicitud.created /> <@localdatetimemacro solicitud.created /></small>
            </div>
            <p class="mb-1 mt-1">Solicitud de anulación de fichaje: <b>${solicitud.fichaje.tipoFichaje.getName()}</b> el día <@localdatemacro solicitud.fichaje.horaFichaje /> a las ${solicitud.fichaje.getHoraFichajeHHmm()}</p>
            <small class="text-600">${solicitud.usuario}</small>
        </a>
        </#list>


    </div>





</div>


<script>

</script>

</@base.themelayout>