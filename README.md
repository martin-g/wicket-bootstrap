WICKET COMPONENTS FOR TWITTER BOOTSTRAP
=======================================

Wicket-Bootstrap is based on Twitter's toolkit (bootstrap) and the Apache Wicket Framework.<br>
Current build status: [![Build Status](https://buildhive.cloudbees.com/job/l0rdn1kk0n/job/wicket-bootstrap/badge/icon)](https://buildhive.cloudbees.com/job/l0rdn1kk0n/job/wicket-bootstrap/)

Components
----------

* Twitter Bootstrap (2.2.2): http://twitter.github.com/bootstrap
* Apache Wicket (6.5.0): http://wicket.apache.org/
* Bootswatch (2.2.2): http://bootswatch.com/

Extensions
----------

* DatePicker: https://github.com/eternicode/bootstrap-datepicker
* Html5 Video Player: http://html5-ninja.com/item/Bootstrap-video-player-jQuery-plugin
* Open-On-Hover for dropdown buttons: https://github.com/CWSpear/twitter-bootstrap-hover-dropdown
* JQueryUI (1.9.2): http://api.jqueryui.com/
   * Draggable: http://api.jqueryui.com/draggable
   * Resizable: http://api.jqueryui.com/resizable
* less4j (0.0.9): https://github.com/SomMeri/less4j
* bootstrap-tour: http://sorich87.github.com/bootstrap-tour
* OpenWebIcons (2012-10-03): http://pfefferle.github.com/openwebicons/

Usage
-----

* you can see wicket-bootstrap in action: http://wb.agilecoders.de/
* how to use wicket-bootstrap? Read the documentation on https://github.com/l0rdn1kk0n/wicket-bootstrap/wiki.
* read more on my [blog](http://blog.agilecoders.de/).

## Maven
wicket-bootstrap is [available](http://search.maven.org/#artifactdetails|de.agilecoders.wicket|bootstrap|0.7.4|jar) in Maven central repository.

core maven dependency:
<pre><code>&lt;dependency&gt;
  &lt;groupId&gt;de.agilecoders.wicket&lt;/groupId&gt;
  &lt;artifactId&gt;bootstrap&lt;/artifactId&gt;
  &lt;version&gt;0.7.6&lt;/version&gt;
&lt;/dependency&gt;
</code></pre>

for all extensions:
<pre><code>&lt;dependency&gt;
  &lt;groupId&gt;de.agilecoders.wicket&lt;/groupId&gt;
  &lt;artifactId&gt;bootstrap-extensions&lt;/artifactId&gt;
  &lt;version&gt;0.7.6&lt;/version&gt;
&lt;/dependency&gt;
</code></pre>

Installation
------------
Install bootstrap settings class:

<pre><code>// best place to do this is in Application#init()
Bootstrap.install(Application.get(), new BootstrapSettings());
</code></pre>

then you are able to use all wicket-bootstrap components.

Twitter account
---------------

Keep up to date on announcements and more by following me [@l0rdn1kk0n](http://twitter.com/l0rdn1kk0n) on Twitter or use the [#WicketBootstrap](https://twitter.com/search?q=%23WicketBootstrap&src=typd) hashtag.


Bug tracker
-----------

Have a bug? Please create an issue here on GitHub!

https://github.com/l0rdn1kk0n/wicket-bootstrap/issues


Versioning
----------

Wicket-Bootstrap will be maintained under the Semantic Versioning guidelines as much as possible.

Releases will be numbered with the follow format:

`<major>.<minor>.<patch>`

And constructed with the following guidelines:

* Breaking backward compatibility bumps the major
* New additions without breaking backward compatibility bumps the minor
* Bug fixes and misc changes bump the patch

For more information on SemVer, please visit http://semver.org/.


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
