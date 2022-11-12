/** <a href="http://www.cpupk.com/decompiler">Eclipse Class Decompiler</a> plugin, Copyright (c) 2017 Chen Chao. **/
package spirit.db;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import spirit.core.debt.detectors.configurationByProject.BrainClassDetectionConfiguration;
import spirit.core.debt.detectors.configurationByProject.BrainMethodDetectionConfiguration;
import spirit.core.debt.detectors.configurationByProject.DataClassDetectionConfiguration;
import spirit.core.debt.detectors.configurationByProject.DispersedCouplingDetectionConfiguration;
import spirit.core.debt.detectors.configurationByProject.FeatureEnvyDetectionConfiguration;
import spirit.core.debt.detectors.configurationByProject.GodClassDetectionConfiguration;
import spirit.core.debt.detectors.configurationByProject.IntensiveCouplingDetectionConfiguration;
import spirit.core.debt.detectors.configurationByProject.RefusedParentBequestDetectionConfiguration;
import spirit.core.debt.detectors.configurationByProject.ShotgunSurgeryDetectionConfiguration;
import spirit.core.debt.detectors.configurationByProject.TraditionBreakerDetectionConfiguration;

public class DataBaseManager {
	private static DataBaseManager instance;

	public static DataBaseManager getInstance() {
		if (instance == null) {
			instance = new DataBaseManager();
		}
		return instance;
	}

	private DataBaseManager() {
		HSQLServer.getInstance().startDatabase();
	}

	public void saveEntity(Object objecToSave) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;

