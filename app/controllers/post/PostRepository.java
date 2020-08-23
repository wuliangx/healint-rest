package controllers.post;

import java.util.Optional;
import java.util.concurrent.CompletionStage;
import java.util.stream.Stream;

import models.Order;

public interface PostRepository {

    CompletionStage<Stream<Order>> list();

    CompletionStage<Order> create(Order postData);

    CompletionStage<Optional<Order>> get(Integer orderId);

    CompletionStage<Optional<Order>> update(Integer orderId, Order postData);
}

