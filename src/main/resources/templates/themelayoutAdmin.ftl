<#macro themelayout active>

<#import "master.ftl" as base>
<@base.layout>


<main class="main" id="top">
    <nav class="navbar navbar-vertical navbar-expand-lg" style="width:12rem">
        <script>
          var navbarStyle = window.config.config.phoenixNavbarStyle;
          if (navbarStyle && navbarStyle !== 'transparent') {
            document.querySelector('body').classList.add('navbar-' + navbarStyle);
          }
        </script>
        <div class="collapse navbar-collapse" id="navbarVerticalCollapse">
            <!-- scrollbar removed-->
            <div class="navbar-vertical-content">
                <ul class="navbar-nav flex-column" id="navbarVerticalNav">
                    <li class="nav-item">
                        <!-- parent pages-->

                        <div class="nav-item-wrapper"><a class="nav-link <#if active == 'fichajes'>active</#if> label-1" href="/listFichajes" role="button" data-bs-toggle="" aria-expanded="false">
                            <div class="d-flex align-items-center"><span class="nav-link-icon"><span class="fas fa-business-time"></span></span><span class="nav-link-text-wrapper"><span class="nav-link-text">Fichajes</span></span>
                            </div>
                        </a>
                        </div>

                        <div class="nav-item-wrapper"><a class="nav-link <#if active == 'vacaciones'>active</#if> label-1" href="/admin/listVacaciones" role="button" data-bs-toggle="" aria-expanded="false">
                            <div class="d-flex align-items-center"><span class="nav-link-icon"><span class="fas fa-plane"></span></span><span class="nav-link-text-wrapper"><span class="nav-link-text">Vacaciones</span></span>
                            </div>
                        </a>
                        </div>

                        <div class="nav-item-wrapper"><a class="nav-link <#if active == 'festivos'>active</#if> label-1" href="/admin/listFestivos" role="button" data-bs-toggle="" aria-expanded="false">
                            <div class="d-flex align-items-center"><span class="nav-link-icon"><span data-feather="calendar"></span></span><span class="nav-link-text-wrapper"><span class="nav-link-text">Festivos</span></span>
                            </div>
                        </a>
                        </div>

                        <div class="nav-item-wrapper"><a class="nav-link <#if active == 'ausencias'>active</#if> label-1" href="/admin/listAusenciasJustificadas" role="button" data-bs-toggle="" aria-expanded="false">
                            <div class="d-flex align-items-center"><span class="nav-link-icon"><span class="fas fa-user-clock"></span></span><span class="nav-link-text-wrapper"><span class="nav-link-text">Ausencias Just.</span></span>
                            </div>
                        </a>
                        </div>

                        <div class="nav-item-wrapper"><a class="nav-link <#if active == 'anulaciones'>active</#if> label-1" href="/admin/listSolicitudesAnulacion" role="button" data-bs-toggle="" aria-expanded="false">
                            <div class="d-flex align-items-center"><span class="nav-link-icon"><span class="fas fa-eraser"></span></span><span class="nav-link-text-wrapper"><span class="nav-link-text">Sol. Anulación</span></span>
                            </div>
                        </a>
                        </div>

                        <div class="nav-item-wrapper"><a class="nav-link <#if active == 'empleados'>active</#if> label-1" href="/admin/listEmpleados" role="button" data-bs-toggle="" aria-expanded="false">
                            <div class="d-flex align-items-center"><span class="nav-link-icon"><span class="fas fa-users"></span></span><span class="nav-link-text-wrapper"><span class="nav-link-text">Empleados</span></span>
                            </div>
                        </a>
                        </div>

                    </li>


                </ul>
            </div>
        </div>
    </nav>
    <nav class="navbar navbar-top fixed-top navbar-expand" id="navbarDefault">
        <div class="collapse navbar-collapse justify-content-between">
            <div class="navbar-logo">

                <button class="btn navbar-toggler navbar-toggler-humburger-icon hover-bg-transparent" type="button" data-bs-toggle="collapse" data-bs-target="#navbarVerticalCollapse" aria-controls="navbarVerticalCollapse" aria-expanded="false" aria-label="Toggle Navigation"><span class="navbar-toggle-icon"><span class="toggle-line"></span></span></button>
                <a class="navbar-brand me-1 me-sm-3" href="/">
                    <div class="d-flex align-items-center">
                        <!--<div class="d-flex align-items-center"><img src="../assets/custom/avantic-logo.jpeg" alt="phoenix" width="27" />-->
                            <p class="logo-text ms-2 d-none d-sm-block">Control de Fichaje</p>
                        </div>
                    </div>
                </a>
            </div>

            <ul class="navbar-nav navbar-nav-icons flex-row">
                <li class="nav-item">
                    <div class="theme-control-toggle fa-icon-wait px-2">
                        <input class="form-check-input ms-0 theme-control-toggle-input" type="checkbox" data-theme-control="phoenixTheme" value="dark" id="themeControlToggle" />
                        <label class="mb-0 theme-control-toggle-label theme-control-toggle-light" for="themeControlToggle" data-bs-toggle="tooltip" data-bs-placement="left" title="Cambiar a modo claro"><span class="icon" data-feather="moon"></span></label>
                        <label class="mb-0 theme-control-toggle-label theme-control-toggle-dark" for="themeControlToggle" data-bs-toggle="tooltip" data-bs-placement="left" title="Cambiar a modo oscuro"><span class="icon" data-feather="sun"></span></label>
                    </div>
                </li>
                <li class="nav-item dropdown">
                    <a class="nav-link" href="#" style="min-width: 2.5rem" role="button" data-bs-toggle="dropdown" aria-haspopup="true" aria-expanded="false" data-bs-auto-close="outside"><span data-feather="bell" style="height:20px;width:20px;"></span></a>

                    <div class="dropdown-menu dropdown-menu-end notification-dropdown-menu py-0 shadow border border-300 navbar-dropdown-caret" id="navbarDropdownNotfication" aria-labelledby="navbarDropdownNotfication">
                        <div class="card position-relative border-0">
                            <div class="card-header p-2">
                                <div class="d-flex justify-content-between">
                                    <h5 class="text-black mb-0">Last Notificatons</h5>
                                </div>
                            </div>
                            <div class="card-body p-0">
                                <div class="scrollbar-overlay">
                                    <div class="border-300">



                                            <div class="px-2 px-sm-3 py-3 border-300 notification-card position-relative unread border-bottom">
                                                <div class="d-flex align-items-center justify-content-between position-relative">
                                                    <div class="d-flex">
                                                        <div class="flex-1 me-sm-3">
                                                            <h4 class="fs--1 text-black">notification.title</h4>
                                                            <p class="fs--1 text-1000 mb-2 mb-sm-3 fw-normal"><span class='me-1 fs--2'></span>notification.message</p>
                                                            <p class="text-800 fs--1 mb-0"><span class="me-1 fas fa-clock"></span><span class="fw-bold">10:20 AM </span>August 7,2021</p>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>



                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </li>

                <li class="nav-item dropdown"><a class="nav-link lh-1 pe-0" id="navbarDropdownUser" href="#!" role="button" data-bs-toggle="dropdown" data-bs-auto-close="outside" aria-haspopup="true" aria-expanded="false">
                </a>${username}
                    <div class="dropdown-menu dropdown-menu-end navbar-dropdown-caret py-0 dropdown-profile shadow border border-300" aria-labelledby="navbarDropdownUser">
                        <div class="card position-relative border-0">
                            <div class="card-body p-0">
                                <div class="text-center pt-4 pb-3">
                                    <div class="avatar avatar-xl ">
                                        <img class="rounded-circle " src="../assets/img/team/72x72/57.webp" alt="" />

                                    </div>
                                    <h6 class="mt-2 text-black">user.name</h6>
                                </div>
                            </div>
                            <div class="overflow-auto scrollbar">
                                <ul class="nav d-flex flex-column mb-2 pb-1">
                                    <li class="nav-item"><a class="nav-link px-3" href="/profile"> <span class="me-2 text-900" data-feather="user"></span><span>Profile</span></a></li>
                                </ul>
                            </div>
                            <div class="card-footer p-2">
                                <div class="px-3"> <a class="btn btn-phoenix-secondary d-flex flex-center w-100" href="#!"> <span class="me-2" data-feather="log-out"> </span>Sign out</a></div>
                                <div class="my-2 text-center fw-bold fs--2 text-600"><a class="text-600 me-1" href="#!">Privacy policy</a>&bull;<a class="text-600 mx-1" href="#!">Terms</a>&bull;<a class="text-600 ms-1" href="#!">Cookies</a></div>
                            </div>
                        </div>
                    </div>
                </li>
            </ul>
        </div>
    </nav>
    <script>
        var navbarTop = document.querySelector('.navbar-top');
        var navbarVertical = document.querySelector('.navbar-vertical');
        window.config.set({
          phoenixNavbarVerticalStyle: 'darker',
          phoenixNavbarTopStyle: 'darker'
        });
        navbarTop.classList.add('navbar-darker');
        navbarVertical.classList.add('navbar-darker');
      </script>

    <div class="content">

            <#if error??>
                <div class="alert alert-soft-danger d-flex align-items-center" role="alert">
                    <span class="fas fa-times-circle text-danger fs-3 me-3"></span>
                    <p class="mb-0 flex-1">Se ha producido un error: ${error}</p>
                    <button class="btn-close" type="button" data-bs-dismiss="alert" aria-label="Close"></button>
                </div>
            </#if>

            <#nested>
    </div>
    <script>
        var navbarTopStyle = window.config.config.phoenixNavbarTopStyle;
        var navbarTop = document.querySelector('.navbar-top');
        if (navbarTopStyle === 'darker') {
          navbarTop.classList.add('navbar-darker');
        }

        var navbarVerticalStyle = window.config.config.phoenixNavbarVerticalStyle;
        var navbarVertical = document.querySelector('.navbar-vertical');
        if (navbarVertical && navbarVerticalStyle === 'darker') {
          navbarVertical.classList.add('navbar-darker');
        }
      </script>

</main>

</@base.layout>

</#macro>