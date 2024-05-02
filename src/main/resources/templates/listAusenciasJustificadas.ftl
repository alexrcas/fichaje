<#import "themelayout.ftl" as base>
<@base.themelayout active="ausencias">
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
                        <th class="sort border-top" data-sort="inicio">Fecha</th>
                        <th class="sort border-top" data-sort="regreso">Horas</th>
                        <th class="sort border-top" data-sort="numeroDias">Motivo</th>
                        <th class="sort text-end align-middle pe-0 border-top" scope="col"></th>
                    </tr>
                    </thead>
                    <tbody class="list">
                    <#list ausencias as ausencia>
                    <tr>
                        <td class="align-middle ps-3 empleado">${ausencia.empleado.email}</td>
                        <td class="align-middle inicio"><@localdatemacro ausencia.fecha /></td>
                        <td class="align-middle regreso">${ausencia.horas}</td>
                        <td class="align-middle numeroDias">${ausencia.motivo}</td>
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
        <h4 class="d-flex justify-content-center mb-3">Añadir Ausencia</h4>
        <form id="fichar-form" action="/web/addAusenciaJustificada" method="post" class="w-100 d-flex justify-content-center">
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
                    <input name="fecha" class="form-control datetimepicker flatpickr-input" id="datetimepicker" type="text" placeholder="DD/MM/YYYY" data-options="{&quot;dateFormat&quot;:&quot;Y-m-d&quot;,&quot;disableMobile&quot;:true}" readonly="readonly">
                    <label for="datetimepicker">Fecha</label>
                </div>

                <div class="text-center mb-3 form-floating">
                    <input name="horas" class="form-control" type="number" placeholder="0.0">
                    <label for="datetimepicker">Horas</label>
                </div>

                <div class="text-center mb-3 form-floating">
                    <input name="motivo" class="form-control" id="motivo" type="text" placeholder="Motivo">
                    <label for="motivo">Motivo</label>
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