package my.edu.umk.pams.intake.core;

/**
 * @author canang technologies
 * @since 20/4/2015.
 */
public enum InFlowState {
	NEW,                    // 0
    DRAFTED,                // 1
    REQUESTED,              // 2
    REGISTERED,             // 3
    PREPARED,               // 4
    VERIFIED,               // 5
    UPPER_VERIFIED,         // 6
    CHECKED,                // 7
    APPROVED,               // 8
    UPPER_APPROVED,         // 9
    SELECTED,               // 10
    EVALUATED,              // 11
    PUBLISHED,              // 12
    CANCELLED,              // 13
    REJECTED,               // 14
    REMOVED,                // 15
    COMPLETED,              // 16
    ARCHIVED,               // 17
    OFFERED;                //18 

    InFlowState() {
    }
}
