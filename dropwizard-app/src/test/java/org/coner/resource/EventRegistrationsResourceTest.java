package org.coner.resource;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.dropwizard.testing.junit.ResourceTestRule;
import org.coner.api.response.GetEventRegistrationsResponse;
import org.coner.boundary.EventBoundary;
import org.coner.boundary.RegistrationBoundary;
import org.coner.core.ConerCoreService;
import org.coner.util.ApiEntityUtils;
import org.coner.util.DomainUtils;
import org.coner.util.TestConstants;
import org.eclipse.jetty.http.HttpStatus;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import javax.ws.rs.NotFoundException;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.failBecauseExceptionWasNotThrown;
import static org.fest.assertions.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 *
 */
public class EventRegistrationsResourceTest {

    private final EventBoundary eventBoundary = mock(EventBoundary.class);
    private final RegistrationBoundary registrationBoundary = mock(RegistrationBoundary.class);
    private final ConerCoreService conerCoreService = mock(ConerCoreService.class);

    private ObjectMapper objectMapper;

    @Rule
    public final ResourceTestRule resources = ResourceTestRule.builder()
            .addResource(new EventRegistrationsResource(eventBoundary, registrationBoundary, conerCoreService))
            .build();


    @Before
    public void setup() {
        reset(eventBoundary, registrationBoundary, conerCoreService);
    }

    @Test
    public void itShouldGetRegistrationsForEvent() throws Exception {

        // Event
        final String eventId = TestConstants.EVENT_ID;

        org.coner.core.domain.Event domainEvent = DomainUtils.fullDomainEvent();

        org.coner.api.entity.Registration.Event apiEvent = ApiEntityUtils.partialApiEvent();

        // Registrations
        final String registrationID = TestConstants.REGISTRATION_ID;

        List<org.coner.core.domain.Registration> domainRegistrations = new ArrayList<>();
        org.coner.core.domain.Registration domainRegistration1 = DomainUtils.fullDomainRegistration();

        List<org.coner.api.entity.Registration> apiRegistrations = new ArrayList<>();
        org.coner.api.entity.Registration apiRegistration1 = ApiEntityUtils.fullApiRegistration();

        domainRegistrations.add(domainRegistration1);
        apiRegistrations.add(apiRegistration1);

        when(conerCoreService.getEvent(eventId)).thenReturn(domainEvent);
        when(conerCoreService.getRegistrations(domainEvent)).thenReturn(domainRegistrations);
        when(registrationBoundary.toApiEntities(domainRegistrations)).thenReturn(apiRegistrations);

        GetEventRegistrationsResponse response = resources.client()
                .target("/events/" + eventId + "/registrations")
                .request(MediaType.APPLICATION_JSON_TYPE)
                .get(GetEventRegistrationsResponse.class);

        verify(conerCoreService).getRegistrations(domainEvent);
        verify(registrationBoundary).toApiEntities(domainRegistrations);

        assertThat(response)
                .isNotNull();
        assertThat(response.getRegistrations())
                .isNotNull()
                .isNotEmpty();
        assertThat(response.getRegistrations().get(0).getId()).isEqualTo(registrationID);
        assertThat(response.getRegistrations().get(0).getEvent()).isEqualTo(apiEvent);
    }

    @Test
    public void itShouldThrowNotFoundExceptionIfEventDoesNotExist() throws Exception {
        final String eventId = "event-test-id";
        try {
            when(conerCoreService.getEvent(eventId)).thenReturn(null);

            GetEventRegistrationsResponse response = resources.client()
                    .target("/events/" + eventId + "/registrations")
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .get(GetEventRegistrationsResponse.class);

            failBecauseExceptionWasNotThrown(NotFoundException.class);
        } catch (NotFoundException nfe) {
            assertThat(nfe.getResponse().getStatus())
                    .isEqualTo(HttpStatus.NOT_FOUND_404);
        }
    }
}