package org.oplm.core.model.util;


/**
 * @generated
 */
public class HibernateHelper {
	/**
	 * @generated
	 */
	private static HibernateHelper singleton = new HibernateHelper();
	/**
	 * @generated
	 */
	private javax.persistence.EntityManagerFactory factory;
	/**
	 * @generated
	 */
	private ThreadLocal currentEntityManager = new ThreadLocal();

	/**
	 * @generated
	 */
	private HibernateHelper() {
	}

	/**
	 * @generated
	 */
	public static HibernateHelper getInstance() {
		return singleton;
	}

	/**
	 * @generated
	 */
	public synchronized javax.persistence.EntityManagerFactory getFactory() {
		if (factory == null) {
			factory = javax.persistence.Persistence
					.createEntityManagerFactory("oplm-core");
		}
		return factory;
	}

	/**
	 * @generated
	 */
	public synchronized void close() {
		closeEntityManager();
		if (factory != null) {
			factory.close();
			factory = null;
		}
	}

	/**
	 * @generated
	 */
	public javax.persistence.EntityManager getEntityManager() {
		javax.persistence.EntityManager entityManager = (javax.persistence.EntityManager) currentEntityManager
				.get();
		if (entityManager == null || !entityManager.isOpen()) {
			entityManager = getFactory().createEntityManager();
			currentEntityManager.set(entityManager);
		}
		return entityManager;
	}

	/**
	 * @generated
	 */
	public void closeEntityManager() {
		javax.persistence.EntityManager entityManager = (javax.persistence.EntityManager) currentEntityManager
				.get();
		if (entityManager != null && entityManager.isOpen()) {
			entityManager.close();
		}
		currentEntityManager.set(null);
	}

	/**
	 * @generated
	 */
	public String toString() {
		return "HibernateHelper";
	}
}