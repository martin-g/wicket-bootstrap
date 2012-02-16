WICKET COMPONENTS FOR TWITTER BOOTSTRAP
=======================================

Wicket-Bootstrap is based on Twitter's toolkit (bootstrap) and the Apache Wicket Framework.

* Twitter Bootstrap: http://twitter.github.com/bootstrap
* Apache Wicket: http://wicket.apache.org/


Installation
------------

* as maven dependency:
    add the following dependency to your pom:
            <dependency>
                <groupId>de.agile-coders.wicket</groupId>
                <artifactId>bootstrap</artifactId>
                <version>0.1.0</version>
            </dependency>
* as java library:
  download and add the bootstrap-0.1.0.jar to your library path
* use the sources:
  checkout http://twitter.github.com/wicket-bootstrap

Setup
-----

+ extend the BootstrapApplication instead of WebApplication or implement IBootstrapApplication, this is necessary to add the BootstrapSettings to your application.
  you are able to implement the IBootstrapSettings or extend BootstrapSettings to change the path of each file / jquery version / etc by yourself.
+ every Page has to extend the BootstrapPage to ensure that all js/css files will be loaded. If you don't want to extend the BootstrapPage you have to add the following lines of code to your BasePage#renderHead or on each page you want to use a twitter bootstrap component:
  <code>new BootstrapHead(getApplication()).renderHead(response);</code>


Usage
-----

 TBD


Bug tracker
-----------

Have a bug? Please create an issue here on GitHub!

https://github.com/twitter/bootstrap/issues



Copyright and license
---------------------

Copyright 2012 AgileCoders.

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this work except in compliance with the License.
You may obtain a copy of the License in the LICENSE file, or at:

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.