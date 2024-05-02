<#import "master.ftl" as base>
<@base.layout>

<main class="main" id="top">
    <div class="container">
        <div class="row flex-center min-vh-100 py-5">
            <div class="col-sm-10 col-md-8 col-lg-5 col-xl-5 col-xxl-3"><a class="d-flex flex-center text-decoration-none mb-4" href="../../../index.html">
                <div class="d-flex align-items-center fw-bolder fs-5 d-inline-block"><img src="../../../assets/img/icons/logo.png" alt="phoenix" width="58" />
                </div>
            </a>
                <div class="text-center mb-7">
                    <h3 class="text-1000">Sign In</h3>
                </div>
                <button class="btn btn-phoenix-secondary w-100 mb-3"><span class="fab fa-google text-danger me-2 fs--1"></span>Sign in with google</button>
                <div class="position-relative">
                    <hr class="bg-200 mt-5 mb-4" />
                    <div class="divider-content-center">or use email</div>
                </div>
                <form action="/login" method="post">
                    <div class="mb-3 text-start">
                        <div class="form-icon-container">
                            <input class="form-control form-icon-input" id="username" name="username" placeholder="email"/><span class="fas fa-user text-900 fs--1 form-icon"></span>
                        </div>
                    </div>
                    <div class="mb-3 text-start">
                        <label class="form-label" for="password">Password</label>
                        <div class="form-icon-container">
                            <input class="form-control form-icon-input" id="password" name="password" type="password" placeholder="password"/><span class="fas fa-key text-900 fs--1 form-icon"></span>
                        </div>
                    </div>
                    <div class="row flex-between-center mb-7">
                        <div class="col-auto">
                        </div>
                        <div class="col-auto"><a class="fs--1 fw-semi-bold" href="../../../pages/authentication/simple/forgot-password.html">Forgot Password?</a></div>
                    </div>
                    <button class="btn btn-primary w-100 mb-3" type="submit">Sign In</button>
                </form>
                <div class="text-center"><a class="fs--1 fw-bold" href="../../../pages/authentication/simple/sign-up.html">Create an account</a></div>
            </div>
        </div>
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