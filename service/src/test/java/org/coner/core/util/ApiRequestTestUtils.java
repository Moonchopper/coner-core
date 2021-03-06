package org.coner.core.util;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.Year;
import java.util.Date;
import java.util.Set;

import org.coner.core.api.request.AddCompetitionGroupRequest;
import org.coner.core.api.request.AddCompetitionGroupSetRequest;
import org.coner.core.api.request.AddEventRequest;
import org.coner.core.api.request.AddHandicapGroupRequest;
import org.coner.core.api.request.AddHandicapGroupSetRequest;
import org.coner.core.api.request.AddRegistrationRequest;
import org.coner.core.api.request.AddRunRequest;

import com.google.common.collect.Sets;

public final class ApiRequestTestUtils {

    private ApiRequestTestUtils() {

    }

    public static AddEventRequest fullAddEvent() {
        return fullAddEvent(
                TestConstants.EVENT_NAME,
                TestConstants.EVENT_DATE,
                TestConstants.HANDICAP_GROUP_SET_ID,
                TestConstants.COMPETITION_GROUP_SET_ID,
                TestConstants.EVENT_MAX_RUNS_PER_REGISTRATION
        );
    }

    public static AddEventRequest fullAddEvent(
            String name,
            Date date,
            String handicapGroupSetId,
            String competitionGroupSetId,
            int maxRunsPerRegistration
    ) {
        AddEventRequest request = new AddEventRequest();
        request.setName(name);
        request.setDate(date);
        request.setHandicapGroupSetId(handicapGroupSetId);
        request.setCompetitionGroupSetId(competitionGroupSetId);
        request.setMaxRunsPerRegistration(maxRunsPerRegistration);
        return request;
    }

    public static AddCompetitionGroupRequest fullAddCompetitionGroup() {
        return fullAddCompetitionGroup(
                TestConstants.COMPETITION_GROUP_NAME,
                TestConstants.COMPETITION_GROUP_GROUPING,
                TestConstants.COMPETITION_GROUP_FACTOR,
                TestConstants.COMPETITION_GROUP_RESULT_TIME_TYPE.toString()
        );
    }

    public static AddCompetitionGroupRequest fullAddCompetitionGroup(
            String name,
            boolean grouping,
            BigDecimal handicapFactor,
            String resultTimeType
    ) {
        AddCompetitionGroupRequest addCompetitionGroupRequest = new AddCompetitionGroupRequest();
        addCompetitionGroupRequest.setName(name);
        addCompetitionGroupRequest.setGrouping(grouping);
        addCompetitionGroupRequest.setFactor(handicapFactor);
        addCompetitionGroupRequest.setResultTimeType(resultTimeType);
        return addCompetitionGroupRequest;
    }

    public static AddCompetitionGroupSetRequest fullAddCompetitionGroupSet() {
        return fullAddCompetitionGroupSet(
                TestConstants.COMPETITION_GROUP_SET_NAME,
                Sets.newHashSet(TestConstants.COMPETITION_GROUP_ID)
        );
    }

    public static AddCompetitionGroupSetRequest fullAddCompetitionGroupSet(
            String competitionGroupSetName,
            Set<String> competitionGroupSetIds
    ) {
        AddCompetitionGroupSetRequest addCompetitionGroupSetRequest = new AddCompetitionGroupSetRequest();
        addCompetitionGroupSetRequest.setName(competitionGroupSetName);
        addCompetitionGroupSetRequest.setCompetitionGroupIds(competitionGroupSetIds);
        return addCompetitionGroupSetRequest;
    }

    public static AddHandicapGroupRequest fullAddHandicapGroup() {
        return fullAddHandicapGroup(
                TestConstants.HANDICAP_GROUP_NAME,
                TestConstants.HANDICAP_GROUP_FACTOR
        );
    }

    public static AddHandicapGroupRequest fullAddHandicapGroup(
            String name,
            BigDecimal factor
    ) {
        AddHandicapGroupRequest addHandicapGroupRequest = new AddHandicapGroupRequest();
        addHandicapGroupRequest.setName(name);
        addHandicapGroupRequest.setFactor(factor);
        return addHandicapGroupRequest;
    }

    public static AddHandicapGroupSetRequest fullAddHandicapGroupSet(
            String name,
            Set<String> handicapGroupIds
    ) {
        AddHandicapGroupSetRequest addHandicapGroupSetRequest = new AddHandicapGroupSetRequest();
        addHandicapGroupSetRequest.setName(name);
        addHandicapGroupSetRequest.setHandicapGroupIds(handicapGroupIds);
        return addHandicapGroupSetRequest;
    }

