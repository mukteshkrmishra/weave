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
package com.continuuity.weave.internal;

import com.continuuity.weave.api.LocalFile;
import com.continuuity.weave.api.ResourceSpecification;
import com.continuuity.weave.api.RuntimeSpecification;
import com.continuuity.weave.api.WeaveRunnableSpecification;
import com.google.common.collect.ImmutableList;

import java.util.Collection;

/**
 * Straightforward implementation of {@link RuntimeSpecification}.
 */
public final class DefaultRuntimeSpecification implements RuntimeSpecification {

  private final String name;
  private final WeaveRunnableSpecification runnableSpec;
  private final ResourceSpecification resourceSpec;
  private final Collection<LocalFile> localFiles;

  public DefaultRuntimeSpecification(String name,
                                     WeaveRunnableSpecification runnableSpec,
                                     ResourceSpecification resourceSpec,
                                     Collection<LocalFile> localFiles) {
    this.name = name;
    this.runnableSpec = runnableSpec;
    this.resourceSpec = resourceSpec;
    this.localFiles = ImmutableList.copyOf(localFiles);
  }

  @Override
  public String getName() {
    return name;
  }

  @Override
  public WeaveRunnableSpecification getRunnableSpecification() {
    return runnableSpec;
  }

  @Override
  public ResourceSpecification getResourceSpecification() {
    return resourceSpec;
  }

  @Override
  public Collection<LocalFile> getLocalFiles() {
    return localFiles;
  }
}
