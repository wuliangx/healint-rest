package it;

import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.assertThat;
import static play.test.Helpers.GET;
import static play.test.Helpers.contentAsString;
import static play.test.Helpers.route;

import org.junit.Test;

import controllers.post.PostRepository;
import models.Order;
import play.Application;
import play.inject.guice.GuiceApplicationBuilder;
import play.mvc.Http;
import play.mvc.Result;
import play.test.WithApplication;

public class IntegrationTest extends WithApplication {

    @Override
    protected Application provideApplication() {
        return new GuiceApplicationBuilder().build();
    }

    @Test
    public void testList() {
        PostRepository repository = app.injector().instanceOf(PostRepository.class);
        repository.create(new Order.Builder().setId(1).setOrderId("order1").build());

        Http.RequestBuilder request = new Http.RequestBuilder()
                .method(GET)
                .uri("/orders");

        Result result = route(app, request);
        final String body = contentAsString(result);
        assertThat(body, containsString("orderId"));
    }


}
