package vn.spring.rnd.Common.Startup;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import vn.spring.rnd.Common.Models.Module;
import vn.spring.rnd.Common.Models.Permission;
import vn.spring.rnd.Common.Models.User;
import vn.spring.rnd.Common.Repository.ModuleRepository;
import vn.spring.rnd.Common.Repository.PermissionRepository;
import vn.spring.rnd.Common.Repository.UserRepository;
import vn.spring.rnd.Permission.Dtos.CreateModuleDTO;
import vn.spring.rnd.Permission.Dtos.CreatePermissionDTO;
import vn.spring.rnd.Permission.Seeder.InitPermission;
import vn.spring.rnd.User.Constant.EAccountType;

@Component
public class StartupLoader implements ApplicationListener<ContextRefreshedEvent> {
  @Autowired
  private UserRepository userRepository;

  @Autowired
  private ModuleRepository moduleRepository;

  @Autowired
  private PermissionRepository permissionRepository;

  @Autowired
  private PasswordEncoder passwordEncoder;

  @Value("${external.app.super_admin_username}")
  private String superAdminUsername;

  @Value("${external.app.super_admin_password}")
  private String superAdminPassword;

  @Value("${external.app.super_admin_email}")
  private String superAdminEmail;

  private static final Logger logger = LoggerFactory.getLogger(StartupLoader.class);

  @Override
  public void onApplicationEvent(ContextRefreshedEvent event) {
    createAdminUser();
    generatePermissions();
  }

  private void createAdminUser() {
    User user = userRepository.findByUsername(superAdminUsername).orElse(null);
    if (user == null) {
      user = new User();
      user.setUsername(superAdminUsername);
      user.setAccountType(EAccountType.SUPER_ADMIN);
      user.setFullName("Admin");
      user.setPassword(passwordEncoder.encode(superAdminPassword));
      user.setEmail(superAdminEmail);
      userRepository.save(user);
      logger.info("createAdminUser successfully!");
    }
  }

  private void generatePermissions() {
    logger.info("Start generatePermissions...");
    try {
      List<CreateModuleDTO> modules = InitPermission.init();
      for (CreateModuleDTO iModule : modules) {
        Module module = moduleRepository.findByName(iModule.name());
        if (module == null)
          module = new Module();

        module.setName(iModule.name());
        module.setDescription(iModule.description());
        moduleRepository.save(module);

        List<Permission> permissions = new ArrayList<>();
        for (CreatePermissionDTO iPerm : iModule.permissions()) {
          Permission permission = permissionRepository.findByName(iPerm.name());
          if (permission == null) {
            permission = new Permission();
          }

          permission.setName(iPerm.name());
          permission.setModule(module);
          permission.setDescription(iPerm.description());
          permissions.add(permission);
        }

        permissionRepository.saveAll(permissions);

      }
    } catch (Exception e) {
      logger.error("ERROR generatePermissions: {}", e.getMessage());
    } finally {
      logger.info("End generatePermissions");
    }
  }
}
