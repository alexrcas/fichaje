<#import "themelayout.ftl" as base>
<@base.themelayout active="fichajes">
<#include "localdatemacro.ftl">

<div class="row">

    <div style="max-height:80vh" class="col-6 h-100 overflow-auto">

            <table class="table table-bordered">
                <thead class="sticky-top tabla-fichaje bg-body dark__bg-dark">
                <tr>
                    <th class="text-center">L</th>
                    <th class="text-center">M</th>
                    <th class="text-center">X</th>
                    <th class="text-center">J</th>
                    <th class="text-center">V</th>
                    <th class="text-center">Total</th>
                </tr>
                </thead>
                <tbody>
                <#list semanasJornadas as semanaJornada>
                <tr>
                    <#list semanaJornada.jornadas as jornada>
                    <td class="cursor-pointer p-2 table-cell-hover <#if semanaJornada.semanaActual> bg-300 dark__bg-1000</#if>"
                        <#if jornada.horas != ''>onclick="showDetalleJornada(${jornada.id})"</#if> >
                        <div class="d-flex flex-column">
                            <div class="small d-flex justify-content-end"><@localdatemacro jornada.fecha /></div>
                            <#if jornada.horas != '' && jornada.horas != 'E'>
                            <div class="text-center fw-bold mt-1">${jornada.horas?number?string["0.00"]}</div>
                            </#if>
                        <#if jornada.horas == 'E'>
                        <div class="text-center fw-bold mt-1 bg-danger">E</div>
                    </#if>
                        </div>
                    </td>
                    </#list>

                <#if (semanaJornada.tiempoSemana < 38.5) || (semanaJornada.tiempoSemana > 40) >
                <td class="p-2 bg-danger">
                    <div class="text-center fw-bold mt-3">${semanaJornada.tiempoSemana?string["0.0"]}</div>
                </td>
                <#else>
                <td class="p-2 bg-success">
                    <div class="text-center fw-bold mt-3">${semanaJornada.tiempoSemana?string["0.0"]}</div>
                </td>
                </#if>

                </tr>
                </#list>

                </tbody>
            </table>

    </div>

    <div class="col-6 d-flex align-items-center flex-column">

        <form action="/web/fichar" method="post" class="w-100 d-flex justify-content-center">
            <div class="w-50 ps-2 d-flex text-center flex-column">
                <div class="mb-3">
                    <div class="form-floating">
                        <select name="fichaje" class="form-select" id="floatingSelect" aria-label="Floating label select example">
                            <option value="ENTRADA_JORNADA">Entrada jornada</option>
                            <option value="SALIDA_DESAYUNO">Salida desayuno</option>
                            <option value="ENTRADA_DESAYUNO">Entrada desayuno</option>
                            <option value="SALIDA_COMIDA">Salida comida</option>
                            <option value="ENTRADA_COMIDA">Entrada comida</option>
                            <option value="SALIDA_JORNADA">Salida jornada</option>
                        </select>
                        <label for="floatingSelect">Tipo de fichaje</label>
                    </div>
                </div>

                <div class="text-center mb-3 form-floating">
                    <input name="fecha" class="form-control datetimepicker flatpickr-input" id="datetimepicker" type="text" placeholder="DD/MM/YYYY HH:mm" data-options="{&quot;enableTime&quot;:true,&quot;dateFormat&quot;:&quot;d/m/y H:i&quot;,&quot;disableMobile&quot;:true}" readonly="readonly">
                    <label for="datetimepicker">Fecha (solo en caso de extemporáneo)</label>
                </div>
                <div class="text-center mb-0">
                    <button type="submit" class="btn btn-primary">Fichar</button>
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

</script>

</@base.themelayout>