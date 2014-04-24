/**
 * Copyright (C) 2013 Microsoft Corporation
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.microsoft.reef.webserver;

import javax.inject.Inject;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.microsoft.wake.EventHandler;
import com.microsoft.wake.time.runtime.event.RuntimeStart;

/**
 *  RuntimeStartHandler for Http server
 */
public class HttpRuntimeStartHandler implements EventHandler<RuntimeStart> {
    private static final Logger LOG = Logger.getLogger(HttpRuntimeStartHandler.class.getName());

    private IHttpServer httpServer;

    /**
     * Constructor of HttpRuntimeStartHandler. It has a reference of HttpServer
     * @param httpServer
     */
    @Inject
    public HttpRuntimeStartHandler(HttpServer httpServer)
    {
        this.httpServer = httpServer;
    }

    /**
     * Override EventHandler<RuntimeStart>
     * @param runtimeStart
     */
    @Override
    public synchronized void onNext(final RuntimeStart runtimeStart) {
        LOG.log(Level.FINEST, "HttpRuntimeStartHandler: {0}", runtimeStart);
        try {
            httpServer.start();
            LOG.log(Level.FINEST, "HttpRuntimeStartHandler complete.");
        } catch (Exception e) {
            LOG.log(Level.SEVERE, "HttpRuntimeStartHandler cannot start the Server." + e);
        }
    }
}
