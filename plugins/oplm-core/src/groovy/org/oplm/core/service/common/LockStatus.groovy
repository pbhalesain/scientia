package org.oplm.core.service.common


public enum LockStatus {
	NO_LOCK,        // Indicates that there is no lock present 
	LOCKED,         // Indicates that the node is locked
	LOCK_OWNER,     // Indicates that the node is locked and you have lock ownership rights 
	LOCK_EXPIRED    // Indicates that the lock has expired and the node can be considered to be unlocked
}
