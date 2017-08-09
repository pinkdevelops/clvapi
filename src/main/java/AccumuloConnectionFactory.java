/**
 * Copyright 2017 Hortonworks.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at

 *   http://www.apache.org/licenses/LICENSE-2.0

 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 **/

import org.apache.accumulo.core.client.*;
import org.apache.accumulo.core.client.security.tokens.PasswordToken;
import org.apache.accumulo.core.security.Authorizations;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class AccumuloConnectionFactory {

    private String instanceName;
    private String zooKeeperServerList;
    private String username;
    private String password;
    private String roles;

    public Scanner getScanner() {
        Properties prop = getConfigs();
        String instanceName = prop.getProperty("instance");
        String zooServers = prop.getProperty("zooServers");
        Instance inst = new ZooKeeperInstance(instanceName, zooServers);
        Scanner scan = null;

        try {
            Connector conn = inst.getConnector(prop.getProperty("user"), new PasswordToken(prop.getProperty("pass")));
            scan = conn.createScanner(prop.getProperty("table"), new Authorizations(prop.getProperty("auths")));
        } catch (AccumuloException e) {
            e.printStackTrace();
        } catch (AccumuloSecurityException e) {
            e.printStackTrace();
        } catch (TableNotFoundException e) {
            e.printStackTrace();
        }
        return scan;
    }

    public String getInstanceName() {
        return instanceName;
    }

    public void setInstanceName(String instanceName) {
        this.instanceName = instanceName;
    }

    public String getZooKeeperServerList() {
        return zooKeeperServerList;
    }

    public void setZooKeeperServerList(String zooKeeperServerList) {
        this.zooKeeperServerList = zooKeeperServerList;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }

    public Properties getConfigs() {
        Properties prop = new Properties();
        InputStream input = null;

        try {

            String filename = "config.properties";
            input = AccumuloMain.class.getClassLoader().getResourceAsStream(filename);
            if (input == null) {
                System.out.println("Unable to find " + filename);
                return null;
            }

            prop.load(input);

        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return prop;
    }
}