    public static AddHandicapGroupSetRequest fullAddHandicapGroupSet() {
        return fullAddHandicapGroupSet(
                TestConstants.HANDICAP_GROUP_SET_NAME,
                Sets.newHashSet(TestConstants.HANDICAP_GROUP_ID)
        );
    }

    public static AddRegistrationRequest fullAddRegistration() {
        return fullAddRegistration(
                fullAddPerson(),
                fullAddCar(),
                TestConstants.HANDICAP_GROUP_ID,
                TestConstants.COMPETITION_GROUP_ID,
                TestConstants.REGISTRATION_NUMBER,
                TestConstants.REGISTRATION_CHECKED_IN
        );
    }

    public static AddRegistrationRequest fullAddRegistration(
            AddRegistrationRequest.AddPerson person, AddRegistrationRequest.AddCar car, String handicapGroupId,
            String competitionGroupId,
            String number,
            boolean checkedIn
    ) {
        AddRegistrationRequest addRegistrationRequest = new AddRegistrationRequest();
        addRegistrationRequest.setPerson(person);
        addRegistrationRequest.setCar(car);
        addRegistrationRequest.setHandicapGroupId(handicapGroupId);
        addRegistrationRequest.setCompetitionGroupId(competitionGroupId);
        addRegistrationRequest.setNumber(number);
        addRegistrationRequest.setCheckedIn(checkedIn);
        return addRegistrationRequest;
    }

    public static AddRegistrationRequest.AddPerson fullAddPerson() {
        return fullAddPerson(
                TestConstants.PERSON_FIRST_NAME,
                TestConstants.PERSON_MIDDLE_NAME,
                TestConstants.PERSON_LAST_NAME
        );
    }

    public static AddRegistrationRequest.AddPerson fullAddPerson(
            String firstName,
            String middleName,
            String lastName
    ) {
        AddRegistrationRequest.AddPerson addPerson = new AddRegistrationRequest.AddPerson();
        addPerson.setFirstName(firstName);
        addPerson.setMiddleName(middleName);
        addPerson.setLastName(lastName);
        return addPerson;
    }

    public static AddRegistrationRequest.AddCar fullAddCar() {
        return fullAddCar(
                TestConstants.CAR_YEAR,
                TestConstants.CAR_MAKE,
                TestConstants.CAR_MODEL,
                TestConstants.CAR_TRIM,
                TestConstants.CAR_COLOR
        );
    }

    public static AddRegistrationRequest.AddCar fullAddCar(
            Year year,
            String make,
            String model,
            String trim,
            String color
    ) {
        AddRegistrationRequest.AddCar addCar = new AddRegistrationRequest.AddCar();
        addCar.setYear(year);
        addCar.setMake(make);
        addCar.setModel(model);
        addCar.setTrim(trim);
        addCar.setColor(color);
        return addCar;
    }

    public static AddRunRequest fullAddRun() {
        return AddRunRequest.builder()
                .registrationId(TestConstants.REGISTRATION_ID)
                .timestamp(TestConstants.RUN_TIMESTAMP)
                .rawTime(TestConstants.RUN_RAW_TIME)
                .cones(TestConstants.RUN_CONES)
                .didNotFinish(TestConstants.RUN_DID_NOT_FINISH)
                .disqualified(TestConstants.RUN_DISQUALIFIED)
                .rerun(TestConstants.RUN_RERUN)
                .competitive(TestConstants.RUN_COMPETITIVE)
                .build();
    }

    public static AddRunRequest fullAddRun(
            String registrationId,
            Instant timestamp,
            BigDecimal rawTime,
            int cones,
            boolean didNotFinish,
            boolean disqualified,
            boolean rerun,
            boolean competitive
    ) {
        AddRunRequest addRunRequest = new AddRunRequest();
        addRunRequest.setRegistrationId(registrationId);
        addRunRequest.setTimestamp(timestamp);
        addRunRequest.setRawTime(rawTime);
        addRunRequest.setCones(cones);
        addRunRequest.setDidNotFinish(didNotFinish);
        addRunRequest.setDisqualified(disqualified);
        addRunRequest.setRerun(rerun);
        addRunRequest.setCompetitive(competitive);
        return addRunRequest;
    }
}
