package com.powerup.user.adapter;

import com.powerup.user.domain.model.Role;

public class SaveRoleJpaAdapterDataTest {

    public static Role obtainRole(){
        Role role = new Role(
                2L,
                "ROLE_PROPRIETARY",
                "Proprietary"
        );

        return role;
    }

}
