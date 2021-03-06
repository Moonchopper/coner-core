package org.coner.core.util;

import java.math.BigDecimal;
import java.time.Year;
import java.util.Date;
import java.util.Set;

import org.coner.core.domain.entity.Car;
import org.coner.core.domain.entity.CompetitionGroup;
import org.coner.core.domain.entity.CompetitionGroupSet;
import org.coner.core.domain.entity.Event;
import org.coner.core.domain.entity.HandicapGroup;
import org.coner.core.domain.entity.HandicapGroupSet;
import org.coner.core.domain.entity.Person;
import org.coner.core.domain.entity.Registration;
import org.coner.core.domain.entity.Run;
import org.coner.core.domain.entity.ScoredRun;

import com.google.common.collect.Sets;

public final class DomainEntityTestUtils {

    private DomainEntityTestUtils() {
    }

    public static Person fullPerson() {
        return fullPerson(
                TestConstants.PERSON_ID,
                TestConstants.PERSON_FIRST_NAME,
                TestConstants.PERSON_MIDDLE_NAME,
                TestConstants.PERSON_LAST_NAME
        );
    }

    public static Person fullPerson(String id, String firstName, String middleName, String lastName) {
        Person person = new Person();
        person.setId(id);
        person.setFirstName(firstName);
        person.setMiddleName(middleName);
        person.setLastName(lastName);
        return person;
    }

    public static Car fullCar() {
        return fullCar(
                TestConstants.CAR_ID,
                TestConstants.CAR_YEAR,
                TestConstants.CAR_MAKE,
                TestConstants.CAR_MODEL,
                TestConstants.CAR_TRIM,
                TestConstants.CAR_COLOR
        );
    }

    public static Car fullCar(String id, Year year, String make, String model, String trim, String color) {
        Car car = new Car();
        car.setId(id);
        car.setYear(year);
        car.setMake(make);
        car.setModel(model);
        car.setTrim(trim);
        car.setColor(color);
        return car;
    }

    public static Event fullEvent() {
        return fullEvent(
                TestConstants.EVENT_ID,
                TestConstants.EVENT_NAME,
                TestConstants.EVENT_DATE,
                fullHandicapGroupSet(),
                fullCompetitionGroupSet(),
                TestConstants.EVENT_MAX_RUNS_PER_REGISTRATION,
                TestConstants.EVENT_CURRENT
        );
    }

    public static Event fullEvent(
            String id,
            String name,
            Date date,
            HandicapGroupSet handicapGroupSet,
            CompetitionGroupSet competitionGroupSet,
            int maxRunsPerRegistration,
            boolean current
    ) {
        Event event = new Event();
        event.setId(id);
        event.setName(name);
        event.setDate(date);
        event.setHandicapGroupSet(handicapGroupSet);
        event.setCompetitionGroupSet(competitionGroupSet);
        event.setMaxRunsPerRegistration(maxRunsPerRegistration);
        event.setCurrent(current);
        return event;
    }

    public static Registration fullRegistration() {
        return fullRegistration(
                TestConstants.REGISTRATION_ID,
                fullPerson(),
                fullCar(),
                fullEvent(),
                fullHandicapGroup(),
                fullCompetitionGroup(),
                TestConstants.REGISTRATION_NUMBER,
                TestConstants.REGISTRATION_CHECKED_IN
        );
    }

    public static Registration fullRegistration(
            String id,
            Person person,
            Car car,
            Event event,
            HandicapGroup handicapGroup,
            CompetitionGroup competitionGroup,
            String number,
            boolean checkedIn
    ) {
        Registration registration = new Registration();
        registration.setId(id);
        registration.setPerson(person);
        registration.setCar(car);
        registration.setEvent(event);
        registration.setHandicapGroup(handicapGroup);
        registration.setCompetitionGroup(competitionGroup);
        registration.setNumber(number);
        registration.setCheckedIn(checkedIn);
        return registration;
    }

    public static HandicapGroup fullHandicapGroup() {
        return fullHandicapGroup(
                TestConstants.HANDICAP_GROUP_ID,
                TestConstants.HANDICAP_GROUP_NAME,
                TestConstants.HANDICAP_GROUP_FACTOR
        );
    }

