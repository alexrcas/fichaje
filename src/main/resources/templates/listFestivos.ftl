<#import "themelayout.ftl" as base>
<@base.themelayout active="festivos">
<#include "localdatemacro.ftl">


<div class="row">

    <div class="col-7">


        <div id="tableExample3" data-list='{"valueNames":["fecha","motivo"]}'>
            <div class="search-box mb-3 mx-auto">
                <form class="position-relative" data-bs-toggle="search" data-bs-display="static"><input class="form-control search-input search form-control-sm" type="search" placeholder="Buscar" aria-label="Search" />
                    <span class="fas fa-search search-box-icon"></span>
                </form>
            </div>
            <div class="table-responsive">
                <table class="table table-striped table-sm fs--1 mb-0">
                    <thead>
                    <tr>
                        <th class="sort border-top" data-sort="inicio">Fecha</th>
                        <th class="sort border-top" data-sort="numeroDias">Motivo</th>
                        <th class="sort text-end align-middle pe-0 border-top" scope="col"></th>
                    </tr>
                    </thead>
                    <tbody class="list">
                    <#list festivos as festivo>
                    <tr>
                        <td class="align-middle fecha"><@localdatemacro festivo.dia.fecha /></td>
                        <td class="align-middle motivo">${festivo.motivo}</td>
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
        <h4 class="d-flex justify-content-center mb-3">Añadir Festivo</h4>
        <form id="fichar-form" action="/web/addFestivo" method="post" class="w-100 d-flex justify-content-center">
            <div class="ps-2 d-flex text-center flex-column w-75">

                <div class="text-center mb-3 form-floating">
                    <input name="fecha" class="form-control datetimepicker flatpickr-input" id="datetimepicker" type="text" placeholder="DD/MM/YYYY" data-options="{&quot;dateFormat&quot;:&quot;Y-m-d&quot;,&quot;disableMobile&quot;:true}" readonly="readonly">
                    <label for="datetimepicker">Fecha</label>
                </div>

                <div class="form-floating mb-3">
                    <input class="form-control" id="motivo" placeholder="Motivo" />
                    <label for="motivo">Motivo</label>
                </div>

                <div class="text-center mb-0">
                    <button id="fichar-btn" type="submit" class="btn btn-outline-primary">Añadir</button>
                </div>

            </div>
        </form>
    </div>




</div>





<script>

</script>

</@base.themelayout>