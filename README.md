Oracle Java SE Development Kit Installer Plugin
===============================================

This plugin provides a toool installer for installing [Oracle Java SE Development Kit](https://www.oracle.com/technetwork/java/javase/) in Jenkins. 
See this [plugin's wiki page](https://wiki.jenkins.io/display/JENKINS/JDK+Tool+Plugin).
This plugin was formerly known as "JDK Tool Plugin", but it was renamed in order to reflect what it actually does.

## License constraints

There are some license constraints which should be taken into account while using this plugin.

* Oracle Java SE 11+ is not available for business, commercial or production use without a commercial license.
* Public updates for Oracle Java SE 8 released after January 2019 are not be available for business, commercial or production use without a commercial license.

See [Oracle Java SE License FAQ](https://www.oracle.com/technetwork/java/javase/overview/oracle-jdk-faqs.html) for more information.
If you use this plugin on your instance, make sure to be compliant with license terms.

## Alternative solutions

As a general recommendation, it is advised not to rely on external downloads in your CI systems.
For example, JDK can be preinstalled in Docker images.
ZIP Tool Installer can be also used to download the JDK tool from a local infrastructure.
If an external download is needed due to any reason, 
[AdoptOpenJDK Plugin](https://plugins.jenkins.io/adoptopenjdk) might be used instead of this plugin.
