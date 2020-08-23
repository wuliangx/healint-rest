package controllers.post;

import com.palominolabs.http.url.UrlBuilder;

import models.Order;
import models.OrderCodec;
import models.PostOrder;
import play.libs.concurrent.HttpExecutionContext;
import play.mvc.Http;

import javax.inject.Inject;
import java.nio.charset.CharacterCodingException;
import java.util.Optional;
import java.util.concurrent.CompletionStage;
import java.util.stream.Stream;

/**
 * Handles presentation of Post resources, which map to JSON.
 */
public class PostResourceHandler {

    private final PostRepository repository;
    private final HttpExecutionContext ec;
    private final OrderCodec codec;

    @Inject
    public PostResourceHandler(PostRepository repository, HttpExecutionContext ec, OrderCodec codec) {
        this.repository = repository;
        this.ec = ec;
        this.codec = codec;
    }

    public CompletionStage<Stream<PostOrder>> find() {
        return repository.list().thenApplyAsync(postDataStream -> {
            return postDataStream.map(codec::getPostOrder);
        }, ec.current());
    }

    public CompletionStage<PostOrder> create(PostOrder resource) {
        final Order data = codec.getOrder(resource);
        return repository.create(data).thenApplyAsync(codec::getPostOrder, ec.current());
    }

    public CompletionStage<Optional<PostOrder>> lookup(Integer id) {
        return repository.get(id).thenApplyAsync(optionalData -> {
            return optionalData.map(codec::getPostOrder);
        }, ec.current());
    }

    public CompletionStage<Optional<PostOrder>> update(Integer id, PostOrder resource) {
        final Order data = codec.getOrder(resource);
        return repository.update(id, data).thenApplyAsync(optionalData -> {
            return optionalData.map(codec::getPostOrder);
        }, ec.current());
    }
}
