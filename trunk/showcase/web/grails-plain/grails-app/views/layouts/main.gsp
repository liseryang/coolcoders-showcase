<!doctype html>
<html lang="en" class="no-js">
<head>
  <!-- www.phpied.com/conditional-comments-block-downloads/ -->
  <!--[if IE]><![endif]-->

  <!-- Always force latest IE rendering engine (even in intranet) & Chrome Frame
       Remove this if you use the .htaccess -->
  <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
  <meta name="description" content="Cool coders Grails plain showcase">
  <meta name="author" content="cool coders">
  <!--  Mobile Viewport Fix
        j.mp/mobileviewport & davidbcalhoun.com/2010/viewport-metatag
  device-width : Occupy full width of the screen in its current orientation
  initial-scale = 1.0 retains dimensions instead of zooming out if page height > device height
  maximum-scale = 1.0 retains dimensions instead of zooming in if page width < device width
  -->
  <meta name="viewport" content="width=device-width; initial-scale=1.0; maximum-scale=1.0;">


  <title><g:layoutTitle default="Cool coders grails plain"/></title>
  <link rel="stylesheet" href="${resource(dir: 'css', file: 'style.css?v=1')}"/>
  <link rel="stylesheet" media="handheld" href="${resource(dir: 'css', file: 'handheld.css?v=1')}"/>
  <link rel="shortcut icon" href="${resource(dir: 'images', file: 'favicon.ico')}" type="image/x-icon"/>
  <link rel="apple-touch-icon" href="${resource(dir: 'images', file: 'apple-touch-icon.png')}"/>
  <g:layoutHead/>
  <!-- All JavaScript at the bottom, except for Modernizr which enables HTML5 elements & feature detects -->
  <script src="${resource(dir: 'js', file: 'modernizr-1.5.min.js')}"></script>

  <g:javascript library="application"/>
</head>
<!-- paulirish.com/2008/conditional-stylesheets-vs-css-hacks-answer-neither/ -->

<!--[if lt IE 7 ]> <body class="ie6"> <![endif]-->
<!--[if IE 7 ]>    <body class="ie7"> <![endif]-->
<!--[if IE 8 ]>    <body class="ie8"> <![endif]-->
<!--[if IE 9 ]>    <body class="ie9"> <![endif]-->
<!--[if (gt IE 9)|!(IE)]><!--> <body><!--<![endif]-->
<div id="spinner" class="spinner" style="display:none;">
  <img src="${resource(dir: 'images', file: 'spinner.gif')}" alt="${message(code: 'spinner.alt', default: 'Loading...')}"/>
</div>
<div id="container">
  <header class="clearfix">
    <h1>Cool Coders Showcase</h1>
    <div class="userInfo">
      <g:if test="${session.currentUser}">
        <div>
          <g:link controller="profile">${session.currentUser.username}</g:link></div>
        <div>
          <g:link controller="logout"><g:message code="default.logout.label"/></g:link></div>
      </g:if>
      <g:else>
        <div><g:link controller="register"><g:message code="default.register.label"/></g:link></div>
      </g:else>
    </div>
  </header>

  <div id="main">
    <div class="nav">
      <g:pageProperty name="page.breadcrump" default="No Breadcrump found"/>
    </div>
    <g:if test="${flash.message}">
      <div class="message">${flash.message}</div>
    </g:if>
    <g:layoutBody/>
  </div>

  <footer>
    Grails Plain Showcase by <a href="http://www.cool-coders.net" target="_blank">Cool coders</a>
  </footer>
</div> <!--! end of #container -->

<!-- Javascript at the bottom for fast page loading -->

<!-- Grab Google CDN's jQuery. fall back to local if necessary -->
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.4.2/jquery.min.js"></script>
<script>!window.jQuery && document.write('<script src="js/jquery-1.4.2.min.js"><\/script>')</script>


<script src="${resource(dir: 'js', file: 'plugins.js?v=1')}"></script>
<script src="${resource(dir: 'js', file: 'script.js?v=1')}"></script>

<!--[if lt IE 7 ]>
    <script src="${resource(dir: 'js', file: 'dd_belatedpng.js?v=1')}"></script>
  <![endif]-->


<!-- yui profiler and profileviewer - remove for production -->
<script src="${resource(dir: 'js/profiling/', file: 'yahoo-profiling.min.js?v=1')}"></script>
<script src="${resource(dir: 'js/profiling/', file: 'config.js?v=1')}"></script>
<!-- end profiling code -->

</body>
</html>