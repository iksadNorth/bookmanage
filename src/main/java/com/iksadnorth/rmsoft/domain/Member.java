package com.iksadnorth.rmsoft.domain;

import com.iksadnorth.rmsoft.type.MemberRoleType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter @Setter @NoArgsConstructor
public class Member {
    private Long id;
    private String username;
    private String password;
    private MemberRoleType roleType;
}
