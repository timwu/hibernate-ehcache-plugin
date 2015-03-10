/*
 * Hibernate, Relational Persistence for Idiomatic Java
 *
 * Copyright (c) 2007-2011, Red Hat Inc. or third-party contributors as
 * indicated by the @author tags or express copyright attribution
 * statements applied by the authors.  All third-party contributions are
 * distributed under license by Red Hat Inc.
 *
 * This copyrighted material is made available to anyone wishing to use, modify,
 * copy, or redistribute it subject to the terms and conditions of the GNU
 * Lesser General Public License, as published by the Free Software Foundation.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY
 * or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU Lesser General Public License
 * for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this distribution; if not, write to:
 * Free Software Foundation, Inc.
 * 51 Franklin Street, Fifth Floor
 * Boston, MA  02110-1301  USA
 */
package org.hibernate.cache.ehcache;


/**
 * The jboss-logging {@link MessageLogger} for the hibernate-ehcache module.  It reserves message ids ranging from
 * 20001 to 25000 inclusively.
 * <p/>
 * New messages must be added after the last message defined to ensure message codes are unique.
 */
public final class EhCacheLogMessage {
	private EhCacheLogMessage() {
	}

	/**
	 * Log a message (WARN) about attempt to start an already started Ehcache region factory
	 */
	public static String attemptToRestartAlreadyStartedEhCacheProvider() {
		return "Attempt to restart an already started EhCacheRegionFactory.  Use sessionFactory.close() between " +
				"repeated calls to buildSessionFactory. Using previously created EhCacheRegionFactory. If this " +
				"behaviour is required, consider using org.hibernate.cache.ehcache.SingletonEhCacheRegionFactory.";
	}

	/**
	 * Log a message (WARN) about inability to find configuration file
	 *
	 * @param name The name of the configuration file
	 */
	public static String unableToFindConfiguration(String name) {
		return String.format( "Could not find configuration [%s]; using defaults.", name );
	}

	/**
	 * Log a message (WARN) about inability to find named cache configuration
	 *
	 * @param name The name of the cache configuration
	 */
	@LogMessage(level = WARN)
	@Message(value = "Could not find a specific ehcache configuration for cache named [%s]; using defaults.", id = 20003)
	void unableToFindEhCacheConfiguration(String name);

	/**
	 * Logs a message about not being able to resolve the configuration by resource name.
	 *
	 * @param configurationResourceName The resource name we attempted to resolve
	 */
	@LogMessage(level = WARN)
	@Message(
			value = "A configurationResourceName was set to %s but the resource could not be loaded from the classpath. " +
					"Ehcache will configure itself using defaults.",
			id = 20004
	)
	void unableToLoadConfiguration(String configurationResourceName);

	/**
	 * Logs a message (WARN) about attempt to use an incompatible
	 * {@link net.sf.ehcache.config.TerracottaConfiguration.ValueMode}.
	 */
	@LogMessage(level = WARN)
	@Message(
			value = "The default cache value mode for this Ehcache configuration is \"identity\". " +
					"This is incompatible with clustered Hibernate caching - the value mode has therefore been " +
					"switched to \"serialization\"",
			id = 20005
	)
	void incompatibleCacheValueMode();

	/**
	 * Logs a message (WARN) about attempt to use an incompatible
	 * {@link net.sf.ehcache.config.TerracottaConfiguration.ValueMode}.
	 *
	 * @param cacheName The name of the cache whose config attempted to specify value mode.
	 */
	@LogMessage(level = WARN)
	@Message(value = "The value mode for the cache[%s] is \"identity\". This is incompatible with clustered Hibernate caching - "
			+ "the value mode has therefore been switched to \"serialization\"", id = 20006)
	void incompatibleCacheValueModePerCache(String cacheName);

	/**
	 * Log a message (WARN) about an attempt to specify read-only caching for a mutable entity
	 *
	 * @param entityName The name of the entity
	 */
	@LogMessage(level = WARN)
	@Message(value = "read-only cache configured for mutable entity [%s]", id = 20007)
	void readOnlyCacheConfiguredForMutableEntity(String entityName);

	/**
	 * Log a message (WARN) about expiry of soft-locked region.
	 *
	 * @param regionName The region name
	 * @param key The cache key
	 * @param lock The lock
	 */
	@LogMessage(level = WARN)
	@Message(
			value = "Cache[%s] Key[%s] Lockable[%s]\n" +
					"A soft-locked cache entry was expired by the underlying Ehcache. If this happens regularly you " +
					"should consider increasing the cache timeouts and/or capacity limits",
			id = 20008
	)
	void softLockedCacheExpired(String regionName, Object key, String lock);

}
