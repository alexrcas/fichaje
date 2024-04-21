<#import "themelayout.ftl" as base>
<@base.themelayout active="fichajes">

<div class="container mb-4">
    <div class="row w-50">

        <div class="col">
            <select class="form-select" aria-label="Default select example">
                <option selected="">Abril</option>
            </select>
        </div>

        <div class="col">
            <select class="form-select" aria-label="Default select example">
                <option selected="">2024</option>
            </select>
        </div>

        <div class="col">
            <button class="btn btn-outline-primary">Buscar selección</button>
        </div>

    </div>
</div>

<div style="overflow-x: auto; width: 100%;">

    <div style="min-width: max-content;">

    <table class="table table-sm table-hover table-sticky phoenix-body-bg">
        <thead>
        <tr>
            <th scope="col"></th>
            <#list dias as dia>
            <th class="text-center" scope="col">
                <div class="d-flex flex-column">
                    <span class="pb-2">${dia.diaSemana.getCodigo()}</span>
                    <span>${dia.fechaDDMM()}</span>
                </div>
            </th>
            </#list>
        </tr>
        </thead>
        <tbody>
        <#list jornadas as jornada>
        <tr>
            <th scope="row">${jornada.empleado.email}</th>
            <#list jornada.jornadas as jornadaEmpleado>
                <td class="text-center table-cell-hover cursor-pointer" onclick="showDetalleJornada(${jornadaEmpleado.id})">
                    <#if jornadaEmpleado.horas != 'E'>
                    ${jornadaEmpleado.horas?number?string["0.0"]}
                    <#else>
                        E
                    </#if>
                </td>
            </#list>
        </tr>
        </#list>
        </tbody>
    </table>

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