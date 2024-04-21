<#import "themelayout.ftl" as base>
<@base.themelayout active="fichajes">
<#include "localdatemacro.ftl">

<div class="row">

    <div class="col-6">

            <table class="table table-bordered">
                <thead>
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
                    <td class="cursor-pointer p-2 table-cell-hover <#if semanaJornada.semanaActual> bg-300 dark__bg-1000</#if>" onclick="showDetalleJornada(${jornada.id})">
                        <div class="d-flex flex-column ">
                            <div class="small d-flex justify-content-end"><@localdatemacro jornada.fecha /></div>
                            <#if jornada.horas != ''>
                            <div class="text-center fw-bold mt-1">${jornada.horas?number?string["0.00"]}</div>
                            </#if>
                        </div>
                    </td>
                    </#list>
                <td class="p-2">
                    <div class="text-center fw-bold mt-3">8.0</div>
                </td>
                </tr>
                </#list>

                </tbody>
            </table>

    </div>

    <div class="col-6 d-flex align-items-center flex-column">

        <div class="w-50 ps-2 d-flex text-center flex-column">
            <div class="mb-3">
                <div class="form-floating">
                    <select class="form-select" id="floatingSelect" aria-label="Floating label select example">
                        <option value="1">One</option>
                        <option value="2">Two</option>
                        <option value="3">Three</option>
                    </select>
                    <label for="floatingSelect">Tipo de fichaje</label>
                </div>
            </div>

            <div class="text-center mb-3 form-floating">
                <input class="form-control datetimepicker flatpickr-input" id="datetimepicker" type="text" placeholder="DD/MM/YYYY HH:mm" data-options="{&quot;enableTime&quot;:true,&quot;dateFormat&quot;:&quot;d/m/y H:i&quot;,&quot;disableMobile&quot;:true}" readonly="readonly">
                <label for="datetimepicker">Fecha (solo en caso de extemporáneo)</label>
            </div>
            <div class="text-center mb-0">
                <button class="btn btn-primary">Fichar</button>
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
</script>

</@base.themelayout>