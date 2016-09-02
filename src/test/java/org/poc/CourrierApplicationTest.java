/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.poc;

import java.net.ConnectException;
import org.junit.Test;
import org.junit.Rule;
import org.springframework.boot.test.rule.OutputCapture;
import org.springframework.core.NestedCheckedException;
import static org.assertj.core.api.Assertions.assertThat;

/**
 *
 * @author simon.pascal.ngos
 */
public class CourrierApplicationTest {

    @Rule
    public OutputCapture outputCapture = new OutputCapture();

    @Test
    public void testDefaultSettings() throws Exception {
        try {
            CourrierApplication.main(new String[]{"--server.port=0"});
        } catch (IllegalStateException ex) {
            if (serverNotRunning(ex)) {
                return;
            }
        }
        String output = this.outputCapture.toString();
           assertThat(output).contains("Successfully acquired change log lock")
               .contains("Creating database history "
                        + "table with name: PUBLIC.DATABASECHANGELOG")
                /* .contains("Table person created")
                .contains("ChangeSet classpath:/db/"
                        + "changelog/changelog.xml::1::"
                        + "Simon ran successfully")
                .contains("New row inserted into person")
                .contains("ChangeSet classpath:/db/changelog/"
                        + "changelog.xml::2::"
                        + "Simon ran successfully")
                .contains("Successfully released change log lock")*/;
    }

    @SuppressWarnings("serial")
    private boolean serverNotRunning(IllegalStateException ex) {
        NestedCheckedException nested = new NestedCheckedException("failed", ex) {
        };
        if (nested.contains(ConnectException.class)) {
            Throwable root = nested.getRootCause();
            if (root.getMessage().contains("Connection refused")) {
                return true;
            }
        }
        return false;
    }

}
