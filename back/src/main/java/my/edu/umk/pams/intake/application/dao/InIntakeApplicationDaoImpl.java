package my.edu.umk.pams.intake.application.dao;

import my.edu.umk.pams.intake.application.model.*;
import my.edu.umk.pams.intake.common.model.InPromoCode;
import my.edu.umk.pams.intake.core.GenericDaoSupport;
import my.edu.umk.pams.intake.core.InMetaState;
import my.edu.umk.pams.intake.core.InMetadata;
import my.edu.umk.pams.intake.identity.model.InApplicant;
import my.edu.umk.pams.intake.identity.model.InUser;
import my.edu.umk.pams.intake.policy.model.InIntake;

import org.apache.commons.lang.Validate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;

@Repository("intakeApplicationDao")
public class InIntakeApplicationDaoImpl extends GenericDaoSupport<Long, InIntakeApplication> implements InIntakeApplicationDao {

    public InIntakeApplicationDaoImpl() {
        super(InIntakeApplicationImpl.class);
    }

    // ====================================================================================================
    // FINDER
    // ====================================================================================================

    @Override
    public InIntakeApplication findByReferenceNo(String referenceNo) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select p from InIntakeApplication p " +
                "where p.referenceNo = :referenceNo ");
        query.setString("referenceNo", referenceNo);
        return (InIntakeApplication) query.uniqueResult();
    }

    // TODO(ashraf): OR passport no  where (p.x = :n or p.y = :n)
    @Override
    public InIntakeApplication findByNricNoOrPassportNo(String identityNo) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select p from InIntakeApplication p " +
                "where p.nricNo = :nricNo ");
        query.setString("nricNo", identityNo);
        return (InIntakeApplication) query.uniqueResult();
    }
    
    @Override
    public InIntakeApplication findByAddress(String officialAddress1) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select p from InIntakeApplication p " +
        		"where p.officialAddress1 = :officialAddress1 ");
        query.setString("officialAddress1", officialAddress1);
        return (InIntakeApplication) query.uniqueResult();
    }

    @Override
    public InIntakeApplication findByIntakeAndApplicant(InIntake intake, InApplicant applicant) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select p from InIntakeApplication p " +
                "where p.applicant = :applicant " +
                "and p.intake = :intake ");
        query.setEntity("applicant", applicant);
        query.setEntity("intake", intake);
        return (InIntakeApplication) query.uniqueResult();
    }
    
    @Override
    public InIntakeApplication findByApplicant(InApplicant applicant) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select p from InIntakeApplication p " +
                "where p.applicant = :applicant ");
        query.setEntity("applicant", applicant);
        return (InIntakeApplication) query.uniqueResult();
    }
    
    @Override
    public boolean isIntakeApplicationExists(InIntake intake, InApplicant applicant) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select count(*) from InIntakeApplication s where " +
                "s.intake.session.year = :year " +
                "and s.applicant = :applicant " +
                "and s.metadata.state = :state ");
        query.setEntity("applicant", applicant);
        query.setInteger("year", intake.getSession().getYear());
        query.setInteger("state", InMetaState.ACTIVE.ordinal());
        return 0 < ((Long) query.uniqueResult()).intValue();
    }
    
    @Override
    public boolean isPromoCodeEntered(InPromoCode promoCode) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select count(*) from InIntakeApplication s where " +
                "s.promoCode = :promoCode " +
                "and s.metadata.state = :state ");
        query.setEntity("promoCode", promoCode);
        query.setInteger("state", InMetaState.ACTIVE.ordinal());
        return 0 < ((Long) query.uniqueResult()).intValue();
    }

    @Override
    public InResult findResultById(Long id) {
        Session currentSession = sessionFactory.getCurrentSession();
        return (InResult) currentSession.get(InResultImpl.class, id);
    }

    @Override
    public InResult findResult(InIntakeApplication application, InResultType resultType) {
        Session currentSession = sessionFactory.getCurrentSession();
        Query query = currentSession.createQuery("select p from InResult p where " +
                "p.application = :application " +
                "and p.resultType = :resultType ");
        query.setEntity("application", application);
        query.setInteger("resultType", resultType.ordinal());
        return (InResult) query.uniqueResult();
    }

    @Override
    public InGuarantor findGuarantorById(Long id) {
        Session currentSession = sessionFactory.getCurrentSession();
        return (InGuarantor) currentSession.get(InGuarantorImpl.class, id);
    }

    @Override
    public InGuardian findGuardianById(Long id) {
        Session currentSession = sessionFactory.getCurrentSession();
        return (InGuardian) currentSession.get(InGuardianImpl.class, id);
    }

    @Override
    public InContact findContactById(Long id) {
        Session currentSession = sessionFactory.getCurrentSession();
        return (InContact) currentSession.get(InContactImpl.class, id);
    }

    @Override
    public InEmployment findEmploymentById(Long id) {
        Session currentSession = sessionFactory.getCurrentSession();
        return (InEmployment) currentSession.get(InEmploymentImpl.class, id);
    }

    @Override
    public InReferee findRefereeById(Long id) {
        Session currentSession = sessionFactory.getCurrentSession();
        return (InReferee) currentSession.get(InRefereeImpl.class, id);
    }

    @Override
    public InLanguage findLanguageById(Long id) {
        Session currentSession = sessionFactory.getCurrentSession();
        return (InLanguage) currentSession.get(InLanguageImpl.class, id);
    }

    @Override
    public InAttachment findAttachmentById(Long id) {
        Session currentSession = sessionFactory.getCurrentSession();
        return (InAttachment) currentSession.get(InAttachmentImpl.class, id);
    }

    @Override
    public InGuarantor findGuarantorByType(InGuarantorType type, InIntakeApplication application) {
        Session currentSession = sessionFactory.getCurrentSession();
        Query query = currentSession.createQuery("select a from InGuarantor a where " +
                "a.application = :application " +
                "and a.type = :type ");
        query.setInteger("type", type.ordinal());
        query.setEntity("application", application);
        return (InGuarantor) query.uniqueResult();
    }

    @Override
    public InGuardian findGuardianByType(InGuardianType type, InIntakeApplication application) {
        Session currentSession = sessionFactory.getCurrentSession();
        Query query = currentSession.createQuery("select a from InContact a where " +
                "a.application = :application " +
                "and a.type = :type ");
        query.setInteger("type", type.ordinal());
        query.setEntity("application", application);
        return (InGuardian) query.uniqueResult();
    }

    @Override
    public InContact findContactByType(InContactType type, InIntakeApplication application) {
        Session currentSession = sessionFactory.getCurrentSession();
        Query query = currentSession.createQuery("select a from InContact a where " +
                "a.application = :application " +
                "and a.type = :type ");
        query.setInteger("type", type.ordinal());
        query.setEntity("application", application);
        return (InContact) query.uniqueResult();
    }

    @Override
    public InApplicant findApplicant(InIntakeApplication application) {
        Session currentSession = sessionFactory.getCurrentSession();
        Query query = currentSession.createQuery("select p.applicant from InIntakeApplication p where " +
                "p = :application ");
        query.setEntity("application", application);
        return (InApplicant) query.uniqueResult();
    }

    @Override
    public List<InIntakeApplication> find(InIntake intake) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select p from InIntakeApplication p " +
                "where p.intake = :intake ");
        query.setEntity("intake", intake);
        return (List<InIntakeApplication>) query.list();
    }

    @Override
    public List<InIntakeApplication> find(InIntake intake, Integer offset, Integer limit) {
        Session currentSession = sessionFactory.getCurrentSession();
        Query query = currentSession.createQuery("select p from InIntakeApplication p where " +
                "p.intake = :intake ");
        query.setEntity("intake", intake);
        query.setFirstResult(offset);
        query.setMaxResults(limit);
        return (List<InIntakeApplication>) query.list();
    }

    // TODO: filter
    @Override
    public List<InIntakeApplication> find(String filter, InIntake intake, Integer offset, Integer limit) {
        Session currentSession = sessionFactory.getCurrentSession();
        Query query = currentSession.createQuery("select p from InIntakeApplication p where " +
                "p.intake = :intake ");
        query.setEntity("intake", intake);
        query.setFirstResult(offset);
        query.setMaxResults(limit);
        return (List<InIntakeApplication>) query.list();
    }

    // TODO: filter
    @Override
    public List<InIntakeApplication> find(String filter, InBidType bidType, InIntake intake, Integer offset, Integer limit) {
        Session currentSession = sessionFactory.getCurrentSession();
        Query query = currentSession.createQuery("select p from InIntakeApplication p where " +
                "p.intake = :intake " +
                "and p.bidType = :bidType ");
        query.setEntity("intake", intake);
        query.setInteger("bidType", bidType.ordinal());
        query.setFirstResult(offset);
        query.setMaxResults(limit);
        return (List<InIntakeApplication>) query.list();
    }

    @Override
    public List<InIntakeApplication> find(InApplicant applicant) {
        Session currentSession = sessionFactory.getCurrentSession();
        Query query = currentSession.createQuery("select p from InIntakeApplication p where " +
                "p.applicant = :applicant ");
        query.setEntity("applicant", applicant);
        return (List<InIntakeApplication>) query.list();
    }

    @Override
    public List<InIntakeApplication> find(InApplicant applicant, InBidStatus bidStatus) {
        Session currentSession = sessionFactory.getCurrentSession();
        Query query = currentSession.createQuery("select p from InIntakeApplication p where " +
                "p.applicant = :applicant " +
                "and p.bidStatus =:bidStatus");
        query.setEntity("applicant", applicant);
        query.setInteger("bidStatus", bidStatus.ordinal());
        return (List<InIntakeApplication>) query.list();
    }

    @Override
    public List<InIntakeApplication> findIntakeApplicationsByPaidStatus(InIntake intake, Boolean paid) {
        Session currentSession = sessionFactory.getCurrentSession();
        Query query = currentSession.createQuery("select p from InIntakeApplication p where " +
                "p.intake = :intake " +
                "and p.paid =:paid");
        query.setEntity("intake", intake);
        query.setBoolean("paid", paid);
        return (List<InIntakeApplication>) query.list();
    }

    @Override
    public List<InIntakeApplication> findIntakeApplicationsByVerificationStatus(InIntake intake, InBidStatus bidStatus, Boolean verified) {
        Session currentSession = sessionFactory.getCurrentSession();
        Query query = currentSession.createQuery("select p from InIntakeApplication p where " +
                "p.intake = :intake " +
                "and p.bidStatus = :bidStatus " +
                "and p.verified =:verified");
        query.setEntity("intake", intake);
        query.setInteger("bidStatus", bidStatus.ordinal());
        query.setBoolean("verified", verified);
        return (List<InIntakeApplication>) query.list();
    }

    @Override
    public List<InIntakeApplication> findByOrderedRank(InIntake intake) {
        Session currentSession = sessionFactory.getCurrentSession();
        Query query = currentSession.createQuery("select p from InIntakeApplication p where " +
                "p.intake = :intake " +
                "order by p.rank asc");
        query.setEntity("intake", intake);
        return (List<InIntakeApplication>) query.list();
    }

    @Override
    public List<InIntakeApplication> findByOrderedMerit(InIntake intake, InBidStatus bidStatus) {
        Session currentSession = sessionFactory.getCurrentSession();
        Query query = currentSession.createQuery("select p from InIntakeApplication p where " +
                "p.intake = :intake " +
                "and p.bidStatus = :bidStatus " +
                "order by p.merit desc");
        query.setEntity("intake", intake);
        query.setInteger("bidStatus", bidStatus.ordinal());
        return (List<InIntakeApplication>) query.list();
    }

    @Override
    public List<InIntakeApplication> find(InIntake intake, InBidStatus bidStatus) {
        Session currentSession = sessionFactory.getCurrentSession();
        Query query = currentSession.createQuery("select p from InIntakeApplication p where " +
                "p.intake = :intake " +
                "and p.bidStatus = :bidStatus ");
        query.setEntity("intake", intake);
        query.setInteger("bidStatus", bidStatus.ordinal());
        return (List<InIntakeApplication>) query.list();
    }
    
    @Override
    public List<InIntakeApplication> findStatusVerify(InIntake intake, InBidStatus bidStatus) {
        Session currentSession = sessionFactory.getCurrentSession();
        Query query = currentSession.createQuery("select p from InIntakeApplication p where " +
                "p.intake = :intake " +
                "and p.bidStatus = :bidStatus and p.verified = TRUE ");
        query.setEntity("intake", intake);
        query.setInteger("bidStatus", bidStatus.ordinal());
        return (List<InIntakeApplication>) query.list();
    }

    @Override
    public List<InApplicant> findApplicants(InIntake intake) {
        Session currentSession = sessionFactory.getCurrentSession();
        Query query = currentSession.createQuery("select p.applicant from InIntakeApplication p where " +
                "p.intake = :intake " +
                "order by p.rank");
        query.setEntity("intake", intake);
        return (List<InApplicant>) query.list();
    }

    @Override
    public List<InResult> findResults(InIntakeApplication application) {
        Session currentSession = sessionFactory.getCurrentSession();
        Query query = currentSession.createQuery("select p from InResult p where " +
                "p.application = :application ");
        query.setEntity("application", application);
        return (List<InResult>) query.list();
    }

    @Override
    public List<InEducation> findEducations(InIntakeApplication application) {
        Session currentSession = sessionFactory.getCurrentSession();
        Query query = currentSession.createQuery("select p from InEducation p where " +
                "p.application = :application ");
        query.setEntity("application", application);
        return (List<InEducation>) query.list();
    }

    @Override
    public List<InEmployment> findEmployments(InIntakeApplication application) {
        Session currentSession = sessionFactory.getCurrentSession();
        Query query = currentSession.createQuery("select p from InEmployment p where " +
                "p.application = :application " +
                "and p.metadata.state = :state");
        query.setInteger("state", InMetaState.ACTIVE.ordinal());
        query.setEntity("application", application);
        return (List<InEmployment>) query.list();
    }

    @Override
    public List<InLanguage> findLanguages(InIntakeApplication application) {
        Session currentSession = sessionFactory.getCurrentSession();
        Query query = currentSession.createQuery("select p from InLanguage p where " +
                "p.application = :application " +
                "and p.metadata.state = :state");
        query.setInteger("state", InMetaState.ACTIVE.ordinal());
        query.setEntity("application", application);
        return (List<InLanguage>) query.list();
    }

    @Override
    public List<InAttachment> findAttachments(InIntakeApplication application) {
        Session currentSession = sessionFactory.getCurrentSession();
        Query query = currentSession.createQuery("select p from InAttachment p where " +
                "p.application = :application " +
                "and p.metadata.state = :state");
        query.setInteger("state", InMetaState.ACTIVE.ordinal());
        query.setEntity("application", application);
        return (List<InAttachment>) query.list();
    }

    @Override
    public List<InReferee> findReferees(InIntakeApplication application) {
        Session currentSession = sessionFactory.getCurrentSession();
        Query query = currentSession.createQuery("select p from InReferee p where " +
                "p.application = :application " +
                "and p.metadata.state = :state");
        query.setInteger("state", InMetaState.ACTIVE.ordinal());
        query.setEntity("application", application);
        return (List<InReferee>) query.list();
    }

    @Override
    public List<InInvolvement> findInvolvements(InIntakeApplication application) {
        Session currentSession = sessionFactory.getCurrentSession();
        Query query = currentSession.createQuery("select p from InInvolvement p where " +
                "p.application = :application ");
        query.setEntity("application", application);
        return (List<InInvolvement>) query.list();
    }

    @Override
    public List<InGuarantor> findGuarantors(InIntakeApplication application) {
        Session currentSession = sessionFactory.getCurrentSession();
        Query query = currentSession.createQuery("select p from InGuarantor p where " +
                "p.application = :application ");
        query.setEntity("application", application);
        return (List<InGuarantor>) query.list();
    }

    @Override
    public List<InGuardian> findGuardians(InIntakeApplication application) {
        Session currentSession = sessionFactory.getCurrentSession();
        Query query = currentSession.createQuery("select p from InGuardian p where " +
                "p.application = :application ");
        query.setEntity("application", application);
        return (List<InGuardian>) query.list();
    }

    @Override
    public List<InContact> findContacts(InIntakeApplication application) {
        Session currentSession = sessionFactory.getCurrentSession();
        Query query = currentSession.createQuery("select p from InContact p where " +
                "p.application = :application ");
        query.setEntity("application", application);
        return (List<InContact>) query.list();
    }

    // ====================================================================================================
    // HELPER
    // ====================================================================================================

    @Override
    public Integer count(InIntake intake) {
        Session currentSession = sessionFactory.getCurrentSession();
        Query query = currentSession.createQuery("select count(p) from InIntakeApplication p " +
                "where p.intake = :intake");
        query.setEntity("intake", intake);
        return ((Long) query.uniqueResult()).intValue();
    }

    // TODO:
    @Override
    public Integer count(String filter, InIntake intake) {
        Session currentSession = sessionFactory.getCurrentSession();
        Query query = currentSession.createQuery("select count(p) from InIntakeApplication p " +
                "where p.intake = :intake");
        query.setEntity("intake", intake);
        return ((Long) query.uniqueResult()).intValue();
    }

    @Override
    public Integer count(String filter, InBidType bidType, InIntake intake) {
        Session currentSession = sessionFactory.getCurrentSession();
        Query query = currentSession.createQuery("select count(p) from InIntakeApplication p " +
                "where p.intake = :intake " +
                "and p.bidType = :bidType");
        query.setEntity("intake", intake);
        query.setInteger("bidType", bidType.ordinal());
        return ((Long) query.uniqueResult()).intValue();
    }

    @Override
    public boolean hasResult(InIntakeApplication application, InResultType type) {
        Session currentSession = sessionFactory.getCurrentSession();
        Query query = currentSession.createQuery("select count(p) from InResult p " +
                "where p.application = :application " +
                "and p.resultType = :resultType");
        query.setEntity("application", application);
        query.setInteger("resultType", type.ordinal());
        return ((Long) query.uniqueResult()).intValue() > 0;
    }

    @Override
    public boolean hasEducation(InIntakeApplication application) {
        Session currentSession = sessionFactory.getCurrentSession();
        Query query = currentSession.createQuery("select count(p) from InEducation p " +
                "where p.application = :application");
        query.setEntity("application", application);
        return (Integer) query.uniqueResult() > 0;
    }

    @Override
    public boolean hasEmployment(InIntakeApplication application) {
        Session currentSession = sessionFactory.getCurrentSession();
        Query query = currentSession.createQuery("select count(p) from InEmployment p " +
                "where p.application = :application");
        query.setEntity("application", application);
        return (Integer) query.uniqueResult() > 0;
    }

    @Override
    public boolean hasLanguage(InIntakeApplication application) {
        Session currentSession = sessionFactory.getCurrentSession();
        Query query = currentSession.createQuery("select count(p) from InLanguage p " +
                "where p.application = :application");
        query.setEntity("application", application);
        return (Integer) query.uniqueResult() > 0;
    }

    @Override
    public boolean hasInvolvement(InIntakeApplication application) {
        Session currentSession = sessionFactory.getCurrentSession();
        Query query = currentSession.createQuery("select count(p) from InEmployment p " +
                "where p.application = :application");
        query.setEntity("application", application);
        return (Integer) query.uniqueResult() > 0;
    }

    @Override
    public boolean hasReferee(InIntakeApplication application) {
        Session currentSession = sessionFactory.getCurrentSession();
        Query query = currentSession.createQuery("select count(p) from InReferee p " +
                "where p.application = :application");
        query.setEntity("application", application);
        return (Integer) query.uniqueResult() > 0;
    }

    @Override
    public boolean hasAttachment(InIntakeApplication application) {
        Session currentSession = sessionFactory.getCurrentSession();
        Query query = currentSession.createQuery("select count(p) from InAttachment p " +
                "where p.application = :application");
        query.setEntity("application", application);
        return (Integer) query.uniqueResult() > 0;
    }

    @Override
    public boolean hasApplied(InIntake intake, InApplicant applicant) {
        Session currentSession = sessionFactory.getCurrentSession();
        Query query = currentSession.createQuery("select count(p) from InIntakeApplication p " +
                "where p.intake = :intake " +
                "and p.applicant = :applicant");
        query.setEntity("intake", intake);
        query.setEntity("applicant", applicant);
        return ((Long) query.uniqueResult()) > 0;
    }

    // ====================================================================================================
    // CRUD
    // ====================================================================================================

    @Override
    public void addResult(InIntakeApplication application, InResult result, InUser user) {
        Validate.notNull(application, "Application cannot be null");
        Validate.notNull(result, "Result cannot be null");
        Validate.notNull(user, "User cannot be null");

        Session session = sessionFactory.getCurrentSession();
        result.setApplication(application);

        InMetadata metadata = new InMetadata();
        metadata.setModifiedDate(new Timestamp(System.currentTimeMillis()));
        metadata.setModifierId(user.getId());
        metadata.setState(InMetaState.ACTIVE);
        result.setMetadata(metadata);
        session.save(result);

    }

    @Override
    public void updateResult(InIntakeApplication application, InResult result, InUser user) {
        Validate.notNull(application, "Application cannot be null");
        Validate.notNull(result, "Result cannot be null");
        Validate.notNull(user, "User cannot be null");

        Session session = sessionFactory.getCurrentSession();
        session.update(result);
    }

    @Override
    public void deleteResult(InIntakeApplication application, InResult result, InUser user) {
        Validate.notNull(application, "Application cannot be null");
        Validate.notNull(result, "Result cannot be null");
        Validate.notNull(user, "User cannot be null");

        Session session = sessionFactory.getCurrentSession();
        session.delete(result);
    }

    @Override
    public void addEducation(InIntakeApplication application, InEducation education, InUser user) {
        Validate.notNull(application, "Application cannot be null");
        Validate.notNull(education, "Education cannot be null");
        Validate.notNull(user, "User cannot be null");

        Session session = sessionFactory.getCurrentSession();
        education.setApplication(application);

        InMetadata metadata = new InMetadata();
        metadata.setModifiedDate(new Timestamp(System.currentTimeMillis()));
        metadata.setModifierId(user.getId());
        metadata.setState(InMetaState.ACTIVE);
        education.setMetadata(metadata);
        session.save(education);
    }

    @Override
    public void deleteEducation(InIntakeApplication application, InEducation education, InUser user) {
        Validate.notNull(application, "Application cannot be null");
        Validate.notNull(education, "Education cannot be null");
        Validate.notNull(user, "User cannot be null");

        Session session = sessionFactory.getCurrentSession();
        session.delete(education);
    }

    @Override
    public void addEmployment(InIntakeApplication application, InEmployment employment, InUser user) {
        Validate.notNull(application, "Application cannot be null");
        Validate.notNull(employment, "Employment cannot be null");
        Validate.notNull(user, "User cannot be null");

        Session session = sessionFactory.getCurrentSession();
        employment.setApplication(application);

        InMetadata metadata = new InMetadata();
        metadata.setModifiedDate(new Timestamp(System.currentTimeMillis()));
        metadata.setModifierId(user.getId());
        metadata.setState(InMetaState.ACTIVE);
        employment.setMetadata(metadata);
        session.save(employment);
    }

    @Override
    public void deleteEmployment(InIntakeApplication application, InEmployment employment, InUser user) {
        Validate.notNull(application, "Application cannot be null");
        Validate.notNull(employment, "Employment cannot be null");
        Validate.notNull(user, "User cannot be null");

        Session session = sessionFactory.getCurrentSession();
        session.delete(employment);
    }

    @Override
    public void addLanguage(InIntakeApplication application, InLanguage language, InUser user) {
        Validate.notNull(application, "Application cannot be null");
        Validate.notNull(language, "Language cannot be null");
        Validate.notNull(user, "User cannot be null");

        Session session = sessionFactory.getCurrentSession();
        language.setApplication(application);

        InMetadata metadata = new InMetadata();
        metadata.setModifiedDate(new Timestamp(System.currentTimeMillis()));
        metadata.setModifierId(user.getId());
        metadata.setState(InMetaState.ACTIVE);
        language.setMetadata(metadata);
        session.save(language);
    }

    @Override
    public void deleteLanguage(InIntakeApplication application, InLanguage language, InUser user) {
        Validate.notNull(application, "Application cannot be null");
        Validate.notNull(language, "Language cannot be null");
        Validate.notNull(user, "User cannot be null");

        Session session = sessionFactory.getCurrentSession();
        session.delete(language);
    }

    @Override
    public void addInvolvement(InIntakeApplication application, InInvolvement involvement, InUser user) {
        Validate.notNull(application, "Application cannot be null");
        Validate.notNull(involvement, "Involvement cannot be null");
        Validate.notNull(user, "User cannot be null");

        Session session = sessionFactory.getCurrentSession();
        involvement.setApplication(application);

        InMetadata metadata = new InMetadata();
        metadata.setModifiedDate(new Timestamp(System.currentTimeMillis()));
        metadata.setModifierId(user.getId());
        metadata.setState(InMetaState.ACTIVE);
        involvement.setMetadata(metadata);
        session.save(involvement);
    }

    @Override
    public void deleteInvolvement(InIntakeApplication application, InInvolvement involvement, InUser user) {
        Validate.notNull(application, "Application cannot be null");
        Validate.notNull(involvement, "Involvement cannot be null");
        Validate.notNull(user, "User cannot be null");

        Session session = sessionFactory.getCurrentSession();
        session.delete(involvement);
    }

    @Override
    public void addGuardian(InIntakeApplication application, InGuardian guardian, InUser user) {
        Validate.notNull(application, "Application cannot be null");
        Validate.notNull(guardian, "Guardian cannot be null");
        Validate.notNull(user, "User cannot be null");

        Session session = sessionFactory.getCurrentSession();
        guardian.setApplication(application);

        InMetadata metadata = new InMetadata();
        metadata.setModifiedDate(new Timestamp(System.currentTimeMillis()));
        metadata.setModifierId(user.getId());
        metadata.setState(InMetaState.ACTIVE);
        guardian.setMetadata(metadata);
        session.save(guardian);
    }

    @Override
    public void deleteGuardian(InIntakeApplication application, InGuardian guardian, InUser user) {
        Validate.notNull(application, "Application cannot be null");
        Validate.notNull(guardian, "Guardian cannot be null");
        Validate.notNull(user, "User cannot be null");

        Session session = sessionFactory.getCurrentSession();
        session.delete(guardian);
    }

    @Override
    public void addReferee(InIntakeApplication application, InReferee referee, InUser user) {
        Validate.notNull(application, "Application cannot be null");
        Validate.notNull(referee, "Referee cannot be null");
        Validate.notNull(user, "User cannot be null");

        Session session = sessionFactory.getCurrentSession();
        referee.setApplication(application);

        InMetadata metadata = new InMetadata();
        metadata.setModifiedDate(new Timestamp(System.currentTimeMillis()));
        metadata.setModifierId(user.getId());
        metadata.setState(InMetaState.ACTIVE);
        referee.setMetadata(metadata);
        session.save(referee);
    }

    @Override
    public void addGuarantor(InIntakeApplication application, InGuarantor guarantor, InUser user) {
        Validate.notNull(application, "Application cannot be null");
        Validate.notNull(guarantor, "Guarantor cannot be null");
        Validate.notNull(user, "User cannot be null");

        Session session = sessionFactory.getCurrentSession();
        guarantor.setApplication(application);

        InMetadata metadata = new InMetadata();
        metadata.setModifiedDate(new Timestamp(System.currentTimeMillis()));
        metadata.setModifierId(user.getId());
        metadata.setState(InMetaState.ACTIVE);
        guarantor.setMetadata(metadata);
        session.save(guarantor);
    }

    @Override
    public void deleteGuarantor(InIntakeApplication application, InGuarantor guarantor, InUser user) {
        Validate.notNull(application, "Application cannot be null");
        Validate.notNull(guarantor, "Guarantor cannot be null");
        Validate.notNull(user, "User cannot be null");

        Session session = sessionFactory.getCurrentSession();
        session.delete(guarantor);
    }

    @Override
    public void addContact(InIntakeApplication application, InContact contact, InUser user) {
        Validate.notNull(application, "Application cannot be null");
        Validate.notNull(contact, "Contact cannot be null");
        Validate.notNull(user, "User cannot be null");

        Session session = sessionFactory.getCurrentSession();
        contact.setApplication(application);

        InMetadata metadata = new InMetadata();
        metadata.setModifiedDate(new Timestamp(System.currentTimeMillis()));
        metadata.setModifierId(user.getId());
        metadata.setState(InMetaState.ACTIVE);
        contact.setMetadata(metadata);
        session.save(contact);
    }

    @Override
    public void deleteContact(InIntakeApplication application, InContact contact, InUser user) {
        Validate.notNull(application, "Application cannot be null");
        Validate.notNull(contact, "Contact cannot be null");
        Validate.notNull(user, "User cannot be null");

        Session session = sessionFactory.getCurrentSession();
        session.delete(contact);
    }

    @Override
    public void updateReferee(InIntakeApplication application, InReferee referee, InUser user) {
        Validate.notNull(application, "Application cannot be null");
        Validate.notNull(referee, "Referee cannot be null");
        Validate.notNull(user, "User cannot be null");

        Session session = sessionFactory.getCurrentSession();
        session.update(referee);
    }

    @Override
    public void updateEmployment(InIntakeApplication application, InEmployment employment, InUser user) {
        Validate.notNull(application, "Application cannot be null");
        Validate.notNull(employment, "Employment cannot be null");
        Validate.notNull(user, "User cannot be null");

        Session session = sessionFactory.getCurrentSession();
        session.update(employment);
    }

    @Override
    public void updateGuardian(InIntakeApplication application, InGuardian guardian, InUser user) {
        Validate.notNull(application, "Application cannot be null");
        Validate.notNull(guardian, "Guardian cannot be null");
        Validate.notNull(user, "User cannot be null");

        Session session = sessionFactory.getCurrentSession();
        session.update(guardian);
    }
    
    @Override
    public void updateLanguage(InIntakeApplication application, InLanguage language, InUser user) {
        Validate.notNull(application, "Application cannot be null");
        Validate.notNull(language, "Language cannot be null");
        Validate.notNull(user, "User cannot be null");

        Session session = sessionFactory.getCurrentSession();
        session.update(language);
    }
    

    @Override
    public void deleteReferee(InIntakeApplication application, InReferee referee, InUser user) {
        Validate.notNull(application, "Application cannot be null");
        Validate.notNull(referee, "Address cannot be null");
        Validate.notNull(user, "User cannot be null");

        Session session = sessionFactory.getCurrentSession();
        session.delete(referee);
    }

    @Override
    public void addAttachment(InIntakeApplication application, InAttachment attachment, InUser user) {
        Validate.notNull(application, "Application cannot be null");
        Validate.notNull(attachment, "Attachment cannot be null");
        Validate.notNull(user, "User cannot be null");

        Session session = sessionFactory.getCurrentSession();
        attachment.setApplication(application);

        InMetadata metadata = new InMetadata();
        metadata.setModifiedDate(new Timestamp(System.currentTimeMillis()));
        metadata.setModifierId(user.getId());
        metadata.setState(InMetaState.ACTIVE);
        attachment.setMetadata(metadata);
        session.save(attachment);

    }

    @Override
    public void deleteAttachment(InIntakeApplication application, InAttachment attachment, InUser user) {
        Validate.notNull(application, "Application cannot be null");
        Validate.notNull(attachment, "Attachment cannot be null");
        Validate.notNull(user, "User cannot be null");

        Session session = sessionFactory.getCurrentSession();
        session.delete(attachment);
    }   
}