		try {
			transaction = session.beginTransaction();
			session.save(objecToSave);
			transaction.commit();
		} catch (HibernateException arg7) {
			transaction.rollback();
			arg7.printStackTrace();
		} finally {
			session.close();
		}

	}

	public void saveOrUpdateEntity(Object objecToSave) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;

		try {
			transaction = session.beginTransaction();
			session.saveOrUpdate(objecToSave);
			transaction.commit();
		} catch (HibernateException arg7) {
			transaction.rollback();
			arg7.printStackTrace();
		} finally {
			session.close();
		}

	}

	public TraditionBreakerDetectionConfiguration getTraditionBreakerDetectionConfiguration(
			String projectName) {
		TraditionBreakerDetectionConfiguration ret = new TraditionBreakerDetectionConfiguration(
				projectName);
		Session session = HibernateUtil.getSessionFactory().openSession();
		List list = session
				.createCriteria(TraditionBreakerDetectionConfiguration.class)
				.add(Restrictions.eq("projectName", projectName)).list();
		if (list.size() > 0) {
			ret.setId(((TraditionBreakerDetectionConfiguration) list.get(0))
					.getId());
			ret.setAMW_Greater_AMWAvg(((TraditionBreakerDetectionConfiguration) list
					.get(0)).getAMW_Greater_AMWAvg());
			ret.setAMW_Greater_AMWAvg_2(((TraditionBreakerDetectionConfiguration) list
					.get(0)).getAMW_Greater_AMWAvg_2());
			ret.setNAS_GreaterEqual_NOMAvg(((TraditionBreakerDetectionConfiguration) list
					.get(0)).getNAS_GreaterEqual_NOMAvg());
			ret.setNOM_GreatherEqual_High(((TraditionBreakerDetectionConfiguration) list
					.get(0)).getNOM_GreatherEqual_High());
			ret.setNOM_GreatherEqual_HighDiv2(((TraditionBreakerDetectionConfiguration) list
					.get(0)).getNOM_GreatherEqual_HighDiv2());
			ret.setPNAS_GreaterEqual_TWO_THIRD(((TraditionBreakerDetectionConfiguration) list
					.get(0)).getPNAS_GreaterEqual_TWO_THIRD());
			ret.setWMC_Greater_VeryHighDiv2(((TraditionBreakerDetectionConfiguration) list
					.get(0)).getWMC_Greater_VeryHighDiv2());
			ret.setWMC_GreaterEqual_VeryHigh(((TraditionBreakerDetectionConfiguration) list
					.get(0)).getWMC_GreaterEqual_VeryHigh());
		}

		session.close();
		return ret;
	}

	public ShotgunSurgeryDetectionConfiguration getShotgunSurgeryDetectionConfiguration(
			String projectName) {
		ShotgunSurgeryDetectionConfiguration ret = new ShotgunSurgeryDetectionConfiguration(
				projectName);
		Session session = HibernateUtil.getSessionFactory().openSession();
		List list = session
				.createCriteria(ShotgunSurgeryDetectionConfiguration.class)
				.add(Restrictions.eq("projectName", projectName)).list();
		if (list.size() > 0) {
			ret.setId(((ShotgunSurgeryDetectionConfiguration) list.get(0))
					.getId());
			ret.setCC_GreaterEqual_MANY(((ShotgunSurgeryDetectionConfiguration) list
					.get(0)).getCC_GreaterEqual_MANY());
			ret.setCM_Greater_SMemCap(((ShotgunSurgeryDetectionConfiguration) list
					.get(0)).getCM_Greater_SMemCap());
		}

		session.close();
		return ret;
	}

	public RefusedParentBequestDetectionConfiguration getRefusedParentBequestDetectionConfiguration(
			String projectName) {
		RefusedParentBequestDetectionConfiguration ret = new RefusedParentBequestDetectionConfiguration(
				projectName);
		Session session = HibernateUtil.getSessionFactory().openSession();
		List list = session
				.createCriteria(
						RefusedParentBequestDetectionConfiguration.class)
				.add(Restrictions.eq("projectName", projectName)).list();
		if (list.size() > 0) {
			ret.setId(((RefusedParentBequestDetectionConfiguration) list.get(0))
					.getId());
			ret.setAMW_Greater_AMWAvg(((RefusedParentBequestDetectionConfiguration) list
					.get(0)).getAMW_Greater_AMWAvg());
			ret.setBOvR_Less_OneThird(((RefusedParentBequestDetectionConfiguration) list
					.get(0)).getBOvR_Less_OneThird());
			ret.setBUR_Less_OneThird(((RefusedParentBequestDetectionConfiguration) list
					.get(0)).getBUR_Less_OneThird());
			ret.setNOM_Greater_NOMAvg(((RefusedParentBequestDetectionConfiguration) list
					.get(0)).getNOM_Greater_NOMAvg());
			ret.setNProtM_Greater_Few(((RefusedParentBequestDetectionConfiguration) list
					.get(0)).getNProtM_Greater_Few());
			ret.setWMC_Greater_WMCAvg(((RefusedParentBequestDetectionConfiguration) list
					.get(0)).getWMC_Greater_WMCAvg());
		}

		session.close();
		return ret;
	}

	public IntensiveCouplingDetectionConfiguration getIntensiveCouplingDetectionConfiguration(
			String projectName) {
		IntensiveCouplingDetectionConfiguration ret = new IntensiveCouplingDetectionConfiguration(
				projectName);
		Session session = HibernateUtil.getSessionFactory().openSession();
		List list = session
				.createCriteria(IntensiveCouplingDetectionConfiguration.class)
				.add(Restrictions.eq("projectName", projectName)).list();
		if (list.size() > 0) {
			ret.setId(((IntensiveCouplingDetectionConfiguration) list.get(0))
					.getId());
			ret.setCDISP_Less_Half(((IntensiveCouplingDetectionConfiguration) list
					.get(0)).getCDISP_Less_Half());
			ret.setCDISP_Less_OneQuarter(((IntensiveCouplingDetectionConfiguration) list
					.get(0)).getCDISP_Less_OneQuarter());
			ret.setCINT_Greater_Few(((IntensiveCouplingDetectionConfiguration) list
					.get(0)).getCINT_Greater_Few());
			ret.setCINT_Greater_SMemCap(((IntensiveCouplingDetectionConfiguration) list
					.get(0)).getCINT_Greater_SMemCap());
			ret.setMAXNESTING_Greater_SHALLOW(((IntensiveCouplingDetectionConfiguration) list
					.get(0)).getMAXNESTING_Greater_SHALLOW());
		}

		session.close();
		return ret;
	}

	public GodClassDetectionConfiguration getGodClassDetectionConfiguration(
			String projectName) {
		GodClassDetectionConfiguration ret = new GodClassDetectionConfiguration(
				projectName);
		Session session = HibernateUtil.getSessionFactory().openSession();
		List list = session
				.createCriteria(GodClassDetectionConfiguration.class)
				.add(Restrictions.eq("projectName", projectName)).list();
		if (list.size() > 0) {
			ret.setId(((GodClassDetectionConfiguration) list.get(0)).getId());
			ret.setATFD_Greater_FEW(((GodClassDetectionConfiguration) list
					.get(0)).getATFD_Greater_FEW());
			ret.setTCC_Less_OneThird(((GodClassDetectionConfiguration) list
					.get(0)).getTCC_Less_OneThird());
			ret.setWMC_GreaterEqual_VeryHigh(((GodClassDetectionConfiguration) list
					.get(0)).getWMC_GreaterEqual_VeryHigh());
		}

		session.close();
		return ret;
	}

	public FeatureEnvyDetectionConfiguration getFeatureEnvyDetectionConfiguration(
			String projectName) {
		FeatureEnvyDetectionConfiguration ret = new FeatureEnvyDetectionConfiguration(
				projectName);
		Session session = HibernateUtil.getSessionFactory().openSession();
		List list = session
				.createCriteria(FeatureEnvyDetectionConfiguration.class)
				.add(Restrictions.eq("projectName", projectName)).list();
		if (list.size() > 0) {
			ret.setId(((FeatureEnvyDetectionConfiguration) list.get(0)).getId());
			ret.setATFD_Greater_Few(((FeatureEnvyDetectionConfiguration) list
					.get(0)).getATFD_Greater_Few());
			ret.setFDP_LessEqual_FEW(((FeatureEnvyDetectionConfiguration) list
					.get(0)).getFDP_LessEqual_FEW());
			ret.setLAA_Less_OneThird(((FeatureEnvyDetectionConfiguration) list
					.get(0)).getLAA_Less_OneThird());
		}

		session.close();
		return ret;
	}

	public DispersedCouplingDetectionConfiguration getDispersedCouplingDetectionConfiguration(
			String projectName) {
		DispersedCouplingDetectionConfiguration ret = new DispersedCouplingDetectionConfiguration(
				projectName);
		Session session = HibernateUtil.getSessionFactory().openSession();
		List list = session
				.createCriteria(DispersedCouplingDetectionConfiguration.class)
				.add(Restrictions.eq("projectName", projectName)).list();
		if (list.size() > 0) {
			ret.setId(((DispersedCouplingDetectionConfiguration) list.get(0))
					.getId());
			ret.setCDISP_GreaterEqual_Half(((DispersedCouplingDetectionConfiguration) list
					.get(0)).getCDISP_GreaterEqual_Half());
			ret.setCINT_Greater_SMemCap(((DispersedCouplingDetectionConfiguration) list
					.get(0)).getCINT_Greater_SMemCap());
			ret.setMAXNESTING_Greater_Shallow(((DispersedCouplingDetectionConfiguration) list
					.get(0)).getMAXNESTING_Greater_Shallow());
		}

		session.close();
		return ret;
	}

	public DataClassDetectionConfiguration getDataClassDetectionConfiguration(
			String projectName) {
		DataClassDetectionConfiguration ret = new DataClassDetectionConfiguration(
				projectName);
		Session session = HibernateUtil.getSessionFactory().openSession();
		List list = session
				.createCriteria(DataClassDetectionConfiguration.class)
				.add(Restrictions.eq("projectName", projectName)).list();
		if (list.size() > 0) {
			ret.setId(((DataClassDetectionConfiguration) list.get(0)).getId());
			ret.setNOAP_SOAP_Greater_Few(((DataClassDetectionConfiguration) list
					.get(0)).getNOAP_SOAP_Greater_Few());
			ret.setNOAP_SOAP_Greater_Many(((DataClassDetectionConfiguration) list
					.get(0)).getNOAP_SOAP_Greater_Many());
			ret.setWMC_Less_High(((DataClassDetectionConfiguration) list.get(0))
					.getWMC_Less_High());
			ret.setWMC_Less_VeryHigh(((DataClassDetectionConfiguration) list
					.get(0)).getWMC_Less_VeryHigh());
			ret.setWOC_Less_OneThird(((DataClassDetectionConfiguration) list
					.get(0)).getWOC_Less_OneThird());
		}

		session.close();
		return ret;
	}

	public BrainMethodDetectionConfiguration getBrainMethodDetectionConfiguration(
			String projectName) {
		BrainMethodDetectionConfiguration ret = new BrainMethodDetectionConfiguration(
				projectName);
		Session session = HibernateUtil.getSessionFactory().openSession();
		List list = session
				.createCriteria(BrainMethodDetectionConfiguration.class)
				.add(Restrictions.eq("projectName", projectName)).list();
		if (list.size() > 0) {
			ret.setId(((BrainMethodDetectionConfiguration) list.get(0)).getId());
			ret.setLOC_Greater_VeryHigh(((BrainMethodDetectionConfiguration) list
					.get(0)).getLOC_Greater_VeryHigh());
			ret.setMAXNESTING_GreaterEqual_DEEP(((BrainMethodDetectionConfiguration) list
					.get(0)).getMAXNESTING_GreaterEqual_DEEP());
			ret.setNOF_GreaterEqual_SMemCap(((BrainMethodDetectionConfiguration) list
					.get(0)).getNOF_GreaterEqual_SMemCap());
			ret.setWMC_GreaterEqual_Many(((BrainMethodDetectionConfiguration) list
					.get(0)).getWMC_GreaterEqual_Many());
		}

		session.close();
		return ret;
	}

	public BrainClassDetectionConfiguration getBrainClassDetectionConfiguration(
			String projectName) {
		BrainClassDetectionConfiguration ret = new BrainClassDetectionConfiguration(
				projectName);
		Session session = HibernateUtil.getSessionFactory().openSession();
		List list = session
				.createCriteria(BrainClassDetectionConfiguration.class)
				.add(Restrictions.eq("projectName", projectName)).list();
		if (list.size() > 0) {
			ret.setId(((BrainClassDetectionConfiguration) list.get(0)).getId());
			ret.setLOC_GreaterEqual_2xVeryHigh(((BrainClassDetectionConfiguration) list
					.get(0)).getLOC_GreaterEqual_2xVeryHigh());
			ret.setLOC_GreaterEqual_VeryHigh(((BrainClassDetectionConfiguration) list
					.get(0)).getLOC_GreaterEqual_VeryHigh());
			ret.setTCC_Less_Half(((BrainClassDetectionConfiguration) list
					.get(0)).getTCC_Less_Half());
			ret.setWMC_GreaterEqual_2xVeryHigh(((BrainClassDetectionConfiguration) list
					.get(0)).getWMC_GreaterEqual_2xVeryHigh());
			ret.setWMC_GreaterEqual_VeryHigh(((BrainClassDetectionConfiguration) list
					.get(0)).getWMC_GreaterEqual_VeryHigh());
		}

		session.close();
		return ret;
	}

	private List<?> searchForTable(Class<?> aClass) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Criteria crit = session.createCriteria(aClass);
		List results = crit.list();
		session.close();
		return results;
	}


}