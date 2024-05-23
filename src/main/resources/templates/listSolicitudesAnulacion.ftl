<#import "themelayoutAdmin.ftl" as base>
<@base.themelayout active="anulaciones">
<#include "localdatemacro.ftl">
<#include "localdatetimemacro.ftl">


<div class="row">



    <div class="list-group">

        <#list solicitudesAnulacion as solicitud>
        <a class="list-group-item list-group-item-action flex-column align-items-start p-3 p-sm-4 mb-1 cursor-pointer"
           onclick="showDetalleSolicitudAnulacion(${solicitud.fichaje.id})">
            <div class="d-flex flex-column flex-sm-row justify-content-between mb-1 mb-md-0">
                <small class="text-600"><@localdatemacro solicitud.created /> <@localdatetimemacro solicitud.created /></small>
            </div>
            <p class="mb-1 mt-1">Solicitud de anulación de fichaje: <b>${solicitud.fichaje.tipoFichaje.getName()}</b> el día <@localdatemacro solicitud.fichaje.horaFichaje /> a las ${solicitud.fichaje.getHoraFichajeHHmm()}</p>
            <small class="text-600">${solicitud.usuario}</small>
        </a>
        </#list>


    </div>

</div>

<div class="offcanvas offcanvas-end" id="offcanvas" tabindex="-1">
    <div class="offcanvas-header">
        <button class="btn-close text-reset" type="button" data-bs-dismiss="offcanvas" aria-label="Close"></button>
    </div>
    <div class="offcanvas-body" id="offcanvas-body">
    </div>
</div>


<script>

    const showDetalleSolicitudAnulacion = async (idFichaje) => {
        const response = await fetch('/admin/showDetalleSolicitudAnulacion/' + idFichaje);
        const html = await response.text();
        document.getElementById('offcanvas-body').innerHTML = html;

        const script = document.createElement('script');
        const offcanvasContent = document.querySelector('.modal-content');
        if (offcanvasContent.querySelector('script') == null) { return }
        script.innerHTML = offcanvasContent.querySelector('script').innerText;
        document.body.appendChild(script)

        const offcanvas = new bootstrap.Offcanvas(document.getElementById('offcanvas'));
        offcanvas.show();
    }

</script>

</@base.themelayout>