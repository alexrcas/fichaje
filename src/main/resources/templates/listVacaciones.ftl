<#import "themelayout.ftl" as base>
<@base.themelayout active="vacaciones">
<#include "localdatemacro.ftl">

<div class="row mb-4">
    <div class="d-flex justify-content-end">
        <button class="btn btn-outline-primary">Añadir vacaciones</button>
    </div>
</div>

<div class="row">

        <table class="table">
            <thead>
            <tr>
                <th scope="col">Empleado</th>
                <th scope="col">Desde (primer día de ausencia)</th>
                <th scope="col">Hasta (día de incorporación)</th>
                <th scope="col">Días</th>
            </tr>
            </thead>
            <tbody>
            <#list vacaciones as v>
            <tr>
                <td>${v.empleado.email}</td>
                <td><@localdatemacro v.primerDia.fecha /></td>
                <td><@localdatemacro v.diaRegreso.fecha /></td>
                <td>${v.numeroDias}</td>
            </tr>
            </#list>
            </tbody>
        </table>


</div>


<form id="fichar-form" action="/web/addVacaciones" method="post" class="w-100 d-flex justify-content-center">
    <div class="w-50 ps-2 d-flex text-center flex-column">
        <div class="mb-3">
            <div class="form-floating">
                <select name="idEmpleado" class="form-select" id="floatingSelect" aria-label="Floating label select example">
                    <option value="1">test</option>
                </select>
            <label for="floatingSelect">Tipo de fichaje</label>
        </div>
    </div>

    <div class="text-center mb-3 form-floating">
        <input name="fechaInicio" class="form-control datetimepicker flatpickr-input" id="datetimepicker" type="text" placeholder="DD/MM/YYYY" data-options="{&quot;dateFormat&quot;:&quot;Y-m-d&quot;,&quot;disableMobile&quot;:true}" readonly="readonly">
        <label for="datetimepicker">Inicio (primer día de ausencia)</label>
    </div>

    <div class="text-center mb-3 form-floating">
        <input name="fechaFin" class="form-control datetimepicker flatpickr-input" id="datetimepicker" type="text" placeholder="DD/MM/YYYY" data-options="{&quot;dateFormat&quot;:&quot;Y-m-d&quot;,&quot;disableMobile&quot;:true}" readonly="readonly">
        <label for="datetimepicker">Regreso (día de incorporación)</label>
    </div>

    <div class="text-center mb-0">
        <button id="fichar-btn" type="submit" class="btn btn-primary">Añadir</button>
    </div>

    </div>
</form>


<script>

</script>

</@base.themelayout>