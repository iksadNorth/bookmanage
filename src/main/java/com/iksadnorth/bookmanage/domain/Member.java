package com.iksadnorth.bookmanage.domain;

import com.iksadnorth.bookmanage.type.MemberRoleType;
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
