package com.solveria.iamservice.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.tags.Tag;

@OpenAPIDefinition(
        info = @Info(
                title = "SolverIA IAM API",
                version = "v1",
                description = "Identity and Access Management API",
                contact = @Contact(
                        name = "SolverIA Platform Team",
                        email = "platform-team@solveria.com"
                ),
                license = @License(
                        name = "Proprietary",
                        url = "https://www.solveria.com/license"
                )
        ),
        tags = {
                @Tag(name = "Roles", description = "Role management operations"),
                @Tag(name = "Permissions", description = "Permission management operations")
        }
)
public class OpenApiConfig {
}
