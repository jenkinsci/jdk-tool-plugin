Oracle Java SE Development Kit Installer Plugin
===============================================

This plugin provides a toool installer for installing [Oracle Java SE Development Kit](https://www.oracle.com/technetwork/java/javase/) in Jenkins. 
See this [plugin's wiki page](https://wiki.jenkins.io/display/JENKINS/JDK+Tool+Plugin).
This plugin was formerly known as "JDK Tool Plugin", but it was renamed in order to reflect what it actually does.

## License constraints

There are some license constrains which should be taken into account while using this plugin.

* Oracle Java SE 11+ is not available for business, commercial or production use without a commercial license.
* Public updates for Oracle Java SE 8 released after January 2019 are not be available for business, commercial or production use without a commercial license.

See [Oracle Java SE License FAQ](https://www.oracle.com/technetwork/java/javase/overview/oracle-jdk-faqs.html) for more information.
If you use this plugin on your instance, make sure to be compliant with license terms.

[AdoptOpenJDK Plugin](https://plugins.jenkins.io/adoptopenjdk) is a recommended replacement for those ones who want to modern JDK versions without the license restrictions mentioned above.
