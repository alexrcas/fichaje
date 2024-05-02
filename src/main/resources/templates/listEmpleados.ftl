<#import "themelayout.ftl" as base>
<@base.themelayout active="empleados">


<div class="row">


    <div id="tableExample3" data-list='{"valueNames":["email","nombre"]}'>
        <div class="search-box mb-3 mx-auto ms-0">
            <form class="position-relative" data-bs-toggle="search" data-bs-display="static"><input class="form-control search-input search form-control-sm" type="search" placeholder="Buscar" aria-label="Search" />
                <span class="fas fa-search search-box-icon"></span>
            </form>
        </div>
        <div class="table-responsive">
            <table class="table table-striped table-sm fs--1 mb-0">
                <thead>
                <tr>
                    <th class="sort border-top" data-sort="email">Email / Usuario</th>
                    <th class="sort border-top" data-sort="nombre">Nombre</th>
                    <th class="sort text-end align-middle pe-0 border-top" scope="col"></th>
                </tr>
                </thead>
                <tbody class="list">
                <#list empleados as empleado>
                <tr>
                    <td class="align-middle email ps-1">${empleado.email}</td>
                    <td class="align-middle nombre">${empleado.nombre} ${empleado.apellidos}</td>
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

</@base.themelayout>