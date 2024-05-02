<#import "themelayout.ftl" as base>
<@base.themelayout active="fichajes">


<div class="mb-4 d-flex justify-content-start">
    <button class="btn btn-outline-primary" type="button" data-bs-toggle="modal" data-bs-target="#vacacionesModal">Añadir vacaciones</button>
</div>


<div class="row">

</div>




<div class="modal fade" id="vacacionesModal" tabindex="-1" aria-labelledby="verticallyCenteredModalLabel" aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="verticallyCenteredModalLabel">Añadir Vacaciones</h5><button class="btn p-1" type="button" data-bs-dismiss="modal" aria-label="Close"><span class="fas fa-times fs-9"></span></button>
            </div>
            <div class="modal-body">

                <form id="addVacaciones-form" action="/web/admin/addVacaciones" method="post">
                    <select id="idEmpleado" name="idEmpleado" class="form-select mb-3" aria-label="Default select example">
                        <option selected="">Seleccionar empleado</option>
                        <option value="1">One</option>
                        <option value="2">Two</option>
                        <option value="3">Three</option>
                    </select>

                    <div class="text-center mb-3 form-floating">
                        <input id="fechaInicio" name="fechaInicio" class="form-control datetimepicker" id="datepickerInicio" type="text" placeholder="dd/mm/yyyy" data-options='{"disableMobile":true,"dateFormat":"Y-m-d"}'>
                        <label for="datepickerInicio">Primer día de ausencia</label>
                    </div>

                    <div class="text-center mb-3 form-floating">
                        <input id="fechaFin" name="fechaFin" class="form-control datetimepicker" id="datepickerFin" type="text" placeholder="dd/mm/yyyy" data-options='{"disableMobile":true,"dateFormat":"Y-m-d"}'>
                        <label for="datepickerFin">Primer día de ausencia</label>
                    </div>

                </form>

            </div>
            <div class="modal-footer">
                <button id="addVacacionesBtn" class="btn btn-primary" type="button">Okay</button>
                <button id="dismissBtn" class="btn btn-outline-primary" type="button" data-bs-dismiss="modal">Cancel</button>
            </div>
        </div>
    </div>
</div>

<script>



    const addVacacionesBtn = document.getElementById('addVacacionesBtn');
    addVacacionesBtn.addEventListener('click', () => {
        addVacacionesBtn.disabled = true;
        document.getElementById('addVacaciones-form').submit();
    })

    document.getElementById('vacacionesModal').addEventListener('hidden.bs.modal', () => {
        document.getElementById('idEmpleado').value = 'Seleccionar empleado';
        document.getElementById('fechaInicio').value = '';
        document.getElementById('fechaFin').value = '';
        addVacacionesBtn.disabled = false;
    })

</script>

</@base.themelayout>