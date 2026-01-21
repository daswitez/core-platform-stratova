package com.solveria.core.iam.application.usecase;

import com.solveria.core.iam.application.command.AssignPermissionsToRoleCommand;
import com.solveria.core.iam.application.port.PermissionRepositoryPort;
import com.solveria.core.iam.application.port.RoleRepositoryPort;
import com.solveria.core.iam.domain.model.Permission;
import com.solveria.core.iam.domain.model.Role;
import com.solveria.core.shared.exceptions.SolverException;

import java.util.Set;

public class AssignPermissionsToRoleUseCase {

    private final RoleRepositoryPort roleRepository;
    private final PermissionRepositoryPort permissionRepository;

    public AssignPermissionsToRoleUseCase(
            RoleRepositoryPort roleRepository,
            PermissionRepositoryPort permissionRepository
    ) {
        this.roleRepository = roleRepository;
        this.permissionRepository = permissionRepository;
    }

    public Role execute(AssignPermissionsToRoleCommand command) {

        Role role = roleRepository.findById(command.roleId())
                .orElseThrow(() -> new SolverException("IAM_ROLE_NOT_FOUND"));

        if (command.permissionIds() == null || command.permissionIds().isEmpty()) {
            return roleRepository.save(role);
        }

        Set<Long> permissionIds = Set.copyOf(command.permissionIds());
        Set<Permission> permissions = permissionRepository.findByIds(permissionIds);

        if (permissions.size() != permissionIds.size()) {
            throw new SolverException("IAM_PERMISSION_NOT_FOUND");
        }

        role.assignPermissions(permissions);
        return roleRepository.save(role);
    }
}
