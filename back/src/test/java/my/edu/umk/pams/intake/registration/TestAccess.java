package my.edu.umk.pams.intake.registration;

import org.jfree.util.Log;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import my.edu.umk.pams.intake.config.TestAppConfiguration;
import my.edu.umk.pams.intake.identity.model.InGroup;
import my.edu.umk.pams.intake.identity.model.InPrincipal;
import my.edu.umk.pams.intake.identity.service.IdentityService;
import my.edu.umk.pams.intake.security.integration.InPermission;
import my.edu.umk.pams.intake.security.service.AccessService;
import my.edu.umk.pams.intake.system.model.InModule;
import my.edu.umk.pams.intake.system.service.SystemService;

@RunWith(SpringRunner.class)
@Transactional
@ContextConfiguration(classes = TestAppConfiguration.class)
public class TestAccess {

    @Autowired
    IdentityService identityService;

    @Autowired
    SystemService systemService;

    @Autowired
    AccessService accessService;

    @Test
    @Rollback(false)
    public void testAccess() {
        InGroup group = identityService.findGroupByName("GRP_APCN");
        InModule module = systemService.findModuleByCode("APN");
        accessService.grantPermission(module, group, InPermission.VIEW);
    }

    @Test
    @Rollback(false)
    public void testAccessToRoot() {
        InPrincipal root = identityService.findPrincipalByName("root");
        InModule stmModule = systemService.findModuleByCode("STM");
        InModule idtModule = systemService.findModuleByCode("IDT");
        InModule apnModule = systemService.findModuleByCode("APN");
        InModule rgnModule = systemService.findModuleByCode("RGN");
        InModule admModule = systemService.findModuleByCode("ADM");
        InModule plcModule = systemService.findModuleByCode("PLC");
        accessService.grantPermission(stmModule, root, InPermission.VIEW);
        accessService.grantPermission(idtModule, root, InPermission.VIEW);
        accessService.grantPermission(apnModule, root, InPermission.VIEW);
        accessService.grantPermission(plcModule, root, InPermission.VIEW);
        accessService.grantPermission(rgnModule, root, InPermission.VIEW);
        accessService.grantPermission(admModule, root, InPermission.VIEW);
    }
}
