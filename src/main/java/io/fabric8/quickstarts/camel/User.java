/*
 * Copyright 2005-2016 Red Hat, Inc.
 *
 * Red Hat licenses this file to you under the Apache License, version
 * 2.0 (the "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or
 * implied.  See the License for the specific language governing
 * permissions and limitations under the License.
 */
package io.fabric8.quickstarts.camel;

import org.apache.commons.lang3.builder.ToStringBuilder;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.Extension;
import io.swagger.annotations.ExtensionProperty;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * User entity
 *
 */
@JsonAutoDetect
@ApiModel(description = "Represents an user of the system")
public class User {

    @ApiModelProperty(value = "The 3scale application ID", required = true, extensions = { @Extension(properties = { @ExtensionProperty(name = "x-data-threescale-name", value = "app_ids")})})
    private String appId;

    @ApiModelProperty(value = "The 3scale application key", required = true, extensions = { @Extension(properties = { @ExtensionProperty(name = "x-data-threescale-name", value = "app_keys")})})
    private String appKey;

    @ApiModelProperty(value = "The name of the user", required = true)
    private String name;

    public User() {
    }

    @JsonCreator
    public User(@JsonProperty("appId") String appId,
                @JsonProperty("appKey") String appKey,
                @JsonProperty("name") String name) {
        this.appId = appId;
        this.appKey = appKey;
        this.name = name;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getAppKey() {
        return appKey;
    }

    public void setAppKey(String appKey) {
        this.appKey = appKey;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

}
