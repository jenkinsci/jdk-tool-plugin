Oracle Java SE Development Kit Installer Plugin
===============================================

[![Jenkins Plugin](https://img.shields.io/jenkins/plugin/v/jdk-tool.svg)](https://plugins.jenkins.io/jdk-tool)
[![GitHub release](https://img.shields.io/github/release/jenkinsci/jdk-tool-plugin.svg?label=changelog)](https://github.com/jenkinsci/jdk-tool-plugin/releases/latest)

This plugin provides a tool installer for installing [Oracle Java SE Development Kit](https://www.oracle.com/technetwork/java/javase/) in Jenkins. 

The installer provided by the plugin used to be a part of the Jenkins core before it was detached to a separate plugin in [Jenkins 2.112](https://jenkins.io/changelog/#v2.112). 
Before version `1.3` the plugin was named as "JDK Tool Plugin", but it was renamed later to reflect what it actually does.

## Usage recommendations

We want to warn that this plugin is **NOT** a good practice for production environments. 
As it relies on the Oracle's website to do the job, it's highly likely to stop working. 
It could happen because Oracle's website change or even if Oracle bans our downloads due to excessive bandwidth or whatever other reason). 

The recommended approach is to download the JDK distribution using other installers, for example downloading it from a well known URL (preferably hosted on your own network) with _ZIP Tool Installer_, having it pre-installed in agent docker images, or executing a script to do the job.
If an external download is needed due to any reason, 
[AdoptOpenJDK Plugin](https://plugins.jenkins.io/adoptopenjdk) might be used instead of this plugin.

## License constraints

There are some license constraints which should be taken into account while using this plugin.

* Oracle Java SE 11+ is not available for business, commercial or production use without a commercial license.
* Public updates for Oracle Java SE 8 released after January 2019 are not be available for business, commercial or production use without a commercial license.

See [Oracle Java SE License FAQ](https://www.oracle.com/technetwork/java/javase/overview/oracle-jdk-faqs.html) for more information.
If you use this plugin on your instance, make sure to be compliant with license terms.

## Changelog

See [GitHub Releases](https://github.com/jenkinsci/jdk-tool-plugin/releases)
