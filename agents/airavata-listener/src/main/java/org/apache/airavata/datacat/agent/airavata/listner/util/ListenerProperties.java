/*
 *
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 *
*/
package org.apache.airavata.datacat.agent.airavata.listner.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;

public class ListenerProperties {

    private final static Logger logger = LoggerFactory.getLogger(ListenerProperties.class);

    public static final String LISTENER_PROPERTY_FILE = "../conf/airavata-listener.properties";
    public static final String DEFAULT_LISTENER_PROPERTY_FILE = "conf/airavata-listener.properties";

    private static ListenerProperties instance;

    private java.util.Properties properties = null;

    private ListenerProperties() {
        try {
            InputStream fileInput;
            if (new File(LISTENER_PROPERTY_FILE).exists()) {
                fileInput = new FileInputStream(LISTENER_PROPERTY_FILE);
                logger.info("Using configured listener property (airavata-listener.properties) file");
            } else {
                logger.info("Using default listener property (airavata-listener) file");
                fileInput = ClassLoader.getSystemResource(DEFAULT_LISTENER_PROPERTY_FILE).openStream();
            }
            java.util.Properties properties = new java.util.Properties();
            properties.load(fileInput);
            fileInput.close();
            this.properties = properties;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static ListenerProperties getInstance() {
        if (instance == null) {
            instance = new ListenerProperties();
        }
        return instance;
    }

    public String getProperty(String key, String defaultVal) {
        String val = this.properties.getProperty(key);

        if (val.isEmpty() || val == "") {
            return defaultVal;
        } else {
            return val;
        }
    }
}