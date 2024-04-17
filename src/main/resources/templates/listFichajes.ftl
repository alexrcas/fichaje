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

    <table class="table table-sm table-hover table-sticky">
        <thead>
        <tr>
            <th scope="col"></th>
            <#list dias as dia>
            <th class="text-center" scope="col">${dia.fechaDDMM()}</th>
            </#list>
        </tr>
        </thead>
        <tbody>
        <#list jornadas as jornada>
        <tr>
            <th scope="row">${jornada.empleado.email}</th>
            <#list jornada.jornadas as jornadaEmpleado>
                <td class="text-center table-cell-hover">
                    <a href="/showDetalleJornada/${jornadaEmpleado.id}">
                        ${jornadaEmpleado.horas}
                    </a>
                </td>
            </#list>
        </tr>
        </#list>
        </tbody>
    </table>

    </div>

</div>


</@base.themelayout>