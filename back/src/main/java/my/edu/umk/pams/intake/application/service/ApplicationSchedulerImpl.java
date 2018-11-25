package my.edu.umk.pams.intake.application.service;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import my.edu.umk.pams.intake.admission.model.InCandidate;
import my.edu.umk.pams.intake.admission.model.InCandidateStatus;
import my.edu.umk.pams.intake.application.model.InBidStatus;
import my.edu.umk.pams.intake.application.model.InIntakeApplication;
import my.edu.umk.pams.intake.policy.model.InIntake;
import my.edu.umk.pams.intake.policy.model.InIntakeSession;
import my.edu.umk.pams.intake.policy.service.PolicyService;
import my.edu.umk.pams.intake.system.model.InEmailQueue;
import my.edu.umk.pams.intake.system.model.InEmailQueueImpl;
import my.edu.umk.pams.intake.system.model.InEmailQueueStatus;
import my.edu.umk.pams.intake.system.service.SystemService;

@Component("applicationScheduler")
public class ApplicationSchedulerImpl implements ApplicationScheduler {

	private static final Logger LOG = LoggerFactory.getLogger(ApplicationSchedulerImpl.class);

	@Autowired
	private PolicyService policyService;

	@Autowired
	private ApplicationService applicationService;

	@Autowired
	private SystemService systemService;

	@Scheduled(cron = "*/10 * * * * *")
	public void sendEmail() {
		this.reminderApplicant();
	}

	public void reminderApplicant() {
		// // TODO Auto-generated method stub
		// InIntakeSession intakeSession =
		// policyService.findCurrentIntakeSession();
		// List<InIntake> intake = policyService.findIntakes(intakeSession);
		// SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		// Date todayDate = new Date();
		// Calendar cal = Calendar.getInstance();
		// cal.setTime(todayDate);
		// cal.add(Calendar.DATE, 7); // 7 is the days you want to add or
		// subtract
		// todayDate = cal.getTime();
		// String warnDate = sdf.format(todayDate);
		//
		// for (InIntake inIntake : intake) {
		//
		// String endDate = sdf.format(inIntake.getEndDate());
		// if (warnDate.equals(endDate)) {
		// List<InIntakeApplication> draftApplication =
		// applicationService.findIntakeApplications(inIntake,
		// InBidStatus.DRAFTED);
		// LOG.debug("{} reminder email to be sent", draftApplication.size());
		// for (InIntakeApplication inIntakeApplication : draftApplication) {
		// boolean emailQueueExist =
		// systemService.hasEmailQueue(inIntakeApplication.getEmail());
		// if (emailQueueExist == true) {
		// InEmailQueue email = new InEmailQueueImpl();
		// String subject = "Reminder";
		// String body = "Please complete your application. You have 7 days to
		// go.. Hurry up times is running up";
		// // verification.getToken();
		// email.setTo(inIntakeApplication.getEmail());
		// email.setSubject(subject);
		// email.setBody(body);
		// // method send email
		// email.setCode("EQ/" + System.currentTimeMillis());
		// email.setQueueStatus(InEmailQueueStatus.QUEUED);
		// systemService.saveEmailQueue(email);
		// }
		// }
		// }
		// }
		//
		//
	}

}
