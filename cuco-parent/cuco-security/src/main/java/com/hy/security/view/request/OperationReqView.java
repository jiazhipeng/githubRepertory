package com.hy.security.view.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Created by zc.du 2016/7/29.
 */
public class OperationReqView {

    private String name;

    @JsonProperty("system_id")
    private String systemId;

    private String email;

    @JsonProperty("role_ids")
    private List<Long> roleIds;

    @JsonProperty("city_ids")
    private List<Long> cityIds;


}
