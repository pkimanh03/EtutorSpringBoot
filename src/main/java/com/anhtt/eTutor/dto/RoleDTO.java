package com.anhtt.eTutor.dto;

import com.anhtt.eTutor.model.RoleName;

public class RoleDTO {
    Long id;
    RoleName name;

    public RoleDTO(Long id, RoleName name) {
        this.id = id;
        this.name = name;
    }

    public RoleDTO() {
    }

    public static RoleDTOBuilder builder() {
        return new RoleDTOBuilder();
    }

    public Long getId() {
        return this.id;
    }

    public RoleName getName() {
        return this.name;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(RoleName name) {
        this.name = name;
    }

    public static class RoleDTOBuilder {
        private Long id;
        private RoleName name;

        RoleDTOBuilder() {
        }

        public RoleDTO.RoleDTOBuilder id(Long id) {
            this.id = id;
            return this;
        }

        public RoleDTO.RoleDTOBuilder name(RoleName name) {
            this.name = name;
            return this;
        }

        public RoleDTO build() {
            return new RoleDTO(id, name);
        }

        public String toString() {
            return "RoleDTO.RoleDTOBuilder(id=" + this.id + ", name=" + this.name + ")";
        }
    }
}
