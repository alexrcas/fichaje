<#include "localdatetimemacro.ftl">

<div class="container">

    <h3>${dia.getFechaFormateada()}</h3>
    <h5 class="mt-2">arodriguez</h5>

    <#if !validacion.valid>
    <div class="alert alert-outline-warning d-flex align-items-center p-3 mt-3" role="alert">
        <span class="fas fa-info-circle text-warning fs-1 me-3"></span>
        <p class="mb-0 flex-1 small">${validacion.mensaje}</p>
    </div>
    </#if>

    <div class="timeline-basic mb-2 py-2">

        <#list fichajes as fichaje>
        <div class="timeline-item">
            <div class="row g-3">
                <div class="col-auto">
                    <div class="timeline-item-bar position-relative">
                        <div class="icon-item icon-item-md rounded-7 border border-translucent">
                            <#if fichaje.tipoFichaje == 'ENTRADA_JORNADA'><span class="fas fa-suitcase text-success text-opacity-75"></span></#if>
                            <#if fichaje.tipoFichaje == 'SALIDA_DESAYUNO'><span class="fas fa-coffee text-danger text-opacity-75"></span></#if>
                            <#if fichaje.tipoFichaje == 'ENTRADA_DESAYUNO'><span class="fas fa-coffee text-success text-opacity-75"></span></#if>
                            <#if fichaje.tipoFichaje == 'SALIDA_COMIDA'><span class="fas fa-utensils text-danger text-opacity-75"></span></#if>
                            <#if fichaje.tipoFichaje == 'ENTRADA_COMIDA'><span class="fas fa-utensils text-success text-opacity-75"></span></#if>
                            <#if fichaje.tipoFichaje == 'SALIDA_JORNADA'><span class="fas fa-suitcase text-danger text-opacity-75"></span></#if>
                        </div>
                        <#if (fichaje_has_next)>
                        <span class="timeline-bar border-end border-dashed"></span>
                        </#if>
                    </div>
                </div>
                <div class="col">
                    <div class="d-flex justify-content-between">
                        <div class="d-flex mb-2">
                            <h6 class="lh-sm mb-0 me-2 text-body-secondary timeline-item-title">
                                ${fichaje.tipoFichaje}
                                <#if fichaje.id == validacion.idEstado>
                                <span class="fas fa-warning text-warning text-opacity-75 fs-2 ps-4"></span>
                                </#if>
                            </h6>
                        </div>
                        <p class="text-body-quaternary fs-9 mb-0 text-nowrap timeline-time"><svg class="svg-inline--fa fa-clock me-1" aria-hidden="true" focusable="false" data-prefix="far" data-icon="clock" role="img" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 512 512" data-fa-i2svg=""><path fill="currentColor" d="M232 120C232 106.7 242.7 96 256 96C269.3 96 280 106.7 280 120V243.2L365.3 300C376.3 307.4 379.3 322.3 371.1 333.3C364.6 344.3 349.7 347.3 338.7 339.1L242.7 275.1C236 271.5 232 264 232 255.1L232 120zM256 0C397.4 0 512 114.6 512 256C512 397.4 397.4 512 256 512C114.6 512 0 397.4 0 256C0 114.6 114.6 0 256 0zM48 256C48 370.9 141.1 464 256 464C370.9 464 464 370.9 464 256C464 141.1 370.9 48 256 48C141.1 48 48 141.1 48 256z"></path></svg><!-- <span class="fa-regular fa-clock me-1"></span> Font Awesome fontawesome.com -->
                            ${fichaje.getHoraFichajeHHmm()}
                        </p>
                    </div>
                    <#if fichaje.extemporaneo>
                    <h6 class="fs-10 fw-normal mb-3">Fichado extemporáneo a las: ${fichaje.getCreatedHHmm()}</h6>
                    </#if>
                    <p class="fs-9 text-body-secondary w-sm-60 mb-5">
                    </p>
                </div>
            </div>
        </div>
        </#list>

    </div>


<table class="table table-sm">
    <thead>
    <tr>
        <th scope="col"></th>
        <th scope="col" class="text-center">Inicio</th>
        <th scope="col" class="text-center">Fin</th>
        <th scope="col" class="text-center">Tiempo</th>
    </tr>
    </thead>
    <tbody>
    <tr>
        <th scope="row">Jornada</th>
        <td class="text-center"><@localdatetimemacro computo.inicioJornada /></td>
        <td class="text-center"><@localdatetimemacro computo.finJornada /></td>
        <td class="text-center">${computo.tiempoJornada?string["0.0"]}</td>
    </tr>
    <tr>
        <th scope="row">Desayuno</th>
        <td class="text-center"><@localdatetimemacro computo.inicioDesayuno /></td>
        <td class="text-center"><@localdatetimemacro computo.finDesayuno /></td>
        <td class="text-center">${computo.tiempoDesayuno?string["0.0"]}</td>
    </tr>
    <tr>
        <th scope="row">Almuerzo</th>
        <td class="text-center"><@localdatetimemacro computo.inicioComida /></td>
        <td class="text-center"><@localdatetimemacro computo.finComida /></td>
        <td class="text-center">${computo.tiempoComida?string["0.0"]}</td>
    </tr>
    <tr>
        <td colspan="3" class="text-end"><b>Total Jornada</b></td>
        <td class="text-center"><b>${computo.tiempoTotalJornada?string["0.0"]}</b></td>
    </tr>
    </tbody>
</table>

</div>
