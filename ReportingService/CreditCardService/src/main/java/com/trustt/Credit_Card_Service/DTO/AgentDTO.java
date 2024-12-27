package com.trustt.Credit_Card_Service.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AgentDTO {
    private String name;
    private String email;
    private Integer no_of_success;
    private Integer companyId;;
}
