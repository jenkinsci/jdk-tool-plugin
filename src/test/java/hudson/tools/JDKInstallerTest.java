package hudson.tools;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assumptions.assumeFalse;

import org.htmlunit.html.HtmlForm;
import org.htmlunit.html.HtmlFormUtil;
import org.htmlunit.html.HtmlPage;
import hudson.tools.JDKInstaller.DescriptorImpl;

import java.io.InputStream;
import java.nio.file.Files;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import hudson.model.JDK;
import hudson.model.TaskListener;
import hudson.util.StreamTaskListener;
import hudson.tools.JDKInstaller.Platform;
import hudson.FilePath;
import hudson.Functions;
import hudson.Launcher.LocalLauncher;

import java.io.File;
import java.util.List;
import java.util.Properties;
import java.util.logging.Logger;

import org.jvnet.hudson.test.JenkinsRule;
import org.jvnet.hudson.test.junit.jupiter.WithJenkins;

/**
 * @author Kohsuke Kawaguchi
 */
@WithJenkins
class JDKInstallerTest {

    private static final Logger LOGGER = Logger.getLogger(JDKInstallerTest.class.getName());

    private JenkinsRule j;

    @TempDir
    private File tmp;

    @BeforeEach
    void beforeEach(JenkinsRule rule) throws Exception {
        j = rule;

        File f = new File(new File(System.getProperty("user.home")),".jenkins-ci.org");
        if (!f.exists()) {
            LOGGER.warning(f + " doesn't exist. Skipping JDK installation tests");
        } else {
            Properties prop = new Properties();
            try (InputStream in = Files.newInputStream(f.toPath())) {
                prop.load(in);
                String u = prop.getProperty("oracle.userName");
                String p = prop.getProperty("oracle.password");
                if (u == null || p == null) {
                    LOGGER.warning(f + " doesn't contain oracle.userName and oracle.password. Skipping JDK installation tests.");
                } else {
                    DescriptorImpl d = j.jenkins.getDescriptorByType(DescriptorImpl.class);
                    d.doPostCredential(u,p);
                }
            }
        }
    }

    @Test
    void enterCredential() throws Exception {
        HtmlPage p = j.createWebClient().goTo("descriptorByName/hudson.tools.JDKInstaller/enterCredential");
        HtmlForm form = p.getFormByName("postCredential");
        form.getInputByName("username").setValue("foo");
        form.getInputByName("password").setValue("bar");
        HtmlFormUtil.submit(form, null);

        DescriptorImpl d = j.jenkins.getDescriptorByType(DescriptorImpl.class);
        assertEquals("foo",d.getUsername());
        assertEquals("bar",d.getPassword().getPlainText());
    }

    /**
     * Tests the configuration round trip.
     */
    @Test
    void configRoundtrip() throws Exception {
        JDKInstaller installer = new JDKInstaller("jdk-6u13-oth-JPR@CDS-CDS_Developer", true);

        j.jenkins.getJDKs().add(new JDK("test", tmp.getAbsolutePath(), List.of(
                new InstallSourceProperty(List.of(installer)))));

        j.submit(j.createWebClient().goTo("configureTools").getFormByName("config"));

        JDK jdk = j.jenkins.getJDK("test");
        InstallSourceProperty isp = jdk.getProperties().get(InstallSourceProperty.class);
        assertEquals(1, isp.installers.size());
        j.assertEqualBeans(installer, isp.installers.get(JDKInstaller.class), "id,acceptLicense");
    }

    /**
     * Fake installation on Unix.
     */
    @Test
    void fakeUnixInstall() throws Exception {
        assumeFalse(Functions.isWindows(), "If we're on Windows, don't bother doing this");

        File bundle = File.createTempFile("fake-jdk-by-hudson","sh");
        try {
            new FilePath(bundle).write(
                    """
                            #!/bin/bash -ex
                            mkdir -p jdk1.6.0_dummy/bin
                            touch jdk1.6.0_dummy/bin/java
                            """,
                    "ASCII");
            TaskListener l = StreamTaskListener.fromStdout();

            new JDKInstaller("",true).install(new LocalLauncher(l), Platform.LINUX,
                    new JDKInstaller.FilePathFileSystem(j.jenkins), l, tmp.getPath(), bundle.getPath());

            assertTrue(new File(tmp,"bin/java").exists());
        } finally {
            bundle.delete();
        }
    }
}
