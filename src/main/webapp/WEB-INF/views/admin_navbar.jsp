<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div class="width">
    <nav class="navbar navbar-default navbar-fixed-top" style="background-color: #5bc0de">
        <div class="container">
            <div class="navbar-header">
                <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar"
                        aria-expanded="false" aria-controls="navbar">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span><span class="icon-bar"></span>
                </button>

            </div>

            <div id="navbar" class="navbar-collapse collapse">
                <div id="menu-product">
                    <ul class="nav navbar-nav">
                        <li><a><span class="nav-text-label color-blue " style="color:#2e6da4"><i style="font-size:20px;color:red" class="fa">&#xf0fa;</i>DOCTOR ADMINISTRATION PANEL</span></a></li>


                        <li id="nav-orders"><a href="/list"><span class="nav-text-label color-blue glyphicon glyphicon-home" style="color:#2e6da4">PATIENTS LIST</span></a></li>
                        <li id="nav-main"><a href="/newuser"> <span class="nav-text-label color-blue glyphicon glyphicon-plus-sign" style="color: #2e6da4;">ADD NEW PATIENT</span></a></li>
                        <li><a href="https://github.com/AntonSyzko/doctoradministrationpanel"><span class="nav-text-label color-blue " style="color: #2e6da4"><i class="fa fa-github"></i>CODE ON GITHUB</span></a></li>
                        <li class="dropdown"><a class="dropdown-toggle" data-toggle="dropdown" href="#" style="color: #2e6da4">SOCIAL <span class="caret"></span></a>
                            <ul class="dropdown-menu">
                                <li><a href="https://www.facebook.com/anton.syzko"><span class="nav-text-label color-blue " style="color: #2e6da4"><i class="fa fa-facebook"></i>FACEBOOK</span></a></li>
                                <li><a href="https://vk.com/id84408030"><span class="nav-text-label color-blue " style="color: #2e6da4"><i class="fa fa-vk"></i>VKONTAKTE</span></a></li>
                                <li><a href="https://uk.linkedin.com/pub/anton-syzko/123/9ab/b70"><span class="nav-text-label color-blue " style="color: #2e6da4"><i class="fa fa-linkedin"></i>LINKEDIN</span></a></li>
                            </ul>
                        </li>

                    </ul>

                    <ul class="nav navbar-nav navbar-right">
                            <li><a href="/logout"><span class="nav-text-label color-blue glyphicon glyphicon-log-out" style="color: #2e6da4">LOGOUT</span></a></li>
                    </ul>
                </div>
            </div>
        </div>
    </nav>
</div>
