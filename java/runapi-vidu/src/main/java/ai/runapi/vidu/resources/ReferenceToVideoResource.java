package ai.runapi.vidu.resources;

import ai.runapi.core.ClientOptions;
import ai.runapi.core.RequestOptions;
import ai.runapi.core.http.HttpTransport;
import ai.runapi.core.polling.TaskCreateResponse;
import ai.runapi.vidu.types.CompletedReferenceToVideoResponse;
import ai.runapi.vidu.types.ReferenceToVideoParams;
import ai.runapi.vidu.types.ReferenceToVideoResponse;

/** Image To Video operations. */
public final class ReferenceToVideoResource extends ViduResource {
  /** API endpoint path for reference to video operations. */
  public static final String ENDPOINT = "/api/v1/vidu/reference_to_video";

  /** Creates a resource bound to the supplied transport and client options. */
  public ReferenceToVideoResource(HttpTransport transport, ClientOptions options) {
    super(transport, options, ENDPOINT);
  }

  /** Creates a reference to video task. */
  public TaskCreateResponse create(ReferenceToVideoParams params) {
    return create(params, RequestOptions.none());
  }

  /** Creates a reference to video task with per-request options. */
  public TaskCreateResponse create(ReferenceToVideoParams params, RequestOptions options) {
    return createTask(params.action(), params.toMap(), options);
  }

  /** Retrieves a reference to video task by ID. */
  public ReferenceToVideoResponse get(String id) {
    return get(id, RequestOptions.none());
  }

  /** Retrieves a reference to video task by ID with per-request options. */
  public ReferenceToVideoResponse get(String id, RequestOptions options) {
    return getTask(id, options, ReferenceToVideoResponse.class);
  }

  /** Creates a reference to video task and polls until it completes. */
  public CompletedReferenceToVideoResponse run(ReferenceToVideoParams params) {
    return run(params, RequestOptions.none());
  }

  /** Creates a reference to video task with per-request options and polls until it completes. */
  public CompletedReferenceToVideoResponse run(ReferenceToVideoParams params, RequestOptions options) {
    return runTask(params.action(), params.toMap(), options, ReferenceToVideoResponse.class, CompletedReferenceToVideoResponse.class);
  }
}