    public static HandicapGroup fullHandicapGroup(
            String id,
            String name,
            BigDecimal factor
    ) {
        HandicapGroup handicapGroup = new HandicapGroup();
        handicapGroup.setId(id);
        handicapGroup.setName(name);
        handicapGroup.setFactor(factor);
        return handicapGroup;
    }

    public static HandicapGroupSet fullHandicapGroupSet(
            String id,
            String name,
            Set<HandicapGroup> handicapGroups
    ) {
        HandicapGroupSet handicapGroupSet = new HandicapGroupSet();
        handicapGroupSet.setId(id);
        handicapGroupSet.setName(name);
        handicapGroupSet.setHandicapGroups(handicapGroups);
        return handicapGroupSet;
    }

    public static HandicapGroupSet fullHandicapGroupSet() {
        return fullHandicapGroupSet(
                TestConstants.HANDICAP_GROUP_SET_ID,
                TestConstants.HANDICAP_GROUP_SET_NAME,
                Sets.newHashSet(fullHandicapGroup())
        );
    }

    public static CompetitionGroup fullCompetitionGroup() {
        return fullCompetitionGroup(
                TestConstants.COMPETITION_GROUP_ID,
                TestConstants.COMPETITION_GROUP_NAME,
                TestConstants.COMPETITION_GROUP_FACTOR,
                TestConstants.COMPETITION_GROUP_GROUPING,
                TestConstants.COMPETITION_GROUP_RESULT_TIME_TYPE
        );
    }

    public static CompetitionGroup fullCompetitionGroup(
            String id,
            String name,
            BigDecimal factor,
            boolean grouping,
            CompetitionGroup.ResultTimeType resultTimeType
    ) {
        CompetitionGroup domainCompetitionGroup = new CompetitionGroup();
        domainCompetitionGroup.setId(id);
        domainCompetitionGroup.setName(name);
        domainCompetitionGroup.setFactor(factor);
        domainCompetitionGroup.setGrouping(grouping);
        domainCompetitionGroup.setResultTimeType(resultTimeType);
        return domainCompetitionGroup;
    }

    public static CompetitionGroupSet fullCompetitionGroupSet() {
        return fullCompetitionGroupSet(
                TestConstants.COMPETITION_GROUP_SET_ID,
                TestConstants.COMPETITION_GROUP_SET_NAME,
                Sets.newHashSet(fullCompetitionGroup())
        );
    }

    public static CompetitionGroupSet fullCompetitionGroupSet(
            String id,
            String name,
            Set<CompetitionGroup> competitionGroups
    ) {
        CompetitionGroupSet competitionGroupSet = new CompetitionGroupSet();
        competitionGroupSet.setId(id);
        competitionGroupSet.setName(name);
        competitionGroupSet.setCompetitionGroups(competitionGroups);
        return competitionGroupSet;
    }

    public static Run fullRun() {
        return Run.builder()
                .id(TestConstants.RUN_ID)
                .event(fullEvent())
                .registration(fullRegistration())
                .sequence(TestConstants.RUN_SEQUENCE)
                .timestamp(TestConstants.RUN_TIMESTAMP)
                .rawTime(TestConstants.RUN_RAW_TIME)
                .cones(TestConstants.RUN_CONES)
                .didNotFinish(TestConstants.RUN_DID_NOT_FINISH)
                .disqualified(TestConstants.RUN_DISQUALIFIED)
                .rerun(TestConstants.RUN_RERUN)
                .competitive(TestConstants.RUN_COMPETITIVE)
                .build();
    }

    public static ScoredRun fullScoredRun() {
        return fullScoredRun(
                fullRun(),
                TestConstants.SCORED_RUN_RAW_TIME_SCORED,
                TestConstants.SCORED_RUN_HANDICAP_TIME_SCORED
        );
    }

    public static ScoredRun fullScoredRun(Run run, BigDecimal rawTimeScored, BigDecimal handicapTimeScored) {
        ScoredRun scoredRun = new ScoredRun();
        scoredRun.setRun(run);
        scoredRun.setRawTimeScored(rawTimeScored);
        scoredRun.setHandicapTimeScored(handicapTimeScored);
        return scoredRun;
    }

}
