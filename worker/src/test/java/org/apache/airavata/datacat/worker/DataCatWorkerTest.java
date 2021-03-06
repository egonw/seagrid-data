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
package org.apache.airavata.datacat.worker;

import junit.framework.Assert;
import org.apache.airavata.datacat.commons.CatalogFileRequest;
import org.apache.airavata.datacat.commons.DataTypes;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;

public class DataCatWorkerTest {
    private final static Logger logger = LoggerFactory.getLogger(DataCatWorkerTest.class);

    @Test
    public void testDataCatWorker(){
        try{
            DataCatWorker datacatWorker = new DataCatWorker();
            CatalogFileRequest catalogFileRequest = new CatalogFileRequest();
            HashMap<String, Object> ingestMetadata = new HashMap<>();
            ingestMetadata.put("Id", "test-000000-0000000-000000000-00000000000");
            ingestMetadata.put("ExperimentName", "test-000000-0000000-000000000-00000000000");
            ingestMetadata.put("ProjectName", "mvn_proj");
            ingestMetadata.put("Username", "scnakandala");
            catalogFileRequest.setIngestMetadata(ingestMetadata);
            catalogFileRequest.setMimeType(DataTypes.APPLICATION_GAUSSIAN);
            catalogFileRequest.setDirUri("file://"+DataCatWorkerTest.class.getResource("/gaussian").getPath());
            datacatWorker.handle(catalogFileRequest);
        }catch (Exception ex){
            ex.printStackTrace();
            Assert.fail();
        }
    }
}