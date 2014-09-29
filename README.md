WICKET COMPONENTS FOR TWITTER BOOTSTRAP
=======================================

Wicket-Bootstrap is based on Twitter's toolkit (bootstrap) and the Apache Wicket Framework.<br>
Current build status: [![Build Status](https://buildhive.cloudbees.com/job/l0rdn1kk0n/job/wicket-bootstrap/badge/icon)](https://buildhive.cloudbees.com/job/l0rdn1kk0n/job/wicket-bootstrap/) [![Build Status](https://travis-ci.org/l0rdn1kk0n/wicket-bootstrap.png?branch=master)](https://travis-ci.org/l0rdn1kk0n/wicket-bootstrap)

Current release version:

* Wicket 6.* and Bootstrap 3.*: 0.9.6
* Wicket 6.* and Bootstrap 2.*: 0.8.4

Components
----------

* Twitter Bootstrap (3.2.0): https://github.com/twbs/bootstrap/
* Apache Wicket (6.17.0): http://wicket.apache.org/

Themes
------

* Bootstrap (3.2.0): http://getbootstrap.com/
* Vegibit (3.1.1): http://vegibit.com/vegithemes-twitter-bootstrap-themes/
* Bootswatch (3.2.0): http://bootswatch.com/
* todc-bootstrap, google styled theme (3.2.0): http://todc.github.io/todc-bootstrap
* metro-bootstrap (3.1.1-1): https://github.com/TalksLab/metro-bootstrap

Extensions
----------

* DatePicker: https://github.com/eternicode/bootstrap-datepicker
* Html5 Video Player: http://html5-ninja.com/item/Bootstrap-video-player-jQuery-plugin
* Open-On-Hover for dropdown buttons: https://github.com/CWSpear/twitter-bootstrap-hover-dropdown
* JQueryUI (1.9.2): http://api.jqueryui.com/
   * Draggable: http://api.jqueryui.com/draggable
   * Resizable: http://api.jqueryui.com/resizable
* less4j (1.8.2): https://github.com/SomMeri/less4j
* bootstrap-tour (0.9.3): http://sorich87.github.com/bootstrap-tour
* OpenWebIcons (2014-09-29): http://pfefferle.github.com/openwebicons/
* [Twitter Typeahead (0.9.3)](https://github.com/l0rdn1kk0n/wicket-bootstrap/tree/master/bootstrap-extensions/src/main/java/de/agilecoders/wicket/extensions/markup/html/bootstrap/form/typeahead): http://twitter.github.io/typeahead.js/
* [Twitter Typeahead (0.10.x)](https://github.com/l0rdn1kk0n/wicket-bootstrap/tree/master/bootstrap-extensions/src/main/java/de/agilecoders/wicket/extensions/markup/html/bootstrap/form/typeaheadv10): http://twitter.github.io/typeahead.js/

How to get help and news
------------------------

* Keep up to date on announcements and more by following me [@l0rdn1kk0n](http://twitter.com/l0rdn1kk0n) on Twitter or use the [#WicketBootstrap](https://twitter.com/search?q=%23WicketBootstrap&src=typd) hashtag
* use the [google group](https://groups.google.com/d/forum/wicket-bootstrap) (wicket-bootstrap (at) googlegroups.com)
* you can see wicket-bootstrap in action: http://wb.agilecoders.de/
* how to use wicket-bootstrap? Read the documentation on https://github.com/l0rdn1kk0n/wicket-bootstrap/wiki.
* read more on my [blog](http://blog.agilecoders.de/).

## Maven
wicket-bootstrap is [available](http://search.maven.org/#artifactdetails|de.agilecoders.wicket|wicket-bootstrap-core|0.9.5|jar) in Maven central repository.

core maven dependency:

```xml
<dependency>
    <groupId>de.agilecoders.wicket</groupId>
    <artifactId>wicket-bootstrap-core</artifactId>
    <version>0.9.5</version>
</dependency>
```

for all extensions:

```xml
<dependency>
    <groupId>de.agilecoders.wicket</groupId>
    <artifactId>wicket-bootstrap-extensions</artifactId>
    <version>0.9.5</version>
</dependency>
```

for all themes:

```xml
<dependency>
    <groupId>de.agilecoders.wicket</groupId>
    <artifactId>wicket-bootstrap-themes</artifactId>
    <version>0.9.5</version>
</dependency>
```

if you want to use a less compiler:

```xml
<dependency>
    <groupId>de.agilecoders.wicket</groupId>
    <artifactId>wicket-bootstrap-less</artifactId>
    <version>0.9.5</version>
</dependency>
```

all samples can be used with this dependency:

```xml
<dependency>
    <groupId>de.agilecoders.wicket</groupId>
    <artifactId>wicket-bootstrap-samples</artifactId>
    <version>0.9.5</version>
</dependency>
```

Installation
------------
Install bootstrap settings class:

```java
// best place to do this is in Application#init()
Bootstrap.install(this);

// if you want to customize bootstrap:
BootstrapSettings settings = new BootstrapSettings();
settings.setXXX(...);
Bootstrap.install(this, settings);
```

then you are able to use all wicket-bootstrap components.

Authors
-------

[![Ohloh profile for Michael Haitz](https://www.ohloh.net/accounts/235496/widgets/account_detailed.gif)](https://www.ohloh.net/accounts/235496?ref=Detailed)

[![Ohloh profile for Martin Grigorov](https://www.ohloh.net/accounts/138913/widgets/account_detailed.gif)](https://www.ohloh.net/accounts/138913?ref=Detailed)

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
