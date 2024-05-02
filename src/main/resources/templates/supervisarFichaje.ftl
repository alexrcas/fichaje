<#import "themelayoutAdmin.ftl" as base>
<@base.themelayout active="fichajes">
<#include "localdatemacro.ftl">


<div class="row mb-3">

    <div class="d-flex w-100">
        <div class="form-floating">
            <select class="form-select" style="width: 25vw" id="empleado-select" aria-label="Floating label select example">
                <option value="" selected></option>
                <#list empleados as empleado>
                <option value="${empleado.id}">${empleado.email}</option>
            </#list>
            </select>
            <label for="empleado-select">Seleccione Empleado</label>
        </div>

        <button class="btn btn-outline-primary ms-2" id="ir-button" disabled>Ver</button>

    </div>

</div>


<div class="row">

    <div style="max-height:80vh" class="col-7 h-100 overflow-auto">

            <table class="table table-bordered tabla-fichajes">
                <thead class="sticky-top tabla-fichaje bg-body dark__bg-dark">
                <tr>
                    <th class="text-center">L</th>
                    <th class="text-center">M</th>
                    <th class="text-center">X</th>
                    <th class="text-center">J</th>
                    <th class="text-center">V</th>
                    <th class="text-center">Suma</th>
                    <th class="text-center">Jornada</th>
                </tr>
                </thead>
                <tbody>
                <#list semanasJornadas as semanaJornada>
                <tr <#if semanaJornada.semanaActual>id="currentWeek"</#if>>
                    <#list semanaJornada.dias as dia>

                <#if dia.festivo>
                    <td class="cursor-pointer p-2 table-cell-hover <#if semanaJornada.semanaActual> bg-300 dark__bg-1000</#if>">
                        <div class="d-flex flex-column">
                        <div class="small d-flex justify-content-end <#if dia.today>text-primary fw-bold</#if>"><@localdatemacro dia.fecha /></div>
                            <div class="text-center fw-bold mt-1">
                                <span class="badge badge-phoenix badge-phoenix-info">Fest.</span>
                            </div>
                        </div>
                    </td>
                <#elseif dia.vacaciones>
                <td class="cursor-pointer p-2 table-cell-hover <#if semanaJornada.semanaActual> bg-300 dark__bg-1000</#if>">
                    <div class="d-flex flex-column">
                        <div class="small d-flex justify-content-end <#if dia.today>text-primary fw-bold</#if>"><@localdatemacro dia.fecha /></div>
                        <div class="text-center fw-bold mt-1">
                            <span class="badge badge-phoenix badge-phoenix-success">Vac.</span>
                        </div>
                    </div>
                </td>
                <#else>
                        <td class="cursor-pointer p-2 table-cell-hover <#if semanaJornada.semanaActual> bg-300 dark__bg-1000</#if>"
                            <#if dia.jornada??>
                                <#if dia.jornada.horas != ''>onclick="showDetalleJornada(${dia.jornada.id})"</#if> >
                                <div class="d-flex flex-column">
                                    <div class="small d-flex justify-content-end <#if dia.today>text-primary fw-bold</#if>">
                                        <@localdatemacro dia.fecha />
                                    </div>
                                    <div class="text-center fw-bold mt-1">
                                        <#if dia.jornada.horas == 'E'>
                                            <span class="badge badge-phoenix badge-phoenix-danger">ERR</span>
                                        <#else>
                                            ${dia.jornada.horas?number?string["0.00"]}
                                        </#if>
                                    </div>
                                    <#if dia.jornada.ausenciaJustificada>
                                    <span class="badge badge-phoenix badge-phoenix-secondary">Just.</span>
                                    </#if>
                                </div>
                            <#else>
                            <td class="cursor-pointer p-2 table-cell-hover <#if semanaJornada.semanaActual> bg-300 dark__bg-1000</#if>">
                                <div class="d-flex flex-column">
                                    <div class="small d-flex justify-content-end <#if dia.today>text-primary fw-bold</#if>">
                                        <@localdatemacro dia.fecha />
                                    </div>
                                </div>
                            </td>
                            </#if>
                        </td>
                    </#if>


                    </#list>

                    <#if (semanaJornada.tiempoSemana < (semanaJornada.horas - 1.5)) || (semanaJornada.tiempoSemana > semanaJornada.horas) >
                    <td class="p-2 bg-danger-soft">
                        <div class="text-center fw-bold mt-3 text-secondary">
                            ${semanaJornada.tiempoSemana?string["0.0"]}
                        </div>
                    </td>
                    <#else>
                    <td class="p-2 bg-success-soft">
                        <div class="text-center fw-bold text-secondary mt-3">${semanaJornada.tiempoSemana?string["0.0"]}</div>
                    </td>
                    </#if>

                    <td class="p-2">
                        <div class="text-center fw-bold mt-3">${semanaJornada.horas?string["0.0"]}</div>
                    </td>
                </tr>

                </#list>

                </tbody>
            </table>

    </div>

    <div class="col-5 d-flex align-items-center flex-column">
        <div class="alert alert-outline-primary p-3 mt-3" role="alert">
            <div class="d-flex align-items-center">
            <span class="fas fa-info-circle text-primary fs-1 me-3"></span>
            <p class="mb-0 flex-1">Está visualizando el fichaje de otro empleado.</p>
            </div>

            <div>
                <p class="d-flex justify-content-center"><a href="/listFichajes"><b>Ir a mi fichaje</b></a></p>
            </div>
        </div>

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
    const showDetalleJornada = async (id) => {
        const response = await fetch('/showDetalleJornada/' + id);
        const html = await response.text();
        document.getElementById('offcanvas-body').innerHTML = html;

        const offcanvas = new bootstrap.Offcanvas(document.getElementById('offcanvas'));
        offcanvas.show();
    }

    const el = document.getElementById('currentWeek');
    if (el) {
        el.scrollIntoView(true) ;
    }


        document.getElementById('empleado-select').addEventListener('change', e => {
        if (e.target.value) {
            document.getElementById('ir-button').disabled = false;
        } else {
            document.getElementById('ir-button').disabled = true;
        }
    })

    document.getElementById('ir-button').addEventListener('click', () => {
        const idEmpleado = document.getElementById('empleado-select').value;
        window.location.href = '/admin/supervisarFichaje?idEmpleado=' + idEmpleado;
    });

</script>

</@base.themelayout>