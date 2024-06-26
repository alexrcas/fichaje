<#import "themelayoutAdmin.ftl" as base>
<@base.themelayout active="vacaciones">
<#include "localdatemacro.ftl">


<div class="row">

    <div class="col-7">


        <div id="tableExample3" data-list='{"valueNames":["empleado","inicio","regreso", "numeroDias"]}'>
            <div class="search-box mb-3 mx-auto ms-0">
                <form class="position-relative" data-bs-toggle="search" data-bs-display="static"><input class="form-control search-input search form-control-sm" type="search" placeholder="Buscar" aria-label="Search" />
                    <span class="fas fa-search search-box-icon"></span>
                </form>
            </div>
            <div class="table-responsive">
                <table class="table table-striped table-sm fs--1 mb-0">
                    <thead>
                    <tr>
                        <th class="sort border-top ps-3" data-sort="empleado">Empleado</th>
                        <th class="sort border-top" data-sort="inicio">Primer día de ausencia</th>
                        <th class="sort border-top" data-sort="regreso">Día de incorporación</th>
                        <th class="sort border-top" data-sort="numeroDias">Días</th>
                        <th class="sort text-end align-middle pe-0 border-top" scope="col"></th>
                    </tr>
                    </thead>
                    <tbody class="list">
                    <#list vacaciones as v>
                    <tr>
                        <td class="align-middle ps-3 empleado">${v.empleado.email}</td>
                        <td class="align-middle inicio"><@localdatemacro v.primerDia.fecha /></td>
                        <td class="align-middle regreso"><@localdatemacro v.diaRegreso.fecha /></td>
                        <td class="align-middle numeroDias">${v.numeroDias}</td>
                        <td class="align-middle white-space-nowrap text-end pe-0">
                            <div class="font-sans-serif btn-reveal-trigger position-static"><button class="btn btn-sm dropdown-toggle dropdown-caret-none transition-none btn-reveal fs--2" type="button" data-bs-toggle="dropdown" data-boundary="window" aria-haspopup="true" aria-expanded="false" data-bs-reference="parent"><span class="fas fa-ellipsis-h fs--2"></span></button>
                                <div class="dropdown-menu dropdown-menu-end py-2">
                                    <a class="dropdown-item text-danger" href="#!">Eliminar</a>
                                </div>
                            </div>
                        </td>
                    </tr>
                    </#list>
                    </tbody>
                </table>
            </div>
        </div>





    </div>

    <div class="col-5">
        <h4 class="d-flex justify-content-center mb-3">Añadir vacaciones</h4>
        <form id="fichar-form" action="/web/admin/addVacaciones" method="post" class="w-100 d-flex justify-content-center">
            <div class="ps-2 d-flex text-center flex-column w-75">
                <div class="mb-3">
                    <div class="form-floating">
                        <select name="idEmpleado" class="form-select" id="floatingSelect" aria-label="Floating label select example">
                            <option value="1">arodriguez</option>
                        </select>
                        <label for="floatingSelect">Empleado</label>
                    </div>
                </div>

                <div class="text-center mb-3 form-floating">
                    <input name="fechaInicio" class="form-control datetimepicker flatpickr-input" id="datetimepicker" type="text" placeholder="DD/MM/YYYY" data-options="{&quot;dateFormat&quot;:&quot;Y-m-d&quot;,&quot;disableMobile&quot;:true}" readonly="readonly">
                    <label for="datetimepicker">Primer día de ausencia</label>
                </div>

                <div class="text-center mb-3 form-floating">
                    <input name="fechaFin" class="form-control datetimepicker flatpickr-input" id="datetimepicker" type="text" placeholder="DD/MM/YYYY" data-options="{&quot;dateFormat&quot;:&quot;Y-m-d&quot;,&quot;disableMobile&quot;:true}" readonly="readonly">
                    <label for="datetimepicker">Día de incorporación</label>
                </div>

                <div class="text-center mb-0">
                    <button id="fichar-btn" type="submit" class="btn btn-outline-primary">Aceptar</button>
                </div>

            </div>
        </form>
    </div>




</div>





<script>

</script>

</@base.themelayout>