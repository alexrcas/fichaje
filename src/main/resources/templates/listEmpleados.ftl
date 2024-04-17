<#import "themelayout.ftl" as base>
<@base.themelayout active="empleados">


<div class="container d-flex flex-column">

    <div id="members" data-list="{&quot;valueNames&quot;:[&quot;customer&quot;,&quot;email&quot;,&quot;mobile_number&quot;,&quot;city&quot;,&quot;last_active&quot;,&quot;joined&quot;],&quot;page&quot;:10,&quot;pagination&quot;:true}">
        <div class="row align-items-center justify-content-between g-3 mb-4">
            <div class="col col-auto">
                <div class="search-box">
                    <form class="position-relative"><input class="form-control search-input search" type="search" placeholder="Buscar..." aria-label="Search">
                        <svg class="svg-inline--fa fa-magnifying-glass search-box-icon" aria-hidden="true" focusable="false" data-prefix="fas" data-icon="magnifying-glass" role="img" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 512 512" data-fa-i2svg=""><path fill="currentColor" d="M500.3 443.7l-119.7-119.7c27.22-40.41 40.65-90.9 33.46-144.7C401.8 87.79 326.8 13.32 235.2 1.723C99.01-15.51-15.51 99.01 1.724 235.2c11.6 91.64 86.08 166.7 177.6 178.9c53.8 7.189 104.3-6.236 144.7-33.46l119.7 119.7c15.62 15.62 40.95 15.62 56.57 0C515.9 484.7 515.9 459.3 500.3 443.7zM79.1 208c0-70.58 57.42-128 128-128s128 57.42 128 128c0 70.58-57.42 128-128 128S79.1 278.6 79.1 208z"></path></svg><!-- <span class="fas fa-search search-box-icon"></span> Font Awesome fontawesome.com -->
                    </form>
                </div>
            </div>

        </div>
        <div class="mx-n4 mx-lg-n6 px-4 px-lg-6 mb-9 bg-body-emphasis border-y mt-2 position-relative top-1">
            <div class="table-responsive scrollbar ms-n1 ps-1">
                <table class="table table-sm fs-9 mb-0">
                    <thead>
                    <tr>
                        <th class="sort align-middle" scope="col" data-sort="customer" style="width:15%; min-width:200px;">Nombre</th>
                        <th class="sort align-middle" scope="col" data-sort="email" style="width:15%; min-width:200px;">Email</th>
                        <th class="sort align-middle pe-3" scope="col" data-sort="mobile_number" style="width:20%; min-width:200px;">Teléfono</th>
                    </tr>
                    </thead>
                    <tbody class="list" id="members-table-body">
                    <tr class="hover-actions-trigger btn-reveal-trigger position-static">
                        <td class="customer align-middle white-space-nowrap"><a class="d-flex align-items-center text-body text-hover-1000" href="#!">
                            <div class="avatar avatar-m"><img class="rounded-circle" src="../assets/img/team/32.webp" alt=""></div>
                            <h6 class="mb-0 ms-3 fw-semibold">Carry Anna</h6>
                        </a></td>
                        <td class="email align-middle white-space-nowrap"><a class="fw-semibold" href="mailto:annac34@gmail.com">annac34@gmail.com</a></td>
                        <td class="mobile_number align-middle white-space-nowrap"><a class="fw-bold text-body-emphasis" href="tel:+912346578">+912346578</a></td>
                    </tr><tr class="hover-actions-trigger btn-reveal-trigger position-static">
                        <td class="customer align-middle white-space-nowrap"><a class="d-flex align-items-center text-body text-hover-1000" href="#!">
                            <div class="avatar avatar-m"><img class="rounded-circle avatar-placeholder" src="../assets/img/team/avatar.webp" alt=""></div>
                            <h6 class="mb-0 ms-3 fw-semibold">Milind Mikuja</h6>
                        </a></td>
                        <td class="email align-middle white-space-nowrap"><a class="fw-semibold" href="mailto:mimiku@yahoo.com">mimiku@yahoo.com</a></td>
                        <td class="mobile_number align-middle white-space-nowrap"><a class="fw-bold text-body-emphasis" href="tel:+8801564768976">+8801564768976</a></td>
                    </tr><tr class="hover-actions-trigger btn-reveal-trigger position-static">
                        <td class="customer align-middle white-space-nowrap"><a class="d-flex align-items-center text-body text-hover-1000" href="#!">
                            <div class="avatar avatar-m"><img class="rounded-circle" src="../assets/img/team/35.webp" alt=""></div>
                            <h6 class="mb-0 ms-3 fw-semibold">Stanly Drinkwater</h6>
                        </a></td>
                        <td class="email align-middle white-space-nowrap"><a class="fw-semibold" href="mailto:stnlwasser@hotmail.com">stnlwasser@hotmail.com</a></td>
                        <td class="mobile_number align-middle white-space-nowrap"><a class="fw-bold text-body-emphasis" href="tel:+78675436798">+78675436798</a></td>
                    </tr><tr class="hover-actions-trigger btn-reveal-trigger position-static">
                        <td class="customer align-middle white-space-nowrap"><a class="d-flex align-items-center text-body text-hover-1000" href="#!">
                            <div class="avatar avatar-m"><img class="rounded-circle" src="../assets/img/team/57.webp" alt=""></div>
                            <h6 class="mb-0 ms-3 fw-semibold">Josef Stravinsky</h6>
                        </a></td>
                        <td class="email align-middle white-space-nowrap"><a class="fw-semibold" href="mailto:Josefsky@sni.it">Josefsky@sni.it</a></td>
                        <td class="mobile_number align-middle white-space-nowrap"><a class="fw-bold text-body-emphasis" href="tel:+118796567894">+118796567894</a></td>
                    </tr><tr class="hover-actions-trigger btn-reveal-trigger position-static">
                        <td class="customer align-middle white-space-nowrap"><a class="d-flex align-items-center text-body text-hover-1000" href="#!">
                            <div class="avatar avatar-m"><img class="rounded-circle" src="../assets/img/team/58.webp" alt=""></div>
                            <h6 class="mb-0 ms-3 fw-semibold">Igor Borvibson</h6>
                        </a></td>
                        <td class="email align-middle white-space-nowrap"><a class="fw-semibold" href="mailto:vibigorr@technext.it">vibigorr@technext.it</a></td>
                        <td class="mobile_number align-middle white-space-nowrap"><a class="fw-bold text-body-emphasis" href="tel:+65899655678">+65899655678</a></td>
                    </tr><tr class="hover-actions-trigger btn-reveal-trigger position-static">
                        <td class="customer align-middle white-space-nowrap"><a class="d-flex align-items-center text-body text-hover-1000" href="#!">
                            <div class="avatar avatar-m"><img class="rounded-circle" src="../assets/img/team/59.webp" alt=""></div>
                            <h6 class="mb-0 ms-3 fw-semibold">Katerina Karenin</h6>
                        </a></td>
                        <td class="email align-middle white-space-nowrap"><a class="fw-semibold" href="mailto:karkat99@gmail.com">karkat99@gmail.com</a></td>
                        <td class="mobile_number align-middle white-space-nowrap"><a class="fw-bold text-body-emphasis" href="tel:+00564327890">+00564327890</a></td>
                    </tr><tr class="hover-actions-trigger btn-reveal-trigger position-static">
                        <td class="customer align-middle white-space-nowrap"><a class="d-flex align-items-center text-body text-hover-1000" href="#!">
                            <div class="avatar avatar-m">
                                <div class="avatar-name rounded-circle"><span>R</span></div>
                            </div>
                            <h6 class="mb-0 ms-3 fw-semibold">Roy Anderson</h6>
                        </a></td>
                        <td class="email align-middle white-space-nowrap"><a class="fw-semibold" href="mailto:andersonroy@netflix.chill">andersonroy@netflix.chill</a></td>
                        <td class="mobile_number align-middle white-space-nowrap"><a class="fw-bold text-body-emphasis" href="tel:+55890776">+55890776</a></td>
                    </tr><tr class="hover-actions-trigger btn-reveal-trigger position-static">
                        <td class="customer align-middle white-space-nowrap"><a class="d-flex align-items-center text-body text-hover-1000" href="#!">
                            <div class="avatar avatar-m"><img class="rounded-circle" src="../assets/img/team/31.webp" alt=""></div>
                            <h6 class="mb-0 ms-3 fw-semibold">Martina scorcese</h6>
                        </a></td>
                        <td class="email align-middle white-space-nowrap"><a class="fw-semibold" href="mailto:cesetina1@gmail.com">cesetina1@gmail.com</a></td>
                        <td class="mobile_number align-middle white-space-nowrap"><a class="fw-bold text-body-emphasis" href="tel:+666611896">+666611896</a></td>
                    </tr><tr class="hover-actions-trigger btn-reveal-trigger position-static">
                        <td class="customer align-middle white-space-nowrap"><a class="d-flex align-items-center text-body text-hover-1000" href="#!">
                            <div class="avatar avatar-m"><img class="rounded-circle" src="../assets/img/team/33.webp" alt=""></div>
                            <h6 class="mb-0 ms-3 fw-semibold">Luis Bunuel</h6>
                        </a></td>
                        <td class="email align-middle white-space-nowrap"><a class="fw-semibold" href="mailto:luisuel@live.com">luisuel@live.com</a></td>
                        <td class="mobile_number align-middle white-space-nowrap"><a class="fw-bold text-body-emphasis" href="tel:+55786534">+55786534</a></td>
                    </tr><tr class="hover-actions-trigger btn-reveal-trigger position-static">
                        <td class="customer align-middle white-space-nowrap"><a class="d-flex align-items-center text-body text-hover-1000" href="#!">
                            <div class="avatar avatar-m"><img class="rounded-circle" src="../assets/img/team/34.webp" alt=""></div>
                            <h6 class="mb-0 ms-3 fw-semibold">Jean Renoir</h6>
                        </a></td>
                        <td class="email align-middle white-space-nowrap"><a class="fw-semibold" href="mailto:renoirjean1836@gmail.com">renoirjean1836@gmail.com</a></td>
                        <td class="mobile_number align-middle white-space-nowrap"><a class="fw-bold text-body-emphasis" href="tel:+8801765458767">+8801765458767</a></td>
                    </tr>
                    <tr class="hover-actions-trigger btn-reveal-trigger position-static">
                        <td class="customer align-middle white-space-nowrap"><a class="d-flex align-items-center text-body text-hover-1000" href="#!">
                            <div class="avatar avatar-m"><img class="rounded-circle" src="../assets/img/team/32.webp" alt=""></div>
                            <h6 class="mb-0 ms-3 fw-semibold">Carry Anna</h6>
                        </a></td>
                        <td class="email align-middle white-space-nowrap"><a class="fw-semibold" href="mailto:annac34@gmail.com">annac34@gmail.com</a></td>
                        <td class="mobile_number align-middle white-space-nowrap"><a class="fw-bold text-body-emphasis" href="tel:+912346578">+912346578</a></td>
                    </tr><tr class="hover-actions-trigger btn-reveal-trigger position-static">
                        <td class="customer align-middle white-space-nowrap"><a class="d-flex align-items-center text-body text-hover-1000" href="#!">
                            <div class="avatar avatar-m"><img class="rounded-circle avatar-placeholder" src="../assets/img/team/avatar.webp" alt=""></div>
                            <h6 class="mb-0 ms-3 fw-semibold">Milind Mikuja</h6>
                        </a></td>
                        <td class="email align-middle white-space-nowrap"><a class="fw-semibold" href="mailto:mimiku@yahoo.com">mimiku@yahoo.com</a></td>
                        <td class="mobile_number align-middle white-space-nowrap"><a class="fw-bold text-body-emphasis" href="tel:+8801564768976">+8801564768976</a></td>
                    </tr><tr class="hover-actions-trigger btn-reveal-trigger position-static">
                        <td class="customer align-middle white-space-nowrap"><a class="d-flex align-items-center text-body text-hover-1000" href="#!">
                            <div class="avatar avatar-m"><img class="rounded-circle" src="../assets/img/team/35.webp" alt=""></div>
                            <h6 class="mb-0 ms-3 fw-semibold">Stanly Drinkwater</h6>
                        </a></td>
                        <td class="email align-middle white-space-nowrap"><a class="fw-semibold" href="mailto:stnlwasser@hotmail.com">stnlwasser@hotmail.com</a></td>
                        <td class="mobile_number align-middle white-space-nowrap"><a class="fw-bold text-body-emphasis" href="tel:+78675436798">+78675436798</a></td>
                    </tr><tr class="hover-actions-trigger btn-reveal-trigger position-static">
                        <td class="customer align-middle white-space-nowrap"><a class="d-flex align-items-center text-body text-hover-1000" href="#!">
                            <div class="avatar avatar-m"><img class="rounded-circle" src="../assets/img/team/57.webp" alt=""></div>
                            <h6 class="mb-0 ms-3 fw-semibold">Josef Stravinsky</h6>
                        </a></td>
                        <td class="email align-middle white-space-nowrap"><a class="fw-semibold" href="mailto:Josefsky@sni.it">Josefsky@sni.it</a></td>
                        <td class="mobile_number align-middle white-space-nowrap"><a class="fw-bold text-body-emphasis" href="tel:+118796567894">+118796567894</a></td>
                    </tr><tr class="hover-actions-trigger btn-reveal-trigger position-static">
                        <td class="customer align-middle white-space-nowrap"><a class="d-flex align-items-center text-body text-hover-1000" href="#!">
                            <div class="avatar avatar-m"><img class="rounded-circle" src="../assets/img/team/58.webp" alt=""></div>
                            <h6 class="mb-0 ms-3 fw-semibold">Igor Borvibson</h6>
                        </a></td>
                        <td class="email align-middle white-space-nowrap"><a class="fw-semibold" href="mailto:vibigorr@technext.it">vibigorr@technext.it</a></td>
                        <td class="mobile_number align-middle white-space-nowrap"><a class="fw-bold text-body-emphasis" href="tel:+65899655678">+65899655678</a></td>
                    </tr><tr class="hover-actions-trigger btn-reveal-trigger position-static">
                        <td class="customer align-middle white-space-nowrap"><a class="d-flex align-items-center text-body text-hover-1000" href="#!">
                            <div class="avatar avatar-m"><img class="rounded-circle" src="../assets/img/team/59.webp" alt=""></div>
                            <h6 class="mb-0 ms-3 fw-semibold">Katerina Karenin</h6>
                        </a></td>
                        <td class="email align-middle white-space-nowrap"><a class="fw-semibold" href="mailto:karkat99@gmail.com">karkat99@gmail.com</a></td>
                        <td class="mobile_number align-middle white-space-nowrap"><a class="fw-bold text-body-emphasis" href="tel:+00564327890">+00564327890</a></td>
                    </tr><tr class="hover-actions-trigger btn-reveal-trigger position-static">
                        <td class="customer align-middle white-space-nowrap"><a class="d-flex align-items-center text-body text-hover-1000" href="#!">
                            <div class="avatar avatar-m">
                                <div class="avatar-name rounded-circle"><span>R</span></div>
                            </div>
                            <h6 class="mb-0 ms-3 fw-semibold">Roy Anderson</h6>
                        </a></td>
                        <td class="email align-middle white-space-nowrap"><a class="fw-semibold" href="mailto:andersonroy@netflix.chill">andersonroy@netflix.chill</a></td>
                        <td class="mobile_number align-middle white-space-nowrap"><a class="fw-bold text-body-emphasis" href="tel:+55890776">+55890776</a></td>
                    </tr><tr class="hover-actions-trigger btn-reveal-trigger position-static">
                        <td class="customer align-middle white-space-nowrap"><a class="d-flex align-items-center text-body text-hover-1000" href="#!">
                            <div class="avatar avatar-m"><img class="rounded-circle" src="../assets/img/team/31.webp" alt=""></div>
                            <h6 class="mb-0 ms-3 fw-semibold">Martina scorcese</h6>
                        </a></td>
                        <td class="email align-middle white-space-nowrap"><a class="fw-semibold" href="mailto:cesetina1@gmail.com">cesetina1@gmail.com</a></td>
                        <td class="mobile_number align-middle white-space-nowrap"><a class="fw-bold text-body-emphasis" href="tel:+666611896">+666611896</a></td>
                    </tr><tr class="hover-actions-trigger btn-reveal-trigger position-static">
                        <td class="customer align-middle white-space-nowrap"><a class="d-flex align-items-center text-body text-hover-1000" href="#!">
                            <div class="avatar avatar-m"><img class="rounded-circle" src="../assets/img/team/33.webp" alt=""></div>
                            <h6 class="mb-0 ms-3 fw-semibold">Luis Bunuel</h6>
                        </a></td>
                        <td class="email align-middle white-space-nowrap"><a class="fw-semibold" href="mailto:luisuel@live.com">luisuel@live.com</a></td>
                        <td class="mobile_number align-middle white-space-nowrap"><a class="fw-bold text-body-emphasis" href="tel:+55786534">+55786534</a></td>
                    </tr><tr class="hover-actions-trigger btn-reveal-trigger position-static">
                        <td class="customer align-middle white-space-nowrap"><a class="d-flex align-items-center text-body text-hover-1000" href="#!">
                            <div class="avatar avatar-m"><img class="rounded-circle" src="../assets/img/team/34.webp" alt=""></div>
                            <h6 class="mb-0 ms-3 fw-semibold">Jean Renoir</h6>
                        </a></td>
                        <td class="email align-middle white-space-nowrap"><a class="fw-semibold" href="mailto:renoirjean1836@gmail.com">renoirjean1836@gmail.com</a></td>
                        <td class="mobile_number align-middle white-space-nowrap"><a class="fw-bold text-body-emphasis" href="tel:+8801765458767">+8801765458767</a></td>
                    </tr>
                    </tbody>
                </table>
            </div>
        </div>
    </div>




</div>


</@base.themelayout>