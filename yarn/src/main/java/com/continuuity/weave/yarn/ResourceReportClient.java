/*
 * Copyright 2012-2013 Continuuity,Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package com.continuuity.weave.yarn;

import com.continuuity.weave.api.ResourceReport;
import com.continuuity.weave.internal.json.ResourceReportAdapter;
import com.google.common.base.Charsets;
import com.google.common.io.Closeables;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;

/**
 * Package private class to get {@link ResourceReport} from the application master.
 */
final class ResourceReportClient {
  private static final Logger LOG = LoggerFactory.getLogger(ResourceReportClient.class);

  private final ResourceReportAdapter reportAdapter;
  private final URL resourceUrl;

  ResourceReportClient(URL resourceUrl) {
    this.resourceUrl = resourceUrl;
    this.reportAdapter = ResourceReportAdapter.create();
  }

  /**
   * Returns the resource usage of the application fetched from the resource endpoint URL.
   * @return A {@link ResourceReport} or {@code null} if failed to fetch the report.
   */
  public ResourceReport get() {
    try {
      Reader reader = new BufferedReader(new InputStreamReader(resourceUrl.openStream(), Charsets.UTF_8));
      try {
        return reportAdapter.fromJson(reader);
      } finally {
        Closeables.closeQuietly(reader);
      }
    } catch (Exception e) {
      LOG.error("Exception getting resource report from {}.", resourceUrl, e);
      return null;
    }
  }
}
