package com.hy.security.view.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Created by WangShuai on 2016/7/21.
 */
public class UserReqView {

    private String name;

    @JsonProperty("phone_number")
    private String phoneNumber;

    private String email;

    @JsonProperty("role_ids")
    private List<Long> roleIds;

    @JsonProperty("city_ids")
    private List<Long> cityIds;


}
