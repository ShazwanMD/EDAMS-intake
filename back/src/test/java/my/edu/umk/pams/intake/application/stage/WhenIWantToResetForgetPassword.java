package my.edu.umk.pams.intake.application.stage;

import com.tngtech.jgiven.Stage;
import com.tngtech.jgiven.annotation.ExpectedScenarioState;
import com.tngtech.jgiven.annotation.ProvidedScenarioState;
import com.tngtech.jgiven.integration.spring.JGivenStage;

import groovy.util.logging.Log;
import my.edu.umk.pams.intake.application.model.InIntakeApplication;
import my.edu.umk.pams.intake.application.model.InResult;
import my.edu.umk.pams.intake.application.model.InResultImpl;
import my.edu.umk.pams.intake.application.model.InResultType;
import my.edu.umk.pams.intake.application.service.ApplicationService;
import my.edu.umk.pams.intake.common.model.InGradeCode;
import my.edu.umk.pams.intake.common.model.InSpmResult;
import my.edu.umk.pams.intake.common.model.InSpmResultImpl;
import my.edu.umk.pams.intake.common.model.InSpmSubjectCode;
import my.edu.umk.pams.intake.common.model.InSpmSubjectCodeImpl;
import my.edu.umk.pams.intake.common.service.CommonService;
import my.edu.umk.pams.intake.identity.model.InApplicant;
import my.edu.umk.pams.intake.identity.model.InApplicantImpl;
import my.edu.umk.pams.intake.identity.model.InUser;
import my.edu.umk.pams.intake.identity.service.IdentityService;
import my.edu.umk.pams.intake.policy.model.InIntake;
import my.edu.umk.pams.intake.policy.model.InIntakeImpl;
import my.edu.umk.pams.intake.web.module.admission.controller.AdmissionController;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author PAMS
 */
@JGivenStage
public class WhenIWantToResetForgetPassword extends Stage<WhenIWantToResetForgetPassword> {
	
	private static final Logger LOG = LoggerFactory.getLogger(WhenIWantToResetForgetPassword.class);

    @Autowired
    private IdentityService identityService;
    
    @Autowired
    private CommonService commonService;
    
    @Autowired
    private ApplicationService applicationService; 
    
    
    public WhenIWantToResetForgetPassword I_want_to_reset_forget_password() {
    	
    	//Find Intake Application
    	InIntakeApplication application = applicationService.findIntakeApplicationByReferenceNo("201720181-DEGREE-001");
    	LOG.debug("application:{}",application.getApplicant().getName());
    	
    	
    	//Store in result
    	InResult result = new InResultImpl();
    	result.setGraduationYear(2008);
    	result.setName("SPM");
    	result.setResultType(InResultType.SPM);
    	result.setApplication(application);
    	applicationService.addResult(application, result);
    	
    	LOG.debug("Result_id:{}",result.getId());
    	LOG.debug("Result:{}",result.getName());
    	LOG.debug("Result_Application:{}",result.getApplication().getApplicant().getName());
    	
   

    	InGradeCode gradeCode = commonService.findGradeCodeByCode("0");
    	LOG.debug("gradeCode:{}",gradeCode.getDescription());
    	
     	//Store in spm result bridge
    	InSpmSubjectCode spmSubjectCode = new InSpmSubjectCodeImpl();
    	spmSubjectCode.setCode("1");
    	spmSubjectCode.setDescription("Subjek MAT12");
    	
    	commonService.saveSpmSubjectCode(spmSubjectCode);
    	
    	LOG.debug("spmSubjectCode1:{}",spmSubjectCode.getDescription());
    	
    	InSpmResult spmResult1 = new InSpmResultImpl();
    	spmResult1.setSpmSubjectCode(spmSubjectCode);
    	spmResult1.setGradeCode(gradeCode);
    	
    	
    	commonService.saveSpmResult(spmResult1);
    	
    	
    	InSpmSubjectCode spmSubjectCode2 = new InSpmSubjectCodeImpl();
    	spmSubjectCode2.setCode("2");
    	spmSubjectCode2.setDescription("Subjek BM12");
    	
    	commonService.saveSpmSubjectCode(spmSubjectCode2);
    	
    	LOG.debug("spmSubjectCode2:{}",spmSubjectCode2.getDescription());
    	
    	InSpmResult spmResult2 = new InSpmResultImpl();
    	spmResult2.setSpmSubjectCode(spmSubjectCode2);
    	spmResult2.setGradeCode(gradeCode);
    	
    	
    	commonService.saveSpmResult(spmResult2);
    	
    	InSpmSubjectCode spmSubjectCode3 = new InSpmSubjectCodeImpl();
    	spmSubjectCode3.setCode("3");
    	spmSubjectCode3.setDescription("Subjek BI12");
    	
    	commonService.saveSpmSubjectCode(spmSubjectCode3);
    	
    	LOG.debug("spmSubjectCode3:{}",spmSubjectCode3.getDescription());
    	
    	InSpmResult spmResult3 = new InSpmResultImpl();
    	spmResult3.setSpmSubjectCode(spmSubjectCode3);
    	spmResult3.setGradeCode(gradeCode);
    	
    	
    	commonService.saveSpmResult(spmResult3);
    	
    	
    	LOG.debug("spmResultGradeCode_1:{}",spmResult1.getGradeCode().getDescription());
    	LOG.debug("spmResultSubjectCode_1:{}",spmResult1.getSpmSubjectCode().getDescription());
    	
    	LOG.debug("spmResultGradeCode_2:{}",spmResult2.getGradeCode().getDescription());
    	LOG.debug("spmResultSubjectCode_2:{}",spmResult2.getSpmSubjectCode().getDescription());
    	
    	LOG.debug("spmResultGradeCode_3:{}",spmResult3.getGradeCode().getDescription());
    	LOG.debug("spmResultSubjectCode_3:{}",spmResult3.getSpmSubjectCode().getDescription());
    	

    	
    

    	
    	
    	return self();
    	
    }

}

