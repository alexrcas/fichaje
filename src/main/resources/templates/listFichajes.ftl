<#import "themelayout.ftl" as base>
<@base.themelayout active="fichajes">
<#include "localdatemacro.ftl">

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
        <h4 class="d-flex justify-content-center mb-3">Fichar</h4>
        <form id="fichar-form" action="/web/fichar" method="post" class="w-100 d-flex justify-content-center">
            <div class="w-50 ps-2 d-flex text-center flex-column">
                <div class="mb-3">
                    <div class="form-floating">
                        <select name="fichaje" class="form-select" id="floatingSelect" aria-label="Floating label select example">
                            <#list opciones as opcion>
                            <option value="${opcion}" <#if opcion == opcionSugerida>selected</#if>>${opcion.getName()}</option>
                            </#list>
                        </select>
                        <label for="floatingSelect">Tipo de fichaje</label>
                    </div>
                </div>

                <div class="text-center mb-3 form-floating">
                    <input name="fecha" class="form-control datetimepicker flatpickr-input" id="datetimepicker" type="text" placeholder="DD/MM/YYYY HH:mm" data-options="{&quot;enableTime&quot;:true,&quot;dateFormat&quot;:&quot;d/m/y H:i&quot;,&quot;disableMobile&quot;:true}" readonly="readonly">
                    <label for="datetimepicker">Fecha (solo en caso de extemporáneo)</label>
                </div>
                <div class="text-center mb-0">
                    <button id="fichar-btn" type="submit" class="btn btn-outline-primary">Fichar</button>
                </div>

            </div>
        </form>
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

    const ficharBtn = document.getElementById('fichar-btn');
    ficharBtn.addEventListener('click', () => {
        ficharBtn.disabled = true;
        document.getElementById('fichar-form').submit();
    })

    document.getElementById('currentWeek').scrollIntoView(true);

</script>

</@base.themelayout>