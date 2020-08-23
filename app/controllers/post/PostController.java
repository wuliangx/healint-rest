package controllers.post;

import com.fasterxml.jackson.databind.JsonNode;

import models.PostOrder;
import play.libs.Json;
import play.libs.concurrent.HttpExecutionContext;
import play.mvc.*;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.concurrent.CompletionStage;
import java.util.stream.Collectors;

@With(PostAction.class)
public class PostController extends Controller {

    private HttpExecutionContext ec;
    private PostResourceHandler handler;
    private static final Logger log = LoggerFactory.getLogger(PostController.class);

    @Inject
    public PostController(HttpExecutionContext ec, PostResourceHandler handler) {
        this.ec = ec;
        this.handler = handler;
    }

    public CompletionStage<Result> list() {
        return handler.find().thenApplyAsync(posts -> {
            final List<PostOrder> postList = posts.collect(Collectors.toList());
            return ok(Json.toJson(postList));
        }, ec.current());
    }

    public CompletionStage<Result> show(String id) {
        return handler.lookup(Integer.parseInt(id)).thenApplyAsync(optionalResource -> {
            return optionalResource.map(resource ->
                ok(Json.toJson(resource))
            ).orElseGet(() ->
                notFound()
            );
        }, ec.current());
    }

    public CompletionStage<Result> update(String id) {
        JsonNode json = request().body().asJson();
        PostOrder resource = Json.fromJson(json, PostOrder.class);
        return handler.update(Integer.parseInt(id), resource).thenApplyAsync(optionalResource -> {
            return optionalResource.map(r ->
                    ok(Json.toJson(r))
            ).orElseGet(() ->
                    notFound()
            );
        }, ec.current()).exceptionally(throwable -> {
        	log.error("Exception updating ", throwable);
        	return internalServerError();
        });
    }

    public CompletionStage<Result> create() {
        JsonNode json = request().body().asJson();
        final PostOrder resource = Json.fromJson(json, PostOrder.class);
        return handler.create(resource).thenApplyAsync(savedResource -> {
            return created(Json.toJson(savedResource));
        }, ec.current()).exceptionally(throwable -> {
        	log.error("Exception creating ", throwable);
        	return internalServerError();
        });
    }
}
