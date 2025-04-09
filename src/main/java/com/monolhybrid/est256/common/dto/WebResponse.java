package com.monolhybrid.est256.common.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.monolhybrid.est256.module.auth.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class WebResponse <T> {
    private String message;
    private Integer status;
    private T data;
    private User.RoleBase role;
    private List<String> errors;
}
